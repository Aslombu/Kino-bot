package org.example.Bot.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dburl {
    public static Connection connection;


    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if(connection == null){
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/kino", "postgres", "1117");
        }
        return connection;
    }

}
