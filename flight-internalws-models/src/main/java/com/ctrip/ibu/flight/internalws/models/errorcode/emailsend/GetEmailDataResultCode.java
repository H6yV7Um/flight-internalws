package com.ctrip.ibu.flight.internalws.models.errorcode.emailsend;

/**
 * 获取邮件数据ErrorCode
 * Create by kyxie on 2018/4/14 19:30
 */
public enum GetEmailDataResultCode {

    SUCCESS(0,"成功"),

    ILLEGAL_REQUEST(1,"不合法请求"),

    SYSTEM_ERROR(2,"系统错误"),

    LACK_ORDERID(3,"缺少订单号"),

    NOT_IBU_ORDER(4,"非IBU订单"),

    TEMPLATE_RENDER_FAILED(5,"模板渲染失败"),

    ORDER_DETAIL_INVALID(6,"订单详情数据错误"),

    LACK_INTL_INSURANCE(7,"没有国际旅行险信息"),

    LACK_INSURANCE_NO(8,"缺少保单号"),

    LACK_INSURANCE_INFO(9,"缺少有效的保险信息"),

    LACK_CHANGE_BILL_ID(10,"缺少改签单号"),

    LACK_REBOOKING_INFO(11,"缺少改签信息"),

    LACK_XPRODUCT_ORDERID(12,"缺少X产品订单号"),

    LACK_XPRODUCT_INFO(13,"缺少X产品订单信息"),

    LACK_RESCHEDULE_ORDER_ID(14,"缺少改签咨询单订单号"),

    LACK_RESCHEDULE_ASK_ID(15,"缺少改签咨询单咨询单编号"),

    LACK_RESCHEDULE_ASK_INFO(16,"缺少改签咨询单信息"),

    LACK_REFUND_BILL_ID(17,"缺少退票单号"),

    LACK_REFUND_INFO(18,"没有退票信息"),

    FREE_PAY_FEE(19,"不需要支付费用"),

    NOT_SELF_PAY(20,"非自助支付"),

    LACK_FLIGHT_CLASS(21,"缺少国际（I）或国内（N）类型"),

    FREE_RESCHEDULE(22,"免费改签"),

    SUPPLE_BAGGAGE_STATUS_ILLEGAL(23,"补订行李额状态不合法"),

    LACK_XPRODUCT_BAGGAGE_INFO(24,"缺少X产品行李额信息"),

    LACK_XPRODUCT_NAMEMODIFY_INFO(25,"缺少X产品姓名更改信息"),

    LACK_XPRODUCT_PAPERMODIFY_INFO(26,"缺少X产品证件更改信息"),

    LACK_XPRODUCT_MEALS_INFO(27,"缺少X产品餐食信息"),

    LACK_XPRODUCT_SPECIALREQUEST_INFO(28,"缺少X产品特殊需求信息"),

    LACK_XPRODUCT_CHECKSEAT_INFO(29,"缺少X产品值机选座信息"),

    LACK_MERGE_ORDER_DETAIL(30,"缺少合并订单详情"),

    MERGE_ORDER_DETAIL_ILLEGAL(31,"合并订单详情不合法");

    private final Integer val;

    private final String desc;

    GetEmailDataResultCode(Integer val,String desc){
        this.val =val;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getVal() {
        return val;
    }
}
