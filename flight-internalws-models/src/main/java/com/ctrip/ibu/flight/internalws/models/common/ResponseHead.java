package com.ctrip.ibu.flight.internalws.models.common;

/**
 * 公共响应头
 * Created by kyxie on 2017/10/30.
 */
public class ResponseHead {

    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
