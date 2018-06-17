package com.ctrip.ibu.flight.internalws.business.common;

import com.ctrip.ibu.flight.internalws.common.businesshelper.BusinessHelper;
import com.ctrip.ibu.flight.internalws.common.cache.ICache;
import com.ctrip.ibu.flight.internalws.common.cache.LocalCache;
import com.ctrip.ibu.flight.internalws.common.i18n.IMessageResource;
import com.ctrip.ibu.flight.internalws.common.log.LoggerHelper;
import com.ctrip.ibu.flight.internalws.common.utils.*;
import com.ctrip.ibu.flight.internalws.contract.commontypes.LanguageType;
import com.ctrip.ibu.flight.internalws.models.common.CacheKeyManager;
import com.ctrip.ibu.flight.internalws.models.common.RequestHead;
import com.ctrip.ibu.flight.internalws.models.constant.*;
import com.ctrip.ibu.flight.internalws.models.exception.repository.RepositoryException;
import com.ctrip.ibu.flight.internalws.models.flight.*;
import com.ctrip.ibu.flight.internalws.models.log.IndexedLogTag;
import com.ctrip.ibu.flight.internalws.models.log.LogLevel;
import com.ctrip.ibu.flight.internalws.models.repository.dao.ibuflightdb.Localeidmappingconfig;
import com.ctrip.ibu.flight.internalws.models.util.EncryptAccessTokenRequestModel;
import com.ctrip.ibu.flight.internalws.models.util.EncryptAccessTokenResponseModel;
import com.ctrip.ibu.flight.internalws.models.util.TokenModel;
import com.ctrip.ibu.flight.internalws.repository.IAccountRepository;
import com.ctrip.ibu.flight.internalws.repository.IFlightRepository;
import com.ctrip.ibu.flight.internalws.repository.dao.ibuflightdb.LocaleidMappingConfigDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 机票订单业务
 * 主要用于一些需要读取接口数据，在Common层无法进行处理的方法
 * Created by kyxie on 2017/11/1.
 */
@Component("flightCommonBusiness")
public class FlightCommonBusiness implements IFlightCommonBusiness {

    private final static Integer TRAVELERTIME_OFFSETDATE = 365;

    private final static Integer ACCESSTOKEN_RETRYTIMES = 2;

    private final static Integer ACCESSTOKEN_RETRYINTERVAL = 1000;

    private final static String ACCESS_TOKEN_RESULT_SUCCESS = "SUCCESS";

    private final static String ACCESS_TOKEN_RESULT_RETRY_SUCCESS = "RetrySuccess";

    private final static String ACCESS_TOKEN_RESULT_RETRY_FAILED = "RetryFailed";

    private ICache cache;

    private IMessageResource messageResource;

    private IAccountRepository accountRepository;

    private IFlightRepository flightRepository;

    @Inject
    public FlightCommonBusiness(@Named("guavaCache") ICache cache,
                                @Named(BeanConst.BEANNAME_LOCALMESSAGERESOURCEENGINE) IMessageResource messageResource,
                                @Named("accountRepository") IAccountRepository accountRepository,
                                @Named("flightRepository") IFlightRepository flightRepository) {
        this.cache = cache;
        this.messageResource = messageResource;
        this.accountRepository = accountRepository;
        this.flightRepository = flightRepository;
    }


