package com.ctrip.ibu.flight.internalws.models.flight;

import java.util.List;

/**
 * X产品信息
 * Created by kyxie on 2017/9/22.
 */
public class XProduct {

    //增值行李额
    private ValueAddedBaggageInfo passengerValueAddedBaggage;

    //贵宾休息室
    private List<AirportLounge> airportLoungeList;

    //X产品酒店优惠券
    private List<XCoupon> xCouponList;

    //X产品值机信息
    private List<XCheckIn> xCheckInList;

    public ValueAddedBaggageInfo getPassengerValueAddedBaggage() {
        return passengerValueAddedBaggage;
    }

    public void setPassengerValueAddedBaggage(ValueAddedBaggageInfo passengerValueAddedBaggage) {
        this.passengerValueAddedBaggage = passengerValueAddedBaggage;
    }

    public List<AirportLounge> getAirportLoungeList() {
        return airportLoungeList;
    }

    public void setAirportLoungeList(List<AirportLounge> airportLoungeList) {
        this.airportLoungeList = airportLoungeList;
    }

    public List<XCoupon> getxCouponList() {
        return xCouponList;
    }

    public void setxCouponList(List<XCoupon> xCouponList) {
        this.xCouponList = xCouponList;
    }

    public List<XCheckIn> getxCheckInList() {
        return xCheckInList;
    }

    public void setxCheckInList(List<XCheckIn> xCheckInList) {
        this.xCheckInList = xCheckInList;
    }
}
