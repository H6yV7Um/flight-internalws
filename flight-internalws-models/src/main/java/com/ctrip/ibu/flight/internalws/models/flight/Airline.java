package com.ctrip.ibu.flight.internalws.models.flight;

import java.math.BigDecimal;

/**
 * 航司
 * Created by kyxie on 2017/8/16.
 */
public class Airline {

    private String code;

    private String name;

    private String enName;

    private BigDecimal airlineLowestPrice;

    private String belongToAlliance;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public BigDecimal getAirlineLowestPrice() {
        return airlineLowestPrice;
    }

    public void setAirlineLowestPrice(BigDecimal airlineLowestPrice) {
        this.airlineLowestPrice = airlineLowestPrice;
    }

    public String getBelongToAlliance() {
        return belongToAlliance;
    }

    public void setBelongToAlliance(String belongToAlliance) {
        this.belongToAlliance = belongToAlliance;
    }
}
