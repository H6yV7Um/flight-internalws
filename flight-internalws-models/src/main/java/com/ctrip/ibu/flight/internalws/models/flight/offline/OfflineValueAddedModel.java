package com.ctrip.ibu.flight.internalws.models.flight.offline;

import com.ctrip.ibu.flight.internalws.models.flight.FlightColumn;

import java.math.BigDecimal;
import java.util.List;

/**
 * Offline增值服务模型
 * Created by kyxie on 2017/10/31.
 */
public class OfflineValueAddedModel {

    private Long id;

    /**
     * X 产品订单号
     */
    private Long productOrderId;

    /**
     * 增值服务类型
     * 1 - 行李购买
     * 2 - 餐食购买
     * 3 - 姓名修改
     * 4 - 证件修改
     * 5 - 值机选座
     * 6 - 特殊需求
     */
    private Integer serviceType;

    /**
     * 增值服务名称
     */
    private String serviceTypeName;

    /**
     * 航段信息
     * */
    private List<FlightColumn> flightColumnList;

    /**
     * 增值服务售价
     * */
    private BigDecimal salePrice;

    /**
     * 价格币种
     * */
    private String currency;

    /**
     * W：待处理 
     * P：出票处理中已创单 
     * B：部分出票 
     * S：已出票 
     * F：出票失败 
     * D：出票超时 
     * C：已取消 
     * R：全部退票 
     * T：部分退票 
     * U：下单失败 
     * Q：退票创单成功 
     * K：退票失败 
     * A：作废 
     * I：退票处理中 
     * H：改签成功 
     * G：改签失败 
     * M: 出票中 
     */
    private String status;

    /**
     * 订单状态类型
     */
    private Integer statusType;

    /**
     * 状态描述(多语言)
     */
    private String statusDesc;

    /**
     * 提醒(多语言)
     */
    private String remind;

    /**
     * 支付链接
     */
    private String payUrl;

    private OfflineBaggageInfo baggageInfo;

    private OfflineMealsInfo mealsInfo;

    private List<OfflineNameModifyInfo> nameModifyInfoList;

    private List<OfflinePaperModifyInfo> paperModifyInfoList;

    private List<OfflineCheckSeatInfo> checkSeatInfoList;

    private OfflineSpecialServiceInfo specialServiceInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(Long productOrderId) {
        this.productOrderId = productOrderId;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }

    public List<FlightColumn> getFlightColumnList() {
        return flightColumnList;
    }

    public void setFlightColumnList(List<FlightColumn> flightColumnList) {
        this.flightColumnList = flightColumnList;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Integer getStatusType() {
        return statusType;
    }

    public void setStatusType(Integer statusType) {
        this.statusType = statusType;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public OfflineBaggageInfo getBaggageInfo() {
        return baggageInfo;
    }

    public void setBaggageInfo(OfflineBaggageInfo baggageInfo) {
        this.baggageInfo = baggageInfo;
    }

    public OfflineMealsInfo getMealsInfo() {
        return mealsInfo;
    }

    public void setMealsInfo(OfflineMealsInfo mealsInfo) {
        this.mealsInfo = mealsInfo;
    }

    public List<OfflineNameModifyInfo> getNameModifyInfoList() {
        return nameModifyInfoList;
    }

    public void setNameModifyInfoList(List<OfflineNameModifyInfo> nameModifyInfoList) {
        this.nameModifyInfoList = nameModifyInfoList;
    }

    public List<OfflinePaperModifyInfo> getPaperModifyInfoList() {
        return paperModifyInfoList;
    }

    public void setPaperModifyInfoList(List<OfflinePaperModifyInfo> paperModifyInfoList) {
        this.paperModifyInfoList = paperModifyInfoList;
    }

    public List<OfflineCheckSeatInfo> getCheckSeatInfoList() {
        return checkSeatInfoList;
    }

    public void setCheckSeatInfoList(List<OfflineCheckSeatInfo> checkSeatInfoList) {
        this.checkSeatInfoList = checkSeatInfoList;
    }

    public OfflineSpecialServiceInfo getSpecialServiceInfo() {
        return specialServiceInfo;
    }

    public void setSpecialServiceInfo(OfflineSpecialServiceInfo specialServiceInfo) {
        this.specialServiceInfo = specialServiceInfo;
    }
}
