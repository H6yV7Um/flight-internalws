package com.ctrip.ibu.flight.internalws.models.message;

import java.util.List;

/**
 * 邮件发送响应实体
 * Created by kyxie on 2017/7/7.
 */
public class EmailSendResponseDO {

    //发送结果代码，0：成功，其他值：失败
    private Integer resultCode;

    //发送结果描述
    private String resultMsg;

    //email id列表
    private List<String> emailIdList;

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public List<String> getEmailIdList() {
        return emailIdList;
    }

    public void setEmailIdList(List<String> emailIdList) {
        this.emailIdList = emailIdList;
    }

}
