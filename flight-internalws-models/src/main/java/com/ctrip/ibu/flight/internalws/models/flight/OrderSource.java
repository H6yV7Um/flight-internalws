package com.ctrip.ibu.flight.internalws.models.flight;

/**
 * 订单来源
 * Created by kyxie on 2017/7/17.
 */
public enum OrderSource {

    /**
     * 不明确
     * */
    Unknown,

    /**
     * 大系统
     * */
    Ctrip,

    /**
     * IBU网站
     * */
    IBU,

    /**
     * SC预定
     * */
    SC
}
