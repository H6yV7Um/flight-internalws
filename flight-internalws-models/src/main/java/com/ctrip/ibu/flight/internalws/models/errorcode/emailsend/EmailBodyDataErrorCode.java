package com.ctrip.ibu.flight.internalws.models.errorcode.emailsend;

/**
 * 邮件Body数据错误类型枚举
 * Created by kyxie on 2017/10/17.
 */
public enum EmailBodyDataErrorCode {

    DataLegal(0, "数据合法"),

    OrderDetailInvalid(1, "订单详情数据错误"),

    MessageResourceInvalid(2, "多语言数据错误"),

    NoInsurance(3, "没有保险信息"),

    NoIntlInsurance(4, "没有国际旅行险信息"),

    NoInsuranceNo(5, "缺少保单号"),

    NoValidInsurance(6,"缺少有效的保险信息"),

    NoChangeBillId(7, "缺少改签单号"),

    NoRebookingInfo(8, "缺少改签信息"),

    NoProductOrderId(9, "缺少X产品订单号"),

    NoProductOrderInfo(10, "缺少X产品订单信息"),

    NoRescheduleAskInfo(11, "缺少改签咨询单信息"),

    NoRescheduleOrderId(12, "缺少改签咨询单订单号"),

    NoRescheduleAskId(13, "缺少改签咨询单咨询单编号"),

    NoReBookingApplicationId(14, "缺少改签咨询单申请单号"),

    NoRefundBillId(15,"缺少退票单号"),

    NoRefundInfo(16,"没有退票信息"),

    NoPayFee(17,"不需要支付费用"),

    NotWhiteListUser(18,"非白名单用户"),

    NoIsSelfPay(19,"非自助支付"),

    NoFlightClass(20,"缺少国际（I）或国内（N）类型"),

    NoSendEmail(21,"免费改签不发送支付成功邮件"),

    NotIbuOrder(22,"非IBU订单");

    private final Integer value;

    private final String desc;

    EmailBodyDataErrorCode(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }
}
