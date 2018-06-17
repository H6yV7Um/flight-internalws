package com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.mappers;

import com.ctrip.ibu.flight.internalws.models.message.EmailSendRequest;
import com.ctrip.ibu.flight.internalws.models.message.EmailSendResponseDO;
import com.ctrip.ibu.flight.internalws.models.message.EmailSendResponse;
import com.ctrip.ibu.flight.internalws.models.message.GetEmailDataResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 邮件发送结果Mapper
 * Create by kyxie on 2018/4/12 16:56
 */
@Mapper
public interface EmailSendResultMapper {

    public EmailSendResultMapper INSTANCE = Mappers.getMapper(EmailSendResultMapper.class);

    /**
     * 将邮件发送的响应转换为邮件发送响应DTO
     * @param emailSendResponse 邮件发送响应
     * @return 邮件发送DTO
     * */
    @Mappings({
            @Mapping(source = "resultCode",target = "sendResultCode"),
            @Mapping(source = "resultMsg",target = "sendResultMsg"),
            @Mapping(source = "emailIdList",target = "emailIdList")
    })
    EmailSendResponse emailSendResponseDO2EmailSendResponseDTO(EmailSendResponseDO emailSendResponse);

    /**
     * 将获取的邮件数据实体转换为邮件发送实体
     * @param getEmailDataResponse 获取邮件数据响应
     * */
    EmailSendRequest emailDataResponse2EmailSendRequest(GetEmailDataResponse getEmailDataResponse);

}
