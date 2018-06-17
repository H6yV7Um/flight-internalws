package com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.getemaildataprocessor;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.business.common.IEmailCommonBusiness;
import com.ctrip.ibu.flight.internalws.business.common.IFlightCommonBusiness;
import com.ctrip.ibu.flight.internalws.common.businesshelper.BusinessHelper;
import com.ctrip.ibu.flight.internalws.common.components.Checker;
import com.ctrip.ibu.flight.internalws.common.i18n.IMessageResource;
import com.ctrip.ibu.flight.internalws.common.log.LoggerHelper;
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
import com.ctrip.ibu.flight.internalws.models.flight.SegmentValueAddedBaggage;
import com.ctrip.ibu.flight.internalws.models.flight.ValueAddedBaggageInfo;
import com.ctrip.ibu.flight.internalws.models.message.*;
import com.ctrip.ibu.flight.internalws.models.flight.offline.OfflineValueAddedModel;
import com.ctrip.ibu.flight.internalws.models.flight.offline.SearchOfflineValueAddedServiceRequest;
import com.ctrip.ibu.flight.internalws.models.flight.offline.SearchOfflineValueAddedServiceResponse;
import com.ctrip.ibu.flight.internalws.repository.IFlightRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.*;

/**
 * 获取Offline X产品邮件数据处理类
 * Create by kyxie on 2018/4/16 20:28
 */
@Component
@GetEmailDataProcessor(name = "获取确认邮件数据处理类",processedEmailTypes = {EmailTemplateType.XProductCanceled,EmailTemplateType.XProductIssued,EmailTemplateType.XProductSubmitted,EmailTemplateType.XProductPaymentSuccess})
public class GetOfflineXProductEmailDataProcessor extends GetEmailDataProcessBase {

    private final static ILog CLOG = LogManager.getLogger(GetOfflineXProductEmailDataProcessor.class);

    private final static boolean NEED_CACHE_ORDER_DETAIL = true;

    private final static int BAGGAGE_SERVICETYPE = 1;

    private final static int MEALS_SERVICETYPE = 2;

    private final static int NAMEMODIFY_SERVICETYPE = 3;

    private final static int PAPERMODIFY_SERVICETYPE = 4;

    private final static int CHECKSEAT_SERVICETYPE = 5;

    private final static int SPECIALREQUEST_SERVICETYPE = 6;

    private final static int SUPPLE_BAGGAGE_STATUS_1 = 2;

    private final static int SUPPLE_BAGGAGE_STATUS_2 = 5;

    private IFlightRepository flightRepository;

    private IFlightCommonBusiness flightCommonBusiness;

    private IEmailCommonBusiness emailCommonBusiness;

    private IMessageResource messageResource;

    private ITemplateEngine templateEngine;

    @Inject
    public GetOfflineXProductEmailDataProcessor(IFlightRepository flightRepository,
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

        Checker.checkWithThrowable(sendEmailRequest.additionalIds == null || sendEmailRequest.additionalIds.isEmpty(),new GetEmailDataException(GetEmailDataResultCode.LACK_XPRODUCT_ORDERID,"additionalIds字段X产品订单号"));
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

        EmailData emailData;

        SendEmailRequestType emailSendRequest = getEmailDataRequest.getSendEmailRequest();

        RequestHead requestHead = new RequestHead();
        requestHead.setLanguage(emailSendRequest.languageType);

        OrderDetailModel orderDetail = flightCommonBusiness.getOrderDetail(emailSendRequest.orderID, requestHead,NEED_CACHE_ORDER_DETAIL);

        if (orderDetail == null){
            throw new GetEmailDataException(GetEmailDataResultCode.ORDER_DETAIL_INVALID,"获取订单详情失败");
        }

        getEmailDataRequest.setOrderDetail(orderDetail);


        if (isAdditionalBaggage(getEmailDataRequest)){

            LoggerHelper.appendResponseContent(String.format("请求为补订行李额请求，ProductType = %d",BusinessConst.OfflineXProductConstant.SERVICETYPECODE_XPRODUCTEXTERNALBAGGAGE));

            //补订行李额
            emailData = getXProductBaggageEmailDataModel(getEmailDataRequest);
        } else {

            LoggerHelper.appendResponseContent("请求为Offline X产品补订请求，不包含ProductType传值");

            //欧洲呼叫中心Offline X产品
            emailData = getOfflineXProductEmailDataModel(getEmailDataRequest);
        }

        emailData.setRecipientList(BusinessHelper.getContactEmailListFromOrder(orderDetail));

        response.setEmailData(emailData);

        EmailSendConfig emailSendConfig = new EmailSendConfig();
        emailSendConfig.setEmailSendLan(emailSendRequest.languageType);
        emailSendConfig.setOrderTrademark(orderDetail.getTrademark());
        response.setEmailConfig(emailSendConfig);

        return response;
    }

