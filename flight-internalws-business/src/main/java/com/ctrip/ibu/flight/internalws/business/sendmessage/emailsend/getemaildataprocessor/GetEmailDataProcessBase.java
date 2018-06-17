package com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.getemaildataprocessor;

import com.ctrip.ibu.flight.internalws.business.sendmessage.IGetMessageDataService;
import com.ctrip.ibu.flight.internalws.common.components.Checker;
import com.ctrip.ibu.flight.internalws.common.log.LoggerHelper;
import com.ctrip.ibu.flight.internalws.contract.commontypes.EmailTemplateType;
import com.ctrip.ibu.flight.internalws.models.errorcode.emailsend.GetEmailDataResultCode;
import com.ctrip.ibu.flight.internalws.models.exception.emailsend.GetEmailDataException;
import com.ctrip.ibu.flight.internalws.models.log.IndexedLogTag;
import com.ctrip.ibu.flight.internalws.models.log.LogLevel;
import com.ctrip.ibu.flight.internalws.models.message.GetEmailDataRequest;
import com.ctrip.ibu.flight.internalws.models.message.GetEmailDataResponse;

/**
 * 获取邮件数据处理基类
 * todo:如果觉得获取邮件数据(subject,bodyContent等纬度过大，这里可以重构，针对每个纬度单独去获取)
 * @author : kyxie
 * createtime : 2018/5/17 12:14
 */
public abstract class GetEmailDataProcessBase implements IGetMessageDataService<GetEmailDataRequest,GetEmailDataResponse,GetEmailDataException> {

    /**
     * 验证请求
     * @param request 获取邮件数据请求
     * @throws GetEmailDataException 获取邮件数据异常
     * */
    protected void proCheckRequest(GetEmailDataRequest request) throws GetEmailDataException{
        Checker.checkWithThrowable(request == null,new GetEmailDataException(GetEmailDataResultCode.ILLEGAL_REQUEST,"请求为NULL"),() -> {
            LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel,LogLevel.ERROR.toString());
            LoggerHelper.addIndexedLogTag(IndexedLogTag.Note,"请求为NULL不合法");
            LoggerHelper.appendResponseContent("获取邮件数据请求不合法：获取邮件数据请求为NULL");
        });

        Checker.checkWithThrowable(request.getSendEmailRequest() == null,new GetEmailDataException(GetEmailDataResultCode.ILLEGAL_REQUEST,"发送邮件请求为NULL"),() -> {
            LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel,LogLevel.ERROR.toString());
            LoggerHelper.addIndexedLogTag(IndexedLogTag.Note,"发送邮件请求为NULL");
            LoggerHelper.appendResponseContent("获取邮件数据请求不合法：邮件发送请求为NULL");
        });

        Checker.checkWithThrowable(request.getSendEmailRequest().emailTemplateType == null || request.getSendEmailRequest().emailTemplateType == EmailTemplateType.unspecified,
                new GetEmailDataException(GetEmailDataResultCode.ILLEGAL_REQUEST,"邮件类型不合法"),() -> {
                    LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel,LogLevel.ERROR.toString());
                    LoggerHelper.addIndexedLogTag(IndexedLogTag.Note,"邮件类型不合法");
                    LoggerHelper.appendResponseContent("邮件类型不合法");
                });
    }

    /**
     * 获取邮件数据
     * @param request 获取邮件数据请求
     * @throws IllegalArgumentException 参数不合法异常
     * @throws GetEmailDataException 获取邮件数据异常
     * */
    public GetEmailDataResponse getEmailData(GetEmailDataRequest request) throws IllegalArgumentException, GetEmailDataException{

        //验证参数
        this.proCheckRequest(request);

        //获取邮件数据
        return this.getMessageSendInfo(request);
    }
}
