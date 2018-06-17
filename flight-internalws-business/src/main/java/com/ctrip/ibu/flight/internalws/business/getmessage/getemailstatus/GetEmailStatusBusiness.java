package com.ctrip.ibu.flight.internalws.business.getmessage.getemailstatus;

import com.ctrip.ibu.flight.internalws.common.config.Config;
import com.ctrip.ibu.flight.internalws.models.constant.EmailConst;
import com.ctrip.ibu.flight.internalws.contract.commontypes.Email;
import com.ctrip.ibu.flight.internalws.contract.getemailstatusservice.GetEmailStatusRequestType;
import com.ctrip.ibu.flight.internalws.contract.getemailstatusservice.GetEmailStatusResponseType;
import com.ctrip.ibu.flight.internalws.models.exception.repository.RepositoryException;
import com.ctrip.ibu.flight.internalws.models.message.EmailModel;
import com.ctrip.ibu.flight.internalws.models.message.GetEmailStatusRequestModel;
import com.ctrip.ibu.flight.internalws.models.message.GetEmailStatusResponseModel;
import com.ctrip.ibu.flight.internalws.models.message.GetEmailStatusResultCode;
import com.ctrip.ibu.flight.internalws.repository.IEmailRepository;
import com.ctriposs.baiji.rpc.common.types.AckCodeType;
import com.ctriposs.baiji.rpc.common.types.ResponseStatusType;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取邮件发送状态业务逻辑
 * Created by kyxie on 2017/8/31.
 */
@Service("getEmailStatusBusiness")
public class GetEmailStatusBusiness implements IGetEmailStatusBusiness {

    private IEmailRepository emailRepository;

    private Config Config;

    @Inject
    public GetEmailStatusBusiness(@Named("emailRepository") IEmailRepository emailRepository,
                                  @Named("config") Config Config){
        this.emailRepository = emailRepository;
        this.Config = Config;
    }

    /**
     * 获取邮件发送状态
     *
     * @param request
     */
    @Override
    public GetEmailStatusResponseType getEmailStatus(GetEmailStatusRequestType request) {

        GetEmailStatusResponseType response = new GetEmailStatusResponseType();
        ResponseStatusType responseStatus = new ResponseStatusType();
        Calendar now = Calendar.getInstance();
        now.setTime(new Date(System.currentTimeMillis()));
        responseStatus.setTimestamp(now);
        responseStatus.setAck(AckCodeType.Success);

        GetEmailStatusResultCode resultCode = verifyOrigRequest(request);
        if (resultCode == GetEmailStatusResultCode.Success){

            try {
                GetEmailStatusRequestModel getEmailStatusRequest = getEmailStatusRequest(request);
                GetEmailStatusResponseModel getEmailStatusResponse = emailRepository.getEmailStatus(getEmailStatusRequest);
                response.setResponseStatus(responseStatus);
                if (getEmailStatusResponse == null){
                    responseStatus.setAck(AckCodeType.Failure);
                }else {
                    response.setResultCode(getEmailStatusResponse.getResultCode() == 1 ? 0 : 1);
                    response.setResultMsg(getEmailStatusResponse.getResultMsg());
                    List<EmailModel> emailList = getEmailStatusResponse.getEmailList();
                    if (emailList != null && !emailList.isEmpty()){
                        List<Email> resEmailList = new ArrayList<>();
                        for (EmailModel emailModel : emailList){
                            resEmailList.add(convertEmailModel(emailModel));
                        }
                        response.setEmailList(resEmailList);
                    }
                }

            } catch (RepositoryException e) {
                responseStatus.setAck(AckCodeType.Failure);
                response.setResultCode(GetEmailStatusResultCode.SystemError.getValue());
                response.setResultMsg(String.format("获取邮件状态仓储异常，异常发生点：%s\r\n异常信息：%s\r\n",e.getErrorSource(),e.getMessage()));
            } catch (Exception e){
                responseStatus.setAck(AckCodeType.Failure);
                response.setResultCode(GetEmailStatusResultCode.SystemError.getValue());
                response.setResultMsg(String.format("获取邮件状态异常，异常信息：%s\r\n",e.getMessage()));
            }
        }else {
            response.setResultCode(resultCode.getValue());
            response.setResultMsg("请求不合法");
            responseStatus.setAck(AckCodeType.Failure);
            response.setResponseStatus(responseStatus);
        }
        response.setResponseStatus(responseStatus);

        return response;
    }

    //验证邮件发送请求
    private GetEmailStatusResultCode verifyOrigRequest(GetEmailStatusRequestType request){

        if (request == null){
            return GetEmailStatusResultCode.ParameterError;
        }
        if (request.getEmailIdList() == null || request.getEmailIdList().isEmpty()){
            return GetEmailStatusResultCode.ParameterError;
        }

        return GetEmailStatusResultCode.Success;
    }

    /**
     * 获取邮件状态查询请求
     * @param origReq 原始邮件状态查询请求
     * */
    private GetEmailStatusRequestModel getEmailStatusRequest(GetEmailStatusRequestType origReq){
        GetEmailStatusRequestModel request = new GetEmailStatusRequestModel();
        String sendCode = origReq.sendCode;
        if (sendCode == null || sendCode.isEmpty()){
            request.setSendCode(Config.getEmailConfigInfo().getEmailSendCode());
        }else {
            request.setSendCode(sendCode);
        }
        request.setEmailIdList(origReq.emailIdList);
        return request;
    }

    /**
     * 转换Email模型到响应
     * */
    private Email convertEmailModel(EmailModel emailModel){
        Email email = new Email();
        if (emailModel != null){
            email.setBcc(emailModel.getBcc());
            email.setUid(emailModel.getUid());
            email.setSubject(emailModel.getSubject());
            email.setSendTime(emailModel.getSendTime());
            email.setSendHost(emailModel.getSendHost());
            email.setSender(emailModel.getSender());
            email.setSenderName(emailModel.getSenderName());
            email.setCreateTime(emailModel.getCreateTime());
            email.setScheduleTime(emailModel.getScheduleTime());
            email.setExpiredTime(emailModel.getExpiredTime());
            email.setRecipient(emailModel.getRecipientList());
            email.setRecipientName(emailModel.getRecipientName());
            email.setErrorMsg(emailModel.getErrorMsg());
            email.setEmstpId(emailModel.getEmstpID());
            email.setEmailStatus(emailModel.getEmailStatus());
            email.setEmailId(emailModel.getEmailId());
            email.setEid(emailModel.getEid());
            email.setCc(emailModel.getCc());
            email.setBodyTemplateId(emailModel.getBodyTemplateId());
            String emailBody = emailModel.getBodyContent();
            if (emailBody != null && !emailBody.isEmpty()){
                Pattern pattern = Pattern.compile(EmailConst.REGEX_GETEMAILCONTENT);
                Matcher matcher = pattern.matcher(emailBody);
                //正则不生效时直接替换
                if (matcher.find()){
                    email.setBodyContent(matcher.group(0).intern());
                }else {
                    email.setBodyContent(emailBody.replace("<entry><content>","").replace("</content></entry>",""));
                }
            }
        }
        return email;
    }
}
