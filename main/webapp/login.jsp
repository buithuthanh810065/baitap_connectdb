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
    <input type="hidden" name="action" value="login"/>
    <input type="text" name="username" placeholder="Tên đăng nhập"><br>
    <input type="password" name="password" placeholder="Mật khẩu"><br>
    <button type="submit">Đăng nhập</button>
    <p style="color:red">${error}</p>
    <p style="color:green">${msg}</p>
</form>
<a href="register.jsp">Chưa có tài khoản? Đăng ký</a>
<script type="text/javascript">
document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/baitapweb;";
</script>
</body>
</html>