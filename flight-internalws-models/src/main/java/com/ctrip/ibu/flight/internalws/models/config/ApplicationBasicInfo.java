package com.ctrip.ibu.flight.internalws.models.config;

import com.ctrip.ibu.flight.internalws.models.message.EmailSenderModel;

import java.util.List;

/**
 * 应用配置信息
 * Created by kyxie on 2017/7/19.
 */
public class ApplicationBasicInfo {

    private String appId;

    private String jdkVersion;

    //Ctrip版权声明描述格式
    private String ctripCopyrightNoticePattern;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getJdkVersion() {
        return jdkVersion;
    }

    public void setJdkVersion(String jdkVersion) {
        this.jdkVersion = jdkVersion;
    }

    public String getCtripCopyrightNoticePattern() {
        return ctripCopyrightNoticePattern;
    }

    public void setCtripCopyrightNoticePattern(String ctripCopyrightNoticePattern) {
        this.ctripCopyrightNoticePattern = ctripCopyrightNoticePattern;
    }
}
