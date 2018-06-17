package com.ctrip.ibu.flight.internalws.repository.ws.flight.mergeorderdetail;

import com.ctrip.ibu.flight.afterbooking.client.IbuFlightAfterBookingServiceClient;
import com.ctrip.ibu.flight.afterbooking.contract.MergeOrderDetailInfoType;
import com.ctrip.ibu.flight.afterbooking.contract.MergeOrderDetailSearchRequestType;
import com.ctrip.ibu.flight.afterbooking.contract.MergeOrderDetailSearchResponseType;
import com.ctrip.ibu.flight.internalws.common.utils.CommonUtil;
import com.ctrip.ibu.flight.internalws.models.annotation.WSMethodMeta;
import com.ctrip.ibu.flight.internalws.models.common.ResponseHead;
import com.ctrip.ibu.flight.internalws.models.flight.afterbooking.mergeorderdetail.MergeOrderDetailEntity;
import com.ctrip.ibu.flight.internalws.models.flight.afterbooking.mergeorderdetail.MergeOrderDetailRequestEntity;
import com.ctrip.ibu.flight.internalws.models.flight.afterbooking.mergeorderdetail.MergeOrderDetailResponseEntity;
import com.ctrip.ibu.flight.internalws.repository.ws.WSMethodInvoker;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.AfterBookingServiceHelper;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.SOACommonHelper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * 合并订单详情接口
 * Created by kyxie on 2018/4/28 16:03
 */
@Component("mergeOrderDetailWS")
@WSMethodMeta(methodDesc = "合并订单详情接口")
public class MergeOrderDetailWS implements WSMethodInvoker<IbuFlightAfterBookingServiceClient,MergeOrderDetailRequestEntity,MergeOrderDetailResponseEntity,MergeOrderDetailSearchRequestType,MergeOrderDetailSearchResponseType> {

    private final static String METHOD_NAME = "mergeOrderDetailSearch";

    //测试URI
    private static String testUri = "";

    private AfterBookingServiceHelper afterBookingServiceHelper;

    private EntityConvertMapper entityConvertMapper;

    @Inject
    public MergeOrderDetailWS(AfterBookingServiceHelper afterBookingServiceHelper,
                              EntityConvertMapper entityConvertMapper){
        this.afterBookingServiceHelper = afterBookingServiceHelper;
        this.entityConvertMapper = entityConvertMapper;
    }

    /**
     * 获取WebService的Client实例
     */
    @Override
    public IbuFlightAfterBookingServiceClient getClientInstance() {
        return SOACommonHelper.getSoaClientInstance(IbuFlightAfterBookingServiceClient.class,testUri);
    }

    /**
     * Request参数转换
     *
     * @param request 请求
     */
    @Override
    public MergeOrderDetailSearchRequestType convertRequestToWSRequest(MergeOrderDetailRequestEntity request) {

        if (request == null){
            return null;
        }

        MergeOrderDetailSearchRequestType wsRequest = new MergeOrderDetailSearchRequestType();
        wsRequest.setHead(this.afterBookingServiceHelper.getRequestHead(request.getRequestHead()));
        wsRequest.setOrderIDList(request.getOrderIdList());

        return wsRequest;
    }

    /**
     * 调用方法
     *
     * @param ibuFlightAfterBookingServiceClient AfterBookingService调用Client
     * @param mergeOrderDetailSearchRequestType 合并订单详情接口请求Request
     */
    @Override
    public MergeOrderDetailSearchResponseType invokeMethod(IbuFlightAfterBookingServiceClient ibuFlightAfterBookingServiceClient, MergeOrderDetailSearchRequestType mergeOrderDetailSearchRequestType) throws Exception {
        return ibuFlightAfterBookingServiceClient.mergeOrderDetailSearch(mergeOrderDetailSearchRequestType);
    }

    /**
     * Response转换
     *
     * @param wsResponse WS层Response
     */
    @Override
    public MergeOrderDetailResponseEntity convertWSResponseToResponse(MergeOrderDetailSearchResponseType wsResponse) {

        if (wsResponse == null){
            return null;
        }

        MergeOrderDetailResponseEntity response = new MergeOrderDetailResponseEntity();

        if (wsResponse.getResponseHead() != null){
            ResponseHead responseHead = new ResponseHead();
            responseHead.setErrorMsg(this.afterBookingServiceHelper.getResponseHeadMsg(wsResponse.responseHead));
            response.setResponseHead(responseHead);
        }

        response.setOrderDetailList(CommonUtil.convertList(wsResponse.orderDetailList,wsOrderDetail -> this.convertMergeOrderDetail(wsOrderDetail)));

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
     * 转换合并订单详情
     * @param wsMergeOrderDetail WS返回的合并订单详情
     * */
    private MergeOrderDetailEntity convertMergeOrderDetail(MergeOrderDetailInfoType wsMergeOrderDetail){
        if (wsMergeOrderDetail == null){
            return null;
        }

        MergeOrderDetailEntity mergeOrderDetail = new MergeOrderDetailEntity();
        mergeOrderDetail.setOrderBasicInfo(this.entityConvertMapper.convertWSOrderBasicInfo(wsMergeOrderDetail.orderBasicInfo));
        mergeOrderDetail.setLatestDraftTime(this.entityConvertMapper.convertWSLatestDraftTime(wsMergeOrderDetail.latestDraftTime));
        mergeOrderDetail.setPaymentInfoEntity(this.entityConvertMapper.convertWSPaymentInfo(wsMergeOrderDetail.paymentInfo));
        mergeOrderDetail.setPassengerFlightInfoMapList(CommonUtil.convertList(wsMergeOrderDetail.passengerInfoList,
                wsPassengerInfo -> this.entityConvertMapper.convertWSPassengerFlightInfoMap(wsPassengerInfo)));
        mergeOrderDetail.setMergeOrderInfoList(CommonUtil.convertList(wsMergeOrderDetail.mergeOrderInfo,
                wsMergeOrderInfo -> this.entityConvertMapper.convertWSMergeOrderInfo(wsMergeOrderInfo)));
        mergeOrderDetail.setMergeFlightInfoMapList(CommonUtil.convertList(wsMergeOrderDetail.mergeFlightInfoList,
                wsFlightInfo -> this.entityConvertMapper.convertWSMergeFlightInfo(wsFlightInfo)));

        return mergeOrderDetail;
    }
}
