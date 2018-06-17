package com.ctrip.ibu.flight.internalws.models.constant;

/**
 * QConfig相关常量
 * Created by kyxie on 2017/7/24.
 */
public final class QConfigConst {

    //region --QConfig配置文件名--
    //以下为QConfig配置文件名
    public static final String CONFIGFILENAME_CONFIGPROFILEPROP = "configprofile.properties";

    //Switch开关配置文件名
    public static final String CONFIGFILENAME_SWITCH = "switch.properties";

    //region --邮件模板文件名--
    //支付成功邮件模板名
    public static final String TEMPLATEFILENAME_PAYMENTSUCCESSEMAIL = "email_paymentsuccess.ftl";

    //支付成功SC邮件模板
    public static final String TEMPLATEFILENAME_PAYMENTSUCCESSEMAIL_SC = "email_paymentsuccess_sc.ftl";

    //出保邮件模板名
    public static final String TEMPLATEFILENAME_INSURANCEISSUEDEMAIL = "email_insuranceissued.ftl";

    //出保邮件模板名-V1
    public static final String TEMPLATEFILENAME_INSURANCEISSUEDEMAIL_V1 = "email_insuranceissued_v1.ftl";

    //退保邮件模板名
    public static final String TEMPLATEFILENAME_INSURANCEREFUNDEDEMAIL = "email_insurancerefunded.ftl";

    //改保邮件模板名
    public static final String TEMPLATEFILENAME_INSURANCECHANGEDEMAIL = "email_insurancechanged.ftl";

    //出保邮件模板名
    public static final String TEMPLATEFILENAME_INSURANCEISSUEDEMAIL_SC = "email_insuranceissued_sc.ftl";

    //退保邮件模板名
    public static final String TEMPLATEFILENAME_INSURANCEREFUNDEDEMAIL_SC = "email_insurancerefunded_sc.ftl";

    //改保邮件模板名
    public static final String TEMPLATEFILENAME_INSURANCECHANGEDEMAIL_SC = "email_insurancechanged_sc.ftl";

    //改保邮件模板名-V1
    public static final String TEMPLATEFILENAME_INSURANCECHANGEDEMAIL_V1 = "email_insurancechanged_v1.ftl";

    //改签完成邮件模板名
    public static final String TEMPLATEFILENAME_NEWFLIGHTSUCCESSEMAIL = "email_newflightsuccess.ftl";

    //SC改签完成邮件模板名
    public static final String TEMPLATEFILENAME_NEWFLIGHTSUCCESSEMAIL_SC = "email_newflightsuccess_sc.ftl";

    //改签取消邮件模板名
    public static final String TEMPLATEFILENAME_NEWFLIGHTCANCELEDEMAIL = "email_newflightcanceled.ftl";

    //SC改签取消邮件模板名
    public static final String TEMPLATEFILENAME_NEWFLIGHTCANCELEDEMAIL_SC = "email_newflightcanceled_sc.ftl";

    //改签支付成功邮件模板名
    public static final String TEMPLATEFILENAME_NEWFLIGHTPAYMENTSUCCESSEMAIL = "email_newflightpaymentsuccess.ftl";

    //SC改签支付成功邮件模板名
    public static final String TEMPLATEFILENAME_NEWFLIGHTPAYMENTSUCCESSEMAIL_SC = "email_newflightpaymentsuccess_sc.ftl";

    //改签待支付邮件模板名
    public static final String TEMPLATEFILENAME_NEWFLIGHTTOBEPAIDEMAIL = "email_newflighttobepaid.ftl";

    //SC改签待支付邮件模板名
    public static final String TEMPLATEFILENAME_NEWFLIGHTTOBEPAIDEMAIL_SC = "email_newflighttobepaid_sc.ftl";


    //改签咨询单邮件模板名
    public static final String TEMPLATEFILENAME_EMAIL_NEWFLIGHTCONSULTINGVERIFIED = "email_newflightconsultingverified.ftl";

    //SC改签咨询单邮件模板名
    public static final String TEMPLATEFILENAME_EMAIL_NEWFLIGHTCONSULTINGVERIFIED_SC = "email_newflightconsultingverified_sc.ftl";

    //订单取消邮件模板名
    public static final String TEMPLATEFILENAME_PAYMENTCANCELEDEMAIL = "email_paymentcanceled.ftl";

    //SC订单取消邮件模板名
    public static final String TEMPLATEFILENAME_PAYMENTCANCELEDEMAIL_SC = "email_paymentcanceled_sc.ftl";

    //支付失败邮件模板名
    public static final String TEMPLATEFILENAME_PAYMENTFAILEDMAIL = "email_paymentfailed.ftl";

    //支付失败SC邮件模板
    public static final String TEMPLATEFILENAME_PAYMENTFAILEDMAIL_SC = "email_paymentfailed_sc.ftl";

    public static final String TEMPLATEFILENAME_XPRODUCTBAGGAGE = "email_xproductbaggage.ftl";

    public static final String TEMPLATEFILENAME_XPRODUCTBAGGAGE_SC = "email_xproductbaggage_sc.ftl";

    public static final String TEMPLATEFILENAME_XPRODUCTMEALS = "email_xproductmeals.ftl";

    public static final String TEMPLATEFILENAME_XPRODUCTMEALS_SC = "email_xproductmeals_sc.ftl";

    public static final String TEMPLATEFILENAME_XPRODUCTNAMEMODIFY = "email_xproductnamemodify.ftl";

    public static final String TEMPLATEFILENAME_XPRODUCTNAMEMODIFY_SC = "email_xproductnamemodify_sc.ftl";

