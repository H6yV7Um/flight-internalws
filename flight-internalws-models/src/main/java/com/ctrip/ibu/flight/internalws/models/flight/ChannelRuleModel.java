package com.ctrip.ibu.flight.internalws.models.flight;

/**
 * Meta SID及市场对应关系模型
 * */
public class ChannelRuleModel {

    /**
     * ID
     * */
    private Integer id;

    /**
     * 合作方Id
     * */
    private Integer allianceId;

    /**
     * 合作方名
     * */
    private String allianceName;

    /**
     * 分销市场Id
     * */
    private Integer sid;

    /**
     * 分销市场名称
     * */
    private String sidName;

    /**
     * 合作方分类
     * */
    private String classify;

    /**
     * SID对应的合作方市场
     * */
    private String market;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
}