    /**
     * 是否是补订行李额请求
     * */
    private boolean isAdditionalBaggage(GetEmailDataRequest getEmailDataRequest){
        return getEmailDataRequest.getSendEmailRequest() != null
                && getEmailDataRequest.getSendEmailRequest().getAdditionalIds() != null
                && getEmailDataRequest.getSendEmailRequest().getAdditionalIds().containsKey(BusinessConst.XPRODUCTTYPE)
                && getEmailDataRequest.getSendEmailRequest().getAdditionalIds().get(BusinessConst.XPRODUCTTYPE)
                .equalsIgnoreCase(BusinessConst.OfflineXProductConstant.SERVICETYPECODE_XPRODUCTEXTERNALBAGGAGE.toString());
    }

    /**
     * 获取补订行李额数据
     * */
    private EmailData getXProductBaggageEmailDataModel(GetEmailDataRequest getEmailDataRequest) throws GetEmailDataException {

        String bodyContent = getXProductBaggageBodyContent(getEmailDataRequest);

        EmailData emailData = new EmailData();
        emailData.setBodyContent(bodyContent);

        return emailData;
    }

    /**
     * 获取Offline X产品信息
     * */
    private EmailData getOfflineXProductEmailDataModel(GetEmailDataRequest getEmailDataRequest) throws GetEmailDataException {
        EmailData emailData = new EmailData();

        String bodyContent = getBodyContent(getEmailDataRequest);
        emailData.setBodyContent(bodyContent);

        return emailData;
    }

    /**
     * 获取补订行李额信息
     * */
    private String getXProductBaggageBodyContent(GetEmailDataRequest request) throws GetEmailDataException{
        SendEmailRequestType emailSendRequest = request.getSendEmailRequest();
        OrderDetailModel orderDetail = request.getOrderDetail();

        //模板数据
        Map<String,Object> templateData = new HashMap<>();
        templateData.put(EmailConst.TEMPLATEDATAKEY_ORDERDETAIL,orderDetail);

        ValueAddedBaggageInfo valueAddedBaggageInfo = orderDetail.getxProduct().getPassengerValueAddedBaggage();
        if (valueAddedBaggageInfo != null) {
            long productorderId = Long.parseLong(CommonUtil.getValue(emailSendRequest.getAdditionalIds(), BusinessConst.SOAREQUESTKEY_PRODUCTORDERID, true));
            for (SegmentValueAddedBaggage segmentValueAddedBaggage : valueAddedBaggageInfo.getSegmentValueAddedBaggageList()) {
                if (segmentValueAddedBaggage.getProductOrderID() == productorderId) {
                    //补订行李额
                    templateData.put(EmailConst.TEMPLATEDATAKEY_XPRODUCTBAGGAGE, segmentValueAddedBaggage);
                    break;
                }
            }
        }

        //获取邮件模板子类型
        String emailTemplateName = getEmailTemplateName(orderDetail,emailSendRequest);

        //邮件头尾
        EmailHeaderAndFooterResponse headerAndFooter = emailCommonBusiness.getEmailHeaderAndFooter(orderDetail,emailSendRequest.emailTemplateType,emailSendRequest.languageType);
        String header = "";
        String footer = "";
        if (headerAndFooter != null){
            header = headerAndFooter.getEmailHeader() == null ? "" : headerAndFooter.getEmailHeader();
            footer = headerAndFooter.getEmailFooter() == null ? "" : headerAndFooter.getEmailFooter();
        }
        templateData.put(EmailConst.TEMPLATEDATAKEY_COMMONHEADER,header);
        templateData.put(EmailConst.TEMPLATEDATAKEY_COMMONFOOTER,footer);

        FreemarkerConfig freemarkerConfig = new FreemarkerConfig();
        freemarkerConfig.setFreemarkerLocale(LanguageUtil.mapLanguageTypeToLocale(emailSendRequest.languageType));

        try {
            return templateEngine.renderTemplate(emailTemplateName,templateData,freemarkerConfig);
        } catch (TemplateRenderException e) {
            GetEmailDataException getEmailDataException = new GetEmailDataException(GetEmailDataResultCode.TEMPLATE_RENDER_FAILED, String.format("渲染模板%s异常", emailTemplateName));
            getEmailDataException.initCause(e);
            throw getEmailDataException;
        }
    }

