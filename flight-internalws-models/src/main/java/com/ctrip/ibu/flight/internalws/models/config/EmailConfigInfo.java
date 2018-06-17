package com.ctrip.ibu.flight.internalws.models.config;

import com.ctrip.ibu.flight.internalws.models.message.EmailSenderModel;

import java.util.List;
import java.util.Map;

/**
 * 邮件配置信息
 * Created by kyxie on 2017/8/9.
 */
public class EmailConfigInfo {

    //邮件发送Code
    private String emailSendCode;

    //邮件模板ID
    private String emailTemplateId;

    //邮件字符集(UTF8发送多语言)
    private String emailCharset;

    //EmailSender配置
    private List<EmailSenderModel> emailSenderList;

    //需要缓存的模板名集合
    private List<String> cachedTemplatesNameList;

    //需要缓存的多语言名集合
    private List<String> cachedMessageResourcesNameList;

    //订单详情URL模式列表
    private Map<String,String> orderDetailUrlPattern;

    //邮件模板配置
    private EmailTemplateConfigInfo emailTemplateConfigInfo;

    public String getEmailSendCode() {
        return emailSendCode;
    }

    public void setEmailSendCode(String emailSendCode) {
        this.emailSendCode = emailSendCode;
    }

    public String getEmailTemplateId() {
        return emailTemplateId;
    }

    public void setEmailTemplateId(String emailTemplateId) {
        this.emailTemplateId = emailTemplateId;
    }

    public List<EmailSenderModel> getEmailSenderList() {
        return emailSenderList;
    }

    public void setEmailSenderList(List<EmailSenderModel> emailSenderList) {
        this.emailSenderList = emailSenderList;
    }

    public EmailTemplateConfigInfo getEmailTemplateConfigInfo() {
        return emailTemplateConfigInfo;
    }

    public void setEmailTemplateConfigInfo(EmailTemplateConfigInfo emailTemplateConfigInfo) {
        this.emailTemplateConfigInfo = emailTemplateConfigInfo;
    }

    public List<String> getCachedTemplatesNameList() {
        return cachedTemplatesNameList;
    }

    public void setCachedTemplatesNameList(List<String> cachedTemplatesNameList) {
        this.cachedTemplatesNameList = cachedTemplatesNameList;
    }

    public List<String> getCachedMessageResourcesNameList() {
        return cachedMessageResourcesNameList;
    }

    public void setCachedMessageResourcesNameList(List<String> cachedMessageResourcesNameList) {
        this.cachedMessageResourcesNameList = cachedMessageResourcesNameList;
    }

    public String getEmailCharset() {
        return emailCharset;
    }

    public void setEmailCharset(String emailCharset) {
        this.emailCharset = emailCharset;
    }

    public Map<String, String> getOrderDetailUrlPattern() {
        return orderDetailUrlPattern;
    }

    public void setOrderDetailUrlPattern(Map<String, String> orderDetailUrlPattern) {
        this.orderDetailUrlPattern = orderDetailUrlPattern;
    }
}
