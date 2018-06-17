package com.ctrip.ibu.flight.internalws.models.repository.ws;

/**
 * SOA服务接口元信息
 * author : kyxie
 * date : 2018/5/4 14:17
 */
public class WSMethodMetaInfo {

    private String methodName;

    private boolean recordRequestLog;

    private boolean recordResponseLog;

    private String desc;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public boolean isRecordRequestLog() {
        return recordRequestLog;
    }

    public void setRecordRequestLog(boolean recordRequestLog) {
        this.recordRequestLog = recordRequestLog;
    }

    public boolean isRecordResponseLog() {
        return recordResponseLog;
    }

    public void setRecordResponseLog(boolean recordResponseLog) {
        this.recordResponseLog = recordResponseLog;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
