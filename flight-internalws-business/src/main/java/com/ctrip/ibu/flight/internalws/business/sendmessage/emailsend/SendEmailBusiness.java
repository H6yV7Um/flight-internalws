package com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.framework.foundation.Foundation;
import com.ctrip.ibu.flight.internalws.business.common.IEmailCommonBusiness;
import com.ctrip.ibu.flight.internalws.business.common.IFlightCommonBusiness;
import com.ctrip.ibu.flight.internalws.business.sendmessage.ISendMessageService;
import com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.getemaildataprocessor.GetEmailDataProcessBase;
import com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.getemaildataprocessor.GetEmailDataProcessorRouting;
import com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.mappers.EmailSendResultMapper;
import com.ctrip.ibu.flight.internalws.common.businesshelper.BusinessHelper;
import com.ctrip.ibu.flight.internalws.common.businesshelper.GdprOperateHelper;
import com.ctrip.ibu.flight.internalws.common.components.Checker;
import com.ctrip.ibu.flight.internalws.common.components.HttpContext;
import com.ctrip.ibu.flight.internalws.common.config.Config;
import com.ctrip.ibu.flight.internalws.common.log.LoggerHelper;
import com.ctrip.ibu.flight.internalws.common.utils.LanguageUtil;
import com.ctrip.ibu.flight.internalws.common.utils.SerializeUtil;
import com.ctrip.ibu.flight.internalws.common.utils.ThrowableUtils;
import com.ctrip.ibu.flight.internalws.contract.commontypes.LanguageType;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailRequestType;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailResponseType;
import com.ctrip.ibu.flight.internalws.models.common.RequestHead;
import com.ctrip.ibu.flight.internalws.models.common.Trademark;
import com.ctrip.ibu.flight.internalws.models.constant.LogConst;
import com.ctrip.ibu.flight.internalws.models.constant.QConfigConst;
import com.ctrip.ibu.flight.internalws.models.errorcode.emailsend.EmailSendErrorCode;
import com.ctrip.ibu.flight.internalws.models.exception.emailsend.EmailSendException;
import com.ctrip.ibu.flight.internalws.models.exception.emailsend.GetEmailDataException;
import com.ctrip.ibu.flight.internalws.models.exception.emailsend.GetEmailDataImplException;
import com.ctrip.ibu.flight.internalws.models.flight.OrderDetailModel;
import com.ctrip.ibu.flight.internalws.models.flight.Passenger;
import com.ctrip.ibu.flight.internalws.models.log.IndexedLogTag;
import com.ctrip.ibu.flight.internalws.models.log.LogLevel;
import com.ctrip.ibu.flight.internalws.models.message.*;
import com.ctrip.ibu.flight.internalws.models.util.GDPRType;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 邮件发送接口
 * Created by kyxie on 2017/7/21.
 */
@Service("sendEmailBusiness")
public class SendEmailBusiness implements ISendEmailBusiness {

    private static final ILog LOGGER = LogManager.getLogger(SendEmailBusiness.class);

