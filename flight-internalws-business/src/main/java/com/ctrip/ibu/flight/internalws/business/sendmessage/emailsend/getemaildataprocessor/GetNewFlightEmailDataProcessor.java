package com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.getemaildataprocessor;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.business.common.IEmailCommonBusiness;
import com.ctrip.ibu.flight.internalws.business.common.IFlightCommonBusiness;
import com.ctrip.ibu.flight.internalws.common.businesshelper.BusinessHelper;
import com.ctrip.ibu.flight.internalws.common.components.Checker;
import com.ctrip.ibu.flight.internalws.common.log.LoggerHelper;
import com.ctrip.ibu.flight.internalws.common.template.ITemplateEngine;
import com.ctrip.ibu.flight.internalws.common.utils.CommonUtil;
import com.ctrip.ibu.flight.internalws.common.utils.LanguageUtil;
import com.ctrip.ibu.flight.internalws.common.utils.ThrowableUtils;
import com.ctrip.ibu.flight.internalws.models.annotation.GetEmailDataProcessor;
import com.ctrip.ibu.flight.internalws.models.common.CorpGroup;
import com.ctrip.ibu.flight.internalws.models.common.RequestHead;
import com.ctrip.ibu.flight.internalws.models.config.FreemarkerConfig;
import com.ctrip.ibu.flight.internalws.models.constant.BusinessConst;
import com.ctrip.ibu.flight.internalws.models.constant.EmailConst;
import com.ctrip.ibu.flight.internalws.models.constant.QConfigConst;
import com.ctrip.ibu.flight.internalws.contract.commontypes.EmailTemplateType;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailRequestType;
import com.ctrip.ibu.flight.internalws.models.errorcode.emailsend.GetEmailDataResultCode;
import com.ctrip.ibu.flight.internalws.models.exception.common.TemplateRenderException;
import com.ctrip.ibu.flight.internalws.models.exception.emailsend.GetEmailDataException;
import com.ctrip.ibu.flight.internalws.models.exception.repository.RepositoryException;
import com.ctrip.ibu.flight.internalws.models.flight.OrderDetailModel;
import com.ctrip.ibu.flight.internalws.models.flight.RebookingInfo;
import com.ctrip.ibu.flight.internalws.models.log.IndexedLogTag;
import com.ctrip.ibu.flight.internalws.models.log.LogLevel;
import com.ctrip.ibu.flight.internalws.models.message.*;
import com.ctrip.ibu.flight.internalws.models.flight.rebooking.RebookingDetailInfoModel;
import com.ctrip.ibu.flight.internalws.models.flight.rebooking.RebookingQueryRequestModel;
import com.ctrip.ibu.flight.internalws.models.flight.rebooking.RebookingQueryResponseModel;
import com.ctrip.ibu.flight.internalws.repository.FlightRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取改签航班邮件数据处理类
 * Create by kyxie on 2018/4/16 16:13
 */
@Component
@GetEmailDataProcessor(name = "获取确认邮件数据处理类",processedEmailTypes = {EmailTemplateType.NewFlightTobePaid,EmailTemplateType.NewFlightPaymentSuccess,EmailTemplateType.NewFlightCanceled,EmailTemplateType.NewFlightSuccess})
public class GetNewFlightEmailDataProcessor extends GetEmailDataProcessBase {

    private final static ILog CLOG = LogManager.getLogger(GetNewFlightEmailDataProcessor.class);

    private final static boolean NEED_CACHE_ORDER_DETAIL = false;

    private FlightRepository flightRepository;

    private IFlightCommonBusiness flightCommonBusiness;

    private IEmailCommonBusiness emailCommonBusiness;

    private ITemplateEngine templateEngine;

    @Inject
    public GetNewFlightEmailDataProcessor(FlightRepository flightRepository,
                                          IFlightCommonBusiness flightCommonBusiness,
                                          IEmailCommonBusiness emailCommonBusiness,
                                          ITemplateEngine templateEngine){
        this.flightRepository = flightRepository;
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
        SendEmailRequestType sendEmailRequest = request.getSendEmailRequest();

        Checker.checkWithThrowable(sendEmailRequest.orderID == null || sendEmailRequest.orderID <= 0,
                new GetEmailDataException(GetEmailDataResultCode.LACK_ORDERID,"订单号不合法"));

        Checker.checkWithThrowable(sendEmailRequest.additionalIds == null || sendEmailRequest.additionalIds.isEmpty(),new GetEmailDataException(GetEmailDataResultCode.LACK_RESCHEDULE_ORDER_ID,"additionalIds字段缺少改签咨询单编号"));
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
            throw new GetEmailDataException(GetEmailDataResultCode.ORDER_DETAIL_INVALID,String.format("获取%d订单信息失败",sendEmailRequest.orderID));
        }

