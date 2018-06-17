package com.ctrip.ibu.flight.internalws.models.flight;

import java.util.List;

/**
 * Created by zhangrm on 2018/1/15.
 */
public class ValueAddedBaggageInfo {

    //是否购买行李额服务
    private boolean hasPurchasedBaggageService;

    //是否还有额外的行李额服务(是否可以调用补订接口)
    private boolean hasAdditionalBaggageService;

    //已选行李额列表(按照乘客分组)
    private List<PassengerValueAddedBaggage> baggageInfoList;

    //已选行李额列表(按照航程分组)
    private List<SegmentValueAddedBaggage> segmentValueAddedBaggageList;

    public boolean isHasPurchasedBaggageService() {
        return hasPurchasedBaggageService;
    }

    public void setHasPurchasedBaggageService(boolean hasPurchasedBaggageService) {
        this.hasPurchasedBaggageService = hasPurchasedBaggageService;
    }

    public  boolean isHasAdditionalBaggageService() {
        return hasAdditionalBaggageService;
    }public void    setHasAdditionalBaggageService(boolean hasAdditionalBaggageService) {
        this.hasAdditionalBaggageService = hasAdditionalBaggageService;
    }

    public List<PassengerValueAddedBaggage> getBaggageInfoList() {
        return baggageInfoList;
    }

    public void setBaggageInfoList(List<PassengerValueAddedBaggage> baggageInfoList) {
        this.baggageInfoList = baggageInfoList;
    }

    public List<SegmentValueAddedBaggage> getSegmentValueAddedBaggageList() {
        return segmentValueAddedBaggageList;
    }

    public void setSegmentValueAddedBaggageList(List<SegmentValueAddedBaggage> segmentValueAddedBaggageList) {
        this.segmentValueAddedBaggageList = segmentValueAddedBaggageList;
    }
}
