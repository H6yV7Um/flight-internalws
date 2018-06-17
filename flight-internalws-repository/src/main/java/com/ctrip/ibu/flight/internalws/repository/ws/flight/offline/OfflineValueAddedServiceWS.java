package com.ctrip.ibu.flight.internalws.repository.ws.flight.offline;

import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.client.IBUFlightAPIClient;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.comm.head.v1.Head;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.searchxorderattached.v1.OrderInfoEntity;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.searchxorderattached.v1.SearchXOrderAttachedRequest;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.searchxorderattached.v1.SearchXOrderAttachedResponse;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.searchxorderattached.v1.XAttachedInfoEntity;
import com.ctrip.ibu.flight.internalws.common.utils.CommonUtil;
import com.ctrip.ibu.flight.internalws.models.annotation.WSMethodMeta;
import com.ctrip.ibu.flight.internalws.models.common.RequestHead;
import com.ctrip.ibu.flight.internalws.models.common.ResponseHead;
import com.ctrip.ibu.flight.internalws.models.flight.offline.OfflineValueAddedModel;
import com.ctrip.ibu.flight.internalws.models.flight.offline.SearchOfflineValueAddedServiceRequest;
import com.ctrip.ibu.flight.internalws.models.flight.offline.SearchOfflineValueAddedServiceResponse;
import com.ctrip.ibu.flight.internalws.repository.ws.WSMethodInvoker;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.IbuFlightApiHelper;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.SOACommonHelper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by kyxie on 2017/10/30.
 */
@Component("offlineValueAddedServiceWS")
@WSMethodMeta(methodDesc = "Offline增值服务接口")
public class OfflineValueAddedServiceWS implements WSMethodInvoker<IBUFlightAPIClient, SearchOfflineValueAddedServiceRequest, SearchOfflineValueAddedServiceResponse, SearchXOrderAttachedRequest, SearchXOrderAttachedResponse> {

    private IbuFlightApiHelper ibuFlightApiHelper;

    @Inject
    public OfflineValueAddedServiceWS(IbuFlightApiHelper ibuFlightApiHelper){
        this.ibuFlightApiHelper = ibuFlightApiHelper;
    }

    private String testUrl = "http://ws.flight.ibu.fat55.qa.nt.ctripcorp.com/intlflightapi";

    /**
     * 获取WebService的Client实例
     */
    @Override
    public IBUFlightAPIClient getClientInstance() {
        return SOACommonHelper.getSoaClientInstance(IBUFlightAPIClient.class,"");
    }

    /**
     * Request参数转换
     *
     * @param searchOfflineValueAddedServiceRequest
     */
    @Override
    public SearchXOrderAttachedRequest convertRequestToWSRequest(SearchOfflineValueAddedServiceRequest searchOfflineValueAddedServiceRequest) {

        SearchXOrderAttachedRequest request = new SearchXOrderAttachedRequest();

        if (searchOfflineValueAddedServiceRequest != null) {

            Head head = this.ibuFlightApiHelper.getHead();

            RequestHead requestHead = searchOfflineValueAddedServiceRequest.getRequestHead();
            if (requestHead != null) {
                head.setUID(requestHead.getUid());
            }

            request.setHead(head);
            request.setOrderID(searchOfflineValueAddedServiceRequest.getOrderId());
            if (searchOfflineValueAddedServiceRequest.getProductOrderId() != null){
                request.setProductOrderID(searchOfflineValueAddedServiceRequest.getProductOrderId());
            }
            request.setExternalNo(searchOfflineValueAddedServiceRequest.getExternalNo());
        }

        return request;
    }

    /**
     * 调用方法
     *
     * @param client
     * @param searchXOrderAttachedRequest
     */
    @Override
    public SearchXOrderAttachedResponse invokeMethod(IBUFlightAPIClient client, SearchXOrderAttachedRequest searchXOrderAttachedRequest) throws Exception {
        return client.searchXOrderAttached(searchXOrderAttachedRequest);
    }

    /**
     * Response转换
     *
     * @param wsResponse
     */
    @Override
    public SearchOfflineValueAddedServiceResponse convertWSResponseToResponse(SearchXOrderAttachedResponse wsResponse) {
        SearchOfflineValueAddedServiceResponse response = new SearchOfflineValueAddedServiceResponse();
        if (wsResponse != null) {

            ResponseHead responseHead = new ResponseHead();
            if (wsResponse.getResponseHead() != null) {
                responseHead.setErrorMsg(wsResponse.getResponseHead().getErrorMessage());
            }
            response.setResponseHead(responseHead);

            XAttachedInfoEntity wsValueAddedInfo = wsResponse.getXAttachedInfo();
            OrderInfoEntity wsOrderInfo = wsResponse.getOrderInfo();

            OfflineValueAddedModel valueAddedModel = new OfflineValueAddedModel();

            if (wsValueAddedInfo != null) {
                valueAddedModel.setId(wsValueAddedInfo.getID());
                valueAddedModel.setServiceType(wsValueAddedInfo.getServiceType());
                valueAddedModel.setSalePrice(wsValueAddedInfo.getSalePrice());
                valueAddedModel.setCurrency(wsValueAddedInfo.getCurrency());
                valueAddedModel.setStatus(wsValueAddedInfo.getStatus());
                valueAddedModel.setFlightColumnList(CommonUtil.convertList(wsValueAddedInfo.getFlightWayInfo(), flightWayEntity -> this.ibuFlightApiHelper.convertWSOfflineFlightColumnInfo(flightWayEntity)));
                valueAddedModel.setBaggageInfo(this.ibuFlightApiHelper.convertWSOfflineBaggageInfo(wsValueAddedInfo.getBaggagesInfo()));
                valueAddedModel.setMealsInfo(this.ibuFlightApiHelper.convertWSOfflineMealsInfo(wsValueAddedInfo.getMealsInfo()));
                valueAddedModel.setNameModifyInfoList(CommonUtil.convertList(wsValueAddedInfo.getNameModifyInfo(), nameModifyInfoEntity -> this.ibuFlightApiHelper.convertWSOfflineNameModifyInfo(nameModifyInfoEntity)));
                valueAddedModel.setPaperModifyInfoList(CommonUtil.convertList(wsValueAddedInfo.getPaperModifyInfo(), paperModifyInfoEntity -> this.ibuFlightApiHelper.convertWSOfflinePaperModifyInfo(paperModifyInfoEntity)));
                valueAddedModel.setCheckSeatInfoList(CommonUtil.convertList(wsValueAddedInfo.getCheckInInfo(), checkInInfoEntity -> this.ibuFlightApiHelper.convertWSOfflineCheckSeatInfo(checkInInfoEntity)));
                valueAddedModel.setSpecialServiceInfo(this.ibuFlightApiHelper.convertWSOfflineSpecialInfo(wsValueAddedInfo.getSpecialInfo()));
            }
            if (wsOrderInfo != null){
                valueAddedModel.setProductOrderId(wsOrderInfo.getProductOrderID());
            }
            response.setValueAddedService(valueAddedModel);
        }
        return response;
    }

    /**
     * 调用的方法名称
     */
    @Override
    public String methodName() {
        return "searchXOrderAttached";
    }

}
