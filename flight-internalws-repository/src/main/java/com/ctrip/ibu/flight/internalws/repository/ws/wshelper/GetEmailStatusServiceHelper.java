package com.ctrip.ibu.flight.internalws.repository.ws.wshelper;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.models.message.EmailModel;
import com.ctrip.soa.platform.basesystem.emailservice.v1.Email;
import org.springframework.stereotype.Component;

/**
 * 获取邮件状态服务帮助类
 * author : kyxie
 * date : 2018/5/2 11:57
 */
@Component
public class GetEmailStatusServiceHelper {

    private final static ILog CLOG = LogManager.getLogger(GetEmailStatusServiceHelper.class);

    /**
     * 转换WS层Email模型
     * */
    public EmailModel convertEmailModel(Email wsEmail){
        EmailModel emailModel = new EmailModel();
        if (wsEmail != null){
            emailModel.setEid(wsEmail.getEid());
            emailModel.setCreateTime(wsEmail.getCreateTime());
            emailModel.setBodyTemplateId(wsEmail.getBodyTemplateID());
            emailModel.setCc(wsEmail.getCc());
            emailModel.setBodyContent(wsEmail.getBodyContent());
            emailModel.setBcc(wsEmail.getBcc());
            emailModel.setEmailId(wsEmail.getEmailID());
            emailModel.setEmailStatus(wsEmail.getEmailStatus());
            emailModel.setEmstpID(wsEmail.getEmstpID());
            emailModel.setExpiredTime(wsEmail.getExpiredTime());
            emailModel.setUid(wsEmail.getUid());
            emailModel.setSubject(wsEmail.getSubject());
            emailModel.setSendTime(wsEmail.getSendTime());
            emailModel.setSendHost(wsEmail.getSendHost());
            emailModel.setSenderName(wsEmail.getSenderName());
            emailModel.setScheduleTime(wsEmail.getScheduleTime());
            emailModel.setRecipientList(wsEmail.getRecipient());
            emailModel.setRecipientName(wsEmail.getRecipientName());
            emailModel.setErrorMsg(wsEmail.getErrorMsg());
        }
        return emailModel;
    }

}
