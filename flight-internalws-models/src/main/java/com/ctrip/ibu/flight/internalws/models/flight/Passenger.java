package com.ctrip.ibu.flight.internalws.models.flight;

import com.ctrip.ibu.flight.internalws.models.annotation.GdprInfo;
import com.ctrip.ibu.flight.internalws.models.util.GDPRType;

import java.util.Calendar;
import java.util.List;

/**
 * 乘机人
 * Created by kyxie on 2017/8/16.
 */
public class Passenger {

    private Integer passengerId;

    //乘客类型
    private PassengerType passengerType;

    @GdprInfo(GDPRType.GDPR_USER_NAME)
    //乘客姓名(last/first全名)
    private String name;

    //生日
    private Calendar birthday;

    @GdprInfo(GDPRType.GDPR_TRAVELER_IDCARDNO)
    //证件号
    private String idNo;

    //证件类型
    private IdCardType idCardType;

    //证件名称
    private String idName;

    //性别
    private String gender;

    //国籍编码
    private String nationalityCode;

    //国籍名称
    private String nationalityName;

    //证件有效期
    private Calendar idValidPeriod;

    //乘客资质
    private PassengerEligibility passengerEligibility;

    private List<String> eTickets;

    //票号信息列表
    private List<TicketNoInfo> ticketNoInfoList;

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(PassengerType passengerType) {
        this.passengerType = passengerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public IdCardType getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(IdCardType idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationalityCode() {
        return nationalityCode;
    }

    public void setNationalityCode(String nationalityCode) {
        this.nationalityCode = nationalityCode;
    }

    public String getNationalityName() {
        return nationalityName;
    }

    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }

    public PassengerEligibility getPassengerEligibility() {
        return passengerEligibility;
    }

    public void setPassengerEligibility(PassengerEligibility passengerEligibility) {
        this.passengerEligibility = passengerEligibility;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public Calendar getIdValidPeriod() {
        return idValidPeriod;
    }

    public void setIdValidPeriod(Calendar idValidPeriod) {
        this.idValidPeriod = idValidPeriod;
    }

    public List<TicketNoInfo> getTicketNoInfoList() {
        return ticketNoInfoList;
    }

    public void setTicketNoInfoList(List<TicketNoInfo> ticketNoInfoList) {
        this.ticketNoInfoList = ticketNoInfoList;
    }

    public List<String> geteTickets() {
        return eTickets;
    }

    public void seteTickets(List<String> eTickets) {
        this.eTickets = eTickets;
    }

}