    private String getEmailTemplateName(OrderDetailModel orderDetail, SendEmailRequestType emailSendRequest) throws GetEmailDataException {

        String emailTemplateFileName = "";

        ValueAddedBaggageInfo valueAddedBaggageInfo = orderDetail.getxProduct().getPassengerValueAddedBaggage();
        if (valueAddedBaggageInfo != null) {

            long productorderId = Long.parseLong(CommonUtil.getValue(emailSendRequest.getAdditionalIds(), BusinessConst.SOAREQUESTKEY_PRODUCTORDERID, true));
            for (SegmentValueAddedBaggage segmentValueAddedBaggage : valueAddedBaggageInfo.getSegmentValueAddedBaggageList()) {
                if (segmentValueAddedBaggage.getProductOrderID() == productorderId) {
                    if (segmentValueAddedBaggage.getBaggageStatus() == SUPPLE_BAGGAGE_STATUS_1){
                        emailTemplateFileName = QConfigConst.TEMPLATEFILENAME_XPRODUCTBAGGAGESUCCESS;
                    } else if(segmentValueAddedBaggage.getBaggageStatus() == SUPPLE_BAGGAGE_STATUS_2) {
                        emailTemplateFileName = QConfigConst.TEMPLATEFILENAME_XPRODUCTBAGGAGEFAILED;
                    }
                    break;
                }
            }
        }

        if(emailTemplateFileName.isEmpty()){
            LoggerHelper.appendResponseContent("补订行李额状态不是2或者5,不发送邮件");
            throw new GetEmailDataException(GetEmailDataResultCode.SUPPLE_BAGGAGE_STATUS_ILLEGAL,"补订行李额状态不是2或者5,不发送邮件");
        }

        return emailTemplateFileName;
    }


    /**
     * 获取Offline X产品邮件内容
     * */
    private String getBodyContent(GetEmailDataRequest request) throws GetEmailDataException {

        SendEmailRequestType emailSendRequest = request.getSendEmailRequest();
        OrderDetailModel orderDetail = request.getOrderDetail();

        //模板数据
        Map<String,Object> templateData = new HashMap<>();
        templateData.put(EmailConst.TEMPLATEDATAKEY_ORDERDETAIL,orderDetail);

        OfflineValueAddedModel offlineValueAddedInfo = getOfflineValueAddedServiceInfo(orderDetail, CommonUtil.getValue(emailSendRequest.additionalIds, BusinessConst.SOAREQUESTKEY_PRODUCTORDERID, true),
                CommonUtil.getValue(emailSendRequest.additionalIds, BusinessConst.SOAREQUESTKEY_EXTERNALNO, true),
                emailSendRequest.emailTemplateType,emailSendRequest.languageType);

        templateData.put(EmailConst.TEMPLATEDATAKEY_OFFLINEVALUEADDEDINFO,offlineValueAddedInfo);

        String emailTemplateName = getOfflineXProductTmplName(offlineValueAddedInfo.getServiceType(),orderDetail.getCorpGroup());

        //邮件头尾
        EmailHeaderAndFooterResponse headerAndFooter = emailCommonBusiness.getEmailHeaderAndFooter(orderDetail,emailSendRequest.emailTemplateType,emailSendRequest.languageType);
        String header = "";
        String footer = "";
        if (headerAndFooter != null){
            header = headerAndFooter.getEmailHeader() == null ? "" : headerAndFooter.getEmailHeader();
            footer = headerAndFooter.getEmailFooter() == null ? "" : headerAndFooter.getEmailFooter();
        }
        templateData.put(EmailConst.TEMPLATEDATAKEY_COMMONHEADER,header);
        templateData.put(EmailConst.TEMPLATEDATAKEY_COMMONFOOTER,footer);

        FreemarkerConfig config = new FreemarkerConfig();
        config.setFreemarkerLocale(LanguageUtil.mapLanguageTypeToLocale(emailSendRequest.languageType));

        try {
            return templateEngine.renderTemplate(emailTemplateName,templateData,config);
        } catch (TemplateRenderException e) {
            GetEmailDataException getEmailDataException = new GetEmailDataException(GetEmailDataResultCode.TEMPLATE_RENDER_FAILED,String.format("渲染模板%s异常",emailTemplateName));
            getEmailDataException.initCause(e);
            throw getEmailDataException;
        }
    }

