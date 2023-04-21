package com.example.demo.dao;

import com.example.demo.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryJdbcDao implements CategoryDao {
    @Override
    public boolean create(Category entity) {
        return false;
    }

    @Override
    public List<Category> findAll() {
        Connection connection = ConnectionManager.getInstance();
        String query = "SELECT id, name FROM categories";
        List<Category> categoryList = new ArrayList<>();

        try (Statement pst = connection.createStatement()) {
            ResultSet result = pst.executeQuery(query);

            while (result.next()) {
                Category category = new Category(
                        result.getLong("id"),
                        result.getString("name")
                );
                categoryList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public Category findById(Long id) {
        Connection connection = ConnectionManager.getInstance();
        String query = "SELECT id, name FROM categories WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setLong(1, id);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                return new Category(
                        result.getLong("id"),
                        result.getString("name")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Category entity) {
        throw new RuntimeException();
    }

    @Override
    public void delete(Category entity) {
        throw new RuntimeException();
    }
}
