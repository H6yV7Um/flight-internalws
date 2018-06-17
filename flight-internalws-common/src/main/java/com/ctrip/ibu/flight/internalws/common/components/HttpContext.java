package com.ctrip.ibu.flight.internalws.common.components;

import com.ctrip.ibu.flight.internalws.common.log.LogContext;
import com.ctrip.ibu.flight.internalws.contract.commontypes.LanguageType;
import com.ctriposs.baiji.specific.SpecificRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Http上下文
 * Create by kyxie on 2018/1/18 12:05
 */
public final class HttpContext {

    private static final int CONTEXT_DEFAULT_SIZE = 1 << 6;

    //当前线程语言
    private static final String CURRENT_LANGUAGE = "CURRENT_LANGUAGE";

    //邮件发送语言
    private static final String EMAIL_SEND_LANGUAGE = "EMAILSEND_LANGUAGE";

    //日志上下文
    private static final String LOG_CONTEXT = "LOG_CONTEXT";

    private static final LanguageType DEFAULT_LANGUAGE = LanguageType.en_US;

    private static final ThreadLocal<Map<String,Object>> CONTEXT = ThreadLocal.withInitial(() -> new HashMap<>(CONTEXT_DEFAULT_SIZE));

    static {
        add(CURRENT_LANGUAGE,DEFAULT_LANGUAGE);
        add(EMAIL_SEND_LANGUAGE,DEFAULT_LANGUAGE);
    }

    public static <T> void add(String key,T value){
        CONTEXT.get().put(key,value);
    }

    public static <T> T get(String key){
        return (T) CONTEXT.get().get(key);
    }

    public static void remove(String key){
        CONTEXT.get().remove(key);
    }

    public static void clear(){
        CONTEXT.get().clear();
    }

    /**
     * 设置上下文语言
     * @param language 要设置的语言
     * */
    public static void setContextLanguage(LanguageType language){
        add(CURRENT_LANGUAGE,language);
    }

    /**
     * 设置邮件发送语言
     * @param language 邮件发送语言
     * */
    public static void setEmailsendLanguage(LanguageType language){
        add(EMAIL_SEND_LANGUAGE,language);
    }

    /**
     * 获取上下文语言
     * */
    public static LanguageType getContextLanguage(){
        return get(CURRENT_LANGUAGE);
    }

    public static LanguageType getEmailSendLanguage(){
        return get(EMAIL_SEND_LANGUAGE);
    }

    public static void update(String key, Object value) {
        CONTEXT.get().put(key, value);
    }

    /**
     * 获取当前线程日志上下文
     * */
    public static LogContext getLogContext(){
        if (!CONTEXT.get().containsKey(LOG_CONTEXT)){
            add(LOG_CONTEXT,LogContext.LogContextBuilder.createBuilder().build());
        }
        return (LogContext) CONTEXT.get().get(LOG_CONTEXT);
    }
}