    //将邮件发送结果记录DB线程池
    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2 + 1);

    //发送成功Code
    private static final int EMAIL_SEND_SUCCESS_CODE = 0;

    //发送失败Code
    private static final int EMAIL_SEND_FAIL_CODE = 1;

    private static final String EMAIL_SEND_SUCCESS_CODE_DESC = "SUCCESS";

    private static final String EMAIL_SEND_FAIL_CODE_DESC = "SYSTEM_ERROR";

    private static final String SYSTEM_FATAL_ERROR = "SYSTEM_FATAL_ERROR";

    private static final String EMAIL_SEND_SUCCESS_DESC = "邮件发送成功";

    private static final String EMAIL_SEND_SCENE = "邮件发送逻辑";

    private static final LanguageType DEFAULT_EMAIL_SEND_LAN = LanguageType.en_US;

    private final static String EMAILTEMPLATE_HTMLBODYCONTENT_PATTERN = "<entry><content><![CDATA[%s]]></content></entry>";

    //选择邮件Subject的正则表达式
    private final static String REGEX_GETEMAILSUBJECTFROMEMAILBODY = "<title>\\s*(.+?)\\s*</title>";

    //基础业务邮件发送组件成功结果码
    private static final int EMAIL_SEND_PROCESSOR_SUCCESS_CODE = 1;

    private IFlightCommonBusiness flightCommonBusiness;

    private IEmailCommonBusiness emailCommonBusiness;

    private GetEmailDataProcessorRouting getEmailDataProcessorRouting;

    private ISendMessageService<EmailSendRequest,EmailSendResponse,EmailSendException> emailSendService;

    private Config config;

    @Inject
    public SendEmailBusiness(IFlightCommonBusiness flightCommonBusiness,
                             IEmailCommonBusiness emailCommonBusiness,
                             GetEmailDataProcessorRouting getEmailDataProcessorRouting,
                             ISendMessageService<EmailSendRequest,EmailSendResponse,EmailSendException> emailSendService,
                             Config config) {
        this.flightCommonBusiness = flightCommonBusiness;
        this.emailCommonBusiness = emailCommonBusiness;
        this.getEmailDataProcessorRouting = getEmailDataProcessorRouting;
        this.emailSendService = emailSendService;
        this.config = config;
    }

    /**
     * 发送邮件
     *
     * @param sendEmailRequest 邮件发送请求
     */
    @Override
    public SendEmailResponseType sendMessage(SendEmailRequestType sendEmailRequest) {

        LoggerHelper.addIndexedLogTag(IndexedLogTag.Scene,EMAIL_SEND_SCENE);
        LoggerHelper.addIndexedLogTag(IndexedLogTag.MessageType,MessageCategory.Email.toString());
        LoggerHelper.addIndexedLogTag(IndexedLogTag.ReturnCode,String.valueOf(EMAIL_SEND_FAIL_CODE));
        LoggerHelper.addIndexedLogTag(IndexedLogTag.Note,EMAIL_SEND_SCENE);
        LoggerHelper.addIndexedLogTag(IndexedLogTag.EmailTemplateType,String.valueOf(sendEmailRequest.emailTemplateType));

        //默认设置失败
        SendEmailResponseType sendEmailResponse = initResponse();

        try {
            //根据模板类型获取邮件数据的处理类
            GetEmailDataProcessBase getEmailDataProcessor = this.getEmailDataProcessorRouting.getImplProcessor(sendEmailRequest.emailTemplateType);

            //组装获取邮件请求
            GetEmailDataRequest getEmailDataRequest = assemblyGetEmailDataRequest(sendEmailRequest);

            getEmailDataProcessor.validateRequest(getEmailDataRequest);

            //获取邮件数据
            GetEmailDataResponse getEmailDataResponse = getEmailDataProcessor.getEmailData(getEmailDataRequest);

            postProcessGetEmailDataResponse(getEmailDataResponse,getEmailDataRequest);

            EmailSendRequest emailSendRequest = EmailSendResultMapper.INSTANCE.emailDataResponse2EmailSendRequest(getEmailDataResponse);

            this.emailSendService.validateRequest(emailSendRequest);

            //处理邮件数据，一些公共的操作可以在这里进行
            processEmailData(sendEmailRequest,emailSendRequest);

            //发送邮件
            EmailSendResponse emailSendResponse = this.emailSendService.sendMessage(emailSendRequest);

            if (emailSendResponse.getSendResultCode() == EMAIL_SEND_PROCESSOR_SUCCESS_CODE){
                //基础业务返回发送成功
                LoggerHelper.addIndexedLogTag(IndexedLogTag.ReturnCode,String.valueOf(EMAIL_SEND_SUCCESS_CODE));
                sendEmailResponse.setSendResult(EMAIL_SEND_SUCCESS_CODE);
                sendEmailResponse.setActualResult(EMAIL_SEND_SUCCESS_CODE_DESC);
                sendEmailResponse.setResultMessage(EMAIL_SEND_SUCCESS_DESC);
                sendEmailResponse.setEmailIdList(emailSendResponse.getEmailIdList());

                List<String> emailPreviewUrl = new ArrayList<>();
                emailPreviewUrl.add(BusinessHelper.getEmailPreviewUrl(emailSendResponse.getEmailIdList().get(0)));
                sendEmailResponse.setEmailViewUrl(emailPreviewUrl);
            } else {
                sendEmailResponse.setResultMessage(emailSendResponse.getSendResultMsg());
                if (emailSendResponse.getEmailIdList() == null || emailSendResponse.getEmailIdList().size() == 0){
                    sendEmailResponse.setActualResult(EmailSendErrorCode.LACK_EMAIL_ID.toString());
                } else if ("0".equalsIgnoreCase(sendEmailResponse.getEmailIdList().get(0))){
                    sendEmailResponse.setActualResult(EmailSendErrorCode.EMAIL_ID_IS_ZERO.toString());
                }
            }

            sendEmailResponse.setEmailIdList(emailSendResponse.getEmailIdList());

        } catch (GetEmailDataImplException e) {
            LoggerHelper.addIndexedLogTag(IndexedLogTag.Note,String.valueOf(e.getResultCode()));
            LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel,LogLevel.ERROR.toString());
            LoggerHelper.appendResponseContent(String.format("获取邮件数据实现类异常GetEmailDataImplException，异常信息：%s", ThrowableUtils.getExceptionDesc(e)));
            sendEmailResponse.setActualResult(String.valueOf(e.getResultCode()));
            sendEmailResponse.setResultMessage(e.getResultCode().getDesc());
        } catch (GetEmailDataException e) {
            LoggerHelper.addIndexedLogTag(IndexedLogTag.Note,String.valueOf(e.getGetEmailDataResultCode()));
            LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel,LogLevel.ERROR.toString());
            LoggerHelper.appendResponseContent(String.format("获取邮件数据异常GetEmailDataException，异常信息：%s", ThrowableUtils.getExceptionDesc(e)));
            sendEmailResponse.setActualResult(String.valueOf(e.getGetEmailDataResultCode()));
            sendEmailResponse.setResultMessage(e.getGetEmailDataResultCode().getDesc());
        } catch (EmailSendException e) {
            LoggerHelper.addIndexedLogTag(IndexedLogTag.Note,String.valueOf(e.getEmailSendErrorCode()));
            LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel,LogLevel.ERROR.toString());
            LoggerHelper.appendResponseContent(String.format("邮件发送异常EmailSendException，异常信息：%s", ThrowableUtils.getExceptionDesc(e)));
            sendEmailResponse.setActualResult(String.valueOf(e.getEmailSendErrorCode()));
            sendEmailResponse.setResultMessage(e.getEmailSendErrorCode().getErrorMsg());
        } catch (Exception e){
            LoggerHelper.addIndexedLogTag(IndexedLogTag.Note,EMAIL_SEND_FAIL_CODE_DESC);
            LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel,LogLevel.ERROR.toString());
            LoggerHelper.appendResponseContent(String.format("邮件发送逻辑异常Exception，异常信息：%s", ThrowableUtils.getExceptionDesc(e)));
            sendEmailResponse.setActualResult(EMAIL_SEND_FAIL_CODE_DESC);
            sendEmailResponse.setResultMessage("邮件发送业务逻辑异常");
        } catch (Throwable t){
            LoggerHelper.addIndexedLogTag(IndexedLogTag.Note,SYSTEM_FATAL_ERROR);
            LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel,LogLevel.ERROR.toString());
            LoggerHelper.appendResponseContent(String.format("邮件发送逻辑发生严重错误Throwable，错误信息：%s", ThrowableUtils.getExceptionDesc(t)));
            sendEmailResponse.setActualResult(SYSTEM_FATAL_ERROR);
            sendEmailResponse.setResultMessage("邮件发送业务逻辑出现严重错误");
        } finally {

            //记录发送日志到DB
            THREAD_POOL.execute(() -> logSendLog2DB(sendEmailRequest,sendEmailResponse));

            //处理日志记录
            processLog(sendEmailResponse);
        }

        return sendEmailResponse;

    }

    /**
     * 后处理获取邮件数据响应
     * @param getEmailDataResponse 获取邮件数据响应
     * @param getEmailDataRequest 获取邮件数据请求
     * */
    private void postProcessGetEmailDataResponse(GetEmailDataResponse getEmailDataResponse,GetEmailDataRequest getEmailDataRequest){
        if (getEmailDataResponse != null && getEmailDataRequest != null){
            if (getEmailDataResponse.getAdditionalInfo() == null){
                getEmailDataResponse.setAdditionalInfo(new EmailSendAdditionalInfo());
            }
            if (getEmailDataResponse.getAdditionalInfo().getOrderDetail() == null && getEmailDataRequest.getOrderDetail() != null){
                getEmailDataResponse.getAdditionalInfo().setOrderDetail(getEmailDataRequest.getOrderDetail());
            }
        }
    }

    /**
     * 初始化Response（默认失败）
     * */
    private SendEmailResponseType initResponse(){
        SendEmailResponseType response = new SendEmailResponseType();
        response.setSendResult(EMAIL_SEND_FAIL_CODE);
        return response;
    }

    /**
     * 处理日志
     * @param response 邮件发送响应
     * */
    private void processLog(SendEmailResponseType response){
        LoggerHelper.addIndexedLogTag(IndexedLogTag.EmailId,response.emailIdList == null || response.emailIdList.size() == 0 ? "" : response.emailIdList.get(0));
        LoggerHelper.addIndexedLogTag(IndexedLogTag.ReturnCode,String.valueOf(response.sendResult));
        LoggerHelper.addIndexedLogTag(IndexedLogTag.Note,response.resultMessage);
        LoggerHelper.addIndexedLogTag(IndexedLogTag.ActualResult,response.actualResult == null ? "null" : response.actualResult.toLowerCase());
    }

    /**
     * 处理邮件数据
     * @param sendEmailRequest 邮件发送原始请求
     * @param emailSendRequest 需要处理的邮件数据
     * */
    private void processEmailData(SendEmailRequestType sendEmailRequest,EmailSendRequest emailSendRequest) throws EmailSendException{

        EmailData emailData = emailSendRequest.getEmailData();

        if (emailData.getEmailSender() == null){
            EmailSendConfig emailSendConfig = emailSendRequest.getEmailConfig();
            EmailSenderModel emailSender = getEmailSender(emailSendConfig);

            Checker.checkWithThrowable(emailSender == null,
                    new EmailSendException(EmailSendErrorCode.EMAIL_SENDER_ERROR,
                            String.format("根据语言：%s,Trademark：%s获取EmailSender失败",String.valueOf(emailSendConfig.getEmailSendLan()),String.valueOf(emailSendConfig.getOrderTrademark()))),
                    () -> LoggerHelper.appendResponseContent(String.format("根据语言：%s,Trademark：%s获取EmailSender失败",String.valueOf(emailSendConfig.getEmailSendLan()),String.valueOf(emailSendConfig.getOrderTrademark()))));

            emailData.setEmailSender(emailSender);
        }

        processEmailSendConfig(emailSendRequest);

        if(this.config.getSwitch(QConfigConst.OPENEMAILSENDTEST)
                && config.getEmailSendTestTemplateTypeList() != null
                && config.getEmailSendTestTemplateTypeList().contains(sendEmailRequest.emailTemplateType.toString())){

            LoggerHelper.appendResponseContent(String.format("邮件发送给指定收件人开关%s打开，且邮件类型%s在指定模板中，发送邮件到指定联系人",QConfigConst.OPENEMAILSENDTEST,sendEmailRequest.emailTemplateType.toString()));

            //设置测试收件人
            emailData.setRecipientList(this.config.getEmailSendTestRecipientList());
        } else if (sendEmailRequest.recipientEmailList != null && sendEmailRequest.recipientEmailList.size() > 0){

            LoggerHelper.appendResponseContent("邮件发送请求中指定了联系人，优先使用指定联系人");

            //优先以传递的收件人为准
            emailData.setRecipientList(sendEmailRequest.recipientEmailList);
        } else if (emailSendRequest.getAdditionalInfo() != null && emailSendRequest.getAdditionalInfo().getOrderDetail() != null
                && emailSendRequest.getAdditionalInfo().getOrderDetail().getContactInfo() != null
                && StringUtils.isNotBlank(emailSendRequest.getAdditionalInfo().getOrderDetail().getContactInfo().getEmail())){
            List<String> recipientList = new ArrayList<>();
            recipientList.add(emailSendRequest.getAdditionalInfo().getOrderDetail().getContactInfo().getEmail());
            emailData.setRecipientList(recipientList);
            LoggerHelper.appendResponseContent("远端配置，邮件发送请求，及邮件处理类均未设置收件人，使用订单详情联系人邮箱");
        }

        Checker.checkWithThrowable(emailData.getRecipientList() == null || emailData.getRecipientList().size() == 0,
                new EmailSendException(EmailSendErrorCode.EMAIL_RECIPIENT_ERROR,"远端配置，邮件发送请求，及邮件处理类均未设置收件人"), () -> {
                    LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel,LogLevel.ERROR.toString());
                    LoggerHelper.appendResponseContent("远端配置，邮件发送请求，及邮件处理类均未设置收件人!!");
                });

        if (sendEmailRequest.bcc != null && sendEmailRequest.bcc.size() > 0){
            emailData.setBccList(sendEmailRequest.bcc);
        }

        if (sendEmailRequest.cc != null && sendEmailRequest.cc.size() > 0){
            emailData.setCcList(sendEmailRequest.cc);
        }

        if (StringUtils.isNotBlank(sendEmailRequest.subject)){
            emailData.setSubject(sendEmailRequest.subject);
        }

        emailData.setOrderId(sendEmailRequest.orderID);
        emailData.setUid(sendEmailRequest.uid);

        //使用CDATA包装邮件内容
        if (StringUtils.isNotBlank(emailData.getBodyContent())){
            emailData.setBodyContent(String.format(EMAILTEMPLATE_HTMLBODYCONTENT_PATTERN,emailData.getBodyContent()));
        }

        //设置Subject
        if (StringUtils.isBlank(emailData.getSubject()) && org.apache.commons.lang3.StringUtils.isNotBlank(emailData.getBodyContent())){
            Matcher searchSubjectMatcher = Pattern.compile(REGEX_GETEMAILSUBJECTFROMEMAILBODY).matcher(emailData.getBodyContent());
            if (searchSubjectMatcher.find()){
                String findResult = searchSubjectMatcher.group(1).intern();
                if (StringUtils.isNotBlank(findResult)){
                    emailData.setSubject(findResult);
                } else {
                    LoggerHelper.appendResponseContent("邮件Subject获取失败");
                    LOGGER.warn(LogConst.CLOGTITLE_SYSTEMMONITOR,"邮件Subject获取失败");
                }
            }
        }

        sendEmailRequest.setRecipientEmailList(emailData.getRecipientList());
        //sendEmailRequest.setBodyContent(emailData.getBodyContent());
        sendEmailRequest.setSubject(emailData.getSubject());

    }

    private void processEmailSendConfig(EmailSendRequest emailSendRequest){
        EmailSendConfig config = emailSendRequest.getEmailConfig();
        if (config == null){
            emailSendRequest.setEmailConfig(getDefaultEmailSendConfig());
        } else {
            if (config.getBodyHtml() == null){
                config.setBodyHtml(true);
            }
            if (config.getEmailSendLan() == null){
                config.setEmailSendLan(DEFAULT_EMAIL_SEND_LAN);
            }
            if (config.getOrderTrademark() == null){
                config.setOrderTrademark(Trademark.Trip);
            }
        }
    }

    private EmailSendConfig getDefaultEmailSendConfig(){
        EmailSendConfig defaultConfig = new EmailSendConfig();
        defaultConfig.setBodyHtml(true);
        defaultConfig.setOperator(Foundation.app().getAppId());
        return defaultConfig;
    }

    /**
     * 根据订单详情获取EmailSender
     * @param emailSendConfig 邮件发送配置
     * */
    private EmailSenderModel getEmailSender(EmailSendConfig emailSendConfig) throws EmailSendException{
        GetEmailSenderRequest request = new GetEmailSenderRequest();
        request.setLocale(LanguageUtil.mapLanguageTypeToLocale(emailSendConfig.getEmailSendLan()));
        request.setTrademark(emailSendConfig.getOrderTrademark());

        EmailSenderModel emailSender = this.emailCommonBusiness.getEmailSender(request);

        Checker.checkWithThrowable(emailSender == null,new EmailSendException(EmailSendErrorCode.EMAIL_SENDER_ERROR,"获取EmailSender失败"),() -> {
            LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel,LogLevel.FATAL.toString());
            LoggerHelper.appendResponseContent(String.format("获取EmailSender为NULL,获取Sender的请求：%s", SerializeUtil.toJson(request)));
        });

        return emailSender;
    }

    /**
     * 记录邮件发送日志到数据库
     * @param sendEmailRequest 邮件发送请求
     * @param sendEmailResponse 邮件发送响应
     * */
    private void logSendLog2DB(SendEmailRequestType sendEmailRequest,SendEmailResponseType sendEmailResponse){
        this.emailCommonBusiness.recordEmailLog(sendEmailRequest,sendEmailResponse);
    }

    /**
     * 组装获取邮件数据请求
     * @param origSendReq 原始邮件发送请求
     * */
    private GetEmailDataRequest assemblyGetEmailDataRequest(SendEmailRequestType origSendReq){

        preProcessEmailSendReq(origSendReq);

        LoggerHelper.addIndexedLogTag(IndexedLogTag.Uid,origSendReq.uid);
        LoggerHelper.addIndexedLogTag(IndexedLogTag.operator,origSendReq.operator);
        LoggerHelper.appendResponseContent(String.format("原始请求邮件发送语言为：%s",String.valueOf(origSendReq.languageType)));

        GetEmailDataRequest getEmailDataRequest = new GetEmailDataRequest();

        if (origSendReq.orderID != null && origSendReq.orderID > 0){
            LoggerHelper.appendResponseContent(String.format("请求中OrderID=%d合法，尝试从订单详情获取最终邮件发送语言",origSendReq.orderID));
            LoggerHelper.addIndexedLogTag(IndexedLogTag.OrderId,origSendReq.orderID.toString());

            OrderDetailModel orderDetail;

            try {
                orderDetail = getOrderDetail(origSendReq.orderID,origSendReq.uid,origSendReq.languageType,true);

                if (orderDetail != null){
                    getEmailDataRequest.setOrderDetail(orderDetail);
                    processGdpr(orderDetail);
                    LoggerHelper.appendResponseContent("获取订单详情成功，开始根据订单详情结果获取邮件发送最终语言......");

                    //查询LocaleID缓存
                    this.flightCommonBusiness.queryAll();

                    LanguageType emailSendLan = BusinessHelper.getEmailSendLanguage(origSendReq.languageType,orderDetail,origSendReq.uid,origSendReq.isCustomContent);

                    origSendReq.setLanguageType(emailSendLan);

                    LoggerHelper.addIndexedLogTag(IndexedLogTag.EmailLanguageType,String.valueOf(emailSendLan));
                    LoggerHelper.addIndexedLogTag(IndexedLogTag.ServerFrom,orderDetail.getServerFrom());
                    LoggerHelper.addIndexedLogTag(IndexedLogTag.Uid,orderDetail.getUid());
                    LoggerHelper.addIndexedLogTag(IndexedLogTag.BookingChannel,orderDetail.getBookingChannel());
                    LoggerHelper.addIndexedLogTag(IndexedLogTag.EngineType,orderDetail.getEngineType());
                } else {
                    origSendReq.setLanguageType(DEFAULT_EMAIL_SEND_LAN);
                    LoggerHelper.appendResponseContent(String.format("获取%d订单详情失败，无法获知邮件发送语言，设置邮件发送语言为%s\n",origSendReq.orderID,DEFAULT_EMAIL_SEND_LAN));
                }
            } catch (Exception e){
                LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel,LogLevel.WARNING.toString());
                LoggerHelper.appendResponseContent(String.format("assemblyGetEmailDataRequest组装获取邮件数据请求时异常，异常信息:\n%s",ThrowableUtils.getExceptionDesc(e)));
            }
        } else {
            LoggerHelper.appendResponseContent(String.format("原始请求缺少OrderID，以请求的语言为准，为unspecified时设置默认语言%s",DEFAULT_EMAIL_SEND_LAN.toString()));
            if (origSendReq.languageType == LanguageType.unspecified){
                origSendReq.setLanguageType(DEFAULT_EMAIL_SEND_LAN);
            }
        }

        LoggerHelper.addIndexedLogTag(IndexedLogTag.EmailLanguageType, origSendReq.languageType.toString());
        HttpContext.setContextLanguage(origSendReq.languageType);//设置线程语言
        HttpContext.setEmailsendLanguage(origSendReq.languageType);//设置邮件发送语言

        getEmailDataRequest.setSendEmailRequest(origSendReq);

        return getEmailDataRequest;
    }

    /**
     * 处理GDPR相关信息
     * @param orderDetail 订单详情
     * */
    private void processGdpr(OrderDetailModel orderDetail){
        if (orderDetail != null && StringUtils.isNotBlank(orderDetail.getUid())){
            String uid = orderDetail.getUid();

            if (orderDetail.getContactInfo() != null){
                if (StringUtils.isNotBlank(orderDetail.getContactInfo().getEmail())){
                    LoggerHelper.appendResponseContent(String.format("订单联系人邮箱GDPR加密结果：%s",GdprOperateHelper.hashString(uid,GDPRType.GDPR_CONTACT_EMAIL,orderDetail.getContactInfo().getEmail())));
                }
                if (StringUtils.isNotBlank(orderDetail.getContactInfo().getName())){
                    LoggerHelper.appendResponseContent(String.format("订单联系人姓名GDPR加密结果：%s",GdprOperateHelper.hashString(uid,GDPRType.GDPR_CONTACT_NAME,orderDetail.getContactInfo().getName())));
                }
            }
            if (orderDetail.getPassengerList() != null && orderDetail.getPassengerList().size() > 0){
                for (Passenger passenger : orderDetail.getPassengerList()){
                    String passengerId = passenger.getPassengerId() == null ? "" : passenger.getPassengerId().toString();
                    LoggerHelper.appendResponseContent(String.format("订单乘机人(PassengerId:%s)姓名GDPR加密结果：%s",passengerId,GdprOperateHelper.hashString(uid,GDPRType.GDPR_USER_NAME,passenger.getName())));
                    LoggerHelper.appendResponseContent(String.format("订单乘机人(PassengerId:%s)证件号GDPR加密结果：%s",passengerId,GdprOperateHelper.hashString(uid,GDPRType.GDPR_TRAVELER_IDCARDNO,passenger.getIdNo())));
                }
            }
        }
    }

    /**
     * 预处理邮件发送请求
     * @param emailSendReq 邮件发送请求
     * */
    private void preProcessEmailSendReq(SendEmailRequestType emailSendReq){

        if (emailSendReq.languageType == null){
            emailSendReq.languageType = LanguageType.unspecified;
        }

        if (emailSendReq.isCustomContent == null){
            emailSendReq.isCustomContent = false;
        }

        if (emailSendReq.isBodyHtml == null){
            emailSendReq.isBodyHtml = true;
        }
    }

    /**
     * 获取订单详情
     * @param orderId 订单号
     * @param uid UID
     * @param languageType 语言
     * @param needCache 是否需要缓存
     * @return 订单详情
     * */
    private OrderDetailModel getOrderDetail(Long orderId, String uid, LanguageType languageType,boolean needCache){
        RequestHead requestHead = new RequestHead();
        requestHead.setUid(uid);
        if (languageType == LanguageType.unspecified){
            requestHead.setLanguage(DEFAULT_EMAIL_SEND_LAN);
        } else {
            requestHead.setLanguage(languageType);
        }

        return this.flightCommonBusiness.getOrderDetail(orderId,requestHead,needCache);
    }

}