    /**
     * 获取X产品信息
     * */
    private OfflineValueAddedModel getOfflineValueAddedServiceInfo(OrderDetailModel orderDetail, String productOrderId, String externalNo, EmailTemplateType emailTemplate,LanguageType sendLan)
            throws GetEmailDataException{
        OfflineValueAddedModel offlineValueAddedInfo;

        try {
            SearchOfflineValueAddedServiceRequest searchReq = new SearchOfflineValueAddedServiceRequest();
            RequestHead requestHead = new RequestHead();
            requestHead.setUid(orderDetail.getUid());
            searchReq.setRequestHead(requestHead);
            searchReq.setOrderId(orderDetail.getOrderId());
            if (productOrderId != null && !productOrderId.isEmpty()){
                searchReq.setProductOrderId(Long.parseLong(productOrderId));
            }
            searchReq.setExternalNo(externalNo);

            //调用仓储层
            SearchOfflineValueAddedServiceResponse searchRes = flightRepository.searchValueAddedService(searchReq);
            if (searchRes != null && searchRes.getValueAddedService() != null) {
                offlineValueAddedInfo = searchRes.getValueAddedService();

                //验证X产品信息
                verifyXProductInfo(offlineValueAddedInfo,emailTemplate);

                processOfflineXProductInfo(offlineValueAddedInfo, emailTemplate,sendLan);
                offlineValueAddedInfo.setPayUrl(getOfflineXProductPayUrl(orderDetail, offlineValueAddedInfo));
            } else {
                throw new GetEmailDataException(GetEmailDataResultCode.LACK_XPRODUCT_INFO,"X产品查询接口返回无结果");
            }
        } catch (NumberFormatException e) {
            GetEmailDataException getEmailDataException = new GetEmailDataException(GetEmailDataResultCode.LACK_XPRODUCT_ORDERID,String.format("X产品订单号%s不合法",productOrderId));
            getEmailDataException.initCause(e);
            throw getEmailDataException;
        } catch (RepositoryException e) {
            GetEmailDataException getEmailDataException = new GetEmailDataException(GetEmailDataResultCode.LACK_XPRODUCT_INFO,"查询X产品仓储层异常");
            getEmailDataException.initCause(e);
            throw getEmailDataException;
        }

        return offlineValueAddedInfo;
    }

