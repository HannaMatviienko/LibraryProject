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
    <title><fmt:message key="librarian.librarian"/></title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/resources/js/library.js"></script>
</head>
<body>
<div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <div class="col-md-3 text-begin">
            <a href="#"
               onclick="addUrlParameter('lang', 'ua')"
               class="btn ${sessionScope.lang == 'en' ? "btn-outline-secondary" : "btn-outline-primary"} btn-sm"><fmt:message
                    key="lang.ua"/></a>
            <a href="#"
               onclick="addUrlParameter('lang', 'en')"
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
            <a href="${pageContext.request.contextPath}/librarian/users" class="btn btn-outline-primary"><fmt:message
                    key="admin.back"/></a>
            <a href="${pageContext.request.contextPath}/user/logout" class="btn btn-outline-primary"><fmt:message
                    key="menu.logout"/></a>
        </div>
    </header>
</div>

<c:if test="${!fines.isEmpty()}">
<h1 class="text-center"><fmt:message key="user.fine"/></h1>
<div class="container">
    <table class="table mt-4">
        <thead>
        <tr>
            <th><fmt:message key="book.title"/></th>
            <th class="text-center"><fmt:message key="user.wite"/></th>
            <th class="text-center"><fmt:message key="user.days"/></th>
            <th class="text-center"><fmt:message key="date.return"/></th>
            <th class="text-center" style="width: 10%"></th>
        </tr>
        </thead>
        <tbody>
            <%--@elvariable id="fines" type="java.util.List"--%>
        <c:forEach items="${fines}" var="item">
            <tr>
                <td>${item.book.name}</td>
                <td class="text-center">${item.fine}</td>
                <td class="text-center">${item.days}</td>
                <td class="text-center">${item.dateBack}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</c:if>

<h1 class="text-center"><fmt:message key="user.account"/></h1>
<div class="container">
    <p><span><fmt:message key="user.name"/></span><strong>${user.screenName}</strong></p>
    <p><span><fmt:message key="user.email"/></span><strong>${user.email}</strong></p>
    <table class="table mt-4">
        <thead>
        <tr>
            <th><fmt:message key="book.title"/></th>
            <th><fmt:message key="author.name"/></th>
            <th><fmt:message key="publication.name"/></th>
            <th class="text-center"><fmt:message key="year.publication"/></th>
            <th class="text-center"><fmt:message key="date.returned"/></th>
            <th class="text-center" style="width: 10%"></th>
        </tr>
        </thead>
        <tbody>
        <%--@elvariable id="books" type="java.util.List"--%>
        <c:forEach items="${books}" var="item">

        <c:set var="status_back" value=""/>
        <c:set var="status_text" value=""/>
        <c:set var="status_date" value=""/>

        <c:choose>
            <c:when test="${item.status == 0}">
                <c:set var="status_back" value="table-warning"/>
            </c:when>
            <c:when test="${item.status == 1}">
                <c:set var="status_date" value="${item.dateBack}"/>
            </c:when>
            <c:when test="${item.status == 2}">
                <%--                <c:set var="status_back" value="table-secondary"/>--%>
                <c:set var="status_text" value="text-secondary"/>
                <c:set var="status_date" value="${item.dateActual}"/>
            </c:when>
        </c:choose>

        <tr class="${status_back} ${status_text}">
            <td>${item.book.name}</td>
            <td>${item.book.author.name}</td>
            <td>${item.book.publication.name}</td>
            <td class="text-center">${item.book.yearPublication}</td>
            <td class="text-center">${status_date}</td>
            </c:forEach>
        </tr>
        </tbody>
    </table>
</div>