package com.ctrip.ibu.flight.internalws.models.flight.offline;

import com.ctrip.ibu.flight.internalws.models.common.RequestHead;

/**
 * Created by yhhuo on 2017/10/31.
 */
public class RescheduleAskDetailRequestModel {
    /**
    * 订单号
    */
    private Long orderId;
    /**
     * 订单类型：I 国际，N 国内
     */
    private String flightOrderClass;
    /**
    * 咨询单编号
     */
    private Long rescheduleAskId;
    /**
     * 改签申请单号
     */
    private Long rebookingApplicationId;

    /**
     * AccessToken
     */
    private String accessToken;
    /**
     * 请求头
     */
    private RequestHead requestHead;

    /**
     * 获取 订单号
     */
    public Long getOrderId() {
        return this.orderId;
    }

    /**
     * 设置 订单号
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取 订单类型：I 国际，N 国内
     */
    public String getFlightOrderClass() {
        return this.flightOrderClass;
    }

    /**
     * 设置 订单类型：I 国际，N 国内
     */
    public void setFlightOrderClass(String flightOrderClass) {
        this.flightOrderClass = flightOrderClass;
    }

    /**
     * 获取 咨询单编号
     */
    public Long getRescheduleAskId() {
        return this.rescheduleAskId;
    }

    /**
     * 设置 咨询单编号
     */
    public void setRescheduleAskId(Long rescheduleAskId) {
        this.rescheduleAskId = rescheduleAskId;
    }

    /**
     * 获取 改签申请单号
     */
    public Long getRebookingApplicationId() {
        return this.rebookingApplicationId;
    }

    /**
     * 设置 改签申请单号
     */
    public void setRebookingApplicationId(Long rebookingApplicationId) {
        this.rebookingApplicationId = rebookingApplicationId;
    }

    /**
     * 获取 AccessToken
     */
    public String getAccessToken() {
        return this.accessToken;
    }

    /**
     * 设置 AccessToken
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * 获取 请求头
     */
    public RequestHead getRequestHead() {
        return this.requestHead;
    }

    /**
     * 设置 请求头
     */
    public void setRequestHead(RequestHead requestHead) {
        this.requestHead = requestHead;
    }
}
