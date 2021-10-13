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
    <title><fmt:message key="book.ordered"/></title>
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
            <li><a href="${pageContext.request.contextPath}/user/account/librarian" class="nav-link px-2 link-dark"><fmt:message
                    key="librarian.librarian"/></a></li>
            <li><a href="${pageContext.request.contextPath}/user/account/librarian/ordered"
                   class="nav-link px-2 link-primary"><fmt:message key="book.ordered"/></a></li>
            <li><a href="${pageContext.request.contextPath}/user/account/librarian/users" class="nav-link px-2 link-dark"><fmt:message
                    key="user.users"/></a></li>
        </ul>

        <div class="col-md-3 text-end">
            <a href="${pageContext.request.contextPath}/user/logout" class="btn btn-outline-primary"><fmt:message
                    key="menu.logout"/></a>
        </div>
    </header>
</div>

<div class="container"><h1 class="text-center"><fmt:message key="book.ordered"/></h1></div>

<div class="container">
    <table class="table mt-4">
        <thead>
        <tr>
            <th class="text-center" style="width: 16.6%"><fmt:message key="book.title"/></th>
            <th class="text-center" style="width: 16.6%"><fmt:message key="author.name"/></th>
            <th class="text-center" style="width: 16.6%"><fmt:message key="publication.name"/></th>
            <th class="text-center" style="width: 16.6%"><fmt:message key="year.publication"/></th>
            <th class="text-center" style="width: 5%"></th>
            <th class="text-center" style="width: 5%"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orderedBooks}" var="item">
        <tr>
            <td class="text-center">${item.book.name}</td>
            <td class="text-center">${item.book.author.name}</td>
            <td class="text-center">${item.book.publication.name}</td>
            <td class="text-center">${item.book.yearPublication}</td>
            <td class="text-center"> <a href="${pageContext.request.contextPath}/admin/users/edit?id=${user.id}"
                                        class="btn btn-sm btn-outline-success me-2"><fmt:message key="librarian.confirm"/></a></td>
            <td class="text-center"> <a href="${pageContext.request.contextPath}/admin/users/edit?id=${user.id}"
                                        class="btn btn-sm btn-outline-danger me-2"><fmt:message key="librarian.cancel"/></a></td>

            </c:forEach>
        </tr>
        </tbody>
    </table>
</div>