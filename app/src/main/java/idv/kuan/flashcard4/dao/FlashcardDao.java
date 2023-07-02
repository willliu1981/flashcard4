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
    public <U> U findByIDOrAll(Flashcard entity) throws SQLException {
        if (entity == null) {
            throw new SQLException("entity is null");
        }
        Connection connection = DBFactoryCreator.getFactory().getConnection();
        String sqlQuery = "select * from " + getTableName();
        PreparedStatement preparedStatement = null;
        if (entity.getId() == null) {
            preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Flashcard> list = new ArrayList<>();
            Flashcard flashcard = null;
            while (resultSet.next()) {
                flashcard = new Flashcard();
                mapResultSetToEntity(flashcard, resultSet);
                list.add(flashcard);
            }

            return (U) list;

        } else {
            preparedStatement = connection.prepareStatement(sqlQuery + " where id=?");
            preparedStatement.setInt(1, entity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            Flashcard flashcard = new Flashcard();
            if (resultSet.next()) {
                mapResultSetToEntity(flashcard, resultSet);
            }
            return (U) flashcard;
        }
    }




    protected void mapResultSetToEntity(Flashcard entity, ResultSet resultSet) throws SQLException {
        entity.setId(resultSet.getInt("id"));
        entity.setTerm(resultSet.getString("term"));
        entity.setTranslation(resultSet.getString("translation"));
        entity.setAtCreated(resultSet.getString("at_created"));
        entity.setAtUpdated(resultSet.getString("at_updated"));
    }

    @Override
    protected void populateBuilderWithEntityProperties(QueryBuilder builder, Flashcard entity) {
        builder.addColumnValue("term", entity.getTerm());
        builder.addColumnValue("translation", entity.getTranslation());
        builder.addColumnValue("at_created", entity.getAtCreated() == null ? null :
                entity.getAtCreated().toString());
        builder.addColumnValue("at_updated", entity.getAtCreated() == null ? null :
                entity.getAtUpdated().toString());

    }


    @Override
    protected String getTableName() {
        return "flashcards";
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
