package com.ctrip.ibu.flight.internalws.repository.ws.flight.orderdetail;

import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.client.IBUFlightAPIClient;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.comm.head.v1.Head;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.orderdetailsearch.v1.*;
import com.ctrip.ibu.flight.internalws.common.utils.CommonUtil;
import com.ctrip.ibu.flight.internalws.common.utils.DateUtil;
import com.ctrip.ibu.flight.internalws.models.annotation.WSMethodMeta;
import com.ctrip.ibu.flight.internalws.models.common.ResponseHead;
import com.ctrip.ibu.flight.internalws.models.flight.*;
import com.ctrip.ibu.flight.internalws.repository.ws.WSMethodInvoker;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.IbuFlightApiHelper;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.SOACommonHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单详情WS
 */
@Component("flightOrderDetailWS")
@WSMethodMeta(methodDesc = "订单详情接口")
public class FlightOrderDetailWS implements WSMethodInvoker<IBUFlightAPIClient, OrderDetailSearchRequestModel, OrderDetailSearchResponseModel, GaOrderDetailSearchRequest, GaOrderDetailSearchResponse> {

    private final static String METHOD_NAME = "gaOrderDetailSearch";

    private final static int PAID_BAGGAGE_STATUS = 2;

    //private static String testUri = "http://ws.flight.ibu.ctripcorp.com/intlflightapi";
    private static String testUri = "";

    private IbuFlightApiHelper ibuFlightApiHelper;

    @Inject
    public FlightOrderDetailWS(IbuFlightApiHelper ibuFlightApiHelper){
        this.ibuFlightApiHelper = ibuFlightApiHelper;
    }

    /**
     * 获取WebService的Client实例
     */
    @Override
    public IBUFlightAPIClient getClientInstance() {
        return SOACommonHelper.getSoaClientInstance(IBUFlightAPIClient.class, testUri);
    }

    /**
     * Request参数转换
     *
     * @param orderDetailSearchRequestModel 订单详情请求Request
     */
    @Override
    public GaOrderDetailSearchRequest convertRequestToWSRequest(OrderDetailSearchRequestModel orderDetailSearchRequestModel) {
        GaOrderDetailSearchRequest request = new GaOrderDetailSearchRequest();
        Head head = this.ibuFlightApiHelper.getHead(orderDetailSearchRequestModel.getRequestHead());
        request.setHead(head);
        request.setOrderIDList(orderDetailSearchRequestModel.getOrderIds());
        return request;
    }

    /**
     * 调用方法
     *
     * @param ibuFlightAPIClient client
     * @param gaOrderDetailSearchRequest 获取订单详情Request
     */
    @Override
    public GaOrderDetailSearchResponse invokeMethod(IBUFlightAPIClient ibuFlightAPIClient, GaOrderDetailSearchRequest gaOrderDetailSearchRequest) throws Exception {
        return ibuFlightAPIClient.gaOrderDetailSearch(gaOrderDetailSearchRequest);
    }

    /**
     * Response转换
     *
     * @param wsResponse 接口Response
     */
    @Override
    public OrderDetailSearchResponseModel convertWSResponseToResponse(GaOrderDetailSearchResponse wsResponse) {
        OrderDetailSearchResponseModel response = new OrderDetailSearchResponseModel();
        if (wsResponse != null) {
            com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.comm.responsehead.v1.ResponseHead wsResponseHead = wsResponse.getResponseHead();
            if (wsResponseHead != null) {
                ResponseHead responseHead = new ResponseHead();
                responseHead.setErrorMsg(String.format("ErrorCode:%s,ErrorMsg:%s", wsResponseHead.getErrorCode(), wsResponseHead.getErrorMessage()));
                response.setResponseHead(responseHead);
            }

            response.setOrderDetailList(CommonUtil.convertList(wsResponse.getOrderDetailList(), orderDetailInfo -> this.convertGaOrderDetail(orderDetailInfo)));
        }
        return response;
    }

    /**
     * 调用的方法名称
     */
    @Override
    public String methodName() {
        return METHOD_NAME;
    }

