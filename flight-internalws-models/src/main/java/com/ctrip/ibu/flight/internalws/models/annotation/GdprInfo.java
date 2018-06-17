package com.ctrip.ibu.flight.internalws.models.annotation;

import com.ctrip.ibu.flight.internalws.models.util.GDPRType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 敏感信息字段，GSON序列化时会忽略
 * 请查看：com.ctrip.ibu.flight.internalws.common.businesshelper.GdprExclusionStrategy处理类
 * Create by kyxie on 2018/4/20 16:13
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GdprInfo {

    GDPRType value();

}
