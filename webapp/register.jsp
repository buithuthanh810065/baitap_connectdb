<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
<style>
    body {
        background-color: #f8f9fa;
    }
    .register-container {
        max-width: 400px;
        margin: 80px auto;
        padding: 30px;
        background: #fff;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    .register-container h2 {
        text-align: center;
        margin-bottom: 20px;
    }
    .btn-success {
        width: 100%;
    }
</style>
</head>
<body>

<div class="register-container">
    <form action="login" method="post">
        <h2>Tạo Tài Khoản Mới</h2>
        <c:if test="${error != ''}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <c:if test="${msg != ''}">
            <div class="alert alert-success">${msg}</div>
        </c:if>

        <!-- hidden action -->
        <input type="hidden" name="action" value="register">

        <!-- email -->
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text"><i class="fa fa-envelope"></i></span>
            </div>
            <input type="email" name="email" class="form-control" placeholder="Nhập email" required>
        </div>

        <!-- password -->
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text"><i class="fa fa-lock"></i></span>
            </div>
            <input type="password" name="password" class="form-control" placeholder="Mật khẩu" required>
        </div>

        <!-- button -->
        <button type="submit" class="btn btn-success">Đăng ký</button>
    </form>

    <div class="text-center mt-3">
        <a href="login.jsp">Đã có tài khoản? Đăng nhập</a>
    </div>
</div>

</body>
</html>