    /**
     * 转换订单详情
     */
    private OrderDetailModel convertGaOrderDetail(OrderDetailInfo wsOrderDetailInfo) {

        OrderDetailModel orderDetail = new OrderDetailModel();

        if (wsOrderDetailInfo != null) {
            //订单基础信息
            orderDetail.setOrderId(wsOrderDetailInfo.getOrderID());
            orderDetail.setUid(wsOrderDetailInfo.getUID());
            orderDetail.setServerFrom(wsOrderDetailInfo.getServerFrom());
            orderDetail.setActualOrderStatus(wsOrderDetailInfo.getActualOrderStatus());
            orderDetail.setFlightWay(this.ibuFlightApiHelper.convertWSFlightWayType(wsOrderDetailInfo.getFlightWay()));
            orderDetail.setOrderPassengerType(this.ibuFlightApiHelper.convertWSPassengerType(wsOrderDetailInfo.getOrderType()));
            orderDetail.setLocaleId(wsOrderDetailInfo.getLocaleID());
            orderDetail.setEditPayTypeExpiryTime(DateUtil.gregorianCalendarToDate(wsOrderDetailInfo.getEditPayTypeExpiryTime()));
            orderDetail.setEnglishOrder(wsOrderDetailInfo.isIsEnlishOrder());
            orderDetail.setPnr(wsOrderDetailInfo.getPNR());
            orderDetail.setOrderDate(DateUtil.longToDate(wsOrderDetailInfo.getOrderDate() * 1000));
            orderDetail.setUserPayDate(DateUtil.longToDate(wsOrderDetailInfo.getUserPayDate() * 1000));
            orderDetail.setOrderFinishDate(DateUtil.longToDate(wsOrderDetailInfo.getOrderFinishDate() * 1000));
            orderDetail.setCancelReason(wsOrderDetailInfo.getCancelReason() == null ? "" : wsOrderDetailInfo.getCancelReason().toLowerCase());
            if ("I".equalsIgnoreCase(wsOrderDetailInfo.getFlightOrderClass())) {
                orderDetail.setFlightClass(FlightClass.International);
            } else {
                orderDetail.setFlightClass(FlightClass.Domestic);
            }
            orderDetail.setPayMethodStr(wsOrderDetailInfo.getPayMethodStr());
            //关联订单
            orderDetail.setRelatedOrderInfoList(this.ibuFlightApiHelper.getRelatedOrderInfoList(wsOrderDetailInfo));

            //航班信息
            orderDetail.setFlightInfoList(CommonUtil.convertList(wsOrderDetailInfo.getFlightInfoList(), flightOrigDestInfo -> this.ibuFlightApiHelper.convertWSFlightInfo(flightOrigDestInfo)));

            //设置转机机场信息
            setTransferAirport(orderDetail.getFlightInfoList());

            //乘机人信息
            orderDetail.setPassengerList(CommonUtil.convertList(wsOrderDetailInfo.getPassengerInfoList(),gaPassengerInfo -> this.ibuFlightApiHelper.getPassengerInfoList(gaPassengerInfo,wsOrderDetailInfo.getFlightInfoList())));

            //联系人信息
            orderDetail.setContactInfo(this.ibuFlightApiHelper.convertWSContactInfo(wsOrderDetailInfo.getContactInfo()));

            //支付信息
            orderDetail.setPaymentInfo(this.ibuFlightApiHelper.convertWSPaymentInfo(wsOrderDetailInfo.getPayMethod(),wsOrderDetailInfo.getPaymentInfo(),wsOrderDetailInfo.getInsurance()));

            //保险信息
            orderDetail.setInsurance(this.ibuFlightApiHelper.convertWSInsurance(wsOrderDetailInfo.getInsurance()));

            //X产品信息
            orderDetail.setxProduct(this.ibuFlightApiHelper.convertWSXProduct(wsOrderDetailInfo.getXProductDetail(),wsOrderDetailInfo.getFlightInfoList()));

            //改签信息
            orderDetail.setRebookingInfoList(CommonUtil.convertList(wsOrderDetailInfo.getReBookingInfoList(), rebookingInfo -> this.ibuFlightApiHelper.convertWSRebookingInfo(rebookingInfo)));

            //最晚出票时间
            orderDetail.setLastestDraftTime(this.ibuFlightApiHelper.convertWSLastestDraftTime(wsOrderDetailInfo.getLatestDraftTime()));

            //AllianceOrderInfo
            orderDetail.setAllianceOrderInfo(this.ibuFlightApiHelper.convertWSAllianceInfo(wsOrderDetailInfo.getAllianceOrderInfo()));

            //格式化行李额
            orderDetail.setFormatBaggageInfoList(CommonUtil.convertList(wsOrderDetailInfo.getFormatBaggageInfoList(),wsFormatBaggageInfo -> this.ibuFlightApiHelper.convertWSFormatBaggageInfo(wsFormatBaggageInfo)));

            setTicket(orderDetail.getPassengerList(),wsOrderDetailInfo.getFlightInfoList());

            //购买的行李额
            setPaidBaggageInfo(orderDetail.getFlightInfoList(), wsOrderDetailInfo.getXProductDetail());
        }

        return orderDetail;
    }

    /**
     * 设置乘机人票号信息
     * */
    private void setTicket(List<Passenger> passengerList, List<FlightOrigDestInfo> flightInfoList) {
        if (flightInfoList == null || passengerList == null)
            return;

        for (Passenger passenger : passengerList) {
            passenger.seteTickets(new ArrayList<>());
            for (FlightOrigDestInfo info : flightInfoList) {
                if (info.getTicketNoInfoList() == null || info.getTicketNoInfoList().isEmpty())
                    continue;

                List<String> tickets = info.getTicketNoInfoList().stream()
                        .filter(p -> p.getPassengerName().equals(passenger.getName()))
                        .map(a -> a.getTicketNo()).collect(Collectors.toList());
                tickets.forEach(p -> passenger.geteTickets().add(p));
            }
        }
    }

