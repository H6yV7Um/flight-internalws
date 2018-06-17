package com.ctrip.ibu.flight.internalws.repository;

import com.ctrip.ibu.flight.internalws.models.exception.repository.RepositoryException;
import com.ctrip.ibu.flight.internalws.models.message.*;

import java.util.List;

/**
 * 邮件发送接口Repository
 * Created by kyxie on 2017/7/8.
 */
public interface IEmailRepository {

    /**
     * 发送邮件
     * @param request 发送邮件请求
     * */
    EmailSendResponseDO sendEmail(EmailSendRequestDO request) throws RepositoryException;

    /**
     * 根据EmailID获取邮件状态
     * */
    GetEmailStatusResponseModel getEmailStatus(GetEmailStatusRequestModel request) throws RepositoryException;

    List<EmailLogEntity> getEmailLogByOrderIds(List<Long> orderIds) throws RepositoryException;

    /**
     * 插入邮件发送记录
     * @param emailLogEntities 邮件发送记录列表
     * */
    List<Integer> insertEmailLog(List<EmailLogEntity> emailLogEntities) throws RepositoryException;

    /**
     * 插入一条邮件发送记录
     * */
    Integer insertEmailLog(EmailLogEntity emailLog) throws RepositoryException;

    /**
     * 邮件附件上传
     * */
    EmailAttachmentUploadResponseDO uploadAttachment(EmailAttachmentUploadRequestDO request) throws RepositoryException;

    /**
     * 获取邮件公共头尾
     * */
    EmailHeaderAndFooterResponse getEmailHeaderAndFooter(EmailHeaderAndFooterRequest request) throws RepositoryException;


}
