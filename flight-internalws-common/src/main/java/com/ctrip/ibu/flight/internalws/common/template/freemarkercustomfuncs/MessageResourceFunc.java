package com.ctrip.ibu.flight.internalws.common.template.freemarkercustomfuncs;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.common.config.Config;
import com.ctrip.ibu.flight.internalws.common.i18n.IMessageResource;
import com.ctrip.ibu.flight.internalws.common.components.HttpContext;
import com.ctrip.ibu.flight.internalws.common.utils.LanguageUtil;
import com.ctrip.ibu.flight.internalws.models.constant.BeanConst;
import com.ctrip.ibu.flight.internalws.models.constant.BusinessConst;
import com.ctrip.ibu.flight.internalws.models.constant.LogConst;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Locale;

/**
 * freemarker获取多语言自定义函数（多语言获取）
 * Create by kyxie on 2018/2/3 19:00
 */
@Component
public class MessageResourceFunc implements TemplateMethodModelEx {

    private static final ILog LOGGER = LogManager.getLogger(MessageResourceFunc.class);

    private IMessageResource sharkMessageResourceEngine;

    @Inject
    public MessageResourceFunc(@Named(BeanConst.BEANNAME_SHARKMESSAGERESOURCEENGINE) IMessageResource sharkMessageResourceEngine){
        this.sharkMessageResourceEngine = sharkMessageResourceEngine;
    }

    @Override
    public Object exec(List arguments) throws TemplateModelException {

        if (arguments == null || arguments.size() == 0){
            LOGGER.error(LogConst.CLOGTITLE_SYSTEMMONITOR,"Freemarker模板调用多语言缺少Key");
            return "";
        }

        //多语言Key
        String key = String.valueOf(arguments.get(0));

        if (StringUtils.isBlank(key)){
            return "";
        }

        return sharkMessageResourceEngine.getResource(key,LanguageUtil.mapLanguageTypeToLocale(HttpContext.getEmailSendLanguage()));
    }
}
