package com.ctrip.ibu.flight.internalws.models.config;

/**
 * Guava 配置
 * Created by kyxie on 2017/8/9.
 */
public class GuavaConfigInfo {

    private Long cacheExpireTime;

    private Long cacheMaxSize;

    public Long getCacheExpireTime() {
        return cacheExpireTime;
    }

    public void setCacheExpireTime(Long cacheExpireTime) {
        this.cacheExpireTime = cacheExpireTime;
    }

    public Long getCacheMaxSize() {
        return cacheMaxSize;
    }

    public void setCacheMaxSize(Long cacheMaxSize) {
        this.cacheMaxSize = cacheMaxSize;
    }
}