    /**
     * 验证Offline增值服务有效性
     * @param serviceInfo X产品详情
     * @param emailTemplateType 邮件模板类型
     */
    private void verifyXProductInfo(OfflineValueAddedModel serviceInfo, EmailTemplateType emailTemplateType) throws GetEmailDataException {

        Checker.checkWithThrowable(serviceInfo == null || serviceInfo.getServiceType() == null,new GetEmailDataException(GetEmailDataResultCode.LACK_XPRODUCT_INFO,"缺少X产品信息"));

        switch (serviceInfo.getServiceType()) {
            case BAGGAGE_SERVICETYPE:
                Checker.checkWithThrowable(serviceInfo.getBaggageInfo() == null,new GetEmailDataException(GetEmailDataResultCode.LACK_XPRODUCT_BAGGAGE_INFO,"缺少X产品行李额信息"));
                break;
            case MEALS_SERVICETYPE:
                Checker.checkWithThrowable(serviceInfo.getMealsInfo() == null,new GetEmailDataException(GetEmailDataResultCode.LACK_XPRODUCT_MEALS_INFO,"缺少X产品餐食信息"));
                break;
            case NAMEMODIFY_SERVICETYPE:
                Checker.checkWithThrowable(serviceInfo.getNameModifyInfoList() == null,new GetEmailDataException(GetEmailDataResultCode.LACK_XPRODUCT_NAMEMODIFY_INFO,"缺少X产品姓名修改信息"));
                break;
            case PAPERMODIFY_SERVICETYPE:
                Checker.checkWithThrowable(serviceInfo.getPaperModifyInfoList() == null,new GetEmailDataException(GetEmailDataResultCode.LACK_XPRODUCT_PAPERMODIFY_INFO,"缺少X产品证件修改信息"));
                break;
            case CHECKSEAT_SERVICETYPE:
                Checker.checkWithThrowable(serviceInfo.getCheckSeatInfoList() == null,new GetEmailDataException(GetEmailDataResultCode.LACK_XPRODUCT_CHECKSEAT_INFO,"缺少X产品值机选座信息"));
                break;
            case SPECIALREQUEST_SERVICETYPE:
                Checker.checkWithThrowable(serviceInfo.getSpecialServiceInfo() == null,new GetEmailDataException(GetEmailDataResultCode.LACK_XPRODUCT_SPECIALREQUEST_INFO,"缺少X产品特殊需求信息"));
                break;
            default:
                throw new GetEmailDataException(GetEmailDataResultCode.LACK_XPRODUCT_INFO,String.format("当前ServiceType:%d不存在对应的X产品",serviceInfo.getServiceType()));
        }

        //X产品待支付，金额为0时返回失败
        if (emailTemplateType == EmailTemplateType.XProductSubmitted){
            Checker.checkWithThrowable(serviceInfo.getSalePrice() == null || serviceInfo.getSalePrice().compareTo(new BigDecimal("0")) <= 0,
                    new GetEmailDataException(GetEmailDataResultCode.FREE_PAY_FEE,"免费改签不需要支付费用"));
        }
    }

