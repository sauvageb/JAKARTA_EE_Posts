package com.example.demo.dao;

import com.example.demo.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserJdbcDao implements UserDao {

    @Override
    public boolean create(User entity) {
        throw new RuntimeException();
    }

    @Override
    public List<User> findAll() {
        throw new RuntimeException();
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
        Connection connection = ConnectionManager.getInstance();
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
