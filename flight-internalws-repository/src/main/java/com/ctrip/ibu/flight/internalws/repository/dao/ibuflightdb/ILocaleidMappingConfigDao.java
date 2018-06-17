package com.ctrip.ibu.flight.internalws.repository.dao.ibuflightdb;

import com.ctrip.ibu.flight.internalws.models.repository.dao.ibuflightdb.Localeidmappingconfig;
import com.ctrip.platform.dal.dao.DalHints;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zongqingwu on 2018/1/22.
 */
public interface ILocaleidMappingConfigDao {
    /**
     * Get all records from table
     */
    List<Localeidmappingconfig> queryAll() throws SQLException;

    /**
     * Get all records from table
     */
    List<Localeidmappingconfig> queryAll(DalHints hints) throws SQLException ;
}
