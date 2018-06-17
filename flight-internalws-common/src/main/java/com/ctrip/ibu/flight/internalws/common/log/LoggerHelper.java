package com.ctrip.ibu.flight.internalws.common.log;

import com.ctrip.ibu.flight.internalws.common.components.HttpContext;
import com.ctrip.ibu.flight.internalws.models.log.IndexedLogTag;
import com.ctrip.ibu.flight.internalws.models.log.StoredLogTag;

/**
 * 日志帮助类（简化日志记录）
 * Create by kyxie on 2018/4/10 12:06
 */
public final class LoggerHelper {

    private final static ILogger SIMPLE_LOGGER = new SimpleLogger();

    /**
     * 新增一条Indexed Tag
     * @param logTag tag名称
     * @param tagVal tag值
     * */
    public static void addIndexedLogTag(IndexedLogTag logTag, String tagVal){
        HttpContext.getLogContext().addIndexedTag(logTag,tagVal);
    }

    /**
     * 新增一条Stored Tag
     * @param logTag tag名称
     * @param tagVal tag值
     * */
    public static void addStoredLogTag(StoredLogTag logTag, String tagVal){
        HttpContext.getLogContext().addStoredTag(logTag,tagVal);
    }

    /**
     * 追加请求Request文本
     * @param requestContent 请求文本信息
     * */
    public static void appendRequestContent(String requestContent){
        HttpContext.getLogContext().appendRequestContent(requestContent);
    }

    /**
     * 追加响应Response文本
     * @param responseContent 响应文本信息
     * */
    public static void appendResponseContent(String responseContent){
        HttpContext.getLogContext().appendResponseContent(responseContent);
    }

    /**
     * 记录日志
     * */
    public static void doLog(){
        SIMPLE_LOGGER.logTags(HttpContext.getLogContext().getIndexedLogTags(),HttpContext.getLogContext().getStoredLogTags());
    }
}
