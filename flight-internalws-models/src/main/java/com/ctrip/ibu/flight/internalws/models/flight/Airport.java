package com.ctrip.ibu.flight.internalws.models.flight;

/**
 * 机场模型
 * Created by kyxie on 2017/8/11.
 */
public class Airport {

    private String code;

    private String enName;

    private Integer id;

    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
