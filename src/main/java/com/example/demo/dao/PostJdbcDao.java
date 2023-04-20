package com.example.demo.dao;

import com.example.demo.model.Category;
import com.example.demo.model.Post;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                        new Category( result.getLong("category_id"),result.getString("category_name"))
                );
                postList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }


    @Override
    public boolean create(Post entity) {
        return false;
    }


    @Override
    public Post findById(Long aLong) {
        return null;
    }

    @Override
    public void update(Post entity) {

    }

    @Override
    public void delete(Post entity) {

    }
}
