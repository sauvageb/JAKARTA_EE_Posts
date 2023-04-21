package com.example.demo.servlet;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import com.example.demo.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = AddPostServlet.URL)
public class AddPostServlet extends HttpServlet {

    public static final String URL = "/add-post";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Affiche la vue
        List<Category> categories = new CategoryService().fetchAllCategories();
        request.setAttribute("categories", categories);

        request
                .getRequestDispatcher("/WEB-INF/add-post-form.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recupere les données provenant du formulaire
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String content = req.getParameter("content");
        String pictureUrl = req.getParameter("pictureUrl");
        String categoryId = req.getParameter("categoryId");

        // Ajoute l'article via le service
        try {
            new PostService().createPost(title, author, content, pictureUrl, Long.parseLong(categoryId));
            resp.sendRedirect("posts");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + AddPostServlet.URL + "?error=true");
        }
    }
}
