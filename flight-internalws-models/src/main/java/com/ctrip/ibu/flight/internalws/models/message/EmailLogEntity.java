package com.ctrip.ibu.flight.internalws.models.message;

import com.ctrip.ibu.flight.internalws.contract.commontypes.EmailTemplateType;

import java.util.Date;

/**
 * 邮件Log实体
 * Created by kyxie on 2017/8/2.
 */
public class EmailLogEntity {

    private Long orderId;

    private String uid;

    private String mailRequest;

    private Integer retryTimes;

    private String subject;

    private String content;

    private String recipient;

    private Date dataChange_LastTime;

    private Integer sendResult;

    private String emailId;

    private EmailTemplateType templateType;

    private String resultMsg;

    private String actualResult;

    private String emailCategory;

    //邮件发送语言
    private String lan;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMailRequest() {
        return mailRequest;
    }

    public void setMailRequest(String mailRequest) {
        this.mailRequest = mailRequest;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public Date getDataChange_LastTime() {
        return dataChange_LastTime;
    }

    public void setDataChange_LastTime(Date dataChange_LastTime) {
        this.dataChange_LastTime = dataChange_LastTime;
    }

    public Integer getSendResult() {
        return sendResult;
    }

    public void setSendResult(Integer sendResult) {
        this.sendResult = sendResult;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public EmailTemplateType getTemplateType() {
        return templateType;
    }

    public void setTemplateType(EmailTemplateType templateType) {
        this.templateType = templateType;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getEmailCategory() {
        return emailCategory;
    }

    public void setEmailCategory(String emailCategory) {
        this.emailCategory = emailCategory;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getActualResult() {
        return actualResult;
    }

    public void setActualResult(String actualResult) {
        this.actualResult = actualResult;
    }
}
