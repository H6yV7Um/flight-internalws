package com.ctrip.ibu.flight.internalws.models.message;

import com.ctrip.ibu.flight.internalws.models.common.CorpGroup;
import com.ctrip.ibu.flight.internalws.models.common.Trademark;
import com.ctrip.ibu.flight.internalws.contract.commontypes.LanguageType;

import java.util.Calendar;

/**
 * 邮件发送配置实体
 * Create by kyxie on 2018/4/13 15:53
 */
public class EmailSendConfig {

    /**
     * 邮件发送语言
     * */
    private LanguageType emailSendLan;

    /**
     * 订单是SC还是Ctrip
     * */
    private CorpGroup orderCorpGroup;

    /**
     * 订单属于Trip还是Ctrip
     * */
    private Trademark orderTrademark;

    private Boolean isBodyHtml;

    private String operator;

    private Calendar expiredTime;

    private Calendar scheduleTime;

    public LanguageType getEmailSendLan() {
        return emailSendLan;
    }

    public void setEmailSendLan(LanguageType emailSendLan) {
        this.emailSendLan = emailSendLan;
    }

    public CorpGroup getOrderCorpGroup() {
        return orderCorpGroup;
    }

    public void setOrderCorpGroup(CorpGroup orderCorpGroup) {
        this.orderCorpGroup = orderCorpGroup;
    }

    public Trademark getOrderTrademark() {
        return orderTrademark;
    }

    public void setOrderTrademark(Trademark orderTrademark) {
        this.orderTrademark = orderTrademark;
    }

    public Boolean getBodyHtml() {
        return isBodyHtml;
    }

    public void setBodyHtml(Boolean bodyHtml) {
        isBodyHtml = bodyHtml;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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
}
