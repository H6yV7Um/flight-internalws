package com.ctrip.ibu.flight.internalws.models.flight;

import com.ctrip.ibu.flight.internalws.models.common.CorpGroup;
import com.ctrip.ibu.flight.internalws.models.common.Trademark;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单详情实体模型
 * Created by kyxie on 2017/7/1.
 */
public class OrderDetailModel implements Serializable {

    private Long orderId;

    private String uid;

    //订单状态
    private String actualOrderStatus;

    private String serverFrom;

    private Integer localeId;

    //取消原因
    private String cancelReason;

    //订单预定所属企业组
    private CorpGroup corpGroup;

    /**
     * 订单是否来自IBU站点
     */
    private boolean englishOrder;

    //订单商标(Ctrip,Trip)
    private Trademark trademark;

    //订单乘客类型
    private PassengerType orderPassengerType;

    //AccessToken
    private String accessToken;

    //下单时间
    private Date orderDate;

    //用户支付时间
    private Date userPayDate;

    //订单完成时间
    private Date orderFinishDate;

    private String bookingChannel;

    private String engineType;

    /**
     * 分销渠道订单信息
     * */
    private AllianceOrderModel allianceOrderInfo;

    //关联订单列表
    private List<OrderSummary> relatedOrderInfoList;

    //订单详情URL
    private String orderDetailUrl;

    //首页地址
    private String firstPageUrl;

    //机票类型
    private FlightClass flightClass;

    //航程类型(OW,RT,MT)
    private FlightWay flightWay;

    //航班信息
    private List<FlightInfo> flightInfoList;

    //乘机人信息
    private List<Passenger> passengerList;

    //联系人信息
    private ContactInfo contactInfo;

    //支付信息
    private PaymentInfo paymentInfo;

    //保险
    private Insurance insurance;

    //X产品
    private XProduct xProduct;

    //改签列表
    private List<RebookingInfo> rebookingInfoList;

    //最晚出票时间
    private LatestDraftTimeEntity lastestDraftTime;

    //格式化行李额
    private List<FormatBaggageInfo> formatBaggageInfoList;

    //支付方式
    private String payMethodStr;

    //最后修改支付时间
    private Date editPayTypeExpiryTime;

    //PNR
    private String pnr;

    public Date getUserPayDate() {
        return userPayDate;
    }

    public void setUserPayDate(Date userPayDate) {
        this.userPayDate = userPayDate;
    }

    public Date getOrderFinishDate() {
        return orderFinishDate;
    }

    public void setOrderFinishDate(Date orderFinishDate) {
        this.orderFinishDate = orderFinishDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getServerFrom() {
        return serverFrom;
    }

    public void setServerFrom(String serverFrom) {
        this.serverFrom = serverFrom;
    }

    public Integer getLocaleId() {
        return localeId;
    }

    public void setLocaleId(Integer localeId) {
        this.localeId = localeId;
    }

    public String getOrderDetailUrl() {
        return orderDetailUrl;
    }

    public void setOrderDetailUrl(String orderDetailUrl) {
        this.orderDetailUrl = orderDetailUrl;
    }

    public FlightClass getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClass flightClass) {
        this.flightClass = flightClass;
    }

    public String getActualOrderStatus() {
        return actualOrderStatus;
    }

    public void setActualOrderStatus(String actualOrderStatus) {
        this.actualOrderStatus = actualOrderStatus;
    }

    public List<OrderSummary> getRelatedOrderInfoList() {
        return relatedOrderInfoList;
    }

    public void setRelatedOrderInfoList(List<OrderSummary> relatedOrderInfoList) {
        this.relatedOrderInfoList = relatedOrderInfoList;
    }

    public String getBookingChannel() {
        return bookingChannel;
    }

    public void setBookingChannel(String bookingChannel) {
        this.bookingChannel = bookingChannel;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public AllianceOrderModel getAllianceOrderInfo() {
        return allianceOrderInfo;
    }

    public void setAllianceOrderInfo(AllianceOrderModel allianceOrderInfo) {
        this.allianceOrderInfo = allianceOrderInfo;
    }

    public List<FlightInfo> getFlightInfoList() {
        return flightInfoList;
    }

    public void setFlightInfoList(List<FlightInfo> flightInfoList) {
        this.flightInfoList = flightInfoList;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public LatestDraftTimeEntity getLastestDraftTime() {
        return lastestDraftTime;
    }

    public void setLastestDraftTime(LatestDraftTimeEntity lastestDraftTime) {
        this.lastestDraftTime = lastestDraftTime;
    }

    public FlightWay getFlightWay() {
        return flightWay;
    }

    public void setFlightWay(FlightWay flightWay) {
        this.flightWay = flightWay;
    }

    public CorpGroup getCorpGroup() {
        return corpGroup;
    }

    public void setCorpGroup(CorpGroup corpGroup) {
        this.corpGroup = corpGroup;
    }

    public boolean isEnglishOrder() {
        return englishOrder;
    }

    public void setEnglishOrder(boolean englishOrder) {
        this.englishOrder = englishOrder;
    }

    public Trademark getTrademark() {
        return trademark;
    }

    public void setTrademark(Trademark trademark) {
        this.trademark = trademark;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public PassengerType getOrderPassengerType() {
        return orderPassengerType;
    }

    public void setOrderPassengerType(PassengerType orderPassengerType) {
        this.orderPassengerType = orderPassengerType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public XProduct getxProduct() {
        return xProduct;
    }

    public void setxProduct(XProduct xProduct) {
        this.xProduct = xProduct;
    }

    public List<RebookingInfo> getRebookingInfoList() {
        return rebookingInfoList;
    }

    public void setRebookingInfoList(List<RebookingInfo> rebookingInfoList) {
        this.rebookingInfoList = rebookingInfoList;
    }

    public  void   setPayMethodStr(String payMethodStr){
        this.payMethodStr = payMethodStr;
    }

    public String  getPayMethodStr(){
        return payMethodStr;
    }

    public  void   setEditPayTypeExpiryTime(Date editPayTypeExpiryTime){
        this.editPayTypeExpiryTime = editPayTypeExpiryTime;
    }

    public Date  getEditPayTypeExpiryTime(){
        return editPayTypeExpiryTime;
    }

    //X产品
    public XProduct getXProduct() {
        return this.xProduct;
    }

    //X产品
    public void setXProduct(XProduct xProduct) {
        this.xProduct = xProduct;
    }

    //预定时间
    public Date getOrderDate() {
        return this.orderDate;
    }

    //预定时间
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getPnr() {
        return this.pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public List<FormatBaggageInfo> getFormatBaggageInfoList() {
        return formatBaggageInfoList;
    }

    public void setFormatBaggageInfoList(List<FormatBaggageInfo> formatBaggageInfoList) {
        this.formatBaggageInfoList = formatBaggageInfoList;
    }

    public String getFirstPageUrl() {
        return firstPageUrl;
    }

    public void setFirstPageUrl(String firstPageUrl) {
        this.firstPageUrl = firstPageUrl;
    }
}
