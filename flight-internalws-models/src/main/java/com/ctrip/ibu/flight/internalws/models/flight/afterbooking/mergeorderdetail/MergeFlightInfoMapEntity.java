package com.ctrip.ibu.flight.internalws.models.flight.afterbooking.mergeorderdetail;

import com.ctrip.ibu.flight.internalws.models.flight.FlightColumn;

/**
 * 航段信息
 * author : kyxie
 * date : 2018/5/4 11:17
 */
public class MergeFlightInfoMapEntity {

    private Integer fltToken;

    private FlightColumn flightColumnInfo;

    public Integer getFltToken() {
        return fltToken;
    }

    public void setFltToken(Integer fltToken) {
        this.fltToken = fltToken;
    }

    public FlightColumn getFlightColumnInfo() {
        return flightColumnInfo;
    }

    public void setFlightColumnInfo(FlightColumn flightColumnInfo) {
        this.flightColumnInfo = flightColumnInfo;
    }
}
