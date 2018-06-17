package com.ctrip.ibu.flight.internalws.models.exception.soa;

/**
 * WebService调用自定义异常
 * Created by kyxie on 2017/7/1.
 */
public class WSInvokeException extends Exception {

    //方法名称
    private String invokeMethodName;

    //异常发生阶段
    private String exceptionStage;

    //异常信息
    private String message;

    public WSInvokeException(String message){
        super(message);
        this.message = message;
    }

    public WSInvokeException(String invokeMethodName, String message){
        super(message);
        this.invokeMethodName = invokeMethodName;
        this.message = message;
    }


    public WSInvokeException(String invokeMethodName,String exceptionStage,String message){
        super(message);
        this.invokeMethodName = invokeMethodName;
        this.exceptionStage = exceptionStage;
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInvokeMethodName() {
        return invokeMethodName;
    }

    public void setInvokeMethodName(String invokeMethodName) {
        this.invokeMethodName = invokeMethodName;
    }

    public String getExceptionStage() {
        return exceptionStage;
    }

    public void setExceptionStage(String exceptionStage) {
        this.exceptionStage = exceptionStage;
    }
}
