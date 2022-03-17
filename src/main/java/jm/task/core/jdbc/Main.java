package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl test = new UserServiceImpl();
        test.createUsersTable();
        test.saveUser("Petr", "Vasilyev", (byte) 22);
        System.out.println("User с именем – Petr добавлен в базу данных");
        test.saveUser("Igor", "Petrov", (byte) 28);
        System.out.println("User с именем – Igor добавлен в базу данных");
        test.saveUser("Maria", "Alekseeva", (byte) 19);
        System.out.println("User с именем – Maria добавлен в базу данных");
        test.saveUser("Oleg", "Tinkoff", (byte) 40);
        System.out.println("User с именем – Oleg добавлен в базу данных");
        System.out.println(test.getAllUsers());
        test.cleanUsersTable();
        test.dropUsersTable();
    }
}
