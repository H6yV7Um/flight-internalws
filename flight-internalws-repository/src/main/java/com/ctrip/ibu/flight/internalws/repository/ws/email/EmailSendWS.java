package com.ctrip.ibu.flight.internalws.repository.ws.email;

import com.ctrip.framework.foundation.Foundation;
import com.ctrip.ibu.flight.internalws.common.config.Config;
import com.ctrip.ibu.flight.internalws.models.annotation.WSMethodMeta;
import com.ctrip.ibu.flight.internalws.models.application.ApplicationInfo;
import com.ctrip.ibu.flight.internalws.models.message.EmailAttachmentDTO;
import com.ctrip.ibu.flight.internalws.models.message.EmailSendRequestDO;
import com.ctrip.ibu.flight.internalws.models.message.EmailSendResponseDO;
import com.ctrip.ibu.flight.internalws.models.message.EmailSenderModel;
import com.ctrip.ibu.flight.internalws.repository.ws.WSMethodInvoker;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.SOACommonHelper;
import com.ctrip.soa.platform.basesystem.emailservice.v1.Attachment;
import com.ctrip.soa.platform.basesystem.emailservice.v1.EmailServiceClient;
import com.ctrip.soa.platform.basesystem.emailservice.v1.SendEmailRequest;
import com.ctrip.soa.platform.basesystem.emailservice.v1.SendEmailResponse;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 邮件发送接口服务
 * Created by kyxie on 2017/7/8.
 */
@Component("emailSendWS")
@WSMethodMeta(methodDesc = "邮件发送服务接口")
public class EmailSendWS implements WSMethodInvoker<EmailServiceClient,EmailSendRequestDO,EmailSendResponseDO,SendEmailRequest,SendEmailResponse> {

    private Config config;

    @Inject
    public EmailSendWS(@Named("config") Config Config){
        this.config = Config;
    }

    /**
     * 获取WebService的Client实例
     */
    @Override
    public EmailServiceClient getClientInstance() {
        return SOACommonHelper.getSoaClientInstance(EmailServiceClient.class,"");
    }

    /**
     * Request参数转换
     *
     * @param emailSendRequestDO
     */
    @Override
    public SendEmailRequest convertRequestToWSRequest(EmailSendRequestDO emailSendRequestDO) {
        ApplicationInfo appInfo = config.getApplicationInfo();
        SendEmailRequest sendEmailRequest = new SendEmailRequest();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis() + 1*24*60*60*1000));
        sendEmailRequest.setExpiredTime(calendar);
        sendEmailRequest.setIsBodyHtml(emailSendRequestDO.isBodyHtml());
        sendEmailRequest.setOrderID(emailSendRequestDO.getOrderId());
        sendEmailRequest.setUid(emailSendRequestDO.getUid());
        sendEmailRequest.setBodyContent(emailSendRequestDO.getBodyContent());
        //sendEmailRequest.setBodyContent(SerializeUtil.encode(emailSendRequestDO.getBodyContent()));
        sendEmailRequest.setSubject(emailSendRequestDO.getSubject());
        sendEmailRequest.setRecipient(emailSendRequestDO.getRecipientList());
        sendEmailRequest.setBcc(emailSendRequestDO.getBccList());
        sendEmailRequest.setCc(emailSendRequestDO.getCcList());
        sendEmailRequest.setEid(emailSendRequestDO.getEid());
        //sendEmailRequest.setCharset("utf-8");
        sendEmailRequest.setAppID(Integer.parseInt(Foundation.app().getAppId()));
        sendEmailRequest.setSendCode(appInfo.getEmailConfigInfo().getEmailSendCode());
        sendEmailRequest.setBodyTemplateID(Integer.parseInt(appInfo.getEmailConfigInfo().getEmailTemplateId()));
        sendEmailRequest.setUid(emailSendRequestDO.getUid());

        //Email Sender信息
        EmailSenderModel senderInfo = emailSendRequestDO.getSenderInfo();
        if (senderInfo != null){
            sendEmailRequest.setSender(senderInfo.getSender());
            sendEmailRequest.setSenderName(senderInfo.getSenderName());
        }

        //附件
        List<EmailAttachmentDTO> attachmentList = emailSendRequestDO.getAttachmentList();
        if (attachmentList != null && !attachmentList.isEmpty()){
            List<Attachment> attachments = new ArrayList<>();
            for (EmailAttachmentDTO attachment : attachmentList){
                Attachment wsAttachment = new Attachment();
                wsAttachment.setAttachmentName(attachment.getAttachmentName());
                wsAttachment.setAttachmentPath(attachment.getAttachmentPath());
                attachments.add(wsAttachment);
            }
            sendEmailRequest.setAttachmentList(attachments);
        }

        return sendEmailRequest;
    }

    /**
     * 调用方法
     *
     * @param emailServiceClient
     * @param sendEmailRequest
     */
    @Override
    public SendEmailResponse invokeMethod(EmailServiceClient emailServiceClient, SendEmailRequest sendEmailRequest) throws Exception {
        return emailServiceClient.sendEmail(sendEmailRequest);
    }

    /**
     * Response转换
     *
     * @param wsResponse
     */
    @Override
    public EmailSendResponseDO convertWSResponseToResponse(SendEmailResponse wsResponse) {

        EmailSendResponseDO sendEmailResponse = new EmailSendResponseDO();

        if (wsResponse != null){
            sendEmailResponse.setResultCode(wsResponse.getResultCode());
            sendEmailResponse.setResultMsg(wsResponse.getResultMsg());
            sendEmailResponse.setEmailIdList(wsResponse.getEmailIDList());
        }else{
            sendEmailResponse.setResultCode(0);
            sendEmailResponse.setResultMsg("邮件发送接口响应为NULL");
        }

        return sendEmailResponse;
    }

    /**
     * 调用的方法名称
     */
    @Override
    public String methodName() {
        return "sendEmail";
    }
}
