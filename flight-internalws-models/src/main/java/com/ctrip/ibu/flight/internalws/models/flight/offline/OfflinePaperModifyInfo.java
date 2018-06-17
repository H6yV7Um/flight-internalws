package com.ctrip.ibu.flight.internalws.models.flight.offline;

import com.ctrip.ibu.flight.internalws.models.annotation.GdprInfo;
import com.ctrip.ibu.flight.internalws.models.util.GDPRType;

/**
 * Offline证件信息修改
 * Created by kyxie on 2017/10/30.
 */
public class OfflinePaperModifyInfo {

    /**
     * 乘机人信息
     */
    @GdprInfo(GDPRType.GDPR_CONTACT_NAME)
    private String passengerName;

    /**
     * 原始证件类型
     * 1-身份证
     * 2-护照
     * 4-军人证
     * 7-回乡证
     * 8-台胞证
     * 10-港澳通行证
     * 22-台湾通行证
     * 25-户口本
     * 27-出生证明
     * 28-外国人永久居留身份证
     * 999-其他
     */
    private Short originalPaperType;

    /**
     * 原始证件类型名
     */
    private String originalPaperTypeName;

    /**
     * 改前号码
     */
    @GdprInfo(GDPRType.GDPR_USER_IDCARDNO)
    private String originalPaperInfo;

    /**
     * 更改后类型
     */
    private Short modifiedPaperType;

    /**
     * 更改后证件类型名
     */
    private String modifiedPaperTypeName;

    /**
     * 改后号码
     */
    @GdprInfo(GDPRType.GDPR_USER_IDCARDNO)
    private String modifiedPaperInfo;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public Short getOriginalPaperType() {
        return originalPaperType;
    }

    public void setOriginalPaperType(Short originalPaperType) {
        this.originalPaperType = originalPaperType;
    }

    public String getOriginalPaperTypeName() {
        return originalPaperTypeName;
    }

    public void setOriginalPaperTypeName(String originalPaperTypeName) {
        this.originalPaperTypeName = originalPaperTypeName;
    }

    public String getOriginalPaperInfo() {
        return originalPaperInfo;
    }

    public void setOriginalPaperInfo(String originalPaperInfo) {
        this.originalPaperInfo = originalPaperInfo;
    }

    public Short getModifiedPaperType() {
        return modifiedPaperType;
    }

    public void setModifiedPaperType(Short modifiedPaperType) {
        this.modifiedPaperType = modifiedPaperType;
    }

    public String getModifiedPaperTypeName() {
        return modifiedPaperTypeName;
    }

    public void setModifiedPaperTypeName(String modifiedPaperTypeName) {
        this.modifiedPaperTypeName = modifiedPaperTypeName;
    }

    public String getModifiedPaperInfo() {
        return modifiedPaperInfo;
    }

    public void setModifiedPaperInfo(String modifiedPaperInfo) {
        this.modifiedPaperInfo = modifiedPaperInfo;
    }
}
