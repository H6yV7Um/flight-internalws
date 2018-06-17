package com.ctrip.ibu.flight.internalws.business.common;

import com.ctrip.ibu.flight.internalws.models.common.RequestHead;
import com.ctrip.ibu.flight.internalws.models.flight.OrderDetailModel;
import com.ctrip.ibu.flight.internalws.models.repository.dao.ibuflightdb.Localeidmappingconfig;

import java.util.Calendar;
import java.util.List;

/**
 * 公共业务服务接口
 * Created by kyxie on 2017/11/1.
 */
public interface IFlightCommonBusiness {

    /**
     * 通过指定订单详情获取当前订单AccessToken
     * */
    String getAccessToken(OrderDetailModel orderDetail);

    /**
     * 获取AccessToken
     */
    String getAccessToken(List<Long> orderIdList, String uid, String email, Calendar orderTravelTime);

    /**
     * 获取订单详情列表
     *
     * @param orderId        订单号
     * @param requestHead 请求头
     * @param needCache      是否需要缓存
     */
    OrderDetailModel getOrderDetail(Long orderId, RequestHead requestHead, boolean needCache);

    List<Localeidmappingconfig> queryAll();
}
