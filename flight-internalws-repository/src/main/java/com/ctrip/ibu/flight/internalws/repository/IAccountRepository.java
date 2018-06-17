package com.ctrip.ibu.flight.internalws.repository;

import com.ctrip.ibu.flight.internalws.models.exception.repository.RepositoryException;
import com.ctrip.ibu.flight.internalws.models.util.EncryptAccessTokenRequestModel;
import com.ctrip.ibu.flight.internalws.models.util.EncryptAccessTokenResponseModel;

/**
 * 账户相关
 * Created by kyxie on 2017/8/16.
 */
public interface IAccountRepository {

    /**
     * 加密AccessToken
     * */
    EncryptAccessTokenResponseModel encryptAccessToken(EncryptAccessTokenRequestModel request) throws RepositoryException;
}
