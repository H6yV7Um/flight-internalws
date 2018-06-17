package com.ctrip.ibu.flight.internalws.models.application;

import com.ctrip.ibu.flight.internalws.models.config.ApplicationBasicInfo;
import com.ctrip.ibu.flight.internalws.models.config.ElkLogConfigInfo;
import com.ctrip.ibu.flight.internalws.models.config.EmailConfigInfo;
import com.ctrip.ibu.flight.internalws.models.config.GuavaConfigInfo;

import java.util.List;
import java.util.Map;

/**
 * 应用信息
 * Created by kyxie on 2017/7/10.
 */
public class ApplicationInfo {

    //应用的配置信息
    private ApplicationBasicInfo applicationBasicInfo;

    //邮件配置信息
    private EmailConfigInfo emailConfigInfo;

    //ElkLog配置信息
    private ElkLogConfigInfo elkLogConfigInfo;

    //Guava配置信息
    private GuavaConfigInfo guavaConfigInfo;

    //开关配置
    private List<SwitchInfo> switchConfigInfo;

    public ApplicationBasicInfo getApplicationBasicInfo() {
        return applicationBasicInfo;
    }

    public void setApplicationBasicInfo(ApplicationBasicInfo applicationBasicInfo) {
        this.applicationBasicInfo = applicationBasicInfo;
    }

    public EmailConfigInfo getEmailConfigInfo() {
        return emailConfigInfo;
    }

    public void setEmailConfigInfo(EmailConfigInfo emailConfigInfo) {
        this.emailConfigInfo = emailConfigInfo;
    }

    public ElkLogConfigInfo getElkLogConfigInfo() {
        return elkLogConfigInfo;
    }

    public void setElkLogConfigInfo(ElkLogConfigInfo elkLogConfigInfo) {
        this.elkLogConfigInfo = elkLogConfigInfo;
    }

    public GuavaConfigInfo getGuavaConfigInfo() {
        return guavaConfigInfo;
    }

    public void setGuavaConfigInfo(GuavaConfigInfo guavaConfigInfo) {
        this.guavaConfigInfo = guavaConfigInfo;
    }

    public List<SwitchInfo> getSwitchConfigInfo() {
        return switchConfigInfo;
    }

    public void setSwitchConfigInfo(List<SwitchInfo> switchConfigInfo) {
        this.switchConfigInfo = switchConfigInfo;
    }
}
