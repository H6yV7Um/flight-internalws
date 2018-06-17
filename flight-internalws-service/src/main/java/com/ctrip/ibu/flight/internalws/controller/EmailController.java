package com.ctrip.ibu.flight.internalws.controller;

import com.ctrip.ibu.flight.internalws.business.getmessage.getemailstatus.IGetEmailStatusBusiness;
import com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.ISendEmailBusiness;
import com.ctrip.ibu.flight.internalws.common.config.Config;
import com.ctrip.ibu.flight.internalws.contract.commontypes.EmailTemplateType;
import com.ctrip.ibu.flight.internalws.contract.commontypes.LanguageType;
import com.ctrip.ibu.flight.internalws.contract.getemailstatusservice.GetEmailStatusRequestType;
import com.ctrip.ibu.flight.internalws.contract.getemailstatusservice.GetEmailStatusResponseType;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailRequestType;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailResponseType;
import com.ctrip.ibu.flight.internalws.models.message.GetEmailStatusResultCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * EmailController
 * Created by kyxie on 2017/9/1.
 */
@Controller
@RequestMapping({"/email"})
public class EmailController {

    private Config Config;

    private ISendEmailBusiness sendEmailBusiness;

    private IGetEmailStatusBusiness getEmailStatusBusiness;

    @Inject
    public EmailController(@Named("config") Config Config,
                           @Named("sendEmailBusiness") ISendEmailBusiness sendEmailBusiness,
                           @Named("getEmailStatusBusiness") IGetEmailStatusBusiness getEmailStatusBusiness){
        this.Config = Config;
        this.sendEmailBusiness = sendEmailBusiness;
        this.getEmailStatusBusiness = getEmailStatusBusiness;
    }

    @RequestMapping( value = {"","/","/home","/index"},method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");

        mv.addObject("emailTemplateList", EmailTemplateType.values());
        mv.addObject("emailSendLanList", LanguageType.values());
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = {"/sendEmail"},method = RequestMethod.POST)
    public SendEmailResponseType sendEmail(@RequestBody SendEmailRequestType sendEmailRequest){
        SendEmailResponseType response = new SendEmailResponseType();

        if (sendEmailRequest != null){
//            RequestHeader header = new RequestHeader();
//            header.setClientIp(Foundation.net().getHostAddress());
//            header.setClientSource("Local Test");
//            sendEmailRequest.setRequestHeader(header);
//            sendEmailRequest.setOperator("Local Test");
            response = sendEmailBusiness.sendMessage(sendEmailRequest);
        }else {
            response.setResultMessage("请求不能为空");
        }

        return response;
    }

    @ResponseBody
    @RequestMapping(value = {"/getEmailStatus"},method = RequestMethod.POST)
    public GetEmailStatusResponseType getEmailStatus(@RequestBody GetEmailStatusRequestType getEmailStatusRequest){
        GetEmailStatusResponseType response = new GetEmailStatusResponseType();
        if (getEmailStatusRequest != null){
            List<String> emailIdList = getEmailStatusRequest.getEmailIdList();
            if (emailIdList == null || emailIdList.isEmpty()){
                response.setResultCode(GetEmailStatusResultCode.ParameterError.getValue());
                response.setResultMsg("请求EmailId不能为空");
            }else {
                if (getEmailStatusRequest.getSendCode() == null || getEmailStatusRequest.getSendCode().isEmpty()){
                    getEmailStatusRequest.setSendCode(Config.getEmailConfigInfo().getEmailSendCode());
                }
                response = getEmailStatusBusiness.getEmailStatus(getEmailStatusRequest);
            }
        } else {
            response.setResultCode(GetEmailStatusResultCode.ParameterError.getValue());
            response.setResultMsg("请求不能为空");
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/getEmailStatus",method = RequestMethod.GET)
    public GetEmailStatusResponseType getEmailStatus(@RequestParam("emailIdList") List<String> emailIdList, @RequestParam("sendCode") String sendCode){
        GetEmailStatusResponseType response = new GetEmailStatusResponseType();
        if (emailIdList == null || !emailIdList.isEmpty()){
            GetEmailStatusRequestType request = new GetEmailStatusRequestType();
            request.setEmailIdList(emailIdList);
            if (sendCode == null || sendCode.isEmpty()){
                request.setSendCode(Config.getEmailConfigInfo().getEmailSendCode());
            }

            response = getEmailStatusBusiness.getEmailStatus(request);
        } else {
            response.setResultCode(GetEmailStatusResultCode.ParameterError.getValue());
            response.setResultMsg("请求EmailId不能为空");
        }

        return response;
    }

    /**
     * 获取邮件内容页面
     * */
    @RequestMapping(value = {"/getEmailContent"},method = RequestMethod.GET)
    public ModelAndView getEmailContent(@RequestParam("emailId") String emailId,@RequestParam("sendCode") String sendCode){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("emailcontent");
        GetEmailStatusResponseType response = new GetEmailStatusResponseType();
        response.setResultCode(GetEmailStatusResultCode.Success.getValue());
        if (emailId == null || !emailId.isEmpty()){
            GetEmailStatusRequestType request = new GetEmailStatusRequestType();
            List<String> emailIdList = new ArrayList<>();
            emailIdList.add(emailId);
            request.setEmailIdList(emailIdList);
            if (sendCode == null || sendCode.isEmpty()){
                request.setSendCode(Config.getEmailConfigInfo().getEmailSendCode());
            }

            response = getEmailStatusBusiness.getEmailStatus(request);
            if (response != null && response.getEmailList() != null && !response.getEmailList().isEmpty()){
                mv.addObject("bodyContent",response.emailList.get(0).bodyContent);
            }
        } else {
            response.setResultCode(GetEmailStatusResultCode.ParameterError.getValue());
            response.setResultMsg("请求EmailId不能为空");
        }
        return mv;
    }
}
