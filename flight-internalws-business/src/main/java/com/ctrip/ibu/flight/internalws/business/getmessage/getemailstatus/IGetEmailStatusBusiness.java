package com.ctrip.ibu.flight.internalws.business.getmessage.getemailstatus;

import com.ctrip.ibu.flight.internalws.contract.getemailstatusservice.GetEmailStatusRequestType;
import com.ctrip.ibu.flight.internalws.contract.getemailstatusservice.GetEmailStatusResponseType;

/**
 * 获取邮件发送状态业务逻辑
 * Created by kyxie on 2017/8/31.
 */
public interface IGetEmailStatusBusiness {

    /**
     * 获取邮件发送状态
     * @param request 请求
     * */
    GetEmailStatusResponseType getEmailStatus(GetEmailStatusRequestType request);
}
