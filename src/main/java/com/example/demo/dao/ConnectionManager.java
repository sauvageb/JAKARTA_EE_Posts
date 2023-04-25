package com.example.demo.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionManager {

    private static Connection INSTANCE;

    private ConnectionManager() {
    }

    public static Connection getInstance() {
        if (INSTANCE == null) {
            try {
                String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
                String appConfigPath = rootPath + "config.properties";
                Properties props = new Properties();
                props.load(new FileInputStream(appConfigPath));

                Class.forName("com.mysql.cj.jdbc.Driver");

                INSTANCE = DriverManager.getConnection(
                        props.getProperty("DATABASE_URL"),
                        props.getProperty("DATABASE_USER"),
                        props.getProperty("DATABASE_PASSWORD"));

            } catch (SQLException e) {
                System.err.println("Error during getConnection");
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                System.err.println("Error during loading driver");
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                System.err.println("properties file not found");
                throw new RuntimeException(e);
            } catch (IOException e) {
                System.err.println("I/O operations exceptions");
                throw new RuntimeException(e);
            }
        }
        return INSTANCE;
    }
}