    /**
     * 设置转机机场信息
     * @param flightInfoList 航班信息
     * */
    private void setTransferAirport(List<FlightInfo> flightInfoList){
        if (flightInfoList == null || flightInfoList.isEmpty()){
            return;
        }
        for (FlightInfo flightRouteInfo : flightInfoList) {
            List<String> transferAirportList = new ArrayList<>();
            if (flightRouteInfo.getColumnList() != null && !flightRouteInfo.getColumnList().isEmpty()){
                for (FlightColumn flightColumnInfo : flightRouteInfo.getColumnList()) {
                    Airport dAirport = flightColumnInfo.getdPort();
                    Airport aAirport = flightColumnInfo.getaPort();
                    if (dAirport != null && StringUtils.isNotBlank(dAirport.getCode()) && !transferAirportList.contains(dAirport.getCode())){
                        transferAirportList.add(dAirport.getCode());
                    }
                    if (aAirport != null && StringUtils.isNotBlank(aAirport.getCode()) && !transferAirportList.contains(aAirport.getCode())){
                        transferAirportList.add(aAirport.getCode());
                    }
                }
            }
            flightRouteInfo.setTransferAirportCodeList(transferAirportList);
        }
    }

    private void setPaidBaggageInfo(List<FlightInfo> flightInfoList, XProductDetail xProductDetail) {
        if (flightInfoList == null || xProductDetail == null)
            return;

        List<PassengerBaggageDetailEntity> baggageDetailEntities = xProductDetail.getValueAddedBaggageInfo().getBaggageInfoList();
        if (baggageDetailEntities == null)
            return;

        for (FlightInfo flightInfo : flightInfoList) {
            for (FlightColumn col : flightInfo.getColumnList()) {
                setPassengerBaggagePaidModelList(col,flightInfo.getSegmentNo(), baggageDetailEntities);
            }
        }
    }

    private void setPassengerBaggagePaidModelList(FlightColumn col, Integer segmentNo, List<PassengerBaggageDetailEntity> baggageDetailEntities) {
        if (col.getPassengerBaggagePaidModelList() == null)
            col.setPassengerBaggagePaidModelList(new ArrayList<>());

        for (PassengerBaggageDetailEntity passengerBaggageDetailEntity : baggageDetailEntities) {
            for (ValueAddedBaggageDetailEntity selectedBaggage : passengerBaggageDetailEntity.getSelectedBaggageList()) {
                if (selectedBaggage.getSegmentNo() == 0 && segmentNo.equals(selectedBaggage.getSequenceNo())) {
                    AddPaidBaggage(col, passengerBaggageDetailEntity, selectedBaggage);
                } else if (selectedBaggage.getSegmentNo() != 0 && segmentNo.equals(selectedBaggage.getSegmentNo())) {
                    AddPaidBaggage(col, passengerBaggageDetailEntity, selectedBaggage);
                }
            }
        }
    }

    private void AddPaidBaggage(FlightColumn col, PassengerBaggageDetailEntity passengerBaggageDetailEntity, ValueAddedBaggageDetailEntity selectedBaggage) {
        if (selectedBaggage.getBaggageStatus() == PAID_BAGGAGE_STATUS){
            PassengerBaggagePaidModel model = new PassengerBaggagePaidModel();
            model.setPassengerName(passengerBaggageDetailEntity.getPassengerName());
            model.setPkgNumber(selectedBaggage.getPkgNumber());
            model.setBaggageStatus(selectedBaggage.getBaggageStatus());
            model.setWeightInfo(convertXProductWeight(selectedBaggage.getWeightInfoList().get(0)));
            model.setPriceInfo(convertXProdictPrice(selectedBaggage.getPriceInfoList().get(selectedBaggage.getPriceInfoList().size() - 1)));
            col.getPassengerBaggagePaidModelList().add(model);
        }
    }

    private XProductPriceInfo convertXProdictPrice(BaggagePriceEntity baggagePriceEntity) {
        if (baggagePriceEntity == null)
            return null;

        XProductPriceInfo item = new XProductPriceInfo();
        item.setCurrency(baggagePriceEntity.getCurrency());
        item.setSalePrice(baggagePriceEntity.getSalePrice());
        return item;
    }

    private XProductWeightInfo convertXProductWeight(BaggageWeightEntity baggageWeightEntity) {
        if (baggageWeightEntity == null)
            return  null;

        XProductWeightInfo item = new XProductWeightInfo();
        item.setWeight(baggageWeightEntity.getWeight());
        item.setWeightUnits(baggageWeightEntity.getWeightUnits());
        item.setChargeWeight(baggageWeightEntity.getChargeWeight());
        item.setFreeWeight(baggageWeightEntity.getFreeWeight());
        item.setWeightExchangeRate(baggageWeightEntity.getWeightExchangeRate());
        return item;
    }
}
