package com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.mappers;

import com.ctrip.ibu.flight.internalws.models.message.EmailAttachmentDO;
import com.ctrip.ibu.flight.internalws.models.message.EmailAttachmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 邮件附件Mapper
 * Create by kyxie on 2018/4/14 16:51
 */
@Mapper
public interface EmailAttachmentMapper {

    EmailAttachmentMapper INSTANCE = Mappers.getMapper(EmailAttachmentMapper.class);

    /**
     * 邮件附件DTO转换为DO实体
     * @param emailAttachmentDTO 邮件附件DTO实体
     * @return 邮件附件DO实体
     * */
    EmailAttachmentDO emailAttachmentDO2DTO(EmailAttachmentDTO emailAttachmentDTO);

    /**
     * 邮件附件DO转换为DTO
     * @param emailAttachmentDO 邮件附件DO实体
     * @return 邮件附件DTO实体
     * */
    EmailAttachmentDTO emailAttachmentDTO2DO(EmailAttachmentDO emailAttachmentDO);

}
