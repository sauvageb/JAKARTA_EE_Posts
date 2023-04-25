package com.example.demo.dao.jdbc;

import com.example.demo.dao.crud.UserDao;
import com.example.demo.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDao implements UserDao {

    @Override
    public User create(User entity) {
        throw new RuntimeException();
    }

    @Override
    public List<User> findAll() {
        Connection connection = ConnectionPoolManager.getInstance();
        String query = "SELECT id, username, password FROM users";
        List<User> userList = new ArrayList<>();

        try (Statement pst = connection.createStatement()) {
            ResultSet result = pst.executeQuery(query);

            while (result.next()) {
                User user = new User(
                        result.getLong("id"),
                        result.getString("username"),
                        result.getString("password")
                );
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User findById(Long aLong) {
        throw new RuntimeException();
    }

    @Override
    public void update(User entity) {
        throw new RuntimeException();
    }

    @Override
    public void delete(User entity) {
        throw new RuntimeException();
    }

    @Override
    public User findByUsername(String username) {
        Connection connection = ConnectionPoolManager.getInstance();
        String query = "SELECT id, username, password FROM users WHERE username=?";
        User userFound = null;

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, username);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                userFound = new User(
                        result.getLong("id"),
                        result.getString("username"),
                        result.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userFound;
    }
}
