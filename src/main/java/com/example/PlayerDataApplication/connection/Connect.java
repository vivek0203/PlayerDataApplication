package com.example.PlayerDataApplication.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    static Connection conn;
    private static final Logger logger = LoggerFactory.getLogger(Connect.class);
    public static Connection createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbUrl = "jdbc:mysql://localhost:3306/project";
            String user = "root";
            String pass = "password";

            conn = DriverManager.getConnection(dbUrl,user,pass);

        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Error occurred during the database connection",e);

        }
        return conn;

    }
}
