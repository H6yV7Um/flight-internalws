package com.ctrip.ibu.flight.internalws.models.util;

/**
 * 敏感信息类型
 * Create by kyxie on 2018/3/13 17:01
 */
public enum SensitiveInfoType {

    /**
     * 未指定类型的通用加解密类型
     * */
    Generic(0,"通用类型"),

    /**
     * 手机,座机 都采用此类型
     */
    Phone(1,"手机,座机"),
    /**
     * 邮箱
     */
    Mail(2,"邮箱"),
    /**
     * identity card 身份证号码
     */
    Identity_Card(3,"身份证号码"),
    /**
     * 护照
     */
    Passport(4,"护照"),
    /**
     * 军官证
     */
    MilitaryCard(5,"军官证"),
    /**
     * 回乡证
     */
    HomePermit(6,"回乡证"),
    /**
     * 台胞证
     */
    MTP(7,"台胞证"),
    /**
     * 港澳通行证
     */
    HKMacPass(8,"港澳通行证"),
    /**
     * 台湾通行证
     */
    TaiwanPass(9,"台湾通行证"),
    /**
     * 旅行证
     */
    Travel_Document(10,"旅行证"),
    /**
     * 户口簿
     */
    Household_Register(11,"户口簿"),
    /**
     * 学生证
     */
    Student_Id_Card(12,"学生证"),
    /**
     * 士兵证
     */
    Soldier_Card(13,"士兵证"),
    /**
     * 驾驶证
     */
    Driver_License(14,"驾驶证"),
    /**
     * 外国人永久居留证
     */
    Foreigner_Permanent_Residence_Card(15,"外国人永久居留证"),
    /**
     * 国际海员证
     */
    Seafarer_Passport(16,"国际海员证"),
    /**
     * 外国人永久居留身份证
     */
    Foreigner_Permanent_Resident_ID_Card(17,"外国人永久居留身份证"),
    /**
     * 临时身份证
     */
    Interim_Identity_Card(18,"临时身份证"),
    /**
     * 警官证
     */
    Police_Certificate(19,"警官证"),
    /**
     * 出生证明
     */
    Birth_Certificate(20,"出生证明"),
    /**
     * 其他证件
     */
    OtherDocument(21,"其他证件"),
    /**
     * 地址
     */
    Address(22,"地址");

    private final Integer code;

    private final String typeName;

    SensitiveInfoType(Integer code,String typeName){
        this.code = code;
        this.typeName = typeName;
    }

    public Integer getCode() {
        return code;
    }

    public String getTypeName() {
        return typeName;
    }
}
