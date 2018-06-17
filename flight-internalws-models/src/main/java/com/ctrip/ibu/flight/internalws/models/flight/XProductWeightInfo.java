package com.ctrip.ibu.flight.internalws.models.flight;

import java.math.BigDecimal;

/**
 * Created by zhangrm on 2018/1/15.
 */
public class XProductWeightInfo {

    //重量单位
    private String weightUnits;

    //当前重量对kg的转换率(units/kg)
    private BigDecimal weightExchangeRate;

    //行李额重量
    private BigDecimal weight;

    //免费行李额重量
    private BigDecimal freeWeight;

    //收费行李额重量
    private BigDecimal chargeWeight;

    public String getWeightUnits() {
        return weightUnits;
    }

    public void setWeightUnits(String weightUnits) {
        this.weightUnits = weightUnits;
    }

    public BigDecimal getWeightExchangeRate() {
        return weightExchangeRate;
    }

    public void setWeightExchangeRate(BigDecimal weightExchangeRate) {
        this.weightExchangeRate = weightExchangeRate;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getFreeWeight() {
        return freeWeight;
    }

    public void setFreeWeight(BigDecimal freeWeight) {
        this.freeWeight = freeWeight;
    }

    public BigDecimal getChargeWeight() {
        return chargeWeight;
    }

    public void setChargeWeight(BigDecimal chargeWeight) {
        this.chargeWeight = chargeWeight;
    }
}
