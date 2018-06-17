package com.ctrip.ibu.flight.internalws.models.flight.offline;

/**
 * Created by zhangrm on 2017/11/3.
 */
public class InsuranceInfoModel {
    private String tnsType;

    private String type;

    private String price;

    private int count;

    public String getTnsType() {
        return tnsType;
    }

    public void setTnsType(String tnsType) {
        this.tnsType = tnsType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