    /**
     * 处理X产品信息（赋值多语言，修改增值服务状态等）
     * @param serviceInfo 增值服务
     * @param emailTemplateType 模板类型
     */
    private void processOfflineXProductInfo(OfflineValueAddedModel serviceInfo, EmailTemplateType emailTemplateType, LanguageType sendLan) {

        if (serviceInfo != null) {

            Locale emailSendLanLocale = LanguageUtil.mapLanguageTypeToLocale(sendLan);

            switch (serviceInfo.getServiceType()) {
                case BAGGAGE_SERVICETYPE://行李购买
                    serviceInfo.setServiceTypeName(messageResource.getResource(MessageResourceConst.MRKEY_OFFLINEVALUEADDED_BAGGAGE,emailSendLanLocale));
                    break;
                case MEALS_SERVICETYPE://餐食购买
                    serviceInfo.setServiceTypeName(messageResource.getResource(MessageResourceConst.MRKEY_OFFLINEVALUEADDED_MEALS,emailSendLanLocale));
                    serviceInfo.getMealsInfo().setMealType(getMealTypeName(serviceInfo.getMealsInfo().getSpecification(),sendLan));
                    break;
                case NAMEMODIFY_SERVICETYPE://姓名修改
                    serviceInfo.setServiceTypeName(messageResource.getResource(MessageResourceConst.MRKEY_OFFLINEVALUEADDED_NAMEMODIFY,emailSendLanLocale));
                    break;
                case PAPERMODIFY_SERVICETYPE://证件修改
                    serviceInfo.setServiceTypeName(messageResource.getResource(MessageResourceConst.MRKEY_OFFLINEVALUEADDED_PAPERMODIFY,emailSendLanLocale));
                    serviceInfo.getPaperModifyInfoList().forEach(item -> {
                        item.setOriginalPaperTypeName(getIdTypeName(item.getOriginalPaperType(),sendLan));
                        item.setModifiedPaperTypeName(getIdTypeName(item.getModifiedPaperType(),sendLan));
                    });
                    break;
                case CHECKSEAT_SERVICETYPE://值机选座
                    serviceInfo.setServiceTypeName(messageResource.getResource(MessageResourceConst.MRKEY_OFFLINEVALUEADDED_CHECKSEAT,emailSendLanLocale));
                    serviceInfo.getCheckSeatInfoList().forEach(item -> {
                        List<String> optionNameList = new ArrayList<>();
                        item.getPositionOptionList().forEach(option ->{
                            switch (option){
                                case 1:
                                    optionNameList.add(messageResource.getResource(MessageResourceConst.MRKEY_BESIDEWIDOW,emailSendLanLocale));
                                    break;
                                case 2:
                                    optionNameList.add(messageResource.getResource(MessageResourceConst.MRKEY_BESIDECORRIDOR,emailSendLanLocale));
                                    break;
                                case 3:
                                    optionNameList.add(messageResource.getResource(MessageResourceConst.MRKEY_FRONT,emailSendLanLocale));
                                    break;
                                case 4:
                                    optionNameList.add(messageResource.getResource(MessageResourceConst.MRKEY_MIDDLE,emailSendLanLocale));
                                    break;
                                case 5:
                                    optionNameList.add(messageResource.getResource(MessageResourceConst.MRKEY_LEANBACK,emailSendLanLocale));
                                    break;
                                default:
                                    break;
                            }
                        });
                        item.setPositionOptionNameList(optionNameList);
                    });
                    break;
                case SPECIALREQUEST_SERVICETYPE://特殊需求
                    serviceInfo.setServiceTypeName(messageResource.getResource(MessageResourceConst.MRKEY_OFFLINEVALUEADDED_SPECIALSERVICE,emailSendLanLocale));
                    serviceInfo.getSpecialServiceInfo().setServiceName(getSpecialRequestName(serviceInfo.getSpecialServiceInfo().getServiceInfo(),sendLan));
                    break;
                default:
                    break;
            }

            //修改X产品状态，以推送的模板类型为准
            switch (emailTemplateType) {
                case XProductSubmitted:
                    serviceInfo.setStatusType(BusinessConst.OfflineXProductConstant.STATUSTYPECODE_SUBMITTED);
                    serviceInfo.setStatusDesc(messageResource.getResource(MessageResourceConst.MRKEY_OPTIONSERVICETOBEPAID,emailSendLanLocale));
                    serviceInfo.setRemind(messageResource.getResource(MessageResourceConst.MRKEY_XPRODUCTSUBMITTED_REMIND,emailSendLanLocale));
                    break;
                case XProductPaymentSuccess:
                    serviceInfo.setStatusType(BusinessConst.OfflineXProductConstant.STATUSTYPECODE_PAYMENTSUCCESS);
                    serviceInfo.setStatusDesc(messageResource.getResource(MessageResourceConst.MRKEY_OPTIONSERVICEPAYMENTSUCCESS,emailSendLanLocale));
                    serviceInfo.setRemind(messageResource.getResource(MessageResourceConst.MRKEY_XPRODUCTPAYMENTSUCCESS_REMIND,emailSendLanLocale));
                    break;
                case XProductIssued:
                    serviceInfo.setStatusType(BusinessConst.OfflineXProductConstant.STATUSTYPECODE_ISSUED);
                    serviceInfo.setStatusDesc(messageResource.getResource(MessageResourceConst.MRKEY_OPTIONSERVICEISSUED,emailSendLanLocale));
                    serviceInfo.setRemind(messageResource.getResource(MessageResourceConst.MRKEY_XPRODUCTISSUED_REMIND,emailSendLanLocale));
                    break;
                case XProductCanceled:
                    serviceInfo.setStatusType(BusinessConst.OfflineXProductConstant.STATUSTYPECODE_CANCELED);
                    serviceInfo.setStatusDesc(messageResource.getResource(MessageResourceConst.MRKEY_OPTIONSERVICEFAILED,emailSendLanLocale));
                    serviceInfo.setRemind(messageResource.getResource(MessageResourceConst.MRKEY_XPRODUCTCANCELED_REMIND,emailSendLanLocale));
                    break;
                default:
                    serviceInfo.setStatusType(BusinessConst.OfflineXProductConstant.STATUSTYPECODE_UNKNOWN);
                    break;
            }
        }
    }

