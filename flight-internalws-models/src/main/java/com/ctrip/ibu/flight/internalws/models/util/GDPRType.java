package com.ctrip.ibu.flight.internalws.models.util;

/**
 * Gdpr类型
 * Create by kyxie on 2018/4/20 11:50
 */
public enum GDPRType {

    /**
     * 订单联系人姓名
     */
    GDPR_CONTACT_NAME(0,"订单联系人姓名"),
    /**
     * 订单联系人邮箱
     */
    GDPR_CONTACT_EMAIL(1,"订单联系人邮箱"),
    /**
     * 订单联系人手机号
     */
    GDPR_CONTACT_MOBILEPHONE(2,"订单联系人手机号"),
    /**
     * 订单联系人海外手机号
     */
    GDPR_CONTACT_FOREIGN_MOBILEPHONE(3,"订单联系人海外手机号"),
    /**
     * 订单旅客名
     */
    GDPR_TRAVELER_FIRSTNAME(4,"订单旅客名"),
    /**
     * 订单旅客姓
     */
    GDPR_TRAVELER_LASTNAME(5,"订单旅客姓"),
    /**
     * 订单旅客中间名
     */
    GDPR_TRAVELER_MIDDLENAME(6,"订单旅客中间名"),
    /**
     * 订单旅客证件号
     */
    GDPR_TRAVELER_IDCARDNO(7,"订单旅客证件号"),
    /**
     * 用户姓名
     */
    GDPR_USER_NAME(8,"用户姓名"),
    /**
     * 用户证件号
     */
    GDPR_USER_IDCARDNO(9,"用户证件号"),
    /**
     * 用户银行卡号及支付宝账号、微信账号等第三方渠道支付账号
     */
    GDPR_USER_CARDNO(10,"用户银行卡号及支付宝账号、微信账号等第三方渠道支付账号"),
    /**
     * 用户固话号码
     */
    GDPR_USER_TEL(11,"用户固话号码"),
    /**
     * 用户手机号
     */
    GDPR_USER_MOBILEPHONE(12,"用户手机号"),
    /**
     * 用户海外手机号
     */
    GDPR_USER_FOREIGNMOBILEPHONE(13,"用户海外手机号"),
    /**
     * 用户邮箱
     */
    GDPR_USER_EMAIL(14,"用户邮箱"),
    /**
     * 用户地址
     */
    GDPR_USER_ADDRESS(15,"用户地址"),
    /**
     * 用户IDFA（identifierForIdentifier 广告标示符）
     */
    GDPR_USER_IDFA(16,"用户IDFA（identifierForIdentifier 广告标示符）"),
    /**
     * 用户IMEI（International Mobile Equipment Identity 国际移动设备识别码）
     */
    GDPR_USER_IMEI(17,"用户IMEI（International Mobile Equipment Identity 国际移动设备识别码）"),
    /**
     * 用户IP地址
     */
    GDPR_USER_IP(18,"用户IP地址"),
    /**
     * 用户clientcode
     */
    GDPR_USER_CLIENTCODE(19,"用户clientcode"),
    /**
     * 用户deviceid
     */
    GDPR_USER_DEVICEID(20,"用户deviceid"),
    /**
     * 用户devicetoken
     */
    GDPR_USER_DEVICETOKEN(21,"用户devicetoken"),
    /**
     * 用户经度
     */
    GDPR_USER_LONGITUDE(22,"用户经度"),
    /**
     * 用户纬度
     */
    GDPR_USER_LATITUDE(23,"用户纬度"),
    /**
     * 扩展字段
     */
    GDPR_EXT(24,"扩展字段");

    private final Integer val;

    private final String name;

    GDPRType(Integer val,String name){
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
