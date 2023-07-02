package idv.kuan.flashcard4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import idv.kuan.androidlib.databases.provider.AndroidDBFactory;
import idv.kuan.databases.DBFactory;
import idv.kuan.flashcard4.dao.FlashcardDao;
import idv.kuan.flashcard4.databases.models.Flashcard;
import idv.kuan.libs.databases.DBFactoryCreator;
import idv.kuan.libs.databases.daos.CommonDao;
import idv.kuan.libs.databases.models.CommonEntity;
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
        int rnd = ((int) (Math.random() * 1000));
        flashcard.setTerm("apple" + rnd);
        flashcard.setTranslation("蘋果" + rnd);
        flashcard.setAtCreated("2023-07-02 23:12:22");
        flashcard.setAtUpdated("2023-07-02 23:12:22");
        try {
            dao.create(flashcard);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testQuery() {


        FlashcardDao dao = new FlashcardDao();
        Flashcard flashcard = new Flashcard();
        flashcard.setId(3);
        Flashcard byId = null;
        List<Flashcard> list = new ArrayList<>();
        try {
            byId = dao.findById(flashcard);

            list = dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println("xxx MA list:" + list.size());

        for (Flashcard f : list) {

            System.out.println("xxx MA element:" + f.getTerm() + ", item=" + f);

        }
        System.out.println("xxx MA byId:" + byId);
    }


}