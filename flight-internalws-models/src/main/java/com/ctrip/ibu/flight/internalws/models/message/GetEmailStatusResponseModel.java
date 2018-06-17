package com.ctrip.ibu.flight.internalws.models.message;

import java.util.List;

/**
 * Created by kyxie on 2017/8/31.
 */
public class GetEmailStatusResponseModel {

    //服务状态码
    private Integer resultCode;

    //错误描述
    private String resultMsg;

    //查询到的邮件
    private List<EmailModel> emailList;

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

    public List<EmailModel> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<EmailModel> emailList) {
        this.emailList = emailList;
    }
}
