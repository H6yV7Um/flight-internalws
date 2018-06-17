package com.ctrip.ibu.flight.internalws.models.flight.rebooking;

/**
 * Created by li.l on 2018/1/31.
 */
public class RebookingQueryRequestModel {

    private Long orderID;

    private Long rebookingApplicationID;

    private String flightClass;

    public Long getOrderID(){
        return orderID;
    }

    public void setOrderID(Long orderID){
        this.orderID = orderID;
    }

    public Long getRebookingApplicationID(){
        return rebookingApplicationID;
    }

    public void setRebookingApplicationID(Long rebookingApplicationID){
        this.rebookingApplicationID = rebookingApplicationID;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }
}
