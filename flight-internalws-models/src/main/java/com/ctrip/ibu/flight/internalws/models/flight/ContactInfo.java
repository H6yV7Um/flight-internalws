package com.ctrip.ibu.flight.internalws.models.flight;

import com.ctrip.ibu.flight.internalws.models.annotation.GdprInfo;
import com.ctrip.ibu.flight.internalws.models.util.GDPRType;

/**
 * 联系人信息
 * Created by kyxie on 2017/8/16.
 */
public class ContactInfo {

    //联系人姓名
    @GdprInfo(GDPRType.GDPR_CONTACT_NAME)
    private String name;

    @GdprInfo(GDPRType.GDPR_CONTACT_MOBILEPHONE)
    //联系手机号
    private String phone;

    //区号
    private String phoneAreaCode;

    @GdprInfo(GDPRType.GDPR_CONTACT_EMAIL)
    //邮箱地址
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneAreaCode() {
        return phoneAreaCode;
    }

    public void setPhoneAreaCode(String phoneAreaCode) {
        this.phoneAreaCode = phoneAreaCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
