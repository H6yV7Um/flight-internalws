package com.ctrip.ibu.flight.internalws.models.viewmodel.paymentsuccess.partialviewmodel;

import com.ctrip.ibu.flight.internalws.models.flight.PassengerType;

import java.util.List;

/**
 * 同一类乘客类型的信息
 * @author : kyxie
 * createtime : 2018/5/17 15:23
 */
public class PassengerGroup {

    //乘机人类型
    private PassengerType passengerType;

    //乘机人及订单信息列表
    private List<PassengerOrderInfo> passengerOrderInfoList;

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(PassengerType passengerType) {
        this.passengerType = passengerType;
    }

    public List<PassengerOrderInfo> getPassengerOrderInfoList() {
        return passengerOrderInfoList;
    }

    public void setPassengerOrderInfoList(List<PassengerOrderInfo> passengerOrderInfoList) {
        this.passengerOrderInfoList = passengerOrderInfoList;
    }

}
