<%--@elvariable id="user" type="com.example.library.model.entity.User"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties/messages"/>
<!doctype html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <c:choose>
            <c:when test="${mode == 0}">
                <fmt:message key="admin.user.new"/>
            </c:when>

            <c:when test="${mode == 1}">
                <fmt:message key="admin.user.edit"/>
            </c:when>
        </c:choose>
    </title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/library.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <div class="col-md-3 text-begin">
            <a href="?lang=ua"
               class="btn ${sessionScope.lang == 'en' ? "btn-outline-secondary" : "btn-outline-primary"} btn-sm"><fmt:message
                    key="lang.ua"/></a>
            <a href="?lang=en"
               class="btn ${sessionScope.lang == 'ua' ? "btn-outline-secondary" : "btn-outline-primary"} btn-sm"><fmt:message
                    key="lang.en"/></a>
        </div>

        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="${pageContext.request.contextPath}/admin" class="nav-link px-2 link-dark"><fmt:message
                    key="admin.admin"/></a></li>
            <li><a href="${pageContext.request.contextPath}/admin/authors" class="nav-link px-2 link-dark"><fmt:message
                    key="author.authors"/></a></li>
            <li><a href="${pageContext.request.contextPath}/admin/books"
                   class="nav-link px-2 link-dark"><fmt:message key="book.books"/></a></li>
            <li><a href="${pageContext.request.contextPath}/admin/publications" class="nav-link px-2 link-dark"><fmt:message
                    key="publication.publications"/></a></li>
            <li><a href="${pageContext.request.contextPath}/admin/users" class="nav-link px-2 link-primary"><fmt:message
                    key="user.users"/></a></li>
        </ul>

        <div class="col-md-3 text-end">
            <a href="${pageContext.request.contextPath}/user/logout" class="btn btn-outline-primary"><fmt:message
                    key="menu.logout"/></a>
        </div>
    </header>
</div>

<div class="container col-xxl-5 py-3">
    <div>
        <form action="${pageContext.request.contextPath}/admin/users/save" method="post" class="needs-validation" novalidate="">

            <input value="${user.id}" type="hidden" name="id">

            <div class="row g-3">
                <div class="col-12">
                    <label for="firstName" class="form-label"><fmt:message key="signin.firstname"/></label>
                    <input value="${user.firstName}" type="text" class="form-control" id="firstName" name="firstName" required>
                    <div class="invalid-feedback">
                        <fmt:message key="signin.firstname.feedback"/>
                    </div>
                </div>
            </div>

            <div class="row g-3">
                <div class="col-12">
                    <label for="lastName" class="form-label"><fmt:message key="signin.lastname"/></label>
                    <input value="${user.lastName}" type="text" class="form-control" id="lastName" name="lastName" required>
                    <div class="invalid-feedback">
                        <fmt:message key="signin.lastname.feedback"/>
                    </div>
                </div>
            </div>

            <div class="row g-3">
                <div class="col-12">
                    <label for="email" class="form-label"><fmt:message key="login.email"/></label>
                    <input value="${user.email}" type="text" class="form-control" id="email" name="email" required>
                    <div class="invalid-feedback">
                        <fmt:message key="login.email.feedback"/>
                    </div>
                </div>
            </div>

            <div class="row g-3">
                <div class="col-12">
                    <label for="password" class="form-label"><fmt:message key="login.password"/></label>
                    <div class="input-group has-validation">
                    <input value="${user.password}" type="password" class="form-control" id="password" name="password" required>
                    <div class="invalid-feedback">
                        <fmt:message key="login.password.feedback"/>
                    </div>
                    </div>
                </div>
            </div>

            <div class="col-12">
                <label for="role" class="form-label"><fmt:message key="admin.role"/></label>
                <select class="form-select" id="role" name="role">
                    <option ${user.role.toString() == 'ROLE_ADMIN' ? "selected" : ""} value="ROLE_ADMIN"><fmt:message key="admin.admin"/></option>
                    <option ${user.role.toString() == 'ROLE_LIBRARIAN' ? "selected" : ""} value="ROLE_LIBRARIAN"><fmt:message key="librarian.librarian"/></option>
                    <option ${user.role.toString() == 'ROLE_USER' ? "selected" : ""} value="ROLE_USER"><fmt:message key="admin.user"/></option>
                </select>
            </div>

            <div class="col-12">
                <label for="role" class="form-label"><fmt:message key="admin.status"/></label>
                <select class="form-select" id="status" name="status">
                    <option ${user.status == 0 ? "selected" : ""} value="0"><fmt:message key="admin.block"/></option>
                    <option ${user.status == 1 ? "selected" : ""} value="1"><fmt:message key="admin.unblock"/></option>
                </select>
            </div>

            <button class="w-half btn btn-primary btn-lg mt-5" type="submit"><fmt:message key="admin.save"/></button>
            <a class="w-half btn btn-outline-primary btn-lg mt-5" href="${pageContext.request.contextPath}/admin/users" ><fmt:message key="admin.cancel"/></a>
        </form>
    </div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/taxi.js"></script>

</body>
</html>