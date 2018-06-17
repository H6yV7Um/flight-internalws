package com.ctrip.ibu.flight.internalws.business.sendmessage;

import com.ctrip.ibu.flight.internalws.models.exception.MessageSendException;
import com.ctrip.ibu.flight.internalws.models.message.IMessageSendRequest;
import com.ctrip.ibu.flight.internalws.models.message.IMessageSendResponse;

/**
 * 发送消息组件通用接口
 * Create by kyxie on 2018/4/11 22:34
 */
public interface ISendMessageService<TRequest extends IMessageSendRequest,TResponse extends IMessageSendResponse,TException extends MessageSendException> {

    /**
     * 验证参数
     * @param request 请求Request
     * @throws TException 消息发送异常
     * */
    void validateRequest(TRequest request) throws TException;

    /**
     * 发送消息
     * @param request 消息发送请求
     * @throws TException 消息发送异常
     * @return 响应
     * */
    TResponse sendMessage(TRequest request) throws TException;

}
