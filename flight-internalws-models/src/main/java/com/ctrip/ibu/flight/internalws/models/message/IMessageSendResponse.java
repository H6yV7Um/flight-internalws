package com.ctrip.ibu.flight.internalws.models.message;

/**
 * 消息发送响应实体
 * Create by kyxie on 2018/4/12 12:16
 */
public interface IMessageSendResponse {

    /**
     * 获取发送结果
     * */
    Integer getSendResultCode();

    /**
     * 获取发送结果描述
     * */
    String getSendResultMsg();

}
