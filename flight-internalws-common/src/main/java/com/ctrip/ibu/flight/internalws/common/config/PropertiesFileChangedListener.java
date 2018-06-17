package com.ctrip.ibu.flight.internalws.common.config;

import java.util.EventListener;

/**
 * QConfig属性文件监听器
 * Created by kyxie on 2017/7/22.
 */
public interface PropertiesFileChangedListener extends EventListener {

    /**
     * 配置文件改变时的监听器
     * @param configChangedEvent 配置更改事件
     * */
    void onConfigFileChanged(PropertiesFileChangedEvent configChangedEvent);

}
