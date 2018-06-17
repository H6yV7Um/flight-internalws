package com.ctrip.ibu.flight.internalws.repository.ws;

import com.ctrip.ibu.flight.internalws.common.components.Checker;
import com.ctrip.ibu.flight.internalws.common.log.LoggerHelper;
import com.ctrip.ibu.flight.internalws.common.utils.DateUtil;
import com.ctrip.ibu.flight.internalws.common.utils.ThrowableUtils;
import com.ctrip.ibu.flight.internalws.models.annotation.WSMethodMeta;
import com.ctrip.ibu.flight.internalws.models.constant.ApplicationConst;
import com.ctrip.ibu.flight.internalws.models.exception.soa.WSInvokeException;
import com.ctrip.ibu.flight.internalws.models.repository.ws.WSMethodMetaInfo;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.SOACommonHelper;
import com.ctriposs.baiji.rpc.client.ServiceClientBase;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebService调用基类
 * Created by kyxie on 2017/7/1.
 */
public final class WSInvokerHelper {

    private WSInvokerHelper() throws IllegalAccessException{
        throw new IllegalAccessException("Static class init forbidden");
    }

    private static final String PARAMETER_CHECK_STAGE = "Parameter check stage";

    private static final String GET_CLIENT_STAGE = "Get client stage";

    private static final String CLIENT_VALIDATE_STAGE = "Validate client stage";

    private static final String REQUEST_CONVERT_STAGE = "Request convert stage";

    private static final String INVOKE_METHOD_STAGE = "Invoke method stage";

    private static final String RESPONSE_CONVERT_STAGE = "Response convert stage";

    private static final ConcurrentHashMap<Class<?>,WSMethodMetaInfo> WSMETHOD_METAINFO_MAP = new ConcurrentHashMap<>();

    private static final Gson GSON = SOACommonHelper.buildGdprExcludeInstance();

    /**
     * 调用SOA服务
     * @param request 请求
     * @param wsMethodInvoker WS服务调用实现
     * @return 响应
     * @throws WSInvokeException WS服务调用异常
     * */
    public static <Client extends ServiceClientBase<Client>,TRequest,TResponse,GaTRequest,GaTResponse> TResponse invokeApi(TRequest request, WSMethodInvoker<Client,TRequest,TResponse,GaTRequest,GaTResponse> wsMethodInvoker)
            throws WSInvokeException {

        Checker.checkWithThrowable(wsMethodInvoker == null || StringUtils.isBlank(wsMethodInvoker.methodName()),new WSInvokeException("MethodInvoker实现为NULL,或没有指定methodName"), () ->
                    LoggerHelper.appendResponseContent(String.format("调用WSInvoker.invokeApi方法时，没有WSMethodInvoker实例\n")));

        Checker.checkWithThrowable(request == null,new WSInvokeException(wsMethodInvoker.methodName(), PARAMETER_CHECK_STAGE, "请求Request为NULL"), () ->
                    LoggerHelper.appendResponseContent(String.format("调用%s接口原始请求为NULL\n", wsMethodInvoker.methodName())));


        //获取Client
        Client client = wsMethodInvoker.getClientInstance();

        Checker.checkWithThrowable(client == null,new WSInvokeException(wsMethodInvoker.methodName(), GET_CLIENT_STAGE,"获取WebService Client为NULL"), () ->
                    LoggerHelper.appendResponseContent(String.format("调用%s接口时getClientInstance方法返回为NULL\n", wsMethodInvoker.methodName())));


        Checker.checkWithThrowable(StringUtils.isBlank(client.getServiceName()) || StringUtils.isBlank(client.getBaseUri()) || StringUtils.isBlank(client.getServiceNamespace()),
                new WSInvokeException(wsMethodInvoker.methodName(),CLIENT_VALIDATE_STAGE,"Client信息不完整"), () -> {
                    LoggerHelper.appendResponseContent("服务Client信息不完整，ServiceName,BaseUri,ServiceNamespace均不能为空");
                });

        LoggerHelper.appendResponseContent(String.format("%s服务Client信息：\nBaseUri:%s\nFormatter:%s\nServiceNamespace:%s",
                client.getServiceName(), client.getBaseUri(), client.getFormat(), client.getServiceNamespace()));

        WSMethodMetaInfo wsMethodMetaInfo = getWSMethodInfo(wsMethodInvoker);

        //Request转换
        GaTRequest gaRequest;
        try{
            gaRequest = wsMethodInvoker.convertRequestToWSRequest(request);

            Checker.checkWithAction(wsMethodMetaInfo.isRecordRequestLog(),() -> {
                LoggerHelper.appendRequestContent(String.format("调用%s接口Request：\n%s", wsMethodInvoker.methodName(), GSON.toJson(gaRequest)));
            },() -> LoggerHelper.appendRequestContent(String.format("----接口%s设置为不需要记录Request日志，不记录Request----",wsMethodInvoker.methodName())));
        } catch (Exception e){
            LoggerHelper.appendRequestContent(String.format("调用%s接口原始请求为：\n%s", wsMethodInvoker.methodName(), GSON.toJson(request)));
            LoggerHelper.appendResponseContent(String.format("调用%s接口Request转换异常,异常信息：\n%s", wsMethodInvoker.methodName(), ThrowableUtils.getExceptionDesc(e)));
            throw ThrowableUtils.getThrowWithCause(new WSInvokeException(wsMethodInvoker.methodName(), REQUEST_CONVERT_STAGE,String.format("%s：%s","Request convert exception",e.getMessage())),e);
        }

        //调用WS接口
        GaTResponse gaResponse;
        Date invokerStartTime = new Date(System.currentTimeMillis());
        Date invokerEndTime;
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            gaResponse = wsMethodInvoker.invokeMethod(client,gaRequest);

            Checker.checkWithAction(wsMethodMetaInfo.isRecordResponseLog(),() ->{
                LoggerHelper.appendResponseContent(String.format("调用接口%s响应为：\n%s", wsMethodInvoker.methodName(), GSON.toJson(gaResponse)));
            },() -> LoggerHelper.appendResponseContent(String.format("----接口%s设置为不需要记录Response日志，不记录Response----",wsMethodInvoker.methodName())));
        } catch (Exception e){
            LoggerHelper.appendResponseContent(String.format("调用接口%s异常，异常信息：\n%s", wsMethodInvoker.methodName(), ThrowableUtils.getExceptionDesc(e)));
            throw ThrowableUtils.getThrowWithCause(new WSInvokeException(wsMethodInvoker.methodName(), INVOKE_METHOD_STAGE,String.format("%s：%s","Invoker method exception",e.getMessage())),e);
        } finally {
            stopWatch.stop();
            invokerEndTime = new Date(System.currentTimeMillis());
            long elapsedTime = stopWatch.getTime();
            LoggerHelper.appendResponseContent(String.format("接口请求开始时间：%s", DateUtil.getDateDesc(invokerStartTime, ApplicationConst.DATETIMEFORMAT_WITHMILLS)));
            LoggerHelper.appendResponseContent(String.format("接口请求结束时间：%s", DateUtil.getDateDesc(invokerEndTime, ApplicationConst.DATETIMEFORMAT_WITHMILLS)));
            LoggerHelper.appendResponseContent(String.format("接口耗时：%d分%d秒%d毫秒",
                    elapsedTime/(60*1000),
                    (elapsedTime%(60*1000))/1000,
                    (elapsedTime%1000)));
        }

