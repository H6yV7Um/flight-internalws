package com.ctrip.ibu.flight.internalws.models.flight.afterbooking.mergeorderdetail;

import java.math.BigDecimal;
import java.util.List;

/**
 * 支付信息
 * @author : kyxie
 * date : 2018/5/2 19:20
 */
public class PaymentInfoEntity {

    private BigDecimal exchangeRate;

    private Integer dataRule;

    private String currencyType;

    private String merchantInfo;

    private BigDecimal cNYPayTotalPrice;

    private BigDecimal payTotalPrice;

    private Boolean isRealTimePay;

    private Boolean isPayToCBU;

    private Integer cnyCarryRule;

    private List<PaymentDetailEntity> paymentDetailEntityList;

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Integer getDataRule() {
        return dataRule;
    }

    public void setDataRule(Integer dataRule) {
        this.dataRule = dataRule;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getMerchantInfo() {
        return merchantInfo;
    }

    public void setMerchantInfo(String merchantInfo) {
        this.merchantInfo = merchantInfo;
    }

    public BigDecimal getcNYPayTotalPrice() {
        return cNYPayTotalPrice;
    }

    public void setcNYPayTotalPrice(BigDecimal cNYPayTotalPrice) {
        this.cNYPayTotalPrice = cNYPayTotalPrice;
    }

    public BigDecimal getPayTotalPrice() {
        return payTotalPrice;
    }

    public void setPayTotalPrice(BigDecimal payTotalPrice) {
        this.payTotalPrice = payTotalPrice;
    }

    public Boolean getRealTimePay() {
        return isRealTimePay;
    }

    public void setRealTimePay(Boolean realTimePay) {
        isRealTimePay = realTimePay;
    }

    public Boolean getPayToCBU() {
        return isPayToCBU;
    }

    public void setPayToCBU(Boolean payToCBU) {
        isPayToCBU = payToCBU;
    }

    public Integer getCnyCarryRule() {
        return cnyCarryRule;
    }

    public void setCnyCarryRule(Integer cnyCarryRule) {
        this.cnyCarryRule = cnyCarryRule;
    }

    public List<PaymentDetailEntity> getPaymentDetailEntityList() {
        return paymentDetailEntityList;
    }

    public void setPaymentDetailEntityList(List<PaymentDetailEntity> paymentDetailEntityList) {
        this.paymentDetailEntityList = paymentDetailEntityList;
    }
}
