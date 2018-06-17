package com.ctrip.ibu.flight.internalws.models.constant;

/**
 * 业务类常量
 * Created by kyxie on 2017/9/13.
 */
public final class BusinessConst {

    private BusinessConst() throws IllegalAccessException{
        throw new IllegalAccessException("Constructor Forbidden");
    }

    /**
     * 业务公共常量类
     * */
    public final static class BusinessCommonConst {

        public final static String FLIGHT_FIRST_PAGE_URL_PATTERN = "https://${domain}/flights/#ctm_ref=nb_fl_top";

    }

    //APP名称
    public static  String APPNAME = "FlightInternalWS";

    //境外旅行险类型标识
    public static  String JWSGX_INSURANCE_IDENTIFIER = "JWSGX";

    //航意险标识
    public static  String C2C_INSURANCE_IDENTIFIER = "C2C";

    //大陆旅行险标识
    public static  String SGX_INSURANCE_IDENTIFIER = "SGX";

    //6w测试账号
    public static  String TESTACCOUNT_6W = "wwwwww";

    //ServerFrom中SC标识
    public static  String SCSIGN_INSERVERFROM = "/sc";

    //ServerFrom中Trip标识
    public static  String TRIPSIGN_INSERVERFROM = "trip/";

    //商标标识
    public static  String TRADEMARK_CTRIP = "Ctrip";

    public static  String TRADEMARK_TRIP = "Trip.com";

    //SOA请求的key---保单号
    public static  String SOAREQUESTKEY_INSURANCENO = "InsuranceNo";

    //SOA请求的key---改签单号
    public static  String SOAREQUESTKEY_CHANGEBILLID = "ChangeBillID";

    //改签申请单号
    public static  String SOAREQUESTKEY_REBOOKINGAPPLICATIONID = "RebookingApplicationId";

    //国际，国内
    public static final String SOAREQUESTKEY_FLIGHTCLASS = "FlightClass";

    //SOA请求的key---X产品订单号
    public static  String SOAREQUESTKEY_PRODUCTORDERID = "ProductOrderId";

    //SOA请求的key---X产品支付流水号
    public static  String SOAREQUESTKEY_EXTERNALNO = "ExternalNO";

    //SOA请求附件信息Key--报销凭证CustomerName
    public static  String SOAREQUESTKEY_CUSTOMERNAME = "CustomerName";

    //咨询单编号
    public static  String SOAREQUESTKEY_RESCHEDULEASKID = "RescheduleAskId";

    //退票单号
    public static  String SOAREQUESTKEY_REFUNDORDERID = "RefundOrderID";

    //订单详情URL规则
    public static  String ORDERDETAILURLPATTERN = "${protocol}://${domain}/${appname}/vieworder?orderid=${orderid}&accesstoken=${accesstoken}";

    //Offline X 产品详情链接
    public static  String OFFLINEXPRODUCTURLPATTERN = "${protocol}://${domain}/flights/cs/xorderattached?orderid=${orderid}&xproductorderid=${xproductorderid}&accesstoken=${accesstoken}";

    //Offline 退票咨询单 产品详情链接
    public static  String OFFLINEREFUNDURLPATTERN = "${protocol}://${domain}/flights/cs/refundorder?orderid=${orderid}&refundorderid=${refundorderid}&accesstoken=${accesstoken}";

    //Offline 改签咨询单 产品详情链接
    public static  String OFFLINERESCHEDULEURLPATTERN = "${protocol}://${domain}/flights/cs/changeorder?orderid=${orderid}&rebookid=${rebookid}&accesstoken=${accesstoken}";

    //大首页URL规则
    public static final String FIRSTPAGEURLPATTERN = "${protocol}://${domain}/flights/#ctm_ref=nb_fl_top";

    //PayUrl规则
    public static final String PAYURLPATTERN = "${protocol}://${domain}/flights/ChangeOrder/ContinuePayment?orderid=${orderid}&rebookID=${rebookid}&accesstoken=${accesstoken}";

    //Offline订单ServerFrom
    public static  String OFFLINEORDERSERVERFROM1 = "fltint.sh.ctriptravel.com";

    //Offline订单ServerFrom
    public static  String OFFLINEORDERSERVERFROM2 = "fltintlint.sh.ctriptravel.com";

    //Offline订单ServerFrom
    public static  String OFFLINEORDERSERVERFROM3 = "fltintlint.ctripcorp.com";

    //nofaq邮件头尾的邮件类型
    public static  String NOFAQHEADERFOOTER_EMAILTYPE = "email.flight.nofaq";

    //确认邮件头尾的邮件类型
    public static  String RESERVATIONCONFIRMATIONHEADERFOOTER_EMAILTYPE = "email.flight.reservesuccess";


    //X产品类型
    public static  String XPRODUCTTYPE = "ProductType";

    /**
     * Offlie X产品常量
     * */
    public static final class OfflineXProductConstant{

        //未知状态码
        public static Integer STATUSTYPECODE_UNKNOWN = -1;

        //待支付状态码
        public static Integer STATUSTYPECODE_SUBMITTED = 0;

        //支付成功状态码
        public static Integer STATUSTYPECODE_PAYMENTSUCCESS = 1;

        //购买成功状态码
        public static Integer STATUSTYPECODE_ISSUED = 2;

        //购买失败状态码
        public static Integer STATUSTYPECODE_CANCELED = 3;

        //服务类型Code--行李额
        public static Integer  SERVICETYPECODE_BAGGAGE =7;

        //服务类型Code--餐食
        public static Integer SERVICETYPECODE_MEALS = 2;

        //服务类型Code--姓名更改
        public static Integer SERVICETYPECODE_NAMEMODIFY = 3;

        //服务类型Code--证件修改
        public static Integer SERVICETYPECODE_PAPERMODIFY = 4;

        //服务类型Code--值机选座
        public static Integer SERVICETYPECODE_CHECKSEAT = 5;

        //服务类型Code--特殊需求
        public static Integer SERVICETYPECODE_SPECIALREQUEST = 6;

        //服务类型Code--补订行李额
        public static Integer SERVICETYPECODE_XPRODUCTEXTERNALBAGGAGE = 8;
    }

    /**
     * 开关相关类
     * */
    public static final class SwitchKeyConstant{
        public static String OpenShark = "openShark";
    }
}
