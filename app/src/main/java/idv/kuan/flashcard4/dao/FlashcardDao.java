package idv.kuan.flashcard4.dao;


import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import idv.kuan.flashcard4.databases.models.Flashcard;
import idv.kuan.libs.databases.daos.CommonDao;
import idv.kuan.libs.databases.models.CommonEntity;
import idv.kuan.libs.databases.utils.QueryBuilder;

public class FlashcardDao extends CommonDao<Flashcard> {

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
    public CommonEntity findById(Serializable id) throws SQLException {
        return null;
    }


    @Override
    public void delete(CommonEntity entity) throws SQLException {

    }

    @Override
    public List<CommonEntity> findAll() throws SQLException {
        return null;
    }
}
