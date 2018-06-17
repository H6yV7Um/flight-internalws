package com.ctrip.ibu.flight.internalws.models.flight.offline;

import com.ctrip.ibu.flight.internalws.models.flight.AskDetail;
import com.ctrip.ibu.flight.internalws.models.flight.AskReplyGroupItemType;
import com.ctrip.ibu.flight.internalws.models.flight.ContactInfo;
import com.ctrip.ibu.flight.internalws.models.flight.FlightColumn;
import com.ctrip.ibu.flight.internalws.models.flight.Passenger;

import java.util.List;

/**
 * Created by yhhuo on 2017/10/31.
 */
public class RescheduleAskInfoModel {
    /**
     * 订单号
     */
    private Long orderId;
    /**
     * 咨询单编号
     */
    private Long rescheduleAskId;
    /**
     * 改签申请单号
     */
    private  Long rebookingApplicationId;
    /**
     * 咨询单状态（W：待处理，P：处理中，D：员工已回填，S：用户已确认咨询单完成)
     */
    private String askStatus;
    /**
     * 用户id
     */
    private String uid;
    /**
     * 联系人信息
     */
    private ContactInfo contactInfo;
    /**
     * 乘客信息
     */
    private List<Passenger> passengerInfoList;
    /**
     * 原始航班信息
     */
    private List<FlightColumn> oriSegmentInfoList;
    /**
     * 咨询单详情列表
     */
    private List<AskDetail> askDetailList;
    /**
     * 回复明细
     */
    private List<AskReplyGroupItemType> askReplyGroupList;
    /**
     * 改签咨询单url
     */
    private String rescheduleFlightsUrl;

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
     * 获取 咨询单状态（W：待处理，P：处理中，D：员工已回填，S：用户已确认咨询单完成)
     */
    public String getAskStatus() {
        return this.askStatus;
    }

    /**
     * 设置 咨询单状态（W：待处理，P：处理中，D：员工已回填，S：用户已确认咨询单完成)
     */
    public void setAskStatus(String askStatus) {
        this.askStatus = askStatus;
    }

    /**
     * 获取 用户id
     */
    public String getUid() {
        return this.uid;
    }

    /**
     * 设置 用户id
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * 获取 联系人信息
     */
    public ContactInfo getContactInfo() {
        return this.contactInfo;
    }

    /**
     * 设置 联系人信息
     */
    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    /**
     * 获取 乘客信息
     */
    public List<Passenger> getPassengerInfoList() {
        return this.passengerInfoList;
    }

    /**
     * 设置 乘客信息
     */
    public void setPassengerInfoList(List<Passenger> passengerInfoList) {
        this.passengerInfoList = passengerInfoList;
    }

    /**
     * 获取 原始航班信息
     */
    public List<FlightColumn> getOriSegmentInfoList() {
        return this.oriSegmentInfoList;
    }

    /**
     * 设置 原始航班信息
     */
    public void setOriSegmentInfoList(List<FlightColumn> oriSegmentInfoList) {
        this.oriSegmentInfoList = oriSegmentInfoList;
    }

    /**
     * 获取 咨询单详情列表
     */
    public List<AskDetail> getAskDetailList() {
        return this.askDetailList;
    }

    /**
     * 设置 咨询单详情列表
     */
    public void setAskDetailList(List<AskDetail> askDetailList) {
        this.askDetailList = askDetailList;
    }

    /**
     * 获取 回复明细
     */
    public List<AskReplyGroupItemType> getAskReplyGroupList() {
        return this.askReplyGroupList;
    }

    /**
     * 设置 回复明细
     */
    public void setAskReplyGroupList(List<AskReplyGroupItemType> askReplyGroupList) {
        this.askReplyGroupList = askReplyGroupList;
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
     * 获取 改签咨询单url
     */
    public String getRescheduleFlightsUrl() {
        return this.rescheduleFlightsUrl;
    }

    /**
     * 设置 改签咨询单url
     */
    public void setRescheduleFlightsUrl(String rescheduleFlightsUrl) {
        this.rescheduleFlightsUrl = rescheduleFlightsUrl;
    }
}
