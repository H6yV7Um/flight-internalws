package com.ctrip.ibu.flight.internalws.models.flight.offline;

import com.ctrip.ibu.flight.internalws.models.annotation.GdprInfo;
import com.ctrip.ibu.flight.internalws.models.util.GDPRType;

import java.util.List;

/**
 * Offline预定行李额信息
 * Created by kyxie on 2017/10/30.
 */
public class OfflineBaggageInfo {

    /**
     * 乘机人
     */
    @GdprInfo(GDPRType.GDPR_USER_NAME)
    private List<String> passengerNameList;

    /**
     * 规格
     */
    private String specification;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 总数量
     * */
    private Integer totalNumber;

    public List<String> getPassengerNameList() {
        return passengerNameList;
    }

    public void setPassengerNameList(List<String> passengerNameList) {
        this.passengerNameList = passengerNameList;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }
}
