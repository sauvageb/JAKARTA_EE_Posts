package com.example.demo.servlet;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = PostDetailsServlet.URL)
public class PostDetailsServlet extends HttpServlet {

    public static final String URL = "/post-details";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Post post = new PostService().fetchPostById(id);

        req.setAttribute("post", post);

        req
                .getRequestDispatcher("/WEB-INF/post-details.jsp")
                .forward(req, resp);
    }

}
