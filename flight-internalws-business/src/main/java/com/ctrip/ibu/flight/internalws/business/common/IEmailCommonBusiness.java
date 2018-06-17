package com.ctrip.ibu.flight.internalws.business.common;

import com.ctrip.ibu.flight.internalws.models.common.Trademark;
import com.ctrip.ibu.flight.internalws.contract.commontypes.EmailTemplateType;
import com.ctrip.ibu.flight.internalws.contract.commontypes.LanguageType;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailRequestType;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailResponseType;
import com.ctrip.ibu.flight.internalws.models.flight.FlightClass;
import com.ctrip.ibu.flight.internalws.models.flight.OrderDetailModel;
import com.ctrip.ibu.flight.internalws.models.flight.SettlementCurrency;
import com.ctrip.ibu.flight.internalws.models.message.*;

/**
 * 邮件相关业务接口
 */
public interface IEmailCommonBusiness {

    /**
     * 获取邮件公共头尾
     * */
    EmailHeaderAndFooterResponse getEmailHeaderAndFooter(OrderDetailModel orderDetail, EmailTemplateType emailTemplateType, LanguageType emailSendLanguage);

    /**
     * 获取邮件公共头尾
     */
    EmailHeaderAndFooterResponse getEmailHeaderAndFooter(EmailHeaderAndFooterRequest headerAndFooterRequest);

    /**
     * 获取邮件发送Sender
     * */
    EmailSenderModel getEmailSender(GetEmailSenderRequest getEmailSenderRequest);

    /**
     * 获取资源文件路径
     * @param flightClass 订单类型(国际国内)
     * @param trademark 商标
     * @param currency 币种
     * */
    ResourcePathModel getResourcePath(FlightClass flightClass, Trademark trademark, SettlementCurrency currency);

    /**
     * 记录邮件发送记录
     * @param sendEmailRequest 邮件发送请求
     * @param sendEmailResponse 邮件发送响应
     * */
    void recordEmailLog(SendEmailRequestType sendEmailRequest, SendEmailResponseType sendEmailResponse);

}
