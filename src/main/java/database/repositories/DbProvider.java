package database.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbProvider {
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_HOST = "jdbc:mysql://localhost:3306/house_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(DB_HOST, DB_USER, DB_PASS);
    }

}