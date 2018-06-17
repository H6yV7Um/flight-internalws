package com.ctrip.ibu.flight.internalws.models.application;

/**
 * 开关信息
 * Created by kyxie on 2017/8/9.
 */
public class SwitchInfo {

    //开关key
    private String switchKey;

    //开关的值
    private Boolean switchValue;

    //开关描述
    private String switchDesc;

    public String getSwitchKey() {
        return switchKey;
    }

    public void setSwitchKey(String switchKey) {
        this.switchKey = switchKey;
    }

    public Boolean getSwitchValue() {
        return switchValue;
    }

    public void setSwitchValue(Boolean switchValue) {
        this.switchValue = switchValue;
    }

    public String getSwitchDesc() {
        return switchDesc;
    }

    public void setSwitchDesc(String switchDesc) {
        this.switchDesc = switchDesc;
    }
}
