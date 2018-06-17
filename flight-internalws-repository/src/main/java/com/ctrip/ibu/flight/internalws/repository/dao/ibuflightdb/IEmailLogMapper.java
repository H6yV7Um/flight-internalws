package com.ctrip.ibu.flight.internalws.repository.dao.ibuflightdb;

import com.ctrip.ibu.flight.internalws.models.repository.dao.ibuflightdb.EmailLogDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * EmailLog DAO操作
 * Create by kyxie on 2018/4/19 14:51
 */
@Mapper
@Transactional
public interface IEmailLogMapper {

    /**
     * 插入一条记录
     * @param emailLog EmailLog
     * */
    int insert(@Param("emailLog") EmailLogDO emailLog);

    /**
     * 批量插入记录
     * @param emailLogList EmailLog列表
     * */
    void batchInsert(List<EmailLogDO> emailLogList);
}
