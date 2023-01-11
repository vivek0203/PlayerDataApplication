package com.example.PlayerDataApplication.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    private static final Logger logger = LoggerFactory.getLogger(DBUtil.class);

    public static void close(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }

        } catch (SQLException e) {
            logger.error("Error occurred while closing db connection artifacts", e);
        }
    }
    public static void close(Statement st, Connection conn) {
        close(null, st, conn);
    }
}
