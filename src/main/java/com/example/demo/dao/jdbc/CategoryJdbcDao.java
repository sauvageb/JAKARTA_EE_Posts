package com.example.demo.dao.jdbc;

import com.example.demo.dao.crud.CategoryDao;
import com.example.demo.dao.ConnectionManager;
import com.example.demo.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryJdbcDao implements CategoryDao {
    @Override
    public Category create(Category entity) {
        Connection connection = ConnectionManager.getInstance();
        String query = "INSERT INTO categories (name) VALUES (?)";
        try (PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, entity.getName());
            int result = pst.executeUpdate();
            if (result == 1) {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    return new Category(id, entity.getName());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
        Connection connection = ConnectionManager.getInstance();
        String query = "UPDATE posts SET name = ? WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, entity.getName());
            pst.setLong(2, entity.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Category entity) {
        Connection connection = ConnectionManager.getInstance();
        String query = "DELETE FROM categories WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setLong(1, entity.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
