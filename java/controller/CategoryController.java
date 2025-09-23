package controller;

import java.io.IOException;
import java.util.List;

import daos.CategoryService;
import daos.impl.CategoryServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;

@WebServlet(urlPatterns = { "/list-category" })
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        List<Category> cateList = cateService.getAll();
        System.out.println("Số lượng danh mục: " + cateList.size()); // Debug
        req.setAttribute("cateList", cateList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/list-category.jsp");
        dispatcher.forward(req, resp);
    }
}