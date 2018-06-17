package com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.getemaildataprocessor;

import com.ctrip.ibu.flight.internalws.business.common.IEmailCommonBusiness;
import com.ctrip.ibu.flight.internalws.business.common.IFlightCommonBusiness;
import com.ctrip.ibu.flight.internalws.common.businesshelper.BusinessHelper;
import com.ctrip.ibu.flight.internalws.common.components.Checker;
import com.ctrip.ibu.flight.internalws.common.template.ITemplateEngine;
import com.ctrip.ibu.flight.internalws.common.utils.LanguageUtil;
import com.ctrip.ibu.flight.internalws.common.utils.ThrowableUtils;
import com.ctrip.ibu.flight.internalws.contract.commontypes.EmailTemplateType;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailRequestType;
import com.ctrip.ibu.flight.internalws.models.annotation.GetEmailDataProcessor;
import com.ctrip.ibu.flight.internalws.models.common.CorpGroup;
import com.ctrip.ibu.flight.internalws.models.common.RequestHead;
import com.ctrip.ibu.flight.internalws.models.config.FreemarkerConfig;
import com.ctrip.ibu.flight.internalws.models.constant.EmailConst;
import com.ctrip.ibu.flight.internalws.models.constant.QConfigConst;
import com.ctrip.ibu.flight.internalws.models.errorcode.emailsend.GetEmailDataResultCode;
import com.ctrip.ibu.flight.internalws.models.exception.common.TemplateRenderException;
import com.ctrip.ibu.flight.internalws.models.exception.emailsend.GetEmailDataException;
import com.ctrip.ibu.flight.internalws.models.exception.repository.RepositoryException;
import com.ctrip.ibu.flight.internalws.models.flight.*;
import com.ctrip.ibu.flight.internalws.models.flight.Currency;
import com.ctrip.ibu.flight.internalws.models.flight.afterbooking.mergeorderdetail.*;
import com.ctrip.ibu.flight.internalws.models.message.*;
import com.ctrip.ibu.flight.internalws.models.viewmodel.paymentsuccess.MergePaymentViewModel;
import com.ctrip.ibu.flight.internalws.models.viewmodel.paymentsuccess.partialviewmodel.*;
import com.ctrip.ibu.flight.internalws.repository.IFlightRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 发送新版支付成功邮件处理类
 * @author : kyxie
 * createtime : 2018/5/17 12:10
 */
@Component
@GetEmailDataProcessor(name = "获取合并订单详情支付成功邮件数据处理类",processedEmailTypes = {EmailTemplateType.PaymentSuccess})
public class GetMergePaymentSuccessEmailDataProcessor extends GetEmailDataProcessBase {

    private final static boolean NEED_ORDER_DETAIL_CACHE = true;

    private IFlightCommonBusiness flightCommonBusiness;

    private IFlightRepository flightRepository;

    private ITemplateEngine templateEngine;

    private IEmailCommonBusiness emailCommonBusiness;

    @Inject
    public GetMergePaymentSuccessEmailDataProcessor(IFlightCommonBusiness flightCommonBusiness,
                                                    IFlightRepository flightRepository,
                                                    ITemplateEngine templateEngine,
                                                    IEmailCommonBusiness emailCommonBusiness){
        this.flightCommonBusiness = flightCommonBusiness;
        this.flightRepository = flightRepository;
        this.templateEngine = templateEngine;
        this.emailCommonBusiness = emailCommonBusiness;
    }

    /**
     * 验证参数
     *
     * @param request 请求
     * @throws GetEmailDataException 获取消息异常
     */
    @Override
    public void validateRequest(GetEmailDataRequest request) throws GetEmailDataException {
        Checker.checkWithThrowable((request.getSendEmailRequest().orderID == null || request.getSendEmailRequest().orderID <= 0)
                        && (request.getOrderDetail() == null || request.getOrderDetail().getOrderId() == null || request.getOrderDetail().getOrderId() <= 0),
                new GetEmailDataException(GetEmailDataResultCode.LACK_ORDERID,"订单号缺少或不合法"));

        Checker.checkWithThrowable(request.getOrderDetail() == null,new GetEmailDataException(GetEmailDataResultCode.ORDER_DETAIL_INVALID,"订单详情为NULL"));
    }

