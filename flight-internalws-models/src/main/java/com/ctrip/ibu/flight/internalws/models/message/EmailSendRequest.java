package com.ctrip.ibu.flight.internalws.models.message;

/**
 * 邮件数据实体
 * Created by zhangrm on 2018/1/12.
 */
public class EmailSendRequest implements IMessageSendRequest {

    /**
     * 邮件数据
     * */
    private EmailData emailData;

    /**
     * 邮件配置
     * */
    private EmailSendConfig emailConfig;

    /**
     * 邮件发送额外信息
     * */
    private EmailSendAdditionalInfo additionalInfo;

    public EmailData getEmailData() {
        return emailData;
    }

    public void setEmailData(EmailData emailData) {
        this.emailData = emailData;
    }

    public EmailSendConfig getEmailConfig() {
        return emailConfig;
    }

    public void setEmailConfig(EmailSendConfig emailConfig) {
        this.emailConfig = emailConfig;
    }

    public EmailSendAdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(EmailSendAdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
