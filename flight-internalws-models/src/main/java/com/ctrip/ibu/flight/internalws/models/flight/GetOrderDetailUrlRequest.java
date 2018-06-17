package com.ctrip.ibu.flight.internalws.models.flight;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * 获取订单详情URL请求
 * Created by kyxie on 2017/10/22.
 */
public class GetOrderDetailUrlRequest {

    //要查看的订单号
    private Long viewOrderId;

    private List<Long> allOrderIdList;

    private String uid;

    private String email;

    private Calendar orderTravelTime;

    private String serverFrom;

    private FlightClass flightClass;

    public Long getViewOrderId() {
        return viewOrderId;
    }

    public void setViewOrderId(Long viewOrderId) {
        this.viewOrderId = viewOrderId;
    }

    public List<Long> getAllOrderIdList() {
        return allOrderIdList;
    }

    public void setAllOrderIdList(List<Long> allOrderIdList) {
        this.allOrderIdList = allOrderIdList;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getOrderTravelTime() {
        return orderTravelTime;
    }

    public void setOrderTravelTime(Calendar orderTravelTime) {
        this.orderTravelTime = orderTravelTime;
    }

    public String getServerFrom() {
        return serverFrom;
    }

    public void setServerFrom(String serverFrom) {
        this.serverFrom = serverFrom;
    }

    public FlightClass getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClass flightClass) {
        this.flightClass = flightClass;
    }
}
