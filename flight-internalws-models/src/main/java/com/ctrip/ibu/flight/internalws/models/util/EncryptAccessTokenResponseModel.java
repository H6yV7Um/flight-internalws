package com.ctrip.ibu.flight.internalws.models.util;

/**
 * 加密AccessToken响应
 * Created by kyxie on 2017/8/15.
 */
public class EncryptAccessTokenResponseModel {

    private String returnCode;

    private String encryptToken;

    private String errorMsg;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getEncryptToken() {
        return encryptToken;
    }

    public void setEncryptToken(String encryptToken) {
        this.encryptToken = encryptToken;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
