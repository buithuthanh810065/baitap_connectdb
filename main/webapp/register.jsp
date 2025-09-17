<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="auth" method="post">
    <input type="hidden" name="action" value="register"/>
    <input type="email" name="email" placeholder="Nhập email"><br>
    <input type="password" name="password" placeholder="Mật khẩu" required><br>
    <button type="submit">Đăng ký</button>
    <p style="color:green">${msg}</p>
    <p style="color:red">${error}</p>
</form>

<a href="login.jsp">Đã có tài khoản? Đăng nhập</a>

</body>
</html>