package idv.kuan.flashcard4.databases.models;


import java.sql.Timestamp;

import idv.kuan.libs.databases.models.CommonEntity;

public class FlashcardBundle implements CommonEntity {
    private Integer id;
    private String name;
    private Timestamp lastReviewTime;
    private Integer reviewLevel;
    private Timestamp atCreated;
    private Timestamp atUpdated;


    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getLastReviewTime() {
        return lastReviewTime;
    }

    public void setLastReviewTime(Timestamp lastReviewTime) {
        this.lastReviewTime = lastReviewTime;
    }

    public void setLastReviewTime(String lastReviewTime) {
        try {
            setLastReviewTime(Timestamp.valueOf(lastReviewTime));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer getReviewLevel() {
        return reviewLevel;
    }

    public void setReviewLevel(Integer reviewLevel) {
        this.reviewLevel = reviewLevel;
    }

    public Timestamp getAtCreated() {
        return atCreated;
    }

    public void setAtCreated(String atCreated) {
        try {
            setAtCreated(Timestamp.valueOf(atCreated));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAtCreated(Timestamp atCreated) {
        this.atCreated = atCreated;
    }


    public Timestamp getAtUpdated() {
        return atUpdated;
    }

    public void setAtUpdated(Timestamp atUpdated) {
        this.atUpdated = atUpdated;
    }

    public void setAtUpdated(String atUpdated) {

        try {
            setAtUpdated(Timestamp.valueOf(atUpdated));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "FlashcardBundle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastReviewTime=" + lastReviewTime +
                ", reviewLevel=" + reviewLevel +
                ", atCreated=" + atCreated +
                ", atUpdated=" + atUpdated +
                '}';
    }
}
