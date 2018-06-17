package com.ctrip.ibu.flight.internalws.models.config;

import java.util.EventObject;

/**
 * 配置变化事件
 * Created by kyxie on 2017/8/9.
 */
public class ConfigChangedEvent<T extends Object> extends EventObject {

    private T changedConfig;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ConfigChangedEvent(Object source,T changedConfig) {
        super(source);
        this.changedConfig = changedConfig;
    }

    public T getChangedConfig() {
        return changedConfig;
    }

}
