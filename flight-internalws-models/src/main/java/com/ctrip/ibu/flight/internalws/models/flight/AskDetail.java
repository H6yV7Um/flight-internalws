package com.ctrip.ibu.flight.internalws.models.flight;

import java.util.Date;

/**
 * Created by yhhuo on 2017/10/31.
 */
public class AskDetail {
    /**
     * 咨询单详情ID
     */
   private long rescheduleAskDetailId;
    /**
     * 出发日期
     */
    private Date departDate;
    /**
     * 出发城市
     */
    private String departCityName;
    /**
     * 到达城市
     */
    private String arriveCityName;
    /**
     * 航班号
     */
    private String flightNo;
    /**
     * 舱位
     */
    private  String clazz;
    /**
     * 飞行类型（T：直飞，C：中转，U：不限） 多语言
     */
    private  String flyTypeDesc;
    /**
     * 机型（B：大，C：中，S：小，U：不限，多个用逗号隔开，例如：B,C） 多语言
     */
    private String craftTypeDesc;
    /**
     * 起飞时间段，可多个，例如：9:00-10:00；19:00-20:00
     */
    private  String takeOffTimeOption;

    /**
     * 获取 咨询单详情ID
     */
    public long getRescheduleAskDetailId() {
        return this.rescheduleAskDetailId;
    }

    /**
     * 设置 咨询单详情ID
     */
    public void setRescheduleAskDetailId(long rescheduleAskDetailId) {
        this.rescheduleAskDetailId = rescheduleAskDetailId;
    }

    /**
     * 获取 航班号
     */
    public String getFlightNo() {
        return this.flightNo;
    }

    /**
     * 设置 航班号
     */
    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    /**
     * 获取 舱位
     */
    public String getClazz() {
        return this.clazz;
    }

    /**
     * 设置 舱位
     */
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    /**
     * 获取 飞行类型（T：直飞，C：中转，U：不限） 多语言
     */
    public String getFlyTypeDesc() {
        return this.flyTypeDesc;
    }

    /**
     * 设置 飞行类型（T：直飞，C：中转，U：不限） 多语言
     */
    public void setFlyTypeDesc(String flyTypeDesc) {
        this.flyTypeDesc = flyTypeDesc;
    }

    /**
     * 获取 机型（B：大，C：中，S：小，U：不限，多个用逗号隔开，例如：B,C） 多语言
     */
    public String getCraftTypeDesc() {
        return this.craftTypeDesc;
    }

    /**
     * 设置 机型（B：大，C：中，S：小，U：不限，多个用逗号隔开，例如：B,C） 多语言
     */
    public void setCraftTypeDesc(String craftTypeDesc) {
        this.craftTypeDesc = craftTypeDesc;
    }

    /**
     * 获取 起飞时间段，可多个，例如：9:00-10:00；19:00-20:00
     */
    public String getTakeOffTimeOption() {
        return this.takeOffTimeOption;
    }

    /**
     * 设置 起飞时间段，可多个，例如：9:00-10:00；19:00-20:00
     */
    public void setTakeOffTimeOption(String takeOffTimeOption) {
        this.takeOffTimeOption = takeOffTimeOption;
    }

    /**
     * 获取 出发城市
     */
    public String getDepartCityName() {
        return this.departCityName;
    }

    /**
     * 设置 出发城市
     */
    public void setDepartCityName(String departCityName) {
        this.departCityName = departCityName;
    }

    /**
     * 获取 到达城市
     */
    public String getArriveCityName() {
        return this.arriveCityName;
    }

    /**
     * 设置 到达城市
     */
    public void setArriveCityName(String arriveCityName) {
        this.arriveCityName = arriveCityName;
    }

    /**
     * 获取 出发日期
     */
    public Date getDepartDate() {
        return this.departDate;
    }

    /**
     * 设置 出发日期
     */
    public void setDepartDate(Date departDate) {
        this.departDate = departDate;
    }
}
