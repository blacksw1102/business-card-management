<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.blacksw.bcm.vo.UserVO" %>
<%@ page import="com.blacksw.bcm.vo.BusinessCardVO" %>
<%@ page import="com.blacksw.bcm.vo.PageInfoVO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	UserVO user = ((UserVO) session.getAttribute("loginUser"));
	ArrayList<BusinessCardVO> businessCardList = (ArrayList<BusinessCardVO>) request.getAttribute("businessCardList");
	PageInfoVO pageInfo = (PageInfoVO) request.getAttribute("pageInfo");
	String keyword = (String) request.getAttribute("keyword");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Insert title here</title>
<style>
	body {
		margin : 0;
		padding : 0;
		background-color : #e0e0e0;
	}
	
	.navbar {
		padding : 45px;
		background-color : white;	
	}
	
	.container-fluid {
		width : 100%;
		padding : 60px;
		margin-top: 150px;
	}
	
	.search-area form {
		width: 100%;
		margin: 0px;
	}
	
	.search-area input {
		height : 70px;
		font-size: 30px;
	}
	
	.content-area {
		margin-top : 30px;
		background-color : white;
	}
	
	.page-area {
		padding-top: 30px;
		margin-top : 30px;
		background-color : white;	
	}
	
	.item {
		padding : 45px;
		border-bottom : 1px solid #333;
	}
	
	.item a:link { 
		color: black; text-decoration: none;
	}
	
	.item a:visited { 
		color: black; text-decoration: none;
	}
 	
 	.item a:hover { 
 		color: black; text-decoration: none;
	}
	
	.item-image-area {
		position:relative;
	}
	
	.item-image-area img {
		position:absolute;
        max-width:100%; max-height:100%;
        width:auto; height:auto;
        margin:auto;
        top:0; bottom:0; left:0; right:0;
	}
	
	.item-content-area {
		padding-left : 45px;
	}

	.item-content-area p {
		font-size : 30px;
		font-weight : bold;
	}
</style>
<script>
	// 키워드로 명함 검색
	function search() {
		var elem = document.getElementById("search-input");
		if(elem.value == '') {
			alert('검색어를 입력해주세요');
		} else {
			document.getElementById("searchbar").submit();
		}
	}
</script>
</head>
<body>
	<!-- navbar 영역 -->
	<jsp:include page="navbar.jsp"></jsp:include>

	<div class="container-fluid">
		<!-- search-area 영역 -->
		<div class="search-area">
			<form id="searchbar" action="/businessCardList" class="row" method="get">
				<input type="search" id="search-input" class="col-md-10 form-control form-control-dark" name="keyword" placeholder="Search..." aria-label="Search">
				<input type="button" class="col-md-2 btn btn-secondary" value="검색" onclick="search()">
			</form>
		</div>
	
		<!-- businessCardList 영역 -->
		<div class="content-area">
			<c:choose>
				<c:when test="${businessCardList.size() le 0}">
					검색 결과가 없습니다.
				</c:when>
				<c:otherwise>
					<div class="item-list">
						<c:forEach var="businessCard" items="${businessCardList}">
							<div class="item">
								<a class="row mb-3" href="/businessCardDetail?businessCardNo=${businessCard.getBusinessCardNo()}">
									<div class="col-md-4 item-image-area">
										<c:if test="${empty businessCard.getCompanyCI()}">
											<img " src="/upload/no-image.png"/>
										</c:if>
										<c:if test="${not empty businessCard.getCompanyCI()}">
											<img src="/upload/${businessCard.getCompanyCI()}"/>
										</c:if>
									</div>
									<div class="col-md-8 item-content-area">
										<p>${businessCard.getName()}</p>
										<p>${businessCard.getPosition()}</p>
										<p>${businessCard.getDepartment()}</p>
									</div>
								</a>
							</div>
						</c:forEach>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		
		<!-- 일반 pagination-영역 -->
		<c:if test="${empty keyword}">
		<nav class="page-area">
			<ul class="pagination pagination-lg justify-content-center">
				<c:if test="${pageInfo.getStartPage() gt 1}">
					<li class="page-item">
						<a class="page-link" href="/businessCardList?page=${pageInfo.getStartPage() - pageInfo.getListCount()}">이전</a>
					</li>
				</c:if>
				<c:forEach begin="${pageInfo.getStartPage()}" end="${pageInfo.getEndPage()}" varStatus="status">
					<c:if test="${pageInfo.getPage() eq status.current}">
						<li class="page-item active">
							<a class="page-link" href="/businessCardList?page=${status.current}">${status.current}</a>&nbsp;
						</li>
					</c:if>
					<c:if test="${pageInfo.getPage() ne status.current}">
						<li class="page-item">
							<a class="page-link" href="/businessCardList?page=${status.current}">${status.current}</a>&nbsp;
						</li>
					</c:if>
				</c:forEach>
				<c:if test="${pageInfo.getEndPage() lt pageInfo.getMaxPage()}">
					<li class="page-item">
						<a class="page-link" href="/businessCardList?page=${pageInfo.getEndPage() + 1}">다음</a>
					</li>
				</c:if>
			</ul>
		</nav>
		</c:if>
		
		<!-- 키워드 포함 pagination 영역 -->
		<c:if test="${not empty keyword}">
		<nav class="page-area">
			<ul class="pagination pagination-lg justify-content-center">
				<c:if test="${pageInfo.getStartPage() gt 1}">
					<li class="page-item">
						<a class="page-link" href="/businessCardList?page=${pageInfo.getStartPage() - pageInfo.getListCount()}&keyword=${keyword}">이전</a>
					</li>
				</c:if>
				<c:forEach begin="${pageInfo.getStartPage()}" end="${pageInfo.getEndPage()}" varStatus="status">
					<c:if test="${pageInfo.getPage() eq status.current}">
						<li class="page-item active">
							<a class="page-link" href="/businessCardList?page=${status.current}&keyword=${keyword}">${status.current}</a>&nbsp;
						</li>
					</c:if>
					<c:if test="${pageInfo.getPage() ne status.current}">
						<li class="page-item">
							<a class="page-link" href="/businessCardList?page=${status.current}&keyword=${keyword}">${status.current}</a>&nbsp;
						</li>
					</c:if>
				</c:forEach>
				<c:if test="${pageInfo.getEndPage() lt pageInfo.getMaxPage()}">
					<li class="page-item">
						<a class="page-link" href="/businessCardList?page=${pageInfo.getEndPage() + 1}&keyword=${keyword}">다음</a>
					</li>
				</c:if>
			</ul>
		</nav>
		</c:if>
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