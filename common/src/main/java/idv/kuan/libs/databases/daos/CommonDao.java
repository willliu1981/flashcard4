package idv.kuan.libs.databases.daos;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import idv.kuan.libs.databases.DBFactoryCreator;
import idv.kuan.libs.databases.models.CommonEntity;
import idv.kuan.libs.databases.utils.QueryBuilder;

public abstract class CommonDao<V extends CommonEntity> implements Dao<CommonEntity> {


    /**
     * 注意:connection 是從DBFactoryCreator取得預設factory的connection
     *
     * @param entity
     * @throws SQLException
     */
    @Override
    public void createOrUpdateEntity(CommonEntity entity) throws SQLException {
        if (entity == null) {
            throw new SQLException("entity is null");
        }

        Connection connection = DBFactoryCreator.getFactory().getConnection();

        QueryBuilder builder = new QueryBuilder();
        populateBuilderWithEntityProperties(builder, (V) entity);

        String query = null;

        if (entity.getId() == null) {
            query = builder.buildInsertQuery(getTableName());
        } else {
            String condition = "id = " + entity.getId();
            query = builder.buildUpdateQuery(getTableName(), condition);
        }


        PreparedStatement statment = connection.prepareStatement(query);
        builder.prepareStatement(statment);
        statment.executeUpdate();


    }


    /**
     * 會執行createOrUpdateEntity方法
     *
     * @param entity
     * @throws SQLException
     */
    @Override
    public void create(CommonEntity entity) throws SQLException {
        this.createOrUpdateEntity(entity);
    }

    /**
     * 會執行createOrUpdateEntity方法
     *
     * @param entity
     * @throws SQLException
     */
    @Override
    public void update(CommonEntity entity) throws SQLException {
        this.createOrUpdateEntity(entity);
    }


    /**
     * 使用createOrUpdateEntity方法時,必需實作的方法
     * 注意,如果create是AI,不應填id; update 不變更id,不應填id
     *
     * @param builder
     * @param entity
     */
    protected abstract void populateBuilderWithEntityProperties(QueryBuilder builder, V entity);

    protected abstract String getTableName();

}
