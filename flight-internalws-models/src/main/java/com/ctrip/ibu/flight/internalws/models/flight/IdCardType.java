package com.ctrip.ibu.flight.internalws.models.flight;

/**
 * 证件类型枚举
 * Created by kyxie on 2017/8/16.
 */
public enum IdCardType {

    ID("身份证"),

    PASSPORT("护照"),

    STC("学生证"),

    MTC("军人"),

    DRLC("驾驶证"),

    RP("回乡证"),

    MTP("台胞证"),

    HMP("港澳通行证"),

    ISC("国际海员证"),

    PRC("外国人永久居留证"),

    BRC("出生证明"),

    TP("台湾通行证"),

    OTHER("其他");

    private final String name;

    IdCardType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
