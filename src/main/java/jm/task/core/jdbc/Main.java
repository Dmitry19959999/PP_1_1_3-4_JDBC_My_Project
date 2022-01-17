package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 25);
        userService.saveUser("Petr", "Petrov", (byte) 64);
        userService.saveUser("Sidor", "Sidorov", (byte) 72);
        userService.saveUser("Mihail", "Mihailov", (byte) 47);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}