package com.ctrip.ibu.flight.internalws.models.exception.emailsend;

import com.ctrip.ibu.flight.internalws.models.errorcode.emailsend.GetEmailDataResultCode;
import com.ctrip.ibu.flight.internalws.models.exception.GetMessageDataException;
import com.ctrip.ibu.flight.internalws.models.message.MessageCategory;

/**
 * 获取邮件数据异常
 * Create by kyxie on 2018/4/11 15:33
 */
public class GetEmailDataException extends GetMessageDataException {

    /**
     * 获取邮件数据结果码
     * */
    private GetEmailDataResultCode getEmailDataResultCode;

    /**
     * 错误信息
     * */
    private String errorMsg;

    public GetEmailDataException(GetEmailDataResultCode getEmailDataResultCode, String errorMsg){
        super(MessageCategory.Email,getEmailDataResultCode.getVal(),errorMsg);
        this.getEmailDataResultCode = getEmailDataResultCode;
        this.errorMsg = errorMsg;
    }

    public GetEmailDataResultCode getGetEmailDataResultCode() {
        return getEmailDataResultCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
