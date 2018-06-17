package com.ctrip.ibu.flight.internalws.models.errorcode.repository;

/**
 * Repository层异常来源
 * Create by kyxie on 2018/4/19 12:33
 */
public enum WSErrorSource {

    DATABASE(0,"数据库"),

    WEBSERVICE(1,"WebService");

    private final Integer val;

    private final String name;

    WSErrorSource(Integer val,String name){
        this.val = val;
        this.name = name;
    }

    public Integer getVal() {
        return val;
    }

    public String getName() {
        return name;
    }
}
