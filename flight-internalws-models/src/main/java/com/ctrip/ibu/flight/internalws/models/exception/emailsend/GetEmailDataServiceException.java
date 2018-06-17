package com.ctrip.ibu.flight.internalws.models.exception.emailsend;

import com.ctrip.ibu.flight.internalws.models.message.EmailDataServiceError;

/**
 * 获取邮件数据处理类异常
 */
public class GetEmailDataServiceException extends Exception {

    /**
     * 服务Bean名称
     */
    private String serviceBeanName;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 邮件服务错误类型
     */
    private EmailDataServiceError emailDataServiceError;

    /**
     * 错误信息
     */
    private String errMsg;

    public GetEmailDataServiceException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }

    public GetEmailDataServiceException(EmailDataServiceError emailDataServiceError, String errMsg) {
        super(errMsg);
        this.emailDataServiceError = emailDataServiceError;
    }

    public GetEmailDataServiceException(EmailDataServiceError emailDataServiceError, String serviceBeanName, String serviceName, String errMsg) {
        super(errMsg);
        this.emailDataServiceError = emailDataServiceError;
        this.serviceBeanName = serviceBeanName;
        this.serviceName = serviceName;
        this.errMsg = errMsg;
    }

    public String getServiceBeanName() {
        return serviceBeanName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
