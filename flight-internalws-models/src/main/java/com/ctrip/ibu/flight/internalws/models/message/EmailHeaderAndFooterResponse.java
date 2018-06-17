package com.ctrip.ibu.flight.internalws.models.message;

/**
 * 邮件公共头尾响应
 * Created by kyxie on 2017/8/14.
 */
public class EmailHeaderAndFooterResponse {

    private String emailHeader;

    private String emailFooter;

    private String errorMsg;

    public String getEmailHeader() {
        return emailHeader;
    }

    public void setEmailHeader(String emailHeader) {
        this.emailHeader = emailHeader;
    }

    public String getEmailFooter() {
        return emailFooter;
    }

    public void setEmailFooter(String emailFooter) {
        this.emailFooter = emailFooter;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
