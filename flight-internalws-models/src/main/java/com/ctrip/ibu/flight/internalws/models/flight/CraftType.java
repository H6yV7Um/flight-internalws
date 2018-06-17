package com.ctrip.ibu.flight.internalws.models.flight;

/**
 * 机型
 * Created by kyxie on 2017/8/16.
 */
public class CraftType {

    private String craftType;

    private String widthLevel;

    private String craftName;

    private String minSeats;

    private String maxSeats;

    public String getCraftType() {
        return craftType;
    }

    public void setCraftType(String craftType) {
        this.craftType = craftType;
    }

    public String getWidthLevel() {
        return widthLevel;
    }

    public void setWidthLevel(String widthLevel) {
        this.widthLevel = widthLevel;
    }

    public String getCraftName() {
        return craftName;
    }

    public void setCraftName(String craftName) {
        this.craftName = craftName;
    }

    public String getMinSeats() {
        return minSeats;
    }

    public void setMinSeats(String minSeats) {
        this.minSeats = minSeats;
    }

    public String getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(String maxSeats) {
        this.maxSeats = maxSeats;
    }
}
