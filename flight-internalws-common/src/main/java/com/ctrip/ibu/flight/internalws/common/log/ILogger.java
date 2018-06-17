package com.ctrip.ibu.flight.internalws.common.log;

import com.ctrip.ibu.flight.internalws.models.log.IndexedLogTag;
import com.ctrip.ibu.flight.internalws.models.log.StoredLogTag;

import java.util.Map;

/**
 * Elk log接口
 * Created by kyxie on 2017/7/2.
 */
public interface ILogger {

    /**
     * 记录日志
     * @param indexedLogTags 可索引Tag
     * @param storedLogTags 非索引Tag
     * */
    void logTags(Map<IndexedLogTag,String> indexedLogTags,Map<StoredLogTag,String> storedLogTags);
}
