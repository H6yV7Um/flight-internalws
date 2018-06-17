package com.ctrip.ibu.flight.internalws.models.repository.dao.ibuflightdb;

import com.ctrip.platform.dal.dao.DalPojo;
import com.ctrip.platform.dal.dao.annotation.Database;
import com.ctrip.platform.dal.dao.annotation.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.sql.Types;

@Entity
@Database(name="ibuflightdb_W")
@Table(name="metausechannelrule")
public class MetaUseChannelRule implements DalPojo {

    //主键ID
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(value=Types.INTEGER)
    private Integer iD;

    //分销商ID
    @Column(name="AllianceID")
    @Type(value=Types.INTEGER)
    private Integer allianceID;

    //分销市场ID
    @Column(name="SID")
    @Type(value=Types.INTEGER)
    private Integer sID;

    //使用规则1：国家 语言  2：国家 币种
    @Column(name="RuleID")
    @Type(value=Types.INTEGER)
    private Integer ruleID;

    //更新时间
    @Column(name="datachange_lasttime", insertable=false, updatable=false)
    @Type(value=Types.TIMESTAMP)
    private Timestamp datachangeLasttime;

    //分销商名称
    @Column(name="AllianceName")
    @Type(value=Types.VARCHAR)
    private String allianceName;

    //SID名称
    @Column(name="SIDName")
    @Type(value=Types.VARCHAR)
    private String sIDName;

    //合作方分类
    @Column(name="Classify")
    @Type(value=Types.VARCHAR)
    private String classify;

    //sid对应合作方市场
    @Column(name="Market")
    @Type(value=Types.VARCHAR)
    private String market;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public Integer getAllianceID() {
        return allianceID;
    }

    public void setAllianceID(Integer allianceID) {
        this.allianceID = allianceID;
    }

    public Integer getSID() {
        return sID;
    }

    public void setSID(Integer sID) {
        this.sID = sID;
    }

    public Integer getRuleID() {
        return ruleID;
    }

    public void setRuleID(Integer ruleID) {
        this.ruleID = ruleID;
    }

    public Timestamp getDatachangeLasttime() {
        return datachangeLasttime;
    }

    public void setDatachangeLasttime(Timestamp datachangeLasttime) {
        this.datachangeLasttime = datachangeLasttime;
    }

    public String getAllianceName() {
        return allianceName;
    }

    public void setAllianceName(String allianceName) {
        this.allianceName = allianceName;
    }

    public String getSIDName() {
        return sIDName;
    }

    public void setSIDName(String sIDName) {
        this.sIDName = sIDName;
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
