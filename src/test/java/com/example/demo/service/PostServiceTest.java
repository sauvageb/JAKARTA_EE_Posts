package com.example.demo.service;

import com.example.demo.dao.crud.CategoryDao;
import com.example.demo.dao.crud.PostDao;
import com.example.demo.model.Category;
import com.example.demo.model.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDateTime;

class PostServiceTest {

    private PostService postService;
    private CategoryDao categoryDaoMocked = Mockito.mock(CategoryDao.class);
    private PostDao postDaoMocked = Mockito.mock(PostDao.class);

    @BeforeEach
    public void setUp() {
        postService = new PostService();
        postService.setPostDao(postDaoMocked);
        postService.setCategoryDao(categoryDaoMocked);
    }

    @Test
    public void testCreatePost() {
        // Given
        Long categoryId = 1L;
        Category category = new Category(categoryId, "category 1");

        String title = "Test title";
        String author = "Test author";
        String content = "Test content";
        String pictureUrl = "https://picsum.photos/200/300?random=1";
        LocalDateTime now = LocalDateTime.of(2023, 1, 1, 1, 30);
        Post createdPost = new Post(1L, title, author, content, pictureUrl, now, category);

        Mockito.when(categoryDaoMocked.findById(categoryId)).thenReturn(category);
        Mockito.when(postDaoMocked.create(Mockito.any(Post.class))).thenReturn(createdPost);
        ArgumentCaptor<Post> postCaptor = ArgumentCaptor.forClass(Post.class);

        // When
        Post result = postService.createPost(title, author, content, pictureUrl, categoryId);

        // Then
        Mockito.verify(categoryDaoMocked).findById(categoryId);
        Mockito.verify(postDaoMocked).create(postCaptor.capture());

        Post capturedPost = postCaptor.getValue();
        Assertions.assertEquals(createdPost, result);
    }


//    @Test
//    public void test_create_should_return_createdPost() {
//        // GIVEN
//        Category expectedCat = new Category(300L, "Category300");
//        Post expectedPost = new Post("Title1", "Author1", "Content1", "Picture1", expectedCat);
//        expectedPost.setId(400L);
//
//        PostService postService = new PostService();
//
//
//        Mockito.when(categoryDaoMocked.findById(expectedCat.getId())).thenReturn(expectedCat);
//        Mockito.when(postDaoMocked.create(expectedPost)).thenReturn(expectedPost);
//
//        postService.setCategoryDao(categoryDaoMocked);
//        postService.setPostDao(postDaoMocked);
//
//        // WHEN
//        Post createdPost = postService.createPost(
//                expectedPost.getTitle(),
//                expectedPost.getAuthor(),
//                expectedPost.getContent(),
//                expectedPost.getPictureUrl(),
//                expectedPost.getCategory().getId());
//        //THEN
//        Assertions.assertEquals(expectedPost, createdPost);
//    }

}
