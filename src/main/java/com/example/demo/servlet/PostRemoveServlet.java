package com.example.demo.servlet;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/post-remove")
public class PostRemoveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Post post = new PostService().fetchPostById(id);
        req.setAttribute("post", post);
        req
                .getRequestDispatcher("/WEB-INF/post-remove.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String choice = req.getParameter("choice");
        String postId = req.getParameter("id");
        switch (choice) {
            case "yes":
                new PostService().delete(Long.valueOf(postId));
                break;
            case "no":
                break;
        }
        resp.sendRedirect(req.getContextPath() + PostListServlet.URL);
    }
}
