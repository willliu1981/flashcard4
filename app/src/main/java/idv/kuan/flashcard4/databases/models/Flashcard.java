package idv.kuan.flashcard4.databases.models;

import java.sql.Timestamp;

import idv.kuan.libs.databases.models.CommonEntity;

public class Flashcard implements CommonEntity {

    private Integer id;
    private String term;
    private String translation;
    private Timestamp atCreated;
    private Timestamp atUpdated;
    private  Integer test;

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
        try {
            setAtCreated(Timestamp.valueOf(atCreated));

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public Integer getTest() {
        return test;
    }

    public void setTest(Integer test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "Flashcard{" +
                "id=" + id +
                ", term='" + term + '\'' +
                ", translation='" + translation + '\'' +
                ", atCreated=" + atCreated +
                ", atUpdated=" + atUpdated +
                ", test=" + test +
                '}';
    }



}
