package com.ctrip.ibu.flight.internalws.models.flight;

/**
 * Created by zhangrm on 2018/1/16.
 */
public class SegmentValueAddedBaggagePassengerDetail {

    private String passengerName;

    private XProductPriceInfo xProductPriceInfo;

    private XProductWeightInfo xProductWeightInfo;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public XProductPriceInfo getxProductPriceInfo() {
        return xProductPriceInfo;
    }

    public void setxProductPriceInfo(XProductPriceInfo xProductPriceInfo) {
        this.xProductPriceInfo = xProductPriceInfo;
    }

    public XProductWeightInfo getxProductWeightInfo() {
        return xProductWeightInfo;
    }

    public void setxProductWeightInfo(XProductWeightInfo xProductWeightInfo) {
        this.xProductWeightInfo = xProductWeightInfo;
    }
}
