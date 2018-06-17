package com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.mappers;

import com.ctrip.ibu.flight.internalws.models.message.EmailData;
import com.ctrip.ibu.flight.internalws.models.message.EmailSendRequestDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 邮件实体Mapper
 * Create by kyxie on 2018/4/12 16:12
 */
@Mapper
public interface EmailDataMapper {

    EmailDataMapper INSTANCE = Mappers.getMapper(EmailDataMapper.class);

    /**
     * 邮件发送数据DTO转换为邮件发送DO
     * @param emailData 邮件数据DTO
     * @return 邮件数据DO
     * */
    @Mappings({
            @Mapping(source = "emailSender",target = "senderInfo")
    })
    EmailSendRequestDO emailDataDTO2EmailDataDO(EmailData emailData);

}
