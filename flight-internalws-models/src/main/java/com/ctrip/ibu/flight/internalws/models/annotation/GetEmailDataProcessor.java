package com.ctrip.ibu.flight.internalws.models.annotation;

import com.ctrip.ibu.flight.internalws.contract.commontypes.EmailTemplateType;

import java.lang.annotation.*;

/**
 * 获取邮件数据处理类注解
 * Create by kyxie on 2018/4/14 20:18
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GetEmailDataProcessor {

    /**
     * 名称
     * */
    String name();

    /**
     * 该处理类处理的邮件类型
     * */
    EmailTemplateType[] processedEmailTypes();
}
