package com.ctrip.ibu.flight.internalws.models.exception;

import com.ctrip.ibu.flight.internalws.models.message.MessageCategory;

/**
 * 消息发送异常
 * Create by kyxie on 2018/4/12 11:59
 */
public class MessageSendException extends Exception {

    private MessageCategory messageCategory;

    private Integer errorCode;

    private String errorMsg;

    public MessageSendException(MessageCategory messageCategory,Integer errorCode, String errorMsg){
        super(errorMsg);
        this.messageCategory = messageCategory;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public MessageCategory getMessageCategory() {
        return messageCategory;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
