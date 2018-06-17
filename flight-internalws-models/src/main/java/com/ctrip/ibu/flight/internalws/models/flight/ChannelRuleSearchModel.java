package com.ctrip.ibu.flight.internalws.models.flight;

/**
 * Project : flight-internalws
 * Package : com.ctrip.ibu.flight.internalws.models.flight
 * Create by kyxie on 2017/11/29 11:37
 */
public class ChannelRuleSearchModel {

    private Integer id;

    private Integer sid;

    private String sidName;

    private Integer allianceId;

    private String allianceName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSidName() {
        return sidName;
    }

    public void setSidName(String sidName) {
        this.sidName = sidName;
    }

    public Integer getAllianceId() {
        return allianceId;
    }

    public void setAllianceId(Integer allianceId) {
        this.allianceId = allianceId;
    }

    public String getAllianceName() {
        return allianceName;
    }

    public void setAllianceName(String allianceName) {
        this.allianceName = allianceName;
    }
}
