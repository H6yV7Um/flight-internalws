package com.ctrip.ibu.flight.internalws.repository.ws.email;

import com.ctrip.ibu.flight.internalws.common.config.Config;
import com.ctrip.ibu.flight.internalws.models.annotation.WSMethodMeta;
import com.ctrip.ibu.flight.internalws.models.message.EmailModel;
import com.ctrip.ibu.flight.internalws.models.message.GetEmailStatusRequestModel;
import com.ctrip.ibu.flight.internalws.models.message.GetEmailStatusResponseModel;
import com.ctrip.ibu.flight.internalws.repository.ws.WSMethodInvoker;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.GetEmailStatusServiceHelper;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.SOACommonHelper;
import com.ctrip.soa.platform.basesystem.emailservice.v1.Email;
import com.ctrip.soa.platform.basesystem.emailservice.v1.EmailServiceClient;
import com.ctrip.soa.platform.basesystem.emailservice.v1.GetEmailStatusRequest;
import com.ctrip.soa.platform.basesystem.emailservice.v1.GetEmailStatusResponse;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取邮件发送状态服务
 * Created by kyxie on 2017/8/31.
 */
@Component("getEmailStatusWS")
@WSMethodMeta(methodDesc = "获取邮件状态接口")
public class GetEmailStatusWS implements WSMethodInvoker<EmailServiceClient,GetEmailStatusRequestModel,GetEmailStatusResponseModel,GetEmailStatusRequest,GetEmailStatusResponse> {

    private Config config;

    private GetEmailStatusServiceHelper getEmailStatusServiceHelper;

    @Inject
    public GetEmailStatusWS(@Named("config") Config config,
                            GetEmailStatusServiceHelper getEmailStatusServiceHelper){
        this.config = config;
        this.getEmailStatusServiceHelper = getEmailStatusServiceHelper;
    }

    /**
     * 获取WebService的Client实例
     */
    @Override
    public EmailServiceClient getClientInstance() {
        return SOACommonHelper.getSoaClientInstance(EmailServiceClient.class,"");
    }

    /**
     * Request参数转换
     *
     * @param getEmailStatusRequestModel
     */
    @Override
    public GetEmailStatusRequest convertRequestToWSRequest(GetEmailStatusRequestModel getEmailStatusRequestModel) {
        GetEmailStatusRequest request = new GetEmailStatusRequest();
        request.setSendCode(this.config.getEmailConfigInfo().getEmailSendCode());//todo:移到帮助类Bean中
        if (getEmailStatusRequestModel != null){
            request.setEmailIDList(getEmailStatusRequestModel.getEmailIdList());
        }
        return request;
    }

    /**
     * 调用方法
     *
     * @param emailServiceClient
     * @param getEmailStatusRequest
     */
    @Override
    public GetEmailStatusResponse invokeMethod(EmailServiceClient emailServiceClient, GetEmailStatusRequest getEmailStatusRequest) throws Exception {
        return emailServiceClient.getEmailStatus(getEmailStatusRequest);
    }

    /**
     * Response转换
     *
     * @param wsResponse
     */
    @Override
    public GetEmailStatusResponseModel convertWSResponseToResponse(GetEmailStatusResponse wsResponse) {
        GetEmailStatusResponseModel response = new GetEmailStatusResponseModel();
        if (wsResponse != null){
            response.setResultCode(wsResponse.getResultCode());
            response.setResultMsg(wsResponse.getResultMsg());
            List<Email> gaEmailList = wsResponse.getEmailList();
            if (gaEmailList != null && !gaEmailList.isEmpty()){
                List<EmailModel> emailList = new ArrayList<>();
                for (Email wsEmail : gaEmailList){
                    emailList.add(this.getEmailStatusServiceHelper.convertEmailModel(wsEmail));
                }
                response.setEmailList(emailList);
            }
        }
        return response;
    }

    /**
     * 调用的方法名称
     */
    @Override
    public String methodName() {
        return "getEmailStatus";
    }
}
