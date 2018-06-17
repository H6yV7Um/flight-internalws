package com.ctrip.ibu.flight.internalws.models.flight;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

/**
 * 改签信息
 * Created by kyxie on 2017/10/17.
 */
public class RebookingInfo {

    private String rebookingApplicationId;

    private Long orderId;

    private String applicationStatus;

    private Calendar requestTime;

    private String payExternalNo;

    private BigDecimal fCardServiceFee;

    private Calendar paymentDeadline;

    private String baggageRemark;

    private String flightDescription;

    private String airline;

    private String bookSeatType;

    private ContactInfo rebookingContactInfo;

    private List<FlightInfo> rebookingFlightItemList;

    private List<FlightInfo> rebookingNewFlightItemList;

    private List<Passenger> rebookingPassengerItemList;

    private RebookingPayDetail rebookingPayDetail;

    private boolean rebookNeedPay;

    private String payStatus;

    private boolean isChosenNewFlight;

    private boolean unconfirmedStatus;

    public String getRebookingApplicationId() {
        return rebookingApplicationId;
    }

    public void setRebookingApplicationId(String rebookingApplicationId) {
        this.rebookingApplicationId = rebookingApplicationId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Calendar getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Calendar requestTime) {
        this.requestTime = requestTime;
    }

    public String getPayExternalNo() {
        return payExternalNo;
    }

    public void setPayExternalNo(String payExternalNo) {
        this.payExternalNo = payExternalNo;
    }

    public BigDecimal getfCardServiceFee() {
        return fCardServiceFee;
    }

    public void setfCardServiceFee(BigDecimal fCardServiceFee) {
        this.fCardServiceFee = fCardServiceFee;
    }

    public Calendar getPaymentDeadline() {
        return paymentDeadline;
    }

    public void setPaymentDeadline(Calendar paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    public String getBaggageRemark() {
        return baggageRemark;
    }

    public void setBaggageRemark(String baggageRemark) {
        this.baggageRemark = baggageRemark;
    }

    public String getFlightDescription() {
        return flightDescription;
    }

    public void setFlightDescription(String flightDescription) {
        this.flightDescription = flightDescription;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getBookSeatType() {
        return bookSeatType;
    }

    public void setBookSeatType(String bookSeatType) {
        this.bookSeatType = bookSeatType;
    }

    public ContactInfo getRebookingContactInfo() {
        return rebookingContactInfo;
    }

    public void setRebookingContactInfo(ContactInfo rebookingContactInfo) {
        this.rebookingContactInfo = rebookingContactInfo;
    }

    public List<FlightInfo> getRebookingFlightItemList() {
        return rebookingFlightItemList;
    }

    public void setRebookingFlightItemList(List<FlightInfo> rebookingFlightItemList) {
        this.rebookingFlightItemList = rebookingFlightItemList;
    }

    public List<FlightInfo> getRebookingNewFlightItemList() {
        return rebookingNewFlightItemList;
    }

    public void setRebookingNewFlightItemList(List<FlightInfo> rebookingNewFlightItemList) {
        this.rebookingNewFlightItemList = rebookingNewFlightItemList;
    }

    public List<Passenger> getRebookingPassengerItemList() {
        return rebookingPassengerItemList;
    }

    public void setRebookingPassengerItemList(List<Passenger> rebookingPassengerItemList) {
        this.rebookingPassengerItemList = rebookingPassengerItemList;
    }

    public RebookingPayDetail getRebookingPayDetail() {
        return rebookingPayDetail;
    }

    public void setRebookingPayDetail(RebookingPayDetail rebookingPayDetail) {
        this.rebookingPayDetail = rebookingPayDetail;
    }

    public boolean isRebookNeedPay() {
        return rebookNeedPay;
    }

    public void setRebookNeedPay(boolean rebookNeedPay) {
        this.rebookNeedPay = rebookNeedPay;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public boolean isChosenNewFlight() {
        return isChosenNewFlight;
    }

    public void setChosenNewFlight(boolean chosenNewFlight) {
        isChosenNewFlight = chosenNewFlight;
    }

    public boolean isUnconfirmedStatus() {
        return unconfirmedStatus;
    }

    public void setUnconfirmedStatus(boolean unconfirmedStatus) {
        this.unconfirmedStatus = unconfirmedStatus;
    }
}
