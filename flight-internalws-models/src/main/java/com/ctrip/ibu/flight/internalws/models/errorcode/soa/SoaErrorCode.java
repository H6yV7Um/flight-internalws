package com.ctrip.ibu.flight.internalws.models.errorcode.soa;

/**
 * SOA错误码
 * Create by kyxie on 2018/4/11 14:43
 */
public enum SoaErrorCode {

    SUCCESS(0,"成功"),

    PARAM_ILLEGAL(1,"参数不合法"),

    SYSTEM_ERROR(2,"系统错误"),

    FATAL_ERROR(3,"系统严重故障");

    private final Integer errorCode;

    private final String errorMsg;

    SoaErrorCode(Integer errorCode,String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
