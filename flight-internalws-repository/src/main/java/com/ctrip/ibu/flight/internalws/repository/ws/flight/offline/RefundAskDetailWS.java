package com.ctrip.ibu.flight.internalws.repository.ws.flight.offline;

import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.client.IBUFlightAPIClient;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.flightcommon.v1.GaSimpleInsurance;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.garefundaskdetail.v1.*;
import com.ctrip.ibu.flight.internalws.common.utils.DateUtil;
import com.ctrip.ibu.flight.internalws.models.annotation.WSMethodMeta;
import com.ctrip.ibu.flight.internalws.models.flight.City;
import com.ctrip.ibu.flight.internalws.models.flight.offline.*;
import com.ctrip.ibu.flight.internalws.repository.ws.WSMethodInvoker;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.IbuFlightApiHelper;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.SOACommonHelper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangrm on 2017/11/2.
 */
@Component("refundAskDetailWS")
@WSMethodMeta(methodDesc = "退票咨询单接口")
public class RefundAskDetailWS implements WSMethodInvoker<IBUFlightAPIClient, RefundAskDetailRequestModel, RefundAskDetailResponseModel, GaRefundAskDetailRequest, GaRefundAskDetailResponse> {

    private IbuFlightApiHelper ibuFlightApiHelper;

    @Inject
    public RefundAskDetailWS(IbuFlightApiHelper ibuFlightApiHelper){
        this.ibuFlightApiHelper = ibuFlightApiHelper;
    }

    //private String testUrl = "http://ws.flight.ibu.ctripcorp.com/intlflightapi";
    private static String testUrl = "http://ws.flight.ibu.fat56.qa.nt.ctripcorp.com/intlflightapi";

    @Override
    public IBUFlightAPIClient getClientInstance() {
        return SOACommonHelper.getSoaClientInstance(IBUFlightAPIClient.class,testUrl);
    }

    @Override
    public GaRefundAskDetailRequest convertRequestToWSRequest(RefundAskDetailRequestModel refundAskDetailRequestModel) {
        GaRefundAskDetailRequest request = new GaRefundAskDetailRequest();
        if (refundAskDetailRequestModel != null) {
            request.setHead(this.ibuFlightApiHelper.getHead(refundAskDetailRequestModel.getRequestHead()));
            request.setOrderID(refundAskDetailRequestModel.getOrderId());
            request.setRefundOrderID(refundAskDetailRequestModel.getRefundOrderID());
            request.setAccessToken(refundAskDetailRequestModel.getAccessToken());
            request.setRetryGetOrderDetail(refundAskDetailRequestModel.isRetryGetOrderDetail());
        }
        return request;
    }

    @Override
    public GaRefundAskDetailResponse invokeMethod(IBUFlightAPIClient ibuFlightAPIClient, GaRefundAskDetailRequest gaRefundAskDetailRequest) throws Exception {
        return ibuFlightAPIClient.gaRefundAskDetail(gaRefundAskDetailRequest);
    }

    @Override
    public RefundAskDetailResponseModel convertWSResponseToResponse(GaRefundAskDetailResponse wsResponse) {
        RefundAskDetailResponseModel response = new RefundAskDetailResponseModel();
        response.setOrderId(wsResponse.getOrderID());
        response.setUid(wsResponse.getUID());
        response.setRefundOrderID(wsResponse.getRefundOrderID());
        response.setStatus(wsResponse.getStatus());
        response.setRefundStatusEnum(wsResponse.getRefundStatusEnum());
        response.setRefundAskPassengerInfo(convertPassengerInfo(wsResponse.getRefundAskPassengerInfo()));
        response.setxRefundInfoTypes(convertXInfo(wsResponse.getXRefundInfo()));
        response.setInsuranceInfos(convertInsurance(wsResponse.getInsuranceInfo()));

        RefundAskPriceInfoType priceInfo = wsResponse.getRefundAskPriceInfo();
        if (priceInfo != null){
            response.setPayCustomerTotal(priceInfo.getPayCustomerTotal());
            response.setCurrency(String.valueOf(priceInfo.getCurrencyType()));
        }

        return response;
    }

    private List<InsuranceInfoModel> convertInsurance(List<GaSimpleInsurance> insuranceInfo) {
        if (insuranceInfo == null)
            return new ArrayList<>();

        List<InsuranceInfoModel> insurance = new ArrayList<>();

        return insurance;
    }

    private List<XRefundInfoTypeModel> convertXInfo(List<XRefundInfoType> xRefundInfo) {
        if (xRefundInfo == null)
            return new ArrayList<>();

        List<XRefundInfoTypeModel> refundInfo = new ArrayList<>();
        for (XRefundInfoType info : xRefundInfo) {
            XRefundInfoTypeModel item = new XRefundInfoTypeModel();
            item.setxProductType(info.getXProductType());
            item.setCount(info.getCount());
            refundInfo.add(item);
        }
        return refundInfo;
    }

    private List<RefundAskPassengerInfoModel> convertPassengerInfo(List<RefundAskPassengerInfoType> refundAskPassengerInfo) {
        if (refundAskPassengerInfo == null)
            return new ArrayList<>();

        List<RefundAskPassengerInfoModel> passengerList = new ArrayList<>();

        for (RefundAskPassengerInfoType info: refundAskPassengerInfo) {
            RefundAskPassengerInfoModel item = new RefundAskPassengerInfoModel();
            item.setPassengerName(info.getPassengerName());
            item.setFlight(info.getFlight());
            item.setdDate(DateUtil.longToDate(info.getDDate()*1000));
            item.setaCity(new City());
            item.setdCity(new City());
            item.getdCity().setName(info.getDcity().getName());
            item.getaCity().setName(info.getAcity().getName());
            item.setSequence(info.getSequence());

            passengerList.add(item);
        }
        return passengerList;
    }


    @Override
    public String methodName() {
        return "GaRefundAskDetail";
    }

}
