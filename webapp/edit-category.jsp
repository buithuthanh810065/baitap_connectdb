<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa danh mục</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<style>
    .img-responsive { max-width: 100%; height: auto; }
    .form-group { margin-bottom: 1rem; }
</style>
</head>
<body>
<div class="container">
    <h2 style="color: red;">Chỉnh sửa danh mục</h2>
    <% if (request.getAttribute("message") != null) { %>
        <div class="alert alert-success">${message}</div>
    <% } %>
    <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger">${error}</div>
    <% } %>

    <c:if test="${category != null}">
        <form role="form" action="${pageContext.request.contextPath}/edit-category" method="post" enctype="multipart/form-data">
            <input type="hidden" name="cateId" value="${category.cateid}" />
            <input type="hidden" name="oldIcon" value="${category.icon}" />
			<input type="hidden" name="id" value="${category.id}" />
            <div class="form-group mb-3">
                <label>Tên danh mục:</label>
                <input type="text" class="form-control" name="name" value="${category.catename}" required />
            </div>

            <div class="form-group mb-3">
                <label>Ảnh hiện tại:</label>
                <c:if test="${not empty category.icon}">
                    <img src="${pageContext.request.contextPath}/${category.icon}" alt="Ảnh hiện tại" width="100px" class="img-thumbnail mb-2">
                </c:if>
                <label>Thay đổi ảnh:</label>
                <input type="file" class="form-control" name="icon" />
            </div>

            <button type="submit" class="btn btn-primary">Lưu</button>
            <button type="reset" class="btn btn-secondary">Hủy</button>
        </form>
    </c:if>
    <c:if test="${category == null}">
        <p class="text-danger">Không tìm thấy danh mục để chỉnh sửa.</p>
    </c:if>
</div>
</body>
</html>