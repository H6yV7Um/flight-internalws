package com.ctrip.ibu.flight.internalws.models.flight;

import java.util.List;

/**
 * Created by yhhuo on 2017/10/31.
 */
public class AskReplyGroupItemType {
    /**
     *回复组序号
     */
    private Integer groupNo;
    /**
     *航段信息
     */
    List<FlightColumn> colunmInfoList;
    /**
     *费用明细
     */
    AskReplyFeeDetail feeDetail;
}
