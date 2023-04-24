package com.example.demo.api;

import com.example.demo.api.dto.CreatePost;
import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/posts")
public class PostRessource {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        PostService postService = new PostService();
        Post post = postService.fetchPostById(id);

        if (post == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(post).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        PostService postService = new PostService();
        List<Post> postList = postService.fetchAllPosts();

        return Response
                .ok(postList)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(CreatePost createPost) {
        PostService postService = new PostService();
        Post createdPost = postService.createPost(
                createPost.getTitle(),
                createPost.getAuthor(),
                createPost.getContent(),
                createPost.getPictureUrl(),
                createPost.getCategoryId());

        return Response
                .status(Response.Status.CREATED)
                .entity(createdPost)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Post post) {
        PostService postService = new PostService();
        Post existingPost = postService.fetchPostById(id);

        if (existingPost == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        post.setId(existingPost.getId());
        postService.update(post);

        return Response.ok(post).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        PostService postService = new PostService();
        Post post = postService.fetchPostById(id);

        if (post == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        postService.delete(id);

        return Response.noContent().build();
    }


}
