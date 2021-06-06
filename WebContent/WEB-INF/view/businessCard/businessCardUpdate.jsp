<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.blacksw.bcm.vo.BusinessCardVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	BusinessCardVO businessCard = (BusinessCardVO) request.getAttribute("businessCard");
	int nowPage = 0;
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
	 
	 <!-- content 영역 -->
	<div class="container-fluid">
		<div class="content-area">
			<form action="/businessCardUpdate" method="post" enctype="multipart/form-data">
				<div>
					<span><input type="hidden" name="userId" value="<%= businessCard.getUserId() %>"/></span>
					<span><input type="hidden" name="businessCardNo" value="<%= businessCard.getBusinessCardNo() %>"/></span>
					<span><input type="hidden" name="oldCompanyCI" value="<%= businessCard.getCompanyCI() %>"/></span>
				</div>
				<div>
					<label class="sub-title" for="name">이름</label>
					<input type="text" class="form-control" id="name" name="name" value="${businessCard.getName()}" />
				</div>
				<div>
					<label class="sub-title" for="companyName">회사명</label>
					<input type="text" class="form-control" id="companyName" name="companyName" value="${businessCard.getCompanyName()}" />
				</div>
				<div>
					<label class="sub-title" for="department">부서</label>
					<input type="text" class="form-control" id="department" name="department" value="${businessCard.getDepartment()}" />
				</div>
				<div>
					<label class="sub-title" for="position">직책</label>
					<input type="text" class="form-control" id="position" name="position" value="${businessCard.getPosition()}" />
				</div>
				<div>
					<label class="sub-title" for="email">이메일</label>
					<input type="email" class="form-control" id="email" name="email" value="${businessCard.getEmail()}" />
				</div>
				<div>
					<label class="sub-title" for="tel">유선전화</label>
					<input type="text" class="form-control" id="tel" name="tel" value="${businessCard.getTel()}" />
				</div>
				<div>
					<label class="sub-title" for="phone">핸드폰</label>
					<input type="text" class="form-control" id="phone" name="phone" value="${businessCard.getPhone()}" />
				</div>
				<div>
					<label class="sub-title" for="address">주소</label>
					<input type="text" class="form-control" id="address" name="address" value="${businessCard.getAddress()}" />
				</div>
				<div>
					<label class="sub-title" for="companyCI">회사 CI</label>
					<c:if test="${empty businessCard.getCompanyCI()}">
						<img src="/upload/no-image.png"/>
					</c:if>
					<c:if test="${not empty businessCard.getCompanyCI()}">
						<img src="/upload/${businessCard.getCompanyCI()}"/>
					</c:if>
					<input type="file" class="form-control" id="companyCI" name="companyCI"/><br>
				</div>
				<div>
					<input type="submit" class="btn btn-danger" value="명함 수정" />
				</div>
				<div>
					<button class="btn btn-secondary" onclick="history.back(); return false;">취소</button>
				</div>
			</form>
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


<!-- 

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
	<span><img src="/upload/<%= businessCard.getCompanyCI() %>"/></span><br> 
	<span><input type="file" name="companyCI"/></span><br>
	<input type="submit" value="명함 수정"/>
</form>
<div>
	<button onclick="history.back()">취소</button>
</div>

 -->