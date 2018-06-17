package com.ctrip.ibu.flight.internalws.models.constant;

/**
 * Cache常量
 * Created by kyxie on 2017/9/13.
 */
public final class CacheConst {

    private CacheConst(){
        throw new IllegalArgumentException("Constructor Forbidden");
    }

    //订单详情缓存Key
    public static final String ORDERDETAIL_CACHEKEY_PATTERN = "cachekey_orderdetail_%d_%s";

    //保单详情缓存Key
    public static final String INSURANCEDETAIL_CACHEKEY_PATTERN = "cachekey_insurancedetail_%d_%s";

    //邮件头尾缓存Key
    public static final String CACHEKEY_PATTERN_COMMONHEADERFOOTER = "cachekey_headerfooter_${emailType}_${orderId}_${flightClass}_${corpGroup}_${locale}";

    //ChannelRule缓存Key
    public static final String CACHEKEY_PATTERN_CHANNELRULE = "cachekey_channelrule_${sid}";
}
