package com.ctrip.ibu.flight.internalws.repository;

import com.ctrip.ibu.flight.internalws.common.log.LoggerHelper;
import com.ctrip.ibu.flight.internalws.common.utils.CommonUtil;
import com.ctrip.ibu.flight.internalws.common.utils.ThrowableUtils;
import com.ctrip.ibu.flight.internalws.models.errorcode.repository.WSErrorSource;
import com.ctrip.ibu.flight.internalws.models.exception.repository.RepositoryException;
import com.ctrip.ibu.flight.internalws.models.exception.soa.WSInvokeException;
import com.ctrip.ibu.flight.internalws.models.flight.*;
import com.ctrip.ibu.flight.internalws.models.flight.afterbooking.mergeorderdetail.MergeOrderDetailRequestEntity;
import com.ctrip.ibu.flight.internalws.models.flight.afterbooking.mergeorderdetail.MergeOrderDetailResponseEntity;
import com.ctrip.ibu.flight.internalws.models.flight.offline.*;
import com.ctrip.ibu.flight.internalws.models.flight.rebooking.RebookingQueryRequestModel;
import com.ctrip.ibu.flight.internalws.models.flight.rebooking.RebookingQueryResponseModel;
import com.ctrip.ibu.flight.internalws.models.repository.dao.ibuflightdb.MetaUseChannelRule;
import com.ctrip.ibu.flight.internalws.repository.dao.ibuflightdb.ICustomChannelRuleDao;
import com.ctrip.ibu.flight.internalws.repository.ws.WSInvokerHelper;
import com.ctrip.ibu.flight.internalws.repository.ws.flight.insurance.FlightInsuranceDetailWS;
import com.ctrip.ibu.flight.internalws.repository.ws.flight.mergeorderdetail.MergeOrderDetailWS;
import com.ctrip.ibu.flight.internalws.repository.ws.flight.offline.OfflineValueAddedServiceWS;
import com.ctrip.ibu.flight.internalws.repository.ws.flight.offline.RefundAskDetailWS;
import com.ctrip.ibu.flight.internalws.repository.ws.flight.offline.RescheduleAskDetailWS;
import com.ctrip.ibu.flight.internalws.repository.ws.flight.orderdetail.FlightOrderDetailWS;
import com.ctrip.ibu.flight.internalws.repository.ws.flight.rebooking.IntlRebookingDetailWS;
import com.ctrip.ibu.flight.internalws.repository.ws.flight.rebooking.RebookingDetailWS;
import com.ctrip.platform.dal.dao.DalHints;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 机票仓储
 * Created by kyxie on 2017/7/1.
 */
@Repository("flightRepository")
public class FlightRepository implements IFlightRepository {

    private FlightOrderDetailWS orderDetailWS;
    private FlightInsuranceDetailWS insuranceDetailWS;
    private RescheduleAskDetailWS rescheduleAskDetailWS;
    private OfflineValueAddedServiceWS offlineValueAddedServiceWS;
    private RefundAskDetailWS refundAskDetailWS;
    private RebookingDetailWS rebookingDetailWS;
    private IntlRebookingDetailWS intlRebookingDetailWS;
    private ICustomChannelRuleDao customChannelRuleDao;
    private MergeOrderDetailWS mergeOrderDetailWS;

