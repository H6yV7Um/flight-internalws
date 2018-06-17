package com.ctrip.ibu.flight.internalws.repository;

import com.ctrip.ibu.flight.internalws.common.utils.ThrowableUtils;
import com.ctrip.ibu.flight.internalws.models.errorcode.repository.WSErrorSource;
import com.ctrip.ibu.flight.internalws.models.exception.repository.RepositoryException;
import com.ctrip.ibu.flight.internalws.models.exception.soa.WSInvokeException;
import com.ctrip.ibu.flight.internalws.models.util.EncryptAccessTokenRequestModel;
import com.ctrip.ibu.flight.internalws.models.util.EncryptAccessTokenResponseModel;
import com.ctrip.ibu.flight.internalws.repository.ws.WSInvokerHelper;
import com.ctrip.ibu.flight.internalws.repository.ws.account.EncryptTokenWS;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * 账户相关仓储
 * Created by kyxie on 2017/8/16.
 */
@Repository("accountRepository")
public class AccountRepository implements IAccountRepository {

    private EncryptTokenWS encryptTokenWS;

    @Inject
    public AccountRepository(@Named("encryptTokenWS") EncryptTokenWS encryptTokenWS){
        this.encryptTokenWS = encryptTokenWS;
    }

    /**
     * 加密AccessToken
     *
     * @param request
     */
    @Override
    public EncryptAccessTokenResponseModel encryptAccessToken(EncryptAccessTokenRequestModel request) throws RepositoryException {

        EncryptAccessTokenResponseModel response;

        try {
            response = WSInvokerHelper.invokeApi(request,encryptTokenWS);
        } catch (WSInvokeException e) {
            throw ThrowableUtils.getThrowWithCause(new RepositoryException(WSErrorSource.WEBSERVICE,RepositoryHelper.getWSExceptionDesc(e)),e);
        }

        return response;
    }
}
