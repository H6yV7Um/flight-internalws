package com.ctrip.ibu.flight.internalws.repository.ws.wshelper;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.framework.foundation.Foundation;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.flightcommon.v1.GaTicketNoInfo;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.searchflightpolicyinfo.v1.GaInsurancePolicyDetailInfo;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.searchxorderattached.v1.CheckInInfoEntity;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.searchxorderattached.v1.PaperModifyInfoEntity;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.searchxorderattached.v1.SpecialInfoEntity;
import com.ctrip.ibu.flight.internalws.common.businesshelper.GdprExclusionStrategy;
import com.ctrip.ibu.flight.internalws.common.log.LoggerHelper;
import com.ctrip.ibu.flight.internalws.models.constant.LogConst;
import com.ctrip.ibu.flight.internalws.models.log.IndexedLogTag;
import com.ctrip.ibu.flight.internalws.models.log.LogLevel;
import com.ctrip.ibu.flight.internalws.models.thirdcontract.rebooking.RebookContactInfo;
import com.ctrip.ibu.flight.internalws.models.util.GDPRType;
import com.ctrip.ibu.flight.internalws.repository.ws.WSInvokerHelper;
import com.ctrip.ibu.soa.foundation.contract.accountapi.TokenModel;
import com.ctrip.soa.platform.basesystem.emailservice.v1.SendEmailRequest;
import com.ctriposs.baiji.rpc.client.ServiceClientBase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SOA公共帮助类
 * author : kyxie
 * date : 2018/5/2 11:46
 */
public final class SOACommonHelper {

    private final static ILog CLOG = LogManager.getLogger(SOACommonHelper.class);

    //SOA Client获取自身实例的方法名
    private final static String SOA_CLIENT_GET_INSTANCE_METHOD_NAME = "getInstance";

    //Service Client实例缓存
    private final static Map<Class<? extends ServiceClientBase<?>>,Object> SERVICE_CLIENT_INSTANCE_CACHE = new ConcurrentHashMap<>();

