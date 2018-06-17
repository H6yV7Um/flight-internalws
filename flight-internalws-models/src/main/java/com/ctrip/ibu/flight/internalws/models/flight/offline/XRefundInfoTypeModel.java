package com.ctrip.ibu.flight.internalws.models.flight.offline;

/**
 * Created by zhangrm on 2017/11/3.
 */
public class XRefundInfoTypeModel {
    private String xProductType;

    private Integer count;

    private String xProductTypeName;

    public String getxProductType() {
        return xProductType;
    }

    public void setxProductType(String xProductType) {
        this.xProductType = xProductType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getxProductTypeName() {
        return xProductTypeName;
    }

    public void setxProductTypeName(String xProductTypeName) {
        this.xProductTypeName = xProductTypeName;
    }
}
