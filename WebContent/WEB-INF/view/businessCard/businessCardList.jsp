<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.blacksw.bcm.vo.UserVO" %>
<%@ page import="com.blacksw.bcm.vo.BusinessCardVO" %>
<%@ page import="com.blacksw.bcm.vo.PageInfoVO" %>
<%@ page import="java.util.ArrayList" %>

<%@ page session="true" %>

<%
	UserVO user = ((UserVO) session.getAttribute("loginUser"));
	ArrayList<BusinessCardVO> businessCardList = (ArrayList<BusinessCardVO>) request.getAttribute("businessCardList");
	PageInfoVO pageInfo = (PageInfoVO) request.getAttribute("pageInfo");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<a href="/businessCardWrite">명함등록</a>
		<a href="/logout">로그아웃</a>
	</div>
	<div>		
		<% if(businessCardList.size() <= 0) {%>
			검색 결과가 없습니다.
		<% } else {%>
		<%= businessCardList.toString() %>
		<% } %>

		<br><br>		
		<%= pageInfo.toString() %>
	</div>
</body>
</html>