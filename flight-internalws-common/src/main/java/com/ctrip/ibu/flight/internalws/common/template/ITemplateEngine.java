package com.ctrip.ibu.flight.internalws.common.template;

import com.ctrip.ibu.flight.internalws.models.config.FreemarkerConfig;
import com.ctrip.ibu.flight.internalws.models.exception.common.TemplateRenderException;

import java.util.Map;

/**
 * FreeMarker模板殷勤接口
 * Created by kyxie on 2017/7/24.
 */
public interface ITemplateEngine {

    /**
     * 渲染模板
     * @param templateName 模板名
     * @param data 模板数据
     * @param config 配置
     * @return 渲染完成的模板输出
     * */
    <T extends Map<String,Object>> String renderTemplate(String templateName, T data , FreemarkerConfig config) throws TemplateRenderException;

    /**
     * 是否存在指定模板
     * @param templateName 模板名
     * @return 是否存在指定模板
     * */
    boolean hasTemplate(String templateName);
}
