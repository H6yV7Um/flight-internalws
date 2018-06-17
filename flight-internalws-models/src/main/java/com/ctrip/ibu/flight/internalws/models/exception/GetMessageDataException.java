package com.ctrip.ibu.flight.internalws.models.exception;

import com.ctrip.ibu.flight.internalws.models.message.MessageCategory;

/**
 * 获取消息数据异常
 * Create by kyxie on 2018/4/14 19:55
 */
public class GetMessageDataException extends Exception {

    private MessageCategory messageCategory;

    private Integer errorCode;

    private String errorMsg;

    public GetMessageDataException(MessageCategory messageCategory,Integer errorCode, String errorMsg){
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
