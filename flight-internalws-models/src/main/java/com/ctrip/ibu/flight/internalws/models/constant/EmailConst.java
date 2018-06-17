package com.ctrip.ibu.flight.internalws.models.constant;

/**
 * 邮件相关常量
 * Created by kyxie on 2017/7/23.
 */
public final class EmailConst {

    public final static class TemplateDataKey {

        //APP下载链接
        public static String APP_DOWNLOAD_URI = "appDownloadUri";

        //订单详情
        public static String ORDER_DETAIL = "orderDetail";//订单详情

        //合并订单详情
        public static String MERGE_ORDER_DETAIL = "mergeOrderDetail";
    }

    public static String EMAIL_PREVIEW_URL = "${protocol}://${host}:${port}/${urlPattern}/${controller}/${method}?emailid=${emailid}";

    //从基础业务返回的邮件内容中截取内容
    public static String REGEX_GETEMAILCONTENT = "<entry><content>\\s*(.+?)\\s*</content></entry>";

    public static String TEMPLATEDATAKEY_ORDERDETAIL = "orderDetail";//订单详情

    public static String TEMPLATEDATAKEY_CUSTOMERNAME = "customerName";

    public static String TEMPLATEDATAKEY_MESSAGERESOURCEFUNC = "getResource";

    public static String TEMPLATEDATAKEY_XPRODUCTBAGGAGE = "xproductBaggageDetail"; //订单详情里的X产品详情的补订行李额

    public static String TEMPLATEDATAKEY_FONTFAMILY = "fontFamily";//字体

    public static String TEMPLATEDATAKEY_ISSUINGAIRLINES = "issuingAirlines";//承运航司

    public static String TEMPLATEDATAKEY_RESOURCEPATH = "resourcePath";

    public static String TEMPLATEDATAKEY_C2CINSURANCEINFO = "c2cInsuranceDetailList";//航意险

    public static String TEMPLATEDATAKEY_SGXINSURANCEINFO = "sgxInsuranceDetailList";//旅行险

    public static String TEMPLATEDATAKEY_JWSGXINSURANCEINFO = "jwsgxInsuranceDetailList";//境外旅行险

    public static String TEMPLATEDATAKEY_NEWFLIGHTINFO = "newFlightInfo";//改签新航班

    public static String TEMPLATEDATAKEY_OFFLINEVALUEADDEDINFO = "offlineValueAddedInfo";//Offline增值服务

    public static String TEMPLATEDATAKEY_RESCHEDULEASKINFO = "rescheduleAskInfo";//改签咨询单

    public static String TEMPLATEDATAKEY_REFUNDASKINFO = "refundaskdetail";//退票咨询单

    public static String TEMPLATEDATAKEY_COMMONHEADER = "emailHeader";//模板数据Header

    public static String TEMPLATEDATAKEY_COMMONFOOTER = "emailFooter";//模板数据Footer

    public static final String TEMPLATEDATAKEY_FIRSTPAGEURL = "firstpageurl";//大首页地址FirstPageUrl

    public static final String TEMPLATEDATAKEY_PAYURL = "payurl";//PayUrl
    //endregion

}
