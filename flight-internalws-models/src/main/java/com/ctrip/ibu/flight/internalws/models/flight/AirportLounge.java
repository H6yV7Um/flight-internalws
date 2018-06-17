package com.ctrip.ibu.flight.internalws.models.flight;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * 贵宾休息室信息
 * Created by kyxie on 2017/9/22.
 */
public class AirportLounge {
    //休息室产品ID
    private long id;

    //订单号
    private long orderID;

    //预订日期
    private Calendar bookingDate;

    //预订日期
    private long bookingDateLong;

    //过期日期（使用期限）
    private Calendar expiryDate;

    //过期日期（使用期限）
    private long expiryDateLong;

    //验证码
    private String qrCode;

    //休息室类型
    private short loungesType;

    //机场
    private String airport;

    //工作时间
    private String workingTime;

    //产品说明
    private String productDescription;

    //产品说明En
    private String productDescriptionEn;

    //位置说明
    private String locationInfo;

    //位置说明
    private String locationInfoEn;

    //支付币种 票面价格
    private BigDecimal printPrice;

    //多币种 票面价格
    private BigDecimal showCurrencyPrintPrice;

    //底价
    private BigDecimal costPrice;

    //卖价
    private BigDecimal salePrice;

    //创建时间
    private Calendar createTime;

    //创建时间
    private long createTimeLong;

    //退票描述，对客人
    private String refNote;

    //退票描述，对供应商
    private String refsNote;

    //退票公式，对客人
    private String refundInfo;

    //退票公式，对供应商
    private String refundsInfo;

    //产品数量
    private int productCount;

    //所关联的订单航段
    private int sequence;

    //图片信息
    private String pictureInfo;

    //休息室时长（单位小时）
    private int duration;

    //包含服务(1:wifi,2:登机提醒,4:小食,8:正餐,16:其它)
    private int services;

    //休息室产品名称
    private String productName;

    //休息室产品名称
    private String productNameEn;

    //休息室产品ID关联产品信息表ID
    private long loungeProductID;

    //供应商ID( 首都、 龙腾)
    private int flightAgency;

    //乘客姓名
    private String passengerName;

    //第三方订单号(外部订单号)
    private String thirdPartOrderID;

    //休息室产品状态
    private String orderStatus;

    //是否可取消/退订休息室
    private boolean isCanCancel;

    //休息室验证码使用状态（1未使用2已使用）
    private short qrStatus;

    //休息室产品用户名
    private String passagerName;

    //客户退改签描述
    private String refundInfoDescription;

    //供应商改签描述
    private String refundsInfoDescription;

    //订单状态描述
    private String orderStatusDescription;

    //免费设施描述
    private String faciliesDescription;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public Calendar getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Calendar bookingDate) {
        this.bookingDate = bookingDate;
    }

    public long getBookingDateLong() {
        return bookingDateLong;
    }

    public void setBookingDateLong(long bookingDateLong) {
        this.bookingDateLong = bookingDateLong;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }

    public long getExpiryDateLong() {
        return expiryDateLong;
    }

    public void setExpiryDateLong(long expiryDateLong) {
        this.expiryDateLong = expiryDateLong;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public short getLoungesType() {
        return loungesType;
    }

    public void setLoungesType(short loungesType) {
        this.loungesType = loungesType;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductDescriptionEn() {
        return productDescriptionEn;
    }

    public void setProductDescriptionEn(String productDescriptionEn) {
        this.productDescriptionEn = productDescriptionEn;
    }

    public String getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(String locationInfo) {
        this.locationInfo = locationInfo;
    }

    public String getLocationInfoEn() {
        return locationInfoEn;
    }

    public void setLocationInfoEn(String locationInfoEn) {
        this.locationInfoEn = locationInfoEn;
    }

    public BigDecimal getPrintPrice() {
        return printPrice;
    }

    public void setPrintPrice(BigDecimal printPrice) {
        this.printPrice = printPrice;
    }

    public BigDecimal getShowCurrencyPrintPrice() {
        return showCurrencyPrintPrice;
    }

    public void setShowCurrencyPrintPrice(BigDecimal showCurrencyPrintPrice) {
        this.showCurrencyPrintPrice = showCurrencyPrintPrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public long getCreateTimeLong() {
        return createTimeLong;
    }

    public void setCreateTimeLong(long createTimeLong) {
        this.createTimeLong = createTimeLong;
    }

    public String getRefNote() {
        return refNote;
    }

    public void setRefNote(String refNote) {
        this.refNote = refNote;
    }

    public String getRefsNote() {
        return refsNote;
    }

    public void setRefsNote(String refsNote) {
        this.refsNote = refsNote;
    }

    public String getRefundInfo() {
        return refundInfo;
    }

    public void setRefundInfo(String refundInfo) {
        this.refundInfo = refundInfo;
    }

    public String getRefundsInfo() {
        return refundsInfo;
    }

    public void setRefundsInfo(String refundsInfo) {
        this.refundsInfo = refundsInfo;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getPictureInfo() {
        return pictureInfo;
    }

    public void setPictureInfo(String pictureInfo) {
        this.pictureInfo = pictureInfo;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getServices() {
        return services;
    }

    public void setServices(int services) {
        this.services = services;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNameEn() {
        return productNameEn;
    }

    public void setProductNameEn(String productNameEn) {
        this.productNameEn = productNameEn;
    }

    public long getLoungeProductID() {
        return loungeProductID;
    }

    public void setLoungeProductID(long loungeProductID) {
        this.loungeProductID = loungeProductID;
    }

    public int getFlightAgency() {
        return flightAgency;
    }

    public void setFlightAgency(int flightAgency) {
        this.flightAgency = flightAgency;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getThirdPartOrderID() {
        return thirdPartOrderID;
    }

    public void setThirdPartOrderID(String thirdPartOrderID) {
        this.thirdPartOrderID = thirdPartOrderID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isCanCancel() {
        return isCanCancel;
    }

    public void setCanCancel(boolean canCancel) {
        isCanCancel = canCancel;
    }

    public short getQrStatus() {
        return qrStatus;
    }

    public void setQrStatus(short qrStatus) {
        this.qrStatus = qrStatus;
    }

    public String getPassagerName() {
        return passagerName;
    }

    public void setPassagerName(String passagerName) {
        this.passagerName = passagerName;
    }

    public String getRefundInfoDescription() {
        return refundInfoDescription;
    }

    public void setRefundInfoDescription(String refundInfoDescription) {
        this.refundInfoDescription = refundInfoDescription;
    }

    public String getRefundsInfoDescription() {
        return refundsInfoDescription;
    }

    public void setRefundsInfoDescription(String refundsInfoDescription) {
        this.refundsInfoDescription = refundsInfoDescription;
    }

    public String getOrderStatusDescription() {
        return orderStatusDescription;
    }

    public void setOrderStatusDescription(String orderStatusDescription) {
        this.orderStatusDescription = orderStatusDescription;
    }

    public String getFaciliesDescription() {
        return faciliesDescription;
    }

    public void setFaciliesDescription(String faciliesDescription) {
        this.faciliesDescription = faciliesDescription;
    }
}
