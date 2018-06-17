package com.ctrip.ibu.flight.internalws.models.log;

/**
 * 非索引Elk log tag
 * Created by kyxie on 2017/7/12.
 */
public enum StoredLogTag {

    //方法开始时间
    MethodStartTime,

    //方法结束时间
    MethodEndTime,

    //方法执行时间
    MethodElapsedTimeDesc,

    //执行时长
    Interval,

    //异常信息
    ExceptionMsg,

    //请求
    RequestContent,

    //响应
    ResponseContent
}
