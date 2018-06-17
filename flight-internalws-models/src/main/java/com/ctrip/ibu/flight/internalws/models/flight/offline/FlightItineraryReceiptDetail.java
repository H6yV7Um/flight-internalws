package com.ctrip.ibu.flight.internalws.models.flight.offline;

import com.ctrip.ibu.flight.internalws.models.flight.FlightColumn;
import com.ctrip.ibu.flight.internalws.models.flight.Passenger;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhiyizhang on 2018/1/17.
 */
public class FlightItineraryReceiptDetail {
    private String customerName;

    private String totalAmountCurrency;

    private BigDecimal totalAmount;

    private String inPaymentOf;

    private String paymentMethod;

    private String issuingAirline;

    private List<Passenger> passengerList;

    private List<FlightColumn> flightColumnList;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTotalAmountCurrency() {
        return totalAmountCurrency;
    }

    public void setTotalAmountCurrency(String totalAmountCurrency) {
        this.totalAmountCurrency = totalAmountCurrency;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getInPaymentOf() {
        return inPaymentOf;
    }

    public void setInPaymentOf(String inPaymentOf) {
        this.inPaymentOf = inPaymentOf;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getIssuingAirline() {
        return issuingAirline;
    }

    public void setIssuingAirline(String issuingAirline) {
        this.issuingAirline = issuingAirline;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public List<FlightColumn> getFlightColumnList() {
        return flightColumnList;
    }

    public void setFlightColumnList(List<FlightColumn> flightColumnList) {
        this.flightColumnList = flightColumnList;
    }
}
