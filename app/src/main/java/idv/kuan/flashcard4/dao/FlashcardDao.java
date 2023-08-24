package idv.kuan.flashcard4.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import idv.kuan.flashcard4.databases.models.Flashcard;
import idv.kuan.libs.databases.DBFactoryCreator;
import idv.kuan.libs.databases.daos.CommonDao;
import idv.kuan.libs.databases.models.CommonEntity;
import idv.kuan.libs.databases.utils.QueryBuilder;

public class FlashcardDao extends CommonDao<Flashcard> {


    @Override
    protected Flashcard createNewEntity() {
        return new Flashcard();
    }


    protected void mapResultSetToEntity(Flashcard entity, ResultSet resultSet) throws SQLException {
        entity.setId(resultSet.getInt("id"));
        entity.setTerm(resultSet.getString("term"));
        entity.setTranslation(resultSet.getString("translation"));
        entity.setAtCreated(resultSet.getString("at_created"));
        entity.setAtUpdated(resultSet.getString("at_updated"));
        entity.setTest(resultSet.getInt("testg"));
    }

    @Override
    protected void populateBuilderWithEntityProperties(QueryBuilder builder, Flashcard entity) {
        builder.addColumnValue("term", entity.getTerm());
        builder.addColumnValue("translation", entity.getTranslation());
        builder.addColumnValue("at_created", entity.getAtCreated() == null ? null :
                entity.getAtCreated().toString());
        builder.addColumnValue("at_updated", entity.getAtCreated() == null ? null :
                entity.getAtUpdated().toString());
        builder.addColumnValue("testg", entity.getTest());
    }


    @Override
    protected String getTableName() {
        return "flashcard";
    }


    @Override
    public Flashcard findById(Flashcard entity) throws SQLException {
        return this.findByIDOrAll(entity);
    }

    @Override
    public void delete(Flashcard entity) throws SQLException {

    }

    @Override
    public ArrayList<Flashcard> findAll() throws SQLException {
        return this.findByIDOrAll(new Flashcard());
    }


}
