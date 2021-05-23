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
	<form action="/businessCardUpdate" method="post" enctype="multipart/form-data">
		<span><input type="hidden" name="userId" value="<%= businessCard.getUserId() %>"/></span><br>
		<span><input type="hidden" name="businessCardNo" value="<%= businessCard.getBusinessCardNo() %>"/></span><br>
		<span><input type="hidden" name="oldCompanyCI" value="<%= businessCard.getCompanyCI() %>"/></span><br>
		이름 : <input type="text" name="name" / value="<%= businessCard.getName() %>"><br>
		회사명 : <input type="text" name="companyName" value="<%= businessCard.getCompanyName() %>" /><br>
		부서 : <input type="text" name="department" value="<%= businessCard.getDepartment() %>"/><br>
		직책 : <input type="text" name="position" value="<%= businessCard.getPosition() %>"/><br>
		이메일 : <input type="text" name="email" value="<%= businessCard.getEmail() %>"/><br>
		유선전화 : <input type="text" name="tel" value="<%= businessCard.getTel() %>"/><br>
		핸드폰 : <input type="text" name="phone" value="<%= businessCard.getPhone() %>"/><br>
		주소 : <input type="text" name="address" value="<%= businessCard.getAddress() %>"/><br>		
		회사 CI : 
		<span><img src="/ciUpload/<%= businessCard.getCompanyCI() %>"/></span><br>
		<span><input type="file" name="companyCI"/></span><br>
		<input type="submit" value="명함 수정"/>
	</form>
</body>
</html>