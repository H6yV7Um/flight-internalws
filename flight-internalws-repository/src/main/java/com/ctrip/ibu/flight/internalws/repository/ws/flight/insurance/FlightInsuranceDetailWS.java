package com.ctrip.ibu.flight.internalws.repository.ws.flight.insurance;

import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.client.IBUFlightAPIClient;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.searchflightpolicyinfo.v1.GaInsurancePolicyDetailInfo;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.searchflightpolicyinfo.v1.GaSearchInsuranceOrderInfoRequest;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.searchflightpolicyinfo.v1.GaSearchInsuranceOrderInfoResponse;
import com.ctrip.ibu.flight.internalws.models.annotation.WSMethodMeta;
import com.ctrip.ibu.flight.internalws.models.flight.InsuranceDetailModel;
import com.ctrip.ibu.flight.internalws.models.flight.InsuranceDetailSearchRequestModel;
import com.ctrip.ibu.flight.internalws.models.flight.InsuranceDetailSearchResponseModel;
import com.ctrip.ibu.flight.internalws.repository.ws.WSMethodInvoker;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.IbuFlightApiHelper;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.SOACommonHelper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * 保单详情WS
 * Created by kyxie on 2017/8/11.
 */
@Component("insuranceDetailWS")
@WSMethodMeta(methodDesc = "保单详情接口")
public class FlightInsuranceDetailWS implements WSMethodInvoker<IBUFlightAPIClient,InsuranceDetailSearchRequestModel,InsuranceDetailSearchResponseModel,GaSearchInsuranceOrderInfoRequest,GaSearchInsuranceOrderInfoResponse> {

    private final static String METHOD_NAME = "gaSearchInsuranceOrderInfo";

    private IbuFlightApiHelper ibuFlightApiHelper;

    @Inject
    public FlightInsuranceDetailWS(IbuFlightApiHelper ibuFlightApiHelper){
        this.ibuFlightApiHelper = ibuFlightApiHelper;
    }

    private static String testUri = "http://ws.flight.ibu.ctripcorp.com/intlflightapi";

    @Override
    public IBUFlightAPIClient getClientInstance() {
        return SOACommonHelper.getSoaClientInstance(IBUFlightAPIClient.class,testUri);
    }

    @Override
    public GaSearchInsuranceOrderInfoRequest convertRequestToWSRequest(InsuranceDetailSearchRequestModel insuranceDetailSearchRequestModel) {
        GaSearchInsuranceOrderInfoRequest request = new GaSearchInsuranceOrderInfoRequest();

        if (insuranceDetailSearchRequestModel != null){
            request.setHead(this.ibuFlightApiHelper.getHead(insuranceDetailSearchRequestModel.getRequestHead()));
            request.setOrderID(insuranceDetailSearchRequestModel.getOrderId());

            request.setInsuranceNo(insuranceDetailSearchRequestModel.getInsuranceNo());
        }

        return request;
    }

    @Override
    public GaSearchInsuranceOrderInfoResponse invokeMethod(IBUFlightAPIClient client, GaSearchInsuranceOrderInfoRequest gaSearchInsuranceOrderInfoRequest) throws Exception {
        return client.gaSearchInsuranceOrderInfo(gaSearchInsuranceOrderInfoRequest);
    }

    @Override
    public InsuranceDetailSearchResponseModel convertWSResponseToResponse(GaSearchInsuranceOrderInfoResponse wsResponse) {

        InsuranceDetailSearchResponseModel response = new InsuranceDetailSearchResponseModel();

        if (wsResponse != null){

            List<InsuranceDetailModel> insuranceDetailList = new ArrayList<>();

            List<GaInsurancePolicyDetailInfo> policyDetailInfoList = wsResponse.getInsurancePolicyDetailList();

            if (policyDetailInfoList != null && !policyDetailInfoList.isEmpty()){
                for (GaInsurancePolicyDetailInfo gaPolicyDetail : policyDetailInfoList){
                    insuranceDetailList.add(convertGaInsuranceDetail(gaPolicyDetail));
                }
            }

            response.setInsuranceDetailList(insuranceDetailList);
        }

        return response;
    }

    @Override
    public String methodName() {
        return METHOD_NAME;
    }

    private InsuranceDetailModel convertGaInsuranceDetail(GaInsurancePolicyDetailInfo insurancePolicyDetailInfo){
        InsuranceDetailModel result = new InsuranceDetailModel();
        if (insurancePolicyDetailInfo != null){
            result.setInsType(insurancePolicyDetailInfo.getInsType());
            result.setId(insurancePolicyDetailInfo.getId());
            result.setInsuranceNo(insurancePolicyDetailInfo.getInsuranceNo());
            result.setPolicyDownloadUrl(insurancePolicyDetailInfo.getPolicyDownloadUrl());
            result.setPassengerName(insurancePolicyDetailInfo.getPassengerName());
            result.setCompanyId(insurancePolicyDetailInfo.getCompanyId());
            result.setCompanyName(insurancePolicyDetailInfo.getCompanyName());
            result.setCompanyNickName(insurancePolicyDetailInfo.getCompanyNickName());
            result.setTypeId(insurancePolicyDetailInfo.getTypeId());
        }

        return result;
    }
}
