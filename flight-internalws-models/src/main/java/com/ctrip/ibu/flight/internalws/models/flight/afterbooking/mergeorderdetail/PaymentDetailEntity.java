package com.ctrip.ibu.flight.internalws.models.flight.afterbooking.mergeorderdetail;

import java.math.BigDecimal;

/**
 * 支付明细
 * @author : kyxie
 * date : 2018/5/2 19:23
 */
public class PaymentDetailEntity {

    private String name;

    private Integer priceType;

    private BigDecimal amount;

    private Integer copies;

    private String flightPriceOperateRule;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public String getFlightPriceOperateRule() {
        return flightPriceOperateRule;
    }

    public void setFlightPriceOperateRule(String flightPriceOperateRule) {
        this.flightPriceOperateRule = flightPriceOperateRule;
    }
}
