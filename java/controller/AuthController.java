package controller;

import model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import daos.impl.DAOUser;
import jakarta.servlet.annotation.WebServlet;
@WebServlet("/login") 
public class AuthController extends HttpServlet {

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
	        String action = request.getParameter("action"); 
	        
	        if ("login".equals(action)) {
	            handleLogin(request, response);
	        } else if ("register".equals(action)) {
	            handleRegister(request, response);
	        } else if("logout".equals(action)) {
	            handleLogout(request, response);
	        }else {
	            response.sendRedirect("login.jsp");
	        }
	    }
	
	    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");

	        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
	            request.setAttribute("error", "Email hoặc mật khẩu không được để trống!");
	            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	            rd.forward(request, response);
	            return;
	        }

	        DAOUser dao = new DAOUser();
	        User user = dao.login(email, password);

	        if (user != null) {
	            HttpSession session = request.getSession();
	            session.setAttribute("user", user);
	            session.setMaxInactiveInterval(30 * 60); 

	            Cookie emailCookie = new Cookie("email", user.getEmail());
	            emailCookie.setMaxAge(60 * 60 * 24);
	            emailCookie.setHttpOnly(true);
	            emailCookie.setSecure(true);
	            response.addCookie(emailCookie);

	            request.setAttribute("message", "Đăng nhập thành công!");
	            response.sendRedirect(request.getContextPath() + "/list-category"); 
	        } else {
	            request.setAttribute("error", "Sai tài khoản hoặc mật khẩu!");
	            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	            rd.forward(request, response);
	        }
	    }
	
	    private void handleRegister(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        DAOUser dao = new DAOUser();

	        boolean success = dao.register(email, password);

	        if (success) {
	            request.setAttribute("msg", "Đăng ký thành công! Mời bạn đăng nhập.");
	            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	            rd.forward(request, response);
	        } else {
	            request.setAttribute("error", "Đăng ký thất bại. Email đã tồn tại hoặc lỗi khác.");
	            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
	            rd.forward(request, response);
	        }
	    }
	    private void handleLogout(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	    	Cookie[] cookies = request.getCookies();
	    	if (cookies != null) {
	    	    for (Cookie c : cookies) {
	    	        c.setMaxAge(0);
	    	        c.setPath("/baitapweb"); 
	    	        response.addCookie(c);
	    	    }
	    	}
	    	request.getSession().invalidate();
	    	response.sendRedirect("login.jsp");
	    }
}