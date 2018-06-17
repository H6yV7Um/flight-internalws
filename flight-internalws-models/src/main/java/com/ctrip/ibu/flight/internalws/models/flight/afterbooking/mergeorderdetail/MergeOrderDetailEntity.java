package com.ctrip.ibu.flight.internalws.models.flight.afterbooking.mergeorderdetail;

import com.ctrip.ibu.flight.internalws.models.flight.LatestDraftTimeEntity;

import java.util.List;

/**
 * 合并的订单详情实体
 * author : kyxie
 * date : 2018/5/2 18:14
 */
public class MergeOrderDetailEntity {

    //订单基础信息
    private OrderBasicInfoEntity orderBasicInfo;

    //支付信息
    private PaymentInfoEntity paymentInfoEntity;

    //最晚出票时间
    private LatestDraftTimeEntity latestDraftTime;

    //乘机人与航班信息映射关系
    private List<MergePassengerFlightInfoMapEntity> passengerFlightInfoMapList;

    //航班Token与航班信息映射List
    private List<MergeFlightInfoMapEntity> mergeFlightInfoMapList;

    //订单列表信息
    private List<MergeOrderInfoEntity> mergeOrderInfoList;

    public OrderBasicInfoEntity getOrderBasicInfo() {
        return orderBasicInfo;
    }

    public void setOrderBasicInfo(OrderBasicInfoEntity orderBasicInfo) {
        this.orderBasicInfo = orderBasicInfo;
    }

    public PaymentInfoEntity getPaymentInfoEntity() {
        return paymentInfoEntity;
    }

    public void setPaymentInfoEntity(PaymentInfoEntity paymentInfoEntity) {
        this.paymentInfoEntity = paymentInfoEntity;
    }

    public LatestDraftTimeEntity getLatestDraftTime() {
        return latestDraftTime;
    }

    public void setLatestDraftTime(LatestDraftTimeEntity latestDraftTime) {
        this.latestDraftTime = latestDraftTime;
    }

    public List<MergePassengerFlightInfoMapEntity> getPassengerFlightInfoMapList() {
        return passengerFlightInfoMapList;
    }

    public void setPassengerFlightInfoMapList(List<MergePassengerFlightInfoMapEntity> passengerFlightInfoMapList) {
        this.passengerFlightInfoMapList = passengerFlightInfoMapList;
    }

    public List<MergeFlightInfoMapEntity> getMergeFlightInfoMapList() {
        return mergeFlightInfoMapList;
    }

    public void setMergeFlightInfoMapList(List<MergeFlightInfoMapEntity> mergeFlightInfoMapList) {
        this.mergeFlightInfoMapList = mergeFlightInfoMapList;
    }

    public List<MergeOrderInfoEntity> getMergeOrderInfoList() {
        return mergeOrderInfoList;
    }

    public void setMergeOrderInfoList(List<MergeOrderInfoEntity> mergeOrderInfoList) {
        this.mergeOrderInfoList = mergeOrderInfoList;
    }
}
