package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.dao.UserJdbcDao;
import com.example.demo.model.User;

public class UserService {

    private UserDao userDao = new UserJdbcDao();

    public boolean login(String username, String password) {
        User userFound = userDao.findByUsername(username);
        if (userFound != null) {
            return userFound.getPassword().equals(password);
        }
        return false;
    }
}
