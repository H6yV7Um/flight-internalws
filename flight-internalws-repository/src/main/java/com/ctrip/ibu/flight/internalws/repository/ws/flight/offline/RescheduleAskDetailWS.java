package com.ctrip.ibu.flight.internalws.repository.ws.flight.offline;

import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.client.IBUFlightAPIClient;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.flightcommon.v1.GaContactInfo;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.flightcommon.v1.GaPassengerInfo;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.garescheduleaskdetail.v1.GaAskDetailType;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.garescheduleaskdetail.v1.GaOriSegmentInfoType;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.garescheduleaskdetail.v1.GaRescheduleAskDetailRequest;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.garescheduleaskdetail.v1.GaRescheduleAskDetailResponse;
import com.ctrip.ibu.flight.internalws.common.utils.DateUtil;
import com.ctrip.ibu.flight.internalws.models.annotation.WSMethodMeta;
import com.ctrip.ibu.flight.internalws.models.flight.*;
import com.ctrip.ibu.flight.internalws.models.flight.offline.RescheduleAskDetailRequestModel;
import com.ctrip.ibu.flight.internalws.models.flight.offline.RescheduleAskDetailResponseModel;
import com.ctrip.ibu.flight.internalws.models.flight.offline.RescheduleAskInfoModel;
import com.ctrip.ibu.flight.internalws.repository.ws.WSMethodInvoker;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.IbuFlightApiHelper;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.SOACommonHelper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * 改签咨询单WS
 * Created by yhhuo on 2017/10/31.
 */
@Component("rescheduleAskDetailWS")
@WSMethodMeta(methodDesc = "改签咨询单接口")
public class RescheduleAskDetailWS implements WSMethodInvoker<IBUFlightAPIClient, RescheduleAskDetailRequestModel, RescheduleAskDetailResponseModel, GaRescheduleAskDetailRequest, GaRescheduleAskDetailResponse> {

    private IbuFlightApiHelper ibuFlightApiHelper;

    @Inject
    public RescheduleAskDetailWS(IbuFlightApiHelper ibuFlightApiHelper){
        this.ibuFlightApiHelper = ibuFlightApiHelper;
    }

    private static String testUri = "http://ws.flight.ibu.ctripcorp.com/intlflightapi";
    //private String testUrl = "http://ws.flight.ibu.fat56.qa.nt.ctripcorp.com/intlflightapi";

    @Override
    public IBUFlightAPIClient getClientInstance() {
        return SOACommonHelper.getSoaClientInstance(IBUFlightAPIClient.class,testUri);
    }

    @Override
    public GaRescheduleAskDetailRequest convertRequestToWSRequest(RescheduleAskDetailRequestModel rescheduleAskDetailRequestModel) {
        GaRescheduleAskDetailRequest request = new GaRescheduleAskDetailRequest();

        if (rescheduleAskDetailRequestModel != null) {
            request.setHead(this.ibuFlightApiHelper.getHead(rescheduleAskDetailRequestModel.getRequestHead()));
            request.setFlightOrderClass(rescheduleAskDetailRequestModel.getFlightOrderClass());
            request.setOrderID(rescheduleAskDetailRequestModel.getOrderId());
            request.setRebookingApplicationID(rescheduleAskDetailRequestModel.getRebookingApplicationId());
            //request.setRescheduleAskID(rescheduleAskDetailRequestModel.getRescheduleAskId());
            request.setAccessToken(rescheduleAskDetailRequestModel.getAccessToken());
        }

        return request;
    }

    @Override
    public GaRescheduleAskDetailResponse invokeMethod(IBUFlightAPIClient client, GaRescheduleAskDetailRequest gaRescheduleAskDetailRequest) throws Exception {
        return client.gaRescheduleAskDetail(gaRescheduleAskDetailRequest);
    }

    @Override
    public RescheduleAskDetailResponseModel convertWSResponseToResponse(GaRescheduleAskDetailResponse wsResponse) {

        RescheduleAskDetailResponseModel response = new RescheduleAskDetailResponseModel();
        if (wsResponse != null) {
            response.setRebookingApplicationId(wsResponse.getRebookingApplicationID());
            RescheduleAskInfoModel model = convertRescheduleAskInfoModel(wsResponse);
            response.setRescheduleAskInfo(model);
        }
        return response;
    }

    @Override
    public String methodName() {
        return "gaRescheduleAskDetail";
    }

