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
	이름 : <span><%= businessCard.getName() %></span><br>
	회사명 : <span><%= businessCard.getCompanyName() %></span><br>
	부서 : <span><%= businessCard.getDepartment() %></span><br>
	직책 : <span><%= businessCard.getPosition() %></span><br>
	이메일 : <span><%= businessCard.getEmail() %></span><br>
	유선전화 : <span><%= businessCard.getTel() %></span><br>
	핸드폰 : <span><%= businessCard.getPhone() %></span><br>
	주소 : <span><%= businessCard.getAddress() %></span><br>
	회사 CI : <span><img src="/ciUpload/<%= businessCard.getCompanyCI() %>"/></span><br>
</body>
</html>