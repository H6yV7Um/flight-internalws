package com.ctrip.ibu.flight.internalws.models.flight.afterbooking.mergeorderdetail;

import com.ctrip.ibu.flight.internalws.models.common.RequestHead;

import java.util.List;

/**
 * 获取合并订单详情请求实体
 * Created by kyxie on 2018/4/28 16:08
 */
public class MergeOrderDetailRequestEntity {

    private RequestHead requestHead;

    private List<Long> orderIdList;

    public RequestHead getRequestHead() {
        return requestHead;
    }

    public void setRequestHead(RequestHead requestHead) {
        this.requestHead = requestHead;
    }

    public List<Long> getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(List<Long> orderIdList) {
        this.orderIdList = orderIdList;
    }
}
