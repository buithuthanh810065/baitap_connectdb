<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý danh mục</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body { margin: 20px; }
        h2 { color: red; }
        table img { max-width: 80px; height: auto; }
        .action-links a { margin: 0 5px; text-decoration: none; }
    </style>
</head>
<body>
<div class="container">
    <h2>Quản lý danh mục</h2>
    <p>Nơi bạn có thể quản lý danh mục của mình</p>

    <!-- Thanh search -->
    <div class="row mb-3">
        <div class="col-md-6">
            <label>Danh sách danh mục</label>
        </div>
        <div class="col-md-6 text-end">
            <form action="searchCategory" method="get">
                Search: <input type="text" name="keyword"/>
                <button type="submit" class="btn btn-sm btn-primary">Tìm</button>
            </form>
        </div>
    </div>

    <form action="${pageContext.request.contextPath}/add-category" method="get">
        <button type="submit" class="btn btn-sm btn-primary">Add</button>
    </form>

    <!-- Bảng hiển thị -->
    <table class="table table-bordered table-hover">
        <thead>
            <tr>
                <th>ID</th>
                <th>Hình ảnh</th>
                <th>Tên danh mục</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${empty cateList}">
                <tr><td colspan="4">Không có danh mục nào để hiển thị.</td></tr>
            </c:if>
            <c:forEach var="category" items="${cateList}" varStatus="loop">
                <tr>
                    <td>${category.id}</td>
                    <td><img src="${pageContext.request.contextPath}/${category.icon}" alt="Ảnh" width="80" height="100"/></td>
                    <td>${category.catename}</td>
                    <td class="action-links">
                        <a href="${pageContext.request.contextPath}/edit-category?id=${category.id}">Sửa</a> |
                        <a href="${pageContext.request.contextPath}/delete-category?id=${category.id}" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>