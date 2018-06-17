package com.ctrip.ibu.flight.internalws.models.exception.emailsend;

import com.ctrip.ibu.flight.internalws.contract.commontypes.EmailTemplateType;
import com.ctrip.ibu.flight.internalws.models.errorcode.emailsend.GetEmailDataImplResultCode;

/**
 * 获取邮件数据实现异常
 * Create by kyxie on 2018/4/14 22:13
 */
public class GetEmailDataImplException extends Exception{

    private EmailTemplateType emailTemplate;

    private GetEmailDataImplResultCode resultCode;

    private String errorMsg;

    public GetEmailDataImplException(EmailTemplateType emailTemplate,GetEmailDataImplResultCode resultCode,String errorMsg){
        this.emailTemplate = emailTemplate;
        this.resultCode = resultCode;
        this.errorMsg = errorMsg;
    }

    public EmailTemplateType getEmailTemplate() {
        return emailTemplate;
    }

    public GetEmailDataImplResultCode getResultCode() {
        return resultCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
