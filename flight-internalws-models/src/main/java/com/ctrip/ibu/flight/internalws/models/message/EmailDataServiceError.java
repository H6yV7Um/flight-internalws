package com.ctrip.ibu.flight.internalws.models.message;

/**
 * 邮件数据获取服务错误
 */
public enum EmailDataServiceError {

    ServiceNotRegister(0, "服务未注册"),

    ServiceBeanNotFound(1, "服务Bean未找到"),

    ServiceBeanException(2, "服务Bean异常");

    private final Integer value;

    private final String desc;

    EmailDataServiceError(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
