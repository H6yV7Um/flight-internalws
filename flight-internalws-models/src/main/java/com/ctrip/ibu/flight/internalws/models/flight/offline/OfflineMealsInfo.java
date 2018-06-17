package com.ctrip.ibu.flight.internalws.models.flight.offline;

import java.util.List;

/**
 * Offline预定餐食信息
 * Created by kyxie on 2017/10/30.
 */
public class OfflineMealsInfo {

    private List<String> passengerNameList;

    /**
     * 规格
     * 1-胃溃疡餐
     * 2-鲜水果生素餐
     * 3-印度餐
     * 4-无谷物餐
     * 5-犹太餐
     * 6-低盐餐
     * 7-高纤维餐
     * 8-低胆固醇餐
     * 9-婴儿餐
     * 10-穆斯林餐
     * 11-素餐
     * 12-低蛋白质餐
     * 13-无乳糖餐
     * 14-亚州素餐
     * 15-儿童餐
     * 16-鲜水果餐
     * 17-低热能餐
     * 18-西式素餐
     * 19-海鲜餐
     * 20-糖尿病餐
     * 21-印度素食
     */
    private Short specification;

    /**
     * 餐食类型描述
     */
    private String mealType;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 备注信息
     */
    private String remarker;

    public List<String> getPassengerNameList() {
        return passengerNameList;
    }

    public void setPassengerNameList(List<String> passengerNameList) {
        this.passengerNameList = passengerNameList;
    }

    public Short getSpecification() {
        return specification;
    }

    public void setSpecification(Short specification) {
        this.specification = specification;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getRemarker() {
        return remarker;
    }

    public void setRemarker(String remarker) {
        this.remarker = remarker;
    }
}