    /**
     * 获取SOAClient实例
     * @param instanceClazz Client类
     * @param testBaseUri 测试的BaseUri（仅对测试环境有效，如果为NULL或者空字符串，会调用Client的无参构造方法）
     * @return SOA Client的实例
     * */
    public static <T extends ServiceClientBase<T>> T getSoaClientInstance(Class<T> instanceClazz, String testBaseUri){
        if (instanceClazz == null){
            return null;
        }

        if (SERVICE_CLIENT_INSTANCE_CACHE.containsKey(instanceClazz)){
            return (T) SERVICE_CLIENT_INSTANCE_CACHE.get(instanceClazz);
        }

        T instance;
        Method getInstanceMethod;

        try {
            //需要在C:\opt\settings\server.properties文件中新增一个isdev的配置，测试代码才会生效
            if (!Foundation.server().getBooleanProperty("isdev",false) || StringUtils.isBlank(testBaseUri)){
                getInstanceMethod = instanceClazz.getMethod(SOA_CLIENT_GET_INSTANCE_METHOD_NAME,null);
                if (!getInstanceMethod.isAccessible()){
                    getInstanceMethod.setAccessible(true);
                }
                instance = (T) getInstanceMethod.invoke(instanceClazz);
            } else {
                getInstanceMethod = instanceClazz.getMethod(SOA_CLIENT_GET_INSTANCE_METHOD_NAME,String.class);
                if (!getInstanceMethod.isAccessible()){
                    getInstanceMethod.setAccessible(true);
                }
                instance = (T) getInstanceMethod.invoke(instanceClazz,testBaseUri);
            }

            if (validateInstance(instance)){
                SERVICE_CLIENT_INSTANCE_CACHE.put(instanceClazz,instance);
            }

            return instance;
        } catch (NoSuchMethodException e) {
            LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel, LogLevel.ERROR.toString());
            LoggerHelper.appendResponseContent(String.format("获取Soa Client时缺少%s方法，无法获取",SOA_CLIENT_GET_INSTANCE_METHOD_NAME));
            CLOG.error(LogConst.Clog.LogTitle.WS_LOG,String.format("获取Soa Client时缺少%s方法，无法获取",SOA_CLIENT_GET_INSTANCE_METHOD_NAME));
        } catch (IllegalAccessException e) {
            LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel, LogLevel.ERROR.toString());
            LoggerHelper.appendResponseContent(String.format("获取Soa Client时,%s方法IllegalAccess",SOA_CLIENT_GET_INSTANCE_METHOD_NAME));
            CLOG.error(LogConst.Clog.LogTitle.WS_LOG,String.format("获取Soa Client时,%s方法IllegalAccess",SOA_CLIENT_GET_INSTANCE_METHOD_NAME));
        } catch (InvocationTargetException e) {
            LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel, LogLevel.ERROR.toString());
            LoggerHelper.appendResponseContent(String.format("获取Soa Client时缺少%s方法，无法获取",SOA_CLIENT_GET_INSTANCE_METHOD_NAME));
            CLOG.error(LogConst.Clog.LogTitle.WS_LOG,String.format("调用类%s的%s方法获取实例异常",instanceClazz.getName(),SOA_CLIENT_GET_INSTANCE_METHOD_NAME));
        }
        return null;
    }

    /**
     * 验证Client实例有效性
     * */
    private static <T extends ServiceClientBase<T>> boolean validateInstance(T client){
        return client != null && StringUtils.isNotBlank(client.getBaseUri());
    }

    /**
     * 创建一个GDPR过滤的Gson实例
     * todo:合并订单详情GDPR
     * */
    public static Gson buildGdprExcludeInstance(){
        return new GsonBuilder()
                .addSerializationExclusionStrategy(GdprExclusionStrategy.Builder.newInstance(WSInvokerHelper.class)
                        .registerExclusion(SendEmailRequest.class,"recipient",GDPRType.GDPR_USER_EMAIL)
                        .registerExclusion(SendEmailRequest.class,"cc",GDPRType.GDPR_USER_EMAIL)
                        .registerExclusion(SendEmailRequest.class,"bcc",GDPRType.GDPR_USER_EMAIL)
                        .registerExclusion(SendEmailRequest.class,"recipientName",GDPRType.GDPR_USER_NAME)
                        .registerExclusion(TokenModel.class,"email",GDPRType.GDPR_USER_EMAIL)
                        .registerExclusion(GaTicketNoInfo.class,"passengerName",GDPRType.GDPR_USER_NAME)
                        .registerExclusion(GaInsurancePolicyDetailInfo.class,"passengerName",GDPRType.GDPR_USER_NAME)
                        .registerExclusion(GaInsurancePolicyDetailInfo.class,"cardNo",GDPRType.GDPR_USER_IDCARDNO)
                        .registerExclusion(RebookContactInfo.class,"contactor",GDPRType.GDPR_CONTACT_NAME)
                        .registerExclusion(RebookContactInfo.class,"contactPhoneNo",GDPRType.GDPR_CONTACT_MOBILEPHONE)
                        .registerExclusion(RebookContactInfo.class,"contactEmail",GDPRType.GDPR_USER_EMAIL)
                        .registerExclusion(PaperModifyInfoEntity.class,"passengerName",GDPRType.GDPR_USER_NAME)
                        .registerExclusion(PaperModifyInfoEntity.class,"originalPapersInfo",GDPRType.GDPR_USER_IDCARDNO)
                        .registerExclusion(PaperModifyInfoEntity.class,"modifiedPapersInfo",GDPRType.GDPR_USER_IDCARDNO)
                        .registerExclusion(CheckInInfoEntity.class,"passengerName",GDPRType.GDPR_USER_NAME)
                        .registerExclusion(SpecialInfoEntity.class,"passengerName",GDPRType.GDPR_USER_NAME)
                        .build())
                .disableHtmlEscaping()
                .create();
    }
}
