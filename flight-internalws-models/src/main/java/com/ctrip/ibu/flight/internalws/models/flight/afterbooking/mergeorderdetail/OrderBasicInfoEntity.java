package com.ctrip.ibu.flight.internalws.models.flight.afterbooking.mergeorderdetail;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单基础信息
 * @author : kyxie
 * date : 2018/5/2 18:21
 */
public class OrderBasicInfoEntity {

    private String uID;
    private Long orderID;
    private Long tempOrderID;
    private List<Long> associateOrderID;
    private Long payMainOrderID;
    private String bookingChannel;
    private String statusCode;
    private List<String> preOrderProcessList;
    private String orderProcess;
    private List<String> pendingOrderProcessList;
    private String statusDesc;
    private String actualStatusDes;
    private Long orderDate;
    private Long userPayDate;
    private Integer adjustDay1;
    private Integer adjustDay2;
    private String serverFrom;
    private String engineType;
    private String payMethod;
    private String currencyType;
    private BigDecimal cMoneyAmount;
    private String orderRemark;
    private String specialRemark;
    private String flightWay;
    private String flightWayName;
    private Boolean isPartial;
    private Boolean isSubOrder;
    private String validDate;
    private String externalNo;
    private String actualOrderStatus;
    private Long editPayTypeExpiryTimeLong;
    private Integer editPayTypeLimitSecond;
    private String specialPriceType;
    private Long reSubmitExpiryTimeLong;
    private Long serverTimeLong;
    private String flightOrderClass;
    private Boolean hasFlightChange;
    private Integer relateRouteType;
    private Integer orderSplitType;
    private Boolean showRebRefNotice;
    private Boolean isOrderFromIbuSite;
    private Boolean isSuperOrder;
    private Boolean isRefundOrReBooking;
    private Integer localeID;
    private Integer orderPoint;
    private Boolean isPayProcessing;
    private String orderType;

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Long getTempOrderID() {
        return tempOrderID;
    }

    public void setTempOrderID(Long tempOrderID) {
        this.tempOrderID = tempOrderID;
    }

    public List<Long> getAssociateOrderID() {
        return associateOrderID;
    }

    public void setAssociateOrderID(List<Long> associateOrderID) {
        this.associateOrderID = associateOrderID;
    }

    public Long getPayMainOrderID() {
        return payMainOrderID;
    }

    public void setPayMainOrderID(Long payMainOrderID) {
        this.payMainOrderID = payMainOrderID;
    }

    public String getBookingChannel() {
        return bookingChannel;
    }

