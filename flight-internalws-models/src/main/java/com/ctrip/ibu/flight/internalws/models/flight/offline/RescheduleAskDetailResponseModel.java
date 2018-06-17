package com.ctrip.ibu.flight.internalws.models.flight.offline;

/**
 * Created by yhhuo on 2017/10/31.
 */
public class RescheduleAskDetailResponseModel {
    /**
     * 改签申请单号
     */
    private  Long rebookingApplicationId;
    /**
     * 咨询单信息
     */
    private RescheduleAskInfoModel rescheduleAskInfo;

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
     * 获取 咨询单信息
     */
    public RescheduleAskInfoModel getRescheduleAskInfo() {
        return this.rescheduleAskInfo;
    }

    /**
     * 设置 咨询单信息
     */
    public void setRescheduleAskInfo(RescheduleAskInfoModel rescheduleAskInfo) {
        this.rescheduleAskInfo = rescheduleAskInfo;
    }
}
