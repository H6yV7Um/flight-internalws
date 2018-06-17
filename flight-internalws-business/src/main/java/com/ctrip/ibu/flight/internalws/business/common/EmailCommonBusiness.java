package com.ctrip.ibu.flight.internalws.business.common;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.common.businesshelper.BusinessHelper;
import com.ctrip.ibu.flight.internalws.common.businesshelper.SensitiveInfoOperateHelper;
import com.ctrip.ibu.flight.internalws.common.config.Config;
import com.ctrip.ibu.flight.internalws.common.log.LoggerHelper;
import com.ctrip.ibu.flight.internalws.common.utils.LanguageUtil;
import com.ctrip.ibu.flight.internalws.common.utils.SerializeUtil;
import com.ctrip.ibu.flight.internalws.common.utils.ThrowableUtils;
import com.ctrip.ibu.flight.internalws.contract.commontypes.EmailTemplateType;
import com.ctrip.ibu.flight.internalws.contract.commontypes.LanguageType;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailRequestType;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailResponseType;
import com.ctrip.ibu.flight.internalws.models.common.CorpGroup;
import com.ctrip.ibu.flight.internalws.models.common.Market;
import com.ctrip.ibu.flight.internalws.models.common.Trademark;
import com.ctrip.ibu.flight.internalws.models.constant.BusinessConst;
import com.ctrip.ibu.flight.internalws.models.constant.LogConst;
import com.ctrip.ibu.flight.internalws.models.exception.repository.RepositoryException;
import com.ctrip.ibu.flight.internalws.models.flight.FlightClass;
import com.ctrip.ibu.flight.internalws.models.flight.OrderDetailModel;
import com.ctrip.ibu.flight.internalws.models.flight.SettlementCurrency;
import com.ctrip.ibu.flight.internalws.models.message.*;
import com.ctrip.ibu.flight.internalws.models.util.SensitiveInfoType;
import com.ctrip.ibu.flight.internalws.repository.IEmailRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component("emailCommonBusiness")
public class EmailCommonBusiness implements IEmailCommonBusiness {

    private final static ILog LOGGER = LogManager.getLogger(EmailCommonBusiness.class);

    private IEmailRepository emailRepository;

    private Config config;

    @Inject
    public EmailCommonBusiness(@Named("emailRepository") IEmailRepository emailRepository,
                               @Named("config") Config config) {
        this.emailRepository = emailRepository;
        this.config = config;
    }

    /**
     * 获取邮件公共头尾
     *
     * @param orderDetail
     * @param emailSendLanguage
     */
    @Override
    public EmailHeaderAndFooterResponse getEmailHeaderAndFooter(OrderDetailModel orderDetail, EmailTemplateType emailTemplateType, LanguageType emailSendLanguage) {
        EmailHeaderAndFooterRequest headerAndFooterRequest = new EmailHeaderAndFooterRequest();
        headerAndFooterRequest.setOrderId(orderDetail.getOrderId());
        headerAndFooterRequest.setFlightClass(orderDetail.getFlightClass());
        headerAndFooterRequest.setTrademark(orderDetail.getTrademark());
        headerAndFooterRequest.setLocale(getHeaderAndFooterLocale(emailSendLanguage,orderDetail));

        switch (emailTemplateType){
            case ReservationConfirmation:
                headerAndFooterRequest.setEmailType(BusinessConst.RESERVATIONCONFIRMATIONHEADERFOOTER_EMAILTYPE);
                break;
            default:
                headerAndFooterRequest.setEmailType(BusinessConst.NOFAQHEADERFOOTER_EMAILTYPE);
                break;
        }

        return getEmailHeaderAndFooter(headerAndFooterRequest);
    }

    /**
     * 获取邮件头尾请求的Locale
     * @param emailSendLanguage 邮件发送语言
     * @param orderDetail 订单详情
     * */
    private Locale getHeaderAndFooterLocale(LanguageType emailSendLanguage, OrderDetailModel orderDetail){
        Market market;
        Locale locale = LanguageUtil.mapLanguageTypeToLocale(emailSendLanguage);

        if (orderDetail.getCorpGroup() == CorpGroup.SC){
            market = BusinessHelper.getMarketBySid(orderDetail.getAllianceOrderInfo() == null ? null : orderDetail.getAllianceOrderInfo().getSid());
        } else {
            market = BusinessHelper.getMarketByServerFrom(orderDetail.getServerFrom());
        }

        return modifyHeaderAndFooterLocale(locale,market);
    }

    private Locale modifyHeaderAndFooterLocale(Locale origLocale,Market market){
        String lan = origLocale.getLanguage();
        String country = String.valueOf(market);
        if (market == null || market == Market.AllOthers){
            country = String.valueOf(Market.US);
        }

        return new Locale(lan,country);
    }

    /**
     * 获取邮件公共头尾
     *
     * @param headerAndFooterRequest
     */
    @Override
    public EmailHeaderAndFooterResponse getEmailHeaderAndFooter(EmailHeaderAndFooterRequest headerAndFooterRequest) {

        EmailHeaderAndFooterResponse headerAndFooter = new EmailHeaderAndFooterResponse();
        try {
            headerAndFooter = emailRepository.getEmailHeaderAndFooter(headerAndFooterRequest);
        } catch (RepositoryException e) {
            LoggerHelper.appendResponseContent(String.format("获取邮件头尾接口出现异常，异常信息:\n%s", ThrowableUtils.getExceptionDesc(e)));
        }

        return headerAndFooter;
    }

