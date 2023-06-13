package idv.kuan.flashcard4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.sql.Connection;

import idv.kuan.databases.DBFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DBFactory.config(DBFactory.ANDROID, getPackageName());
        Connection connection = DBFactory.getConnection();


System.out.println("xxx ma :"+connection);

    }
}