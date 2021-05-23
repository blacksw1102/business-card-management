<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.blacksw.bcm.vo.BusinessCardVO" %>
 <%
 	BusinessCardVO businessCard = (BusinessCardVO) request.getAttribute("businessCard");
 	int nowPage = 0;
 %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<span><%= businessCard.getName() %></span><br>
	<span><%= businessCard.getCompanyName() %></span><br>
	<span><%= businessCard.getDepartment() %></span><br>
	<span><%= businessCard.getPosition() %></span><br>
	<span><%= businessCard.getEmail() %></span><br>
	<span><%= businessCard.getTel() %></span><br>
	<span><%= businessCard.getPhone() %></span><br>
	<span><%= businessCard.getAddress() %></span><br>
	<span><img src="/ciUpload/<%= businessCard.getCompanyCI() %>"/></span><br>
</body>
</html>