package com.ctrip.ibu.flight.internalws.models.message;

import java.util.List;

/**
 * 邮件发送实体
 * Create by kyxie on 2018/4/12 16:53
 */
public class EmailSendResponse implements IMessageSendResponse {

    /**
     * 发送结果
     * */
    private Integer sendResultCode;

    /**
     * 结果描述
     * */
    private String sendResultMsg;

    /**
     * EmailId列表
     * */
    private List<String> emailIdList;

    public void setSendResultCode(Integer sendResultCode) {
        this.sendResultCode = sendResultCode;
    }

    public void setSendResultMsg(String sendResultMsg) {
        this.sendResultMsg = sendResultMsg;
    }

    public List<String> getEmailIdList() {
        return emailIdList;
    }

    public void setEmailIdList(List<String> emailIdList) {
        this.emailIdList = emailIdList;
    }

    /**
     * 获取发送结果
     */
    @Override
    public Integer getSendResultCode() {
        return sendResultCode;
    }

    /**
     * 获取发送结果描述
     */
    @Override
    public String getSendResultMsg() {
        return sendResultMsg;
    }
}
