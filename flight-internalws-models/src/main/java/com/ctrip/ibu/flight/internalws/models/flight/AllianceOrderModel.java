package com.ctrip.ibu.flight.internalws.models.flight;

/**
 * 分销商订单信息
 * Create by kyxie on 2017/11/29 14:25
 */
public class AllianceOrderModel {

    private Integer allianceId;

    private Integer sid;

    private String ouid;

    private String uuid;

    public Integer getAllianceId() {
        return allianceId;
    }

    public void setAllianceId(Integer allianceId) {
        this.allianceId = allianceId;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getOuid() {
        return ouid;
    }

    public void setOuid(String ouid) {
        this.ouid = ouid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
