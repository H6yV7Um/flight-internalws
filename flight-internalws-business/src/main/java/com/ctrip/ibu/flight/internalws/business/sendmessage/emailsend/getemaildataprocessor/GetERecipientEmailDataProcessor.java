package com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.getemaildataprocessor;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.business.common.IEmailCommonBusiness;
import com.ctrip.ibu.flight.internalws.business.common.IFlightCommonBusiness;
import com.ctrip.ibu.flight.internalws.common.businesshelper.BusinessHelper;
import com.ctrip.ibu.flight.internalws.common.components.Checker;
import com.ctrip.ibu.flight.internalws.common.components.HttpContext;
import com.ctrip.ibu.flight.internalws.common.i18n.IMessageResource;
import com.ctrip.ibu.flight.internalws.common.log.LoggerHelper;
import com.ctrip.ibu.flight.internalws.common.template.ITemplateEngine;
import com.ctrip.ibu.flight.internalws.common.utils.*;
import com.ctrip.ibu.flight.internalws.models.annotation.GetEmailDataProcessor;
import com.ctrip.ibu.flight.internalws.models.common.CorpGroup;
import com.ctrip.ibu.flight.internalws.models.common.PdfGenerateResult;
import com.ctrip.ibu.flight.internalws.models.common.RequestHead;
import com.ctrip.ibu.flight.internalws.models.config.FreemarkerConfig;
import com.ctrip.ibu.flight.internalws.models.constant.*;
import com.ctrip.ibu.flight.internalws.contract.commontypes.EmailTemplateType;
import com.ctrip.ibu.flight.internalws.contract.commontypes.LanguageType;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailRequestType;
import com.ctrip.ibu.flight.internalws.models.errorcode.emailsend.GetEmailDataResultCode;
import com.ctrip.ibu.flight.internalws.models.exception.common.PdfException;
import com.ctrip.ibu.flight.internalws.models.exception.common.TemplateRenderException;
import com.ctrip.ibu.flight.internalws.models.exception.emailsend.GetEmailDataException;
import com.ctrip.ibu.flight.internalws.models.flight.FlightColumn;
import com.ctrip.ibu.flight.internalws.models.flight.FlightInfo;
import com.ctrip.ibu.flight.internalws.models.flight.OrderDetailModel;
import com.ctrip.ibu.flight.internalws.models.log.IndexedLogTag;
import com.ctrip.ibu.flight.internalws.models.message.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

/**
 * 获取报销凭证邮件数据处理类
 * Create by kyxie on 2018/4/15 22:15
 */
@Component
@GetEmailDataProcessor(name = "获取报销凭证邮件数据处理类",processedEmailTypes = {EmailTemplateType.EReceipt})
public class GetERecipientEmailDataProcessor extends GetEmailDataProcessBase {

    private final static ILog LOGGER = LogManager.getLogger(GetERecipientEmailDataProcessor.class);

    //不需要多语言的邮件语言列表
    private final static List<LanguageType> NO_NEED_MULTILAN_ORDERDETAIL_LANLIST = new ArrayList<>();

    private final static boolean NEED_CACHE_ORDER_DETAIL = false;

    private IFlightCommonBusiness flightCommonBusiness;

    private IEmailCommonBusiness emailCommonBusiness;

    private IMessageResource sharkMessageResource;

    private ITemplateEngine templateEngine;

    static {
        init();
    }

    @Inject
    public GetERecipientEmailDataProcessor(IFlightCommonBusiness flightCommonBusiness,
                                           IEmailCommonBusiness emailCommonBusiness,
                                           @Named(BeanConst.BEANNAME_SHARKMESSAGERESOURCEENGINE) IMessageResource sharkMessageResource,
                                           @Named(BeanConst.BEANNAME_FREEMARKERENGINE) ITemplateEngine templateEngine){
        this.flightCommonBusiness = flightCommonBusiness;
        this.emailCommonBusiness = emailCommonBusiness;
        this.sharkMessageResource = sharkMessageResource;
        this.templateEngine = templateEngine;
    }

