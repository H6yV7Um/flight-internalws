package com.ctrip.ibu.flight.internalws.models.repository.dao.ibuflightdb;

import com.ctrip.platform.dal.dao.DalPojo;
import com.ctrip.platform.dal.dao.annotation.Database;
import com.ctrip.platform.dal.dao.annotation.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.sql.Types;

/**
 * Created by kyxie on 2017/8/23.
 */
@Entity
@Database(name="ibuflightdb_W")
@Table(name="EmailLog")
public class EmailLog implements DalPojo {

    //主键
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(value= Types.BIGINT)
    private Long iD;

    //订单号
    @Column(name="OrderId")
    @Type(value=Types.BIGINT)
    private Long orderId;

    //用户ID
    @Column(name="Uid")
    @Type(value=Types.VARCHAR)
    private String uid;

    //邮件模板类型
    @Column(name="Template")
    @Type(value=Types.VARCHAR)
    private String template;

    //邮件发送语言
    @Column(name="Lan")
    @Type(value=Types.VARCHAR)
    private String lan;

    //邮件发送原始请求
    @Column(name="Request")
    @Type(value=Types.VARCHAR)
    private String request;

    //邮件主题
    @Column(name="Subject")
    @Type(value=Types.VARCHAR)
    private String subject;

    //收件人邮箱
    @Column(name="Recipient")
    @Type(value=Types.VARCHAR)
    private String recipient;

    //邮件发送结果
    @Column(name="SendResult")
    @Type(value=Types.TINYINT)
    private Integer sendResult;

    //邮件ID
    @Column(name="EmailId")
    @Type(value=Types.VARCHAR)
    private String emailId;

    //发送重试次数
    @Column(name="RetryTimes")
    @Type(value=Types.INTEGER)
    private Integer retryTimes;

    //发送结果描述
    @Column(name="SendMsg")
    @Type(value=Types.VARCHAR)
    private String sendMsg;

    //邮件类型
    @Column(name="EmailCategory")
    @Type(value=Types.VARCHAR)
    private String emailCategory;

    //数据最后一次更新时间
    @Column(name="DataChange_LastTime", insertable=false, updatable=false)
    @Type(value=Types.TIMESTAMP)
    private Timestamp datachangeLasttime;

    //数据创建时间
    @Column(name="DataChange_CreateTime")
    @Type(value=Types.TIMESTAMP)
    private Timestamp datachangeCreatetime;

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

    public Long getiD() {
        return iD;
    }

    public void setiD(Long iD) {
        this.iD = iD;
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

    public Timestamp getDatachangeLasttime() {
        return datachangeLasttime;
    }

    public void setDatachangeLasttime(Timestamp datachangeLasttime) {
        this.datachangeLasttime = datachangeLasttime;
    }

    public Timestamp getDatachangeCreatetime() {
        return datachangeCreatetime;
    }

    public void setDatachangeCreatetime(Timestamp datachangeCreatetime) {
        this.datachangeCreatetime = datachangeCreatetime;
    }

}
