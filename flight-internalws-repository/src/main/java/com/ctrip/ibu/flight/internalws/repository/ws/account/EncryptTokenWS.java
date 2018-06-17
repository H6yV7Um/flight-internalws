package com.ctrip.ibu.flight.internalws.repository.ws.account;

import com.ctrip.framework.foundation.Foundation;
import com.ctrip.ibu.flight.internalws.models.annotation.WSMethodMeta;
import com.ctrip.ibu.flight.internalws.models.util.EncryptAccessTokenRequestModel;
import com.ctrip.ibu.flight.internalws.models.util.EncryptAccessTokenResponseModel;
import com.ctrip.ibu.flight.internalws.repository.ws.WSMethodInvoker;
import com.ctrip.ibu.flight.internalws.repository.ws.wshelper.SOACommonHelper;
import com.ctrip.ibu.soa.foundation.contract.accountapi.EncryptTokenRequestType;
import com.ctrip.ibu.soa.foundation.contract.accountapi.EncryptTokenResponseType;
import com.ctrip.ibu.soa.foundation.contract.accountapi.IBUAccountsAPIClient;
import com.ctrip.ibu.soa.foundation.contract.accountapi.TokenModel;
import com.ctrip.ibu.soa.foundation.contract.common.Head;
import org.springframework.stereotype.Component;

/**
 * AccessToken加密服务
 * Created by kyxie on 2017/8/15.
 */
@Component("encryptTokenWS")
@WSMethodMeta(methodDesc = "AccessToken加密接口")
public class EncryptTokenWS implements WSMethodInvoker<IBUAccountsAPIClient,EncryptAccessTokenRequestModel,EncryptAccessTokenResponseModel,EncryptTokenRequestType,EncryptTokenResponseType> {

    /**
     * 获取WebService的Client实例
     */
    @Override
    public IBUAccountsAPIClient getClientInstance() {
        return SOACommonHelper.getSoaClientInstance(IBUAccountsAPIClient.class,"");
    }

    /**
     * Request参数转换
     *
     * @param encryptAccessTokenRequestModel
     */
    @Override
    public EncryptTokenRequestType convertRequestToWSRequest(EncryptAccessTokenRequestModel encryptAccessTokenRequestModel) {
        EncryptTokenRequestType result = new EncryptTokenRequestType();
        if (encryptAccessTokenRequestModel == null || encryptAccessTokenRequestModel.getTokenModel() == null){
            return result;
        }

        Head head = new Head();
        head.setIp(Foundation.net().getHostAddress());
        head.setApiKey("ibuflightinternalws");
        head.setClientSign("ibuflightinternalws");
        result.setHead(head);

        TokenModel tokenModel = new TokenModel();
        tokenModel.setEmail(encryptAccessTokenRequestModel.getTokenModel().getEmail());
        tokenModel.setOrderIds(encryptAccessTokenRequestModel.getTokenModel().getOrderIds());
        tokenModel.setUid(encryptAccessTokenRequestModel.getTokenModel().getUid());
        tokenModel.setOrderTravelTime(encryptAccessTokenRequestModel.getTokenModel().getOrderTravelTime());
        result.setTokenModel(tokenModel);

        return result;
    }

    /**
     * 调用方法
     *
     * @param ibuAccountsAPIClient
     * @param encryptTokenRequestType
     */
    @Override
    public EncryptTokenResponseType invokeMethod(IBUAccountsAPIClient ibuAccountsAPIClient, EncryptTokenRequestType encryptTokenRequestType) throws Exception {
        return ibuAccountsAPIClient.encryptToken(encryptTokenRequestType);
    }

    /**
     * Response转换
     *
     * @param wsResponse
     */
    @Override
    public EncryptAccessTokenResponseModel convertWSResponseToResponse(EncryptTokenResponseType wsResponse) {
        EncryptAccessTokenResponseModel response = new EncryptAccessTokenResponseModel();
        if (wsResponse != null){
            response.setReturnCode(wsResponse.getReturnCode());
            response.setEncryptToken(wsResponse.getEncrytToken());
            if (wsResponse.getResponseHead() != null){
                response.setErrorMsg(wsResponse.getResponseHead().getErrorMessage());
            }
        }else {
            response.setErrorMsg("AccessToken加密接口EncryptTokenWS返回为NULL");
        }
        return response;
    }

    /**
     * 调用的方法名称
     */
    @Override
    public String methodName() {
        return "encryptToken";
    }
}
