package idv.kuan.flashcard4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.sql.Connection;
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


        PackageManager packageManager = getPackageManager();
        int versionCode = -4;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            versionCode = packageInfo.versionCode;


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        changeTableStructure(versionCode);


        //changeTableStructure();
        //createNewStructure();

        //*
        testCreate();
        testQuery();


        //*/


    }


    private void changeTableStructure(int appVersion) {
        String sql = "CREATE TABLE \"flashcard\" (   " +
                "   \"id\"   INTEGER NOT NULL UNIQUE,   " +
                "   \"term\"   TEXT NOT NULL,   " +
                "   \"translation\"   TEXT NOT NULL,   " +
                "   \"at_created\"   TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,   " +
                "   \"at_updated\"   TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,   " +
                "   PRIMARY KEY(\"id\" AUTOINCREMENT)   " +
                ")";

        String sq2 = "CREATE TABLE \"flashcard_bundle\" (   " +
                "   \"id\"   INTEGER NOT NULL UNIQUE,   " +
                "   \"name\"   TEXT NOT NULL UNIQUE,   " +
                "   \"last_review_time\"   TEXT,   " +
                "   \"review_level\"   INTEGER NOT NULL DEFAULT 0,   " +
                "   \"at_created\"   TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,   " +
                "   \"at_updated\"   TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,   " +
                "   PRIMARY KEY(\"id\" AUTOINCREMENT)   " +
                ")";

        Connection connection = DBFactoryCreator.getFactory().getConnection();
        
        TableSchemaModifier.createOrUpdateTableWithDataMigration(connection,
                appVersion, "flashcard", sql);

        TableSchemaModifier.createOrUpdateTableWithDataMigration(connection,
                appVersion, "flashcard_bundle", sq2);

        TableSchemaModifier.updateDBVersion(connection, appVersion);


    }

    private void testCreate() {
        FlashcardDao dao = new FlashcardDao();
        Flashcard flashcard = new Flashcard();
        int rnd = ((int) (Math.random() * 1000));
        flashcard.setTerm("apple" + rnd);
        //flashcard.setTranslation("蘋果" + rnd);
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

            System.out.println("xxx MA element:" + f.getTerm() + ", item=" + f + " , transation=" + f.getTranslation());

        }
        System.out.println("xxx MA byId:" + byId);
    }


}