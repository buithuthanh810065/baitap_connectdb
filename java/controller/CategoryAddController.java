package controller;

import java.io.*;
import java.nio.file.Paths;
import java.util.stream.Collectors;

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

@WebServlet(urlPatterns = { "/add-category" })
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
    maxFileSize = 1024 * 1024 * 10,       // 10MB
    maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class CategoryAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/add-category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        try {
            Category category = new Category();

            // Đọc input cateid
            String cateidStr = getFormField(req, "cateid");
            int cateid = 0;
            if (cateidStr != null && !cateidStr.isBlank()) {
                try {
                    cateid = Integer.parseInt(cateidStr.trim());
                } catch (NumberFormatException e) {
                    req.setAttribute("error", "Cateid không hợp lệ: " + cateidStr);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/add-category.jsp");
                    dispatcher.forward(req, resp);
                    return;
                }
            }
            category.setCateid(cateid);

            // Đọc input cate_name
            String name = getFormField(req, "cate_name");
            if (name == null || name.trim().isEmpty()) {
                req.setAttribute("error", "Tên danh mục không được để trống");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/add-category.jsp");
                dispatcher.forward(req, resp);
                return;
            }
            category.setCatename(name);

            System.out.println("cateid = " + cateid);
            System.out.println("cate_name = " + name);

            // Đọc file upload icon
            Part part = req.getPart("icon");
            if (part != null && part.getSize() > 0) {
                String originalFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                String ext = "";
                int index = originalFileName.lastIndexOf(".");
                if (index > 0 && index < originalFileName.length() - 1) {
                    ext = originalFileName.substring(index + 1);
                }
                String fileName = System.currentTimeMillis() + (ext.isEmpty() ? "" : "." + ext);
                String uploadPath = req.getServletContext().getRealPath("/uploads/category");

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists() && !uploadDir.mkdirs()) {
                    throw new IOException("Không thể tạo thư mục upload: " + uploadPath);
                }

                part.write(new File(uploadDir, fileName).getAbsolutePath());
                category.setIcon("uploads/category/" + fileName); 
            } else {
                category.setIcon(""); 
            }

            // Lưu vào DB
            cateService.insert(category);
            req.setAttribute("message", "Thêm danh mục thành công!");
            resp.sendRedirect("list-category"); 

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Lỗi khi thêm danh mục: " + e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/add-category.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private String getFormField(HttpServletRequest req, String name) throws IOException, ServletException {
        Part part = req.getPart(name);
        if (part != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"))) {
                return reader.lines().collect(Collectors.joining("\n")).trim();
            }
        }
        return null;
    }
}