package com.ctrip.ibu.flight.internalws.common.i18n;

import com.ctrip.ibu.flight.internalws.models.common.Trademark;
import com.ctrip.ibu.flight.internalws.models.constant.BeanConst;
import com.ctrip.ibu.flight.internalws.models.constant.BusinessConst;
import com.ctrip.ibu.flight.internalws.models.constant.MessageResourceConst;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地多语言引擎
 * Created by kyxie on 2017/7/13.
 */
@Component(BeanConst.BEANNAME_LOCALMESSAGERESOURCEENGINE)
public class LocalMessageResourceEngine extends ResourceBundleMessageSource implements IMessageResource{

    private static final String BASENAME = "i18n.message";
    private static final String DEFAULTENCODING = "UTF-8";
    private static final String MESSAGE_KEY_FORMAT = "%s_%s";//key格式，语言_国家

    //语言缓存，key为语言local类型,value为key-key存储的语言集合
    private Map<String,Map<String,String>> cachedMessageResource;

    public LocalMessageResourceEngine(){
        super.setBasename(BASENAME);
        super.setDefaultEncoding(DEFAULTENCODING);

        this.cachedMessageResource = new ConcurrentHashMap<>();
    }

    /**
     * 根据语言获取当前语言下的所有多语言
     * @param locale 语言及国家
     * @param trademark 商标(用于替换多语言中的Ctrip占位符)
     * @return 该语言及国家下的所有多语言
     * */
    @Override
    public Map<String, String> getResourceBundle(Locale locale, Trademark trademark) {

        Map<String,String> result = new HashMap<>();

        Map<String,String> defaultMessageResource = cachedMessageResource.get(String.format(MESSAGE_KEY_FORMAT,"en","us"));

        String localeKey = String.format(MESSAGE_KEY_FORMAT,locale.getLanguage(),locale.getCountry());//多语言key

        //首先获取已经缓存的多语言
        if (!cachedMessageResource.isEmpty() && cachedMessageResource.containsKey(localeKey)){
            Map<String,String> cachedMsg = cachedMessageResource.get(localeKey);
            if (cachedMsg != null && !cachedMsg.isEmpty()){
                result = cachedMsg;
            }
        }else {
            //读取资源文件
            ResourceBundle resourceBundle = super.getResourceBundle(BASENAME,locale);

            if (resourceBundle != null){
                Set<String> keySet = resourceBundle.keySet();
                if (keySet != null && !keySet.isEmpty()){

                    //不存在时加入
                    if (!result.containsKey(localeKey)){
                        for (String key : keySet){
                            result.put(key, filterResource(resourceBundle.getString(key), trademark));
                        }
                    }

                    if (result != null && !result.isEmpty()){
                        cachedMessageResource.put(localeKey,result);
                    }
                }
            }

            //赋值英文,取并集
            if (defaultMessageResource != null && !defaultMessageResource.isEmpty()){
                for (Map.Entry<String,String> entry : defaultMessageResource.entrySet()){
                    result.putIfAbsent(entry.getKey(), filterResource(entry.getValue(), trademark));
                }
            }
        }

        return result;
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
        return String.valueOf(super.getResourceBundle(BASENAME,locale).getString(messageKey));
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
