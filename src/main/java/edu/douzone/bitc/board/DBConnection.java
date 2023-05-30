package edu.douzone.bitc.board;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class DBConnection {

    private static final String FILE_PATH = "src/main/resources/db.properties";

    public static Connection getConnection() throws IOException {

        Properties properties = new Properties();

        properties.load(new FileReader(FILE_PATH));

        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static void closeConnection(Connection conn, PreparedStatement psmt, ResultSet rs) {
        if (Objects.nonNull(conn)) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (Objects.nonNull(psmt)) {
            try {
                psmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (Objects.nonNull(rs)) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
