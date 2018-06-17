package com.ctrip.ibu.flight.internalws.models.repository.dao.ibuflightdb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.ctrip.platform.dal.dao.annotation.Database;
import com.ctrip.platform.dal.dao.annotation.Sensitive;
import com.ctrip.platform.dal.dao.annotation.Type;
import java.sql.Types;
import java.sql.Timestamp;

import com.ctrip.platform.dal.dao.DalPojo;

/**
 * Created by kyxie on 2017/8/2.
 */
@Entity
@Database(name="ibuflightdb_W")
@Table(name="ibuflightmaillog")
public class IbuFightMailLog {
    //自增ID
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(value=Types.BIGINT)
    private Long iD;

    //OrderID
    @Column(name="OrderID")
    @Type(value=Types.VARCHAR)
    private String orderID;

    //MailRequest
    @Column(name="MailRequest")
    @Type(value=Types.VARCHAR)
    private String mailRequest;

    //错误消息
    @Column(name="Msg")
    @Type(value=Types.VARCHAR)
    private String msg;

    //重试次数
    @Column(name="RetryTimes")
    @Type(value=Types.INTEGER)
    private Integer retryTimes;

    //DataChange_LastTime
    @Column(name="DataChange_LastTime", insertable=false, updatable=false)
    @Type(value=Types.TIMESTAMP)
    private Timestamp datachangeLasttime;

    //状态
    @Column(name="Status")
    @Type(value=Types.VARCHAR)
    private String status;

    //邮件模板
    @Column(name="Template")
    @Type(value=Types.VARCHAR)
    private String template;

    //邮件发送结果ID
    @Column(name="EmailID")
    @Type(value=Types.BIGINT)
    private Long emailID;

    //UID
    @Column(name="UID")
    @Type(value=Types.VARCHAR)
    private String uID;

    //Type
    @Column(name="Type")
    @Type(value=Types.VARCHAR)
    private String type;

    //邮件主题
    @Column(name="Subject")
    @Type(value=Types.VARCHAR)
    private String subject;

    //收件人
    @Column(name="Receipt")
    @Type(value=Types.VARCHAR)
    private String receipt;

    //邮件发送语言
    @Column(name="Lan")
    @Type(value=Types.VARCHAR)
    private String lan;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getMailRequest() {
        return mailRequest;
    }

    public void setMailRequest(String mailRequest) {
        this.mailRequest = mailRequest;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public Timestamp getDatachangeLasttime() {
        return datachangeLasttime;
    }

    public void setDatachangeLasttime(Timestamp datachangeLasttime) {
        this.datachangeLasttime = datachangeLasttime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public Long getEmailID() {
        return emailID;
    }

    public void setEmailID(Long emailID) {
        this.emailID = emailID;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public Long getiD() {
        return iD;
    }

    public void setiD(Long iD) {
        this.iD = iD;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

}
