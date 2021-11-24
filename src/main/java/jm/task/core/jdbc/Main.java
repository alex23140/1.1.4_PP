package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Sasha", "Ivanov", (byte) 20);
        userService.saveUser("Alena", "Makarova", (byte) 23);
        userService.saveUser("Alexey", "Petrov", (byte) 40);
        userService.saveUser("Victor", "Plaginov", (byte) 18);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
