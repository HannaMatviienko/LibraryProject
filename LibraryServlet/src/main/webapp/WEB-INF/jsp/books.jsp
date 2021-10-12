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
    <title><fmt:message key="book.books"/></title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
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
            <li><a href="${pageContext.request.contextPath}/user/account/admin" class="nav-link px-2 link-dark"><fmt:message
                    key="admin.admin"/></a></li>
            <li><a href="${pageContext.request.contextPath}/user/account/admin/authors" class="nav-link px-2 link-dark"><fmt:message
                    key="author.authors"/></a></li>
            <li><a href="${pageContext.request.contextPath}/user/account/admin/books"
                   class="nav-link px-2 link-primary"><fmt:message key="book.books"/></a></li>
            <li><a href="${pageContext.request.contextPath}/user/account/admin/publications" class="nav-link px-2 link-dark"><fmt:message
                    key="publication.publications"/></a></li>
            <li><a href="${pageContext.request.contextPath}/user/account/admin/users" class="nav-link px-2 link-dark"><fmt:message
                    key="user.users"/></a></li>
        </ul>

        <div class="col-md-3 text-end">
            <a href="${pageContext.request.contextPath}/user/logout" class="btn btn-outline-primary"><fmt:message
                    key="menu.logout"/></a>
        </div>
    </header>
</div>

<div class="container"><h1 class="text-center"><fmt:message key="book.books"/></h1></div>

<div class="container">
    <table class="table mt-4">
        <thead>
        <tr>
            <th class="text-center" style="width: 25%"><fmt:message key="book.title"/></th>
            <th class="text-center" style="width: 25%"><fmt:message key="author.name"/></th>
            <th class="text-center" style="width: 25%"><fmt:message key="publishing.name"/></th>
            <th class="text-center" style="width: 25%"><fmt:message key="year.publication"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${books}" var="item">
        <tr>
            <td class="text-center">${item.name}</td>
            <td class="text-center">${item.author.name}</td>
            <td class="text-center">${item.publication.name}</td>
            <td class="text-center">${item.yearPublication}</td>
            </c:forEach>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
