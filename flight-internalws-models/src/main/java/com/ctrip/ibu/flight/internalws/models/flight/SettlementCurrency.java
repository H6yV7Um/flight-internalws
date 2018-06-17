package com.ctrip.ibu.flight.internalws.models.flight;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 机票结算币种
 * Created by kyxie on 2017/9/22.
 */
public enum SettlementCurrency {

    /**
     * 未知
     * */
    UNKNOWN(0,"UNKNOWN"),

    /**
     * 人民币
     */
    CNY(1, "CNY"),

    /**
     * 港币
     */
    HKD(2, "HKD"),

    /**
     * 新币
     */
    SGD(3, "SGD"),

    /**
     * 日币
     */
    JPY(4, "JPY"),

    /**
     * 美元
     */
    USD(5, "USD"),

    EUR(6, "EUR"),

    GBP(7, "GBP"),

    AUD(8, "AUD"),

    CAD(9, "CAD"),

    CHF(10, "CHF"),

    DKK(11, "DKK"),

    TWD(12, "TWD"),

    KRW(13, "KRW"),

    MOP(14, "MOP"),

    MYR(15, "MYR"),

    NZD(16, "NZD"),

    SEK(17, "SEK"),

    PHP(18, "PHP"),

    RUB(19, "RUB"),

    VND(20, "VND"),

    THB(21, "THB"),

    IDR(22, "IDR"),

    INR(23, "INR"),

    BRL(24, "BRL"),

    PLN(25, "PLN"),

    TRY(26, "TRY");

    private final int intValue;
    private final String value;

    SettlementCurrency(int intValue, String value) {
        this.intValue = intValue;
        this.value = value;
    }

    public int getValue() {
        return intValue;
    }

    @JsonValue
    public String value() {
        return value;
    }

    @JsonCreator
    public static SettlementCurrency fromValue(String v) {
        for (SettlementCurrency e: SettlementCurrency.values()) {
            if (e.value.equals(v)) {
                return e;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static SettlementCurrency findByValue(int value) {
        switch (value) {
            case 0:
                return CNY;
            case 1:
                return HKD;
            case 2:
                return SGD;
            case 3:
                return JPY;
            case 4:
                return USD;
            case 5:
                return EUR;
            case 6:
                return GBP;
            case 7:
                return AUD;
            case 8:
                return CAD;
            case 9:
                return CHF;
            case 10:
                return DKK;
            case 11:
                return TWD;
            case 12:
                return KRW;
            case 13:
                return MOP;
            case 14:
                return MYR;
            case 15:
                return NZD;
            case 16:
                return SEK;
            case 17:
                return PHP;
            case 18:
                return RUB;
            case 19:
                return VND;
            case 20:
                return THB;
            case 21:
                return IDR;
            case 22:
                return INR;
            case 23:
                return BRL;
            case 24:
                return PLN;
            case 25:
                return TRY;
            default:
                return null;
        }
    }
}
