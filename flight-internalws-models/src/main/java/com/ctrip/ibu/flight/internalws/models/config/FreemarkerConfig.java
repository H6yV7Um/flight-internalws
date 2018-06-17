package com.ctrip.ibu.flight.internalws.models.config;

import java.util.Locale;

/**
 * Freemarker相关配置
 * Create by kyxie on 2018/1/27 15:16
 */
public class FreemarkerConfig {

    /**
     * Locale相关，会影响时间等的展示
     * */
    private Locale freemarkerLocale;

    public Locale getFreemarkerLocale() {
        return freemarkerLocale;
    }

    public void setFreemarkerLocale(Locale freemarkerLocale) {
        this.freemarkerLocale = freemarkerLocale;
    }

}
