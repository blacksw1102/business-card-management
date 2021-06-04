<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/businessCardWrite" method="post" enctype="multipart/form-data">
		이름 : <input type="text" name="name" value="테스트" /><br>
		회사명 : <input type="text" name="companyName" value="테스트" /><br>
		부서 : <input type="text" name="department" value="테스트" /><br>
		직책 : <input type="text" name="position" value="테스트" /><br>
		이메일 : <input type="text" name="email" value="테스트" /><br>
		유선전화 : <input type="text" name="tel" value="테스트" /><br>
		핸드폰 : <input type="text" name="phone" value="테스트" /><br>
		주소 : <input type="text" name="address" value="테스트" /><br>		
		회사 CI : <input type="file" name="companyCI"/><br>
		<input type="submit" value="명함 등록" />
	</form>
	<div>
		<button onclick="history.back()">취소</button>
	</div>
</body>
</html>