    /**
     * 验证参数
     *
     * @param request 请求
     * @throws GetEmailDataException 获取消息异常
     */
    @Override
    public void validateRequest(GetEmailDataRequest request) throws GetEmailDataException {
        Checker.checkWithThrowable(request.getSendEmailRequest().orderID == null || request.getSendEmailRequest().orderID <= 0,
                new GetEmailDataException(GetEmailDataResultCode.LACK_ORDERID,"订单号不合法"));
    }

    /**
     * 获取消息发送信息
     *
     * @return 获取消息数据响应
     * @throws GetEmailDataException 获取消息异常类型
     */
    @Override
    public GetEmailDataResponse getMessageSendInfo(GetEmailDataRequest getEmailDataRequest) throws GetEmailDataException {
        GetEmailDataResponse response = new GetEmailDataResponse();

        SendEmailRequestType sendEmailRequest = getEmailDataRequest.getSendEmailRequest();

        List<OrderDetailModel> orderDetailList = new ArrayList<>();

        //先获取英文版订单详情
        RequestHead requestHead = new RequestHead();
        requestHead.setLanguage(LanguageType.en_US);

        OrderDetailModel orderDetail = flightCommonBusiness.getOrderDetail(sendEmailRequest.orderID,requestHead,NEED_CACHE_ORDER_DETAIL);

        if (orderDetail == null){
            throw new GetEmailDataException(GetEmailDataResultCode.ORDER_DETAIL_INVALID,String.format("获取%d订单详情为NULL",sendEmailRequest.orderID));
        }

        orderDetailList.add(orderDetail);
        getEmailDataRequest.setOrderDetail(orderDetail);

        if (!NO_NEED_MULTILAN_ORDERDETAIL_LANLIST.contains(sendEmailRequest.languageType)){
            LoggerHelper.appendResponseContent(String.format("当前邮件发送语言为%s，需要多语言版订单详情",String.valueOf(sendEmailRequest.languageType)));
            requestHead.setLanguage(sendEmailRequest.languageType);
            OrderDetailModel mulLanOrderDetail = flightCommonBusiness.getOrderDetail(sendEmailRequest.orderID,requestHead,NEED_CACHE_ORDER_DETAIL);
            if (mulLanOrderDetail != null){
                orderDetailList.add(mulLanOrderDetail);
                getEmailDataRequest.setOrderDetail(mulLanOrderDetail);
            } else {
                LoggerHelper.appendResponseContent(String.format("获取订单--%d--的%s版的订单详情失败",sendEmailRequest.orderID,String.valueOf(sendEmailRequest.languageType)));
            }
        } else {
            LoggerHelper.appendResponseContent(String.format("当前邮件发送语言为%s，不需要多语言版订单详情",String.valueOf(sendEmailRequest.languageType)));
        }

        EmailData emailData = new EmailData();

        emailData.setRecipientList(BusinessHelper.getContactEmailListFromOrder(orderDetail));
        emailData.setBodyContent(getBodyContent(getEmailDataRequest));

        LoggerHelper.appendResponseContent("邮件Body设置成功，开始获取附件...");
        emailData.setAttachmentList(getEmailAttachmentList(orderDetailList,sendEmailRequest.languageType,sendEmailRequest.additionalIds));
        emailData.setRecipientList(BusinessHelper.getContactEmailListFromOrder(orderDetail));
        response.setEmailData(emailData);

        EmailSendConfig emailSendConfig = new EmailSendConfig();
        emailSendConfig.setEmailSendLan(sendEmailRequest.languageType);
        emailSendConfig.setOrderTrademark(orderDetail.getTrademark());
        response.setEmailConfig(emailSendConfig);

        return response;
    }

