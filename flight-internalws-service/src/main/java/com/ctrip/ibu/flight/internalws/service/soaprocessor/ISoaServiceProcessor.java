package com.ctrip.ibu.flight.internalws.service.soaprocessor;

import com.ctriposs.baiji.rpc.common.HasResponseStatus;
import com.ctriposs.baiji.specific.SpecificRecord;

/**
 * SOA服务方法处理接口
 * Create by kyxie on 2017/12/25 18:01
 */
public interface ISoaServiceProcessor<TRequest extends SpecificRecord,TResponse extends SpecificRecord & HasResponseStatus> {

    /**
     * 验证请求
     * @param request 原始请求
     * @throws IllegalArgumentException 参数不合法异常
     * */
    void validateRequest(TRequest request) throws IllegalArgumentException;

    /**
     * 处理SOA请求
     * @param request 原始请求
     * @return 对外响应
     * @
     * */
    TResponse processSoa(TRequest request);

}
