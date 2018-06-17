package com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend;

import com.ctrip.ibu.flight.internalws.business.sendmessage.ISendMessageBusiness;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailRequestType;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailResponseType;

/**
 * 邮件发送业务逻辑
 * Created by kyxie on 2017/7/21.
 */
public interface ISendEmailBusiness extends ISendMessageBusiness<SendEmailRequestType,SendEmailResponseType> {

}
