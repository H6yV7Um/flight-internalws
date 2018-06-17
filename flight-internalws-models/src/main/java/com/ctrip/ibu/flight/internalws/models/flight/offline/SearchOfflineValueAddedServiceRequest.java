package com.ctrip.ibu.flight.internalws.models.flight.offline;

import com.ctrip.ibu.flight.internalws.models.common.RequestHead;

/**
 * 查询增值服务请求
 * Created by kyxie on 2017/10/30.
 */
public class SearchOfflineValueAddedServiceRequest {

    /**
     * 公共请求头
     */
    private RequestHead requestHead;

    /**
     * 订单号
     */
    private Long orderId;

    /**
     * X产品订单号
     */
    private Long productOrderId;

    /**
     * 支付流水号
     */
    private String externalNo;

    public RequestHead getRequestHead() {
        return requestHead;
    }

    public void setRequestHead(RequestHead requestHead) {
        this.requestHead = requestHead;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(Long productOrderId) {
        this.productOrderId = productOrderId;
    }

    public String getExternalNo() {
        return externalNo;
    }

    public void setExternalNo(String externalNo) {
        this.externalNo = externalNo;
    }
}
