package com.ctrip.ibu.flight.internalws.models.flight.offline;

/**
 * Created by zhangrm on 2017/11/3.
 */
public class RefundAskDetailModel {
    private RefundAskDetailResponseModel refundAskDetailResponseModel;

    private String linkUrl;

    public RefundAskDetailResponseModel getRefundAskDetailResponseModel() {
        return refundAskDetailResponseModel;
    }

    public void setRefundAskDetailResponseModel(RefundAskDetailResponseModel refundAskDetailResponseModel) {
        this.refundAskDetailResponseModel = refundAskDetailResponseModel;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