    /**
     * 获取消息发送信息
     *
     * @param request 获取消息数据请求
     * @return 获取消息数据响应
     * @throws GetEmailDataException 获取消息异常类型
     */
    @Override
    public GetEmailDataResponse getMessageSendInfo(GetEmailDataRequest request) throws GetEmailDataException {

        GetEmailDataResponse response = new GetEmailDataResponse();

        EmailSendAdditionalInfo additionalInfo = new EmailSendAdditionalInfo();

        SendEmailRequestType sendEmailRequest = request.getSendEmailRequest();
        OrderDetailModel orderDetail = getOrderDetail(sendEmailRequest);

        Checker.checkWithThrowable(orderDetail == null,new GetEmailDataException(GetEmailDataResultCode.ORDER_DETAIL_INVALID,"获取订单详情失败"));

        additionalInfo.setOrderDetail(orderDetail);
        request.setOrderDetail(orderDetail);

        EmailData emailData = new EmailData();

        MergeOrderDetailResponseEntity mergeOrderDetailResponse = getMergeOrderDetail(sendEmailRequest);
        MergeOrderDetailEntity mergeOrderDetail = mergeOrderDetailResponse.getOrderDetailList().get(0);

        preProcessMergeOrderDetail(mergeOrderDetail,orderDetail);

        MergePaymentViewModel mergePaymentVM = assemblyMergePaymentSuccessVM(mergeOrderDetail,orderDetail);

        //获取邮件内容
        emailData.setBodyContent(getBodyContent(sendEmailRequest,orderDetail,mergePaymentVM));

        response.setEmailData(emailData);
        response.setAdditionalInfo(additionalInfo);

        EmailSendConfig emailSendConfig = new EmailSendConfig();
        emailSendConfig.setOrderTrademark(orderDetail.getTrademark());
        emailSendConfig.setEmailSendLan(sendEmailRequest.languageType);
        response.setEmailConfig(emailSendConfig);

        return response;
    }

    /**
     * 预处理合并订单详情，设置每个订单的订单详情链接等
     * @param mergeOrderDetail 合并订单详情
     * @param orderDetail 主订单详情
     * */
    private void preProcessMergeOrderDetail(MergeOrderDetailEntity mergeOrderDetail,OrderDetailModel orderDetail){
        if (mergeOrderDetail != null && mergeOrderDetail.getMergeOrderInfoList() != null && mergeOrderDetail.getMergeOrderInfoList().size() > 0
                && orderDetail != null){
            for (MergeOrderInfoEntity mergeOrderInfo : mergeOrderDetail.getMergeOrderInfoList()){
                mergeOrderInfo.setOrderDetailUrl(BusinessHelper.generateOrderDetailUrl(mergeOrderInfo.getOrderId(),orderDetail.getFlightClass(),orderDetail.getAccessToken(),orderDetail.getServerFrom()));
            }
        }
    }

