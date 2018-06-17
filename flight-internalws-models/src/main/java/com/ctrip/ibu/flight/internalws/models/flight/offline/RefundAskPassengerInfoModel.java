package com.ctrip.ibu.flight.internalws.models.flight.offline;

import com.ctrip.ibu.flight.internalws.models.flight.City;

import java.util.Date;

/**
 * Created by zhangrm on 2017/11/3.
 */
public class RefundAskPassengerInfoModel {
    private String passengerName;

    private String flight;

    private Date dDate;

    private City dCity;

    private City aCity;

    private Integer sequence;

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public Date getdDate() {
        return dDate;
    }

    public void setdDate(Date dDate) {
        this.dDate = dDate;
    }

    public City getdCity() {
        return dCity;
    }

    public void setdCity(City dCity) {
        this.dCity = dCity;
    }

    public City getaCity() {
        return aCity;
    }

    public void setaCity(City aCity) {
        this.aCity = aCity;
    }
}
