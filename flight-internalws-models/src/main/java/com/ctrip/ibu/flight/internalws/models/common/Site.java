package com.ctrip.ibu.flight.internalws.models.common;

/**
 * 站点枚举
 * Created by kyxie on 2017/7/17.
 */
public enum Site {
    EN(0,"英文站"),
    JP(1,"日文站"),
    KR(2,"韩文站"),
    FR(3,"法文站"),
    DE(4,"德文站"),
    ES(5,"西班牙文站"),
    RU(6,"俄文站"),
    TW(7,"台湾站"),
    HK(8,"香港站"),
    SG(9,"新加坡站"),
    MY(10,"马来西亚站"),
    ID(11,"印尼站"),
    TH(12,"泰语站"),
    AU(13,"澳大利亚站"),
    CN(14,"简体中文站"),
    GB(15,"英国站"),
    VN(16,"越南站"),
    IT(17,"意大利站");

    private final Integer value;

    private final String name;

    Site(Integer value,String name){
        this.value = value;
        this.name = name;
    }

    public Integer getValue(){
        return this.value;
    }

    public String getName(){
        return this.name;
    }
}
