package com.ctrip.ibu.flight.internalws.repository;

import com.ctrip.ibu.flight.internalws.common.utils.CommonUtil;
import com.ctrip.ibu.flight.internalws.common.utils.ThrowableUtils;
import com.ctrip.ibu.flight.internalws.models.errorcode.repository.WSErrorSource;
import com.ctrip.ibu.flight.internalws.models.exception.repository.RepositoryException;
import com.ctrip.ibu.flight.internalws.models.exception.soa.WSInvokeException;
import com.ctrip.ibu.flight.internalws.models.message.*;
import com.ctrip.ibu.flight.internalws.models.repository.dao.ibuflightdb.EmailLog;
import com.ctrip.ibu.flight.internalws.models.repository.dao.ibuflightdb.EmailLogDO;
import com.ctrip.ibu.flight.internalws.repository.dao.ibuflightdb.IEmailLogDao;
import com.ctrip.ibu.flight.internalws.repository.dao.ibuflightdb.IEmailLogMapper;
import com.ctrip.ibu.flight.internalws.repository.entitymappers.EmailLogMapper;
import com.ctrip.ibu.flight.internalws.repository.ws.WSInvokerHelper;
import com.ctrip.ibu.flight.internalws.repository.ws.email.EmailAttachmentUploadWS;
import com.ctrip.ibu.flight.internalws.repository.ws.email.EmailHeaderAndFooterWS;
import com.ctrip.ibu.flight.internalws.repository.ws.email.EmailSendWS;
import com.ctrip.ibu.flight.internalws.repository.ws.email.GetEmailStatusWS;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 邮件仓储
 * Created by kyxie on 2017/7/8.
 */
@Repository("emailRepository")
public class EmailRepository implements IEmailRepository {

    private EmailAttachmentUploadWS emailAttachmentUploadWS;

    private EmailSendWS emailSendWS;

    private GetEmailStatusWS getEmailStatusWS;

    private EmailHeaderAndFooterWS emailHeaderAndFooterWS;

    private IEmailLogDao emailLogDao;

    private IEmailLogMapper emailLogMapper;

    @Inject
    public EmailRepository(@Named("emailAttachmentUploadWS") EmailAttachmentUploadWS emailAttachmentUploadWS,
                           @Named("emailSendWS") EmailSendWS emailSendWS,
                           @Named("getEmailStatusWS") GetEmailStatusWS getEmailStatusWS,
                           @Named("emailHeaderAndFooterWS") EmailHeaderAndFooterWS emailHeaderAndFooterWS,
                           @Named("emailLogDao") IEmailLogDao emailLogDao,
                           IEmailLogMapper emailLogMapper){
        this.emailAttachmentUploadWS = emailAttachmentUploadWS;
        this.emailSendWS = emailSendWS;
        this.getEmailStatusWS = getEmailStatusWS;
        this.emailHeaderAndFooterWS = emailHeaderAndFooterWS;
        this.emailLogDao = emailLogDao;
        this.emailLogMapper = emailLogMapper;
    }

    @Override
    public EmailSendResponseDO sendEmail(EmailSendRequestDO request) throws RepositoryException {

        EmailSendResponseDO response;

        try {
            response = WSInvokerHelper.invokeApi(request,emailSendWS);
        } catch (WSInvokeException e){
            throw ThrowableUtils.getThrowWithCause(new RepositoryException(WSErrorSource.WEBSERVICE,"调用邮件发送服务EmailSendWS异常"),e);
        }

        return response;
    }

    /**
     * 根据EmailID获取邮件状态
     *
     * @param request
     */
    @Override
    public GetEmailStatusResponseModel getEmailStatus(GetEmailStatusRequestModel request) throws RepositoryException {
        GetEmailStatusResponseModel response;

        try {
            response = WSInvokerHelper.invokeApi(request,getEmailStatusWS);
        } catch (WSInvokeException e) {
            throw ThrowableUtils.getThrowWithCause(new RepositoryException(WSErrorSource.WEBSERVICE,"调用邮件发送服务EmailSendWS异常"),e);
        }

        return response;
    }

