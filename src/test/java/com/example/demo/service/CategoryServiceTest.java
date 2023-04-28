package com.example.demo.service;

import com.example.demo.dao.crud.CategoryDao;
import com.example.demo.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class CategoryServiceTest {

    @Test
    public void test_fetchAllCategories_should_return_all_categories() {
        // GIVEN
        List<Category> expectedCategories = new ArrayList<>();
        expectedCategories.add(new Category(1L, "Category 1"));
        expectedCategories.add(new Category(2L, "Category 2"));

        CategoryDao categoryDaoMocked = Mockito.mock(CategoryDao.class);
        Mockito.when(categoryDaoMocked.findAll()).thenReturn(expectedCategories);
        CategoryService categoryService = new CategoryService();
        categoryService.setCategoryDao(categoryDaoMocked);

        // WHEN
        List<Category> categoryResult = categoryService.fetchAllCategories();

        //THEN
        Assertions.assertEquals(expectedCategories, categoryResult);
    }

    @Test
    public void test_fetchAllCategories_should_return_empty_categories_list() {
        // GIVEN
        List<Category> expectedCategories = new ArrayList<>();
        CategoryDao categoryDaoMocked = Mockito.mock(CategoryDao.class);
        Mockito.when(categoryDaoMocked.findAll()).thenReturn(expectedCategories);

        // WHEN
        CategoryService categoryService = new CategoryService();
        categoryService.setCategoryDao(categoryDaoMocked);

        List<Category> categoryResult = categoryService.fetchAllCategories();

        //THEN
        Assertions.assertEquals(expectedCategories, categoryResult);
    }



    @Test
    public void test2() {
        Assertions.assertEquals(false, true);
    }

    @Test
    public void test3() {
        Assertions.assertEquals(true, true);

    }

}
