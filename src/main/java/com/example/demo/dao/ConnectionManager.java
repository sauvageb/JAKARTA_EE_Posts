package com.example.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private static Connection INSTANCE;
    private static final String url = "jdbc:mysql://localhost:8889/posts_jakarta_ee";
    private static final String user = "root";
    private static final String password = "root";

    private ConnectionManager() {
    }

    public static Connection getInstance() {
        if (INSTANCE == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                INSTANCE = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.err.println("Error during getConnection");
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                System.err.println("Error during loading driver");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return INSTANCE;
    }
}
