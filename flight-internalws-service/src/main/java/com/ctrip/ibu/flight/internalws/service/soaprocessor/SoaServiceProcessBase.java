package com.ctrip.ibu.flight.internalws.service.soaprocessor;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.framework.clogging.agent.metrics.aggregator.MetricsAggregator;
import com.ctrip.ibu.flight.internalws.common.components.Checker;
import com.ctrip.ibu.flight.internalws.common.components.HttpContext;
import com.ctrip.ibu.flight.internalws.common.components.SoaServiceMetaInfo;
import com.ctrip.ibu.flight.internalws.common.components.SoaServiceUtils;
import com.ctrip.ibu.flight.internalws.common.log.LoggerHelper;
import com.ctrip.ibu.flight.internalws.common.log.MetricsBuilder;
import com.ctrip.ibu.flight.internalws.contract.requestheader.RequestHeader;
import com.ctrip.ibu.flight.internalws.contract.responseheader.ResponseHeader;
import com.ctrip.ibu.flight.internalws.models.errorcode.soa.SoaErrorCode;
import com.ctrip.ibu.flight.internalws.models.log.IndexedLogTag;
import com.ctrip.ibu.flight.internalws.models.log.LogLevel;
import com.ctrip.ibu.flight.internalws.models.log.StoredLogTag;
import com.ctriposs.baiji.rpc.common.HasResponseStatus;
import com.ctriposs.baiji.rpc.common.types.AckCodeType;
import com.ctriposs.baiji.rpc.common.types.ErrorDataType;
import com.ctriposs.baiji.rpc.common.types.ResponseStatusType;
import com.ctriposs.baiji.specific.SpecificRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Calendar;
import java.util.Collections;

/**
 * SOA服务处理基类
 * Create by kyxie on 2018/3/14 17:35
 */
public abstract class SoaServiceProcessBase <TRequest extends SpecificRecord,TResponse extends SpecificRecord & HasResponseStatus> implements ISoaServiceProcessor<TRequest,TResponse>{

    private final static ILog CLOG = LogManager.getLogger(SoaServiceProcessBase.class);

    private final static String REQUEST_HEADER_NAME = "requestHeader";

    private final static String RESPONSE_HEADER_NAME = "responseHeader";

    private final static String RESPONSE_STATUS_NAME = "responseStatus";

    private final static MetricsAggregator defaultMetrics = createDefaultMetrics();

    /**
     * 处理SOA请求
     * @param request 请求
     * @return 响应
     * */
    public TResponse process(TRequest request){

        HttpContext.clear();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        TResponse response = null;

        SoaServiceMetaInfo serviceMetaInfo = getSoaServiceMetaInfo();

        try {
            //获取泛型Response的真实类型
            Class<TResponse> responseClass = (Class<TResponse>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];

            response = responseClass.newInstance();

            RequestHeader requestHeader = getRequestHeader(request);

            initLog(requestHeader,serviceMetaInfo);

            this.validateRequest(request);

            response = this.processSoa(request);

            settingResponseStatusAndHeader(response, SoaErrorCode.SUCCESS.getErrorCode(),SoaErrorCode.SUCCESS.getErrorMsg());

        } catch (IllegalArgumentException e){
            settingResponseStatusAndHeader(response, SoaErrorCode.PARAM_ILLEGAL.getErrorCode(),SoaErrorCode.PARAM_ILLEGAL.getErrorMsg());
        } catch (Exception e){
            settingResponseStatusAndHeader(response, SoaErrorCode.SYSTEM_ERROR.getErrorCode(),SoaErrorCode.SYSTEM_ERROR.getErrorMsg());
        } catch (Throwable t){
            settingResponseStatusAndHeader(response, SoaErrorCode.FATAL_ERROR.getErrorCode(),SoaErrorCode.FATAL_ERROR.getErrorMsg());
        } finally {

            stopWatch.stop();

            postProcessLog(serviceMetaInfo);

            LoggerHelper.addStoredLogTag(StoredLogTag.Interval,String.valueOf(stopWatch.getTime()));

            if (enableLog(serviceMetaInfo)){
                LoggerHelper.doLog();
            }

            HttpContext.clear();
        }

        return response;
    }

