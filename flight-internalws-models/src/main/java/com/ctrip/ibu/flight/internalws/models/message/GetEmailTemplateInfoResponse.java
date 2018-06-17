package com.ctrip.ibu.flight.internalws.models.message;

import java.util.Map;

/**
 * 邮件模板Response
 * Created by kyxie on 2017/11/2.
 */
public class GetEmailTemplateInfoResponse {

    private String emailTemplateName;

    private Map<String, Object> emailData;

    public String getEmailTemplateName() {
        return emailTemplateName;
    }

    public void setEmailTemplateName(String emailTemplateName) {
        this.emailTemplateName = emailTemplateName;
    }

    public Map<String, Object> getEmailData() {
        return emailData;
    }

    public void setEmailData(Map<String, Object> emailData) {
        this.emailData = emailData;
    }
}
