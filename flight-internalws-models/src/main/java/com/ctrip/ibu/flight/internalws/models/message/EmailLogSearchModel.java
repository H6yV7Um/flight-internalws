package com.ctrip.ibu.flight.internalws.models.message;

import com.ctrip.ibu.flight.internalws.contract.commontypes.EmailTemplateType;

/**
 * 邮件发送Log查询模型
 * Created by kyxie on 2017/8/2.
 */
public class EmailLogSearchModel {

    private Long orderId;

    private String uid;

    private EmailTemplateType templateType;

    private String emailId;

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

    public EmailTemplateType getTemplateType() {
        return templateType;
    }

    public void setTemplateType(EmailTemplateType templateType) {
        this.templateType = templateType;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
