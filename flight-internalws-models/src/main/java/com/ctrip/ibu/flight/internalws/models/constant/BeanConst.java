package com.ctrip.ibu.flight.internalws.models.constant;

/**
 * Bean相关常量
 * Created by kyxie on 2017/11/5.
 */
public final class BeanConst {

    //region --BeanName常量--

    /**
     * SOA Processor Bean名称：checkHealth
     * */
    public final static String BEANNAME_CHECKHEALTHPROCESSOR = "soaMethodProcessor_CheckHealth";

    /**
     * SOA Processor Bean名称：sendEmail
     * */
    public final static String BEANNAME_SENDEMAILPROCESSOR = "SendEmailProcessor";

    public final static String BEANNAME_GETEMAILSTATUSPROCESSOR = "GetEmailStatusProcessor";

    /**
     * 本地多语言引擎Bean名称
     * */
    public final static String BEANNAME_LOCALMESSAGERESOURCEENGINE = "localMessageResourceEngine";

    /**
     * QConfig多语言引擎Bean名称
     * */
    public final static String BEANNAME_QCONFIGMESSAGERESOURCEENGINE = "qconfigMessageResourceEngine";

    //Shark多语言引擎Bean名称
    public final static String BEANNAME_SHARKMESSAGERESOURCEENGINE = "sharkMessageResourceEngine";

    /**
     * Freemarker模板引擎Bean名称
     * */
    public final static String BEANNAME_FREEMARKERENGINE = "freemarkerTemplateEngine";

    /**
     * Freemarker字符串模板加载器Bean名称
     * */
    public final static String BEANNAME_FREEMARKERSTRINGTMPLLOADER = "freemarkerStringTemplateLoader";

    //endregion
}