    public static final String TEMPLATEFILENAME_XPRODUCTPAPERMODIFY = "email_xproductpapermodify.ftl";

    public static final String TEMPLATEFILENAME_XPRODUCTPAPERMODIFY_SC = "email_xproductpapermodify_sc.ftl";

    public static final String TEMPLATEFILENAME_XPRODUCTCHECKSEAT = "email_xproductcheckseat.ftl";

    public static final String TEMPLATEFILENAME_XPRODUCTCHECKSEAT_SC = "email_xproductcheckseat_sc.ftl";

    public static final String TEMPLATEFILENAME_XPRODUCTSPECIALREQUEST = "email_xproductspecialrequest.ftl";

    public static final String TEMPLATEFILENAME_XPRODUCTSPECIALREQUEST_SC = "email_xproductspecialrequest_sc.ftl";

    public static final String TEMPLATEFILENAME_REFUNDASKDETAIL = "email_refundaskdetail.ftl";

    public static final String TEMPLATEFILENAME_REFUNDASKDETAIL_SC = "email_refundaskdetail_sc.ftl";

    public static final String TEMPLATEFILENAME_REFUNDSUCCESS = "email_refundsuccess.ftl";

    public static final String TEMPLATEFILENAME_REFUNDFEEVERIFY = "email_refundaskdetail.ftl";

    public static final String TEMPLATEFILENAME_REFUNDFEEVERIFY_SC = "email_refundaskdetail_sc.ftl";

    public static final String TEMPLATEFILENAME_REFUNDSUCCESS_SC = "email_refundsuccess_sc.ftl";

    public static final String TEMPLATEFILENAME_XPRODUCTBAGGAGESUCCESS = "email_xproductbaggage_success.ftl";

    public static final String TEMPLATEFILENAME_XPRODUCTBAGGAGEFAILED = "email_xproductbaggage_failed.ftl";


    public static final String TEMPLATEFILENAME_ERECIPIENT = "email_erecipient.ftl";

    public static final String TEMPLATEFILENAME_ERECIPIENT_SC = "email_erecipient_sc.ftl";

    public static final String TEMPLATEFILENAME_ERECIPIENTATTACHMENT = "email_erecipientattachment.ftl";

    public static final String TEMPLATEFILENAME_ERECIPIENTATTACHMENT_SC = "email_erecipientattachment_sc.ftl";

    public static final String TEMPLATEFILENAME_ITINERARY = "email_itinerary.ftl";

    public static final String TEMPLATEFILENAME_ITINERARY_SC = "email_itinerary_sc.ftl";

    public static final String TEMPLATEFILENAME_ITINERARYATTACHMENT = "email_itineraryattachment.ftl";

    public static final String TEMPLATEFILENAME_ITINERARYATTACHMENT_SC = "email_itineraryattachment_sc.ftl";

    public static final String TEMPLATEFILENAME_RESERVATIONCONFIRMATION = "email_reservationconfirmation.ftl";

    public static final String TEMPLATEFILENAME_RESERVATIONCONFIRMATION_SC = "email_reservationconfirmation_sc.ftl";

    //endregion

    //region --多语言文件名--
    //美国英文多语言配置文件
    public static final String MESSAGERESOURCE_EN_US = "message_en_us.properties";

    //简体中文多语言配置文件
    public static final String MESSAGERESOURCE_ZH_CN = "message_zh_cn.properties";

    //香港繁体多语言配置文件
    public static final String MESSAGERESOURCE_ZH_HK = "message_zh_hk.properties";

    //日文多语言配置文件
    public static final String MESSAGERESOURCE_JA_JP = "message_ja_jp.properties";

    //韩文多语言配置文件
    public static final String MESSAGERESOURCE_KO_KR = "message_ko_kr.properties";

    //德文多语言配置文件
    public static final String MESSAGERESOURCE_DE_DE = "message_de_de.properties";

    //西班牙文多语言配置文件
    public static final String MESSAGERESOURCE_ES_ES = "message_es_es.properties";

    //法语多语言配置文件
    public static final String MESSAGERESOURCE_FR_FR = "message_fr_fr.properties";

    //印尼语多语言配置文件
    public static final String MESSAGERESOURCE_ID_ID = "message_id_id.properties";

    //马来语多语言配置文件
    public static final String MESSAGERESOURCE_MS_MY = "message_ms_my.properties";

    //俄文多语言配置文件
    public static final String MESSAGERESOURCE_RU_RU = "message_ru_ru.properties";

    //泰语多语言配置文件
    public static final String MESSAGERESOURCE_TH_TH = "message_th_th.properties";

    //澳大利亚英文多语言配置文件
    public static final String MESSAGERESOURCE_EN_AU = "message_en_au.properties";
    //endregion

    //endregion

    //邮件测试收件人列表
    public static final String EMAILSENDTESTRECIPIENTLIST = "emailSendTestRecipientList";

    //邮件测试收件人列表开关key
    public static final String OPENEMAILSENDTEST = "openEmailSendTest";

    //邮件测试模版列表
    public static final String EMAILSENDTESTTEMPLATETYPELIST = "emailSendTestTemplateTypeList";

    //Ctrip版权声明描述格式
    public static final String CTRIPCOPYRIGHTNOTICEPATTERN = "ctripCopyrightNoticePattern";

    public static final String EMAILSENDER_PREFIX = "sender";

    public static final String EMAILSENDERNAME_PREFIX = "senderName";

    public static final String ORDERDETAILURLPATTERNKEY = "orderdetailurlpattern_%s_%s";
}
