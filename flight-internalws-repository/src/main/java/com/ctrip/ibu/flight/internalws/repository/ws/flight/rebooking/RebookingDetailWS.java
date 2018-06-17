package com.ctrip.ibu.flight.internalws.repository.ws.flight.rebooking;

import com.ctrip.ibu.flight.internalws.common.utils.CommonUtil;
import com.ctrip.ibu.flight.internalws.models.annotation.WSMethodMeta;
import com.ctrip.ibu.flight.internalws.models.flight.rebooking.RebookingDetailInfoModel;
import com.ctrip.ibu.flight.internalws.models.flight.rebooking.RebookingQueryRequestModel;
import com.ctrip.ibu.flight.internalws.models.flight.rebooking.RebookingQueryResponseModel;
import com.ctrip.ibu.flight.internalws.models.thirdcontract.rebooking.QueryOrderRebookingInfoRequestType;
import com.ctrip.ibu.flight.internalws.models.thirdcontract.rebooking.QueryOrderRebookingInfoResponseType;
import com.ctrip.ibu.flight.internalws.models.thirdcontract.rebooking.TicketRebookingQueryApiClient;
import com.ctrip.ibu.flight.internalws.models.thirdcontract.rebooking.TicketingOrderRebookingInfo;
import com.ctrip.ibu.flight.internalws.repository.ws.WSMethodInvoker;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.SOACommonHelper;
import org.springframework.stereotype.Component;

/**
 * 国内改签详情查询
 * Created by li.l on 2018/1/31.
 */
@Component("rebookingDetailWS")
@WSMethodMeta(methodDesc = "改签详情接口")
public class RebookingDetailWS implements WSMethodInvoker<TicketRebookingQueryApiClient,RebookingQueryRequestModel,RebookingQueryResponseModel,QueryOrderRebookingInfoRequestType,QueryOrderRebookingInfoResponseType>{

    private String testUri = "http://ws.ticketpostprocess.flight.tars.fws.qa.nt.ctripcorp.com/ticket.rebooking.queryapi/api";

    /**
     * 获取WebService的Client实例
     */
    @Override
    public TicketRebookingQueryApiClient getClientInstance() {
        return SOACommonHelper.getSoaClientInstance(TicketRebookingQueryApiClient.class,testUri);
    }

    /**
     * Request参数转换
     *
     * @param rebookingQueryRequestModel
     */
    @Override
    public QueryOrderRebookingInfoRequestType convertRequestToWSRequest(RebookingQueryRequestModel rebookingQueryRequestModel) {
        QueryOrderRebookingInfoRequestType request = new QueryOrderRebookingInfoRequestType();
        request.setOrderID(rebookingQueryRequestModel.getOrderID());
        return request;
    }

    /**
     * 调用方法
     *
     * @param ticketRebookingQueryApiClient
     * @param queryOrderRebookingInfoRequestType
     */
    @Override
    public QueryOrderRebookingInfoResponseType invokeMethod(TicketRebookingQueryApiClient ticketRebookingQueryApiClient, QueryOrderRebookingInfoRequestType queryOrderRebookingInfoRequestType) throws Exception {
        return ticketRebookingQueryApiClient.queryOrderRebookingInfo(queryOrderRebookingInfoRequestType);
    }


    /**
     * Response转换
     *
     * @param wsResponse
     */
    @Override
    public RebookingQueryResponseModel convertWSResponseToResponse(QueryOrderRebookingInfoResponseType wsResponse) {
        RebookingQueryResponseModel response = new RebookingQueryResponseModel();
        if(wsResponse != null){
            response.setRebookingDetailInfoModels(CommonUtil.convertList(wsResponse.getTicketingOrderRebookingInfoList(), ticketingOrderRebookingInfo -> this.convertOrderRebookingInfo(ticketingOrderRebookingInfo)));
        }
        return response;
    }

    /**
     * 调用的方法名称
     */
    @Override
    public String methodName() {
        return "queryOrderRebookingInfo";
    }

    /**
     * 转换改签详情
     */
    private RebookingDetailInfoModel convertOrderRebookingInfo(TicketingOrderRebookingInfo wsRebookingDetailItem ){
        RebookingDetailInfoModel rebookingDetailInfoModel = new RebookingDetailInfoModel();
        if(wsRebookingDetailItem != null){
            rebookingDetailInfoModel.setRebookingApplicationID(wsRebookingDetailItem.getRbkId());
            rebookingDetailInfoModel.setEmailAddress(wsRebookingDetailItem.getRebookContactInfo().getContactEmail());
            rebookingDetailInfoModel.setSelfPay(wsRebookingDetailItem.getIsSelfPay());
        }
        return rebookingDetailInfoModel;
    }
}
