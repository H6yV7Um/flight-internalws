package com.ctrip.ibu.flight.internalws.models.flight.afterbooking.mergeorderdetail;

import com.ctrip.ibu.flight.internalws.models.common.ResponseHead;

import java.util.List;

/**
 * 获取合并订单详情响应实体
 * Created by kyxie on 2018/4/28 16:08
 */
public class MergeOrderDetailResponseEntity {

    private ResponseHead responseHead;

    private List<MergeOrderDetailEntity> orderDetailList;

    public ResponseHead getResponseHead() {
        return responseHead;
    }

    public void setResponseHead(ResponseHead responseHead) {
        this.responseHead = responseHead;
    }

    public List<MergeOrderDetailEntity> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<MergeOrderDetailEntity> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