    /**
     * 获取邮件主体(需要抛出异常，因为主体获取不到的话，后续流程就没有必要再继续了)
     * @param getEmailDataRequest 获取邮件数据Request
     * */
    private String getBodyContent(GetEmailDataRequest getEmailDataRequest) throws GetEmailDataException {
        OrderDetailModel orderDetail = getEmailDataRequest.getOrderDetail();
        SendEmailRequestType sendEmailRequest = getEmailDataRequest.getSendEmailRequest();

        //邮件主体模板数据
        Map<String,Object> templateData = new HashMap<>();
        templateData.put(EmailConst.TEMPLATEDATAKEY_ORDERDETAIL,orderDetail);

        //邮件头尾
        EmailHeaderAndFooterResponse headerAndFooter = emailCommonBusiness.getEmailHeaderAndFooter(orderDetail,sendEmailRequest.emailTemplateType,sendEmailRequest.languageType);
        String header = "";
        String footer = "";
        if (headerAndFooter != null){
            header = headerAndFooter.getEmailHeader() == null ? "" : headerAndFooter.getEmailHeader();
            footer = headerAndFooter.getEmailFooter() == null ? "" : headerAndFooter.getEmailFooter();
        }
        templateData.put(EmailConst.TEMPLATEDATAKEY_COMMONHEADER,header);
        templateData.put(EmailConst.TEMPLATEDATAKEY_COMMONFOOTER,footer);

        FreemarkerConfig config = new FreemarkerConfig();
        config.setFreemarkerLocale(LanguageUtil.mapLanguageTypeToLocale(sendEmailRequest.languageType));

        String emailTmplName = getTmplName(orderDetail.getCorpGroup());
        try {
            return templateEngine.renderTemplate(emailTmplName,templateData,config);
        } catch (TemplateRenderException e) {
            LoggerHelper.appendResponseContent(String.format("渲染%s模板异常，异常信息:\n%s",emailTmplName, ThrowableUtils.getExceptionDesc(e)));
            throw new GetEmailDataException(GetEmailDataResultCode.TEMPLATE_RENDER_FAILED,String.format("渲染%s模板失败",emailTmplName));
        }
    }

    /**
     * 获取邮件附件列表（不要抛异常，因为个别附件缺失不应该影响邮件主题发送）
     * @param orderDetailList 订单详情列表(英文及多语言版)
     * @param emailSendLan 邮件发送语言
     * @param additionalIds 附件信息
     * @return 附件列表
     * */
    private List<EmailAttachmentDTO> getEmailAttachmentList(List<OrderDetailModel> orderDetailList, LanguageType emailSendLan, Map<String,String> additionalIds) {
        List<EmailAttachmentDTO> attachmentList = new ArrayList<>();
        LoggerHelper.appendResponseContent("开始获取报销凭证附件...");
        EmailAttachmentDTO eRecipientAttachment = getBilingualAttachment(getEReceiptAttachmentTmplName(orderDetailList.get(0).getCorpGroup()),orderDetailList,emailSendLan,additionalIds);
        if (eRecipientAttachment != null && eRecipientAttachment.getAttachmentContent() != null && eRecipientAttachment.getAttachmentContent().length > 0){
            attachmentList.add(eRecipientAttachment);
        }
        return attachmentList;
    }


