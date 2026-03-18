package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/stock_managmentv2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
<<<<<<< HEAD
    private static final String PASSWORD = "1234.";
=======
    private static final String PASSWORD = "1908Zynp_";
>>>>>>> 4aaa2ec6dc044528cbecee2290688d5f2ccb1db7

    public static Connection getConnection() {

        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
