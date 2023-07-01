package idv.kuan.databases;

import android.content.Context;

import java.sql.Connection;

import idv.kuan.flashcard4.connection.AndroidDBConnection;
import idv.kuan.flashcard4.connection.DefaultDBConnection;

public class DBFactory {
    public static final String DEFAULT = "default";
    public static final String ANDROID = "android";

    private static String source = DEFAULT;
    private static String commands[];

    public static Context context;//這裡context 取得先寫死

    public static void config(String provider) {
        config(provider,null);
    }

    public static void config(String provider,String ... commands) {
        source = provider;
        DBFactory.commands=commands;
    }

    public static Connection getConnection() {
        switch (source) {
            case DEFAULT:
                return new DefaultDBConnection().getConnection(commands);
            case ANDROID:
                return new AndroidDBConnection(context).getConnection(commands);
            default:
                return null;
        }
    }
}
