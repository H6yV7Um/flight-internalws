package com.ctrip.ibu.flight.internalws.models.flight;

import com.ctrip.ibu.flight.internalws.models.annotation.GdprInfo;
import com.ctrip.ibu.flight.internalws.models.util.GDPRType;

import java.util.List;

/**
 * 乘机人及增值行李额
 * Created by kyxie on 2017/9/22.
 */
public class PassengerValueAddedBaggage {

    @GdprInfo(GDPRType.GDPR_USER_NAME)
    private String passengerName;

    private List<ValueAddedBaggage> valueAddedBaggageList;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public List<ValueAddedBaggage> getValueAddedBaggageList() {
        return valueAddedBaggageList;
    }

    public void setValueAddedBaggageList(List<ValueAddedBaggage> valueAddedBaggageList) {
        this.valueAddedBaggageList = valueAddedBaggageList;
    }
}
