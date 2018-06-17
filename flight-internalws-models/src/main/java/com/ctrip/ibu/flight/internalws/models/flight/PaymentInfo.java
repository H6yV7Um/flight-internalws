package com.ctrip.ibu.flight.internalws.models.flight;

import java.math.BigDecimal;

/**
 * 支付信息
 * Created by kyxie on 2017/9/22.
 */
public class PaymentInfo {

    //支付方式
    private PayMethodEnum payMethod;

    //支付方式多语言
    private String payMethodDesc;

    //汇率
    private BigDecimal exchangeRate;

    //进位规则
    private BigDecimal dataRule;

    //结算币种
    private SettlementCurrency currencyType;

    //商户号
    private String merchantInfo;

    //订单总价
    private BigDecimal payTotalPrice;

    //机票总价
    private BigDecimal payFlightPrice;

    //税费
    private BigDecimal payTax;

    //燃油费
    private BigDecimal payOil;

    //保险总额
    private BigDecimal payTotalInsuranceFee;

    //航意险总价
    private BigDecimal payTotalAviationAccidentInsuranceFee;

    //旅行险总价
    private BigDecimal payTotalTravelInsuranceFee;

    //增值行李额总价
    private BigDecimal payValueAddedBaggageFee;

    //X产品酒店优惠券
    private BigDecimal payXHotelCouponFee;

    //套餐捆绑优惠券总价
    private BigDecimal payTotalPackageAttachAmount;

    //外卡手续费
    private BigDecimal payCCardFee;

    //燃油费
    private BigDecimal payDeliverFee;

    //贵宾休息室
    private BigDecimal loungeFee;

    //礼品卡支付费用
    private BigDecimal payCouponAmount;

    //飞享金
    private BigDecimal paySubsidy;

    //是否走实时支付
    private int isRealTimePay;

    //是否CBU支付
    private int isPayToCBU;

    //外币转CNY保留位数
    private int cnyCarrayRule;

    //支付总金额(没有外卡手续费)
    private BigDecimal payTotalPriceNoCCardFee;

    //支付明细列表
    //private List<FlightPriceDetail> paymentDetailDescList;

    //订单外币金额
    private BigDecimal orderForeignAmount;

    public PayMethodEnum getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethodEnum payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayMethodDesc() {
        return payMethodDesc;
    }

    public void setPayMethodDesc(String payMethodDesc) {
        this.payMethodDesc = payMethodDesc;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getDataRule() {
        return dataRule;
    }

    public void setDataRule(BigDecimal dataRule) {
        this.dataRule = dataRule;
    }

    public SettlementCurrency getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(SettlementCurrency currencyType) {
        this.currencyType = currencyType;
    }

    public String getMerchantInfo() {
        return merchantInfo;
    }

    public void setMerchantInfo(String merchantInfo) {
        this.merchantInfo = merchantInfo;
    }

    public BigDecimal getPayTotalPrice() {
        return payTotalPrice;
    }

    public void setPayTotalPrice(BigDecimal payTotalPrice) {
        this.payTotalPrice = payTotalPrice;
    }

    public BigDecimal getPayFlightPrice() {
        return payFlightPrice;
    }

    public void setPayFlightPrice(BigDecimal payFlightPrice) {
        this.payFlightPrice = payFlightPrice;
    }

    public BigDecimal getPayTax() {
        return payTax;
    }

    public void setPayTax(BigDecimal payTax) {
        this.payTax = payTax;
    }

    public BigDecimal getPayOil() {
        return payOil;
    }

    public void setPayOil(BigDecimal payOil) {
        this.payOil = payOil;
    }

    public BigDecimal getPayTotalAviationAccidentInsuranceFee() {
        return payTotalAviationAccidentInsuranceFee;
    }

    public void setPayTotalAviationAccidentInsuranceFee(BigDecimal payTotalAviationAccidentInsuranceFee) {
        this.payTotalAviationAccidentInsuranceFee = payTotalAviationAccidentInsuranceFee;
    }

    public BigDecimal getPayTotalTravelInsuranceFee() {
        return payTotalTravelInsuranceFee;
    }

    public void setPayTotalTravelInsuranceFee(BigDecimal payTotalTravelInsuranceFee) {
        this.payTotalTravelInsuranceFee = payTotalTravelInsuranceFee;
    }

    public BigDecimal getPayTotalInsuranceFee() {
        return payTotalInsuranceFee;
    }

    public void setPayTotalInsuranceFee(BigDecimal payTotalInsuranceFee) {
        this.payTotalInsuranceFee = payTotalInsuranceFee;
    }

    public BigDecimal getPayValueAddedBaggageFee() {
        return payValueAddedBaggageFee;
    }

    public void setPayValueAddedBaggageFee(BigDecimal payValueAddedBaggageFee) {
        this.payValueAddedBaggageFee = payValueAddedBaggageFee;
    }

    public BigDecimal getPayXHotelCouponFee() {
        return payXHotelCouponFee;
    }

    public void setPayXHotelCouponFee(BigDecimal payXHotelCouponFee) {
        this.payXHotelCouponFee = payXHotelCouponFee;
    }

    public BigDecimal getPayTotalPackageAttachAmount() {
        return payTotalPackageAttachAmount;
    }

    public void setPayTotalPackageAttachAmount(BigDecimal payTotalPackageAttachAmount) {
        this.payTotalPackageAttachAmount = payTotalPackageAttachAmount;
    }

    public BigDecimal getPayCCardFee() {
        return payCCardFee;
    }

    public void setPayCCardFee(BigDecimal payCCardFee) {
        this.payCCardFee = payCCardFee;
    }

    public BigDecimal getPayDeliverFee() {
        return payDeliverFee;
    }

    public void setPayDeliverFee(BigDecimal payDeliverFee) {
        this.payDeliverFee = payDeliverFee;
    }

    public BigDecimal getLoungeFee() {
        return loungeFee;
    }

    public void setLoungeFee(BigDecimal loungeFee) {
        this.loungeFee = loungeFee;
    }

    public BigDecimal getPayCouponAmount() {
        return payCouponAmount;
    }

    public void setPayCouponAmount(BigDecimal payCouponAmount) {
        this.payCouponAmount = payCouponAmount;
    }

    public BigDecimal getPaySubsidy() {
        return paySubsidy;
    }

    public void setPaySubsidy(BigDecimal paySubsidy) {
        this.paySubsidy = paySubsidy;
    }

    public int getIsRealTimePay() {
        return isRealTimePay;
    }

    public void setIsRealTimePay(int isRealTimePay) {
        this.isRealTimePay = isRealTimePay;
    }

    public int getIsPayToCBU() {
        return isPayToCBU;
    }

    public void setIsPayToCBU(int isPayToCBU) {
        this.isPayToCBU = isPayToCBU;
    }

    public int getCnyCarrayRule() {
        return cnyCarrayRule;
    }

    public void setCnyCarrayRule(int cnyCarrayRule) {
        this.cnyCarrayRule = cnyCarrayRule;
    }

    public BigDecimal getPayTotalPriceNoCCardFee() {
        return payTotalPriceNoCCardFee;
    }

    public void setPayTotalPriceNoCCardFee(BigDecimal payTotalPriceNoCCardFee) {
        this.payTotalPriceNoCCardFee = payTotalPriceNoCCardFee;
    }

    public BigDecimal getOrderForeignAmount() {
        return orderForeignAmount;
    }

    public void setOrderForeignAmount(BigDecimal orderForeignAmount) {
        this.orderForeignAmount = orderForeignAmount;
    }
}
