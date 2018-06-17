package com.ctrip.ibu.flight.internalws.models.annotation;

import java.lang.annotation.*;

/**
 * WebService接口信息
 * author : kyxie
 * date : 2018/5/4 14:03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface WSMethodMeta {

    /**
     * 方法描述
     * */
    String methodDesc();

    /**
     * 是否需要记录Request日志
     * */
    boolean recordRequestLog() default true;

    /**
     * 是否需要记录Response日志
     * */
    boolean recordResponseLog() default true;
}