    /**
     * 获取邮件内容
     * @param sendEmailRequest 邮件发送请求
     * @param orderDetail 订单详情
     * @param mergePaymentVM 合并订单详情ViewModel
     * @throws GetEmailDataException 获取邮件数据异常
     * */
    private String getBodyContent(SendEmailRequestType sendEmailRequest ,OrderDetailModel orderDetail,MergePaymentViewModel mergePaymentVM) throws GetEmailDataException {

        String bodyContent = "";

        Map<String,Object> templateData = new HashMap<>();
        templateData.put(EmailConst.TemplateDataKey.ORDER_DETAIL,orderDetail);
        templateData.put(EmailConst.TemplateDataKey.MERGE_ORDER_DETAIL,mergePaymentVM);
        templateData.put(EmailConst.TemplateDataKey.APP_DOWNLOAD_URI,
                BusinessHelper.getAppDownloadUrl(LanguageUtil.mapLanguageTypeToLocale(sendEmailRequest.languageType),
                        orderDetail.getAllianceOrderInfo() == null ? null : orderDetail.getAllianceOrderInfo().getAllianceId(),
                        orderDetail.getAllianceOrderInfo() == null ? null : orderDetail.getAllianceOrderInfo().getSid()));

        //邮件头尾
        EmailHeaderAndFooterResponse headerAndFooter = emailCommonBusiness.getEmailHeaderAndFooter(orderDetail,sendEmailRequest.emailTemplateType,sendEmailRequest.languageType);
        String header = "";
        String footer = "";
        if (headerAndFooter != null){
            header = headerAndFooter.getEmailHeader() == null ? "" : headerAndFooter.getEmailHeader();
            footer = headerAndFooter.getEmailFooter() == null ? "" : headerAndFooter.getEmailFooter();
        }
        templateData.put(EmailConst.TEMPLATEDATAKEY_COMMONHEADER,header);
        templateData.put(EmailConst.TEMPLATEDATAKEY_COMMONFOOTER,footer);

        FreemarkerConfig freemarkerConfig = new FreemarkerConfig();
        freemarkerConfig.setFreemarkerLocale(LanguageUtil.mapLanguageTypeToLocale(sendEmailRequest.languageType));

        try {
            bodyContent = templateEngine.renderTemplate(getEmailTemplateName(orderDetail.getCorpGroup()),templateData,freemarkerConfig);
        } catch (TemplateRenderException e) {
            throw ThrowableUtils.getThrowWithCause(new GetEmailDataException(GetEmailDataResultCode.TEMPLATE_RENDER_FAILED,"模板渲染失败"),e);
        }

        return bodyContent;
    }

    /**
     * 获取模板名称
     * @param corpGroup 订单所属企业组
     * @return 模板名
     * */
    private String getEmailTemplateName(CorpGroup corpGroup){
        if (corpGroup == CorpGroup.SC){
            return QConfigConst.TEMPLATEFILENAME_PAYMENTSUCCESSEMAIL_SC;
        } else {
            return QConfigConst.TEMPLATEFILENAME_PAYMENTSUCCESSEMAIL;
        }
    }

    private OrderDetailModel getOrderDetail(SendEmailRequestType request){

        RequestHead requestHead = new RequestHead();
        requestHead.setUid(request.uid);
        requestHead.setLanguage(request.languageType);

        return flightCommonBusiness.getOrderDetail(request.orderID,requestHead,NEED_ORDER_DETAIL_CACHE);
    }

    /**
     * 组装ViewModel
     * @param mergeOrderDetail 合并订单详情
     * @param orderDetail 订单详情
     * @return ViewModel
     * */
    private MergePaymentViewModel assemblyMergePaymentSuccessVM(MergeOrderDetailEntity mergeOrderDetail,OrderDetailModel orderDetail){
        MergePaymentViewModel mergePaymentVM = new MergePaymentViewModel();

        mergePaymentVM.setBasicInfo(getOrderBasicInfo(mergeOrderDetail,orderDetail));
        mergePaymentVM.setPaymentInfo(getPaymentInfoSummary(mergeOrderDetail));
        mergePaymentVM.setPassengerGroupList(getPassengerGroupInfoList(mergeOrderDetail));

        return mergePaymentVM;
    }

