package com.ctrip.ibu.flight.internalws.models.flight;

import java.util.List;

/**
 * Created by zhangrm on 2018/1/16.
 */
public class SegmentValueAddedBaggageDetail {

    private int sequenceNo;

    private List<SegmentValueAddedBaggagePassengerDetail> segmentValueAddedBaggagePassengerDetailList;

    private String dCityName;

    private String aCityName;

    public int getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public List<SegmentValueAddedBaggagePassengerDetail> getSegmentValueAddedBaggagePassengerDetailList() {
        return segmentValueAddedBaggagePassengerDetailList;
    }

    public void setSegmentValueAddedBaggagePassengerDetailList(List<SegmentValueAddedBaggagePassengerDetail> segmentValueAddedBaggagePassengerDetailList) {
        this.segmentValueAddedBaggagePassengerDetailList = segmentValueAddedBaggagePassengerDetailList;
    }

    public String getdCityName() {
        return dCityName;
    }

    public void setdCityName(String dCityName) {
        this.dCityName = dCityName;
    }

    public String getaCityName() {
        return aCityName;
    }

    public void setaCityName(String aCityName) {
        this.aCityName = aCityName;
    }
}
