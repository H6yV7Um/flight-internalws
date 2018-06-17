package com.ctrip.ibu.flight.internalws.models.message;

import java.util.Locale;

/**
 * 邮件发送服务器
 * Created by kyxie on 2017/7/17.
 */
public class EmailSenderModel {

    //语言信息
    private Locale localeOrig;

    //语言
    private String locale;

    //发件服务器邮箱
    private String sender;

    //发件服务器名称
    private String senderName;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Locale getLocaleOrig() {
        return localeOrig;
    }

    public void setLocaleOrig(Locale localeOrig) {
        this.localeOrig = localeOrig;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
