package com.ctrip.ibu.flight.internalws.repository.ws.email;


import com.ctrip.framework.foundation.Foundation;
import com.ctrip.ibu.flight.internalws.models.annotation.WSMethodMeta;
import com.ctrip.ibu.flight.internalws.models.message.EmailHeaderAndFooterRequest;
import com.ctrip.ibu.flight.internalws.models.message.EmailHeaderAndFooterResponse;
import com.ctrip.ibu.flight.internalws.repository.ws.WSMethodInvoker;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.SOACommonHelper;
import com.ctrip.ibu.soa.foundation.contract.common.Head;
import com.ctrip.ibu.soa.foundation.contract.messageapi.api.GetEmailHeaderAndFooterRequestType;
import com.ctrip.ibu.soa.foundation.contract.messageapi.api.GetEmailHeaderAndFooterResponseType;
import com.ctrip.ibu.soa.foundation.contract.messageapi.api.IBUPltMessageAPIClient;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 获取邮件头尾服务
 *
 * 接口说明：
 * http://conf.ctripcorp.com/pages/viewpage.action?pageId=110136366
 *
 * Created by kyxie on 2017/8/14.
 */
@Component("emailHeaderAndFooterWS")
@WSMethodMeta(methodDesc = "获取邮件头尾接口")
public class EmailHeaderAndFooterWS implements WSMethodInvoker<IBUPltMessageAPIClient,EmailHeaderAndFooterRequest,EmailHeaderAndFooterResponse,GetEmailHeaderAndFooterRequestType,GetEmailHeaderAndFooterResponseType> {

    private final static String METHOD_NAME = "getEmailHeaderAndFooter";

    @Override
    public IBUPltMessageAPIClient getClientInstance() {
        return SOACommonHelper.getSoaClientInstance(IBUPltMessageAPIClient.class,"");
    }

    @Override
    public GetEmailHeaderAndFooterRequestType convertRequestToWSRequest(EmailHeaderAndFooterRequest emailHeaderAndFooterRequest) {

        GetEmailHeaderAndFooterRequestType result = new GetEmailHeaderAndFooterRequestType();

        if (emailHeaderAndFooterRequest == null){
            return result;
        }

        Head head = new Head();
        head.setIp(Foundation.net().getHostAddress());
        head.setGroup(String.valueOf(emailHeaderAndFooterRequest.getTrademark()));
        head.setClientSign(Foundation.app().getAppId());
        switch (emailHeaderAndFooterRequest.getFlightClass()){
            case Domestic:
                head.setChannel("FLT");
                break;
            default:
                head.setChannel("FLIT");
                break;
        }
        head.setLocale(getLocaleStr(emailHeaderAndFooterRequest.getLocale()));
        result.setHead(head);
        result.setOrderId(emailHeaderAndFooterRequest.getOrderId());
        result.setEmailType(emailHeaderAndFooterRequest.getEmailType());

        return result;
    }

    @Override
    public GetEmailHeaderAndFooterResponseType invokeMethod(IBUPltMessageAPIClient ibuPltMessageAPIClient, GetEmailHeaderAndFooterRequestType getEmailHeaderAndFooterRequestType) throws Exception {
        return ibuPltMessageAPIClient.getEmailHeaderAndFooter(getEmailHeaderAndFooterRequestType);
    }

    @Override
    public EmailHeaderAndFooterResponse convertWSResponseToResponse(GetEmailHeaderAndFooterResponseType wsResponse) {
        EmailHeaderAndFooterResponse response = new EmailHeaderAndFooterResponse();
        if(wsResponse != null){
            response.setEmailFooter(wsResponse.getEmailFooter());
            response.setEmailHeader(wsResponse.getEmailHeader());
            response.setErrorMsg(wsResponse.getResponseHead().getErrorMessage());
        }else {
            response.setErrorMsg("获取邮件公共头尾响应为空");
        }
        return response;
    }

    @Override
    public String methodName() {
        return METHOD_NAME;
    }

    private String getLocaleStr(Locale locale){
        String localeStr = String.valueOf(locale);
        if ("in_ID".equalsIgnoreCase(localeStr)){
            localeStr = "id_ID";
        }
        return localeStr;
    }
}
