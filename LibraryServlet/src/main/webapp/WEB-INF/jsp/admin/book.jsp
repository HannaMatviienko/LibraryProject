<%--@elvariable id="book" type="com.example.library.model.entity.Book"--%>
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
                <fmt:message key="admin.book.new"/>
            </c:when>

            <c:when test="${mode == 1}">
                <fmt:message key="admin.book.edit"/>
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
                   class="nav-link px-2 link-primary"><fmt:message key="book.books"/></a></li>
            <li><a href="${pageContext.request.contextPath}/admin/publications" class="nav-link px-2 link-dark"><fmt:message
                    key="publication.publications"/></a></li>
            <li><a href="${pageContext.request.contextPath}/admin/users" class="nav-link px-2 link-dark"><fmt:message
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
        <form action="${pageContext.request.contextPath}/admin/books/save" method="post" class="needs-validation" novalidate="">

            <input value="${book.id}" type="hidden" name="id">

            <div class="row g-3">
                <div class="col-12">
                    <label for="name" class="form-label"><fmt:message key="book.name"/></label>
                    <input value="${book.name}" type="text" class="form-control" id="name" name="name" required>
                    <div class="invalid-feedback">
                        <fmt:message key="book.name"/>
                    </div>
                </div>
            </div>

            <div class="row g-3">
                <div class="col-12">
                    <label for="yearPublication" class="form-label"><fmt:message key="book.yearPublication"/></label>
                    <input value="${book.yearPublication}" type="text" class="form-control" id="yearPublication" name="yearPublication" required>
                    <div class="invalid-feedback">
                        <fmt:message key="book.yearPublication"/>
                    </div>
                </div>
            </div>

            <div class="row g-3">
                <div class="col-12">
                    <label for="authorId" class="form-label"><fmt:message key="author.name"/></label>
                    <select class="form-select" id="authorId" name="authorId" required="">
                        <option value="0"><fmt:message key="admin.choose"/>...</option>
                        <%--@elvariable id="authors" type="java.util.List<com.example.library.model.entity.Author>"--%>
                        <c:forEach items="${authors}" var="author">
                            <option value="${author.id}" ${book.author.id == author.id ? "selected" : ""}>${author.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="row g-3">
                <div class="col-12">
                    <label for="publicationId" class="form-label"><fmt:message key="publication.name"/></label>
                    <select class="form-select" id="publicationId" name="publicationId" required="">
                        <option value="0"><fmt:message key="admin.choose"/>...</option>
                        <%--@elvariable id="publications" type="java.util.List<com.example.library.model.entity.Publication>"--%>
                        <c:forEach items="${publications}" var="publication">
                            <option value="${publication.id}" ${book.publication.id == publication.id ? "selected" : ""}>${publication.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="row g-3">
                <div class="col-12">
                    <label for="name" class="form-label"><fmt:message key="number.books"/></label>
                    <input value="${book.numberOf}" type="text" class="form-control" id="numberOf" name="numberOf" required>
                    <div class="invalid-feedback">
                        <fmt:message key="number.books"/>
                    </div>
                </div>
            </div>

            <button class="w-half btn btn-primary btn-lg mt-5" type="submit"><fmt:message key="admin.save"/></button>
            <a class="w-half btn btn-outline-primary btn-lg mt-5" href="${pageContext.request.contextPath}/admin/books" ><fmt:message key="admin.cancel"/></a>
        </form>
    </div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/taxi.js"></script>

</body>
</html>