    /**
     * 获取鉴权AccessToken
     * @param orderIdList 需要查看的所有订单列表
     * @param uid 用户ID
     * @param email 联系人邮箱
     * @param orderTravelTime 订单基准时间(公共会根据这个基准时间+7天的时间范围才允许查看订单)
     */
    @Override
    public String getAccessToken(List<Long> orderIdList, String uid, String email, Calendar orderTravelTime) {
        if (orderIdList == null || orderIdList.isEmpty() || StringUtils.isBlank(uid) || StringUtils.isBlank(email)) {
            return null;
        }

        EncryptAccessTokenResponseModel result;

        TokenModel tokenModel = new TokenModel();
        tokenModel.setEmail(email);
        tokenModel.setOrderIds(orderIdList);
        tokenModel.setUid(uid);
        tokenModel.setOrderTravelTime(orderTravelTime);

        EncryptAccessTokenRequestModel request = new EncryptAccessTokenRequestModel();
        request.setTokenModel(tokenModel);

        try {
            for (int i = 0;i < ACCESSTOKEN_RETRYTIMES;i++){
                LoggerHelper.appendResponseContent(String.format("第%d次尝试调用encryptToken接口...",i+1));
                result = accountRepository.encryptAccessToken(request);
                if (result == null || StringUtils.isBlank(result.getEncryptToken())){
                    LoggerHelper.addIndexedLogTag(IndexedLogTag.AccessTokenReturnCode,result == null ? "NULL" : result.getReturnCode());
                    if (i == ACCESSTOKEN_RETRYTIMES - 1){
                        LoggerHelper.appendResponseContent(String.format("调用encryptToken接口已经尝试最大次数:%d，仍然没有返回AccessToken",ACCESSTOKEN_RETRYTIMES));
                    } else {
                        LoggerHelper.appendResponseContent(String.format("调用encryptToken接口返回为NULL,线程睡眠%dms",ACCESSTOKEN_RETRYINTERVAL));
                        try {
                            Thread.sleep(ACCESSTOKEN_RETRYINTERVAL);
                        } catch (InterruptedException e) {
                            LoggerHelper.appendResponseContent(String.format("Thread.sleep异常，异常信息:%s",ThrowableUtils.getExceptionDesc(e)));
                        }
                    }
                } else {
                    if (i == 0){
                        LoggerHelper.addIndexedLogTag(IndexedLogTag.AccessTokenResult,ACCESS_TOKEN_RESULT_SUCCESS);
                    } else {
                        LoggerHelper.addIndexedLogTag(IndexedLogTag.AccessTokenResult,ACCESS_TOKEN_RESULT_RETRY_SUCCESS);
                    }
                    LoggerHelper.addIndexedLogTag(IndexedLogTag.AccessTokenReturnCode,result.getReturnCode());
                    return result.getEncryptToken();
                }
            }
        } catch (RepositoryException e) {
            LoggerHelper.appendResponseContent(String.format("获取AccessToken异常,异常信息:\n%s",ThrowableUtils.getExceptionDesc(e)));
        }

        LoggerHelper.addIndexedLogTag(IndexedLogTag.AccessTokenResult,ACCESS_TOKEN_RESULT_RETRY_FAILED);
        return "";
    }

