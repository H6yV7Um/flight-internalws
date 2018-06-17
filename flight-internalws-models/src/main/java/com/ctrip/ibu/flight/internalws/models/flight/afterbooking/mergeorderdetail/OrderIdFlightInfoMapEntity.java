package com.ctrip.ibu.flight.internalws.models.flight.afterbooking.mergeorderdetail;

/**
 * author : kyxie
 * date : 2018/5/4 11:04
 */
public class OrderIdFlightInfoMapEntity {

    private Integer fltToken;

    private Long orderId;

    public Integer getFltToken() {
        return fltToken;
    }

    public void setFltToken(Integer fltToken) {
        this.fltToken = fltToken;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
