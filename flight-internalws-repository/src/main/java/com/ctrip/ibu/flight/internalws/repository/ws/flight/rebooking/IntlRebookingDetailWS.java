package com.ctrip.ibu.flight.internalws.repository.ws.flight.rebooking;

import com.ctrip.ibu.flight.internalws.common.utils.CommonUtil;
import com.ctrip.ibu.flight.internalws.models.annotation.WSMethodMeta;
import com.ctrip.ibu.flight.internalws.models.flight.rebooking.RebookingDetailInfoModel;
import com.ctrip.ibu.flight.internalws.models.flight.rebooking.RebookingQueryRequestModel;
import com.ctrip.ibu.flight.internalws.models.flight.rebooking.RebookingQueryResponseModel;
import com.ctrip.ibu.flight.internalws.models.thirdcontract.rebooking.FlightOrderRebookingClient;
import com.ctrip.ibu.flight.internalws.models.thirdcontract.rebooking.RebookingDetailItem;
import com.ctrip.ibu.flight.internalws.models.thirdcontract.rebooking.RebookingDetailSearchRequest;
import com.ctrip.ibu.flight.internalws.models.thirdcontract.rebooking.RebookingDetailSearchResponse;
import com.ctrip.ibu.flight.internalws.repository.ws.WSMethodInvoker;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.SOACommonHelper;
import org.springframework.stereotype.Component;

/**
 * 国际改签详情查询
 * Created by li.l on 2018/1/31.
 */
@Component("intlRebookingDetailWS")
@WSMethodMeta(methodDesc = "国际机票改签详情接口")
public class IntlRebookingDetailWS implements WSMethodInvoker<FlightOrderRebookingClient,RebookingQueryRequestModel,RebookingQueryResponseModel,RebookingDetailSearchRequest,RebookingDetailSearchResponse>{

    private String testUri = "http://ws.flight.order.rebookandrefund.tars.fws.qa.nt.ctripcorp.com/flight-order-rebookandrefund/api";

    /**
     * 获取WebService的Client实例
     */
    @Override
    public FlightOrderRebookingClient getClientInstance() {
        return SOACommonHelper.getSoaClientInstance(FlightOrderRebookingClient.class, testUri);
    }

    /**
     * Request参数转换
     *
     * @param rebookingQueryRequestModel
     */
    @Override
    public RebookingDetailSearchRequest convertRequestToWSRequest(RebookingQueryRequestModel rebookingQueryRequestModel) {
        RebookingDetailSearchRequest request = new RebookingDetailSearchRequest();
        request.setOrderID(rebookingQueryRequestModel.getOrderID());
        request.setRebookingApplicationID(rebookingQueryRequestModel.getRebookingApplicationID());
        return request;
    }

    /**
     * 调用方法
     *
     * @param flightOrderRebookingClient
     * @param rebookingDetailSearchRequest
     */
    @Override
    public RebookingDetailSearchResponse invokeMethod(FlightOrderRebookingClient flightOrderRebookingClient, RebookingDetailSearchRequest rebookingDetailSearchRequest) throws Exception {
        return flightOrderRebookingClient.rebookingDetailSearch(rebookingDetailSearchRequest);
    }


    /**
     * Response转换
     *
     * @param wsResponse
     */
    @Override
    public RebookingQueryResponseModel convertWSResponseToResponse(RebookingDetailSearchResponse wsResponse) {
        RebookingQueryResponseModel response = new RebookingQueryResponseModel();
        if(wsResponse != null){
            response.setRebookingDetailInfoModels(CommonUtil.convertList(wsResponse.getRebookingDetailItemList(), rebookingDetailItem -> this.convertRebookingDetail(rebookingDetailItem)));
        }
        return response;
    }

    /**
     * 调用的方法名称
     */
    @Override
    public String methodName() {
        return "rebookingDetailSearch";
    }

    /**
     * 转换改签详情
     */
    private RebookingDetailInfoModel convertRebookingDetail(RebookingDetailItem wsRebookingDetailItem ){

        RebookingDetailInfoModel rebookingDetailInfoModel = new RebookingDetailInfoModel();
        if(wsRebookingDetailItem != null){
            rebookingDetailInfoModel.setRebookingApplicationID(wsRebookingDetailItem.getRebookingApplicationID());
            rebookingDetailInfoModel.setEmailAddress(wsRebookingDetailItem.getRebookingContactInfo().getEmailAddress());
            rebookingDetailInfoModel.setSelfPay(wsRebookingDetailItem.getIsSelfPay());
        }
        return rebookingDetailInfoModel;
    }
}
