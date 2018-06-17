package com.ctrip.ibu.flight.internalws.models.repository.dao.ibuflightdb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.ctrip.platform.dal.dao.annotation.Database;
import com.ctrip.platform.dal.dao.annotation.Sensitive;
import com.ctrip.platform.dal.dao.annotation.Type;
import java.sql.Types;
import java.sql.Timestamp;

import com.ctrip.platform.dal.dao.DalPojo;

@Entity
@Database(name="ibuflightdb_W")
@Table(name="localeidmappingconfig")
public class Localeidmappingconfig implements DalPojo {

    //IBU FLight Locale ID
    @Id
    @Column(name="LocaleID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(value=Types.INTEGER)
    private Integer localeID;

    //Language
    @Column(name="Language")
    @Type(value=Types.VARCHAR)
    private String language;

    //最后修改时间
    @Column(name="DataChange_LastTime", insertable=false, updatable=false)
    @Type(value=Types.TIMESTAMP)
    private Timestamp datachangeLasttime;

    //记录创建时间
    @Column(name="DataChange_CreateTime")
    @Type(value=Types.TIMESTAMP)
    private Timestamp datachangeCreatetime;

    //更新人
    @Column(name="Operator")
    @Type(value=Types.VARCHAR)
    private String operator;

    public Integer getLocaleID() {
        return localeID;
    }

    public void setLocaleID(Integer localeID) {
        this.localeID = localeID;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Timestamp getDatachangeLasttime() {
        return datachangeLasttime;
    }

    public void setDatachangeLasttime(Timestamp datachangeLasttime) {
        this.datachangeLasttime = datachangeLasttime;
    }

    public Timestamp getDatachangeCreatetime() {
        return datachangeCreatetime;
    }

    public void setDatachangeCreatetime(Timestamp datachangeCreatetime) {
        this.datachangeCreatetime = datachangeCreatetime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

}