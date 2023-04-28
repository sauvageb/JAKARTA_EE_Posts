package com.example.demo.dao.jdbc;

import com.example.demo.dao.ConnectionManager;
import com.example.demo.dao.crud.CategoryDao;
import com.example.demo.model.Category;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryJdbcDaoTest {

    private static Connection conn;
    private static Category category1 = new Category(300L, "Category1");
    private static Category category2 = new Category(400L, "Category2");
    private static Category category3 = new Category(500L, "Category2");

    @BeforeAll
    public static void setUp() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection("jdbc:h2:mem:test", "sa", "");
        // create tables and insert test data
        String createQuery = "CREATE TABLE categories (id INT PRIMARY KEY, name VARCHAR(255))";
        try (Statement st = conn.createStatement()) {
            st.executeUpdate(createQuery);
        }
        String insertQuery = "INSERT INTO categories (id, name) VALUES (?, ?), (?, ?), (?,?)";
        try (PreparedStatement pst = conn.prepareStatement(insertQuery)) {
            pst.setLong(1, category1.getId());
            pst.setString(2, category1.getName());

            pst.setLong(3, category2.getId());
            pst.setString(4, category2.getName());

            pst.setLong(5, category3.getId());
            pst.setString(6, category3.getName());
            pst.executeUpdate();
        }
    }

    @Test
    public void testFindById() {
        Mockito.mockStatic(ConnectionManager.class);
        Mockito.when(ConnectionManager.getInstance()).thenReturn(conn);
        CategoryDao categoryDao = new CategoryJdbcDao();
        Category result = categoryDao.findById(category1.getId());
        assertNotNull(result);
        assertEquals(category1.getName(), result.getName());
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        conn.close();
    }

}
