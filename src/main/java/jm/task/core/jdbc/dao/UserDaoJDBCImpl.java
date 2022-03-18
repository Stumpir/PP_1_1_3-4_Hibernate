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
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users (Id INT PRIMARY KEY AUTO_INCREMENT, UserName VARCHAR(20), UserSecondName VARCHAR(20), Age INT)");
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
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
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("DROP TABLE IF EXISTS users");
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
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
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (UserName, UserSecondName, Age) Values (?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
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
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE Id = ?");
            statement.setInt(1, (int) id);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();
            List<User> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new User(resultSet.getString(2), resultSet.getString(3), (byte) resultSet.getInt(4)));
            }
            connection.commit();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void cleanUsersTable() {
        Connection connection = null;
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE Id > 0");
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
