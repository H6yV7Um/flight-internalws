package com.ctrip.ibu.flight.internalws.common.config;

import java.util.EventObject;
import java.util.Map;

/**
 * QConfig配置文件改变事件
 * Created by kyxie on 2017/7/22.
 */
public class PropertiesFileChangedEvent extends EventObject {

    //Key:文件名，Value：文件内容
    private Map<String,Map<String,String>> changedConfig;

    public PropertiesFileChangedEvent(Object source,Map<String,Map<String,String>> changedConfig){
        super(source);
        this.changedConfig = changedConfig;
    }

    public Map<String, Map<String, String>> getChangedConfig() {
        return changedConfig;
    }

    public void setChangedConfig(Map<String, Map<String, String>> changedConfig) {
        this.changedConfig = changedConfig;
    }
}
