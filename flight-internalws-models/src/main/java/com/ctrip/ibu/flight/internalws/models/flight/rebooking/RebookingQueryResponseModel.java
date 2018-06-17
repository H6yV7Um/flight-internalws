package com.ctrip.ibu.flight.internalws.models.flight.rebooking;

import java.util.List;

/**
 * Created by li.l on 2018/1/31.
 */
public class RebookingQueryResponseModel {

    private List<RebookingDetailInfoModel> rebookingDetailInfoModels;

    public List<RebookingDetailInfoModel> getRebookingDetailInfoModels() {
        return rebookingDetailInfoModels;
    }

    public void setRebookingDetailInfoModels(List<RebookingDetailInfoModel> rebookingDetailInfoModels) {
        this.rebookingDetailInfoModels = rebookingDetailInfoModels;
    }
}
