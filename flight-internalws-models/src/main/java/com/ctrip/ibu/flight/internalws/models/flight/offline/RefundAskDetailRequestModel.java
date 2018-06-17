package com.ctrip.ibu.flight.internalws.models.flight.offline;

import com.ctrip.ibu.flight.internalws.models.common.RequestHead;

/**
 * Created by zhangrm on 2017/11/2.
 */
public class RefundAskDetailRequestModel {

    private RequestHead requestHead;

    private Long orderId;

    private long refundOrderID;

    private String accessToken;

    private boolean retryGetOrderDetail;

    public RequestHead getRequestHead() {
        return requestHead;
    }

    public void setRequestHead(RequestHead requestHead) {
        this.requestHead = requestHead;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public long getRefundOrderID() {
        return refundOrderID;
    }

    public void setRefundOrderID(long refundOrderID) {
        this.refundOrderID = refundOrderID;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isRetryGetOrderDetail() {
        return retryGetOrderDetail;
    }

    public void setRetryGetOrderDetail(boolean retryGetOrderDetail) {
        this.retryGetOrderDetail = retryGetOrderDetail;
    }
}
