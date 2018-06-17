package com.ctrip.ibu.flight.internalws.models.flight.rebooking;

import com.ctrip.ibu.flight.internalws.models.annotation.GdprInfo;
import com.ctrip.ibu.flight.internalws.models.util.GDPRType;

/**
 * Created by li.l on 2018/2/1.
 */
public class RebookingDetailInfoModel {

    private Long rebookingApplicationID;

    @GdprInfo(GDPRType.GDPR_USER_EMAIL)
    private String emailAddress;

    private boolean isSelfPay;

    public void setRebookingApplicationID(Long rebookingApplicationID) {
        this.rebookingApplicationID = rebookingApplicationID;
    }

    public Long getRebookingApplicationID() {
        return rebookingApplicationID;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isSelfPay() {
        return isSelfPay;
    }

    public void setSelfPay(boolean selfPay) {
        isSelfPay = selfPay;
    }
}