    private RescheduleAskInfoModel convertRescheduleAskInfoModel(GaRescheduleAskDetailResponse gaResponse) {
        RescheduleAskInfoModel model = new RescheduleAskInfoModel();
        if (gaResponse.getRescheduleAskInfo() != null) {
            model.setOrderId(gaResponse.getRescheduleAskInfo().getOrderID());
            model.setRescheduleAskId(gaResponse.getRescheduleAskInfo().getRescheduleAskID());
            model.setRebookingApplicationId(gaResponse.getRebookingApplicationID());
            //联系人信息
            if (gaResponse.getRescheduleAskInfo().getContactInfo() != null) {
                model.setContactInfo(convertContactInfo(gaResponse.getRescheduleAskInfo().getContactInfo()));
            }
            //乘客信息
            if (gaResponse.getRescheduleAskInfo().getPassengerInfoList() != null&&!gaResponse.getRescheduleAskInfo().getPassengerInfoList().isEmpty()) {
                model.setPassengerInfoList(convertPassenger(gaResponse.getRescheduleAskInfo().getPassengerInfoList()));
            }
            //原始航班信息
            if (gaResponse.getRescheduleAskInfo().getOriSegmentInfoList() != null&&!gaResponse.getRescheduleAskInfo().getOriSegmentInfoList().isEmpty()) {
                model.setOriSegmentInfoList(convertOriSegmentInfo(gaResponse.getRescheduleAskInfo().getOriSegmentInfoList()));
            }
            //咨询单详情列表
            if (gaResponse.getRescheduleAskInfo().getAskDetailList() != null&&!gaResponse.getRescheduleAskInfo().getAskDetailList().getAskDetailItem().isEmpty()) {
                model.setAskDetailList(convertAskDetail(gaResponse.getRescheduleAskInfo().getAskDetailList().getAskDetailItem()));
            }
        }
        return model;
    }

    private ContactInfo convertContactInfo(GaContactInfo gaContactInfo) {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setName(gaContactInfo.getContactName());
        contactInfo.setEmail(gaContactInfo.getEmail());
        return contactInfo;
    }

    private List<Passenger> convertPassenger(List<GaPassengerInfo> gaPassengerInfos) {
        List<Passenger> passengerList = new ArrayList<>();
        for (GaPassengerInfo gaPassengerInfo : gaPassengerInfos) {
            Passenger passenger = new Passenger();
            passenger.setName(gaPassengerInfo.getName());
            passengerList.add(passenger);
        }
        return passengerList;
    }

    private List<FlightColumn> convertOriSegmentInfo(List<GaOriSegmentInfoType> gaOriSegmentInfoTypes) {
        List<FlightColumn> flightColumnList = new ArrayList<>();
        for (GaOriSegmentInfoType gaOriSegmentInfoType : gaOriSegmentInfoTypes) {
            FlightColumn flightColumn = new FlightColumn();
            flightColumn.setdDate(DateUtil.gregorianCalendarToDate(gaOriSegmentInfoType.getDDate()));
            City dcity=new City();
            dcity.setName(gaOriSegmentInfoType.getDCityName());
            City acity=new City();
            acity.setName(gaOriSegmentInfoType.getACityName());
            flightColumn.setdCity(dcity);
            flightColumn.setaCity(acity);
            flightColumnList.add(flightColumn);
        }
        return flightColumnList;
    }

    private List<AskDetail> convertAskDetail(List<GaAskDetailType> gaAskDetailList) {
        List<AskDetail> askDetailList = new ArrayList<>();
        for (GaAskDetailType askDetailType : gaAskDetailList) {
            AskDetail askDetail = new AskDetail();
            askDetail.setDepartDate(DateUtil.gregorianCalendarToDate(askDetailType.getDDate()));
            askDetail.setDepartCityName(askDetailType.getDCityName());
            askDetail.setArriveCityName(askDetailType.getACityName());
            askDetail.setFlightNo(askDetailType.getFlightNo());
            askDetail.setClazz(askDetailType.getClazz());
            askDetail.setFlyTypeDesc(askDetailType.getFlyTypeDesc());
            askDetail.setCraftTypeDesc(askDetailType.getCraftTypeDesc());
            askDetail.setTakeOffTimeOption(askDetailType.getTakeOffTimeOption());
            askDetailList.add((askDetail));
        }
        return askDetailList;
    }
}

