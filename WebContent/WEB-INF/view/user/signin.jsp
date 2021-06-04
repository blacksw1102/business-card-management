<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/signin" method="post">
		<input type="text" name="id" value="admin"/><br>
		<input type="password" name="pw" value="admin"/><br>
		<input type="submit" value="로그인"/>
	</form>
	<div>
		<a href="/signup">회원가입</a>
	</div>
</body>
</html>