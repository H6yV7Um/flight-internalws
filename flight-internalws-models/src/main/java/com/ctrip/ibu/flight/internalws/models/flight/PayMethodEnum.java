package com.ctrip.ibu.flight.internalws.models.flight;

/**
 * 支付方式
 * Create by kyxie on 2018/1/30 17:43
 */
public enum PayMethodEnum {

    /**
     * 现金
     */
    CASH(0, "CASH"),

    /**
     * 信用卡
     */
    CREDITCARD(1, "CREDITCARD"),

    /**
     * paypal
     */
    PAYPAL(2, "PAYPAL"),

    /**
     * 借记卡
     */
    DEPOSITCARD(3, "DEPOSITCARD"),

    /**
     * 储蓄卡
     */
    DQPAY(4, "DQPAY"),

    /**
     * 未支付
     */
    NA(5, "NA");

    private final Integer value;

    private final String name;

    PayMethodEnum(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
