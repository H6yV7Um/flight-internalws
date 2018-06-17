package com.ctrip.ibu.flight.internalws.business.sendmessage;

import com.ctrip.ibu.flight.internalws.models.exception.GetMessageDataException;
import com.ctrip.ibu.flight.internalws.models.message.IGetMessageDataRequest;
import com.ctrip.ibu.flight.internalws.models.message.IGetMessageDataResponse;

/**
 * 获取消息数据接口
 * Create by kyxie on 2018/4/14 19:54
 */
public interface IGetMessageDataService <TRequest extends IGetMessageDataRequest,TResponse extends IGetMessageDataResponse,TException extends GetMessageDataException> {

    /**
     * 验证参数
     * @param request 请求
     * @throws TException 获取消息异常
     * */
    void validateRequest(TRequest request) throws TException;

    /**
     * 获取消息发送信息
     * @param request 获取消息数据请求
     * @throws TException 获取消息异常类型
     * @return 获取消息数据响应
     * */
    TResponse getMessageSendInfo(TRequest request) throws TException;
}
