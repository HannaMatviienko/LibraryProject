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
    <title><fmt:message key="user.users"/></title>
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
            <li><a href="${pageContext.request.contextPath}/librarian/ordered"
                   class="nav-link px-2 link-dark"><fmt:message key="book.ordered"/></a></li>
            <li><a href="${pageContext.request.contextPath}/librarian/users" class="nav-link px-2 link-primary"><fmt:message
                    key="user.users"/></a></li>
        </ul>

        <div class="col-md-3 text-end">
            <a href="${pageContext.request.contextPath}/user/logout" class="btn btn-outline-primary"><fmt:message
                    key="menu.logout"/></a>
        </div>
    </header>
</div>

<div class="container"><h1 class="text-center"><fmt:message key="user.users"/></h1></div>

<div class="container">
    <table class="table mt-4">
        <thead>
        <tr>
            <th style="width: 25%"><fmt:message key="signin.firstname"/></th>
            <th style="width: 25%"><fmt:message key="signin.lastname"/></th>
            <th style="width: 25%"><fmt:message key="login.email"/></th>
            <th class="text-center" style="width: 25%"></th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="item">
        <tr>
            <td>${item.firstName}</td>
            <td>${item.lastName}</td>
            <td>${item.email}</td>
            <td class="text-end">
                <a href="${pageContext.request.contextPath}/librarian/accounts?id=${item.id}"
                   class="btn btn-sm btn-outline-success"><fmt:message key="librarian.view"/></a>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>