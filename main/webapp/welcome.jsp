<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Xin chào, ${user.username}!</h2>
<form action="auth" method="post">
	<input hidden value="logout" name="action">
	<button>Đăng xuất</button>
</form>
</body>
<script type="text/javascript">
	
</script>
</html>