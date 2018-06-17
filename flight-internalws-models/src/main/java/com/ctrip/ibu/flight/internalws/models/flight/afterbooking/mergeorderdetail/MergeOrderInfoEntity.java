package com.ctrip.ibu.flight.internalws.models.flight.afterbooking.mergeorderdetail;

/**
 * 合并订单详情实体
 * @author : kyxie
 * date : 2018/5/4 11:23
 */
public class MergeOrderInfoEntity {

    private Long orderId;

    private String oriOrderStatus;

    private String oriActualOrderStatus;

    private PaymentInfoEntity paymentInfo;

    //订单航程类型
    private String flightWay;

    //订单详情URL
    private String orderDetailUrl;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOriOrderStatus() {
        return oriOrderStatus;
    }

    public void setOriOrderStatus(String oriOrderStatus) {
        this.oriOrderStatus = oriOrderStatus;
    }

    public String getOriActualOrderStatus() {
        return oriActualOrderStatus;
    }

    public void setOriActualOrderStatus(String oriActualOrderStatus) {
        this.oriActualOrderStatus = oriActualOrderStatus;
    }

    public PaymentInfoEntity getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfoEntity paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public String getFlightWay() {
        return flightWay;
    }

    public void setFlightWay(String flightWay) {
        this.flightWay = flightWay;
    }

    public String getOrderDetailUrl() {
        return orderDetailUrl;
    }

    public void setOrderDetailUrl(String orderDetailUrl) {
        this.orderDetailUrl = orderDetailUrl;
    }
}
