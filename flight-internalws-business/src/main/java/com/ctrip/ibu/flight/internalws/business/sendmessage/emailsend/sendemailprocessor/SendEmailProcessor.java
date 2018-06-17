package com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.sendemailprocessor;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.business.common.IEmailCommonBusiness;
import com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.mappers.EmailAttachmentMapper;
import com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.mappers.EmailDataMapper;
import com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.mappers.EmailSendResultMapper;
import com.ctrip.ibu.flight.internalws.common.components.Checker;
import com.ctrip.ibu.flight.internalws.common.log.LoggerHelper;
import com.ctrip.ibu.flight.internalws.common.utils.LanguageUtil;
import com.ctrip.ibu.flight.internalws.common.utils.ThrowableUtils;
import com.ctrip.ibu.flight.internalws.contract.commontypes.LanguageType;
import com.ctrip.ibu.flight.internalws.models.errorcode.emailsend.EmailSendErrorCode;
import com.ctrip.ibu.flight.internalws.models.exception.emailsend.EmailSendException;
import com.ctrip.ibu.flight.internalws.models.exception.repository.RepositoryException;
import com.ctrip.ibu.flight.internalws.models.flight.OrderDetailModel;
import com.ctrip.ibu.flight.internalws.models.log.IndexedLogTag;
import com.ctrip.ibu.flight.internalws.models.log.LogLevel;
import com.ctrip.ibu.flight.internalws.models.message.*;
import com.ctrip.ibu.flight.internalws.repository.IEmailRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * 邮件发送组件服务
 * Create by kyxie on 2018/4/12 11:56
 */
@Component
public class SendEmailProcessor implements ISendEmailProcessor {

    private final static ILog CLOG = LogManager.getLogger(SendEmailProcessor.class);

    //附件上传成功结果码
    private final static int ATTACHMENT_UPLOAD_SUCCESS_CODE = 1;

    private IEmailRepository emailRepository;

    @Inject
    public SendEmailProcessor(IEmailRepository emailRepository){
        this.emailRepository = emailRepository;
    }

