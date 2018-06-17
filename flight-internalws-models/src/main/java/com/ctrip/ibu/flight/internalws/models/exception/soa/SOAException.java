package com.ctrip.ibu.flight.internalws.models.exception.soa;

/**
 * SOA服务异常
 * Created by kyxie on 2017/7/20.
 */
public class SOAException extends Exception {

    private String soaServiceName;

    private String errorCode;

    private String message;

    private Throwable throwable;

    public SOAException(String soaServiceName,String message){
        super(message);
        this.soaServiceName = soaServiceName;
        this.message = message;
    }

    public SOAException(String soaServiceName,String message,Throwable throwable){
        super(message);
        this.soaServiceName = soaServiceName;
        this.message = message;
        this.throwable = throwable;
    }

    public String getSoaServiceName() {
        return soaServiceName;
    }

    public void setSoaServiceName(String soaServiceName) {
        this.soaServiceName = soaServiceName;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
