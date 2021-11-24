package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Util.getConnection();
            preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users" +
                    "(ID BIGINT PRIMARY KEY AUTO_INCREMENT ," +
                    "NAME  VARCHAR(25)," +
                    "LAST_NAME VARCHAR(25)," +
                    "AGE TINYINT)");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Util.getConnection();
            preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS users");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = null;

        try {
            connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (NAME, LAST_NAME, AGE) VALUES(?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем –" + name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Util.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM users WHERE ID = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        Connection connection = null;
        List<User> userList = new ArrayList<>();
        Statement statement = null;
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LAST_NAME"));
                user.setAge((byte) resultSet.getByte("AGE"));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Util.getConnection();
            preparedStatement = connection.prepareStatement("TRUNCATE TABLE users");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