    /**
     * 验证参数
     *
     * @param request 请求Request
     * @throws EmailSendException 消息发送异常
     */
    @Override
    public void validateRequest(EmailSendRequest request) throws EmailSendException {

        Checker.checkWithThrowable(request == null,new EmailSendException(EmailSendErrorCode.PARAM_ERROR,"邮件发送请求为NULL"));

        Checker.checkWithThrowable(request.getEmailData() == null,new EmailSendException(EmailSendErrorCode.LACK_EMAIL_DATA,"缺少邮件发送数据"));

        //这个其实可以不验证，发空邮件也没问题，但是目前业务需求还没有空邮件的需求，所以这里先拦截，可以暴露问题
        Checker.checkWithThrowable(StringUtils.isBlank(request.getEmailData().getBodyContent()),new EmailSendException(EmailSendErrorCode.LACK_EMAIL_BODY,"邮件发送Body为空"));

        Checker.checkWithThrowable(request.getEmailData().getEmailSender() == null
                        && (request.getEmailConfig() == null || request.getEmailConfig().getOrderTrademark() == null || request.getEmailConfig().getEmailSendLan() == null || request.getEmailConfig().getEmailSendLan() == LanguageType.unspecified),
                new EmailSendException(EmailSendErrorCode.EMAIL_SENDER_ERROR,"获取邮件数据处理类未设置EmailSender，且emailSendConfig中未设置订单Trademark，无法获取EmailSender"),
                () -> {
                    LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel,LogLevel.ERROR.toString());
                    LoggerHelper.appendResponseContent("邮件数据未设置EmailSender，且emailSendConfig中未设置订单Trademark，无法获取EmailSender。请按照上面两种方式设置EmailSender！");
                });
    }

    /**
     * 发送
     *
     * @param emailSendRequest 发送请求
     * @return 响应
     * @throws EmailSendException 邮件发送异常
     */
    @Override
    public EmailSendResponse sendMessage(EmailSendRequest emailSendRequest) throws EmailSendException {

        EmailSendResponse result;

        try {

            //组装邮件发送请求
            EmailSendRequestDO emailSendDO = getEmailSendRequest(emailSendRequest);

            //请求邮件发送接口
            EmailSendResponseDO emailSendResponse = this.emailRepository.sendEmail(emailSendDO);

            //转换响应
            result = EmailSendResultMapper.INSTANCE.emailSendResponseDO2EmailSendResponseDTO(emailSendResponse);

            Checker.checkWithThrowable(result == null,new EmailSendException(EmailSendErrorCode.EMAIL_SEND_SERVICE_ERROR,"邮件发送响应为NULL"));

        } catch (RepositoryException e) {
            EmailSendException emailSendException = new EmailSendException(EmailSendErrorCode.EMAIL_SEND_SERVICE_ERROR,"邮件发送仓储异常：RepositoryException");
            emailSendException.initCause(e);
            throw emailSendException;
        } catch (Exception e){
            EmailSendException emailSendException = new EmailSendException(EmailSendErrorCode.EMAIL_SEND_BUSINESS_ERROR,"邮件发送逻辑异常");
            emailSendException.initCause(e);
            throw emailSendException;
        }

        return result;
    }

    /**
     * 获取邮件发送数据Request
     * @param emailSendRequest 原始邮件数据
     * @return 邮件发送请求
     * */
    private EmailSendRequestDO getEmailSendRequest(EmailSendRequest emailSendRequest) throws EmailSendException{

        preProcessEmailData(emailSendRequest);

        EmailSendRequestDO emailSendRequestDO = EmailDataMapper.INSTANCE.emailDataDTO2EmailDataDO(emailSendRequest.getEmailData());

        emailSendRequestDO.setIsBodyHtml(emailSendRequest.getEmailConfig().getBodyHtml());

        return emailSendRequestDO;
    }

    /**
     * 预处理邮件数据
     * @param emailSendRequest 邮件数据
     * */
    private void preProcessEmailData(EmailSendRequest emailSendRequest) throws EmailSendException{

        //处理附件上传
        processAttachmentUpload(emailSendRequest);

    }

    /**
     * 处理附件上传(附件上传失败不影响邮件发送)
     * @param emailSendRequest 邮件发送请求
     * */
    private void processAttachmentUpload(EmailSendRequest emailSendRequest){

        if (emailSendRequest != null && emailSendRequest.getEmailData() != null && emailSendRequest.getEmailData().getAttachmentList() != null && !emailSendRequest.getEmailData().getAttachmentList().isEmpty()){

            try {

                EmailAttachmentUploadRequestDO uploadRequest = getEmailAttachmentUploadRequest(emailSendRequest.getEmailData().getAttachmentList());

                EmailAttachmentUploadResponseDO uploadResponse = emailRepository.uploadAttachment(uploadRequest);

                if (uploadResponse != null && uploadResponse.getResultCode() == ATTACHMENT_UPLOAD_SUCCESS_CODE){
                    if (uploadResponse.getAttachmentList() != null && !uploadResponse.getAttachmentList().isEmpty()){
                        emailSendRequest.getEmailData().setAttachmentList(getEmailAttachmentList(uploadResponse.getAttachmentList()));
                    }
                }
            } catch (RepositoryException e) {
                LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel, LogLevel.WARNING.toString());
                LoggerHelper.appendResponseContent(String.format("邮件附件上传仓储异常Repository，异常信息:\n%s", ThrowableUtils.getExceptionDesc(e)));
            } catch (Exception e){
                LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel, LogLevel.WARNING.toString());
                LoggerHelper.appendResponseContent(String.format("邮件附件上传异常，异常信息:\n%s", ThrowableUtils.getExceptionDesc(e)));
            }
        }
    }

    private EmailAttachmentUploadRequestDO getEmailAttachmentUploadRequest(List<EmailAttachmentDTO> attachmentList){
        EmailAttachmentUploadRequestDO uploadRequest = new EmailAttachmentUploadRequestDO();
        List<EmailAttachmentDO> attachmentDOList = new ArrayList<>();
        for (EmailAttachmentDTO emailAttachmentDTO : attachmentList) {
            attachmentDOList.add(EmailAttachmentMapper.INSTANCE.emailAttachmentDO2DTO(emailAttachmentDTO));
        }
        uploadRequest.setAttachmentList(attachmentDOList);
        return uploadRequest;
    }

    private List<EmailAttachmentDTO> getEmailAttachmentList(List<EmailAttachmentDO> emailAttachmentDOList){
        List<EmailAttachmentDTO> emailAttachmentDTOList = new ArrayList<>();
        for (EmailAttachmentDO emailAttachmentDO : emailAttachmentDOList){
            emailAttachmentDTOList.add(EmailAttachmentMapper.INSTANCE.emailAttachmentDTO2DO(emailAttachmentDO));
        }
        return emailAttachmentDTOList;
    }
}
