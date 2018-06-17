package com.ctrip.ibu.flight.internalws.models.viewmodel.paymentsuccess.partialviewmodel;

import com.ctrip.ibu.flight.internalws.models.annotation.GdprInfo;
import com.ctrip.ibu.flight.internalws.models.flight.PassengerType;
import com.ctrip.ibu.flight.internalws.models.util.GDPRType;

import java.util.List;

/**
 * 乘机人及订单信息
 * @author : kyxie
 * createtime : 2018/5/17 15:24
 */
public class PassengerOrderInfo {

    @GdprInfo(GDPRType.GDPR_USER_NAME)
    private String passengerName;

    private PassengerType passengerType;

    private List<OrderInfoSummary> orderInfoList;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(PassengerType passengerType) {
        this.passengerType = passengerType;
    }

    public List<OrderInfoSummary> getOrderInfoList() {
        return orderInfoList;
    }

    public void setOrderInfoList(List<OrderInfoSummary> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }

}
