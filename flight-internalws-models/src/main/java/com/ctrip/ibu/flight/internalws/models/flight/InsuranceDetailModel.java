package com.ctrip.ibu.flight.internalws.models.flight;

import java.io.Serializable;
import java.util.Date;

/**
 * 保单详情模型
 * Created by kyxie on 2017/8/11.
 */
public class InsuranceDetailModel implements Serializable {

    private String actualClaimParty;

    private Airport aPort;

    private Airport dPort;

    private String approvalSerial;

    private Date approvalTime;

    private Date arrivalTime;

    private String cardNo;

    private String claimResponsibleParty;

    private String claimStatus;

    private String companyId;

    private String companyName;

    private String companyNickName;

    private String compensation;

    private Date effectTime;

    private Date expireTime;

    private String flightNo;

    private Long id;

    private String insType;

    private String insuranceFee;

    private String insuranceNo;

    private String mobile;

    private String modifySerial;

    private Long orderId;

    private String passengerName;

    private String policyDownloadUrl;

    private String productCode;

    private String refundSerial;

    private Date refundTime;

    private Integer sequence;

    private String status;

    private Date takeOffTime;

    private String typeId;

    public String getActualClaimParty() {
        return actualClaimParty;
    }

    public void setActualClaimParty(String actualClaimParty) {
        this.actualClaimParty = actualClaimParty;
    }

    public Airport getaPort() {
        return aPort;
    }

    public void setaPort(Airport aPort) {
        this.aPort = aPort;
    }

    public Airport getdPort() {
        return dPort;
    }

    public void setdPort(Airport dPort) {
        this.dPort = dPort;
    }

    public String getApprovalSerial() {
        return approvalSerial;
    }

    public void setApprovalSerial(String approvalSerial) {
        this.approvalSerial = approvalSerial;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getClaimResponsibleParty() {
        return claimResponsibleParty;
    }

    public void setClaimResponsibleParty(String claimResponsibleParty) {
        this.claimResponsibleParty = claimResponsibleParty;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNickName() {
        return companyNickName;
    }

    public void setCompanyNickName(String companyNickName) {
        this.companyNickName = companyNickName;
    }

    public String getCompensation() {
        return compensation;
    }

    public void setCompensation(String compensation) {
        this.compensation = compensation;
    }

    public Date getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInsType() {
        return insType;
    }

    public void setInsType(String insType) {
        this.insType = insType;
    }

    public String getInsuranceFee() {
        return insuranceFee;
    }

    public void setInsuranceFee(String insuranceFee) {
        this.insuranceFee = insuranceFee;
    }

    public String getInsuranceNo() {
        return insuranceNo;
    }

    public void setInsuranceNo(String insuranceNo) {
        this.insuranceNo = insuranceNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getModifySerial() {
        return modifySerial;
    }

    public void setModifySerial(String modifySerial) {
        this.modifySerial = modifySerial;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPolicyDownloadUrl() {
        return policyDownloadUrl;
    }

    public void setPolicyDownloadUrl(String policyDownloadUrl) {
        this.policyDownloadUrl = policyDownloadUrl;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getRefundSerial() {
        return refundSerial;
    }

    public void setRefundSerial(String refundSerial) {
        this.refundSerial = refundSerial;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTakeOffTime() {
        return takeOffTime;
    }

    public void setTakeOffTime(Date takeOffTime) {
        this.takeOffTime = takeOffTime;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
