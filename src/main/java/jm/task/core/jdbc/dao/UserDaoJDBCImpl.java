package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    String insertUser = "INSERT INTO Users_Table " +
            "(name, last_name, age) VALUES (?, ?, ?)";
    String deleteUser = "DELETE FROM Users_Table WHERE id = ?";
    String getAllUsers = "SELECT * FROM Users_Table";
    String clearTable = "TRUNCATE TABLE Users_Table";
    String createTable = "CREATE TABLE IF NOT EXISTS Users_Table " +
            "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), last_name VARCHAR(255), age TINYINT)";
    String dropTable = "DROP TABLE IF EXISTS Users_Table";

    private final Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(dropTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement prepSt = connection.prepareStatement(insertUser)) {
            prepSt.setString(1, name);
            prepSt.setString(2, lastName);
            prepSt.setByte(3, age);
            prepSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement prepSt = connection.prepareStatement(deleteUser)) {
            prepSt.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (ResultSet resultSet = connection.createStatement().executeQuery(getAllUsers)) {
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
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
