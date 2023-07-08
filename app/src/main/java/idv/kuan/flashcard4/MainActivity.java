package idv.kuan.flashcard4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import idv.kuan.flashcard4.dao.FlashcardDao;
import idv.kuan.flashcard4.databases.models.Flashcard;
import idv.kuan.kuanandroidlibs.databases.provider.AndroidDBFactory;
import idv.kuan.libs.databases.DBFactoryCreator;
import idv.kuan.libs.databases.utils.TableSchemaModifier;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DBFactoryCreator.getFactory(new AndroidDBFactory(this)).config("android1", "fc4.db", "fc4.db");


        //changeTableStructure();
        //createNewStructure();

        /*
        testCreate();
        testQuery();


        //*/




        //*
        String s = DBFactoryCreator.testMsg();
        System.out.println("xxx MA: msg=" + s);

        //*/

    }

    private void createNewStructure() {
        String sql = "CREATE TABLE \"flashcard_set\" (" +
                "\"id\"INTEGER NOT NULL UNIQUE," +
                "\"name\"TEXT NOT NULL UNIQUE," +
                "\"last_review_time\"TEXT," +
                "\"review_level\"INTEGER NOT NULL DEFAULT 0," +
                "\"at_created\"TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "\"at_updated\"TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "PRIMARY KEY(\"id\" AUTOINCREMENT)" +
                ")";


        try {
            PreparedStatement preparedStatement = DBFactoryCreator.getFactory().getConnection().prepareStatement(sql);
            boolean execute = preparedStatement.execute();

            System.out.println("xxx MA:create ok? " + execute);
        } catch (SQLException e) {
            e.printStackTrace();
        }

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