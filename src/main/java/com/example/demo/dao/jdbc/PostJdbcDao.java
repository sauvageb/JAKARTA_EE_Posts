package com.example.demo.dao.jdbc;

import com.example.demo.dao.ConnectionManager;
import com.example.demo.dao.crud.PostDao;
import com.example.demo.model.Category;
import com.example.demo.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostJdbcDao implements PostDao {

    @Override
    public List<Post> findAll() {
        Connection connection = ConnectionManager.getInstance();
        String query = "SELECT p.id, p.title, p.author, p.content, p.picture_url, p.created_at, c.id AS 'category_id', c.name AS 'category_name' " +
                "FROM posts p " +
                "INNER JOIN categories c ON p.category_fk = c.id";
        List<Post> postList = new ArrayList<>();

        try (Statement pst = connection.createStatement()) {
            ResultSet result = pst.executeQuery(query);

            while (result.next()) {
                Post p = new Post(
                        result.getLong("id"),
                        result.getString("title"),
                        result.getString("author"),
                        result.getString("content"),
                        result.getString("picture_url"),
                        result.getTimestamp("created_at").toLocalDateTime(),
                        new Category(result.getLong("category_id"), result.getString("category_name"))
                );
                postList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }


    @Override
    public Post create(Post entity) {
        Connection connection = ConnectionManager.getInstance();
        String query = "INSERT INTO posts (title, author, content, picture_url, created_at, category_fk) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, entity.getTitle());
            pst.setString(2, entity.getAuthor());
            pst.setString(3, entity.getContent());
            pst.setString(4, entity.getPictureUrl());
            pst.setTimestamp(5, Timestamp.valueOf(entity.getCreatedAt()));
            pst.setLong(6, entity.getCategory().getId());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    Long id = generatedKeys.getLong(1);
                    entity.setId(id);
                    return entity;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Post findById(Long id) {
        Connection connection = ConnectionManager.getInstance();
        String query = "SELECT p.id, p.title, p.author, p.content, p.picture_url, p.created_at, c.id AS 'category_id', c.name AS 'category_name' " +
                "FROM posts p " +
                "INNER JOIN categories c ON p.category_fk = c.id " +
                "WHERE p.id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setLong(1, id);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                return new Post(
                        result.getLong("id"),
                        result.getString("title"),
                        result.getString("author"),
                        result.getString("content"),
                        result.getString("picture_url"),
                        result.getTimestamp("created_at").toLocalDateTime(),
                        new Category(result.getLong("category_id"), result.getString("category_name"))
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
    public void update(Post entity) {
        Connection connection = ConnectionManager.getInstance();
        String query = "UPDATE posts SET title = ?, author = ?, content = ?, picture_url = ?, created_at = ?, category_fk = ? WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, entity.getTitle());
            pst.setString(2, entity.getAuthor());
            pst.setString(3, entity.getContent());
            pst.setString(4, entity.getPictureUrl());
            pst.setTimestamp(5, Timestamp.valueOf(entity.getCreatedAt()));
            pst.setLong(6, entity.getCategory().getId());
            pst.setLong(7, entity.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Post entity) {
        Connection connection = ConnectionManager.getInstance();
        String query = "DELETE FROM posts WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setLong(1, entity.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