    /**
     * 获取双语附件
     * @param attachmentTmplName 附件模板名
     * @param orderDetailList 订单详情列表
     * @param emailSendLan 邮件发送语言
     * */
    private EmailAttachmentDTO getBilingualAttachment(String attachmentTmplName, List<OrderDetailModel> orderDetailList, LanguageType emailSendLan, Map<String,String> additionalIds){

        EmailAttachmentDTO attachment = new EmailAttachmentDTO();

        OrderDetailModel defaultOrderDetail = orderDetailList.get(0);
        OrderDetailModel mulLanOrderDetail = orderDetailList.get(orderDetailList.size() - 1);

        Locale emailSendLanLocale = LanguageUtil.mapLanguageTypeToLocale(emailSendLan);

        //模板数据
        Map<String,Object> templateData = new HashMap<>();
        templateData.put(EmailConst.TEMPLATEDATAKEY_ORDERDETAIL,defaultOrderDetail);
        templateData.put(EmailConst.TEMPLATEDATAKEY_CUSTOMERNAME, CommonUtil.getValue(additionalIds, BusinessConst.SOAREQUESTKEY_CUSTOMERNAME,true));

        templateData.put(EmailConst.TEMPLATEDATAKEY_ISSUINGAIRLINES,getIssuingAirlines(defaultOrderDetail));//承运航司
        templateData.put(EmailConst.TEMPLATEDATAKEY_RESOURCEPATH,emailCommonBusiness.getResourcePath(defaultOrderDetail.getFlightClass(),defaultOrderDetail.getTrademark(),defaultOrderDetail.getPaymentInfo().getCurrencyType()));
        templateData.put(EmailConst.TEMPLATEDATAKEY_FONTFAMILY, BusinessHelper.getFontFamilyByLan(LanguageType.en_US));

        switch (attachmentTmplName){
            case QConfigConst.TEMPLATEFILENAME_ERECIPIENTATTACHMENT:
            case QConfigConst.TEMPLATEFILENAME_ERECIPIENTATTACHMENT_SC:
                attachment.setAttachmentName(String.format("%s.pdf",sharkMessageResource.getResource(MessageResourceConst.MRKEY_ERECEIPT,emailSendLanLocale)));
                break;
            case QConfigConst.TEMPLATEFILENAME_ITINERARYATTACHMENT:
            case QConfigConst.TEMPLATEFILENAME_ITINERARYATTACHMENT_SC:
                attachment.setAttachmentName(String.format("%s.pdf",sharkMessageResource.getResource(MessageResourceConst.MRKEY_ITINERARY,emailSendLanLocale)));
                break;
        }

        try {
            //先获取英文附件
            FreemarkerConfig englishConfig = new FreemarkerConfig();
            englishConfig.setFreemarkerLocale(new Locale("en_US"));

            //设置邮件发送语言
            HttpContext.setEmailsendLanguage(LanguageType.en_US);

            List<String> attachmentList = new ArrayList<>();
            try {
                attachmentList.add(templateEngine.renderTemplate(attachmentTmplName,templateData,englishConfig));
                LoggerHelper.appendResponseContent("成功获取英文版附件!");
            } catch (TemplateRenderException e) {
                LoggerHelper.appendResponseContent(String.format("获取英文版附件异常，异常信息：\n%s",ThrowableUtils.getExceptionDesc(e)));
                LOGGER.error(LogConst.CLOGTITLE_SYSTEMMONITOR,String.format("渲染确认邮件第1封英文附件出现模板异常，模板名：%s，异常描述：\n%s",e.getTemplateName(),ThrowableUtils.getExceptionDesc(e)));
            }

            //再获取邮件发送语言对应的附件
            if (!NO_NEED_MULTILAN_ORDERDETAIL_LANLIST.contains(emailSendLan) && orderDetailList.size() > 1){

                //设置邮件发送语言
                HttpContext.setEmailsendLanguage(emailSendLan);

                LoggerHelper.appendResponseContent(String.format("当前邮件发送语言%s需要获取多语言版附件",String.valueOf(emailSendLan)));
                templateData.put(EmailConst.TEMPLATEDATAKEY_ISSUINGAIRLINES,getIssuingAirlines(mulLanOrderDetail));//承运航司
                templateData.put(EmailConst.TEMPLATEDATAKEY_FONTFAMILY, BusinessHelper.getFontFamilyByLan(emailSendLan));
                templateData.put(EmailConst.TEMPLATEDATAKEY_ORDERDETAIL,mulLanOrderDetail);

                try {
                    FreemarkerConfig curLanConfig = new FreemarkerConfig();
                    curLanConfig.setFreemarkerLocale(LanguageUtil.mapLanguageTypeToLocale(emailSendLan));
                    attachmentList.add(templateEngine.renderTemplate(attachmentTmplName,templateData,curLanConfig));
                } catch (TemplateRenderException e) {
                    LoggerHelper.appendResponseContent(String.format("获取多语言版附件异常，异常信息：\n%s!",ThrowableUtils.getExceptionDesc(e)));
                    LOGGER.error(LogConst.CLOGTITLE_SYSTEMMONITOR,String.format("渲染确认邮件第2封多语言附件出现模板异常，模板名：%s，异常描述：\n%s",e.getTemplateName(),ThrowableUtils.getExceptionDesc(e)));
                }
            } else {
                LoggerHelper.appendResponseContent(String.format("当前邮件发送语言%s不需要获取多语言版附件",String.valueOf(emailSendLan)));
            }

            for (String attachmentStr : attachmentList){
                LoggerHelper.appendResponseContent(String.format("PDF附件内容：\n%s",attachmentStr));
                LOGGER.info(LogConst.Clog.LogTitle.APP_LOG,String.format("PDF附件内容：\n%s",attachmentStr));
            }

            byte[] attachmentBytes = PdfUtilV2.convertHtmlToPdfByteArray(attachmentList);
            if (attachmentBytes == null || attachmentBytes.length == 0){
                LoggerHelper.appendResponseContent("序列化附件HTML失败，附件无效！");
            } else {
                LoggerHelper.appendResponseContent("序列化附件HTML成功！");
                attachment.setAttachmentContent(PdfUtilV2.convertHtmlToPdfByteArray(attachmentList));
            }

            LoggerHelper.addIndexedLogTag( IndexedLogTag.PdfGenerateResult,PdfGenerateResult.SUCCESS.toString());
            LoggerHelper.appendResponseContent("获取PDF附件成功");
        } catch (PdfException e){
            LoggerHelper.addIndexedLogTag(IndexedLogTag.PdfGenerateResult,String.valueOf(e.getPdfGenerateResult()));
            LoggerHelper.appendResponseContent(String.format("获取附件逻辑抛出PdfException异常，异常信息:\n%s",ThrowableUtils.getExceptionDesc(e)));
            LOGGER.error(LogConst.CLOGTITLE_SYSTEMMONITOR,e);
        } catch (Exception e){
            LoggerHelper.addIndexedLogTag(IndexedLogTag.PdfGenerateResult,String.valueOf(PdfGenerateResult.NORMALERROR));
            LoggerHelper.appendResponseContent(String.format("获取附件逻辑抛出异常，异常信息:\n%s",ThrowableUtils.getExceptionDesc(e)));
            LOGGER.error(LogConst.CLOGTITLE_SYSTEMMONITOR,e);
        }

        return attachment;
    }

