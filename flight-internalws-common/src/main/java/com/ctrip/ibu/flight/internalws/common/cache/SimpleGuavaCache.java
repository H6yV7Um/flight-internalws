package com.ctrip.ibu.flight.internalws.common.cache;

import com.ctrip.framework.foundation.Foundation;
import com.google.common.cache.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * GuavaCache简单实现
 * Created by kyxie on 2017/8/8.
 */
@Component("guavaCache")
public class SimpleGuavaCache<K,V> implements ICache<K,V>{

    private final Long DEFAULT_GUAVACACHE_EXPIRETIME = Long.parseLong("30");

    private final Long DEFAULT_GUAVACACHE_MAXSIZE = Long.parseLong("1000");

    private Cache<K,V> cache;

    public SimpleGuavaCache(){
        this.cache = CacheBuilder.newBuilder()
                .expireAfterWrite(Foundation.app().getLongProperty("simpleGuavaCache_ExpireTime",DEFAULT_GUAVACACHE_EXPIRETIME),TimeUnit.MINUTES)
                .maximumSize(Foundation.app().getLongProperty("simpleGuavaCache_MaxSize",DEFAULT_GUAVACACHE_MAXSIZE))
                .recordStats()//开启缓存统计功能
                .build();

        List<String> list = new ArrayList<>();
    }

    @Override
    public V get(K key) {
        return this.cache.getIfPresent(key);
    }

    @Override
    public void put(K key, V value) {
        this.cache.put(key,value);
    }

    @Override
    public void putAll(Map<K, V> keyValues) {
        this.cache.putAll(keyValues);
    }

    @Override
    public void remove(K key) {
        this.cache.invalidate(key);
    }

    @Override
    public long size() {
        return this.cache.size();
    }

    @Override
    public boolean containsKey(K key) {
        return this.cache.getIfPresent(key) != null;
    }
}