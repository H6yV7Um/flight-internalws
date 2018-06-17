package com.ctrip.ibu.flight.internalws.models.message;

import java.util.List;

/**
 * 获取邮件发送状态请求
 * Created by kyxie on 2017/8/31.
 */
public class GetEmailStatusRequestModel {

    //EmailID列表
    private List<String> emailIdList;

    //邮件发送通道ID
    private String sendCode;

    public List<String> getEmailIdList() {
        return emailIdList;
    }

    public void setEmailIdList(List<String> emailIdList) {
        this.emailIdList = emailIdList;
    }

    public String getSendCode() {
        return sendCode;
    }

    public void setSendCode(String sendCode) {
        this.sendCode = sendCode;
    }
}