    /**
     * 获取订单详情列表
     *
     * @param orderId        订单号
     * @param requestHead 请求头
     * @param needCache      是否需要读取缓存
     */
    @Override
    public OrderDetailModel getOrderDetail(Long orderId, RequestHead requestHead, boolean needCache) {
        if (orderId <= 0) {
            return null;
        }

        if (requestHead == null){
            requestHead = new RequestHead();
        }
        if (requestHead.getLanguage() == null || requestHead.getLanguage() == LanguageType.unspecified){
            requestHead.setLanguage(LanguageType.en_US);
        }

        OrderDetailModel orderDetail = null;

        List<Long> orderIds = new ArrayList<>();
        orderIds.add(orderId);

        String cacheKey = String.format(CacheConst.ORDERDETAIL_CACHEKEY_PATTERN, orderId, String.valueOf(requestHead.getLanguage())).toLowerCase();

        if (needCache) {
            orderDetail = (OrderDetailModel) cache.get(cacheKey);
            LoggerHelper.appendResponseContent(String.format("获取【%d】订单详情缓存Key:%s，缓存结果：（GDPR字段结果不输出）\n%s",orderId,cacheKey, SerializeUtil.toJsonWithGdpr(orderDetail)));
        }

        if (orderDetail == null) {

            if (needCache) {
                LoggerHelper.appendResponseContent(String.format("订单号--%d--获取缓存无结果,缓存key:%s，开始读取接口...", orderId,cacheKey));
            }

            OrderDetailSearchRequestModel searchRequest = new OrderDetailSearchRequestModel();
            searchRequest.setRequestHead(requestHead);
            searchRequest.setOrderIds(orderIds);

            try {
                List<OrderDetailModel> orderDetailList = flightRepository.getOrderDetailList(searchRequest);
                if (orderDetailList != null && !orderDetailList.isEmpty()) {
                    for (OrderDetailModel orderDetailModel : orderDetailList) {
                        generateOrderCorpGroup(orderDetailModel);
                        generateOrderTrademark(orderDetailModel);
                        generateOrderDetail(orderDetailModel);
                        fillOrderDetailMulLan(orderDetailModel,requestHead.getLanguage());
                        orderDetailModel.setFirstPageUrl(BusinessHelper.getFlightFirstPageUrl(orderDetailModel.getServerFrom()));
                    }
                    orderDetail = orderDetailList.get(0);
                    cache.put(cacheKey, orderDetail);//写入缓存
                }
            } catch (RepositoryException e) {
                LoggerHelper.appendResponseContent(String.format("调用%d订单详情发生异常,异常类型:%s,异常信息:\n%s", orderId, e.getErrorSource(), ThrowableUtils.getExceptionDesc(e)));
                LoggerHelper.addIndexedLogTag(IndexedLogTag.Note, LogConst.LOGNOTE_ORDERDETAILFAIL);
                LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel, LogLevel.WARNING.toString());
            }
        } else {
            LoggerHelper.appendResponseContent(String.format("订单号%d命中缓存！缓存名称:%s", orderId,cacheKey));
        }

