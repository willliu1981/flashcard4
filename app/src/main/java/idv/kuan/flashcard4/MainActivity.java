package idv.kuan.flashcard4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import idv.kuan.androidlib.databases.provider.AndroidDBFactory;
import idv.kuan.databases.DBFactory;
import idv.kuan.flashcard4.dao.FlashcardDao;
import idv.kuan.flashcard4.databases.models.Flashcard;
import idv.kuan.libs.databases.DBFactoryCreator;
import idv.kuan.libs.databases.utils.TableSchemaModifier;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBFactoryCreator.getFactory(new AndroidDBFactory(this)).config("android1", "fc4.db", "fc4.db");


        //changeTableStructure();

        //*
        testCreate();
        testQuery();


        //*/

    }

    private void changeTableStructure() {
        String sql = "CREATE TABLE \"flashcards\" (" +
                "\"id\"INTEGER NOT NULL UNIQUE," +
                "\"term\"TEXT NOT NULL," +
                "\"translation\"TEXT NOT NULL," +
                "\"at_created\"TEXT NOT NULL," +
                "\"at_updated\"TEXT NOT NULL," +
                "PRIMARY KEY(\"id\" AUTOINCREMENT)" +
                ")";

        TableSchemaModifier.evolveTableStructure(DBFactoryCreator.getFactory().getConnection(),
                "flashcards", "flashcards", sql);
    }

    private void testCreate() {
        FlashcardDao dao = new FlashcardDao();
        Flashcard flashcard = new Flashcard();
        flashcard.setTerm("apple2");
        flashcard.setTranslation("蘋果2");
        flashcard.setAtCreated("2023-07-02 23:12:22");
        flashcard.setAtUpdated("2023-07-02 23:12:22");
        try {
            dao.create(flashcard);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testQuery() {


        Connection connection = DBFactoryCreator.getFactory().getConnection();

        ArrayList<Flashcard> list = new ArrayList<>();

        String sql = "select * from flashcards";


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            Flashcard flashcard = null;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String term = resultSet.getString("term");
                String translation = resultSet.getString("translation");
                flashcard = new Flashcard();
                flashcard.setId(id);
                flashcard.setTerm(term);
                flashcard.setTranslation(translation);
                list.add(flashcard);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("xxx MA list:" + list.size());

        for (Flashcard flashcard : list) {
            System.out.println("xxx MA element:id=" + flashcard.getId() + ", term:" + flashcard.getTerm()
                    + ", translation:" + flashcard.getTranslation());

        }


    }


    private void oldTestConn() {

        DBFactory.context = this;
        DBFactory.config(DBFactory.ANDROID, "fc4.db", null);
        Connection connection = DBFactory.getConnection();

        String sql = "select * from flashcards where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 2);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String term = resultSet.getString("term");
                String translation = resultSet.getString("translation");

                Toast.makeText(this, "term=" + term + " ; trans=" + translation, Toast.LENGTH_LONG);
                System.out.println("xxx MA:" + "term=" + term + " ; trans=" + translation);
            } else {
                System.out.println("xxx MA: not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("xxx ma :" + connection);


    }
}