package com.example.demo.service;

import com.example.demo.api.dto.UserDto;
import com.example.demo.dao.crud.UserDao;
import com.example.demo.dao.jdbc.UserJdbcDao;
import com.example.demo.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private UserDao userDao = new UserJdbcDao();

    public boolean login(String username, String password) {
        User userFound = userDao.findByUsername(username);
        if (userFound != null) {
            return userFound.getPassword().equals(password);
        }
        return false;
    }

    public List<UserDto> findAll() {
        return userDao.findAll().stream()
                .map(user -> user.toDto())
                .collect(Collectors.toList());
    }
}