    /**
     * 获取支付概要
     * @param mergeOrderDetail Merge订单详情
     * @return 支付概要
     * */
    private PaymentSummary getPaymentInfoSummary(MergeOrderDetailEntity mergeOrderDetail){
        PaymentSummary paymentSummary = new PaymentSummary();

        PaymentInfoEntity paymentInfo = mergeOrderDetail.getPaymentInfoEntity();
        if (paymentInfo != null){
            paymentSummary.setPaymentCurrency(Currency.valueOf(paymentInfo.getCurrencyType()));
            paymentSummary.setTotalPrice(paymentInfo.getPayTotalPrice());
        }

        List<MergePassengerFlightInfoMapEntity> mergePassengerFlightInfoList = mergeOrderDetail.getPassengerFlightInfoMapList();
        if (mergePassengerFlightInfoList != null && mergePassengerFlightInfoList.size() > 0){
            List<PassengerOrderInfo> passengerOrderInfoList = new ArrayList<>();
            for (MergePassengerFlightInfoMapEntity mergePassengerFlightInfo : mergePassengerFlightInfoList){
                PassengerOrderInfo passengerOrderInfo = new PassengerOrderInfo();
                passengerOrderInfo.setPassengerName(mergePassengerFlightInfo.getPassengerInfo().getName());
                passengerOrderInfo.setPassengerType(mergePassengerFlightInfo.getPassengerInfo().getPassengerType());
                passengerOrderInfoList.add(passengerOrderInfo);
            }
            paymentSummary.setPassengerOrderInfoList(passengerOrderInfoList);
        }
        return paymentSummary;
    }

    /**
     * 获取合并订单基础信息
     * @param mergeOrderDetail 合并订单详情
     * @param orderDetail 订单详情
     * */
    private BasicInfo getOrderBasicInfo(MergeOrderDetailEntity mergeOrderDetail,OrderDetailModel orderDetail){
        BasicInfo orderBasicInfo = new BasicInfo();
        orderBasicInfo.setTrademark(orderDetail.getTrademark());
        orderBasicInfo.setCorpGroup(orderDetail.getCorpGroup());
        orderBasicInfo.setLatestDraftTime(mergeOrderDetail.getLatestDraftTime());
        orderBasicInfo.setOrderCount(mergeOrderDetail.getMergeOrderInfoList().size());
        orderBasicInfo.setFlightWay(BusinessHelper.parseFlightWay(mergeOrderDetail.getOrderBasicInfo().getFlightWay()));
        orderBasicInfo.setRouteSummaryList(getSearchFlightSummary(mergeOrderDetail));
        return orderBasicInfo;
    }

    /**
     * 获取原始的航班搜索记录
     * */
    private List<String> getSearchFlightSummary(MergeOrderDetailEntity mergeOrderDetail){

        List<String> searchFlightSummaryList = new ArrayList<>();

        List<MergeFlightInfoMapEntity> flightInfoList = mergeOrderDetail.getMergeFlightInfoMapList();

        if ("S".equalsIgnoreCase(mergeOrderDetail.getOrderBasicInfo().getFlightWay()) || "D".equalsIgnoreCase(mergeOrderDetail.getOrderBasicInfo().getFlightWay())){
            searchFlightSummaryList.add(String.format("%s - %s",flightInfoList.get(0).getFlightColumnInfo().getdCity().getName(),flightInfoList.get(0).getFlightColumnInfo().getaCity().getName()));
        } else if ("M".equalsIgnoreCase(mergeOrderDetail.getOrderBasicInfo().getFlightWay())){
            for (int i = 0;i < flightInfoList.size();i++){

                StringBuilder sb = new StringBuilder();

                FlightColumn flightColumn = flightInfoList.get(i).getFlightColumnInfo();

                if (i == 0){
                    sb.append(flightColumn.getdCity().getName());
                }

                sb.append(String.format(" - %s",flightColumn.getaCity().getName()));

                searchFlightSummaryList.add(sb.toString());
            }
        }

        return searchFlightSummaryList;
    }

