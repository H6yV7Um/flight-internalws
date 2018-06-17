package com.ctrip.ibu.flight.internalws.models.message;

import com.ctrip.ibu.flight.internalws.models.common.Trademark;
import com.ctrip.ibu.flight.internalws.models.flight.FlightClass;

import java.util.Locale;

/**
 * 邮件头尾请求
 * Created by kyxie on 2017/8/14.
 */
public class EmailHeaderAndFooterRequest {

    private Long orderId;

    private String emailType;

    private FlightClass flightClass;

    private Locale locale;

    private Trademark trademark;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public FlightClass getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClass flightClass) {
        this.flightClass = flightClass;
    }

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
