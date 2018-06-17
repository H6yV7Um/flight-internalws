package com.ctrip.ibu.flight.internalws.models.flight;

/**
 * 订单概要
 * Created by kyxie on 2017/8/17.
 */
public class OrderSummary {

    //订单号
    private Long orderId;

    //订单详情URL
    private String orderDetailUrl;

    //订单状态
    private String actualOrderStatus;

    //航程信息
    private String flightWayInfo;

    private String orderType;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderDetailUrl() {
        return orderDetailUrl;
    }

    public void setOrderDetailUrl(String orderDetailUrl) {
        this.orderDetailUrl = orderDetailUrl;
    }

    public String getActualOrderStatus() {
        return actualOrderStatus;
    }

    public void setActualOrderStatus(String actualOrderStatus) {
        this.actualOrderStatus = actualOrderStatus;
    }

    //航程信息
    public String getFlightWayInfo() {
        return this.flightWayInfo;
    }

    //航程信息
    public void setFlightWayInfo(String flightWayInfo) {
        this.flightWayInfo = flightWayInfo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
