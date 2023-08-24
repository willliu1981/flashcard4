package idv.kuan.flashcard4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import idv.kuan.flashcard4.dao.FlashcardDao;
import idv.kuan.flashcard4.databases.models.Flashcard;
import idv.kuan.kuanandroidlibs.databases.provider.AndroidDBFactory;
import idv.kuan.libs.databases.DBFactoryCreator;
import idv.kuan.libs.databases.utils.SchemaModifierExecutor;
import idv.kuan.libs.databases.utils.TableSchemaModifier;
import idv.kuan.libs.databases.utils.TableSchemaModifiers;


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
        Connection connection = DBFactoryCreator.getFactory().getConnection();

        String sql1 = "CREATE TABLE \"flashcard\" ( " +
                " \"id\" INTEGER NOT NULL UNIQUE, " +
                " \"term\" TEXT NOT NULL, " +
                " \"translation\" TEXT NOT NULL, " +
                " \"phonetic_notation\" TEXT, " +
                " \"sound_id\" INTEGER, " +
                " \"at_created\" TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                " \"at_updated\" TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                " \"testg\" INTEGER NOT NULL, " +
                " PRIMARY KEY(\"id\" AUTOINCREMENT) " +
                ")";


        String sql2 = "CREATE TABLE \"flashcard_bundle\" (   " +
                "   \"id\"   INTEGER NOT NULL UNIQUE,   " +
                "   \"name\"   TEXT NOT NULL UNIQUE,   " +
                "   \"last_review_time\"   TEXT,   " +
                "   \"review_level\"   INTEGER NOT NULL DEFAULT 0,   " +
                "   \"at_created\"   TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,   " +
                "   \"at_updated\"   TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,   " +
                "   PRIMARY KEY(\"id\" AUTOINCREMENT)   " +
                ")";


        TableSchemaModifier tableSchemaModifier = new TableSchemaModifier();

        tableSchemaModifier.addSchemaModifierExecutor(new SchemaModifierExecutor() {
            @Override
            public void execute() {
                TableSchemaModifiers.createOrUpdateTableWithDataMigration(connection,
                        appVersion, "flashcard", sql1, "(id,term,translation,phonetic_notation," +
                                "sound_id,at_created,at_updated,testg) select id,term,translation,phonetic_notation," +
                                "sound_id,at_created,at_updated,COALESCE(testg, -9)");
            }
        });

        tableSchemaModifier.addSchemaModifierExecutor(new SchemaModifierExecutor() {
            @Override
            public void execute() {
                TableSchemaModifiers.createOrUpdateTableWithDataMigration(connection,
                        appVersion, "flashcard_bundle", sql2, null);
            }
        });

        tableSchemaModifier.updateDBVersion(connection, appVersion);


    }


    private void changeTableStructureOld(int appVersion) {
        Connection connection = DBFactoryCreator.getFactory().getConnection();

        String sql = "CREATE TABLE \"flashcard\" ( " +
                " \"id\" INTEGER NOT NULL UNIQUE, " +
                " \"term\" TEXT NOT NULL, " +
                " \"translation\" TEXT NOT NULL, " +
                " \"phonetic_notation\" TEXT, " +
                " \"sound_id\" INTEGER, " +
                " \"at_created\" TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                " \"at_updated\" TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                " \"testr\" INTEGER, " +
                " PRIMARY KEY(\"id\" AUTOINCREMENT) " +
                ")";

        boolean isUpdated = TableSchemaModifiers.createOrUpdateTableWithDataMigration(connection,
                appVersion, "flashcard", sql, "(id,term,translation,phonetic_notation," +
                        "sound_id,at_created,at_updated,testr) select id,term,translation,phonetic_notation," +
                        "sound_id,at_created,at_updated,-1");

        sql = "CREATE TABLE \"flashcard_bundle\" (   " +
                "   \"id\"   INTEGER NOT NULL UNIQUE,   " +
                "   \"name\"   TEXT NOT NULL UNIQUE,   " +
                "   \"last_review_time\"   TEXT,   " +
                "   \"review_level\"   INTEGER NOT NULL DEFAULT 0,   " +
                "   \"at_created\"   TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,   " +
                "   \"at_updated\"   TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,   " +
                "   PRIMARY KEY(\"id\" AUTOINCREMENT)   " +
                ")";


        TableSchemaModifiers.createOrUpdateTableWithDataMigration(connection,
                appVersion, "flashcard_bundle", sql, null);


        TableSchemaModifiers.updateDBVersion(connection, appVersion);

        System.out.println("xxx MA is table updated? " + isUpdated);


    }

    private void testCreate() {
        FlashcardDao dao = new FlashcardDao();
        Flashcard flashcard = new Flashcard();
        int rnd = ((int) (Math.random() * 1000));
        flashcard.setTerm("apple" + rnd);
        flashcard.setTranslation("蘋果" + rnd);
        flashcard.setAtCreated("2023-07-02 23:12:22");
        flashcard.setAtUpdated("2023-07-02 23:12:22");
        flashcard.setTest((int) (Math.random()*100));

        try {
            dao.create(flashcard);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testQuery() {
        FlashcardDao dao = new FlashcardDao();
        List<Flashcard> list = new ArrayList<>();
        try {
            list = dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println("xxx MA list:" + list.size());

        for (Flashcard f : list) {
            System.out.println("xxx MA element:item=" + f);

        }


    }

    private void testQueryOld() {


        FlashcardDao dao = new FlashcardDao();
        Flashcard flashcard = new Flashcard();
        //flashcard.setId(3);
        Flashcard byId = null;
        List<Flashcard> list = new ArrayList<>();
        try {
            //byId = dao.findById(flashcard);

            list = dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println("xxx MA list:" + list.size());

        for (Flashcard f : list) {

            System.out.println("xxx MA element:" + f.getTerm() + ", item=" + f + " , transation=" + f.getTranslation());

        }
        //System.out.println("xxx MA byId:" + byId);
    }


}