        getEmailDataRequest.setOrderDetail(orderDetail);

        EmailData emailData = new EmailData();

        //改签待支付判断是否自助支付
        if(sendEmailRequest.emailTemplateType == EmailTemplateType.NewFlightTobePaid){
            String reBookingApplicationId= CommonUtil.getValue(sendEmailRequest.additionalIds, BusinessConst.SOAREQUESTKEY_REBOOKINGAPPLICATIONID, true);
            String flightClass = CommonUtil.getValue(sendEmailRequest.additionalIds, BusinessConst.SOAREQUESTKEY_FLIGHTCLASS, true);
            if(("N".equalsIgnoreCase(flightClass) || "I".equalsIgnoreCase(flightClass))){
                RebookingDetailInfoModel rebookingDetailInfoModel = getRebookingDetail(sendEmailRequest.getOrderID(),reBookingApplicationId,flightClass);
                if(rebookingDetailInfoModel == null){
                    throw new GetEmailDataException(GetEmailDataResultCode.LACK_REBOOKING_INFO,String.format("当前订单：%d,没有查询到改签详情信息", sendEmailRequest.orderID));
                }
                if(rebookingDetailInfoModel.isSelfPay()){
                    List<String> emailAddress = new ArrayList<>();
                    emailAddress.add(rebookingDetailInfoModel.getEmailAddress());
                    emailData.setRecipientList(emailAddress);
                } else {
                    throw new GetEmailDataException(GetEmailDataResultCode.NOT_SELF_PAY,"非自助支付");
                }
            }else {
                throw new GetEmailDataException(GetEmailDataResultCode.LACK_FLIGHT_CLASS,"国际或国内类型错误");
            }
        }

        emailData.setBodyContent(getBodyContent(getEmailDataRequest));
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

        String reBookingApplicationId= CommonUtil.getValue(sendEmailRequest.additionalIds, BusinessConst.SOAREQUESTKEY_REBOOKINGAPPLICATIONID, true);

        if(reBookingApplicationId == null){
            throw new GetEmailDataException(GetEmailDataResultCode.LACK_CHANGE_BILL_ID,"缺少改签单号");
        }

        String emailTemplateName = getTmplName(sendEmailRequest.emailTemplateType,orderDetail.getCorpGroup());

        //模板数据
        Map<String,Object> templateData = new HashMap<>();
        templateData.put(EmailConst.TEMPLATEDATAKEY_ORDERDETAIL,orderDetail);

        if (orderDetail.getRebookingInfoList() != null && orderDetail.getRebookingInfoList().size() > 0){
            Map<String,RebookingInfo> infoHashMap = new HashMap<>();
            for (RebookingInfo rebookingInfo:orderDetail.getRebookingInfoList()) {
                infoHashMap.put(rebookingInfo.getRebookingApplicationId(),rebookingInfo);
            }
            if (infoHashMap.containsKey(reBookingApplicationId)){
                RebookingInfo newflightInfo = infoHashMap.get(reBookingApplicationId);
                //免费改签不发送支付成功邮件
                if(sendEmailRequest.getEmailTemplateType() == EmailTemplateType.NewFlightPaymentSuccess && newflightInfo.getRebookingPayDetail().getTotalRebookFee().intValue() == 0){
                    throw new GetEmailDataException(GetEmailDataResultCode.FREE_RESCHEDULE, "免费改签不发送支付成功邮件");
                }
                templateData.put(EmailConst.TEMPLATEDATAKEY_NEWFLIGHTINFO,newflightInfo);
            }
            else {
                throw new GetEmailDataException(GetEmailDataResultCode.LACK_REBOOKING_INFO, String.format("当前订单：%d,详情没有该改签单的信息", sendEmailRequest.getOrderID()));
            }
        }
        else {
            throw new GetEmailDataException(GetEmailDataResultCode.LACK_REBOOKING_INFO,String.format("当前订单：%d,详情没有改签信息", sendEmailRequest.getOrderID()));
        }

