package com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.getemaildataprocessor;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.business.common.IEmailCommonBusiness;
import com.ctrip.ibu.flight.internalws.business.common.IFlightCommonBusiness;
import com.ctrip.ibu.flight.internalws.common.businesshelper.BusinessHelper;
import com.ctrip.ibu.flight.internalws.common.cache.ICache;
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
import com.ctrip.ibu.flight.internalws.models.constant.CacheConst;
import com.ctrip.ibu.flight.internalws.models.constant.EmailConst;
import com.ctrip.ibu.flight.internalws.models.constant.QConfigConst;
import com.ctrip.ibu.flight.internalws.contract.commontypes.EmailTemplateType;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailRequestType;
import com.ctrip.ibu.flight.internalws.models.errorcode.emailsend.GetEmailDataResultCode;
import com.ctrip.ibu.flight.internalws.models.exception.common.TemplateRenderException;
import com.ctrip.ibu.flight.internalws.models.exception.emailsend.GetEmailDataException;
import com.ctrip.ibu.flight.internalws.models.exception.repository.RepositoryException;
import com.ctrip.ibu.flight.internalws.models.flight.InsuranceDetailModel;
import com.ctrip.ibu.flight.internalws.models.flight.InsuranceDetailSearchRequestModel;
import com.ctrip.ibu.flight.internalws.models.flight.OrderDetailModel;
import com.ctrip.ibu.flight.internalws.models.log.IndexedLogTag;
import com.ctrip.ibu.flight.internalws.models.log.LogLevel;
import com.ctrip.ibu.flight.internalws.models.message.*;
import com.ctrip.ibu.flight.internalws.repository.FlightRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取保险相关数据Processor
 * Create by kyxie on 2018/4/16 11:21
 */
@Component
@GetEmailDataProcessor(name = "获取保险相关邮件数据",processedEmailTypes = {EmailTemplateType.InsuranceChanged,EmailTemplateType.InsuranceRefunded,EmailTemplateType.InsuranceIssued})
public class GetInsuranceEmailDataProcessor extends GetEmailDataProcessBase {

    private final static ILog CLOG = LogManager.getLogger(GetInsuranceEmailDataProcessor.class);

    private FlightRepository flightRepository;

    private IFlightCommonBusiness flightCommonBusiness;

    private IEmailCommonBusiness emailCommonBusiness;

    private ITemplateEngine templateEngine;

    private ICache cache;

    public GetInsuranceEmailDataProcessor(FlightRepository flightRepository,
                                          IFlightCommonBusiness flightCommonBusiness,
                                          IEmailCommonBusiness emailCommonBusiness,
                                          ITemplateEngine templateEngine,
                                          ICache cache){
        this.flightRepository = flightRepository;
        this.flightCommonBusiness = flightCommonBusiness;
        this.emailCommonBusiness = emailCommonBusiness;
        this.templateEngine = templateEngine;
        this.cache = cache;
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

        Checker.checkWithThrowable((sendEmailRequest.emailTemplateType == EmailTemplateType.InsuranceChanged || sendEmailRequest.emailTemplateType == EmailTemplateType.InsuranceRefunded)
                && (sendEmailRequest.additionalIds == null || sendEmailRequest.additionalIds.isEmpty()),
                new GetEmailDataException(GetEmailDataResultCode.LACK_INSURANCE_NO,"出保退保邮件必须传递保单号"));

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

        SendEmailRequestType emailSendRequest = getEmailDataRequest.getSendEmailRequest();

        //改保，退保邮件邮件拦截没有保单号的保单
        if (emailSendRequest.emailTemplateType == EmailTemplateType.InsuranceChanged || emailSendRequest.emailTemplateType == EmailTemplateType.InsuranceRefunded){
            if (emailSendRequest.additionalIds == null || emailSendRequest.additionalIds.isEmpty()) {
                LoggerHelper.appendResponseContent("改保，退保邮件发送必须传递保单号");
                LoggerHelper.addIndexedLogTag(IndexedLogTag.Note,"改保，退保缺少保单号");
                throw new GetEmailDataException(GetEmailDataResultCode.LACK_INSURANCE_NO,"改保，退保邮件发送必须传递保单号");
            }
        }

        RequestHead requestHead = new RequestHead();
        requestHead.setLanguage(emailSendRequest.languageType);

        OrderDetailModel orderDetail = getEmailDataRequest.getOrderDetail();//可以使用已经设置的订单详情。因为邮件内容对订单详情不敏感
        if (orderDetail == null){
            orderDetail = this.flightCommonBusiness.getOrderDetail(emailSendRequest.orderID, requestHead,true);
        }

        if (orderDetail == null){
            throw new GetEmailDataException(GetEmailDataResultCode.ORDER_DETAIL_INVALID,String.format("获取%d订单详情失败",emailSendRequest.orderID));
        }

        getEmailDataRequest.setOrderDetail(flightCommonBusiness.getOrderDetail(emailSendRequest.orderID, requestHead,true));

        EmailData emailData = new EmailData();

        String bodyContent = getBodyContent(getEmailDataRequest);

        emailData.setBodyContent(bodyContent);
        emailData.setRecipientList(BusinessHelper.getContactEmailListFromOrder(orderDetail));

        response.setEmailData(emailData);

        EmailSendConfig emailSendConfig = new EmailSendConfig();
        emailSendConfig.setEmailSendLan(emailSendRequest.languageType);
        emailSendConfig.setOrderTrademark(orderDetail.getTrademark());
        response.setEmailConfig(emailSendConfig);

        return response;
    }


