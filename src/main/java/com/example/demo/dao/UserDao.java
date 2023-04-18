package com.example.demo.dao;

import com.example.demo.dao.base.GenericDao;
import com.example.demo.model.Post;
import com.example.demo.model.User;

public interface UserDao extends GenericDao<User, Long> {

    User findByUsername(String username);


}
