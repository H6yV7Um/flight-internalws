package com.ctrip.ibu.flight.internalws.models.errorcode.emailsend;

/**
 * 邮件数据错误类型
 * Create by kyxie on 2018/4/11 15:45
 */
public enum EmailSendErrorCode {

    SUCCESS(0, "数据合法"),

    PARAM_ERROR(1,"请求参数错误"),

    LACK_EMAIL_DATA(2,"缺少邮件发送数据"),

    LACK_EMAIL_BODY(3,"邮件发送主体为空"),

    EMAIL_SEND_BUSINESS_ERROR(4,"邮件发送逻辑出现异常"),

    EMAIL_SENDER_ERROR(5,"EmailSender错误"),

    EMAIL_RECIPIENT_ERROR(6,"收件人错误"),

    EMAIL_SEND_SERVICE_ERROR(7,"邮件发送服务错误"),

    LACK_EMAIL_ID(8,"返回结果缺少EmailId"),

    EMAIL_ID_IS_ZERO(9,"EmailId为0");

    private Integer errorCode;

    private String errorMsg;

    EmailSendErrorCode(Integer errorCode, String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