    /**
     * 获取邮件BodyContent
     * */
    private String getBodyContent(GetEmailDataRequest request) throws GetEmailDataException {

        SendEmailRequestType emailSendRequest = request.getSendEmailRequest();
        OrderDetailModel orderDetail = request.getOrderDetail();

        //保单数据
        Map<String, Object> templateData = new HashMap<>();
        templateData.put(EmailConst.TEMPLATEDATAKEY_ORDERDETAIL,orderDetail);

        //获取模板名称
        String emailTemplateName = getTmplName(emailSendRequest.emailTemplateType,orderDetail.getCorpGroup());


        List<InsuranceDetailModel> insuranceDetailList = getInsuranceList(emailSendRequest.orderID, CommonUtil.getValue(emailSendRequest.additionalIds, BusinessConst.SOAREQUESTKEY_INSURANCENO, true),true);
        Map<String,List<InsuranceDetailModel>> insTmplData = getInsuranceTmplData(insuranceDetailList);
        if (insTmplData != null && !insTmplData.isEmpty()){
            for (Map.Entry<String,List<InsuranceDetailModel>> insDetail : insTmplData.entrySet()){
                templateData.put(insDetail.getKey(),insDetail.getValue());
            }
        }

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

        //模板渲染
        if (templateEngine.hasTemplate(emailTemplateName)){
            throw new GetEmailDataException(GetEmailDataResultCode.TEMPLATE_RENDER_FAILED,String.format("模板%s不存在",emailTemplateName));
        }

        FreemarkerConfig config = new FreemarkerConfig();
        config.setFreemarkerLocale(LanguageUtil.mapLanguageTypeToLocale(emailSendRequest.languageType));

        //调用模板引擎渲染模板
        String bodyContent;
        try {
            bodyContent = templateEngine.renderTemplate(emailTemplateName, templateData, config);
        } catch (TemplateRenderException e) {
            GetEmailDataException getEmailDataException = new GetEmailDataException(GetEmailDataResultCode.TEMPLATE_RENDER_FAILED,String.format("渲染模板%s失败",emailTemplateName));
            getEmailDataException.initCause(e);
            throw getEmailDataException;
        }

        return bodyContent;
    }

    /**
     * 获取模板的保险数据
     * */
    private Map<String,List<InsuranceDetailModel>> getInsuranceTmplData(List<InsuranceDetailModel> insuranceDetailList){
        Map<String,List<InsuranceDetailModel>> insuranceData = new HashMap<>();
        if (insuranceDetailList != null && !insuranceDetailList.isEmpty()){
            List<InsuranceDetailModel> c2cInsList = new ArrayList<>();
            List<InsuranceDetailModel> sgxInsList = new ArrayList<>();
            List<InsuranceDetailModel> jwsgxInsList = new ArrayList<>();
            insuranceDetailList.forEach(insurance -> {
                if (BusinessConst.C2C_INSURANCE_IDENTIFIER.equalsIgnoreCase(insurance.getInsType())){
                    c2cInsList.add(insurance);
                }else if(BusinessConst.SGX_INSURANCE_IDENTIFIER.equalsIgnoreCase(insurance.getInsType())){
                    sgxInsList.add(insurance);
                }else if (BusinessConst.JWSGX_INSURANCE_IDENTIFIER.equalsIgnoreCase(insurance.getInsType())){
                    jwsgxInsList.add(insurance);
                }
            });
            if (!c2cInsList.isEmpty()){
                insuranceData.put(EmailConst.TEMPLATEDATAKEY_C2CINSURANCEINFO,c2cInsList);
            }
            if (!sgxInsList.isEmpty()){
                insuranceData.put(EmailConst.TEMPLATEDATAKEY_SGXINSURANCEINFO,sgxInsList);
            }
            if (!jwsgxInsList.isEmpty()){
                insuranceData.put(EmailConst.TEMPLATEDATAKEY_JWSGXINSURANCEINFO,jwsgxInsList);
            }
        }

        return insuranceData;
    }

