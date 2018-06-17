package com.ctrip.ibu.flight.internalws.repository.dao.ibuflightdb;

import com.ctrip.ibu.flight.internalws.models.repository.dao.ibuflightdb.Localeidmappingconfig;
import com.ctrip.platform.dal.dao.*;
import com.ctrip.platform.dal.dao.sqlbuilder.*;
import java.sql.SQLException;
import java.util.List;

import com.ctrip.platform.dal.dao.helper.DalDefaultJpaParser;

/**
 * Created by zongqingwu on 2018/1/22.
 */
public class LocaleidMappingConfigDao implements ILocaleidMappingConfigDao {

    private static final boolean ASC = true;
    private DalTableDao<Localeidmappingconfig> client;

    public LocaleidMappingConfigDao() throws SQLException {
        this.client = new DalTableDao<>(new DalDefaultJpaParser<>(Localeidmappingconfig.class));
    }

    /**
     * Get all records from table
     */
    public List<Localeidmappingconfig> queryAll() throws SQLException {
        return queryAll(null);
    }

    /**
     * Get all records from table
     */
    public List<Localeidmappingconfig> queryAll(DalHints hints) throws SQLException {
        hints = DalHints.createIfAbsent(hints);

        SelectSqlBuilder builder = new SelectSqlBuilder().selectAll().orderBy("LocaleID", ASC);

        return client.query(builder, hints);
    }
}
