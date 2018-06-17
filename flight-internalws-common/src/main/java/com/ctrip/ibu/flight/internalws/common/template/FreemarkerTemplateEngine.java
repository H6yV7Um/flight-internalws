package com.ctrip.ibu.flight.internalws.common.template;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.common.template.freemarkercustomfuncs.MessageResourceFunc;
import com.ctrip.ibu.flight.internalws.common.utils.ThrowableUtils;
import com.ctrip.ibu.flight.internalws.models.config.FreemarkerConfig;
import com.ctrip.ibu.flight.internalws.models.constant.ApplicationConst;
import com.ctrip.ibu.flight.internalws.models.constant.BeanConst;
import com.ctrip.ibu.flight.internalws.models.constant.EmailConst;
import com.ctrip.ibu.flight.internalws.models.constant.LogConst;
import com.ctrip.ibu.flight.internalws.models.exception.common.TemplateRenderException;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * 自定义FreeMarker模板引擎
 * Created by kyxie on 2017/7/23.
 */
@Component(BeanConst.BEANNAME_FREEMARKERENGINE)
public class FreemarkerTemplateEngine implements ITemplateEngine {

    private final static ILog LOGGER = LogManager.getLogger(FreemarkerTemplateEngine.class);

    //模板Locale相关配置，会影响到日期等的展示
    private Map<String,String> localeConfigs = new HashMap<>();

    private static final String DATEFORMAT_PREFIX = "dateformat";

    private static final String TIMEFORMAT_PREFIX = "timeformat";

    private static final String DATETILEFORMAT_PREFIX = "datetimeformat";

    private static final String NUMBERFORMAT_PREFIX = "numberformat";

    private MessageResourceFunc messageResourceCustomFunc;

    private Configuration configuration;

    private TemplateLoader templateLoader;

    @Inject
    public FreemarkerTemplateEngine(@Named(BeanConst.BEANNAME_FREEMARKERSTRINGTMPLLOADER) StringTemplateLoader stringTemplateLoader,
                                    MessageResourceFunc messageResourceCustomFunc){
        this.messageResourceCustomFunc = messageResourceCustomFunc;
        this.templateLoader = stringTemplateLoader;
    }

