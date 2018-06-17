package com.ctrip.ibu.flight.internalws.repository;

import com.ctrip.ibu.flight.internalws.models.exception.repository.RepositoryException;
import com.ctrip.ibu.flight.internalws.models.flight.*;
import com.ctrip.ibu.flight.internalws.models.flight.afterbooking.mergeorderdetail.MergeOrderDetailRequestEntity;
import com.ctrip.ibu.flight.internalws.models.flight.afterbooking.mergeorderdetail.MergeOrderDetailResponseEntity;
import com.ctrip.ibu.flight.internalws.models.flight.offline.*;
import com.ctrip.ibu.flight.internalws.models.flight.rebooking.RebookingQueryRequestModel;
import com.ctrip.ibu.flight.internalws.models.flight.rebooking.RebookingQueryResponseModel;

import java.util.List;

/**
 * 机票仓储接口
 * Created by kyxie on 2017/7/1.
 */
public interface IFlightRepository {

    /**
     * 获取订单详情列表
     * */
    List<OrderDetailModel> getOrderDetailList(OrderDetailSearchRequestModel searchModel) throws RepositoryException;

    /**
     * 获取保单详情列表
     * */
    List<InsuranceDetailModel> getInsuranceDetailList(InsuranceDetailSearchRequestModel searchModel) throws RepositoryException;

    /**
     * 获取改签咨询单
     */
    RescheduleAskInfoModel getRescheduleAskInfo(RescheduleAskDetailRequestModel searchModel) throws RepositoryException;

    /**
     * 获取增值服务
     */
    SearchOfflineValueAddedServiceResponse searchValueAddedService(SearchOfflineValueAddedServiceRequest searchModel) throws RepositoryException;

    /**
     * 获取退票咨询单
     */
    RefundAskDetailModel getRefundAskDetailInfo(RefundAskDetailRequestModel requestModel) throws RepositoryException;

    /**
     * 获取分销信息
     * */
    List<ChannelRuleModel> getChannelRuleInfoBySid(Integer sid) throws RepositoryException;

    /**
     * 获取改签详情
     */
    RebookingQueryResponseModel getRebookingDetail(RebookingQueryRequestModel requestModel) throws RepositoryException;

    /**
     * 获取合并订单详情
     * @param requestEntity 请求实体
     * */
    MergeOrderDetailResponseEntity getMergeOrderDetail(MergeOrderDetailRequestEntity requestEntity) throws RepositoryException;
}
