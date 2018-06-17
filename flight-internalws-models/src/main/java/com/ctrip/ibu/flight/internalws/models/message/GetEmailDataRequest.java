package com.ctrip.ibu.flight.internalws.models.message;

import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailRequestType;
import com.ctrip.ibu.flight.internalws.models.flight.OrderDetailModel;

/**
 * 获取邮件内容请求
 * Created by kyxie on 2017/11/5.
 */
public class GetEmailDataRequest implements IGetMessageDataRequest{

    /**
     * 邮件发送请求
     * todo:这里不要直接使用这个请求，应该自定义一套GetEmailDataRequest的请求字段，这样不论是发送邮件还是邮件预览，都可以转换成这个请求，从而实现低耦合
     * */
    private SendEmailRequestType sendEmailRequest;

    /**
     * 订单详情
     */
    private OrderDetailModel orderDetail;

    public SendEmailRequestType getSendEmailRequest() {
        return sendEmailRequest;
    }

    public void setSendEmailRequest(SendEmailRequestType sendEmailRequest) {
        this.sendEmailRequest = sendEmailRequest;
    }

    public OrderDetailModel getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailModel orderDetail) {
        this.orderDetail = orderDetail;
    }
}
