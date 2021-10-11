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
            <a href="${pageContext.request.contextPath}/admin/users/new"
               class="btn btn-primary me-2"><fmt:message key="book.order"/></a>
            <a href="${pageContext.request.contextPath}/user/logout" class="btn btn-outline-primary"><fmt:message
                    key="menu.logout"/></a>
        </div>
    </header>
</div>
<h1 class="text-center"><fmt:message key="user.account"/></h1>


<h4 class="text-center"><fmt:message key="book.ordered"/></h4>

<div class="container">
    <table class="table mt-4">
        <thead>
        <tr>
            <th class="text-center" style="width: 14.3%"><fmt:message key="book.title"/></th>
            <th class="text-center" style="width: 14.3%"><fmt:message key="author.name"/></th>
            <th class="text-center" style="width: 14.3%"><fmt:message key="publishing.name"/></th>
            <th class="text-center" style="width: 14.3%"><fmt:message key="year.publication"/></th>
            <th class="text-center" style="width: 14.3%"><fmt:message key="number.books.taken"/></th>
            <th class="text-center" style="width: 14.3%"><fmt:message key="date.return"/></th>
            <th class="text-center" style="width: 14.3%"></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="text-center">Fantastic Beasts and Where to Find Them</td>
            <td class="text-center">J. K. Rowling</td>
            <td class="text-center">Bloomsbury</td>
            <td class="text-center">2001</td>
            <td class="text-center">1</td>
            <td class="text-center">01.11.2021</td>
            <td class="text-center">
                <a href="${pageContext.request.contextPath}/admin/users/edit?id=${user.id}"
                   class="btn btn-sm btn-outline-danger me-2"><fmt:message key="book.remove"/></a> </td>
        </tr>

        <tr>
            <td class="text-center">Harry Potter and the Philosopher's Stone</td>
            <td class="text-center">J. K. Rowling</td>
            <td class="text-center">Bloomsbury</td>
            <td class="text-center">1997</td>
            <td class="text-center">1</td>
            <td class="text-center">16.10.2021</td>
            <td class="text-center">
                <a href="${pageContext.request.contextPath}/admin/users/edit?id=${user.id}"
                   class="btn btn-sm btn-outline-success me-2"><fmt:message key="book.return"/></a> </td>
        </tr>
        <tr>
            <td class="text-center">The Tales of Beedle the Bard</td>
            <td class="text-center">J. K. Rowling</td>
            <td class="text-center">Bloomsbury</td>
            <td class="text-center">2008</td>
            <td class="text-center">1</td>
            <td class="text-center">28.09.2021</td>
            <td class="text-center">
                <a href="${pageContext.request.contextPath}/admin/users/edit?id=${user.id}"
                   class="btn btn-sm btn-outline-secondary me-2"><fmt:message key="book.reorder"/></a> </td>
        </tr>
        </tbody>
    </table>
</div>

<div class="container">
    <table class="table mt-4">
        <thead>
        <tr>
            <th class="text-center" style="width: 14.3%"><fmt:message key="user.user"/></th>
            <th class="text-center" style="width: 14.3%"><fmt:message key="book.title"/></th>
            <th class="text-center" style="width: 14.3%"><fmt:message key="status"/></th>
            <th class="text-center" style="width: 14.3%"><fmt:message key="date.take"/></th>
            <th class="text-center" style="width: 14.3%"><fmt:message key="date.return"/></th>
            <th class="text-center" style="width: 14.3%"><fmt:message key="date.returned"/></th>
            <th class="text-center" style="width: 14.3%"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${books}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.book.name}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>


</html>
