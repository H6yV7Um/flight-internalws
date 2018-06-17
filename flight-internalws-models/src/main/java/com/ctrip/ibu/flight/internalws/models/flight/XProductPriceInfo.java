package com.ctrip.ibu.flight.internalws.models.flight;

import java.math.BigDecimal;

/**
 * Created by zhangrm on 2018/1/15.
 */
public class XProductPriceInfo {

    //币种
    private String currency;

    //预定价格
    private BigDecimal salePrice;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
}
