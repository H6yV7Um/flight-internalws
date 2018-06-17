package com.ctrip.ibu.flight.internalws.models.viewmodel.paymentsuccess.partialviewmodel;

import com.ctrip.ibu.flight.internalws.models.flight.Currency;
import com.ctrip.ibu.flight.internalws.models.flight.FlightInfo;
import com.ctrip.ibu.flight.internalws.models.flight.FlightWay;
import com.ctrip.ibu.flight.internalws.models.flight.afterbooking.mergeorderdetail.PaymentDetailEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单信息
 * @author : kyxie
 * createtime : 2018/5/17 15:23
 */
public class OrderInfoSummary {

    private Long orderId;

    private FlightWay flightWay;

    private List<FlightInfo> flightInfoList;

    private List<PaymentDetailEntity> paymentItemDetailList;

    private BigDecimal totalPayment;

    private Currency currency;

    private String orderDetailUrl;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public FlightWay getFlightWay() {
        return flightWay;
    }

    public void setFlightWay(FlightWay flightWay) {
        this.flightWay = flightWay;
    }

    public List<FlightInfo> getFlightInfoList() {
        return flightInfoList;
    }

    public void setFlightInfoList(List<FlightInfo> flightInfoList) {
        this.flightInfoList = flightInfoList;
    }

    public List<PaymentDetailEntity> getPaymentItemDetailList() {
        return paymentItemDetailList;
    }

    public void setPaymentItemDetailList(List<PaymentDetailEntity> paymentItemDetailList) {
        this.paymentItemDetailList = paymentItemDetailList;
    }

    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getOrderDetailUrl() {
        return orderDetailUrl;
    }

    public void setOrderDetailUrl(String orderDetailUrl) {
        this.orderDetailUrl = orderDetailUrl;
    }
}
