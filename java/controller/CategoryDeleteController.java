package controller;

import java.io.IOException;

import daos.CategoryService;
import daos.impl.CategoryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/delete-category" })
public class CategoryDeleteController extends HttpServlet {
	CategoryService cateService = new CategoryServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse
	resp) throws ServletException, IOException {
	String id = req.getParameter("id");
	cateService.delete(Integer.parseInt(id));
	resp.sendRedirect(req.getContextPath() + "/list-category");
	}
}
