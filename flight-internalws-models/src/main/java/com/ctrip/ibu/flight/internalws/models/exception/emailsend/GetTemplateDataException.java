package com.ctrip.ibu.flight.internalws.models.exception.emailsend;

import com.ctrip.ibu.flight.internalws.models.errorcode.emailsend.EmailBodyDataErrorCode;

/**
 * 获取模板数据异常
 * Created by kyxie on 2017/8/25.
 */
public class GetTemplateDataException extends Exception {

    //模板名
    private String templateName;

    //模板数据名称
    private String templateDataName;

    //数据错误类型
    private EmailBodyDataErrorCode emailBodyDataErrorCode;

    //错误信息
    private String errMsg;

    public GetTemplateDataException(String templateName, String templateDataName, EmailBodyDataErrorCode emailBodyDataErrorCode, String errMsg) {
        super(errMsg);
        this.templateName = templateName;
        this.templateDataName = templateDataName;
        this.emailBodyDataErrorCode = emailBodyDataErrorCode;
        this.errMsg = errMsg;
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getTemplateDataName() {
        return templateDataName;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public EmailBodyDataErrorCode getEmailBodyDataErrorCode() {
        return emailBodyDataErrorCode;
    }
}