        if(sendEmailRequest.emailTemplateType == EmailTemplateType.NewFlightTobePaid){
            templateData.put(EmailConst.TEMPLATEDATAKEY_PAYURL,getPayUrl(orderDetail,reBookingApplicationId));
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
            throw new GetEmailDataException(GetEmailDataResultCode.TEMPLATE_RENDER_FAILED,"模板不存在");
        }

        FreemarkerConfig config = new FreemarkerConfig();
        config.setFreemarkerLocale(LanguageUtil.mapLanguageTypeToLocale(sendEmailRequest.languageType));

        //调用模板引擎渲染模板
        try {
            return templateEngine.renderTemplate(emailTemplateName, templateData, config);
        } catch (TemplateRenderException e) {
            throw new GetEmailDataException(GetEmailDataResultCode.TEMPLATE_RENDER_FAILED,String.format("渲染模板%s异常",emailTemplateName));
        }

    }
    /**
     * 获取模板名称
     * */
    private String getTmplName(EmailTemplateType templateType, CorpGroup corpGroup){

        if (corpGroup == CorpGroup.SC){
            switch (templateType){
                case NewFlightTobePaid:
                    return QConfigConst.TEMPLATEFILENAME_NEWFLIGHTTOBEPAIDEMAIL_SC;
                case NewFlightPaymentSuccess:
                    return QConfigConst.TEMPLATEFILENAME_NEWFLIGHTPAYMENTSUCCESSEMAIL_SC;
                case NewFlightCanceled:
                    return QConfigConst.TEMPLATEFILENAME_NEWFLIGHTCANCELEDEMAIL_SC;
                case NewFlightSuccess:
                    return QConfigConst.TEMPLATEFILENAME_NEWFLIGHTSUCCESSEMAIL_SC;
            }
        } else {
            switch (templateType){
                case NewFlightTobePaid:
                    return QConfigConst.TEMPLATEFILENAME_NEWFLIGHTTOBEPAIDEMAIL;
                case NewFlightPaymentSuccess:
                    return QConfigConst.TEMPLATEFILENAME_NEWFLIGHTPAYMENTSUCCESSEMAIL;
                case NewFlightCanceled:
                    return QConfigConst.TEMPLATEFILENAME_NEWFLIGHTCANCELEDEMAIL;
                case NewFlightSuccess:
                    return QConfigConst.TEMPLATEFILENAME_NEWFLIGHTSUCCESSEMAIL;
            }
        }

        return "";
    }
    /**
     * 获取PayUrl
     */
    private String getPayUrl(OrderDetailModel orderDetail, String rebookId) {
        return BusinessConst.PAYURLPATTERN
                .replace("${protocol}","https")
                .replace("${domain}", BusinessHelper.getDomain(BusinessHelper.getSiteByServerFrom(orderDetail.getServerFrom()),  BusinessHelper.getTrademark(orderDetail.getServerFrom())))
                .replace("${orderid}", String.valueOf(orderDetail.getOrderId()))
                .replace("${rebookid}", rebookId)
                .replace("${accesstoken}", StringUtils.isBlank(orderDetail.getAccessToken()) ? "" : orderDetail.getAccessToken());
    }

    /**
     * 获取改签详情
     */
    private RebookingDetailInfoModel getRebookingDetail(Long orderId, String changeBillID, String flightClass){
        RebookingDetailInfoModel rebookingDetailInfoModel = new RebookingDetailInfoModel();
        RebookingQueryRequestModel rebookingQueryRequestModel = new RebookingQueryRequestModel();
        rebookingQueryRequestModel.setOrderID(orderId);
        rebookingQueryRequestModel.setRebookingApplicationID(Long.parseLong(changeBillID));
        rebookingQueryRequestModel.setFlightClass(flightClass);
        try {
            RebookingQueryResponseModel rebookingQueryResponseModel = flightRepository.getRebookingDetail(rebookingQueryRequestModel);
            if(rebookingQueryResponseModel != null &&rebookingQueryResponseModel.getRebookingDetailInfoModels() != null){
                for (RebookingDetailInfoModel info:rebookingQueryResponseModel.getRebookingDetailInfoModels()
                        ) {
                    if(info.getRebookingApplicationID().toString() .equals(changeBillID)){
                        rebookingDetailInfoModel = info;
                    }
                }
            }
        } catch (RepositoryException e){
            LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel, LogLevel.ERROR.toString());
            LoggerHelper.appendResponseContent(String.format("获取改签详情异常，异常类型：%s,错误描述：\n%s", e.getErrorSource(), ThrowableUtils.getExceptionDesc(e)));
        }
        return rebookingDetailInfoModel;
    }
}
