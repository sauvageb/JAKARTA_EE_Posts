package com.example.demo.service;

import com.example.demo.dao.crud.CategoryDao;
import com.example.demo.dao.jdbc.CategoryJdbcDao;
import com.example.demo.dao.crud.PostDao;
import com.example.demo.dao.jdbc.PostJdbcDao;
import com.example.demo.model.Category;
import com.example.demo.model.Post;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

// CRUD
public class PostService {

    private PostDao postDao = new PostJdbcDao();
    private CategoryDao categoryDao = new CategoryJdbcDao();

    private static Faker faker = new Faker(new Locale("fr"));

    private static long idSequence = 0;

    private static List<Post> posts = new ArrayList<>(Arrays.asList(
            new Post(++idSequence, faker.book().title(), faker.book().author(), faker.lorem().characters(20), "https://picsum.photos/200/300?random=1", LocalDateTime.now(), new Category(1L, "category 1")),
            new Post(++idSequence, faker.book().title(), faker.book().author(), faker.lorem().characters(20), "https://picsum.photos/200/300?random=2", LocalDateTime.now(), new Category(2L, "category 2")),
            new Post(++idSequence, faker.book().title(), faker.book().author(), faker.lorem().characters(20), "https://picsum.photos/200/300?random=3", LocalDateTime.now(), new Category(3L, "category 3")),
            new Post(++idSequence, faker.book().title(), faker.book().author(), faker.lorem().characters(20), "https://picsum.photos/200/300?random=4", LocalDateTime.now(), new Category(4L, "category 4"))
    ));

    public List<Post> fetchAllPosts() {
        return postDao.findAll();
    }

    public Post createPost(String title, String author, String content, String pictureUrl, Long categoryId) {
        Category selectedCategory = categoryDao.findById(categoryId);
        Post postToCreate = new Post(title, author, content, pictureUrl, selectedCategory);
        return postDao.create(postToCreate);
    }

    public Post fetchPostById(Long id) {
        return postDao.findById(id);
    }

    public void delete(Long postId) {
        Post postToDelete = postDao.findById(postId);
        postDao.delete(postToDelete);
    }

    public void update(Post post) {
        postDao.update(post);
    }


//    public Post createPost(String title, String author, String content) {
//        Post p = new Post(++idSequence, title, author, content, "https://picsum.photos/200/300?random=" + idSequence);
//        posts.add(p);
//        return p;
//    }


}
