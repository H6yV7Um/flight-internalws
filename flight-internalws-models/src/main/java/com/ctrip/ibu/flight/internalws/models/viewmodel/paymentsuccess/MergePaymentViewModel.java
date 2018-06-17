package com.ctrip.ibu.flight.internalws.models.viewmodel.paymentsuccess;

import com.ctrip.ibu.flight.internalws.models.viewmodel.paymentsuccess.partialviewmodel.BasicInfo;
import com.ctrip.ibu.flight.internalws.models.viewmodel.paymentsuccess.partialviewmodel.PassengerGroup;
import com.ctrip.ibu.flight.internalws.models.viewmodel.paymentsuccess.partialviewmodel.PaymentSummary;

import java.util.List;

/**
 * 合并支付ViewModel
 * @author : kyxie
 * createtime : 2018/5/17 11:17
 */
public class MergePaymentViewModel {

    private BasicInfo basicInfo;

    private PaymentSummary paymentInfo;

    //按乘机人类别分组的列表
    private List<PassengerGroup> passengerGroupList;

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public PaymentSummary getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentSummary paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public List<PassengerGroup> getPassengerGroupList() {
        return passengerGroupList;
    }

    public void setPassengerGroupList(List<PassengerGroup> passengerGroupList) {
        this.passengerGroupList = passengerGroupList;
    }
}


