package com.ctrip.ibu.flight.internalws.models.flight;

import java.util.Date;
import java.util.List;

/**
 * 航班信息
 * Created by kyxie on 2017/8/16.
 */
public class FlightInfo {

    //大的航线序号(从1开始)
    private Integer segmentNo;

    private Date dDate;

    private Date aDate;

    private City dCity;

    private City aCity;

    private String flightRouteSummary;

    //飞行时间（分钟）
    private Integer duration;

    //航班跨天数
    private Integer crossDays;

    //转机机场
    private List<String> transferAirportCodeList;

    //航段信息
    private List<FlightColumn> columnList;

    //航段间隔信息列表
    private List<ColumnLayoverDetail> columnLayoverDetailList;

    //票号信息列表
    private List<TicketNo> ticketNoList;

    public Integer getSegmentNo() {
        return segmentNo;
    }

    public void setSegmentNo(Integer segmentNo) {
        this.segmentNo = segmentNo;
    }

    public Date getdDate() {
        return dDate;
    }

    public void setdDate(Date dDate) {
        this.dDate = dDate;
    }

    public Date getaDate() {
        return aDate;
    }

    public void setaDate(Date aDate) {
        this.aDate = aDate;
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

    public String getFlightRouteSummary() {
        return flightRouteSummary;
    }

    public void setFlightRouteSummary(String flightRouteSummary) {
        this.flightRouteSummary = flightRouteSummary;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getCrossDays() {
        return crossDays;
    }

    public void setCrossDays(Integer crossDays) {
        this.crossDays = crossDays;
    }

    public List<String> getTransferAirportCodeList() {
        return transferAirportCodeList;
    }

    public void setTransferAirportCodeList(List<String> transferAirportCodeList) {
        this.transferAirportCodeList = transferAirportCodeList;
    }

    public List<FlightColumn> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<FlightColumn> columnList) {
        this.columnList = columnList;
    }

    public List<ColumnLayoverDetail> getColumnLayoverDetailList() {
        return columnLayoverDetailList;
    }

    public void setColumnLayoverDetailList(List<ColumnLayoverDetail> columnLayoverDetailList) {
        this.columnLayoverDetailList = columnLayoverDetailList;
    }

    public List<TicketNo> getTicketNoList() {
        return ticketNoList;
    }

    public void setTicketNoList(List<TicketNo> ticketNoList) {
        this.ticketNoList = ticketNoList;
    }
}
