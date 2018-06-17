package com.ctrip.ibu.flight.internalws.models.viewmodel.paymentsuccess.partialviewmodel;

import com.ctrip.ibu.flight.internalws.models.flight.Currency;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : kyxie
 * createtime : 2018/5/17 15:24
 */
public class PaymentSummary {

    private Currency paymentCurrency;

    private BigDecimal totalPrice;

    private List<PassengerOrderInfo> passengerOrderInfoList;

    public Currency getPaymentCurrency() {
        return paymentCurrency;
    }

    public void setPaymentCurrency(Currency paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<PassengerOrderInfo> getPassengerOrderInfoList() {
        return passengerOrderInfoList;
    }

    public void setPassengerOrderInfoList(List<PassengerOrderInfo> passengerOrderInfoList) {
        this.passengerOrderInfoList = passengerOrderInfoList;
    }

}
