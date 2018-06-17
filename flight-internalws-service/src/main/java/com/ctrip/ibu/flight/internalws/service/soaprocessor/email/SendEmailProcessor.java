package com.ctrip.ibu.flight.internalws.service.soaprocessor.email;

import com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.ISendEmailBusiness;
import com.ctrip.ibu.flight.internalws.common.components.Checker;
import com.ctrip.ibu.flight.internalws.common.log.LoggerHelper;
import com.ctrip.ibu.flight.internalws.common.utils.SerializeUtil;
import com.ctrip.ibu.flight.internalws.contract.commontypes.EmailTemplateType;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailRequestType;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailResponseType;
import com.ctrip.ibu.flight.internalws.models.annotation.SoaServiceProcessor;
import com.ctrip.ibu.flight.internalws.models.constant.BeanConst;
import com.ctrip.ibu.flight.internalws.service.soaprocessor.SoaServiceProcessBase;

import javax.inject.Inject;

/**
 * 邮件发送接口Processor
 * Create by kyxie on 2017/12/25 18:15
 */
@SoaServiceProcessor(value = BeanConst.BEANNAME_SENDEMAILPROCESSOR,actionId = BeanConst.BEANNAME_SENDEMAILPROCESSOR,actionName = "邮件发送接口",actionNote = "邮件发送接口服务")
public class SendEmailProcessor extends SoaServiceProcessBase<SendEmailRequestType,SendEmailResponseType> {

    private ISendEmailBusiness sendEmailBusiness;

    @Inject
    public SendEmailProcessor(ISendEmailBusiness sendEmailBusiness){
        this.sendEmailBusiness = sendEmailBusiness;
    }

    /**
     * 验证请求
     *
     * @param sendEmailRequest 原始请求
     */
    @Override
    public void validateRequest(SendEmailRequestType sendEmailRequest) {

        LoggerHelper.appendRequestContent(String.format("原始邮件发送请求为:\n%1$s",SerializeUtil.toJsonWithGdpr(sendEmailRequest)));

        Checker.checkWithThrowable(sendEmailRequest == null,new IllegalArgumentException("邮件发送请求为NULL"));
        Checker.checkWithThrowable(sendEmailRequest.emailTemplateType == null || sendEmailRequest.emailTemplateType == EmailTemplateType.unspecified
                ,new IllegalArgumentException("邮件发送请求模板类型不合法"));
    }

    /**
     * 处理SOA请求
     *
     * @param sendEmailRequestType 原始请求
     * @return 对外响应
     */
    @Override
    public SendEmailResponseType processSoa(SendEmailRequestType sendEmailRequestType) {
        return this.sendEmailBusiness.sendMessage(sendEmailRequestType);
    }

}
