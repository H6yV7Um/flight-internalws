package com.ctrip.ibu.flight.internalws.models.flight;

import java.util.Date;

/**
 * X产品值机信息
 * Create by kyxie on 2018/2/24 16:49
 */
public class XCheckIn {

    private Long productOrderId;

    private Integer checkInOrderStatus;

    private Date checkInStartTime;

    private Integer segmentNo;

    private Integer sequenceNo;

    public Long getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(Long productOrderId) {
        this.productOrderId = productOrderId;
    }

    public Integer getCheckInOrderStatus() {
        return checkInOrderStatus;
    }

    public void setCheckInOrderStatus(Integer checkInOrderStatus) {
        this.checkInOrderStatus = checkInOrderStatus;
    }

    public Date getCheckInStartTime() {
        return checkInStartTime;
    }

    public void setCheckInStartTime(Date checkInStartTime) {
        this.checkInStartTime = checkInStartTime;
    }

    public Integer getSegmentNo() {
        return segmentNo;
    }

    public void setSegmentNo(Integer segmentNo) {
        this.segmentNo = segmentNo;
    }

    public Integer getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }
}
