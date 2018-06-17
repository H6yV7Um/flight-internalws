package com.ctrip.ibu.flight.internalws.common.log;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.models.constant.LogConst;
import com.ctrip.ibu.flight.internalws.models.log.IndexedLogTag;
import com.ctrip.ibu.flight.internalws.models.log.StoredLogTag;
import com.dianping.cat.Cat;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单日志记录器实现
 * Create by kyxie on 2018/4/10 11:58
 */
public class SimpleLogger implements ILogger {

    private final static ILog CLOG = LogManager.getLogger(SimpleLogger.class);

    private final static String LOG_MSG_SEPARATOR = "******************************";

    /**
     * 记录日志
     *
     * @param indexedLogTags 可索引Tag
     * @param storedLogTags  非索引Tag
     */
    @Override
    public void logTags(Map<IndexedLogTag, String> indexedLogTags, Map<StoredLogTag, String> storedLogTags) {
        Map<String,String> indexedTagsStr = new HashMap<>();
        Map<String,String> storedTagsStr = new HashMap<>();

        if (indexedLogTags != null && !indexedLogTags.isEmpty()){
            for (Map.Entry<IndexedLogTag,String> entry : indexedLogTags.entrySet()){
                indexedTagsStr.put(entry.getKey().toString().toLowerCase(),entry.getValue());
            }
        }

        if (storedLogTags != null && !storedLogTags.isEmpty()){
            for (Map.Entry<StoredLogTag,String> entry : storedLogTags.entrySet()){
                storedTagsStr.put(entry.getKey().toString().toLowerCase(),entry.getValue());
            }
        }

        //记录Clog
        CLOG.info(LogConst.Clog.LogTitle.APP_LOG, formatLogMsg(storedTagsStr),indexedTagsStr);

        //记录ES
        Cat.logTags(LogConst.Elk.SCENRIO,indexedTagsStr,storedTagsStr);
    }

    /**
     * 格式化日志
     * @param storedLogTags 非索引日志Tag
     * */
    private String formatLogMsg(Map<String,String> storedLogTags){
        StringBuilder logMsg = new StringBuilder();

        if (storedLogTags != null && !storedLogTags.isEmpty()){
            for (Map.Entry<String,String> entry : storedLogTags.entrySet()){
                logMsg.append(String.format("%s\n%s:\n%s\n", LOG_MSG_SEPARATOR,entry.getKey(),entry.getValue()));
            }
        }

        return logMsg.toString();
    }
}
