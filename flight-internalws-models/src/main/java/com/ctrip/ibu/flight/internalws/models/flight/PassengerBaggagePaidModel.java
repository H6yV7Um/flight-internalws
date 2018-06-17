package com.ctrip.ibu.flight.internalws.models.flight;

public class PassengerBaggagePaidModel {

    private String passengerName;

    private int pkgNumber;

    private int baggageStatus;

    private XProductPriceInfo priceInfo;

    private XProductWeightInfo weightInfo;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getPkgNumber() {
        return pkgNumber;
    }

    public void setPkgNumber(int pkgNumber) {
        this.pkgNumber = pkgNumber;
    }

    public int getBaggageStatus() {
        return baggageStatus;
    }

    public void setBaggageStatus(int baggageStatus) {
        this.baggageStatus = baggageStatus;
    }

    public XProductPriceInfo getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(XProductPriceInfo priceInfo) {
        this.priceInfo = priceInfo;
    }

    public XProductWeightInfo getWeightInfo() {
        return weightInfo;
    }

    public void setWeightInfo(XProductWeightInfo weightInfo) {
        this.weightInfo = weightInfo;
    }
}
