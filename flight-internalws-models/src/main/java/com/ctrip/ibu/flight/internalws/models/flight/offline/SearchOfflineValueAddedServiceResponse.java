package com.ctrip.ibu.flight.internalws.models.flight.offline;

import com.ctrip.ibu.flight.internalws.models.common.ResponseHead;

/**
 * 增值服务查询响应
 * Created by kyxie on 2017/10/30.
 */
public class SearchOfflineValueAddedServiceResponse {

    /**
     * 响应头
     */
    private ResponseHead responseHead;

    /**
     * 增值服务信息
     */
    private OfflineValueAddedModel valueAddedService;

    public ResponseHead getResponseHead() {
        return responseHead;
    }

    public void setResponseHead(ResponseHead responseHead) {
        this.responseHead = responseHead;
    }

    public OfflineValueAddedModel getValueAddedService() {
        return valueAddedService;
    }

    public void setValueAddedService(OfflineValueAddedModel valueAddedService) {
        this.valueAddedService = valueAddedService;
    }
}
