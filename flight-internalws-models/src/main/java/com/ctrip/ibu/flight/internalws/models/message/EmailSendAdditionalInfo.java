package com.ctrip.ibu.flight.internalws.models.message;

import com.ctrip.ibu.flight.internalws.models.flight.OrderDetailModel;

/**
 * 邮件发送额外信息
 * Create by kyxie on 2018/4/18 17:31
 */
public class EmailSendAdditionalInfo {

    private OrderDetailModel orderDetail;

    public OrderDetailModel getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailModel orderDetail) {
        this.orderDetail = orderDetail;
    }
}
