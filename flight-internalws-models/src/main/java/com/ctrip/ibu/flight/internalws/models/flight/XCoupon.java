package com.ctrip.ibu.flight.internalws.models.flight;

import java.math.BigDecimal;

/**
 * X产品优惠券信息
 * Created by kyxie on 2017/9/22.
 */
public class XCoupon {

    //产品类型
    private int productType;

    //航段
    private short sequence;

    //产品组合
    private short category;

    //票面价
    private BigDecimal printPrice;

    //售价
    private BigDecimal salePrice;

    //每个乘客购买的数量
    private short productCount;

    //乘客数量
    private short passengerCount;

    //产品名称
    private String productName;

    //乘机人姓名
    private String passengerName;

    //订单状态
    private String status;

    //退票描述（对客人）
    private String refundInfo;

    //优惠券发放规则ID
    private long ruleId;

    //优惠券描述
    private String couponDesc;

    //优惠券类型
    private short couponType;

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public short getSequence() {
        return sequence;
    }

    public void setSequence(short sequence) {
        this.sequence = sequence;
    }

    public short getCategory() {
        return category;
    }

    public void setCategory(short category) {
        this.category = category;
    }

    public BigDecimal getPrintPrice() {
        return printPrice;
    }

    public void setPrintPrice(BigDecimal printPrice) {
        this.printPrice = printPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public short getProductCount() {
        return productCount;
    }

    public void setProductCount(short productCount) {
        this.productCount = productCount;
    }

    public short getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(short passengerCount) {
        this.passengerCount = passengerCount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRefundInfo() {
        return refundInfo;
    }

    public void setRefundInfo(String refundInfo) {
        this.refundInfo = refundInfo;
    }

    public long getRuleId() {
        return ruleId;
    }

    public void setRuleId(long ruleId) {
        this.ruleId = ruleId;
    }

    public String getCouponDesc() {
        return couponDesc;
    }

    public void setCouponDesc(String couponDesc) {
        this.couponDesc = couponDesc;
    }

    public short getCouponType() {
        return couponType;
    }

    public void setCouponType(short couponType) {
        this.couponType = couponType;
    }
}