        //Response转换
        TResponse response;
        try {
            response = wsMethodInvoker.convertWSResponseToResponse(gaResponse);
        } catch (Exception e){
            LoggerHelper.appendResponseContent(String.format("调用%s接口Response转换异常，异常信息：\n%s",wsMethodInvoker.methodName(),ThrowableUtils.getExceptionDesc(e)));
            throw ThrowableUtils.getThrowWithCause(new WSInvokeException(wsMethodInvoker.methodName(), RESPONSE_CONVERT_STAGE,String.format("%s：%s","Response convert exception",e.getMessage())),e);
        }

        return response;
    }

    /**
     * 获取WS服务接口元信息
     * @param wsMethodInvoker WS方法操作接口的实现类
     * @return WS方法元信息
     * */
    private static WSMethodMetaInfo getWSMethodInfo(WSMethodInvoker wsMethodInvoker){

        WSMethodMetaInfo methodMetaInfo = WSMETHOD_METAINFO_MAP.get(wsMethodInvoker.getClass());

        if (methodMetaInfo != null){
            return methodMetaInfo;
        }

        methodMetaInfo = new WSMethodMetaInfo();

        methodMetaInfo.setMethodName(wsMethodInvoker.methodName());
        methodMetaInfo.setRecordRequestLog(true);
        methodMetaInfo.setRecordResponseLog(true);

        WSMethodMeta meta = wsMethodInvoker.getClass().getAnnotation(WSMethodMeta.class);
        if (meta != null){
            methodMetaInfo.setDesc(meta.methodDesc());
            methodMetaInfo.setRecordRequestLog(meta.recordRequestLog());
            methodMetaInfo.setRecordResponseLog(meta.recordResponseLog());
        }

        WSMETHOD_METAINFO_MAP.put(wsMethodInvoker.getClass(),methodMetaInfo);

        return methodMetaInfo;
    }

}
