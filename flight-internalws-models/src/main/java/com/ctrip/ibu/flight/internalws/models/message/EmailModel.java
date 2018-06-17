package com.ctrip.ibu.flight.internalws.models.message;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 邮件实体
 * Created by kyxie on 2017/8/31.
 */
public class EmailModel {

    private String emailId;

    private String uid;

    private String eid;

    private String sender;

    private List<String> recipientList;

    private List<String> cc;

    private List<String> bcc;

    private String senderName;

    private String recipientName;

    private String subject;

    private Integer bodyTemplateId;

    private String bodyContent;

    private Calendar expiredTime;

    //计划发送时间
    private Calendar scheduleTime;

    //邮件请求接收时间
    private Calendar createTime;

    //邮件实际发送时间
    private Calendar sendTime;

    //邮件发送服务器
    private String sendHost;

    private String emstpID;

    private String errorMsg;

    //邮件发送状态(1:成功，其他：错误)
    private Integer emailStatus;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getRecipientList() {
        return recipientList;
    }

    public void setRecipientList(List<String> recipientList) {
        this.recipientList = recipientList;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public List<String> getBcc() {
        return bcc;
    }

    public void setBcc(List<String> bcc) {
        this.bcc = bcc;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getBodyTemplateId() {
        return bodyTemplateId;
    }

    public void setBodyTemplateId(Integer bodyTemplateId) {
        this.bodyTemplateId = bodyTemplateId;
    }

    public String getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }

    public Calendar getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Calendar expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Calendar getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Calendar scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public Calendar getSendTime() {
        return sendTime;
    }

    public void setSendTime(Calendar sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendHost() {
        return sendHost;
    }

    public void setSendHost(String sendHost) {
        this.sendHost = sendHost;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(Integer emailStatus) {
        this.emailStatus = emailStatus;
    }

    public String getEmstpID() {
        return emstpID;
    }

    public void setEmstpID(String emstpID) {
        this.emstpID = emstpID;
    }
}
