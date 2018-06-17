package com.ctrip.ibu.flight.internalws.repository.dao.ibuflightdb;

import com.ctrip.ibu.flight.internalws.models.repository.dao.ibuflightdb.MetaUseChannelRule;
import com.ctrip.platform.dal.dao.DalHints;

import java.sql.SQLException;
import java.util.List;

/**
 * Create by kyxie on 2017/11/29 13:48
 */
public interface ICustomChannelRuleDao {

    /**
     * 通过SID获取ChannelSid
     * */
    List<MetaUseChannelRule> getChannelRulaBySid(Integer sid,DalHints dalHints) throws SQLException;

}
