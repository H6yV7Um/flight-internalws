package com.ctrip.ibu.flight.internalws.models.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * SOA接口处理类注解
 * Create by kyxie on 2017/12/25 18:07
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface SoaServiceProcessor {

    /**
     * Bean名称
     * */
    String value();

    /**
     * 服务标识
     * */
    String actionId();

    /**
     * 服务名称
     * */
    String actionName();

    /**
     * 服务描述
     * */
    String actionNote() default "";

    /**
     * 是否允许记录日志
     * */
    boolean enableLog() default true;

    /**
     * 是否开启Metrics监控
     * */
    boolean enableMetrics() default true;

}
