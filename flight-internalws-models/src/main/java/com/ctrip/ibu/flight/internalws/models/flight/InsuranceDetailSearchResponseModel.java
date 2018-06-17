package com.ctrip.ibu.flight.internalws.models.flight;

import java.util.List;

/**
 * Created by kyxie on 2017/8/11.
 */
public class InsuranceDetailSearchResponseModel {

    private List<InsuranceDetailModel> insuranceDetailList;

    public List<InsuranceDetailModel> getInsuranceDetailList() {
        return insuranceDetailList;
    }

    public void setInsuranceDetailList(List<InsuranceDetailModel> insuranceDetailList) {
        this.insuranceDetailList = insuranceDetailList;
    }
}
