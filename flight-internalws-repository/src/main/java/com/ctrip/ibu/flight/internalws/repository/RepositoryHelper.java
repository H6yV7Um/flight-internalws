package com.ctrip.ibu.flight.internalws.repository;

import com.ctrip.ibu.flight.internalws.models.exception.soa.WSInvokeException;

import java.sql.SQLException;

/**
 * 仓储帮助类
 * Create by kyxie on 2018/4/19 13:54
 */
final class RepositoryHelper {

    private final static String SOA_INVOKER_EXCEPTION_DESC = "调用WebService服务%s异常，异常阶段:s%s，异常描述:%s";

    private final static String DAO_INVOKER_EXCEPTION_DESC = "调用DAO方法%s异常，SQLState：%s，Reason：%s";

    /**
     * 获取SOA异常描述
     * @param wsInvokeEx WSInvoker异常
     * */
    static String getWSExceptionDesc(WSInvokeException wsInvokeEx){
        if (wsInvokeEx != null){
            return String.format(SOA_INVOKER_EXCEPTION_DESC,wsInvokeEx.getInvokeMethodName(),wsInvokeEx.getExceptionStage(),wsInvokeEx.getMessage());
        }
        return "";
    }

    static String getSqlExceptionDesc(SQLException sqlEx){
        return "";
    }
}
