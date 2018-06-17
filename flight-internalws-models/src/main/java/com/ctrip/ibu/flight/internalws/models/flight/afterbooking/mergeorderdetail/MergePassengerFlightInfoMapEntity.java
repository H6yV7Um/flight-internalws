package com.ctrip.ibu.flight.internalws.models.flight.afterbooking.mergeorderdetail;

import com.ctrip.ibu.flight.internalws.models.flight.Passenger;

import java.util.List;

/**
 * 乘机人与航班信息映射实体
 * author : kyxie
 * date : 2018/5/4 11:02
 */
public class MergePassengerFlightInfoMapEntity {

    //乘机人信息
    private Passenger passengerInfo;

    //该乘机人下所有订单号与航班信息列表
    private List<OrderIdFlightInfoMapEntity> orderIdFlightInfoMapList;

    public Passenger getPassengerInfo() {
        return passengerInfo;
    }

    public void setPassengerInfo(Passenger passengerInfo) {
        this.passengerInfo = passengerInfo;
    }

    public List<OrderIdFlightInfoMapEntity> getOrderIdFlightInfoMapList() {
        return orderIdFlightInfoMapList;
    }

    public void setOrderIdFlightInfoMapList(List<OrderIdFlightInfoMapEntity> orderIdFlightInfoMapList) {
        this.orderIdFlightInfoMapList = orderIdFlightInfoMapList;
    }
}
