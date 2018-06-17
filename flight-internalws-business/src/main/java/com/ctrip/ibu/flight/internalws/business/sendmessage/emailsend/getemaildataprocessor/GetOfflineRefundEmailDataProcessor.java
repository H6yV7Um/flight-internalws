package com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.getemaildataprocessor;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.business.common.IEmailCommonBusiness;
import com.ctrip.ibu.flight.internalws.business.common.IFlightCommonBusiness;
import com.ctrip.ibu.flight.internalws.common.businesshelper.BusinessHelper;
import com.ctrip.ibu.flight.internalws.common.components.Checker;
import com.ctrip.ibu.flight.internalws.common.i18n.IMessageResource;
import com.ctrip.ibu.flight.internalws.common.template.ITemplateEngine;
import com.ctrip.ibu.flight.internalws.common.utils.CommonUtil;
import com.ctrip.ibu.flight.internalws.common.utils.LanguageUtil;
import com.ctrip.ibu.flight.internalws.models.annotation.GetEmailDataProcessor;
import com.ctrip.ibu.flight.internalws.models.common.CorpGroup;
import com.ctrip.ibu.flight.internalws.models.common.RequestHead;
import com.ctrip.ibu.flight.internalws.models.common.Trademark;
import com.ctrip.ibu.flight.internalws.models.config.FreemarkerConfig;
import com.ctrip.ibu.flight.internalws.models.constant.*;
import com.ctrip.ibu.flight.internalws.contract.commontypes.EmailTemplateType;
import com.ctrip.ibu.flight.internalws.contract.commontypes.LanguageType;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailRequestType;
import com.ctrip.ibu.flight.internalws.models.errorcode.emailsend.GetEmailDataResultCode;
import com.ctrip.ibu.flight.internalws.models.exception.common.TemplateRenderException;
import com.ctrip.ibu.flight.internalws.models.exception.emailsend.GetEmailDataException;
import com.ctrip.ibu.flight.internalws.models.exception.repository.RepositoryException;
import com.ctrip.ibu.flight.internalws.models.flight.OrderDetailModel;
import com.ctrip.ibu.flight.internalws.models.message.*;
import com.ctrip.ibu.flight.internalws.models.flight.offline.RefundAskDetailModel;
import com.ctrip.ibu.flight.internalws.models.flight.offline.RefundAskDetailRequestModel;
import com.ctrip.ibu.flight.internalws.models.flight.offline.XRefundInfoTypeModel;
import com.ctrip.ibu.flight.internalws.repository.IFlightRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 获取Offline退票信息邮件
 * Create by kyxie on 2018/4/16 20:08
 */
@Component
@GetEmailDataProcessor(name = "获取Offline退票信息邮件处理类",processedEmailTypes = {EmailTemplateType.RefundFeeVerified,EmailTemplateType.RefundSuccess})
public class GetOfflineRefundEmailDataProcessor extends GetEmailDataProcessBase {

    private final static ILog CLOG = LogManager.getLogger(GetOfflineRefundEmailDataProcessor.class);

    private final static boolean NEED_CACHE_ORDER_DETAIL = true;

    private IFlightRepository flightRepository;

    private IFlightCommonBusiness flightCommonBusiness;

    private IEmailCommonBusiness emailCommonBusiness;

    private IMessageResource messageResource;

    private ITemplateEngine templateEngine;

