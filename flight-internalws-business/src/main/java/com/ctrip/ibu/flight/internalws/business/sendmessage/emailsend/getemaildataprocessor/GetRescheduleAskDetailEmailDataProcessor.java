package com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.getemaildataprocessor;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.business.common.IEmailCommonBusiness;
import com.ctrip.ibu.flight.internalws.business.common.IFlightCommonBusiness;
import com.ctrip.ibu.flight.internalws.common.businesshelper.BusinessHelper;
import com.ctrip.ibu.flight.internalws.common.components.Checker;
import com.ctrip.ibu.flight.internalws.common.template.ITemplateEngine;
import com.ctrip.ibu.flight.internalws.common.utils.CommonUtil;
import com.ctrip.ibu.flight.internalws.common.utils.LanguageUtil;
import com.ctrip.ibu.flight.internalws.models.annotation.GetEmailDataProcessor;
import com.ctrip.ibu.flight.internalws.models.common.CorpGroup;
import com.ctrip.ibu.flight.internalws.models.common.RequestHead;
import com.ctrip.ibu.flight.internalws.models.common.Trademark;
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
import com.ctrip.ibu.flight.internalws.models.message.*;
import com.ctrip.ibu.flight.internalws.models.flight.offline.RescheduleAskDetailRequestModel;
import com.ctrip.ibu.flight.internalws.models.flight.offline.RescheduleAskInfoModel;
import com.ctrip.ibu.flight.internalws.repository.IFlightRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取改签咨询单邮件数据处理类
 * Create by kyxie on 2018/4/16 22:57
 */
@Component
@GetEmailDataProcessor(name = "获取改签咨询单邮件数据处理类",processedEmailTypes = {EmailTemplateType.NewFlightConsultingVerified})
public class GetRescheduleAskDetailEmailDataProcessor extends GetEmailDataProcessBase {

    private final static ILog LOGGER = LogManager.getLogger(GetRescheduleAskDetailEmailDataProcessor.class);

    private final static boolean NEED_CACHE_ORDER_DETAIL = true;

    private IFlightRepository flightRepository;

    private IFlightCommonBusiness flightCommonBusiness;

    private IEmailCommonBusiness emailCommonBusiness;

    private ITemplateEngine templateEngine;

