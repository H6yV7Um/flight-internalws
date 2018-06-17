package com.ctrip.ibu.flight.internalws.models.exception.repository;

import com.ctrip.ibu.flight.internalws.models.errorcode.repository.WSErrorSource;

/**
 * 仓储层自定义异常，可以包装DAO与WS异常，完成封装
 * Created by kyxie on 2017/7/8.
 */
public class RepositoryException extends Exception {

    //错误来源，WS还是DB
    private WSErrorSource errorSource;

    private String message;

    public RepositoryException(WSErrorSource errorSource,String message){
        super(message);
        this.errorSource = errorSource;
        this.message = message;
    }

    public WSErrorSource getErrorSource() {
        return errorSource;
    }

    public void setErrorSource(WSErrorSource errorSource) {
        this.errorSource = errorSource;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
