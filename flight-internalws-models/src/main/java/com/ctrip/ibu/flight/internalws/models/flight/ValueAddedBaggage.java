package com.ctrip.ibu.flight.internalws.models.flight;

import java.util.List;

/**
 * 增值服务行李额
 * Created by kyxie on 2017/9/22.
 */
public class ValueAddedBaggage {

    //航程序号
    private int sequenceNo;

    //航段序号
    private int segmentNo;

    //是否需要展示该段信息
    private boolean needShow;

    //X产品订单号
    private long productOrderID;

    //行李额状态
    //0：购买成功
    //1：购买失败
    //2：待支付
    //3：处理中
    private int baggageStatus;

    //行李额状态描述
    private String baggageStatusDescription;

    //行李额件数
    private int pkgNumber;

    //价格信息列表(多币种)
    private List<XProductPriceInfo> priceInfoList;

    //重量信息列表(不同重量单位)
    private List<XProductWeightInfo> weightInfoList;

    public int getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public int getSegmentNo() {
        return segmentNo;
    }

    public void setSegmentNo(int segmentNo) {
        this.segmentNo = segmentNo;
    }

    public boolean isNeedShow() {
        return needShow;
    }

    public void setNeedShow(boolean needShow) {
        this.needShow = needShow;
    }

    public long getProductOrderID() {
        return productOrderID;
    }

    public void setProductOrderID(long productOrderID) {
        this.productOrderID = productOrderID;
    }

    public int getBaggageStatus() {
        return baggageStatus;
    }

    public void setBaggageStatus(int baggageStatus) {
        this.baggageStatus = baggageStatus;
    }

    public String getBaggageStatusDescription() {
        return baggageStatusDescription;
    }

    public void setBaggageStatusDescription(String baggageStatusDescription) {
        this.baggageStatusDescription = baggageStatusDescription;
    }

    public int getPkgNumber() {
        return pkgNumber;
    }

    public void setPkgNumber(int pkgNumber) {
        this.pkgNumber = pkgNumber;
    }

    public List<XProductPriceInfo> getPriceInfoList() {
        return priceInfoList;
    }

    public void setPriceInfoList(List<XProductPriceInfo> priceInfoList) {
        this.priceInfoList = priceInfoList;
    }

    public List<XProductWeightInfo> getWeightInfoList() {
        return weightInfoList;
    }

    public void setWeightInfoList(List<XProductWeightInfo> weightInfoList) {
        this.weightInfoList = weightInfoList;
    }
}
