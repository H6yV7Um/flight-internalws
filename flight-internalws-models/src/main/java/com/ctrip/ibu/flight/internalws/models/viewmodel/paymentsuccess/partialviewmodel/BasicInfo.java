package com.ctrip.ibu.flight.internalws.models.viewmodel.paymentsuccess.partialviewmodel;

import com.ctrip.ibu.flight.internalws.models.common.CorpGroup;
import com.ctrip.ibu.flight.internalws.models.common.Trademark;
import com.ctrip.ibu.flight.internalws.models.flight.FlightWay;
import com.ctrip.ibu.flight.internalws.models.flight.LatestDraftTimeEntity;

import java.util.List;

/**
 * @author : kyxie
 * createtime : 2018/5/17 15:22
 */
public class BasicInfo {

    private LatestDraftTimeEntity latestDraftTime;

    private FlightWay flightWay;

    private List<String> routeSummaryList;

    //拆单订单总数
    private Integer orderCount;

    private Trademark trademark;

    private CorpGroup corpGroup;

    public FlightWay getFlightWay() {
        return flightWay;
    }

    public void setFlightWay(FlightWay flightWay) {
        this.flightWay = flightWay;
    }

    public List<String> getRouteSummaryList() {
        return routeSummaryList;
    }

    public void setRouteSummaryList(List<String> routeSummaryList) {
        this.routeSummaryList = routeSummaryList;
    }

    public LatestDraftTimeEntity getLatestDraftTime() {
        return latestDraftTime;
    }

    public void setLatestDraftTime(LatestDraftTimeEntity latestDraftTime) {
        this.latestDraftTime = latestDraftTime;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Trademark getTrademark() {
        return trademark;
    }

    public void setTrademark(Trademark trademark) {
        this.trademark = trademark;
    }

    public CorpGroup getCorpGroup() {
        return corpGroup;
    }

    public void setCorpGroup(CorpGroup corpGroup) {
        this.corpGroup = corpGroup;
    }

}
