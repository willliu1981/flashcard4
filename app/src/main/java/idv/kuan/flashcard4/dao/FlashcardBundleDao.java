package idv.kuan.flashcard4.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import idv.kuan.flashcard4.databases.models.FlashcardBundle;
import idv.kuan.libs.databases.daos.CommonDao;
import idv.kuan.libs.databases.utils.QueryBuilder;

public class FlashcardBundleDao extends CommonDao<FlashcardBundle> {
    @Override
    protected FlashcardBundle createNewEntity() {
        return new FlashcardBundle();
    }


    @Override
    protected void populateBuilderWithEntityProperties(QueryBuilder builder, FlashcardBundle entity) {
        builder.addColumnValue("name", entity.getName());
        builder.addNullableColumnValue("last_review_time", entity.getLastReviewTime());
        builder.addColumnValue("review_level", entity.getReviewLevel());
        builder.addColumnValue("at_created", entity.getAtCreated() == null ? null : entity.getAtCreated().toString());
        builder.addColumnValue("at_updated", entity.getAtUpdated() == null ? null : entity.getAtUpdated().toString());
    }

    @Override
    protected void mapResultSetToEntity(FlashcardBundle entity, ResultSet resultSet) throws SQLException {
        entity.setId(resultSet.getInt("id"));
        entity.setName(resultSet.getString("name"));
        entity.setLastReviewTime(resultSet.getString("last_review_time"));
        entity.setReviewLevel(resultSet.getInt("review_level"));
        entity.setAtCreated(resultSet.getString("at_created"));
        entity.setAtUpdated(resultSet.getString("at_updated"));
    }

    @Override
    protected String getTableName() {
        return "flashcard_bundle";
    }
}
