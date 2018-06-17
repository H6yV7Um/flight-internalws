package com.ctrip.ibu.flight.internalws.models.flight;

import java.math.BigDecimal;

/**
 * 格式化行李额信息
 * Create by kyxie on 2018/2/24 20:12
 */
public class FormatBaggageInfo {

    private Integer sequenceNo;

    private BigDecimal adultWeight;

    private BigDecimal childWeight;

    private BigDecimal infantWeight;

    private Integer adultPiece;

    private Integer childPiece;

    private Integer infantPiece;

    private String sequenceNote;

    private String desc;

    public Integer getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public BigDecimal getAdultWeight() {
        return adultWeight;
    }

    public void setAdultWeight(BigDecimal adultWeight) {
        this.adultWeight = adultWeight;
    }

    public BigDecimal getChildWeight() {
        return childWeight;
    }

    public void setChildWeight(BigDecimal childWeight) {
        this.childWeight = childWeight;
    }

    public BigDecimal getInfantWeight() {
        return infantWeight;
    }

    public void setInfantWeight(BigDecimal infantWeight) {
        this.infantWeight = infantWeight;
    }

    public Integer getAdultPiece() {
        return adultPiece;
    }

    public void setAdultPiece(Integer adultPiece) {
        this.adultPiece = adultPiece;
    }

    public Integer getChildPiece() {
        return childPiece;
    }

    public void setChildPiece(Integer childPiece) {
        this.childPiece = childPiece;
    }

    public Integer getInfantPiece() {
        return infantPiece;
    }

    public void setInfantPiece(Integer infantPiece) {
        this.infantPiece = infantPiece;
    }

    public String getSequenceNote() {
        return sequenceNote;
    }

    public void setSequenceNote(String sequenceNote) {
        this.sequenceNote = sequenceNote;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
