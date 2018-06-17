package com.ctrip.ibu.flight.internalws.models.flight;

import com.ctrip.ibu.flight.internalws.models.common.RequestHead;

/**
 * 保单详情查询模型
 * Created by kyxie on 2017/8/11.
 */
public class InsuranceDetailSearchRequestModel {

    //请求头
    private RequestHead requestHead;

    //订单号
    private Long orderId;

    //保单号
    private String insuranceNo;

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

    public String getInsuranceNo() {
        return insuranceNo;
    }

    public void setInsuranceNo(String insuranceNo) {
        this.insuranceNo = insuranceNo;
    }
}
