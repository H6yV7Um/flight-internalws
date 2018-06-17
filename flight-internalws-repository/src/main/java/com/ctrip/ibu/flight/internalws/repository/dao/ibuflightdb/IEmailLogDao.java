package com.ctrip.ibu.flight.internalws.repository.dao.ibuflightdb;

import com.ctrip.ibu.flight.internalws.models.repository.dao.ibuflightdb.EmailLog;
import com.ctrip.platform.dal.dao.DalHints;
import com.ctrip.platform.dal.dao.KeyHolder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by kyxie on 2017/8/23.
 */
public interface IEmailLogDao {

    /**
     * Query EmailLog by the specified ID
     * The ID must be a number
     **/
    public EmailLog queryByPk(Number id)
            throws SQLException;

    /**
     * Query EmailLog by the specified ID
     * The ID must be a number
     **/
    public EmailLog queryByPk(Number id, DalHints hints)
            throws SQLException;

    /**
     * Query EmailLog by EmailLog instance which the primary key is set
     **/
    public EmailLog queryByPk(EmailLog pk)
            throws SQLException;

    /**
     * Query EmailLog by EmailLog instance which the primary key is set
     **/
    public EmailLog queryByPk(EmailLog pk, DalHints hints)
            throws SQLException;

    /**
     * Query against sample pojo. All not null attributes of the passed in pojo
     * will be used as search criteria.
     **/
    public List<EmailLog> queryLike(EmailLog sample)
            throws SQLException;

    /**
     * Query against sample pojo. All not null attributes of the passed in pojo
     * will be used as search criteria.
     **/
    public List<EmailLog> queryLike(EmailLog sample, DalHints hints)
            throws SQLException;

    /**
     * Get the all records count
     */
    public int count() throws SQLException;

    /**
     * Get the all records count
     */
    public int count(DalHints hints) throws SQLException;

    /**
     * Query EmailLog with paging function
     * The pageSize and pageNo must be greater than zero.
     */
    public List<EmailLog> queryAllByPage(int pageNo, int pageSize)  throws SQLException;

    /**
     * Query EmailLog with paging function
     * The pageSize and pageNo must be greater than zero.
     */
    public List<EmailLog> queryAllByPage(int pageNo, int pageSize, DalHints hints)  throws SQLException;

    /**
     * Get all records from table
     */
    public List<EmailLog> queryAll() throws SQLException;

    /**
     * Get all records from table
     */
    public List<EmailLog> queryAll(DalHints hints) throws SQLException;

    /**
     * Insert single pojo
     *
     * @param daoPojo
     *            pojo to be inserted
     * @return how many rows been affected
     * @throws SQLException
     */
    public int insert(EmailLog daoPojo) throws SQLException;

    /**
     * Insert single pojo
     *
     * @param hints
     *            Additional parameters that instruct how DAL Client perform database operation.
     * @param daoPojo
     *            pojo to be inserted
     * @return how many rows been affected
     * @throws SQLException
     */
    public int insert(DalHints hints, EmailLog daoPojo) throws SQLException;

    /**
     * Insert pojos one by one. If you want to inert them in the batch mode,
     * user batchInsert instead. You can also use the combinedInsert.
     *
     * @param daoPojos
     *            list of pojos to be inserted
     * @return how many rows been affected
     */
    public int[] insert(List<EmailLog> daoPojos) throws SQLException;

    /**
     * Insert pojos one by one. If you want to inert them in the batch mode,
     * user batchInsert instead. You can also use the combinedInsert.
     *
     * @param hints
     *            Additional parameters that instruct how DAL Client perform database operation.
     *            DalHintEnum.continueOnError can be used
     *            to indicate that the inserting can be go on if there is any
     *            failure.
     * @param daoPojos
     *            list of pojos to be inserted
     * @return how many rows been affected
     */
    public int[] insert(DalHints hints, List<EmailLog> daoPojos) throws SQLException;

    /**
     * Insert pojo and get the generated PK back in keyHolder.
     * If the "set no count on" for MS SqlServer is set, the operation may fail.
     * Please don't pass keyholder for MS SqlServer to avoid the failure in such case.
     *
     * @param keyHolder
     *            holder for generated primary keys
     * @param daoPojo
     *            pojo to be inserted
     * @return how many rows been affected
     * @throws SQLException
     */
    public int insertWithKeyHolder(KeyHolder keyHolder, EmailLog daoPojo) throws SQLException;

    /**
     * Insert pojo and get the generated PK back in keyHolder.
     * If the "set no count on" for MS SqlServer is set, the operation may fail.
     * Please don't pass keyholder for MS SqlServer to avoid the failure in such case.
     *
     * @param hints
     *            Additional parameters that instruct how DAL Client perform database operation.
     * @param keyHolder
     *            holder for generated primary keys
     * @param daoPojo
     *            pojo to be inserted
     * @return how many rows been affected
     * @throws SQLException
     */
    public int insert(DalHints hints, KeyHolder keyHolder, EmailLog daoPojo) throws SQLException;

    /**
     * Insert pojos and get the generated PK back in keyHolder.
     * If the "set no count on" for MS SqlServer is set, the operation may fail.
     * Please don't pass keyholder for MS SqlServer to avoid the failure in such case.
     *
     * @param keyHolder
     *            holder for generated primary keys
     * @param daoPojos
     *            list of pojos to be inserted
     * @return how many rows been affected
     * @throws SQLException
     */
    public int[] insertWithKeyHolder(KeyHolder keyHolder, List<EmailLog> daoPojos) throws SQLException;

