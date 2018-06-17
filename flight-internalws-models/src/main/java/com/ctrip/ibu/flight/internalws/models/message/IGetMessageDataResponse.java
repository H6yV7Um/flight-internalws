package com.ctrip.ibu.flight.internalws.models.message;

/**
 * 获取消息数据响应接口
 * Create by kyxie on 2018/4/14 19:24
 */
public interface IGetMessageDataResponse<T> {

    /**
     * 获取消息数据
     * */
    T getMessageData();

}
