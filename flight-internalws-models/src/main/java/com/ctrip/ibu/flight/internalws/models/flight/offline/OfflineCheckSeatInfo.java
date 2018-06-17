package com.ctrip.ibu.flight.internalws.models.flight.offline;

import com.ctrip.ibu.flight.internalws.models.annotation.GdprInfo;
import com.ctrip.ibu.flight.internalws.models.util.GDPRType;

import java.util.List;

/**
 * Offline值机选座信息
 * Created by kyxie on 2017/10/30.
 */
public class OfflineCheckSeatInfo {

    @GdprInfo(GDPRType.GDPR_USER_NAME)
    private String passengerName;

    /**
     * 座位号
     */
    private String seatNumber;

    /**
     * 偏好
     */
    private String predilection;

    /**
     * 座位位置选项
     * 1-靠窗
     * 2-靠走廊
     * 3-靠前
     * 4-靠中
     * 5-靠后
     */
    private List<Short> positionOptionList;

    /**
     * 座位位置选项名
     */
    private List<String> positionOptionNameList;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getPredilection() {
        return predilection;
    }

    public void setPredilection(String predilection) {
        this.predilection = predilection;
    }

    public List<Short> getPositionOptionList() {
        return positionOptionList;
    }

    public void setPositionOptionList(List<Short> positionOptionList) {
        this.positionOptionList = positionOptionList;
    }

    public List<String> getPositionOptionNameList() {
        return positionOptionNameList;
    }

    public void setPositionOptionNameList(List<String> positionOptionNameList) {
        this.positionOptionNameList = positionOptionNameList;
    }
}
