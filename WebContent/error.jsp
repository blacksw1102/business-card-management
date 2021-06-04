<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ERROR PAGE</title>
</head>
<body>
    <c:if test="${requestScope['javax.servlet.error.status_code'] == 400}">
        <p>400 에러가 발생했습니다.</p>
    </c:if>
    <c:if test="${requestScope['javax.servlet.error.status_code'] == 403}">
        <p>403 에러가 발생했습니다.</p>
    </c:if>
    <c:if test="${requestScope['javax.servlet.error.status_code'] == 404}">
        <p>존재하지 않는 페이지입니다.</p>
    </c:if>
    <c:if test="${requestScope['javax.servlet.error.status_code'] == 405}">
        <p>405 에러가 발생했습니다.</p>
    </c:if>
    <c:if test="${requestScope['javax.servlet.error.status_code'] == 500}">
        <p>500 에러가 발생했습니다.</p>
    </c:if>
    <c:if test="${requestScope['javax.servlet.error.status_code'] == 503}">
        <p>503 에러가 발생했습니다.</p>
    </c:if>
    <button onclick="history.back()">되돌아가기</button>
</body>
</html>