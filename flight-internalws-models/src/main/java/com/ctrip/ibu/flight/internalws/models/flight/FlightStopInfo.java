package com.ctrip.ibu.flight.internalws.models.flight;

/**
 * 航班经停信息
 * Create by kyxie on 2018/4/2 12:02
 */
public class FlightStopInfo {

    private City city;

    private Airport airport;

    //经停时间（分钟）
    private Integer duration;

    private Integer hour;

    private Integer minute;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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
}
