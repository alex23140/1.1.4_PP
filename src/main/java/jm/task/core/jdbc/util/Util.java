package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "Soulja4477qwe";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/javastudy";

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
        return connection;
    }
}
