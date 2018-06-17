package com.ctrip.ibu.flight.internalws.common.log;

import com.ctrip.ibu.flight.internalws.models.log.IndexedLogTag;
import com.ctrip.ibu.flight.internalws.models.log.StoredLogTag;

import java.util.HashMap;
import java.util.Map;

/**
 * 日志上下文(非线程安全)
 * Create by kyxie on 2018/4/10 11:46
 */
public class LogContext {

    private final static String CONTENT_DELIMITER = "\n";

    private String logContentDelimiter;

    private LogContext(String logContentDelimiter){
        this.logContentDelimiter = logContentDelimiter;
    }

    public static class LogContextBuilder {

        private String contentDelimiter;

        private LogContextBuilder(String logContentDelimiter){
            if (logContentDelimiter != null){
                this.contentDelimiter = logContentDelimiter;
            }
        }

        public static LogContextBuilder createBuilder(){
            return new LogContextBuilder(CONTENT_DELIMITER);
        }

        /**
         * 创建一个LogContextBuilder
         * @param logContentDelimiter 日志非索引字段的分隔符
         * */
        public static LogContextBuilder createBuilder(String logContentDelimiter){
            return new LogContextBuilder(logContentDelimiter);
        }

        /**
         * 创建一个LogContext实例
         * */
        public LogContext build(){
            return new LogContext(contentDelimiter);
        }
    }

    //请求
    private StringBuilder requestContentSB = new StringBuilder();

    //响应
    private StringBuilder responseContentSB = new StringBuilder();

    //索引Tag
    private Map<IndexedLogTag,String> indexedLogTags = new HashMap<>();

    //非索引Tag
    private Map<StoredLogTag,String> storedLogTags = new HashMap<>();

    /**
     * 追加请求文本
     * @param requestContent 请求文本
     * */
    public void appendRequestContent(String requestContent){
        requestContentSB.append(String.format("%s\n%s\n",requestContent,logContentDelimiter));
    }

    /**
     * 追加响应文本
     * @param responseContent 响应文本
     * */
    public void appendResponseContent(String responseContent){
        responseContentSB.append(String.format("%s\n%s\n",responseContent,logContentDelimiter));
    }

    public void addIndexedTag(IndexedLogTag tagKey, String tagValue){
        if (tagValue == null)tagValue = "";
        indexedLogTags.put(tagKey,tagValue);
    }

    public void addStoredTag(StoredLogTag tagKey, String tagValue){
        if (tagValue == null)tagValue = "";

        if (tagKey == StoredLogTag.RequestContent){
            requestContentSB.append(String.format("%s\n%s\n",tagValue,logContentDelimiter));
        }else if (tagKey == StoredLogTag.ResponseContent){
            responseContentSB.append(String.format("%s\n%s\n",tagValue,logContentDelimiter));
        }else {
            storedLogTags.put(tagKey,tagValue);
        }
    }

    /**
     * 获取索引Tag
     * */
    public Map<IndexedLogTag,String> getIndexedLogTags(){
        return indexedLogTags;
    }

    /**
     * 获取非索引Tag
     * */
    public Map<StoredLogTag,String> getStoredLogTags(){
        String requestContentKey = StoredLogTag.RequestContent.toString();
        String responseContentKey = StoredLogTag.ResponseContent.toString();
        if (storedLogTags.containsKey(StoredLogTag.RequestContent)){
            for (Map.Entry<StoredLogTag,String> entry : storedLogTags.entrySet()){
                if (requestContentKey.equalsIgnoreCase(entry.getKey().toString())){
                    entry.setValue(entry.getValue().concat("\n" + requestContentSB.toString()));
                }
            }
        }else {
            storedLogTags.put(StoredLogTag.RequestContent,requestContentSB.toString());
        }

        if (storedLogTags.containsKey(StoredLogTag.ResponseContent)){
            for (Map.Entry<StoredLogTag,String> entry : storedLogTags.entrySet()){
                if (responseContentKey.equalsIgnoreCase(entry.getKey().toString())){
                    entry.setValue(entry.getValue().concat("\n" + responseContentSB.toString()));
                }
            }
        }else {
            storedLogTags.put(StoredLogTag.ResponseContent,responseContentSB.toString());
        }

        return storedLogTags;
    }
}
