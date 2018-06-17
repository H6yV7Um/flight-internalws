package com.ctrip.ibu.flight.internalws.models.flight.offline;

import com.ctrip.ibu.flight.internalws.models.annotation.GdprInfo;
import com.ctrip.ibu.flight.internalws.models.util.GDPRType;

import java.util.List;

/**
 * Offline特殊服务
 * Created by kyxie on 2017/10/30.
 */
public class OfflineSpecialServiceInfo {

    @GdprInfo(GDPRType.GDPR_USER_NAME)
    private List<String> passengerNameList;

    /**
     * 服务内容
     * 1 - 有氧舱
     * 2 - 婴儿摇篮
     * 3 - 轮椅
     */
    private Short serviceInfo;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 件数
     */
    private Integer number;

    public List<String> getPassengerNameList() {
        return passengerNameList;
    }

    public void setPassengerNameList(List<String> passengerNameList) {
        this.passengerNameList = passengerNameList;
    }

    public Short getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(Short serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
