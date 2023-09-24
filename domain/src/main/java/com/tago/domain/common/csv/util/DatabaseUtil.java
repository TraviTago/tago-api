package com.tago.domain.common.csv.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String JDBC_URL = "jdbc:mysql://tago.cjn3xrjw75zo.ap-northeast-2.rds.amazonaws.com:3306/tago?serverTimezone=Asia/Seoul";
    private static final String USER = System.getenv("DATABASE_USERNAME");
    private static final String PASSWORD = System.getenv("DATABASE_PASSWORD");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load JDBC driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
}