    @Override
    public List<EmailLogEntity> getEmailLogByOrderIds(List<Long> orderIds) {

        List<EmailLogEntity> emailLogList = new ArrayList<>();

        if (orderIds != null && orderIds.size() >= 1){

        }

        return null;
    }

    /**
     * 插入邮件发送记录
     *
     * @param emailLogEntities 邮件发送记录列表
     */
    @Override
    public List<Integer> insertEmailLog(List<EmailLogEntity> emailLogEntities) throws RepositoryException{

        List<Integer> newInsertRecordList = new ArrayList<>();

        if (emailLogEntities != null && emailLogEntities.size() >= 1){
            List<EmailLog> emailLogs = new ArrayList<>();
            for (EmailLogEntity emailLogEntity : emailLogEntities){
                emailLogs.add(convertFlightMailLog(emailLogEntity));
            }

            try {
                int[] newInsertRecords = emailLogDao.insert(emailLogs);
                if (newInsertRecords != null && newInsertRecords.length >= 1){
                    for (int recordId : newInsertRecords){
                        newInsertRecordList.add(recordId);
                    }
                }
            } catch (SQLException e) {
                throw ThrowableUtils.getThrowWithCause(new RepositoryException(WSErrorSource.DATABASE,"调用emailLogDao异常"),e);
            }
        }

        return newInsertRecordList;
    }

    /**
     * 插入一条邮件发送记录
     *
     * @param emailLog 邮件Log实体
     */
    @Override
    public Integer insertEmailLog(EmailLogEntity emailLog) throws RepositoryException {
        try {
            EmailLogDO emailLogDO = EmailLogMapper.INSTANCE.emailLog2DOMapper(emailLog);
            CommonUtil.resetNullField(emailLogDO);
            return this.emailLogMapper.insert(emailLogDO);
        } catch (Exception e){
            throw ThrowableUtils.getThrowWithCause(new RepositoryException(WSErrorSource.DATABASE,"insertEmailLog异常"),e);
        }

    }

    /**
     * 上传邮件附件
     * */
    @Override
    public EmailAttachmentUploadResponseDO uploadAttachment(EmailAttachmentUploadRequestDO request) throws RepositoryException {

        EmailAttachmentUploadResponseDO uploadResponse;
        try {
            uploadResponse = WSInvokerHelper.invokeApi(request,emailAttachmentUploadWS);
        } catch (WSInvokeException e) {
            throw ThrowableUtils.getThrowWithCause(new RepositoryException(WSErrorSource.WEBSERVICE,"调用emailAttachmentUploadWS异常"),e);
        }

        return uploadResponse;
    }

    /**
     * 获取邮件公共头尾
     *
     * @param request
     */
    @Override
    public EmailHeaderAndFooterResponse getEmailHeaderAndFooter(EmailHeaderAndFooterRequest request) throws RepositoryException {

        EmailHeaderAndFooterResponse response;

        try {
            response = WSInvokerHelper.invokeApi(request,emailHeaderAndFooterWS);
        } catch (WSInvokeException e) {
            throw ThrowableUtils.getThrowWithCause(new RepositoryException(WSErrorSource.WEBSERVICE,"调用emailHeaderAndFooterWS异常"),e);
        }

        return response;
    }

    private EmailLog convertFlightMailLog(EmailLogEntity emailLogEntity){
        EmailLog emailLog = new EmailLog();
        if (emailLogEntity != null){
            emailLog.setOrderId(emailLogEntity.getOrderId());
            emailLog.setUid(emailLogEntity.getUid());
            emailLog.setTemplate(emailLogEntity.getTemplateType().toString());
            emailLog.setLan(emailLogEntity.getLan());
            emailLog.setRequest(emailLogEntity.getMailRequest());
            emailLog.setSubject(emailLogEntity.getSubject());
            emailLog.setRecipient(emailLogEntity.getRecipient());
            emailLog.setSendResult(emailLogEntity.getSendResult());
            emailLog.setEmailId(emailLogEntity.getEmailId());
            emailLog.setSendMsg(emailLogEntity.getResultMsg());
        }
        return emailLog;
    }
}
