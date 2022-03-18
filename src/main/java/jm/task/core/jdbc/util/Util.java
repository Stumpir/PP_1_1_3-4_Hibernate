package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/dbtest", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static SessionFactory getHibernateConnection() {
        try {
            Configuration configuration = new Configuration();
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/dbtest");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "123456");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            configuration.setProperties(settings);
            configuration.addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
