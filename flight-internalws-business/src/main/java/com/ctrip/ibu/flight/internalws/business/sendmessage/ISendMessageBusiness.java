package com.ctrip.ibu.flight.internalws.business.sendmessage;

import com.ctriposs.baiji.rpc.common.HasResponseStatus;
import com.ctriposs.baiji.specific.SpecificRecord;

/**
 * 消息发送业务接口
 * Create by kyxie on 2018/4/16 11:16
 */
public interface ISendMessageBusiness<TRequest extends SpecificRecord,TResponse extends SpecificRecord & HasResponseStatus> {

    /**
     * 发送消息
     * */
    TResponse sendMessage(TRequest request);

}
