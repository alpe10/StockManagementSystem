package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/stock_managmentv2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1908Zynp_";

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
