package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    private final static UserService USER_SERVICE = new UserServiceImpl();

    public static void main(String[] args) {
        USER_SERVICE.createUsersTable();
        USER_SERVICE.saveUser("Ivan", "Ivanov", (byte) 25);
        USER_SERVICE.saveUser("Petr", "Petrov", (byte) 64);
        USER_SERVICE.saveUser("Sidor", "Sidorov", (byte) 72);
        USER_SERVICE.saveUser("Mihail", "Mihailov", (byte) 47);
        USER_SERVICE.getAllUsers();
        USER_SERVICE.cleanUsersTable();
        USER_SERVICE.dropUsersTable();

    }
}