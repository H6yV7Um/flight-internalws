package com.ctrip.ibu.flight.internalws.repository.ws.email;

import com.ctrip.ibu.flight.internalws.models.annotation.WSMethodMeta;
import com.ctrip.ibu.flight.internalws.models.message.EmailAttachmentDO;
import com.ctrip.ibu.flight.internalws.models.message.EmailAttachmentUploadRequestDO;
import com.ctrip.ibu.flight.internalws.models.message.EmailAttachmentUploadResponseDO;
import com.ctrip.ibu.flight.internalws.repository.ws.WSMethodInvoker;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.SOACommonHelper;
import com.ctrip.soa.platform.basesystem.emailservice.v1.Attachment;
import com.ctrip.soa.platform.basesystem.emailservice.v1.EmailServiceClient;
import com.ctrip.soa.platform.basesystem.emailservice.v1.UploadAttachmentRequest;
import com.ctrip.soa.platform.basesystem.emailservice.v1.UploadAttachmentResponse;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 邮件附件上传接口服务
 * Created by kyxie on 2017/8/3.
 */
@Repository("emailAttachmentUploadWS")
@WSMethodMeta(methodDesc = "邮件附件上传接口",recordRequestLog = false)
public class EmailAttachmentUploadWS implements WSMethodInvoker<EmailServiceClient,EmailAttachmentUploadRequestDO,EmailAttachmentUploadResponseDO,UploadAttachmentRequest,UploadAttachmentResponse> {

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
     * @param emailAttachmentUploadRequestDO
     */
    @Override
    public UploadAttachmentRequest convertRequestToWSRequest(EmailAttachmentUploadRequestDO emailAttachmentUploadRequestDO) {

        UploadAttachmentRequest result = new UploadAttachmentRequest();
        List<Attachment> wsAttachmentList = new ArrayList<>();

        if (emailAttachmentUploadRequestDO != null){
            List<EmailAttachmentDO> attachmentList = emailAttachmentUploadRequestDO.getAttachmentList();
            if (attachmentList != null && !attachmentList.isEmpty()){
                for (EmailAttachmentDO attachment : attachmentList){
                    Attachment wsAttachment = new Attachment();
                    wsAttachment.setAttachmentName(attachment.getAttachmentName());
                    wsAttachment.setAttachmentContent(attachment.getAttachmentContent());
                    wsAttachmentList.add(wsAttachment);
                }

                result.setAttachmentList(wsAttachmentList);
            }
        }

        return result;
    }

    /**
     * 调用方法
     *
     * @param emailServiceClient
     * @param uploadAttachmentRequest
     */
    @Override
    public UploadAttachmentResponse invokeMethod(EmailServiceClient emailServiceClient, UploadAttachmentRequest uploadAttachmentRequest) throws Exception {

        return emailServiceClient.uploadAttachment(uploadAttachmentRequest);

    }

    /**
     * Response转换
     *
     * @param wsResponse
     */
    @Override
    public EmailAttachmentUploadResponseDO convertWSResponseToResponse(UploadAttachmentResponse wsResponse) {
        EmailAttachmentUploadResponseDO result = new EmailAttachmentUploadResponseDO();

        if (wsResponse != null){
            List<Attachment> wsAttachmentList = wsResponse.getAttachmentList();
            if (wsAttachmentList != null && !wsAttachmentList.isEmpty()){
                List<EmailAttachmentDO> attachmentList = new ArrayList<>();
                for (Attachment wsAttachment : wsAttachmentList){
                    EmailAttachmentDO attachment = new EmailAttachmentDO();
                    attachment.setAttachmentName(wsAttachment.getAttachmentName());
                    attachment.setAttachmentPath(wsAttachment.getAttachmentPath());
                    attachmentList.add(attachment);
                }
                result.setAttachmentList(attachmentList);
            }
            result.setResultCode(wsResponse.getResultCode());
            result.setResultMsg(wsResponse.getResultMsg());
        }

        return result;
    }

    /**
     * 调用的方法名称
     */
    @Override
    public String methodName() {
        return "uploadAttachment";
    }

}
