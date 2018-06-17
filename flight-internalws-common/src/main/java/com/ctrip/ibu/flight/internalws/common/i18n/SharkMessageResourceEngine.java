package com.ctrip.ibu.flight.internalws.common.i18n;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.common.log.LoggerHelper;
import com.ctrip.ibu.flight.internalws.models.common.Trademark;
import com.ctrip.ibu.flight.internalws.models.constant.BeanConst;
import com.ctrip.ibu.flight.internalws.models.constant.LogConst;
import com.ctrip.ibu.platform.shark.sdk.api.Shark;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Shark多语言引擎
 * Create by kyxie on 2018/1/22 15:58
 */
@Component(BeanConst.BEANNAME_SHARKMESSAGERESOURCEENGINE)
public class SharkMessageResourceEngine extends ResourceBundleMessageSource implements IMessageResource {

    private static final ILog LOGGER = LogManager.getLogger(SharkMessageResourceEngine.class);

    private static final String BASENAME = "i18n.message";
    private static final String DEFAULT_ENCODING = "UTF-8";

    //英文保底多语言
    private ResourceBundle defaultResourceBundle;

    public SharkMessageResourceEngine(){
        super.setBasename(BASENAME);
        super.setDefaultEncoding(DEFAULT_ENCODING);
    }

    @PostConstruct
    private void init(){
        defaultResourceBundle = super.getResourceBundle(BASENAME,Locale.US);
    }

    /**
     * 根据Locale获取多语言
     *
     * @param locale    语言
     * @param trademark 商标
     */
    @Override
    public Map<String, String> getResourceBundle(Locale locale, Trademark trademark) {
        return null;
    }

    /**
     * 获取单个字符的多语言
     *
     * @param messageKey 多语言Key
     * @param locale     指定语言
     */
    @Override
    public String getResource(String messageKey, Locale locale) {
        if (StringUtils.isEmpty(messageKey)){
            return "";
        }

        Map<String,String> logTags = new HashMap<>();
        logTags.put(LogConst.LOGTAG_CLASSNAME,"SharkMessageResourceEngine");
        logTags.put(LogConst.LOGTAG_METHODNAME,"getResource");

        try {
            return Shark.get(messageKey,parseLocale(locale),getDefaultValue(messageKey));
        } catch (Exception e){
            LOGGER.error(LogConst.CLOGTITLE_SYSTEMMONITOR,e,logTags);
        }
        return "";
    }

    /**
     * 获取指定Key的所以多语言
     *
     * @param messageKey 多语言Key
     */
    @Override
    public Map<Locale, String> getResourceBundleByMessageKey(String messageKey) {
        return null;
    }

    /**
     * 获取Key的英文保底话述
     * */
    private String getDefaultValue(String key){
        String defaultVal = "";
        if (StringUtils.isBlank(key)){
            return defaultVal;
        }

        Map<String,String> logTags = new HashMap<>();
        logTags.put(LogConst.LOGTAG_CLASSNAME,"SharkMessageResourceEngine");
        logTags.put(LogConst.LOGTAG_METHODNAME,"getDefaultValue");

        try {
            defaultVal = String.valueOf(defaultResourceBundle.getObject(key));
        } catch (Exception e){
            LOGGER.warn(LogConst.CLOGTITLE_SYSTEMMONITOR,e,logTags);
            LoggerHelper.appendResponseContent(String.format("模板Key：%s在本地未配置,请Review本地保底英文配置并建议增加配置",key));
        }

        return defaultVal;
    }

    /**
     * 将Locale解析为字符串，处理in_ID的问题
     * @param locale 原始的Locale
     * */
    private String parseLocale(Locale locale){
        if (locale == null){
            locale = Locale.US;
        }
        String localeStr = String.valueOf(locale);
        if ("in_ID".equalsIgnoreCase(localeStr)){
            localeStr = "id_ID";
        }
        return localeStr;
    }
}