    @Inject
    public FlightRepository(@Named("flightOrderDetailWS") FlightOrderDetailWS orderDetailWS,
                            @Named("insuranceDetailWS") FlightInsuranceDetailWS insuranceDetailWS,
                            @Named("rescheduleAskDetailWS")RescheduleAskDetailWS rescheduleAskDetailWS,
                            @Named("offlineValueAddedServiceWS") OfflineValueAddedServiceWS offlineValueAddedServiceWS,
                            @Named("refundAskDetailWS") RefundAskDetailWS refundAskDetailWS,
                            @Named("customChannelRuleDao") ICustomChannelRuleDao customChannelRuleDao,
                            @Named("rebookingDetailWS")RebookingDetailWS rebookingDetailWS,
                            @Named("intlRebookingDetailWS")IntlRebookingDetailWS intlRebookingDetailWS,
                            MergeOrderDetailWS mergeOrderDetailWS) {
        this.orderDetailWS = orderDetailWS;
        this.insuranceDetailWS = insuranceDetailWS;
        this.rescheduleAskDetailWS=rescheduleAskDetailWS;
        this.offlineValueAddedServiceWS = offlineValueAddedServiceWS;
        this.refundAskDetailWS = refundAskDetailWS;
        this.customChannelRuleDao = customChannelRuleDao;
        this.rebookingDetailWS = rebookingDetailWS;
        this.intlRebookingDetailWS = intlRebookingDetailWS;
        this.mergeOrderDetailWS = mergeOrderDetailWS;
    }

    /**
     * 获取订单详情列表
     * */
    @Override
    public List<OrderDetailModel> getOrderDetailList(OrderDetailSearchRequestModel searchModel) throws RepositoryException{

        List<OrderDetailModel> orderDetailList = new ArrayList<>();

        try{
            OrderDetailSearchResponseModel orderDetail = WSInvokerHelper.invokeApi(searchModel,orderDetailWS);
            if (orderDetail != null){
                orderDetailList = orderDetail.getOrderDetailList();
            }
        }catch (WSInvokeException e){
            throw ThrowableUtils.getThrowWithCause(new RepositoryException(WSErrorSource.WEBSERVICE, RepositoryHelper.getWSExceptionDesc(e)), e);
        }

        return orderDetailList;
    }

    /**
     * 获取保单详情列表
     * */
    @Override
    public List<InsuranceDetailModel> getInsuranceDetailList(InsuranceDetailSearchRequestModel searchModel) throws RepositoryException {

        List<InsuranceDetailModel> insuranceDetailList = new ArrayList<>();

        try {
            InsuranceDetailSearchResponseModel response = WSInvokerHelper.invokeApi(searchModel,insuranceDetailWS);
            if (response != null){
                insuranceDetailList = response.getInsuranceDetailList();
            }
        }catch (WSInvokeException e){
            throw ThrowableUtils.getThrowWithCause(new RepositoryException(WSErrorSource.WEBSERVICE, RepositoryHelper.getWSExceptionDesc(e)), e);
        }

        return insuranceDetailList;
    }

    @Override
    public RescheduleAskInfoModel getRescheduleAskInfo(RescheduleAskDetailRequestModel searchModel) throws RepositoryException {
        RescheduleAskInfoModel model;
        try {
            RescheduleAskDetailResponseModel response = WSInvokerHelper.invokeApi(searchModel, rescheduleAskDetailWS);
            model= response.getRescheduleAskInfo();
        } catch (WSInvokeException e){
            throw ThrowableUtils.getThrowWithCause(new RepositoryException(WSErrorSource.WEBSERVICE, RepositoryHelper.getWSExceptionDesc(e)), e);
        }

        return model;
    }

    /**
     * 获取增值服务
     *
     * @param searchModel
     */
    @Override
    public SearchOfflineValueAddedServiceResponse searchValueAddedService(SearchOfflineValueAddedServiceRequest searchModel) throws RepositoryException {
        SearchOfflineValueAddedServiceResponse response = new SearchOfflineValueAddedServiceResponse();

        try {
            response = WSInvokerHelper.invokeApi(searchModel, offlineValueAddedServiceWS);
        } catch (WSInvokeException e) {
            throw ThrowableUtils.getThrowWithCause(new RepositoryException(WSErrorSource.WEBSERVICE, RepositoryHelper.getWSExceptionDesc(e)), e);
        }

        return response;
    }

