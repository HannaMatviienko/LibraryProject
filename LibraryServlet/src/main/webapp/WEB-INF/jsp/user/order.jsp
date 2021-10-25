<%--@elvariable id="scol" type="java.lang.String"--%>
<%--@elvariable id="sdir" type="java.lang.String"--%>
<%--@elvariable id="pageCount" type="java.lang.Integer"--%>
<%--@elvariable id="page" type="java.lang.Integer"--%>
<%--@elvariable id="error" type="java.lang.Boolean"--%>

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
    <link href="${pageContext.request.contextPath}/resources/css/pagination.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<page id='page' page='${page}' scol='${scol}' sdir='${sdir}'/>

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
            <a href="${pageContext.request.contextPath}/user"
               class="btn btn-primary me-2"><fmt:message key="admin.back"/></a>
            <a href="${pageContext.request.contextPath}/user/logout" class="btn btn-outline-primary"><fmt:message
                    key="menu.logout"/></a>
        </div>
    </header>
</div>

<div class="container">

    <h1 class="text-center"><fmt:message key="book.available.order"/></h1>

    <div class="row mt-4">
        <div class="col-4">
            <%--@elvariable id="name" type="java.lang.String"--%>
            <input value="${name}" class="w-100 qcol" qcol="name" placeholder="<fmt:message key="book.title"/>">
        </div>
        <div class="col-2">
            <%--@elvariable id="author_name" type="java.lang.String"--%>
            <input value="${author_name}" class="w-100 qcol" qcol="author_name"
                   placeholder="<fmt:message key="author.name"/>">
        </div>
        <div class="col-2">
            <%--@elvariable id="publication_name" type="java.lang.String"--%>
            <input value="${publication_name}" class="w-100 qcol" qcol="publication_name"
                   placeholder="<fmt:message key="publication.name"/>">
        </div>
        <div class="col-2">
            <%--@elvariable id="year_publication" type="java.lang.String"--%>
            <input value="${year_publication}" class="w-100 qcol" qcol="year_publication"
                   placeholder="<fmt:message key="year.publication"/>">
        </div>
        <div class="col-2 text-end buttons">
            <button class="btn btn-sm btn-outline-secondary clear me-2"><fmt:message key="admin.clear"/></button>
            <button class="btn btn-sm btn-outline-primary find"><fmt:message key="admin.find"/></button>
        </div>
    </div>

    <c:if test="${param.containsKey('error')}">
        <div class="alert alert-danger alert-dismissible fade show my-3">
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            <fmt:message key="error.books"/>
        </div>
    </c:if>

    <table class="table">
        <thead>
        <tr>
            <th class="sort" scol="name" sdir="${scol=='name' ? sdir : 'asc'}">
                <fmt:message key="book.title"/><span
                    class="fa fa-fw ${scol=='name' ? (sdir == 'asc' ? "fa-sort-up" : "fa-sort-down") : "fa-sort sort_gray"}"></span>
            </th>

            <th class="sort" scol="author_name" sdir="${scol=='author_name' ? sdir : 'asc'}" style="width: 15%">
                <fmt:message key="author.name"/><span
                    class="fa fa-fw ${scol=='author_name' ? (sdir == 'asc' ? "fa-sort-up" : "fa-sort-down") : "fa-sort sort_gray"}"></span>
            </th>

            <th class="sort" scol="publication_name" sdir="${scol=='publication_name' ? sdir : 'asc'}"
                style="width: 12%">
                <fmt:message key="publication.name"/><span
                    class="fa fa-fw ${scol=='publication_name' ? (sdir == 'asc' ? "fa-sort-up" : "fa-sort-down") : "fa-sort sort_gray"}"></span>
            </th>

            <th class="sort text-center" scol="year_publication" sdir="${scol=='year_publication' ? sdir : 'asc'}"
                style="width: 7%">
                <fmt:message key="year.publication"/><span
                    class="fa fa-fw ${scol=='year_publication' ? (sdir == 'asc' ? "fa-sort-up" : "fa-sort-down") : "fa-sort sort_gray"}"></span>
            </th>

            <th class="text-end" style="width: 17%"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.name}</td>
            <td>${book.author.name}</td>
            <td>${book.publication.name}</td>
            <td class="text-center">${book.yearPublication}</td>
            <td class="text-center">
                <a href="${pageContext.request.contextPath}/user/order?id=${book.id}"
                   class="btn btn-sm btn-outline-success me-2"><fmt:message key="book.order"/></a>
            </td>
            </c:forEach>
        </tr>
        </tbody>
    </table>

    <c:if test="${pageCount > 1}">
        <nav>
            <ul class="pagination justify-content-center">
                <li class="page-item ${page == 1 ? 'disabled' : ''}"><a class="page-link" href='#'
                                                                        page="${page-1}">Previous</a></li>
                <c:forEach var="i" begin="1" end="${pageCount}">
                    <li class="page-item ${i == page ? 'active' : ''}"><a class="page-link" page="${i}"
                                                                          href="#">${i}</a>
                    </li>
                </c:forEach>
                <li class="page-item ${page == pageCount ? 'disabled' : ''}"><a class="page-link" href='#'
                                                                                page="${page+1}">Next</a>
                </li>
            </ul>
        </nav>
    </c:if>
</div>

</body>
</html>