    /**
     * 获取合并订单详情结果
     * @param sendEmailRequest 邮件发送请求
     * @return 合并订单详情结果
     * @throws GetEmailDataException 获取邮件数据异常
     * */
    private MergeOrderDetailResponseEntity getMergeOrderDetail(SendEmailRequestType sendEmailRequest) throws GetEmailDataException {
        MergeOrderDetailRequestEntity getMergeOrderDetailRequest = new MergeOrderDetailRequestEntity();
        RequestHead requestHead = new RequestHead();
        requestHead.setUid(sendEmailRequest.uid);
        requestHead.setLanguage(sendEmailRequest.languageType);

        List<Long> mergeOrderIdList = new ArrayList<>();
        mergeOrderIdList.add(sendEmailRequest.orderID);

        getMergeOrderDetailRequest.setRequestHead(requestHead);
        getMergeOrderDetailRequest.setOrderIdList(mergeOrderIdList);

        MergeOrderDetailResponseEntity getMergeOrderDetailResponse = null;
        try {
            getMergeOrderDetailResponse = flightRepository.getMergeOrderDetail(getMergeOrderDetailRequest);
        } catch (RepositoryException e) {
            throw ThrowableUtils.getThrowWithCause(new GetEmailDataException(GetEmailDataResultCode.LACK_MERGE_ORDER_DETAIL,"合并订单详情查询异常"),e);
        }

        if (getMergeOrderDetailResponse == null || getMergeOrderDetailResponse.getOrderDetailList() == null || getMergeOrderDetailResponse.getOrderDetailList().isEmpty()){
            throw new GetEmailDataException(GetEmailDataResultCode.LACK_MERGE_ORDER_DETAIL,"合并订单详情查询无结果");
        }

        return getMergeOrderDetailResponse;
    }

