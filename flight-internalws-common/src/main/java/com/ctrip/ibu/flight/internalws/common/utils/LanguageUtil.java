package com.ctrip.ibu.flight.internalws.common.utils;

import com.ctrip.ibu.flight.internalws.common.cache.LocalCache;
import com.ctrip.ibu.flight.internalws.common.log.LoggerHelper;
import com.ctrip.ibu.flight.internalws.models.common.CacheKeyManager;
import com.ctrip.ibu.flight.internalws.contract.commontypes.LanguageType;
import com.ctrip.ibu.flight.internalws.models.repository.dao.ibuflightdb.Localeidmappingconfig;

import java.util.List;
import java.util.Locale;

/**
 * 语言帮助类
 * Created by kyxie on 2017/7/13.
 */
public final class LanguageUtil {

    /**
     * 从ServerFrom中解析语言
     * @param serverFrom ServerFrom订单预定站点标识
     * */
    public static LanguageType getLanguageTypeByServerFrom(String serverFrom) {
        LanguageType languageType = LanguageType.unspecified;

        if (serverFrom != null && !serverFrom.isEmpty()){
            serverFrom = serverFrom.toLowerCase();
            if (serverFrom.contains("jp.") || serverFrom.contains("/jp")) {
                languageType = LanguageType.ja_JP;
            }
            if (serverFrom.contains("co.kr") || serverFrom.contains("/kr")) {
                languageType = LanguageType.ko_KR;
            }
            if (serverFrom.contains("de.") || serverFrom.contains("/de")) {
                languageType = LanguageType.de_DE;
            }
            if (serverFrom.contains("es.") || serverFrom.contains("/es")) {
                languageType = LanguageType.es_ES;
            }
            if (serverFrom.contains("fr.") || serverFrom.contains("/fr")) {
                languageType = LanguageType.fr_FR;
            }
            if (serverFrom.contains("ru.") || serverFrom.contains("/ru")) {
                languageType = LanguageType.ru_RU;
            }
            if (serverFrom.contains("vn.") || serverFrom.contains("/vn")) {
                languageType = LanguageType.vi_VN;
            }
            if (serverFrom.contains(".sg") || serverFrom.contains("/sg")) {
                languageType = LanguageType.en_SG;
            }
            if (serverFrom.contains(".my") || serverFrom.contains("/my")){
                languageType = LanguageType.ms_MY;
            }
            if (serverFrom.contains(".co.th") || serverFrom.contains("/th")){
                languageType = LanguageType.th_TH;
            }
            if (serverFrom.contains("co.id")|| serverFrom.contains("/id")){
                languageType = LanguageType.id_ID;
            }
            if (serverFrom.contains("english") || serverFrom.contains("/uk")) {
                languageType = LanguageType.en_US;
            }
            if (serverFrom.contains(".hk")){
                if (serverFrom.contains("/en")){
                    languageType = LanguageType.en_HK;
                } else {
                    languageType = LanguageType.zh_HK;
                }
            }
            if (serverFrom.contains("/ph") || serverFrom.contains(".ph")){
                languageType = LanguageType.tl_PH;
            }
            if (serverFrom.contains("au.") || serverFrom.contains("/au")) {
                languageType = LanguageType.en_AU;
            }
            if (serverFrom.contains("zh.") || serverFrom.contains("/cn")) {
                languageType = LanguageType.zh_CN;
            }
            if (serverFrom.contains("/it") || serverFrom.contains(".it")){
                languageType = LanguageType.it_IT;
            }
        }

        return languageType;
    }

    /**
     * 通过LocaleID Map语言
     * @param localeId LocaleID
     */
    public static LanguageType getLanguageTypeByLocaleId(Integer localeId) {

        try {
            List<Localeidmappingconfig> localeidMappingConfigList = (List<Localeidmappingconfig>)LocalCache.get(CacheKeyManager.CacheKey.LocalidMappingConfig.toString());
            if (localeidMappingConfigList != null && localeidMappingConfigList.size() > 0) {
                Localeidmappingconfig localeidmappingconfig ;
                for (int i = 0, count = localeidMappingConfigList.size(); i < count; i++) {
                    localeidmappingconfig = localeidMappingConfigList.get(i);
                    if (localeidmappingconfig.getLocaleID().intValue() == localeId.intValue()) {
                        String language = localeidmappingconfig.getLanguage();
                        switch (language) {
                            case "fr":
                                return LanguageType.fr_FR;
                            case "de":
                                return LanguageType.de_DE;
                            case "es":
                                return LanguageType.es_ES;
                            case "ja":
                                return LanguageType.ja_JP;
                            case "ko":
                                return LanguageType.ko_KR;
                            case "ru":
                                return LanguageType.ru_RU;
                            case "th":
                                return LanguageType.th_TH;
                            case "id":
                                return LanguageType.id_ID;
                            case "vi":
                                return LanguageType.vi_VN;
                            case "ms":
                                return LanguageType.ms_MY;
                            case "en":
                                return LanguageType.en_US;
                            case "zh":
                                return LanguageType.zh_HK;
                            case "it":
                                return LanguageType.it_IT;
                            case "zh_SG":
                                return LanguageType.zh_CN;
                            case "en_MY":
                                return LanguageType.en_US;
                            default:
                                return LanguageType.valueOf(language);
                        }
                    }
                }
            }
        }catch(Exception ex) {
            LoggerHelper.appendResponseContent(String.format("LocaleID : %d 自动匹配语言失败,使用默认语言%s",localeId,LanguageType.en_US.toString()));
        }

        return LanguageType.en_US;
    }

    /**
     * LanguageType类型到Locale的映射
     * @param languageType 语言类型
     */
    public static Locale mapLanguageTypeToLocale(LanguageType languageType) {
        switch (languageType) {
            case ko_KR:
                return new Locale("ko", "KR");
            case ja_JP:
                return new Locale("ja", "JP");
            case zh_HK:
                return new Locale("zh", "HK");
            case zh_CN:
                return new Locale("zh", "CN");
            case zh_TW:
                return new Locale("zh", "TW");
            case th_TH:
                return new Locale("th", "TH");
            case ru_RU:
                return new Locale("ru", "RU");
            case id_ID:
                return new Locale("id", "ID");
            case fr_FR:
                return new Locale("fr", "FR");
            case vi_VN:
                return new Locale("vi", "VN");
            case tl_PH:
                return new Locale("tl", "PH");
            case es_ES:
                return new Locale("es", "ES");
            case de_DE:
                return new Locale("de", "DE");
            case en_US:
                return new Locale("en", "US");
            case en_AU:
                return new Locale("en", "AU");
            case en_HK:
                return new Locale("en", "HK");
            case en_SG:
                return new Locale("en", "SG");
            case it_IT:
                return new Locale("it", "IT");
            case ms_MY:
                return new Locale("ms", "MY");
            default:
                return new Locale("en", "US");
        }
    }
}