    /**
     * 获取保险列表信息
     * @param orderId 订单号
     * @param insuranceNo 指定保单号
     * */
    private List<InsuranceDetailModel> getInsuranceList(Long orderId,String insuranceNo,boolean needCache) throws GetEmailDataException{
        List<InsuranceDetailModel> insuranceDetailList = new ArrayList<>();

        List<InsuranceDetailModel> origInsuranceDetailList = new ArrayList<>();

        //保险缓存key
        String cacheKey = String.format(CacheConst.INSURANCEDETAIL_CACHEKEY_PATTERN, orderId, insuranceNo == null ? "" : insuranceNo).toLowerCase();
        if (needCache){
            origInsuranceDetailList = (List<InsuranceDetailModel>) cache.get(cacheKey);
        }
        if (origInsuranceDetailList == null || origInsuranceDetailList.isEmpty()){
            try {
                InsuranceDetailSearchRequestModel searchRequest = new InsuranceDetailSearchRequestModel();
                searchRequest.setOrderId(orderId);
                searchRequest.setInsuranceNo(insuranceNo);

                origInsuranceDetailList = flightRepository.getInsuranceDetailList(searchRequest);

                if (origInsuranceDetailList != null && !origInsuranceDetailList.isEmpty()){
                    //将所有保险加入缓存
                    cache.put(cacheKey,origInsuranceDetailList);
                } else {
                    throw new GetEmailDataException(GetEmailDataResultCode.LACK_INSURANCE_INFO,String.format("当前订单%d不存在保险数据", orderId));
                }
            } catch (RepositoryException re){
                LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel, LogLevel.ERROR.toString());
                LoggerHelper.appendResponseContent(String.format("获取保单详情接口异常，异常类型：%s,错误描述：\n%s\n", re.getErrorSource(), ThrowableUtils.getExceptionDesc(re)));
            }
        }

        //获取有效的保险
        insuranceDetailList = getValidInsurance(origInsuranceDetailList);

        if (insuranceDetailList != null && !insuranceDetailList.isEmpty()){
            if (insuranceNo != null && !insuranceNo.isEmpty()){
                InsuranceDetailModel specifiedInsurance = getSpecifiedInsurance(insuranceDetailList,insuranceNo);
                //清空保险列表
                insuranceDetailList = new ArrayList<>();
                if (specifiedInsurance != null){
                    insuranceDetailList.add(specifiedInsurance);
                } else {
                    throw new GetEmailDataException(GetEmailDataResultCode.LACK_INSURANCE_INFO,String.format("当前订单%d不存在保单号为%s的保险数据", orderId, insuranceNo));
                }
            }
        } else {
            throw new GetEmailDataException(GetEmailDataResultCode.LACK_INTL_INSURANCE,String.format("当前订单%d不存在保单号为%s的国际旅行险", orderId, insuranceNo));
        }

        return insuranceDetailList;
    }

    /**
     * 获取指定保险
     * @param insuranceList 原始保险列表
     * @param insuranceNo 指定的保单号
     * */
    private InsuranceDetailModel getSpecifiedInsurance(List<InsuranceDetailModel> insuranceList, String insuranceNo){
        if (insuranceList == null || insuranceList.isEmpty() || insuranceNo == null || insuranceNo.isEmpty()){
            return null;
        }
        for(InsuranceDetailModel insurance : insuranceList){
            if (insuranceNo.equalsIgnoreCase(insurance.getInsuranceNo())){
                return insurance;
            }
        }
        return null;
    }

    /**
     * 过滤保险
     * */
    private List<InsuranceDetailModel> getValidInsurance(List<InsuranceDetailModel> origInsuranceList){
        if (origInsuranceList == null || origInsuranceList.isEmpty()){
            return origInsuranceList;
        }

        List<InsuranceDetailModel> invalidInsuranceList = new ArrayList<>();
        //过滤非航意险，大陆旅行险，境外旅行险的保险
        origInsuranceList.forEach(origInsurance ->{
            if (!BusinessConst.JWSGX_INSURANCE_IDENTIFIER.equalsIgnoreCase(origInsurance.getInsType())
                    && !BusinessConst.C2C_INSURANCE_IDENTIFIER.equalsIgnoreCase(origInsurance.getInsType())
                    && !BusinessConst.SGX_INSURANCE_IDENTIFIER.equalsIgnoreCase(origInsurance.getInsType())){
                invalidInsuranceList.add(origInsurance);
            }
        });
        origInsuranceList.removeAll(invalidInsuranceList);

        return origInsuranceList;
    }

    /**
     * 获取主模板名
     * @param corpGroup 企业所属组
     * */
    private String getTmplName(EmailTemplateType emailTemplateType, CorpGroup corpGroup){
        if (corpGroup == CorpGroup.SC){
            switch (emailTemplateType){
                case InsuranceChanged:
                    return QConfigConst.TEMPLATEFILENAME_INSURANCECHANGEDEMAIL_SC;
                case InsuranceRefunded:
                    return QConfigConst.TEMPLATEFILENAME_INSURANCEREFUNDEDEMAIL_SC;
                case InsuranceIssued:
                    return QConfigConst.TEMPLATEFILENAME_INSURANCEISSUEDEMAIL_SC;
            }
        } else {
            switch (emailTemplateType){
                case InsuranceChanged:
                    return QConfigConst.TEMPLATEFILENAME_INSURANCECHANGEDEMAIL;
                case InsuranceRefunded:
                    return QConfigConst.TEMPLATEFILENAME_INSURANCEREFUNDEDEMAIL;
                case InsuranceIssued:
                    return QConfigConst.TEMPLATEFILENAME_INSURANCEISSUEDEMAIL;
            }
        }

        return "";
    }
}