    public void setBookingChannel(String bookingChannel) {
        this.bookingChannel = bookingChannel;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> getPreOrderProcessList() {
        return preOrderProcessList;
    }

    public void setPreOrderProcessList(List<String> preOrderProcessList) {
        this.preOrderProcessList = preOrderProcessList;
    }

    public String getOrderProcess() {
        return orderProcess;
    }

    public void setOrderProcess(String orderProcess) {
        this.orderProcess = orderProcess;
    }

    public List<String> getPendingOrderProcessList() {
        return pendingOrderProcessList;
    }

    public void setPendingOrderProcessList(List<String> pendingOrderProcessList) {
        this.pendingOrderProcessList = pendingOrderProcessList;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getActualStatusDes() {
        return actualStatusDes;
    }

    public void setActualStatusDes(String actualStatusDes) {
        this.actualStatusDes = actualStatusDes;
    }

    public Long getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Long orderDate) {
        this.orderDate = orderDate;
    }

    public Long getUserPayDate() {
        return userPayDate;
    }

    public void setUserPayDate(Long userPayDate) {
        this.userPayDate = userPayDate;
    }

    public Integer getAdjustDay1() {
        return adjustDay1;
    }

    public void setAdjustDay1(Integer adjustDay1) {
        this.adjustDay1 = adjustDay1;
    }

    public Integer getAdjustDay2() {
        return adjustDay2;
    }

    public void setAdjustDay2(Integer adjustDay2) {
        this.adjustDay2 = adjustDay2;
    }

    public String getServerFrom() {
        return serverFrom;
    }

    public void setServerFrom(String serverFrom) {
        this.serverFrom = serverFrom;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public BigDecimal getcMoneyAmount() {
        return cMoneyAmount;
    }

    public void setcMoneyAmount(BigDecimal cMoneyAmount) {
        this.cMoneyAmount = cMoneyAmount;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public String getSpecialRemark() {
        return specialRemark;
    }

    public void setSpecialRemark(String specialRemark) {
        this.specialRemark = specialRemark;
    }

    public String getFlightWay() {
        return flightWay;
    }

    public void setFlightWay(String flightWay) {
        this.flightWay = flightWay;
    }

    public String getFlightWayName() {
        return flightWayName;
    }

    public void setFlightWayName(String flightWayName) {
        this.flightWayName = flightWayName;
    }

    public Boolean getPartial() {
        return isPartial;
    }

    public void setPartial(Boolean partial) {
        isPartial = partial;
    }

    public Boolean getSubOrder() {
        return isSubOrder;
    }

    public void setSubOrder(Boolean subOrder) {
        isSubOrder = subOrder;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getExternalNo() {
        return externalNo;
    }

    public void setExternalNo(String externalNo) {
        this.externalNo = externalNo;
    }

    public String getActualOrderStatus() {
        return actualOrderStatus;
    }

    public void setActualOrderStatus(String actualOrderStatus) {
        this.actualOrderStatus = actualOrderStatus;
    }

    public Long getEditPayTypeExpiryTimeLong() {
        return editPayTypeExpiryTimeLong;
    }

    public void setEditPayTypeExpiryTimeLong(Long editPayTypeExpiryTimeLong) {
        this.editPayTypeExpiryTimeLong = editPayTypeExpiryTimeLong;
    }

    public Integer getEditPayTypeLimitSecond() {
        return editPayTypeLimitSecond;
    }

    public void setEditPayTypeLimitSecond(Integer editPayTypeLimitSecond) {
        this.editPayTypeLimitSecond = editPayTypeLimitSecond;
    }

    public String getSpecialPriceType() {
        return specialPriceType;
    }

    public void setSpecialPriceType(String specialPriceType) {
        this.specialPriceType = specialPriceType;
    }

    public Long getReSubmitExpiryTimeLong() {
        return reSubmitExpiryTimeLong;
    }

    public void setReSubmitExpiryTimeLong(Long reSubmitExpiryTimeLong) {
        this.reSubmitExpiryTimeLong = reSubmitExpiryTimeLong;
    }

    public Long getServerTimeLong() {
        return serverTimeLong;
    }

    public void setServerTimeLong(Long serverTimeLong) {
        this.serverTimeLong = serverTimeLong;
    }

    public String getFlightOrderClass() {
        return flightOrderClass;
    }

    public void setFlightOrderClass(String flightOrderClass) {
        this.flightOrderClass = flightOrderClass;
    }

    public Boolean getHasFlightChange() {
        return hasFlightChange;
    }

    public void setHasFlightChange(Boolean hasFlightChange) {
        this.hasFlightChange = hasFlightChange;
    }

    public Integer getRelateRouteType() {
        return relateRouteType;
    }

    public void setRelateRouteType(Integer relateRouteType) {
        this.relateRouteType = relateRouteType;
    }

    public Integer getOrderSplitType() {
        return orderSplitType;
    }

    public void setOrderSplitType(Integer orderSplitType) {
        this.orderSplitType = orderSplitType;
    }

    public Boolean getShowRebRefNotice() {
        return showRebRefNotice;
    }

    public void setShowRebRefNotice(Boolean showRebRefNotice) {
        this.showRebRefNotice = showRebRefNotice;
    }

    public Boolean getOrderFromIbuSite() {
        return isOrderFromIbuSite;
    }

    public void setOrderFromIbuSite(Boolean orderFromIbuSite) {
        isOrderFromIbuSite = orderFromIbuSite;
    }

    public Boolean getSuperOrder() {
        return isSuperOrder;
    }

    public void setSuperOrder(Boolean superOrder) {
        isSuperOrder = superOrder;
    }

    public Boolean getRefundOrReBooking() {
        return isRefundOrReBooking;
    }

    public void setRefundOrReBooking(Boolean refundOrReBooking) {
        isRefundOrReBooking = refundOrReBooking;
    }

    public Integer getLocaleID() {
        return localeID;
    }

    public void setLocaleID(Integer localeID) {
        this.localeID = localeID;
    }

    public Integer getOrderPoint() {
        return orderPoint;
    }

    public void setOrderPoint(Integer orderPoint) {
        this.orderPoint = orderPoint;
    }

    public Boolean getPayProcessing() {
        return isPayProcessing;
    }

    public void setPayProcessing(Boolean payProcessing) {
        isPayProcessing = payProcessing;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
