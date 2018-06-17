package com.ctrip.ibu.flight.internalws.repository.entitymappers;

import com.ctrip.ibu.flight.internalws.models.repository.dao.ibuflightdb.EmailLogDO;
import com.ctrip.ibu.flight.internalws.models.message.EmailLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 邮件发送实体映射
 * Create by kyxie on 2018/4/19 15:55
 */
@Mapper
public interface EmailLogMapper {

    EmailLogMapper INSTANCE = Mappers.getMapper(EmailLogMapper.class);

    @Mappings({
            @Mapping(source = "templateType",target = "template"),
            @Mapping(source = "mailRequest",target = "request"),
            @Mapping(source = "resultMsg",target = "sendMsg"),
            @Mapping(source = "emailCategory",target = "emailCategory")
    })
    EmailLogDO emailLog2DOMapper(EmailLogEntity emailLog);

}
