package com.ctrip.ibu.flight.internalws.models.message;

import com.ctrip.ibu.flight.internalws.models.common.Trademark;

import java.util.Locale;

/**
 * 获取邮件发送Sender请求
 * */
public class GetEmailSenderRequest {

    /**
     * 语言
     * */
    private Locale locale;

    /**
     * 商标
     * */
    private Trademark trademark;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Trademark getTrademark() {
        return trademark;
    }

    public void setTrademark(Trademark trademark) {
        this.trademark = trademark;
    }
}
