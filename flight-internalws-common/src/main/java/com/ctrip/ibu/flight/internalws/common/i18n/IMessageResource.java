package com.ctrip.ibu.flight.internalws.common.i18n;

import com.ctrip.ibu.flight.internalws.models.common.CorpGroup;
import com.ctrip.ibu.flight.internalws.models.common.Trademark;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 多语言管理接口
 * TODO:新增一个获取原始内容的方法
 * Created by kyxie on 2017/7/24.
 */
public interface IMessageResource {

    /**
     * 根据Locale获取多语言
     * @param locale 语言
     * */
    Map<String, String> getResourceBundle(Locale locale, Trademark trademark);

    /**
     * 获取单个字符的多语言
     * @param messageKey 多语言Key
     * @param locale 指定语言
     * */
    String getResource(String messageKey,Locale locale);

    /**
     * 获取指定Key的所以多语言
     * @param messageKey 多语言Key
     * */
    Map<Locale,String> getResourceBundleByMessageKey(String messageKey);
}