    /**
     * Insert pojos and get the generated PK back in keyHolder.
     * If the "set no count on" for MS SqlServer is set, the operation may fail.
     * Please don't pass keyholder for MS SqlServer to avoid the failure in such case.
     *
     * @param hints
     *            Additional parameters that instruct how DAL Client perform database operation.
     *            DalHintEnum.continueOnError can be used
     *            to indicate that the inserting can be go on if there is any
     *            failure.
     * @param keyHolder
     *            holder for generated primary keys
     * @param daoPojos
     *            list of pojos to be inserted
     * @return how many rows been affected
     * @throws SQLException
     */
    public int[] insert(DalHints hints, KeyHolder keyHolder, List<EmailLog> daoPojos) throws SQLException;

    /**
     * Insert pojos in batch mode.
     * The DalDetailResults will be set in hints to allow client know how the operation performed in each of the shard.
     *
     * @param daoPojos list of pojos to be inserted
     * @return how many rows been affected for inserting each of the pojo
     * @throws SQLException
     */
    public int[] batchInsert(List<EmailLog> daoPojos) throws SQLException ;

    /**
     * Insert pojos in batch mode.
     * The DalDetailResults will be set in hints to allow client know how the operation performed in each of the shard.
     *
     * @param hints Additional parameters that instruct how DAL Client perform database operation.
     * @param daoPojos list of pojos to be inserted
     * @return how many rows been affected for inserting each of the pojo
     * @throws SQLException
     */
    public int[] batchInsert(DalHints hints, List<EmailLog> daoPojos) throws SQLException;

    /**
     * Delete the given pojo.
     *
     * @param daoPojo pojo to be deleted
     * @return how many rows been affected
     * @throws SQLException
     */
    public int delete(EmailLog daoPojo) throws SQLException;

    /**
     * Delete the given pojo.
     *
     * @param hints Additional parameters that instruct how DAL Client perform database operation.
     * @param daoPojo pojo to be deleted
     * @return how many rows been affected
     * @throws SQLException
     */
    public int delete(DalHints hints, EmailLog daoPojo) throws SQLException;

    /**
     * Delete the given pojos list one by one.
     *
     * @param daoPojos list of pojos to be deleted
     * @return how many rows been affected
     * @throws SQLException
     */
    public int[] delete(List<EmailLog> daoPojos) throws SQLException;

    /**
     * Delete the given pojos list one by one.
     *
     * @param hints Additional parameters that instruct how DAL Client perform database operation.
     * @param daoPojos list of pojos to be deleted
     * @return how many rows been affected
     * @throws SQLException
     */
    public int[] delete(DalHints hints, List<EmailLog> daoPojos) throws SQLException;

    /**
     * Delete the given pojo list in batch.
     * The DalDetailResults will be set in hints to allow client know how the operation performed in each of the shard.
     *
     * @param daoPojos list of pojos to be deleted
     * @return how many rows been affected for deleting each of the pojo
     * @throws SQLException
     */
    public int[] batchDelete(List<EmailLog> daoPojos) throws SQLException;

    /**
     * Delete the given pojo list in batch.
     * The DalDetailResults will be set in hints to allow client know how the operation performed in each of the shard.
     *
     * @param hints Additional parameters that instruct how DAL Client perform database operation.
     * @param daoPojos list of pojos to be deleted
     * @return how many rows been affected for deleting each of the pojo
     * @throws SQLException
     */
    public int[] batchDelete(DalHints hints, List<EmailLog> daoPojos) throws SQLException;

    /**
     * Update the given pojo . By default, if a field of pojo is null actionId,
     * that field will be ignored, so that it will not be updated. You can
     * overwrite this by set updateNullField in hints.
     *
     * @param daoPojo pojo to be updated
     * @return how many rows been affected
     * @throws SQLException
     */
    public int update(EmailLog daoPojo) throws SQLException ;

    /**
     * Update the given pojo . By default, if a field of pojo is null actionId,
     * that field will be ignored, so that it will not be updated. You can
     * overwrite this by set updateNullField in hints.
     *
     * @param hints
     * 			Additional parameters that instruct how DAL Client perform database operation.
     *          DalHintEnum.updateNullField can be used
     *          to indicate that the field of pojo is null actionId will be update.
     * @param daoPojo pojo to be updated
     * @return how many rows been affected
     * @throws SQLException
     */
    public int update(DalHints hints, EmailLog daoPojo) throws SQLException;

    /**
     * Update the given pojo list one by one. By default, if a field of pojo is null actionId,
     * that field will be ignored, so that it will not be updated. You can
     * overwrite this by set updateNullField in hints.
     *
     * @param daoPojos list of pojos to be updated
     * @return how many rows been affected
     * @throws SQLException
     */
    public int[] update(List<EmailLog> daoPojos) throws SQLException;

    /**
     * Update the given pojo list one by one. By default, if a field of pojo is null actionId,
     * that field will be ignored, so that it will not be updated. You can
     * overwrite this by set updateNullField in hints.
     *
     * @param hints
     * 			Additional parameters that instruct how DAL Client perform database operation.
     *          DalHintEnum.updateNullField can be used
     *          to indicate that the field of pojo is null actionId will be update.
     * @param daoPojos list of pojos to be updated
     * @return how many rows been affected
     * @throws SQLException
     */
    public int[] update(DalHints hints, List<EmailLog> daoPojos) throws SQLException;

    /**
     * Update the given pojo list in batch.
     *
     * @return how many rows been affected
     * @throws SQLException
     */
    public int[] batchUpdate(List<EmailLog> daoPojos) throws SQLException;

    /**
     * Update the given pojo list in batch.
     *
     * @return how many rows been affected
     * @throws SQLException
     */
    public int[] batchUpdate(DalHints hints, List<EmailLog> daoPojos) throws SQLException;
}
