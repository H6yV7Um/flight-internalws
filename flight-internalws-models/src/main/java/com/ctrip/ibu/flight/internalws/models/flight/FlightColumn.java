package com.ctrip.ibu.flight.internalws.models.flight;

import java.util.Date;
import java.util.List;

/**
 * 航段
 * Created by kyxie on 2017/8/16.
 */
public class FlightColumn {

    private Integer segmentNo;

    private Integer sequenceNo;

    private String className;

    private CraftType craftType;

    //航班号
    private String flightNo;

    //航空公司
    private Airline airline;

    //航班出发时间
    private Date dDate;

    //航班到达时间
    private Date aDate;

    private City dCity;

    private City aCity;

    private Airport dPort;

    private Airport aPort;

    private Terminal dTerminal;

    private Terminal aTerminal;

    //航班经停信息
    private List<FlightStopInfo> flightStopInfoList;

    //承运航司
    private String carrierAirline;

    //承运航班航班号
    private String carrierFlightNo;

    //免费行李额文案
    private String baggageInfoFree;

    //购买的行李额文案
    private String baggageInfoPaid;

    private List<PassengerBaggagePaidModel> passengerBaggagePaidModelList;

    public Integer getSegmentNo() {
        return segmentNo;
    }

    public void setSegmentNo(Integer segmentNo) {
        this.segmentNo = segmentNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public CraftType getCraftType() {
        return craftType;
    }

    public void setCraftType(CraftType craftType) {
        this.craftType = craftType;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
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

    public Airport getdPort() {
        return dPort;
    }

    public void setdPort(Airport dPort) {
        this.dPort = dPort;
    }

    public Airport getaPort() {
        return aPort;
    }

    public void setaPort(Airport aPort) {
        this.aPort = aPort;
    }

    public Terminal getdTerminal() {
        return dTerminal;
    }

    public void setdTerminal(Terminal dTerminal) {
        this.dTerminal = dTerminal;
    }

    public Terminal getaTerminal() {
        return aTerminal;
    }

    public void setaTerminal(Terminal aTerminal) {
        this.aTerminal = aTerminal;
    }

    public List<FlightStopInfo> getFlightStopInfoList() {
        return flightStopInfoList;
    }

    public void setFlightStopInfoList(List<FlightStopInfo> flightStopInfoList) {
        this.flightStopInfoList = flightStopInfoList;
    }

    public String getCarrierAirline() {
        return carrierAirline;
    }

    public void setCarrierAirline(String carrierAirline) {
        this.carrierAirline = carrierAirline;
    }

    public String getCarrierFlightNo() {
        return carrierFlightNo;
    }

    public void setCarrierFlightNo(String carrierFlightNo) {
        this.carrierFlightNo = carrierFlightNo;
    }

    public String getBaggageInfoFree() {
        return baggageInfoFree;
    }

    public void setBaggageInfoFree(String baggageInfoFree) {
        this.baggageInfoFree = baggageInfoFree;
    }

    public String getBaggageInfoPaid() {
        return baggageInfoPaid;
    }

    public void setBaggageInfoPaid(String baggageInfoPaid) {
        this.baggageInfoPaid = baggageInfoPaid;
    }

    public List<PassengerBaggagePaidModel> getPassengerBaggagePaidModelList() {
        return passengerBaggagePaidModelList;
    }

    public void setPassengerBaggagePaidModelList(List<PassengerBaggagePaidModel> passengerBaggagePaidModelList) {
        this.passengerBaggagePaidModelList = passengerBaggagePaidModelList;
    }

    public Integer getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }
}