    @Inject
    public GetRescheduleAskDetailEmailDataProcessor(IFlightRepository flightRepository,
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

        Checker.checkWithThrowable(sendEmailRequest.additionalIds == null || sendEmailRequest.additionalIds.isEmpty(),new GetEmailDataException(GetEmailDataResultCode.LACK_RESCHEDULE_ASK_ID,"additionalIds字段改签咨询单编号"));
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

        OrderDetailModel orderDetail = flightCommonBusiness.getOrderDetail(sendEmailRequest.orderID, requestHead,NEED_CACHE_ORDER_DETAIL);

        if (orderDetail == null){
            throw new GetEmailDataException(GetEmailDataResultCode.ORDER_DETAIL_INVALID,"获取订单详情失败");
        }

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
     * 获取邮件BodyContent
     * */
    private String getBodyContent(GetEmailDataRequest getEmailDataRequest) throws GetEmailDataException {
        SendEmailRequestType sendEmailRequest = getEmailDataRequest.getSendEmailRequest();
        OrderDetailModel orderDetail = getEmailDataRequest.getOrderDetail();

        String emailTemplateName = getTmplName(sendEmailRequest.emailTemplateType,orderDetail.getCorpGroup());

        //模板数据
        Map<String,Object> templateData = new HashMap<>();
        templateData.put(EmailConst.TEMPLATEDATAKEY_ORDERDETAIL,orderDetail);

        templateData.put(EmailConst.TEMPLATEDATAKEY_RESCHEDULEASKINFO,getRescheduleAskInfoModel(orderDetail,sendEmailRequest));

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

        if (templateEngine.hasTemplate(emailTemplateName)){
            throw new GetEmailDataException(GetEmailDataResultCode.TEMPLATE_RENDER_FAILED,String.format("模板%s不存在",emailTemplateName));
        }

        FreemarkerConfig config = new FreemarkerConfig();
        config.setFreemarkerLocale(LanguageUtil.mapLanguageTypeToLocale(sendEmailRequest.languageType));

        try {
            return templateEngine.renderTemplate(emailTemplateName,templateData,config);
        } catch (TemplateRenderException e) {
            GetEmailDataException getEmailDataException = new GetEmailDataException(GetEmailDataResultCode.TEMPLATE_RENDER_FAILED,String.format("渲染模板%s异常",emailTemplateName));
            getEmailDataException.initCause(e);
            throw getEmailDataException;
        }
    }

    /**
     * 获取改签咨询单信息
     * @param orderDetailModel 订单详情
     * @param sendEmailRequest 邮件发送请求
     * */
    private RescheduleAskInfoModel getRescheduleAskInfoModel(OrderDetailModel orderDetailModel, SendEmailRequestType sendEmailRequest)
            throws GetEmailDataException{

        RescheduleAskInfoModel result;
        try {
            String reBookingApplicationId= CommonUtil.getValue(sendEmailRequest.additionalIds, BusinessConst.SOAREQUESTKEY_REBOOKINGAPPLICATIONID, true);
            if (reBookingApplicationId == null||reBookingApplicationId.isEmpty()) {
                throw new GetEmailDataException(GetEmailDataResultCode.LACK_RESCHEDULE_ASK_ID, "缺少改签咨询单申请单号");
            }

            RescheduleAskDetailRequestModel searchModel=new RescheduleAskDetailRequestModel();
            if ("International".equals(String.valueOf(orderDetailModel.getFlightClass()))) {
                searchModel.setFlightOrderClass("I");
            }
            else {
                searchModel.setFlightOrderClass("N");
            }
            searchModel.setOrderId(orderDetailModel.getOrderId());
            searchModel.setRebookingApplicationId(Long.parseLong(reBookingApplicationId));
            searchModel.setAccessToken(orderDetailModel.getAccessToken());
            //赋值语言
            RequestHead head=new RequestHead();
            head.setLanguage(sendEmailRequest.languageType);
            searchModel.setRequestHead(head);

            result=flightRepository.getRescheduleAskInfo(searchModel);
            if(result == null||result.getOrderId() == null||result.getAskDetailList() == null||
                    result.getPassengerInfoList() == null||result.getPassengerInfoList().isEmpty()
                    ||result.getContactInfo() == null||result.getOriSegmentInfoList() == null||result.getOriSegmentInfoList().isEmpty()){
                LOGGER.info(String.format("获取改签咨询单信息失败：订单号%s,订单类型%s,咨询单申请编号%s",orderDetailModel.getOrderId(),searchModel.getFlightOrderClass(),reBookingApplicationId));
                throw new GetEmailDataException(GetEmailDataResultCode.LACK_RESCHEDULE_ASK_INFO,"缺少改签咨询单信息");
            }

            //改签联系人
//            if (sendEmailRequest.getRecipientEmailList() == null||sendEmailRequest.getRecipientEmailList().isEmpty()) {
//                List<String> recipientEmailList=new ArrayList<>();
//                if (result.getContactInfo()!= null){
//                    recipientEmailList.add(result.getContactInfo().getEmail());
//                    sendEmailRequest.setRecipientEmailList(recipientEmailList);
//                    LOGGER.info(String.format("改签咨询单接收人邮件%s",result.getContactInfo().getEmail()));
//                }
//            }

            //改签咨询单url
            String rescheduleFlightsUrl = getRescheduleFlightsUrl(orderDetailModel,reBookingApplicationId);
            result.setRescheduleFlightsUrl(rescheduleFlightsUrl);
        } catch (RepositoryException ex) {
            GetEmailDataException getEmailDataException = new GetEmailDataException(GetEmailDataResultCode.LACK_REBOOKING_INFO,"获取改签咨询单信息仓储异常");
            getEmailDataException.initCause(ex);
            throw getEmailDataException;

        }
        return result;
    }

    /**
     * 获取改签Url
     */
    private String getRescheduleFlightsUrl(OrderDetailModel orderDetailModel, String rebookId) {
        return BusinessConst.OFFLINERESCHEDULEURLPATTERN
                .replace("${protocol}","https")
                .replace("${domain}", BusinessHelper.getDomain(BusinessHelper.getSiteByServerFrom(orderDetailModel.getServerFrom()), Trademark.Trip))
                .replace("${orderid}", String.valueOf(orderDetailModel.getOrderId()))
                .replace("${rebookid}", rebookId)
                .replace("${accesstoken}", StringUtils.isBlank(orderDetailModel.getAccessToken()) ? "" : orderDetailModel.getAccessToken());
    }

    /**
     * 获取模板名称
     * */
    private String getTmplName(EmailTemplateType templateType, CorpGroup corpGroup){

        if (corpGroup == CorpGroup.SC){
            switch (templateType){
                case NewFlightConsultingVerified:
                    return QConfigConst.TEMPLATEFILENAME_EMAIL_NEWFLIGHTCONSULTINGVERIFIED_SC;
            }
        } else {
            switch (templateType){
                case NewFlightConsultingVerified:
                    return QConfigConst.TEMPLATEFILENAME_EMAIL_NEWFLIGHTCONSULTINGVERIFIED;
            }
        }

        return "";
    }
}
