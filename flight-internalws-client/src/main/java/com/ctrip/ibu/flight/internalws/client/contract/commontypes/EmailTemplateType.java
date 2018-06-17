package com.ctrip.ibu.flight.internalws.client.contract.commontypes;

public enum EmailTemplateType {

    unspecified(0),
    ReservationConfirmation(1),
    PaymentSuccess(2),
    PaymentFailed(3),
    PaymentCanceled(4),
    FlightChangeNotify(5),
    FlightChangeCanceled(6),
    FlightChangeFeedback(7),
    NewFlightSuccess(8),
    NewFlightPaymentSuccess(9),
    NewFlightCanceled(10),
    NewFlightTobePaid(11),
    NewFlightConsultingVerified(12),
    InsuranceIssued(13),
    InsuranceRefunded(14),
    InsuranceChanged(15),
    XProductSubmitted(16),
    XProductPaymentSuccess(17),
    XProductIssued(18),
    XProductCanceled(19),
    RefundFeeVerified(20),
    RefundSuccess(21),
    EReceipt(22),
    ItineraryDetail(23);


    private final int value;

    EmailTemplateType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EmailTemplateType findByValue(int value) {
        switch (value) {
            case 0:
                return unspecified;
            case 1:
                return ReservationConfirmation;
            case 2:
                return PaymentSuccess;
            case 3:
                return PaymentFailed;
            case 4:
                return PaymentCanceled;
            case 5:
                return FlightChangeNotify;
            case 6:
                return FlightChangeCanceled;
            case 7:
                return FlightChangeFeedback;
            case 8:
                return NewFlightSuccess;
            case 9:
                return NewFlightPaymentSuccess;
            case 10:
                return NewFlightCanceled;
            case 11:
                return NewFlightTobePaid;
            case 12:
                return NewFlightConsultingVerified;
            case 13:
                return InsuranceIssued;
            case 14:
                return InsuranceRefunded;
            case 15:
                return InsuranceChanged;
            case 16:
                return XProductSubmitted;
            case 17:
                return XProductPaymentSuccess;
            case 18:
                return XProductIssued;
            case 19:
                return XProductCanceled;
            case 20:
                return RefundFeeVerified;
            case 21:
                return RefundSuccess;
            case 22:
                return EReceipt;
            case 23:
                return ItineraryDetail;
            default:
                return null;
        }
    }
}