    /**
     * 获取邮件发送Sender
     *
     * @param getEmailSenderRequest
     */
    @Override
    public EmailSenderModel getEmailSender(GetEmailSenderRequest getEmailSenderRequest) {
        EmailSenderModel sender = new EmailSenderModel();
        if (getEmailSenderRequest != null){
            sender = this.config.getEmailSenderInfo(getEmailSenderRequest.getLocale(),getEmailSenderRequest.getTrademark());
        }
        return sender;
    }

    /**
     * 获取资源文件路径
     *
     * @param flightClass 国际或者国内机票
     * @param trademark 商标
     * @param currency  币种
     */
    @Override
    public ResourcePathModel getResourcePath(FlightClass flightClass, Trademark trademark, SettlementCurrency currency) {
        ResourcePathModel resourcePathModel = new ResourcePathModel();
        try {
            String resourceRootPath = ResourceUtils.getURL("classpath:/statics/images").getPath();
            resourcePathModel.setIataLogoPath(resourceRootPath + "iata-logo.png");
            switch (trademark){
                case Trip:
                    resourceRootPath += "trip/";
                    resourcePathModel.setTrademarkLogoPath(resourceRootPath + "trip-logo.png");
                    switch (currency){
                        case CNY:
                            resourcePathModel.setStampLogoPath(resourceRootPath + "stamp-CNY.png");
                            break;
                        case KRW:
                            resourcePathModel.setStampLogoPath(resourceRootPath + "stamp-KR.png");
                            break;
                        default:
                            resourcePathModel.setStampLogoPath(resourceRootPath + "stamp-other-currency.png");
                            break;
                    }
                    break;
                default:
                    resourceRootPath += "ctrip/";
                    resourcePathModel.setTrademarkLogoPath(resourceRootPath + "ctrip-logo.png");
                    switch (flightClass){
                        case International:
                            resourcePathModel.setStampLogoPath(resourceRootPath + "pic-seal-hk.png");
                            break;
                        default:
                            resourcePathModel.setStampLogoPath(resourceRootPath + "pic-seal.png");
                            break;

                    }
                    break;
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(LogConst.CLOGTITLE_SYSTEMMONITOR,String.format("获取资源文件路径异常，异常信息:%s",e.getMessage()));
        }
        return resourcePathModel;
    }

    /**
     * 记录邮件发送记录
     *
     * @param sendEmailRequest 邮件发送请求
     * @param sendEmailResponse 邮件发送响应
     */
    @Override
    public void recordEmailLog(SendEmailRequestType sendEmailRequest, SendEmailResponseType sendEmailResponse) {
        try {
            EmailLogEntity emailLog = getEmailLog(sendEmailRequest,sendEmailResponse);
            this.emailRepository.insertEmailLog(emailLog);
        } catch (Exception e) {
            LOGGER.warn(LogConst.CLOGTITLE_SYSTEMMONITOR,e);
        }

    }

    private EmailLogEntity getEmailLog(SendEmailRequestType sendEmailRequest, SendEmailResponseType sendEmailResponse){
        EmailLogEntity emailLog = new EmailLogEntity();

        emailLog.setMailRequest(SerializeUtil.toJson(sendEmailRequest));
        emailLog.setRetryTimes(0);

        if (sendEmailRequest != null){
            emailLog.setEmailCategory(sendEmailRequest.emailTemplateType.toString());
            emailLog.setLan(String.valueOf(sendEmailRequest.languageType));
            emailLog.setSubject(sendEmailRequest.subject);
            emailLog.setUid(sendEmailRequest.uid);
            emailLog.setTemplateType(sendEmailRequest.emailTemplateType);
            emailLog.setOrderId(sendEmailRequest.orderID);
            emailLog.setRecipient(encryptRecipient(sendEmailRequest.recipientEmailList));
        }

        if (sendEmailResponse != null){
            if (sendEmailResponse.emailIdList != null && sendEmailResponse.emailIdList.size() > 0){
                emailLog.setEmailId(sendEmailResponse.emailIdList.get(0) == null ? "" : sendEmailResponse.emailIdList.get(0));
            } else {
                emailLog.setEmailId("");
            }
            emailLog.setActualResult(sendEmailResponse.actualResult);
            emailLog.setSendResult(sendEmailResponse.sendResult);
            emailLog.setResultMsg(sendEmailResponse.resultMessage);
        }

        return emailLog;
    }

    /**
     * 加密收件人
     * @param recipientList 收件人列表
     * */
    private String encryptRecipient(List<String> recipientList){
        if (recipientList != null && recipientList.size() > 0){

            List<String> encryptRecipient = new ArrayList<>();

            recipientList.forEach(recipient -> {
                encryptRecipient.add(SensitiveInfoOperateHelper.encrypt(SensitiveInfoType.Mail,recipient));
            });

            return String.join(",",encryptRecipient);
        }
        return "";
    }
}
