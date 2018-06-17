package com.ctrip.ibu.flight.internalws.models.flight;

import java.math.BigDecimal;

/**
 * 保险
 * Created by kyxie on 2017/9/22.
 */
public class Insurance {

    //所有乘客的保险总额
    private BigDecimal totalPrice;

    //支付币种 保险单价*航段数量
    private BigDecimal origTotalPrice;

    //多币种 保险单价*航段数量
    private BigDecimal showCurrencyOrigTotalPrice;

    //保险购买总份数(目前程序中是取的乘客第一份保险的份数总和) 航意险份数
    private int insuranceCount;

    //所有乘客的保险总额
    private BigDecimal cnyTotalPrice;

    //旅行险份数
    private int travelInsuranceCount;

    //多币种 航意险单价
    private BigDecimal insUnitPrice;

    //多币种 旅行险单价（单乘客类型才用）
    private BigDecimal travelInsUnitPrice;

    //旅行险类型
    private String travelInsType;

    //旅行险总价
    private BigDecimal travelInsTotalPrice;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getOrigTotalPrice() {
        return origTotalPrice;
    }

    public void setOrigTotalPrice(BigDecimal origTotalPrice) {
        this.origTotalPrice = origTotalPrice;
    }

    public BigDecimal getShowCurrencyOrigTotalPrice() {
        return showCurrencyOrigTotalPrice;
    }

    public void setShowCurrencyOrigTotalPrice(BigDecimal showCurrencyOrigTotalPrice) {
        this.showCurrencyOrigTotalPrice = showCurrencyOrigTotalPrice;
    }

    public int getInsuranceCount() {
        return insuranceCount;
    }

    public void setInsuranceCount(int insuranceCount) {
        this.insuranceCount = insuranceCount;
    }

    public BigDecimal getCnyTotalPrice() {
        return cnyTotalPrice;
    }

    public void setCnyTotalPrice(BigDecimal cnyTotalPrice) {
        this.cnyTotalPrice = cnyTotalPrice;
    }

    public int getTravelInsuranceCount() {
        return travelInsuranceCount;
    }

    public void setTravelInsuranceCount(int travelInsuranceCount) {
        this.travelInsuranceCount = travelInsuranceCount;
    }

    public BigDecimal getInsUnitPrice() {
        return insUnitPrice;
    }

    public void setInsUnitPrice(BigDecimal insUnitPrice) {
        this.insUnitPrice = insUnitPrice;
    }

    public BigDecimal getTravelInsUnitPrice() {
        return travelInsUnitPrice;
    }

    public void setTravelInsUnitPrice(BigDecimal travelInsUnitPrice) {
        this.travelInsUnitPrice = travelInsUnitPrice;
    }

    public String getTravelInsType() {
        return travelInsType;
    }

    public void setTravelInsType(String travelInsType) {
        this.travelInsType = travelInsType;
    }

    public BigDecimal getTravelInsTotalPrice() {
        return travelInsTotalPrice;
    }

    public void setTravelInsTotalPrice(BigDecimal travelInsTotalPrice) {
        this.travelInsTotalPrice = travelInsTotalPrice;
    }
}
