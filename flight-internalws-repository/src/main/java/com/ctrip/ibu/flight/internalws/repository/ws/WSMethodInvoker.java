package com.ctrip.ibu.flight.internalws.repository.ws;

import com.ctriposs.baiji.rpc.client.ServiceClientBase;

/**
 * WebService方法调用操作
 * 操作类需要实现自己的方法，以及getClient方法，不应该由底层基础类强制指定派生类使用getInstance的无参版本
 * Created by kyxie on 2017/7/1.
 */
public interface WSMethodInvoker<Client extends ServiceClientBase<Client>,TRequest,TResponse, WSTRequest, WSTResponse> {

    /**
     * 获取WebService的Client实例
     * @return SOA调用Client
     * */
    Client getClientInstance();

    /**
     * Request参数转换
     * @param request 原始请求Request
     * @return SOA调用的Request
     * */
    WSTRequest convertRequestToWSRequest(TRequest request);

    /**
     * 调用方法
     * @param client SOA调用Client
     * @param request SOA调用Request
     * @return SOA Response
     * */
    WSTResponse invokeMethod(Client client, WSTRequest request) throws Exception;

    /**
     * Response转换
     * @param wsResponse SOA Response
     * @return 转换后的Response
     * */
    TResponse convertWSResponseToResponse(WSTResponse wsResponse);

    /**
     * 调用的方法名称
     * */
    String methodName();
}
