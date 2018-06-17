package com.ctrip.ibu.flight.internalws.service.soaprocessor.email;

import com.ctrip.ibu.flight.internalws.business.getmessage.getemailstatus.IGetEmailStatusBusiness;
import com.ctrip.ibu.flight.internalws.contract.getemailstatusservice.GetEmailStatusRequestType;
import com.ctrip.ibu.flight.internalws.contract.getemailstatusservice.GetEmailStatusResponseType;
import com.ctrip.ibu.flight.internalws.models.annotation.SoaServiceProcessor;
import com.ctrip.ibu.flight.internalws.models.constant.BeanConst;
import com.ctrip.ibu.flight.internalws.models.message.GetEmailStatusResultCode;
import com.ctrip.ibu.flight.internalws.service.soaprocessor.SoaServiceProcessBase;

import javax.inject.Inject;

/**
 * 获取邮件发送状态接口Processor
 * todo:业务逻辑可以复用GetEmailDataProcessBase，将这个基类的请求抽象成不面向任何具体实现的请求
 * @see com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.getemaildataprocessor.GetEmailDataProcessBase 获取邮件数据请求
 * Create by kyxie on 2017/12/25 18:35
 */
@SoaServiceProcessor(value = BeanConst.BEANNAME_GETEMAILSTATUSPROCESSOR,actionId = BeanConst.BEANNAME_GETEMAILSTATUSPROCESSOR, actionName = "获取邮件状态接口",actionNote = "获取已发送邮件信息")
public class GetEmailStatusProcessor extends SoaServiceProcessBase<GetEmailStatusRequestType,GetEmailStatusResponseType> {

    private IGetEmailStatusBusiness getEmailStatusBusiness;

    @Inject
    public GetEmailStatusProcessor(IGetEmailStatusBusiness getEmailStatusBusiness){
        this.getEmailStatusBusiness = getEmailStatusBusiness;
    }

    /**
     * 验证请求
     *
     * @param getEmailStatusRequestType 原始请求
     */
    @Override
    public void validateRequest(GetEmailStatusRequestType getEmailStatusRequestType) {

    }

    /**
     * 处理SOA请求
     *
     * @param getEmailStatusRequestType 原始请求
     * @return 对外响应
     */
    @Override
    public GetEmailStatusResponseType processSoa(GetEmailStatusRequestType getEmailStatusRequestType) {
        GetEmailStatusResponseType getEmailStatusResponse = new GetEmailStatusResponseType();

        try {
            getEmailStatusResponse = getEmailStatusBusiness.getEmailStatus(getEmailStatusRequestType);
        }catch (Exception e){
            getEmailStatusResponse.setResultCode(GetEmailStatusResultCode.SystemError.ordinal());
            getEmailStatusResponse.setResultMsg("获取邮件发送状态逻辑异常");
        }

        return getEmailStatusResponse;
    }
}
