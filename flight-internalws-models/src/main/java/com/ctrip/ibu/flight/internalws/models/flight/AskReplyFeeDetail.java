package com.ctrip.ibu.flight.internalws.models.flight;

import java.math.BigDecimal;

/**
 * Created by yhhuo on 2017/10/31.
 */
public class AskReplyFeeDetail {
    /**
     *费用明细Id
     */
   private long feeDetailId;
    /**
     *客人实付金额
     */
    private  BigDecimal payAmount;
    /**
     *唯一标示,用于客户端和API直接传递
     */
    private String shoppingID;
    /**
     *原订单支付币种
     */
    private String currency;

    /**
     *费用明细Id
     */
    public long getFeeDetailId() {
        return this.feeDetailId;
    }

    /**
     *费用明细Id
     */
    public void setFeeDetailId(long feeDetailId) {
        this.feeDetailId = feeDetailId;
    }

    /**
     *客人实付金额
     */
    public BigDecimal getPayAmount() {
        return this.payAmount;
    }

    /**
     *客人实付金额
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     *唯一标示,用于客户端和API直接传递
     */
    public String getShoppingID() {
        return this.shoppingID;
    }

    /**
     *唯一标示,用于客户端和API直接传递
     */
    public void setShoppingID(String shoppingID) {
        this.shoppingID = shoppingID;
    }

    /**
     *原订单支付币种
     */
    public String getCurrency() {
        return this.currency;
    }

    /**
     *原订单支付币种
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
