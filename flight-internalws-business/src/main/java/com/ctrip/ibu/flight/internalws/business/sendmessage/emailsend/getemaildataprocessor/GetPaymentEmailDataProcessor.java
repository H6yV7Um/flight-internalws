package com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.getemaildataprocessor;

import com.ctrip.ibu.flight.internalws.business.common.IEmailCommonBusiness;
import com.ctrip.ibu.flight.internalws.business.common.IFlightCommonBusiness;
import com.ctrip.ibu.flight.internalws.common.businesshelper.BusinessHelper;
import com.ctrip.ibu.flight.internalws.common.components.Checker;
import com.ctrip.ibu.flight.internalws.common.template.ITemplateEngine;
import com.ctrip.ibu.flight.internalws.common.utils.LanguageUtil;
import com.ctrip.ibu.flight.internalws.contract.commontypes.EmailTemplateType;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailRequestType;
import com.ctrip.ibu.flight.internalws.models.annotation.GetEmailDataProcessor;
import com.ctrip.ibu.flight.internalws.models.common.CorpGroup;
import com.ctrip.ibu.flight.internalws.models.common.RequestHead;
import com.ctrip.ibu.flight.internalws.models.config.FreemarkerConfig;
import com.ctrip.ibu.flight.internalws.models.constant.BusinessConst;
import com.ctrip.ibu.flight.internalws.models.constant.EmailConst;
import com.ctrip.ibu.flight.internalws.models.constant.QConfigConst;
import com.ctrip.ibu.flight.internalws.models.errorcode.emailsend.GetEmailDataResultCode;
import com.ctrip.ibu.flight.internalws.models.exception.common.TemplateRenderException;
import com.ctrip.ibu.flight.internalws.models.exception.emailsend.GetEmailDataException;
import com.ctrip.ibu.flight.internalws.models.flight.OrderDetailModel;
import com.ctrip.ibu.flight.internalws.models.message.*;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取支付相关邮件数据处理类
 * Create by kyxie on 2018/4/16 22:39
 */
@Component
@GetEmailDataProcessor(name = "获取确认邮件数据处理类",processedEmailTypes = {EmailTemplateType.PaymentCanceled,EmailTemplateType.PaymentFailed})
public class GetPaymentEmailDataProcessor extends GetEmailDataProcessBase {

    private final static boolean NEED_CACHE_ORDER_DETAIL = false;

    private IFlightCommonBusiness flightCommonBusiness;

    private IEmailCommonBusiness emailCommonBusiness;

    private ITemplateEngine templateEngine;

