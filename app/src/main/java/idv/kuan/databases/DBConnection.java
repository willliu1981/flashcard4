package idv.kuan.databases;

import java.sql.Connection;

public abstract class DBConnection {


    public abstract Connection getConnection(String  commands[]);


}