    /**
     * 获取承运航司字符串描述
     * @param orderDetail 订单详情
     * */
    private String getIssuingAirlines(OrderDetailModel orderDetail){
        if (orderDetail == null || orderDetail.getFlightInfoList() == null || orderDetail.getFlightInfoList().isEmpty()){
            return null;
        }

        String issuingAirlines = "";
        for (FlightInfo flightInfo : orderDetail.getFlightInfoList()) {
            if (flightInfo.getColumnList() != null && !flightInfo.getColumnList().isEmpty()){
                for (FlightColumn flightColumn : flightInfo.getColumnList()) {
                    if (StringUtils.isBlank(issuingAirlines)){
                        issuingAirlines = flightColumn.getCarrierAirline();
                    } else {
                        if (StringUtils.isNotBlank(flightColumn.getCarrierAirline())){
                            issuingAirlines = String.join(",",issuingAirlines,flightColumn.getCarrierAirline());
                        }
                    }
                }
            }
        }
        return issuingAirlines;
    }

    /**
     * 获取主模板名
     * @param corpGroup 企业所属组
     * */
    private String getTmplName(CorpGroup corpGroup){
        switch (corpGroup){
            case SC:
                return QConfigConst.TEMPLATEFILENAME_ERECIPIENT_SC;
            default:
                return QConfigConst.TEMPLATEFILENAME_ERECIPIENT;
        }
    }

    /**
     * 获取报销凭证附件模板名
     * @param corpGroup 企业所属组
     * */
    private String getEReceiptAttachmentTmplName(CorpGroup corpGroup){
        switch (corpGroup){
            case SC:
                return QConfigConst.TEMPLATEFILENAME_ERECIPIENTATTACHMENT_SC;
            default:
                return QConfigConst.TEMPLATEFILENAME_ERECIPIENTATTACHMENT;
        }
    }

    /**
     * 初始化
     * */
    private static void init(){
        NO_NEED_MULTILAN_ORDERDETAIL_LANLIST.add(LanguageType.en_US);
        NO_NEED_MULTILAN_ORDERDETAIL_LANLIST.add(LanguageType.en_AU);
        NO_NEED_MULTILAN_ORDERDETAIL_LANLIST.add(LanguageType.en_HK);
        NO_NEED_MULTILAN_ORDERDETAIL_LANLIST.add(LanguageType.en_SG);
    }

}