    /**
     * 初始化Freemarker配置
     * */
    @PostConstruct
    private void initFreemarkerConfig(){
        configuration = new Configuration(Configuration.VERSION_2_3_25);
        configuration.setTemplateLoader(this.templateLoader);
        configuration.setDefaultEncoding("UTF-8");

        Properties prop = new Properties();
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(FreemarkerConfig.class.getResourceAsStream("/META-INF/freemarker-config.properties"),"UTF-8");
            prop.load(inputStreamReader);
        } catch (IOException e) {
            prop = null;
            localeConfigs = getDefaultLocaleConfig();
        } finally {
            if (inputStreamReader != null){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    LOGGER.error(LogConst.CLOGTITLE_SYSTEMMONITOR,e);
                }
            }
        }

        if (prop != null && !prop.isEmpty()){
            for (Map.Entry<Object,Object> entry : prop.entrySet()){
                localeConfigs.put(((String)entry.getKey()).toLowerCase(),(String)entry.getValue());
            }
        }
    }

    /**
     * 渲染模板
     * @param templateName 模板名称全称
     * @param data 模板数据
     * */
    @Override
    public <T extends Map<String,Object>> String renderTemplate(String templateName, T data, FreemarkerConfig config) throws TemplateRenderException {

        if (StringUtils.isBlank(templateName)){
            return null;
        }

        String renderedTemplateString = "";


        try {
            Locale freemarkerLocale = getLocaleFromConfig(config);

            //多语言自定义函数
            if (data != null){
                data.put(EmailConst.TEMPLATEDATAKEY_MESSAGERESOURCEFUNC,messageResourceCustomFunc);
            }

            Template template = getTemplate(templateName);
            StringWriter writer = new StringWriter();
            Environment environment = template.createProcessingEnvironment(data,writer);
            environment.setLocale(freemarkerLocale);
            environment.setDateFormat(getDateFormat(freemarkerLocale));
            environment.setTimeFormat(getTimeFormat(freemarkerLocale));
            environment.setDateTimeFormat(getDateTimeFormat(freemarkerLocale));
            environment.setURLEscapingCharset("utf-8");
            environment.setClassicCompatible(true);
            environment.process();
            renderedTemplateString = writer.toString();
        } catch (IOException e) {
            LOGGER.error(LogConst.CLOGTITLE_SYSTEMMONITOR,String.format("模板渲染IO异常，异常信息:\n%s",ThrowableUtils.getExceptionDesc(e)));
            throw ThrowableUtils.getThrowWithCause(new TemplateRenderException(templateName,String.format("读取模板文件%s失败,模板不存在,异常信息：%s",templateName,e.getMessage())),e);
        } catch (TemplateException e) {
            LOGGER.error(LogConst.CLOGTITLE_SYSTEMMONITOR,String.format("模板渲染TemplateException异常，异常描述:%s",ThrowableUtils.getExceptionDesc(e)));
            throw ThrowableUtils.getThrowWithCause(new TemplateRenderException(templateName,
                    String.format("渲染模板%s异常，错误行：%d，列：%d,错误描述：%s", e.getTemplateSourceName(),e.getLineNumber(),e.getColumnNumber(),e.getMessage())),e);
        } catch (Exception e){
            LOGGER.error(LogConst.CLOGTITLE_SYSTEMMONITOR,String.format("模板渲染异常，异常信息:%s",ThrowableUtils.getExceptionDesc(e)));
            throw ThrowableUtils.getThrowWithCause(new TemplateRenderException(templateName,String.format("模板渲染异常，异常描述:%s",e.getMessage())),e);
        }

        return renderedTemplateString;
    }

    /**
     * 从配置中获取Locale(主要为了解决TH下佛历展示的问题，强制使用en_US)
     * @param freemarkerConfig freemarker配置
     * */
    private Locale getLocaleFromConfig(FreemarkerConfig freemarkerConfig){

        if (freemarkerConfig == null){
            freemarkerConfig = new FreemarkerConfig();
            freemarkerConfig.setFreemarkerLocale(Locale.getDefault());
        }

        Locale defaultLocale = new Locale("en","US");
        Locale thaiLocale = new Locale("th","TH");

        if (freemarkerConfig.getFreemarkerLocale() == null){
            return defaultLocale;
        }

        Locale configLocale = freemarkerConfig.getFreemarkerLocale();
        if (thaiLocale.equals(configLocale)){
            return defaultLocale;
        } else {
            return configLocale;
        }
    }

    /**
     * 是否存在指定模板
     *
     * @param templateName 模板名
     * @return 是否存在指定模板
     */
    @Override
    public boolean hasTemplate(String templateName) {

        boolean hasTemplate = true;

        try {
            configuration.getTemplate(templateName);
        } catch (IOException e) {
            LOGGER.error(LogConst.CLOGTITLE_SYSTEMMONITOR,e);
            hasTemplate = false;
        }

        return !hasTemplate;
    }

    //获取模板格式的默认配置
    private Map<String,String> getDefaultLocaleConfig(){
        Map<String,String> defaultConfig = new HashMap<>();
        defaultConfig.put(String.format("%s_%s_%s",DATEFORMAT_PREFIX,"en","us").toLowerCase(), ApplicationConst.DATEFORMAT);
        defaultConfig.put(String.format("%s_%s_%s",TIMEFORMAT_PREFIX,"en","us").toLowerCase(), ApplicationConst.TIMEFORMAT);
        defaultConfig.put(String.format("%s_%s_%s",DATETILEFORMAT_PREFIX,"en","us").toLowerCase(), ApplicationConst.DATETIMEFORMAT);
        return defaultConfig;
    }


    /**
     * 根据模板名获取模板
     * @param templateName 模板名称
     * @return Template类型的FreeMarker模板实例
     * */
    private Template getTemplate(String templateName) throws IOException{
        return configuration.getTemplate(templateName);
    }

    /**
     * 获取指定Locale的Date格式化信息
     * */
    public String getDateFormat(Locale locale){
        String value = localeConfigs.get(String.format("%s_%s_%s",DATEFORMAT_PREFIX,locale.getLanguage(),locale.getCountry()).toLowerCase());
        if (value == null || value.isEmpty()){
            value = localeConfigs.get(String.format("%s_%s_%s",DATEFORMAT_PREFIX,"en","us"));
        }
        return value;
    }

    /**
     * 获取指定Locale的Time格式化信息
     * */
    public String getTimeFormat(Locale locale){
        String value = localeConfigs.get(String.format("%s_%s_%s",TIMEFORMAT_PREFIX,locale.getLanguage(),locale.getCountry()).toLowerCase());
        if (value == null || value.isEmpty()){
            value = localeConfigs.get(String.format("%s_%s_%s",TIMEFORMAT_PREFIX,"en","us"));
        }
        return value;
    }

    /**
     * 获取指定Locale的DateTime格式化信息
     * */
    public String getDateTimeFormat(Locale locale){
        String value = localeConfigs.get(String.format("%s_%s_%s",DATETILEFORMAT_PREFIX,locale.getLanguage(),locale.getCountry()).toLowerCase());
        if (value == null || value.isEmpty()){
            value = localeConfigs.get(String.format("%s_%s_%s",DATETILEFORMAT_PREFIX,"en","us"));
        }
        return value;
    }

    /**
     * 获取指定Locale的Number格式化信息
     * */
    public String getNumberFormat(Locale locale){
        String value = localeConfigs.get(String.format("%s_%s_%s",NUMBERFORMAT_PREFIX,locale.getLanguage(),locale.getCountry()).toLowerCase());
        if (value == null || value.isEmpty()){
            value = localeConfigs.get(String.format("%s_%s_%s",NUMBERFORMAT_PREFIX,"en","us"));
        }
        return value;
    }
}
