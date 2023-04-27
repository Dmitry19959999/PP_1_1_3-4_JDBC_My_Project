package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private static final String INSERT_USER = "INSERT INTO Users_Table " +
            "(name, last_name, age) VALUES (?, ?, ?)";
    private static final String DELETE_USER = "DELETE FROM Users_Table WHERE id = ?";
    private static final String GET_All_USERS = "SELECT * FROM Users_Table";
    private static final String CLEAR_TABLE = "TRUNCATE TABLE Users_Table";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Users_Table " +
            "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), last_name VARCHAR(255), age TINYINT)";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS Users_Table";

    private static final   Connection CONNECTION = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate(DROP_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement prepSt = CONNECTION.prepareStatement(INSERT_USER)) {
            prepSt.setString(1, name);
            prepSt.setString(2, lastName);
            prepSt.setByte(3, age);
            prepSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement prepSt = CONNECTION.prepareStatement(DELETE_USER)) {
            prepSt.setLong(1, id);
            prepSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (ResultSet resultSet = CONNECTION.createStatement().executeQuery(GET_All_USERS)) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("last_name"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate(CLEAR_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
