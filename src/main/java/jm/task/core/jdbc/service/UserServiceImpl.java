
package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.util.List;


public class UserServiceImpl extends Util implements UserService {
    private static final UserDao USER_DAO = new UserDaoJDBCImpl();

    public void createUsersTable() {
        USER_DAO.createUsersTable();
    }

    public void dropUsersTable() {
        USER_DAO.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        USER_DAO.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        USER_DAO.removeUserById(id);
    }

    public List<User> getAllUsers() {
        List<User> users = USER_DAO.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }

    public void cleanUsersTable() {
        USER_DAO.cleanUsersTable();
    }
}