    @Override
    public RefundAskDetailModel getRefundAskDetailInfo(RefundAskDetailRequestModel requestModel) throws RepositoryException {
        RefundAskDetailModel model;
        try {
            RefundAskDetailResponseModel response = WSInvokerHelper.invokeApi(requestModel, refundAskDetailWS);
            model = new RefundAskDetailModel();
            model.setRefundAskDetailResponseModel(response);
        } catch (WSInvokeException e) {
            throw ThrowableUtils.getThrowWithCause(new RepositoryException(WSErrorSource.WEBSERVICE, RepositoryHelper.getWSExceptionDesc(e)), e);
        }

        return model;
    }

    /**
     * 获取分销信息
     *
     * @param sid
     */
    @Override
    public List<ChannelRuleModel> getChannelRuleInfoBySid(Integer sid) throws RepositoryException {

        List<ChannelRuleModel> channelRuleList = new ArrayList<>();

        if (sid != null){
            try {
                List<MetaUseChannelRule> daoChannelRuleList = customChannelRuleDao.getChannelRulaBySid(sid,new DalHints());
                channelRuleList = CommonUtil.convertList(daoChannelRuleList, metaUseChannelRule -> this.convertChannelRuleModel(metaUseChannelRule));
            } catch (SQLException e) {
                throw ThrowableUtils.getThrowWithCause(new RepositoryException(WSErrorSource.WEBSERVICE, "调用customChannelRuleDao异常"), e);
            }
        } else {
            LoggerHelper.appendResponseContent("查询分销信息SID为null");
        }

        return channelRuleList;
    }

    private ChannelRuleModel convertChannelRuleModel(MetaUseChannelRule daoChannelRule){

        if (daoChannelRule == null){
            return null;
        }

        ChannelRuleModel channelRuleModel = new ChannelRuleModel();
        channelRuleModel.setAllianceId(daoChannelRule.getAllianceID());
        channelRuleModel.setAllianceName(daoChannelRule.getAllianceName());
        channelRuleModel.setClassify(daoChannelRule.getClassify());
        channelRuleModel.setId(daoChannelRule.getID());
        channelRuleModel.setMarket(daoChannelRule.getMarket());
        channelRuleModel.setSid(daoChannelRule.getSID());
        channelRuleModel.setSidName(daoChannelRule.getSIDName());
        return channelRuleModel;
    }

    /**
     * 获取改签详情
     */
    @Override
    public RebookingQueryResponseModel getRebookingDetail(RebookingQueryRequestModel requestModel) throws RepositoryException{
        RebookingQueryResponseModel responseModel = null;
        if(requestModel.getFlightClass().equalsIgnoreCase("N")){
            try{
                responseModel = WSInvokerHelper.invokeApi(requestModel,rebookingDetailWS);
            } catch (WSInvokeException e) {
                throw ThrowableUtils.getThrowWithCause(new RepositoryException(WSErrorSource.WEBSERVICE, RepositoryHelper.getWSExceptionDesc(e)), e);
            }
        }
        if(requestModel.getFlightClass().equalsIgnoreCase("I")){
            try{
                responseModel = WSInvokerHelper.invokeApi(requestModel,intlRebookingDetailWS);
            } catch (WSInvokeException e) {
                throw ThrowableUtils.getThrowWithCause(new RepositoryException(WSErrorSource.WEBSERVICE, RepositoryHelper.getWSExceptionDesc(e)), e);
            }
        }
        return responseModel;
    }

    /**
     * 获取合并订单详情
     *
     * @param requestEntity 请求实体
     */
    @Override
    public MergeOrderDetailResponseEntity getMergeOrderDetail(MergeOrderDetailRequestEntity requestEntity) throws RepositoryException {

        MergeOrderDetailResponseEntity responseEntity = null;

        try {
            responseEntity = WSInvokerHelper.invokeApi(requestEntity,this.mergeOrderDetailWS);
        } catch (WSInvokeException e) {
            throw ThrowableUtils.getThrowWithCause(new RepositoryException(WSErrorSource.WEBSERVICE,RepositoryHelper.getWSExceptionDesc(e)),e);
        }

        return responseEntity;
    }
}
