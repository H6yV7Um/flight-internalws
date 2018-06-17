package com.ctrip.ibu.flight.internalws.models.repository.dao.ibuflightdb;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * EmailLog DO
 * Create by kyxie on 2018/4/19 14:51
 */
@Alias("emailLogDO")
public class EmailLogDO {

    private Long id;

    private Long orderId;

    private String uid;

    private String template;

    private String lan;

    private String request;

    private String subject;

    private String recipient;

    private Integer sendResult;

    private String actualResult;

    private String emailId;

    private Integer retryTimes;

    private String sendMsg;

    private String emailCategory;

    private Date dataChange_LastTime;

    private Date dataChange_CreateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Integer getSendResult() {
        return sendResult;
    }

    public void setSendResult(Integer sendResult) {
        this.sendResult = sendResult;
    }

    public String getActualResult() {
        return actualResult;
    }

    public void setActualResult(String actualResult) {
        this.actualResult = actualResult;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public String getSendMsg() {
        return sendMsg;
    }

    public void setSendMsg(String sendMsg) {
        this.sendMsg = sendMsg;
    }

    public String getEmailCategory() {
        return emailCategory;
    }

    public void setEmailCategory(String emailCategory) {
        this.emailCategory = emailCategory;
    }

    public Date getDataChange_LastTime() {
        return dataChange_LastTime;
    }

    public void setDataChange_LastTime(Date dataChange_LastTime) {
        this.dataChange_LastTime = dataChange_LastTime;
    }

    public Date getDataChange_CreateTime() {
        return dataChange_CreateTime;
    }

    public void setDataChange_CreateTime(Date dataChange_CreateTime) {
        this.dataChange_CreateTime = dataChange_CreateTime;
    }
}
