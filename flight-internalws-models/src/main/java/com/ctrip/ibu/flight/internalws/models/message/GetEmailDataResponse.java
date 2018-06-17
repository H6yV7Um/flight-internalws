package com.ctrip.ibu.flight.internalws.models.message;

/**
 * 获取邮件数据响应
 * Created by kyxie on 2017/11/5.
 */
public class GetEmailDataResponse implements IGetMessageDataResponse<EmailData> {

    /**
     * 邮件数据
     * */
    private EmailData emailData;

    /**
     * 邮件配置
     * */
    private EmailSendConfig emailConfig;

    /**
     * 额外信息
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

    @Override
    public EmailData getMessageData() {
        return emailData;
    }
}
