package com.ctrip.ibu.flight.internalws.models.flight;

/**
 * 城市信息
 * Created by kyxie on 2017/8/16.
 */
public class City {

    private Integer id;

    private String code;

    private String name;

    private String enName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }
}
