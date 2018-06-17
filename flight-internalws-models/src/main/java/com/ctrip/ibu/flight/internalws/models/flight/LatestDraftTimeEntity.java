package com.ctrip.ibu.flight.internalws.models.flight;

/**
 * 最晚出票时间
 * Created by kyxie on 2017/9/21.
 */
public class LatestDraftTimeEntity {

    private Integer protectReference;

    private Integer hour;

    private Integer minute;

    private String desc;

    public Integer getProtectReference() {
        return protectReference;
    }

    public void setProtectReference(Integer protectReference) {
        this.protectReference = protectReference;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
