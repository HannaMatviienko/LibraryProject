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
    <title><fmt:message key="user.user"/></title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>

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
        <div class="col-md-3 text-end">
            <a href="${pageContext.request.contextPath}/user/new"
               class="btn btn-primary me-2"><fmt:message key="book.order"/></a>
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

                    <td class="text-center">
                        <a href="${pageContext.request.contextPath}/user/pay?id=${item.id}"
                           class="btn btn-sm btn-outline-danger me-2"><fmt:message key="user.pay"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

<h1 class="text-center"><fmt:message key="user.account"/></h1>
<div class="container">
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
        <tr>
            <td>${item.book.name}</td>
            <td>${item.book.author.name}</td>
            <td>${item.book.publication.name}</td>
            <td class="text-center">${item.book.yearPublication}</td>

            <td class="text-center">
                <c:choose>
                <c:when test="${item.status == 0}"> </td>
            </c:when>

            <c:when test="${item.status == 1}"> ${item.dateBack}
            </c:when>

            <c:when test="${item.status == 2}"> ${item.dateActual}
            </c:when>
            </c:choose>


            <td class="text-center">
                <c:choose>
                <c:when test="${item.status == 0}">
                <a href="${pageContext.request.contextPath}/user/delete?id=${item.id}"
                   class="btn btn-sm btn-outline-danger me-2"><fmt:message key="book.remove"/></a></td>
            </c:when>

            <c:when test="${item.status == 1}">
                <a href="${pageContext.request.contextPath}/user/return?id=${item.id}"
                   class="btn btn-sm btn-outline-success me-2"><fmt:message key="book.return"/></a>
            </c:when>

            <c:when test="${item.status == 2}">
                <a href="${pageContext.request.contextPath}/user/order?id=${item.book.id}"
                   class="btn btn-sm btn-outline-secondary me-2"><fmt:message key="book.reorder"/></a>
            </c:when>
            </c:choose>

            </c:forEach>
        </tr>
        </tbody>
    </table>
</div>
</html>
