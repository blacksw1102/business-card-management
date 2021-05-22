<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.blacksw.bcm.vo.UserVO" %>
<%@ page session="true" %>

<%
	UserVO user = ((UserVO) session.getAttribute("loginUser"));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<% if(user != null) { %>
			<%= user.getName() %> 님 환영합니다.
		<% } %>
	</div>
	businessCardList.jsp
</body>
</html>