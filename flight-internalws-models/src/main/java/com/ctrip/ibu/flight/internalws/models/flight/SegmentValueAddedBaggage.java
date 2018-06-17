package com.ctrip.ibu.flight.internalws.models.flight;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhangrm on 2018/1/16.
 */
public class SegmentValueAddedBaggage {

    private long productOrderID;

    private int baggageStatus;

    private List<SegmentValueAddedBaggageDetail> segmentValueAddedBaggageDetailList;

    private BigDecimal totalAmount;

    private String currency;

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

    public List<SegmentValueAddedBaggageDetail> getSegmentValueAddedBaggageDetailList() {
        return segmentValueAddedBaggageDetailList;
    }

    public void setSegmentValueAddedBaggageDetailList(List<SegmentValueAddedBaggageDetail> segmentValueAddedBaggageDetailList) {
        this.segmentValueAddedBaggageDetailList = segmentValueAddedBaggageDetailList;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
