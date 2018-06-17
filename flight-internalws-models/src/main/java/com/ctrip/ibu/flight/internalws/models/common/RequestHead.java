package com.ctrip.ibu.flight.internalws.models.common;


import com.ctrip.ibu.flight.internalws.contract.commontypes.LanguageType;
import com.ctrip.ibu.flight.internalws.models.flight.Currency;

/**
 * 请求头
 * Created by kyxie on 2017/8/15.
 */
public class RequestHead {

    private String uid;

    private LanguageType language;

    private Currency currency;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public LanguageType getLanguage() {
        return this.language;
    }

    public void setLanguage(LanguageType language) {
        this.language = language;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
