package com.ctrip.ibu.flight.internalws.repository.dao.ibuflightdb;

import com.ctrip.ibu.flight.internalws.models.repository.dao.ibuflightdb.MetaUseChannelRule;
import com.ctrip.platform.dal.common.enums.DatabaseCategory;
import com.ctrip.platform.dal.dao.*;
import com.ctrip.platform.dal.dao.helper.DalDefaultJpaMapper;
import com.ctrip.platform.dal.dao.sqlbuilder.FreeSelectSqlBuilder;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义ChannelRuleDao
 * Create by kyxie on 2017/11/29 13:50
 */
@Component("customChannelRuleDao")
public class CustomChannelRuleDao implements ICustomChannelRuleDao {

    private static final String DATA_BASE = "ibuflightdb_W";

    private static final DatabaseCategory DATABASE_CATEGORY = DatabaseCategory.MySql;

    private DalQueryDao queryDao = null;

    private DalRowMapper<MetaUseChannelRule> channelRuleModelDalRowMapper = null;

    public CustomChannelRuleDao() throws SQLException{
        this.channelRuleModelDalRowMapper = new DalDefaultJpaMapper<>(MetaUseChannelRule.class);
        this.queryDao = new DalQueryDao(DATA_BASE);
    }

    /**
     * 通过SID获取ChannelSid
     *
     * @param sid
     */
    @Override
    public List<MetaUseChannelRule> getChannelRulaBySid(Integer sid, DalHints dalHints) throws SQLException {

        List<MetaUseChannelRule> resultList = new ArrayList<>();

        if (sid != null){

            FreeSelectSqlBuilder<List<MetaUseChannelRule>> builder = new FreeSelectSqlBuilder<>(DATABASE_CATEGORY);

            builder.setTemplate("select * from metausechannelrule as m where SID=? order by m.sid desc");

            StatementParameters parameters = new StatementParameters();
            parameters.setSensitive(1,"SID", Types.INTEGER,sid);

//            StatementParameter sidParam = new StatementParameter();
//            sidParam.setName("sid");
//            sidParam.setValue(sid);
//            sidParam.setSqlType(Types.INTEGER);
//
//            parameters.add(sidParam);

            builder.mapWith(channelRuleModelDalRowMapper);
            dalHints=DalHints.createIfAbsent(dalHints);
            resultList = queryDao.query(builder,parameters,dalHints);
        }

        return resultList;
    }
}
