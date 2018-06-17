package com.ctrip.ibu.flight.internalws.service.soaentry;

import com.ctrip.ibu.flight.internalws.contract.IInternalws;
import com.ctrip.ibu.flight.internalws.contract.getemailstatusservice.GetEmailStatusRequestType;
import com.ctrip.ibu.flight.internalws.contract.getemailstatusservice.GetEmailStatusResponseType;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailRequestType;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailResponseType;
import com.ctrip.ibu.flight.internalws.models.constant.BeanConst;
import com.ctrip.ibu.flight.internalws.service.soaprocessor.SoaServiceProcessBase;
import com.ctriposs.baiji.rpc.common.types.AckCodeType;
import com.ctriposs.baiji.rpc.common.types.CheckHealthRequestType;
import com.ctriposs.baiji.rpc.common.types.CheckHealthResponseType;
import com.ctriposs.baiji.rpc.common.types.ResponseStatusType;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Calendar;

/**
 * SOA服务实现
 * */
@Component
public class InternalwsImpl implements IInternalws {

    //邮件发送
    private SoaServiceProcessBase<SendEmailRequestType,SendEmailResponseType> sendEmailProcessor;

    //获取邮件内容
    private SoaServiceProcessBase<GetEmailStatusRequestType,GetEmailStatusResponseType> getEmailStatusProcessor;

    @Inject
    public InternalwsImpl(@Named(BeanConst.BEANNAME_SENDEMAILPROCESSOR) SoaServiceProcessBase<SendEmailRequestType,SendEmailResponseType> sendEmailProcessor,
                          @Named(BeanConst.BEANNAME_GETEMAILSTATUSPROCESSOR) SoaServiceProcessBase<GetEmailStatusRequestType,GetEmailStatusResponseType> getEmailStatusProcessor){
        this.sendEmailProcessor = sendEmailProcessor;
        this.getEmailStatusProcessor = getEmailStatusProcessor;
    }

    /**
     * 健康检查(不要写复杂的逻辑)
     * @param request 请求
     * */
    @Override
    public CheckHealthResponseType checkHealth(CheckHealthRequestType request) throws Exception {
        CheckHealthResponseType response = new CheckHealthResponseType();
        ResponseStatusType responseStatus = new ResponseStatusType();
        responseStatus.setAck(AckCodeType.Success);
        responseStatus.setTimestamp(Calendar.getInstance());
        response.setResponseStatus(responseStatus);
        return response;
    }

    /**
     * 邮件发送接口
     * @param request 邮件发送请求
     * */
    @Override
    public SendEmailResponseType sendEmail(SendEmailRequestType request) throws Exception {
        return sendEmailProcessor.process(request);
    }

    /**
     * 获取邮件发送状态接口
     * @param request 获取邮件发送状态请求
     * */
    @Override
    public GetEmailStatusResponseType getEmailStatus(GetEmailStatusRequestType request) throws Exception {
        return getEmailStatusProcessor.process(request);
    }

}