    /**
     * 获取特殊服务名
     * */
    private String getSpecialRequestName(Short serviceType,LanguageType sendLan){

        Locale sendLanLocale = LanguageUtil.mapLanguageTypeToLocale(sendLan);

        switch (serviceType){
            case 1:
                return messageResource.getResource(MessageResourceConst.MRKEY_AEROBICCABIN,sendLanLocale);
            case 2:
                return messageResource.getResource(MessageResourceConst.MRKEY_BABYCRADLE,sendLanLocale);
            case 3:
                return messageResource.getResource(MessageResourceConst.MRKEY_WHEELCHAIR,sendLanLocale);
            default:
                return "";
        }
    }

    /**
     * 获取餐食多语言
     * */
    private String getMealTypeName(Short mealType,LanguageType sendLan){

        Locale sendLanLocale = LanguageUtil.mapLanguageTypeToLocale(sendLan);

        switch (mealType){
            case 1:
                return messageResource.getResource(MessageResourceConst.MRKEY_MEALTYPE_GASTRICULCER,sendLanLocale);
            case 2:
                return messageResource.getResource(MessageResourceConst.MEKEY_MEALTYPE_FRUITRAWVEGETARIAN,sendLanLocale);
            case 3:
                return messageResource.getResource(MessageResourceConst.MRKEY_MEALTYPE_INDIA,sendLanLocale);
            case 4:
                return messageResource.getResource(MessageResourceConst.MRKEY_MEALTYPE_NOCEREALS,sendLanLocale);
            case 5:
                return messageResource.getResource(MessageResourceConst.MRKEY_MEALTYPE_JEWISH,sendLanLocale);
            case 6:
                return messageResource.getResource(MessageResourceConst.MRKEY_MEALTYPE_LOWSALT,sendLanLocale);
            case 7:
                return messageResource.getResource(MessageResourceConst.MRKEY_MEALTYPE_HIGHFIBER,sendLanLocale);
            case 8:
                return messageResource.getResource(MessageResourceConst.MRKEY_MEALTYPE_LOWCHOLESTEROL,sendLanLocale);
            case 9:
                return messageResource.getResource(MessageResourceConst.MRKEY_MEALTYPE_BABY,sendLanLocale);
            case 10:
                return messageResource.getResource(MessageResourceConst.MRKEY_MEALTYPE_MUSLIM,sendLanLocale);
            case 11:
                return messageResource.getResource(MessageResourceConst.MRKEY_MEALTYPE_VEGETARIAN,sendLanLocale);
            case 12:
                return messageResource.getResource(MessageResourceConst.MRKEY_MEALTYPE_LOWPROTEIN,sendLanLocale);
            case 13:
                return messageResource.getResource(MessageResourceConst.MRKEY_MEALTYPE_NOLACTOSE,sendLanLocale);
            case 14:
                return messageResource.getResource(MessageResourceConst.MRKEY_MEALTYPE_ASISVEGETARIAN,sendLanLocale);
            case 15:
                return messageResource.getResource(MessageResourceConst.MRKEY_MEALTYPE_CHILD,sendLanLocale);
            case 16:
                return messageResource.getResource(MessageResourceConst.MRKEY_MEALTYPE_FRESHFRUIT,sendLanLocale);
            case 17:
                return messageResource.getResource(MessageResourceConst.MRKEY_MEALTYPE_LOWENERGY,sendLanLocale);
            case 18:
                return messageResource.getResource(MessageResourceConst.MRKEY_MEALTYPE_WESTERNVEGETARIAN,sendLanLocale);
            case 19:
                return messageResource.getResource(MessageResourceConst.MRKEY_MEALTYPE_SEAFOOD,sendLanLocale);
            case 20:
                return messageResource.getResource(MessageResourceConst.MRKEY_MEALTYPE_DIABETES,sendLanLocale);
            case 21:
                return messageResource.getResource(MessageResourceConst.MRKEY_MEALTYPE_INDIAVEGETERIAN,sendLanLocale);
            default:
                return "";
        }
    }