        return orderDetail;
    }

    @Override
  public List<Localeidmappingconfig> queryAll(){
    List<Localeidmappingconfig> localeidmappingconfigList = null;
    if(!LocalCache.containsKey(CacheKeyManager.CacheKey.LocalidMappingConfig.toString())){
        try {
            localeidmappingconfigList = new LocaleidMappingConfigDao().queryAll();
            LocalCache.put(CacheKeyManager.CacheKey.LocalidMappingConfig.toString(), localeidmappingconfigList);
        } catch (SQLException e){
                LoggerHelper.appendResponseContent(String.format("LocalidMappingConfig数据加载异常，异常信息:\n%s", ThrowableUtils.getExceptionDesc(e)));
            }
        }
        return localeidmappingconfigList;
    }

    /**
     * 生成订单详情多语言
     * @param orderDetail 订单详情实体
     * */
    private void fillOrderDetailMulLan(OrderDetailModel orderDetail,LanguageType languageType){
        if (orderDetail != null){
            Map<String,String> mulLanMap = messageResource.getResourceBundle(LanguageUtil.mapLanguageTypeToLocale(languageType),orderDetail.getTrademark());
            if (orderDetail.getPaymentInfo() != null){
                switch (orderDetail.getPaymentInfo().getPayMethod()){
                    case CASH:
                        orderDetail.getPaymentInfo().setPayMethodDesc(mulLanMap.get(MessageResourceConst.MRKEY_CASH));
                        break;
                    case CREDITCARD:
                        orderDetail.getPaymentInfo().setPayMethodDesc(mulLanMap.get(MessageResourceConst.MRKEY_CREDITCARD));
                        break;
                    case DEPOSITCARD:
                        orderDetail.getPaymentInfo().setPayMethodDesc(mulLanMap.get(MessageResourceConst.MRKEY_DEPOSITCARD));
                        break;
                    case PAYPAL:
                        orderDetail.getPaymentInfo().setPayMethodDesc(mulLanMap.get(MessageResourceConst.MRKEY_PAYPAL));
                        break;
                    case DQPAY:
                        orderDetail.getPaymentInfo().setPayMethodDesc(mulLanMap.get(MessageResourceConst.MRKEY_DQPAY));
                        break;
                }
            }

            //格式化行李额
            List<FormatBaggageInfo> formatBaggageInfoList = orderDetail.getFormatBaggageInfoList();
            if (formatBaggageInfoList == null || formatBaggageInfoList.size() > 0){
                for (FlightInfo flightInfo : orderDetail.getFlightInfoList()){
                    for (FlightColumn columnInfo : flightInfo.getColumnList()){
                        List<FormatBaggageInfo> columnFormatBaggageList = CommonUtil.selectList(formatBaggageInfoList,formatBaggageInfo -> columnInfo.getSequenceNo().equals(formatBaggageInfo.getSequenceNo()));
                        if (columnFormatBaggageList != null && columnFormatBaggageList.size() > 0){
                            FormatBaggageInfo formatBaggageInfo = columnFormatBaggageList.get(0);
                            columnInfo.setBaggageInfoFree(getFormatBaggageDesc(orderDetail.getOrderPassengerType(),formatBaggageInfo,mulLanMap));
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取格式化行李额描述
     * @param orderType 订单乘客类型
     * @param formatBaggageInfo 格式化行李额
     * @param mulLan 多语言
     * */
    private String getFormatBaggageDesc(PassengerType orderType,FormatBaggageInfo formatBaggageInfo,Map<String,String> mulLan){
        if (formatBaggageInfo == null || orderType == null){
            return null;
        }

        StringBuilder formatBaggageDesc = new StringBuilder();

        switch (orderType){
            case CHD:
                if (formatBaggageInfo.getChildPiece() == null || formatBaggageInfo.getChildPiece() <= -1){
                    //件数未知
                    if (formatBaggageInfo.getChildWeight() != null){
                        if (formatBaggageInfo.getChildWeight().compareTo(BigDecimal.ZERO) == 1){
                            //无件数，有重量话述
                            formatBaggageDesc.append(mulLan.get(MessageResourceConst.MRKEY_FORMATBAGGAGEDESC_ONLYWEIGHT).replace("${weight}",String.valueOf(formatBaggageInfo.getChildWeight())));
                        } else if (formatBaggageInfo.getChildWeight().compareTo(BigDecimal.ZERO) == 0){
                            //无件数，无重量话述(无免费行李额)
                            formatBaggageDesc.append(mulLan.get(MessageResourceConst.MRKEY_NOFREEBAGGAGE));
                        }
                    }
                } else if (formatBaggageInfo.getChildPiece() == 0) {
                    //无免费行李额
                    formatBaggageDesc.append(mulLan.get(MessageResourceConst.MRKEY_NOFREEBAGGAGE));
                } else{
                    //有件数
                    if (formatBaggageInfo.getChildWeight() == null || formatBaggageInfo.getChildWeight().compareTo(BigDecimal.ZERO) != 1){
                        formatBaggageDesc.append(mulLan.get(MessageResourceConst.MRKEY_FORMATBAGGAGEDESC_ONLYPIECE).replace("${piece}",String.valueOf(formatBaggageInfo.getChildPiece())));
                    } else {
                        //有件数有重量话述
                        formatBaggageDesc.append(mulLan.get(MessageResourceConst.MRKEY_FORMATBAGGAGEDESC_BOTHPIECEWEIGHT)
                                .replace("${weight}",String.valueOf(formatBaggageInfo.getChildWeight()))
                                .replace("${piece}",String.valueOf(formatBaggageInfo.getChildPiece())));
                    }
                }
                break;
            case INF:
                if (formatBaggageInfo.getInfantPiece() == null || formatBaggageInfo.getInfantPiece() <= -1){
                    //件数未知
                    if (formatBaggageInfo.getInfantWeight() != null){
                        if (formatBaggageInfo.getInfantWeight().compareTo(BigDecimal.valueOf(0L)) == 1){
                            //无件数，有重量话述
                            formatBaggageDesc.append(mulLan.get(MessageResourceConst.MRKEY_FORMATBAGGAGEDESC_ONLYWEIGHT).replace("${weight}",String.valueOf(formatBaggageInfo.getInfantWeight())));
                        } else if (formatBaggageInfo.getInfantWeight().compareTo(BigDecimal.valueOf(0L)) == 0){
                            //无件数，无重量话述(无免费行李额)
                            formatBaggageDesc.append(mulLan.get(MessageResourceConst.MRKEY_NOFREEBAGGAGE));
                        }
                    }
                } else if (formatBaggageInfo.getInfantPiece() == 0) {
                    //无免费行李额
                    formatBaggageDesc.append(mulLan.get(MessageResourceConst.MRKEY_NOFREEBAGGAGE));
                } else{
                    //有件数
                    if (formatBaggageInfo.getInfantWeight() == null || formatBaggageInfo.getInfantWeight().compareTo(BigDecimal.ZERO) != 1){
                        formatBaggageDesc.append(mulLan.get(MessageResourceConst.MRKEY_FORMATBAGGAGEDESC_ONLYPIECE).replace("${piece}",String.valueOf(formatBaggageInfo.getInfantPiece())));
                    } else {
                        //有件数有重量话述
                        formatBaggageDesc.append(mulLan.get(MessageResourceConst.MRKEY_FORMATBAGGAGEDESC_BOTHPIECEWEIGHT)
                                .replace("${weight}",String.valueOf(formatBaggageInfo.getInfantWeight()))
                                .replace("${piece}",String.valueOf(formatBaggageInfo.getInfantPiece())));
                    }
                }
                break;
            default:
                if (formatBaggageInfo.getAdultPiece() == null || formatBaggageInfo.getAdultPiece() <= -1){
                    //件数未知
                    if (formatBaggageInfo.getAdultWeight() != null){
                        if (formatBaggageInfo.getAdultWeight().compareTo(BigDecimal.valueOf(0L)) == 1){
                            //无件数，有重量话述
                            formatBaggageDesc.append(mulLan.get(MessageResourceConst.MRKEY_FORMATBAGGAGEDESC_ONLYWEIGHT).replace("${weight}",String.valueOf(formatBaggageInfo.getAdultWeight())));
                        } else if (formatBaggageInfo.getAdultWeight().compareTo(BigDecimal.valueOf(0L)) == 0){
                            //无件数，无重量话述(无免费行李额)
                            formatBaggageDesc.append(mulLan.get(MessageResourceConst.MRKEY_NOFREEBAGGAGE));
                        }
                    }
                } else if (formatBaggageInfo.getAdultPiece() == 0) {
                    //无免费行李额
                    formatBaggageDesc.append(mulLan.get(MessageResourceConst.MRKEY_NOFREEBAGGAGE));
                } else{
                    //有件数
                    if (formatBaggageInfo.getAdultWeight() == null || formatBaggageInfo.getAdultWeight().compareTo(BigDecimal.ZERO) != 1){
                        formatBaggageDesc.append(mulLan.get(MessageResourceConst.MRKEY_FORMATBAGGAGEDESC_ONLYPIECE).replace("${piece}",String.valueOf(formatBaggageInfo.getAdultPiece())));
                    } else {
                        //有件数有重量话述
                        formatBaggageDesc.append(mulLan.get(MessageResourceConst.MRKEY_FORMATBAGGAGEDESC_BOTHPIECEWEIGHT)
                                .replace("${weight}",String.valueOf(formatBaggageInfo.getAdultWeight()))
                                .replace("${piece}",String.valueOf(formatBaggageInfo.getAdultPiece())));
                    }
                }
                break;
        }

        if (StringUtils.isNotBlank(formatBaggageInfo.getDesc())){
            formatBaggageDesc.append(formatBaggageInfo.getDesc());
        }

        return formatBaggageDesc.toString();
    }

    /**
     * 生成订单所属Corp组
     */
    private void generateOrderCorpGroup(OrderDetailModel orderDetail) {
        if (orderDetail != null) {
            orderDetail.setCorpGroup(BusinessHelper.getCorpGroup(orderDetail.getServerFrom()));
        }
    }

    /**
     * 生成订单商标
     */
    private void generateOrderTrademark(OrderDetailModel orderDetail) {
        if (orderDetail != null) {
            orderDetail.setTrademark(BusinessHelper.getTrademark(orderDetail.getServerFrom()));
        }
    }

    /**
     * 生成订单详情URL(不要忘记关联订单)
     * @param orderDetail 订单详情
     */
    private void generateOrderDetail(OrderDetailModel orderDetail) {

        if (orderDetail != null) {

            //设置主订单订单详情
            orderDetail.setOrderDetailUrl(getOrderDetailUrl(orderDetail, null));

            //设置关联订单订单详情
            if (orderDetail.getRelatedOrderInfoList() != null && !orderDetail.getRelatedOrderInfoList().isEmpty()) {
                for (OrderSummary relatedOrderInfo : orderDetail.getRelatedOrderInfoList()) {
                    OrderDetailModel relatedOrderSummaryInfo = new OrderDetailModel();
                    relatedOrderSummaryInfo.setOrderId(relatedOrderInfo.getOrderId());
                    relatedOrderSummaryInfo.setFlightClass(orderDetail.getFlightClass());
                    relatedOrderSummaryInfo.setServerFrom(orderDetail.getServerFrom());
                    relatedOrderSummaryInfo.setTrademark(orderDetail.getTrademark());
                    relatedOrderSummaryInfo.setCorpGroup(orderDetail.getCorpGroup());

                    relatedOrderInfo.setOrderDetailUrl(getOrderDetailUrl(relatedOrderSummaryInfo,orderDetail.getAccessToken()));
                }
            }
        }
    }

    /**
     * 获取指定订单的订单详情
     * @param orderDetail 订单详情
     * @param accessToken 指定AccessToken
     */
    private String getOrderDetailUrl(OrderDetailModel orderDetail,String accessToken) {
        if (orderDetail == null) {
            return null;
        }

        if (StringUtils.isBlank(accessToken)){
            LoggerHelper.appendResponseContent("AccessToken为空，请求接口");
            accessToken = getAccessToken(orderDetail);
            orderDetail.setAccessToken(accessToken);
        } else {
            LoggerHelper.appendResponseContent("AccessToken不为空，不请求接口");
        }

        return BusinessHelper.generateOrderDetailUrl(orderDetail.getOrderId(),orderDetail.getFlightClass(),accessToken,orderDetail.getServerFrom());
    }

    /**
     * 获取指定订单的AccessToken
     */
    @Override
    public String getAccessToken(OrderDetailModel orderDetail) {

        List<FlightInfo> flightInfoList = orderDetail.getFlightInfoList();
        if (flightInfoList == null || flightInfoList.isEmpty()) {
            return "";
        }

        Calendar orderTravelTime = Calendar.getInstance();
        orderTravelTime.setTime(DateUtil.addDays(orderDetail.getOrderDate(),TRAVELERTIME_OFFSETDATE));
        ContactInfo contactInfo = orderDetail.getContactInfo();
        if (contactInfo == null || contactInfo.getEmail() == null || contactInfo.getEmail().isEmpty()) {
            return "";
        }
        List<Long> allOrderList = new ArrayList<>();
        allOrderList.add(orderDetail.getOrderId());
        List<OrderSummary> relatedOrderInfoList = orderDetail.getRelatedOrderInfoList();
        if (relatedOrderInfoList != null && !relatedOrderInfoList.isEmpty()) {
            for (OrderSummary relatedOrderInfo : relatedOrderInfoList) {
                allOrderList.add(relatedOrderInfo.getOrderId());
            }
        }

        String accessToken = getAccessToken(allOrderList, orderDetail.getUid(), contactInfo.getEmail(), orderTravelTime);
        if (accessToken == null){
            accessToken = "";
        }

        return accessToken;
    }
}
