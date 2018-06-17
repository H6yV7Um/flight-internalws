package com.ctrip.ibu.flight.internalws.models.flight.offline;

import com.ctrip.ibu.flight.internalws.models.flight.ContactInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhangrm on 2017/11/2.
 */
public class RefundAskDetailResponseModel {
    private Long orderId;

    private String flightOrderClass;

    private String uid;

    private String contactEmail;

    private Long refundOrderID;

    private String status;

    private String refundStatusEnum;

    private List<RefundAskPassengerInfoModel> refundAskPassengerInfo;

    private BigDecimal payCustomerTotal;

    private List<XRefundInfoTypeModel> xRefundInfoTypes;

    private List<InsuranceInfoModel> insuranceInfos;

    private ContactInfo contactInfo;

    private String currency;

    /**
     * 订单号
     */
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getFlightOrderClass() {
        return flightOrderClass;
    }

    public void setFlightOrderClass(String flightOrderClass) {
        this.flightOrderClass = flightOrderClass;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Long getRefundOrderID() {
        return refundOrderID;
    }

    public void setRefundOrderID(Long refundOrderID) {
        this.refundOrderID = refundOrderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRefundStatusEnum() {
        return refundStatusEnum;
    }

    public void setRefundStatusEnum(String refundStatusEnum) {
        this.refundStatusEnum = refundStatusEnum;
    }

    public List<RefundAskPassengerInfoModel> getRefundAskPassengerInfo() {
        return refundAskPassengerInfo;
    }

    public void setRefundAskPassengerInfo(List<RefundAskPassengerInfoModel> refundAskPassengerInfo) {
        this.refundAskPassengerInfo = refundAskPassengerInfo;
    }

    public BigDecimal getPayCustomerTotal() {
        return payCustomerTotal;
    }

    public void setPayCustomerTotal(BigDecimal payCustomerTotal) {
        this.payCustomerTotal = payCustomerTotal;
    }

    public List<XRefundInfoTypeModel> getxRefundInfoTypes() {
        return xRefundInfoTypes;
    }

    public void setxRefundInfoTypes(List<XRefundInfoTypeModel> xRefundInfoTypes) {
        this.xRefundInfoTypes = xRefundInfoTypes;
    }

    public List<InsuranceInfoModel> getInsuranceInfos() {
        return insuranceInfos;
    }

    public void setInsuranceInfos(List<InsuranceInfoModel> insuranceInfos) {
        this.insuranceInfos = insuranceInfos;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
