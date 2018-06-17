package com.ctrip.ibu.flight.internalws.models.flight;

/**
 * 票号实体
 * Create by kyxie on 2018/1/31 11:28
 */
public class TicketNoInfo {

    //票号
    private String ticketNo;

    //票号状态
    private String ticketNoStatus;

    //票号状态描述
    private String ticketNoStatusDesc;

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getTicketNoStatus() {
        return ticketNoStatus;
    }

    public void setTicketNoStatus(String ticketNoStatus) {
        this.ticketNoStatus = ticketNoStatus;
    }

    public String getTicketNoStatusDesc() {
        return ticketNoStatusDesc;
    }

    public void setTicketNoStatusDesc(String ticketNoStatusDesc) {
        this.ticketNoStatusDesc = ticketNoStatusDesc;
    }
}
