package com.ctrip.ibu.flight.internalws.models.flight.offline;

import com.ctrip.ibu.flight.internalws.models.annotation.GdprInfo;
import com.ctrip.ibu.flight.internalws.models.util.GDPRType;

/**
 * Offline姓名更改服务
 * Created by kyxie on 2017/10/30.
 */
public class OfflineNameModifyInfo {

    @GdprInfo(GDPRType.GDPR_USER_NAME)
    private String originalName;

    @GdprInfo(GDPRType.GDPR_USER_NAME)
    private String modifiedName;

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getModifiedName() {
        return modifiedName;
    }

    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName;
    }
}
