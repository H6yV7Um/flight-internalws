package com.ctrip.ibu.flight.internalws.models.message;

import java.util.List;

/**
 * 邮件数据DTO
 * Create by kyxie on 2018/4/13 15:52
 */
public class EmailData {

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String bodyContent;

    /**
     * 收件人
     */
    private List<String> recipientList;

    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 用户UID
     */
    private String uid;

    /**
     * 抄送人列表
     */
    private List<String> ccList;

    /**
     * 密送人列表
     */
    private List<String> bccList;

    /**
     * 附件列表
     */
    private List<EmailAttachmentDTO> attachmentList;

    /**
     * 发件人
     */
    private EmailSenderModel emailSender;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }

    public List<String> getRecipientList() {
        return recipientList;
    }

    public void setRecipientList(List<String> recipientList) {
        this.recipientList = recipientList;
    }

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

    public List<String> getCcList() {
        return ccList;
    }

    public void setCcList(List<String> ccList) {
        this.ccList = ccList;
    }

    public List<String> getBccList() {
        return bccList;
    }

    public void setBccList(List<String> bccList) {
        this.bccList = bccList;
    }

    public List<EmailAttachmentDTO> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<EmailAttachmentDTO> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public EmailSenderModel getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(EmailSenderModel emailSender) {
        this.emailSender = emailSender;
    }
}