    /**
     * 获取证件类型多语言
     * */
    private String getIdTypeName(Short idType,LanguageType sendLan){

        Locale sendLanLocale = LanguageUtil.mapLanguageTypeToLocale(sendLan);

        switch (idType){
            case 1:
                return messageResource.getResource(MessageResourceConst.MRKEY_IDCARD,sendLanLocale);
            case 2:
                return messageResource.getResource(MessageResourceConst.MRKEY_PASSPORT,sendLanLocale);
            case 4:
                return messageResource.getResource(MessageResourceConst.MRKEY_MILITARYLICENSE,sendLanLocale);
            case 7:
                return messageResource.getResource(MessageResourceConst.MRKEY_HOMETOWNCARD,sendLanLocale);
            case 8:
                return messageResource.getResource(MessageResourceConst.MRKEY_MTPS,sendLanLocale);
            case 10:
                return messageResource.getResource(MessageResourceConst.MRKEY_HKMACAOPASS,sendLanLocale);
            case 22:
                return messageResource.getResource(MessageResourceConst.MRKEY_TAIWANPASS,sendLanLocale);
            case 25:
                return messageResource.getResource(MessageResourceConst.MRKEY_ACCOUNTBOOK,sendLanLocale);
            case 27:
                return messageResource.getResource(MessageResourceConst.MRKEY_BIRTHCERTIFICATE,sendLanLocale);
            case 28:
                return messageResource.getResource(MessageResourceConst.MRKEY_PERMANENTRESIDENCEIDCARD,sendLanLocale);
            default:
                return "";
        }
    }

    /**
     * 获取X产品支付URL
     */
    private String getOfflineXProductPayUrl(OrderDetailModel orderDetail, OfflineValueAddedModel offlineValueAddedInfo) {
        return BusinessConst.OFFLINEXPRODUCTURLPATTERN
                .replace("${protocol}","https")
                .replace("${domain}", BusinessHelper.getDomain(BusinessHelper.getSiteByServerFrom(orderDetail.getServerFrom()), Trademark.Trip))
                .replace("${orderid}", String.valueOf(orderDetail.getOrderId()))
                .replace("${xproductorderid}", String.valueOf(offlineValueAddedInfo.getProductOrderId()))
                .replace("${accesstoken}", StringUtils.isBlank(orderDetail.getAccessToken()) ? "" : orderDetail.getAccessToken());
    }

    /**
     * 获取Offline X产品模板名称
     * @param xProductType X产品类型
     * @param corpGroup 企业组
     * */
    private String getOfflineXProductTmplName(int xProductType, CorpGroup corpGroup){
        if (corpGroup == CorpGroup.SC){
            switch (xProductType){
                case BAGGAGE_SERVICETYPE:
                    return QConfigConst.TEMPLATEFILENAME_XPRODUCTBAGGAGE_SC;
                case MEALS_SERVICETYPE:
                    return QConfigConst.TEMPLATEFILENAME_XPRODUCTMEALS_SC;
                case NAMEMODIFY_SERVICETYPE:
                    return QConfigConst.TEMPLATEFILENAME_XPRODUCTNAMEMODIFY_SC;
                case PAPERMODIFY_SERVICETYPE:
                    return QConfigConst.TEMPLATEFILENAME_XPRODUCTPAPERMODIFY_SC;
                case CHECKSEAT_SERVICETYPE:
                    return QConfigConst.TEMPLATEFILENAME_XPRODUCTCHECKSEAT_SC;
                case SPECIALREQUEST_SERVICETYPE:
                    return QConfigConst.TEMPLATEFILENAME_XPRODUCTSPECIALREQUEST_SC;
                default:
                    return "";
            }
        } else {
            switch (xProductType){
                case BAGGAGE_SERVICETYPE:
                    return QConfigConst.TEMPLATEFILENAME_XPRODUCTBAGGAGE;
                case MEALS_SERVICETYPE:
                    return QConfigConst.TEMPLATEFILENAME_XPRODUCTMEALS;
                case NAMEMODIFY_SERVICETYPE:
                    return QConfigConst.TEMPLATEFILENAME_XPRODUCTNAMEMODIFY;
                case PAPERMODIFY_SERVICETYPE:
                    return QConfigConst.TEMPLATEFILENAME_XPRODUCTPAPERMODIFY;
                case CHECKSEAT_SERVICETYPE:
                    return QConfigConst.TEMPLATEFILENAME_XPRODUCTCHECKSEAT;
                case SPECIALREQUEST_SERVICETYPE:
                    return QConfigConst.TEMPLATEFILENAME_XPRODUCTSPECIALREQUEST;
                default:
                    return "";
            }
        }
    }
}
