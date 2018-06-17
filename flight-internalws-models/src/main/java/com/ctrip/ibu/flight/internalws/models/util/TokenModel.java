package com.ctrip.ibu.flight.internalws.models.util;

import com.ctrip.ibu.flight.internalws.models.annotation.GdprInfo;

import java.util.Calendar;
import java.util.List;

/**
 * Created by kyxie on 2017/8/15.
 */
public class TokenModel {

    @GdprInfo(GDPRType.GDPR_USER_EMAIL)
    private String email;

    private String uid;

    private List<Long> orderIds;

    //订单出行时间
    private Calendar orderTravelTime;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Long> orderIds) {
        this.orderIds = orderIds;
    }

    public Calendar getOrderTravelTime() {
        return orderTravelTime;
    }

    public void setOrderTravelTime(Calendar orderTravelTime) {
        this.orderTravelTime = orderTravelTime;
    }
}
