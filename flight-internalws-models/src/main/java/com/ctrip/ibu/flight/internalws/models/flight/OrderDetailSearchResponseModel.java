package com.ctrip.ibu.flight.internalws.models.flight;

import com.ctrip.ibu.flight.internalws.models.common.ResponseHead;

import java.util.List;

/**
 * 订单详情查询Response模型
 * Created by kyxie on 2017/8/11.
 */
public class OrderDetailSearchResponseModel {

    private ResponseHead responseHead;

    private List<OrderDetailModel> orderDetailList;

    public ResponseHead getResponseHead() {
        return responseHead;
    }

    public void setResponseHead(ResponseHead responseHead) {
        this.responseHead = responseHead;
    }

    public List<OrderDetailModel> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetailModel> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
