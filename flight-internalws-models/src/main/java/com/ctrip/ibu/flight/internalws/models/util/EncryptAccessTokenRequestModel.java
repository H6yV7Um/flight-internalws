package com.ctrip.ibu.flight.internalws.models.util;

/**
 * AccessToken加密模型
 * Created by kyxie on 2017/8/15.
 */
public class EncryptAccessTokenRequestModel {

    private TokenModel tokenModel;

    public TokenModel getTokenModel() {
        return tokenModel;
    }

    public void setTokenModel(TokenModel tokenModel) {
        this.tokenModel = tokenModel;
    }
}
