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
	}

	.title {
		margin-bottom : 90px;
		text-align : center;
		font-size: 50px;
		font-weight: bold;
	}
	
	.login-area {
		padding : 45px;
		background-color : white;
	}
	
	.login-area div {
		width : 100%;
		margin-bottom : 30px;
	}
	
	.login-area div:last-child {
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
	
	hr {
	    border: none;
	    height: 1px;
	    /* Set the hr color */
	    color: #333; /* old IE */
	    background-color: #333; /* Modern Browsers */
	}
	
	@media screen and (min-width: 1240px) {
		.container-fluid {
			width : 50%;
			margin-top: 200px;
		}
	}
	
</style>
</head>
<body>
	<div class="container-fluid">
		<h2 class="title">명함 관리</h2>
		<div class="login-area">
			<p class="sub-title">회원가입</p>
			<hr>
			<form action="/signup" method="post">
				<div>
					<label class="sub-title" for="id">아이디</label>
					<input type="text" class="form-control" id="id" name="id" value="testUser1"/>
				</div>
				<div>
					<label class="sub-title" for="pw">비밀번호</label>
					<input type="password" class="form-control" id="pw" name="pw" value="testUser1"/>
				</div>
				<div>
					<label class="sub-title" for="name">비밀번호</label>
					<input type="text" class="form-control" id="name" name="name" value="홍길동"/>
				</div>
				<div>
					<button type="submit" class="btn btn-danger">회원가입</button>
				</div>
				<div>
					<a class="btn btn-secondary" href="/signin">로그인</a>
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