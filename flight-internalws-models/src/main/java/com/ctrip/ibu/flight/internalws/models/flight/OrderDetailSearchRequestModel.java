package com.ctrip.ibu.flight.internalws.models.flight;

import com.ctrip.ibu.flight.internalws.models.common.RequestHead;

import java.util.List;

/**
 * 订单详情查询Request模型
 * Created by kyxie on 2017/7/1.
 */
public class OrderDetailSearchRequestModel {

    private RequestHead requestHead;

    private List<Long> orderIds;

    public RequestHead getRequestHead() {
        return requestHead;
    }

    public void setRequestHead(RequestHead requestHead) {
        this.requestHead = requestHead;
    }

    public List<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Long> orderIds) {
        this.orderIds = orderIds;
    }

}