    private boolean enableLog(SoaServiceMetaInfo serviceMetaInfo){
        return serviceMetaInfo != null && serviceMetaInfo.isEnableLog();
    }

    /**
     * 获取请求头
     * @param request 原始请求
     * @return 请求头
     * */
    private RequestHeader getRequestHeader(TRequest request) throws NoSuchFieldException, IllegalAccessException {
        Checker.isNotNull(request,"request");

        Field requestHeaderField = request.getClass().getDeclaredField(REQUEST_HEADER_NAME);
        Checker.isNotNull(requestHeaderField,REQUEST_HEADER_NAME);
        if (!requestHeaderField.isAccessible()){
            requestHeaderField.setAccessible(true);
        }
        return (RequestHeader) requestHeaderField.get(request);
    }

    /**
     * 获取SOA接口处理类信息
     * */
    private SoaServiceMetaInfo getSoaServiceMetaInfo(){
        SoaServiceMetaInfo soaServiceMetaInfo = SoaServiceUtils.getSoaServiceInfo(this.getClass());

        if (soaServiceMetaInfo == null){
            throw new RuntimeException(String.format("%s class need \"SoaServiceProcessor\" annotation.",this.getClass().getName()));
        }

        return soaServiceMetaInfo;
    }

    /**
     * 初始化Log
     * @param requestHeader 请求头
     * @param serviceMetaInfo 服务元信息
     * */
    private void initLog(RequestHeader requestHeader,SoaServiceMetaInfo serviceMetaInfo){

        LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel, LogLevel.INFO.toString());
        LoggerHelper.addIndexedLogTag(IndexedLogTag.ActionId, serviceMetaInfo.getActionId());
        LoggerHelper.addIndexedLogTag(IndexedLogTag.ActionName, serviceMetaInfo.getActionName());
        LoggerHelper.addIndexedLogTag(IndexedLogTag.Note, serviceMetaInfo.getActionNote());
        LoggerHelper.addIndexedLogTag(IndexedLogTag.AppId, requestHeader != null && StringUtils.isNotBlank(requestHeader.appId) ? requestHeader.appId : "");
    }

    /**
     * 后处理Log
     * @param serviceMetaInfo 服务元信息
     * */
    private void postProcessLog(SoaServiceMetaInfo serviceMetaInfo){
        if (serviceMetaInfo != null && serviceMetaInfo.isEnableMetrics()){
            defaultMetrics.add(1L,serviceMetaInfo.getActionId());
        }
    }

    /**
     * 设置ResponseStatus及ResponseHeader
     * */
    private void settingResponseStatusAndHeader(TResponse response, Integer errorCode, String errorMessage) {

        //set ResponseHead
        ResponseHeader responseHead = new ResponseHeader();
        responseHead.setResultCode(errorCode);
        responseHead.setErrorMsg(errorMessage);

        //set ResponseStatusType
        ResponseStatusType responseStatusType = new ResponseStatusType();
        responseStatusType.setTimestamp(Calendar.getInstance());
        responseStatusType.setAck(AckCodeType.Success);

        ErrorDataType errorDataType = new ErrorDataType();
        errorDataType.setErrorCode(String.valueOf(errorCode));
        errorDataType.setMessage(errorMessage);
        responseStatusType.setErrors(Collections.singletonList(errorDataType));

        try {
            Class<?> clazz = response.getClass();
            clazz.getDeclaredField(RESPONSE_HEADER_NAME).set(response, responseHead);
            clazz.getDeclaredField(RESPONSE_STATUS_NAME).set(response, responseStatusType);
        } catch (Throwable ex) {
            CLOG.error("SoaServiceProcessBase",ex);
        }
    }

    private static MetricsAggregator createDefaultMetrics(){
        return MetricsBuilder.newBuilder(String.format("%1$s.%2$s","ibu.flight.internalws","soamonitor.count"))
                .addTag("methodName")
                .build();
    }
}
