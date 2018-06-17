package com.ctrip.ibu.flight.internalws.models.flight;

import java.math.BigDecimal;

/**
 * 改签支付明细
 * Created by kyxie on 2017/10/17.
 */
public class RebookingPayDetail {

    private String currency;

    private BigDecimal currencyRate;

    private BigDecimal totalRebookFee;

    private BigDecimal dateChangeFee;

    private BigDecimal priceDifferential;

    private BigDecimal taxDifferential;

    private BigDecimal supplierServiceFeeForCustom;

    private BigDecimal ctripServiceFeeForCustom;

    private BigDecimal airlineServiceFeeForCustom;

    private BigDecimal fCardServiceFee;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
    }

    public BigDecimal getTotalRebookFee() {
        return totalRebookFee;
    }

    public void setTotalRebookFee(BigDecimal totalRebookFee) {
        this.totalRebookFee = totalRebookFee;
    }

    public BigDecimal getDateChangeFee() {
        return dateChangeFee;
    }

    public void setDateChangeFee(BigDecimal dateChangeFee) {
        this.dateChangeFee = dateChangeFee;
    }

    public BigDecimal getPriceDifferential() {
        return priceDifferential;
    }

    public void setPriceDifferential(BigDecimal priceDifferential) {
        this.priceDifferential = priceDifferential;
    }

    public BigDecimal getTaxDifferential() {
        return taxDifferential;
    }

    public void setTaxDifferential(BigDecimal taxDifferential) {
        this.taxDifferential = taxDifferential;
    }

    public BigDecimal getSupplierServiceFeeForCustom() {
        return supplierServiceFeeForCustom;
    }

    public void setSupplierServiceFeeForCustom(BigDecimal supplierServiceFeeForCustom) {
        this.supplierServiceFeeForCustom = supplierServiceFeeForCustom;
    }

    public BigDecimal getCtripServiceFeeForCustom() {
        return ctripServiceFeeForCustom;
    }

    public void setCtripServiceFeeForCustom(BigDecimal ctripServiceFeeForCustom) {
        this.ctripServiceFeeForCustom = ctripServiceFeeForCustom;
    }

    public BigDecimal getAirlineServiceFeeForCustom() {
        return airlineServiceFeeForCustom;
    }

    public void setAirlineServiceFeeForCustom(BigDecimal airlineServiceFeeForCustom) {
        this.airlineServiceFeeForCustom = airlineServiceFeeForCustom;
    }

    public BigDecimal getfCardServiceFee() {
        return fCardServiceFee;
    }

    public void setfCardServiceFee(BigDecimal fCardServiceFee) {
        this.fCardServiceFee = fCardServiceFee;
    }
}
