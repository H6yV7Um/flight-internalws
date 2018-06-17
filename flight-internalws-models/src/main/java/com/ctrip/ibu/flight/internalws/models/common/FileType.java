package com.ctrip.ibu.flight.internalws.models.common;

/**
 * 文件类型
 * Create by kyxie on 2018/1/27 15:17
 */
public enum FileType {

    HTML(0,"Html格式"),

    PDF(1,"Pdf格式");

    private final Integer value;

    private final String name;

    FileType(Integer value,String name){
        this.value = value;
        this.name = name;
    }

}