    @Inject
    public GetOfflineRefundEmailDataProcessor(IFlightRepository flightRepository,
                                              IFlightCommonBusiness flightCommonBusiness,
                                              IEmailCommonBusiness emailCommonBusiness,
                                              @Named(BeanConst.BEANNAME_SHARKMESSAGERESOURCEENGINE) IMessageResource messageResource,
                                              ITemplateEngine templateEngine){
        this.flightRepository = flightRepository;
        this.flightCommonBusiness = flightCommonBusiness;
        this.emailCommonBusiness = emailCommonBusiness;
        this.messageResource = messageResource;
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
        SendEmailRequestType sendEmailRequest = request.getSendEmailRequest();

        Checker.checkWithThrowable(sendEmailRequest.orderID == null || sendEmailRequest.orderID <= 0,
                new GetEmailDataException(GetEmailDataResultCode.LACK_ORDERID,"订单号不合法"));

        Checker.checkWithThrowable(sendEmailRequest.additionalIds == null || sendEmailRequest.additionalIds.isEmpty(),new GetEmailDataException(GetEmailDataResultCode.LACK_REFUND_BILL_ID,"additionalIds字段缺少退票单号"));
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

        SendEmailRequestType sendEmailRequest = getEmailDataRequest.getSendEmailRequest();

        RequestHead requestHead = new RequestHead();
        requestHead.setLanguage(sendEmailRequest.languageType);

        OrderDetailModel orderDetail = flightCommonBusiness.getOrderDetail(sendEmailRequest.orderID,requestHead,NEED_CACHE_ORDER_DETAIL);

        if (orderDetail == null){
            throw new GetEmailDataException(GetEmailDataResultCode.ORDER_DETAIL_INVALID,"获取订单详情失败(退票相关邮件需要实时获取订单详情)");
        }

        getEmailDataRequest.setOrderDetail(orderDetail);

        EmailData emailData = new EmailData();

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
    private String getBodyContent(GetEmailDataRequest getEmailDataRequest) throws GetEmailDataException {
        SendEmailRequestType sendEmailRequest = getEmailDataRequest.getSendEmailRequest();
        OrderDetailModel orderDetail = getEmailDataRequest.getOrderDetail();

        String emailTemplateName = getTmplName(sendEmailRequest.emailTemplateType,orderDetail.getCorpGroup());

        if (StringUtils.isBlank(emailTemplateName)){
            throw new GetEmailDataException(GetEmailDataResultCode.TEMPLATE_RENDER_FAILED,String.format("模板%s的模板名获取失败",sendEmailRequest.emailTemplateType.toString()));
        }

        //模板数据
        Map<String,Object> templateData = new HashMap<>();
        templateData.put(EmailConst.TEMPLATEDATAKEY_ORDERDETAIL,orderDetail);

        templateData.put(EmailConst.TEMPLATEDATAKEY_REFUNDASKINFO,getRefundAskModel(orderDetail, sendEmailRequest));

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

        FreemarkerConfig freemarkerConfig = new FreemarkerConfig();
        freemarkerConfig.setFreemarkerLocale(LanguageUtil.mapLanguageTypeToLocale(sendEmailRequest.languageType));

        try {
            return templateEngine.renderTemplate(emailTemplateName,templateData,freemarkerConfig);
        } catch (TemplateRenderException e) {
            GetEmailDataException getEmailDataException = new GetEmailDataException(GetEmailDataResultCode.TEMPLATE_RENDER_FAILED,String.format("渲染%s模板失败",emailTemplateName));
            getEmailDataException.initCause(e);
            throw getEmailDataException;
        }
    }

    /**
     * 获取退票详情
     * */
    private RefundAskDetailModel getRefundAskModel(OrderDetailModel order, SendEmailRequestType sendEmailRequest) throws GetEmailDataException {
        RefundAskDetailModel result;
        try {
            String refundOrderId = CommonUtil.getValue(sendEmailRequest.additionalIds, BusinessConst.SOAREQUESTKEY_REFUNDORDERID, true);
            if (StringUtils.isBlank(refundOrderId)){
                throw new GetEmailDataException(GetEmailDataResultCode.LACK_REFUND_BILL_ID,"缺少退票单号");
            }
            RefundAskDetailRequestModel requestModel = new RefundAskDetailRequestModel();
            RequestHead requestHead = new RequestHead();
            requestHead.setUid(order.getUid());
            requestHead.setLanguage(sendEmailRequest.languageType);
            requestModel.setOrderId(order.getOrderId());
            requestModel.setRefundOrderID(Integer.parseInt(refundOrderId));
            requestModel.setRetryGetOrderDetail(true);
            requestModel.setAccessToken(order.getAccessToken());

            requestModel.setRequestHead(requestHead);

            result = flightRepository.getRefundAskDetailInfo(requestModel);
            if (result == null || result.getRefundAskDetailResponseModel() == null || result.getRefundAskDetailResponseModel().getRefundAskPassengerInfo() == null
                    || result.getRefundAskDetailResponseModel().getRefundAskPassengerInfo().size() == 0){
                throw new GetEmailDataException(GetEmailDataResultCode.LACK_REFUND_INFO,"缺少退票单信息");
            }
            result.getRefundAskDetailResponseModel().setContactInfo(order.getContactInfo());
            result.setLinkUrl(getRefundPayUrl(order, refundOrderId));
            List<XRefundInfoTypeModel> xRefundInfo = result.getRefundAskDetailResponseModel().getxRefundInfoTypes();
            for (XRefundInfoTypeModel info: xRefundInfo) {
                info.setxProductTypeName(getXProductName(info.getxProductType(),sendEmailRequest.languageType));
            }

        } catch (RepositoryException ex) {
            GetEmailDataException getEmailDataException = new GetEmailDataException(GetEmailDataResultCode.LACK_REFUND_INFO,"获取退票单仓储异常");
            getEmailDataException.initCause(ex);
            throw getEmailDataException;
        }
        return result;
    }

    /**
     * 获取X产品多语言
     */
    private String getXProductName(String code, LanguageType sendLan) {

        Locale emailSendLanLocale = LanguageUtil.mapLanguageTypeToLocale(sendLan);

        if ("TravelInsurance".equals(code))
            return messageResource.getResource(MessageResourceConst.MRKEY_TRAVELINSURANCE,emailSendLanLocale);
        if("AcciInsurance".equals(code))
            return messageResource.getResource(MessageResourceConst.MRKEY_ACCIINSURANCE,emailSendLanLocale);
        if ("5".equals(code))
            return messageResource.getResource(MessageResourceConst.MRKEY_XPRODUCT_VIPLounge,emailSendLanLocale);
        if("6".equals(code))
            return messageResource.getResource(MessageResourceConst.MRKEY_XPRODUCT_COUPON,emailSendLanLocale);
        if("8".equals(code))
            return messageResource.getResource(MessageResourceConst.MRKEY_XPRODUCT_BAGGAGE,emailSendLanLocale);
        return  code;

    }

    /**
     * 获取退票咨询单支付URL
     */
    private String getRefundPayUrl(OrderDetailModel orderDetail, String refundorderid) {
        return BusinessConst.OFFLINEREFUNDURLPATTERN
                .replace("${protocol}","https")
                .replace("${domain}", BusinessHelper.getDomain(BusinessHelper.getSiteByServerFrom(orderDetail.getServerFrom()), Trademark.Trip))
                .replace("${orderid}", String.valueOf(orderDetail.getOrderId()))
                .replace("${refundorderid}", refundorderid)
                .replace("${accesstoken}", StringUtils.isBlank(orderDetail.getAccessToken()) ? "" : orderDetail.getAccessToken());
    }

    /**
     * 获取模板名称
     * */
    private String getTmplName(EmailTemplateType templateType, CorpGroup corpGroup) {

        if (corpGroup == CorpGroup.SC){
            switch (templateType){
                case RefundFeeVerified:
                    return QConfigConst.TEMPLATEFILENAME_REFUNDASKDETAIL_SC;
                case RefundSuccess:
                    return QConfigConst.TEMPLATEFILENAME_REFUNDSUCCESS_SC;
            }
        } else {
            switch (templateType){
                case RefundFeeVerified:
                    return QConfigConst.TEMPLATEFILENAME_REFUNDASKDETAIL_SC;
                case RefundSuccess:
                    return QConfigConst.TEMPLATEFILENAME_REFUNDSUCCESS;
            }
        }

        return null;
    }

}
