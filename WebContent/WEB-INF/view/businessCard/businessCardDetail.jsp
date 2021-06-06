<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.blacksw.bcm.vo.BusinessCardVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%
 	BusinessCardVO businessCard = (BusinessCardVO) request.getAttribute("businessCard");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<style>
	body {
		margin : 0;
		padding : 0;
		background-color : #e0e0e0;
	}

	.container-fluid {
		width : 100%;
		padding : 60px;
		margin-top: 150px;
	}
	
	.content-area {
		padding : 45px;
		background-color : white;
	}
	
	.content-area div {
		width : 100%;
		margin-bottom : 30px;
	}
	
	.content-area div:last-child {
		margin-bottom : 0;
	}
	
	.sub-title {
		display: block;
		font-size: 30px;
		font-weight: bold;
	}
	
	.form-control {
		height: 70px;
		font-size : 30px;
	}
	
	.btn {
		width: 100%;
		height: 70px;
		font-size: 30px;
		color : white;
	}
	
	img {
        max-width:100%; max-height:100%;
        width:auto; height:auto;
        margin:auto;
	}
	
	@media screen and (min-width: 990px) {
		.container-fluid {
			width : 50%;
			margin-top: 200px;
		}
	}
	
</style>
</head>
<body>
	<!-- navbar 영역 -->
	<jsp:include page="navbar.jsp"></jsp:include>
	
	<div class="container-fluid">
		<div class="content-area">
			<div>
				<span class="sub-title">이름</span>
				<span class="form-control"><%= businessCard.getName() %></span>
			</div>
			<div>
				<span class="sub-title">회사명</span>
				<span class="form-control"><%= businessCard.getCompanyName() %></span>
			</div>
			<div>
				<span class="sub-title">부서</span>
				<span class="form-control"><%= businessCard.getDepartment() %></span>
			</div>
			<div>
				<span class="sub-title">직책</span>
				<span class="form-control"><%= businessCard.getPosition() %></span>
			</div>
			<div>
				<span class="sub-title">이메일</span>
				<span class="form-control"><%= businessCard.getEmail() %></span>
			</div>
			<div>
				<span class="sub-title">유선전화</span>
				<span class="form-control"><%= businessCard.getTel() %></span>
			</div>
			<div>
				<span class="sub-title">핸드폰</span>
				<span class="form-control"><%= businessCard.getPhone() %></span>
			</div>
			<div>
				<span class="sub-title">주소</span>
				<span class="form-control"><%= businessCard.getAddress() %></span>
			</div>
			<div>
				<span class="sub-title">회사 CI</span>
				<c:if test="${empty businessCard.getCompanyCI()}">
					<img src="/upload/no-image.png"/>
				</c:if>
				<c:if test="${not empty businessCard.getCompanyCI()}">
					<img src="/upload/${businessCard.getCompanyCI()}"/>
				</c:if>
			</div>
			<div>
				<a class="btn btn-secondary" href="/businessCardUpdate?businessCardNo=<%=businessCard.getBusinessCardNo() %>">명함 수정</a>
			</div>
			<div>
				<form action="/businessCardDelete" method="post">
					<input type="hidden" name="businessCardNo" value="<%= businessCard.getBusinessCardNo() %>"/>
					<input type="hidden" name="userId" value="<%= businessCard.getUserId() %>"/>
					<input type="hidden" name="companyCI" value="<%= businessCard.getCompanyCI() %>"/>
					<input class="btn btn-danger" type="submit" value="명함 삭제"/>
				</form>
			</div>
		</div>
	</div>
	
	<!-- bootstrap cdn -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>