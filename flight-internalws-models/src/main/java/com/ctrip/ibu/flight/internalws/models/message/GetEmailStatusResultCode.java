package com.ctrip.ibu.flight.internalws.models.message;

/**
 * Created by kyxie on 2017/9/1.
 */
public enum GetEmailStatusResultCode {

    Success(0),

    ParameterError(1),

    SystemError(2);

    private final Integer value;

    GetEmailStatusResultCode(Integer value){
        this.value = value;
    }

    public Integer getValue(){
        return this.value;
    }
}
