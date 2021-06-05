<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
</style>
</head>
<body>
	<!-- navbar 영역 -->
	<jsp:include page="navbar.jsp"></jsp:include>
	
	<!-- content 영역 -->
	<div class="container-fluid">
		<div class="content-area">
			<form action="/businessCardWrite" method="post" enctype="multipart/form-data">
				<div>
					<label class="sub-title" for="name">이름</label>
					<input type="text" class="form-control" id="name" name="name" value="홍길동" />
				</div>
				<div>
					<label class="sub-title" for="companyName">회사명</label>
					<input type="text" class="form-control" id="companyName" name="companyName" value="삼성생명 태평로지점" />
				</div>
				<div>
					<label class="sub-title" for="department">부서</label>
					<input type="text" class="form-control" id="department" name="department" value="Financial Consultant" />
				</div>
				<div>
					<label class="sub-title" for="position">직책</label>
					<input type="text" class="form-control" id="position" name="position" value="CA" />
				</div>
				<div>
					<label class="sub-title" for="email">이메일</label>
					<input type="email" class="form-control" id="email" name="email" value="gildong@samsunglife.com" />
				</div>
				<div>
					<label class="sub-title" for="tel">유선전화</label>
					<input type="text" class="form-control" id="tel" name="tel" value="02-1234-5678" />
				</div>
				<div>
					<label class="sub-title" for="phone">핸드폰</label>
					<input type="text" class="form-control" id="phone" name="phone" value="010-1234-5678" />
				</div>
				<div>
					<label class="sub-title" for="address">주소</label>
					<input type="text" class="form-control" id="address" name="address" value="서울시 중구 세종대로 55 삼성생명빌딩 7층 태평로지점" />
				</div>
				<div>
					<label class="sub-title" for="companyCI">회사 CI</label>
					<input type="file" class="form-control" id="companyCI" name="companyCI"/><br>
				</div>
				<div>
					<input type="submit" class="btn btn-danger" value="명함 등록" />
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