    @Inject
    public GetPaymentEmailDataProcessor(IFlightCommonBusiness flightCommonBusiness,
                                        IEmailCommonBusiness emailCommonBusiness,
                                        ITemplateEngine templateEngine){
        this.flightCommonBusiness = flightCommonBusiness;
        this.emailCommonBusiness = emailCommonBusiness;
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
     * @param getEmailDataRequest 获取消息数据请求
     * @return 获取消息数据响应
     * @throws GetEmailDataException 获取消息异常类型
     */
    @Override
    public GetEmailDataResponse getMessageSendInfo(GetEmailDataRequest getEmailDataRequest) throws GetEmailDataException {
        GetEmailDataResponse response = new GetEmailDataResponse();

        EmailData emailData = new EmailData();

        SendEmailRequestType sendEmailRequest = getEmailDataRequest.getSendEmailRequest();

        RequestHead requestHead = new RequestHead();
        requestHead.setLanguage(sendEmailRequest.languageType);

        OrderDetailModel orderDetail = flightCommonBusiness.getOrderDetail(sendEmailRequest.orderID, requestHead,NEED_CACHE_ORDER_DETAIL);
        if (orderDetail == null){
            throw new GetEmailDataException(GetEmailDataResultCode.ORDER_DETAIL_INVALID,"获取订单详情失败");
        }

        getEmailDataRequest.setOrderDetail(orderDetail);

        String bodyContent = getBodyContent(getEmailDataRequest);
        emailData.setBodyContent(bodyContent);
        emailData.setRecipientList(BusinessHelper.getContactEmailListFromOrder(orderDetail));

        response.setEmailData(emailData);

        EmailSendConfig emailSendConfig = new EmailSendConfig();
        emailSendConfig.setEmailSendLan(sendEmailRequest.languageType);
        emailSendConfig.setOrderTrademark(orderDetail.getTrademark());
        response.setEmailConfig(emailSendConfig);

        return response;
    }

    /**
     * 获取邮件主体数据
     * */
    private String getBodyContent(GetEmailDataRequest getEmailDataRequest) throws GetEmailDataException{

        SendEmailRequestType sendEmailRequest = getEmailDataRequest.getSendEmailRequest();
        OrderDetailModel orderDetail = getEmailDataRequest.getOrderDetail();

        String emailTemplateName = getTmplName(sendEmailRequest.emailTemplateType,orderDetail.getCorpGroup());

        //模板数据
        Map<String,Object> templateData = new HashMap<>();
        templateData.put(EmailConst.TEMPLATEDATAKEY_ORDERDETAIL,orderDetail);
        templateData.put(EmailConst.TemplateDataKey.APP_DOWNLOAD_URI,
                BusinessHelper.getAppDownloadUrl(LanguageUtil.mapLanguageTypeToLocale(sendEmailRequest.languageType),
                        orderDetail.getAllianceOrderInfo() == null ? null : orderDetail.getAllianceOrderInfo().getAllianceId(),
                        orderDetail.getAllianceOrderInfo() == null ? null : orderDetail.getAllianceOrderInfo().getSid()));

        if(sendEmailRequest.emailTemplateType == EmailTemplateType.PaymentCanceled){
            templateData.put(EmailConst.TEMPLATEDATAKEY_FIRSTPAGEURL,getFirstPageUrl(orderDetail));
        }

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

        //模板渲染
        if (templateEngine.hasTemplate(emailTemplateName)){
            throw new GetEmailDataException(GetEmailDataResultCode.TEMPLATE_RENDER_FAILED,String.format("模板%s不存在",emailTemplateName));
        }

        FreemarkerConfig config = new FreemarkerConfig();
        config.setFreemarkerLocale(LanguageUtil.mapLanguageTypeToLocale(sendEmailRequest.languageType));


        //调用模板引擎渲染模板
        try {
            return templateEngine.renderTemplate(emailTemplateName, templateData, config);
        } catch (TemplateRenderException e) {
            GetEmailDataException getEmailDataException = new GetEmailDataException(GetEmailDataResultCode.TEMPLATE_RENDER_FAILED,String.format("渲染%s模板异常",emailTemplateName));
            getEmailDataException.initCause(e);
            throw getEmailDataException;
        }
    }

    /**
     * 获取模板名称
     * */
    private String getTmplName(EmailTemplateType templateType, CorpGroup corpGroup){
        if (corpGroup == CorpGroup.SC){
            switch (templateType){
                case PaymentCanceled:
                    return QConfigConst.TEMPLATEFILENAME_PAYMENTCANCELEDEMAIL_SC;
                case PaymentFailed:
                    return QConfigConst.TEMPLATEFILENAME_PAYMENTFAILEDMAIL_SC;
            }
        } else {
            switch (templateType){
                case PaymentCanceled:
                    return QConfigConst.TEMPLATEFILENAME_PAYMENTCANCELEDEMAIL;
                case PaymentFailed:
                    return QConfigConst.TEMPLATEFILENAME_PAYMENTFAILEDMAIL;
            }
        }
        return "";
    }
    /**
     * 获取大首页地址FirstPageUrl
     */
    private String getFirstPageUrl(OrderDetailModel orderDetail) {
        return BusinessConst.FIRSTPAGEURLPATTERN
                .replace("${protocol}","https")
                .replace("${domain}",BusinessHelper.getDomain(BusinessHelper.getSiteByServerFrom(orderDetail.getServerFrom()), BusinessHelper.getTrademark(orderDetail.getServerFrom())));
    }


}
