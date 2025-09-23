<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
<style>
	
    body {
        background-color: #f8f9fa;
    }
    .login-container {
        max-width: 400px;
        margin: 80px auto;
        padding: 30px;
        background: #fff;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    .login-container h2 {
        text-align: center;
        margin-bottom: 20px;
    }
    .btn-primary {
        width: 100%;
    }
    .form-check {
        display: flex;
        justify-content: space-between;
    }
</style>
</head>
<body>

<div class="login-container">
    <form action="login" method="post">
        <h2>Đăng Nhập Vào Hệ Thống</h2>
        <c:if test="${error != null}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <input type="hidden" name="action" value="login">

        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text"><i class="fa fa-user"></i></span>
            </div>
            <input type="text" name="email" class="form-control" placeholder="Nhập email" required>
        </div>

        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text"><i class="fa fa-lock"></i></span>
            </div>
            <input type="password" name="password" class="form-control" placeholder="Mật khẩu" required>
        </div>

        <button type="submit" class="btn btn-primary">Đăng nhập</button>
    </form>

    <div class="text-center mt-3">
        <a href="register.jsp">Chưa có tài khoản? Đăng ký</a>
    </div>
</div>

</body>
</html>