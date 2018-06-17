package com.ctrip.ibu.flight.internalws.common.i18n;

import com.ctrip.ibu.flight.internalws.common.config.Config;
import com.ctrip.ibu.flight.internalws.common.config.PropertiesFileChangedListener;
import com.ctrip.ibu.flight.internalws.models.common.Trademark;
import com.ctrip.ibu.flight.internalws.common.config.PropertiesFileChangedEvent;
import com.ctrip.ibu.flight.internalws.models.constant.BeanConst;
import com.ctrip.ibu.flight.internalws.models.constant.BusinessConst;
import com.ctrip.ibu.flight.internalws.models.constant.MessageResourceConst;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * QConfig远端多语言引擎
 * Created by kyxie on 2017/7/24.
 */
@Component(BeanConst.BEANNAME_QCONFIGMESSAGERESOURCEENGINE)
public class QConfigMessageResourceEngine implements IMessageResource {

    private static final String MESSAGERESOURCECACHEKEY_PREFIX = "message_";
    private static final String MESSAGERESOURCECACHEKEY_SUFFIX = ".properties";
    private static final String MESSAGE_KEY_FORMAT = "%s_%s";//key格式，语言_国家

    //多语言缓存
    private Map<String,Map<String,String>> messageResourceCache = new ConcurrentHashMap<>();

    private Config config;

    private List<String> cachedMessageResourceNameList;

    @Inject
    public QConfigMessageResourceEngine(@Named("config") Config config){
        this.config = config;
        this.cachedMessageResourceNameList = config.getApplicationInfo().getEmailConfigInfo().getCachedMessageResourcesNameList();

        //初始化多语言缓存
        initMessageResourceCache();

        this.config.addEventListener(new PropertiesFileChangedListener() {
            @Override
            public void onConfigFileChanged(PropertiesFileChangedEvent configChangedEvent) {
                Map<String,Map<String,String>> changedConfig = configChangedEvent.getChangedConfig();
                updateMessageResource(changedConfig);
            }
        });
    }

    /**
     * 初始化多语言缓存
     * */
    private synchronized void initMessageResourceCache(){

        if (this.cachedMessageResourceNameList != null && !this.cachedMessageResourceNameList.isEmpty()){
            for (String messageResourceName : this.cachedMessageResourceNameList){
                messageResourceName = messageResourceName.toLowerCase();
                Map<String,String> messageResource = config.getPropertiesConfig(String.format("%s%s%s",MESSAGERESOURCECACHEKEY_PREFIX,messageResourceName,MESSAGERESOURCECACHEKEY_SUFFIX).toLowerCase());

                if (this.messageResourceCache.containsKey(messageResourceName)){
                    this.messageResourceCache.remove(messageResourceName);
                }
                //不为NULL时才加入缓存
                if (messageResource != null){
                    this.messageResourceCache.put(messageResourceName,messageResource);
                }
            }
        }
    }

    /**
     * 更新多语言缓存
     * */
    private synchronized void updateMessageResource(Map<String,Map<String,String>> newestMessageResource){
        if (newestMessageResource != null && !newestMessageResource.isEmpty()){

            for (Map.Entry<String,Map<String,String>> entry : newestMessageResource.entrySet()){
                //获取Cache Key
                String cacheKey = getMessageResourceCacheKey(entry.getKey());
                Map<String,String> newestValue = entry.getValue();

                if (this.cachedMessageResourceNameList != null && !this.cachedMessageResourceNameList.isEmpty()){
                    //只更新指定文件名的模板信息
                    if (this.cachedMessageResourceNameList.contains(cacheKey)){

                        if (this.messageResourceCache.containsKey(cacheKey)){
                            this.messageResourceCache.remove(cacheKey);
                        }
                        if (newestValue != null){
                            this.messageResourceCache.put(cacheKey,entry.getValue());
                        }
                    }
                }
            }
        }
    }

    /**
     * 根据多语言配置文件名获取Cache Key
     * @param configFileName 配置文件名
     * */
    private String getMessageResourceCacheKey(String configFileName){
        if (configFileName != null){
            return configFileName.replace(MESSAGERESOURCECACHEKEY_PREFIX,"").replace(MESSAGERESOURCECACHEKEY_SUFFIX,"").toLowerCase();
        }

        return null;
    }

    /**
     * 根据Locale获取多语言
     *
     * @param locale 语言
     */
    @Override
    public Map<String, String> getResourceBundle(Locale locale, Trademark trademark) {

        if (locale == null) {
            return null;
        }

        Map<String,String> resourceBundle = new HashMap<>();

        Map<String,String> defaultMessageResource = messageResourceCache.get(String.format(MESSAGE_KEY_FORMAT,"en","us"));

        if (locale != null){
            String cacheKey = String.format(MESSAGE_KEY_FORMAT,locale.getLanguage(),locale.getCountry()).toLowerCase();

            //赋值HashMap
            Map<String,String> curLocaleMessageResource = messageResourceCache.get(cacheKey);
            if (curLocaleMessageResource != null && !curLocaleMessageResource.isEmpty()){
                Set<String> keySet = curLocaleMessageResource.keySet();
                for (String key : keySet){
                    resourceBundle.put(key, filterResource(curLocaleMessageResource.get(key), trademark));
                }
            }

            //使用en_US填充不存在的Key
            if (defaultMessageResource != null && !defaultMessageResource.isEmpty()){

                //赋值英文
                for (Map.Entry<String,String> entry : defaultMessageResource.entrySet()){
                    resourceBundle.putIfAbsent(entry.getKey(), filterResource(entry.getValue(), trademark));
                }
            }
        }

        return resourceBundle;
    }

    /**
     * 过滤多语言话述,替换占位符
     * */
    private String filterResource(String resVal, Trademark trademark) {
        if (resVal == null || resVal.isEmpty()){
            return resVal;
        }
        switch (trademark) {
            case Trip:
                return resVal.replace(MessageResourceConst.MRPLACEHOLDER_CORPGROUP, BusinessConst.TRADEMARK_TRIP);
            default:
                return resVal.replace(MessageResourceConst.MRPLACEHOLDER_CORPGROUP, BusinessConst.TRADEMARK_CTRIP);
        }
    }

    /**
     * 获取单个字符的多语言
     *
     * @param messageKey 多语言Key
     * @param locale     指定语言
     */
    @Override
    public String getResource(String messageKey, Locale locale) {
        if (messageKey != null && !messageKey.isEmpty()){
            Map<String,String> curMessageResource = messageResourceCache.get(locale);
            if (curMessageResource != null && curMessageResource.containsKey(messageKey)){
                return curMessageResource.get(messageKey);
            }
        }

        return null;
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
}