    /**
     * 获取乘机人按乘机人类型的分组及预订航班等信息
     * */
    private List<PassengerGroup> getPassengerGroupInfoList(MergeOrderDetailEntity mergeOrderDetail){

        List<PassengerGroup> passengerGroupList = new ArrayList<>();

        if (mergeOrderDetail != null && mergeOrderDetail.getPassengerFlightInfoMapList() != null && mergeOrderDetail.getPassengerFlightInfoMapList().size() > 0){

            List<MergeOrderInfoEntity> mergeOrderInfoList = mergeOrderDetail.getMergeOrderInfoList();
            List<MergeFlightInfoMapEntity> mergeFlightInfoList = mergeOrderDetail.getMergeFlightInfoMapList();

            Map<PassengerType,List<MergePassengerFlightInfoMapEntity>> passengerTypeGroup = mergeOrderDetail.getPassengerFlightInfoMapList().stream()
                    .collect(Collectors.groupingBy((item) -> item.getPassengerInfo().getPassengerType()));

            if (passengerTypeGroup != null && !passengerTypeGroup.isEmpty()){
                for (Map.Entry<PassengerType,List<MergePassengerFlightInfoMapEntity>> entry : passengerTypeGroup.entrySet()) {
                    PassengerGroup passengerGroup = new PassengerGroup();
                    PassengerType passengerType = entry.getKey();

                    //乘客类型
                    passengerGroup.setPassengerType(passengerType);

                    //所有乘客信息
                    List<PassengerOrderInfo> passengerOrderInfoList = new ArrayList<>();

                    List<MergePassengerFlightInfoMapEntity> passengerList = entry.getValue();
                    for (MergePassengerFlightInfoMapEntity passenger : passengerList) {
                        PassengerOrderInfo passengerOrder = new PassengerOrderInfo();
                        //乘客基本信息
                        passengerOrder.setPassengerType(passengerType);
                        passengerOrder.setPassengerName(passenger.getPassengerInfo().getName());
                        passengerOrder.setOrderInfoList(new ArrayList<>());

                        List<OrderIdFlightInfoMapEntity> passengerOrderFlightList = passenger.getOrderIdFlightInfoMapList();

                        //按订单分组
                        Map<Long,List<OrderIdFlightInfoMapEntity>> orderFlightGroup = passengerOrderFlightList.stream()
                                .collect(Collectors.groupingBy((o -> o.getOrderId())));

                        for (Map.Entry<Long,List<OrderIdFlightInfoMapEntity>> orderFlightGroupEntry : orderFlightGroup.entrySet()){

                            Long orderId = orderFlightGroupEntry.getKey();//订单号
                            List<OrderIdFlightInfoMapEntity> flightTokenList = orderFlightGroupEntry.getValue();//航班Token

                            //1.订单基础信息及支付信息
                            OrderInfoSummary orderInfoSummary = new OrderInfoSummary();
                            orderInfoSummary.setOrderId(orderId);

                            MergeOrderInfoEntity mergeOrderInfo = (MergeOrderInfoEntity) mergeOrderInfoList.stream().filter(orderInfoEntity -> orderId.equals(orderInfoEntity.getOrderId())).toArray()[0];
                            PaymentInfoEntity mergePaymentInfo = mergeOrderInfo.getPaymentInfo();

                            orderInfoSummary.setTotalPayment(mergePaymentInfo.getPayTotalPrice());
                            orderInfoSummary.setCurrency(Currency.valueOf(mergeOrderDetail.getPaymentInfoEntity().getCurrencyType()));
                            orderInfoSummary.setPaymentItemDetailList(mergePaymentInfo.getPaymentDetailEntityList());
                            orderInfoSummary.setFlightWay(BusinessHelper.parseFlightWay(mergeOrderInfo.getFlightWay()));
                            orderInfoSummary.setOrderDetailUrl(mergeOrderInfo.getOrderDetailUrl());

                            //2.航班信息
                            orderInfoSummary.setFlightInfoList(new ArrayList<>());

                            List<FlightColumn> flightColumnList = new ArrayList<>();

                            for (OrderIdFlightInfoMapEntity orderIdFlightMap : flightTokenList){
                                List<MergeFlightInfoMapEntity> mergeFlightDetailList = mergeFlightInfoList.stream().filter(flightInfo -> orderIdFlightMap.getFltToken().equals(flightInfo.getFltToken())).collect(Collectors.toList());
                                flightColumnList.add(mergeFlightDetailList.get(0).getFlightColumnInfo());
                            }

                            //航班信息分组
                            Map<Integer,List<FlightColumn>> flightGroupMap = flightColumnList.stream().collect(Collectors.groupingBy(f -> f.getSegmentNo()));

                            for (Map.Entry<Integer,List<FlightColumn>> flightGroupEntry : flightGroupMap.entrySet()){
                                FlightInfo flightRouteInfo = new FlightInfo();
                                flightRouteInfo.setSegmentNo(flightGroupEntry.getKey());
                                flightRouteInfo.setColumnList(flightGroupEntry.getValue());
                                orderInfoSummary.getFlightInfoList().add(flightRouteInfo);
                            }

                            postProcessFlightInfo(orderInfoSummary.getFlightInfoList());

                            passengerOrder.getOrderInfoList().add(orderInfoSummary);
                        }

                        passengerOrderInfoList.add(passengerOrder);
                    }
                    passengerGroup.setPassengerOrderInfoList(passengerOrderInfoList);
                    passengerGroupList.add(passengerGroup);
                }
            }
        }

        return passengerGroupList;
    }

    private void postProcessFlightInfo(List<FlightInfo> flightInfoList){
        if (flightInfoList != null && flightInfoList.size() > 0){

            for (FlightInfo flightInfo : flightInfoList){
                StringBuilder flightRouteInfoSummary = new StringBuilder();
                flightRouteInfoSummary.append(String.format("%s",flightInfo.getColumnList().get(0).getdCity().getName()));

                int columnCnt = flightInfo.getColumnList().size();

                for (int i = 0; i < columnCnt;i++){
                    FlightColumn flightColumn = flightInfo.getColumnList().get(i);
                    if (i == 0){
                        flightInfo.setdCity(flightColumn.getdCity());
                        flightInfo.setdDate(new Date(flightColumn.getdDate().getTime() * 1000));
                    }
                    if (i == columnCnt - 1){
                        flightInfo.setaCity(flightColumn.getaCity());
                        flightInfo.setaDate(new Date(flightColumn.getaDate().getTime() * 1000));
                    }

                    flightRouteInfoSummary.append(String.format("-%s",flightColumn.getaCity().getName()));
                }

                flightInfo.setFlightRouteSummary(flightRouteInfoSummary.toString());
            }
        }
    }

}
