<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.blacksw.bcm.vo.BusinessCardVO" %>
 <%
 	BusinessCardVO businessCard = (BusinessCardVO) request.getAttribute("businessCard");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<div>
			<div>
				<span>이름 : </span>
				<span><%= businessCard.getName() %></span>
			</div>
			<div>
				<span>회사명 : </span>
				<span><%= businessCard.getCompanyName() %></span>
			</div>
			<div>
				<span>부서 : </span>
				<span><%= businessCard.getDepartment() %></span>
			</div>
			<div>
				<span>직책 : </span>
				<span><%= businessCard.getPosition() %></span>
			</div>
			<div>
				<span>이메일 : </span>
				<span><%= businessCard.getEmail() %></span>
			</div>
			<div>
				<span>유선전화 : </span>
				<span><%= businessCard.getTel() %></span>
			</div>
			<div>
				<span>핸드폰 : </span>
				<span><%= businessCard.getPhone() %></span>
			</div>
			<div>
				<span>주소 : </span>
				<span><%= businessCard.getAddress() %></span>
			</div>
			<div>
				<span>회사 CI : </span>
				<span><img src="/upload/<%= businessCard.getCompanyCI() %>"/></span>
			</div>
		</div>
		<div>
			<a href="/businessCardUpdate?businessCardNo=<%=businessCard.getBusinessCardNo() %>">명함 수정</a>
			<form action="/businessCardDelete" method="post">
				<input type="hidden" name="businessCardNo" value="<%= businessCard.getBusinessCardNo() %>"/>
				<input type="hidden" name="userId" value="<%= businessCard.getUserId() %>"/>
				<input type="hidden" name="companyCI" value="<%= businessCard.getCompanyCI() %>"/>
				<input type="submit" value="명함 삭제"/>
			</form>
		</div>	
	</div>
</body>
</html>