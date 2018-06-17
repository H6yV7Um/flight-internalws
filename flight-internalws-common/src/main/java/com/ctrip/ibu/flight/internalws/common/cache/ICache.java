package com.ctrip.ibu.flight.internalws.common.cache;

import java.util.Map;

/**
 * Created by kyxie on 2017/8/8.
 */
public interface ICache<K,V> {

    V get(K key);

    void put(K key,V value);

    void putAll(Map<K,V> keyValues);

    void remove(K key);

    long size();

    boolean containsKey(K key);
}
