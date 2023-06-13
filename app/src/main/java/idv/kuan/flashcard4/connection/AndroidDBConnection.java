package idv.kuan.flashcard4.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import idv.kuan.databases.DBConnection;

public class AndroidDBConnection extends DBConnection {
    public static String DefaultSuffixUrl = "databases/fc4.db";

    @Override
    public Connection getConnection(String commands[]) {
        /*
        command1 是常前package路徑
        command2 是android 預設db路徑
        "/data/data/"+command1+command2 才是完整路徑
        省略 command[1] 將以DefaultSuffixUrl 為值於command2
         */

        String command1 = "";
        String command2 = "";

        if (commands == null || !(commands.length >= 1)) {
            command1 = "";

        }

        if (!(commands.length >= 2) || commands[1] == null || commands[1].equals("")) {
            command2 = DefaultSuffixUrl;


        }


        String url = "jdbc:sqldroid:" + "/data/data/" + command1 + "/" + command2;
        try {
            Class.forName("org.sqldroid.SQLDroidDriver");
            return DriverManager.getConnection(url);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
