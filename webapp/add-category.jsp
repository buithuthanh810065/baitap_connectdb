<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm danh mục</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2 style="color: red;">Thêm danh mục</h2>
    <% if (request.getAttribute("message") != null) { %>
        <div class="alert alert-success"><%= request.getAttribute("message") %></div>
    <% } %>
    <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
    <% } %>

    <form action="add-category" method="post" enctype="multipart/form-data">
        <div class="form-group mb-3">
            <label>ID danh mục:</label>
            <input class="form-control" placeholder="Nhập ID (số)" type="text" name="cateid" />
        </div>
        <div class="form-group mb-3">
            <label>Tên danh mục:</label>
            <input class="form-control" placeholder="Nhập tên danh mục" type="text" name="cate_name" />
        </div>
        <div class="form-group mb-3">
            <label>Ảnh đại diện:</label>
            <input type="file" class="form-control" name="icon" />
        </div>
        <button type="submit" class="btn btn-primary">Thêm</button>
        <button type="reset" class="btn btn-secondary">Hủy</button>
    </form>
</div>
</body>
</html>