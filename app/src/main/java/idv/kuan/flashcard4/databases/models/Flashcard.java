package idv.kuan.flashcard4.databases.models;

import java.sql.Timestamp;

import idv.kuan.libs.databases.models.CommonEntity;

public class Flashcard implements CommonEntity {

    private Integer id;
    private String term;
    private String translation;
    private Timestamp atCreated;
    private Timestamp atUpdated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public Timestamp getAtCreated() {
        return atCreated;
    }

    public void setAtCreated(Timestamp atCreated) {
        this.atCreated = atCreated;
    }

    public void setAtCreated(String atCreated) {
        setAtCreated(Timestamp.valueOf(atCreated));
    }

    public Timestamp getAtUpdated() {
        return atUpdated;
    }

    public void setAtUpdated(Timestamp atUpdated) {
        this.atUpdated = atUpdated;
    }

    public void setAtUpdated(String atUpdated) {
        setAtUpdated(Timestamp.valueOf(atUpdated));
    }

    @Override
    public String toString() {
        return "Flashcard{" +
                "id=" + id +
                ", term='" + term + '\'' +
                ", translation='" + translation + '\'' +
                ", atCreated=" + atCreated +
                ", atUpdated=" + atUpdated +
                '}';
    }
}
