package com.example.demo.service;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.CategoryJdbcDao;
import com.example.demo.model.Category;

import java.util.List;

public class CategoryService {

    private CategoryDao categoryDao = new CategoryJdbcDao();


    public List<Category> fetchAllCategories() {
        return categoryDao.findAll();
    }
}
