package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import daos.CategoryService;
import daos.impl.CategoryServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Category;

@WebServlet(urlPatterns = { "/edit-category" })
@MultipartConfig(
    fileSizeThreshold = 2 * 1024 * 1024,   // 2 MB
    maxFileSize = 10 * 1024 * 1024,        // 10 MB
    maxRequestSize = 50 * 1024 * 1024      // 50 MB
)
public class CategoryEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null && !id.isEmpty()) {
            try {
                int cateId = Integer.parseInt(id);
                Category category = cateService.get(cateId);
                if (category != null) {
                    req.setAttribute("category", category);
                } else {
                    req.setAttribute("error", "Không tìm thấy danh mục với ID: " + id);
                }
                RequestDispatcher dispatcher = req.getRequestDispatcher("/edit-category.jsp"); 
                dispatcher.forward(req, resp);
            } catch (NumberFormatException e) {
                req.setAttribute("error", "ID không hợp lệ: " + id);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/edit-category.jsp"); 
                dispatcher.forward(req, resp);
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/list-category");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        try {
            String id = req.getParameter("id");
            String cateId = req.getParameter("cateId");
            String name = req.getParameter("name");

            if (id == null || id.isEmpty() || name == null || name.trim().isEmpty()) {
                req.setAttribute("error", "ID hoặc tên danh mục không được để trống");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/edit-category.jsp"); 
                dispatcher.forward(req, resp);
                return;
            }

            Category category = new Category();
            category.setId(Integer.parseInt(id));
            category.setCateid(Integer.parseInt(cateId));
            category.setCatename(name);

            String oldIcon = req.getParameter("oldIcon");
            if (oldIcon != null && !oldIcon.isEmpty()) {
                category.setIcon(oldIcon);
            }

            Part filePart = req.getPart("icon");
            if (filePart != null && filePart.getSize() > 0) {
                String realPath = req.getServletContext().getRealPath("/uploads/category");
                String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
                String fileName = System.currentTimeMillis() + "." + ext;

                if (!Files.exists(Paths.get(realPath))) {
                    Files.createDirectories(Paths.get(realPath));
                }

                filePart.write(realPath + File.separator + fileName);
                category.setIcon("uploads/category/" + fileName);
            }

            cateService.edit(category);
            req.setAttribute("message", "Cập nhật danh mục thành công!");
            resp.sendRedirect(req.getContextPath() + "/list-category");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Lỗi khi cập nhật danh mục: " + e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/edit-category.jsp"); 
            dispatcher.forward(req, resp);
        }
    }
}