package com.ctrip.ibu.flight.internalws.models.flight;

/**
 * 票号
 * Created by kyxie on 2017/8/16.
 */
public class TicketNo {

    private String passengerName;

    private String ticketNo;

    private String status;

    private String statusDesc;

    private String tripRecordStatusDesc;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getTripRecordStatusDesc() {
        return tripRecordStatusDesc;
    }

    public void setTripRecordStatusDesc(String tripRecordStatusDesc) {
        this.tripRecordStatusDesc = tripRecordStatusDesc;
    }
}
