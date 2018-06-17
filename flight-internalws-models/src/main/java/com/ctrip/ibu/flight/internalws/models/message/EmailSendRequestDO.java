package com.ctrip.ibu.flight.internalws.models.message;

import java.util.Calendar;
import java.util.List;

/**
 * 邮件发送Request模型
 * Created by kyxie on 2017/7/7.
 */
public class EmailSendRequestDO {

    //订单号
    private Long orderId;

    //UID
    private String uid;

    //主题
    private String subject;

    //收件人邮箱
    private List<String> recipientList;

    //收件人姓名
    private String recipientName;

    //邮件内容
    private String bodyContent;

    //附件列表
    private List<EmailAttachmentDTO> attachmentList;

    //抄送列表
    private List<String> ccList;

    //密送列表
    private List<String> bccList;

    //发件服务器
    private EmailSenderModel senderInfo;

    //操作人
    private String eid;

    //Body是否是HTML格式
    private boolean isBodyHtml;

    //编码格式
    private String charset;

    //过期时间
    private Calendar expiredTime;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getRecipientList() {
        return recipientList;
    }

    public void setRecipientList(List<String> recipientList) {
        this.recipientList = recipientList;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }
    public List<EmailAttachmentDTO> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<EmailAttachmentDTO> attachmentList) {
        this.attachmentList = attachmentList;
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

    public EmailSenderModel getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(EmailSenderModel senderInfo) {
        this.senderInfo = senderInfo;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public boolean isBodyHtml() {
        return isBodyHtml;
    }

    public void setIsBodyHtml(boolean bodyHtml) {
        isBodyHtml = bodyHtml;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public Calendar getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Calendar expiredTime) {
        this.expiredTime = expiredTime;
    }

    public void setBodyHtml(boolean bodyHtml) {
        isBodyHtml = bodyHtml;
    }
}
