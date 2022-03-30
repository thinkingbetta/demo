package com.accenture.europeanunion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {
    public Connection getConnection() throws SQLException {

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root"); // Workbench credentials
        connectionProps.put("password", "secret");
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/salsasyntax", connectionProps);
        System.out.println("Connected to database");
        return conn;
    }
}
