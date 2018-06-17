package com.ctrip.ibu.flight.internalws.models.exception.emailsend;

import com.ctrip.ibu.flight.internalws.models.errorcode.emailsend.EmailSendErrorCode;
import com.ctrip.ibu.flight.internalws.models.exception.MessageSendException;
import com.ctrip.ibu.flight.internalws.models.message.MessageCategory;

/**
 * 邮件发送异常
 * Create by kyxie on 2018/4/11 15:35
 */
public class EmailSendException extends MessageSendException {

    private EmailSendErrorCode emailSendErrorCode;

    private String errorMsg;

    public EmailSendException(EmailSendErrorCode emailSendErrorCode, String errorMsg){
        super(MessageCategory.Email, emailSendErrorCode.getErrorCode(),errorMsg);
        this.emailSendErrorCode = emailSendErrorCode;
        this.errorMsg = errorMsg;
    }

    public EmailSendErrorCode getEmailSendErrorCode() {
        return emailSendErrorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
