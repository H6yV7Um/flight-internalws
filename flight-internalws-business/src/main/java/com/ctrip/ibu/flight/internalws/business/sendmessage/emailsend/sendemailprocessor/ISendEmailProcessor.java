package com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.sendemailprocessor;

import com.ctrip.ibu.flight.internalws.business.sendmessage.ISendMessageService;
import com.ctrip.ibu.flight.internalws.models.exception.emailsend.EmailSendException;
import com.ctrip.ibu.flight.internalws.models.message.EmailSendRequest;
import com.ctrip.ibu.flight.internalws.models.message.EmailSendResponse;

/**
 * 邮件发送组件接口(这里可以丰富邮件发送接口的内容)
 * Create by kyxie on 2018/4/14 20:27
 */
public interface ISendEmailProcessor extends ISendMessageService<EmailSendRequest,EmailSendResponse,EmailSendException>{

    //NO ADDITIONAL METHODS

}
