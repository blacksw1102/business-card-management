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
		이름 : <input type="text" name="name" /><br>
		회사명 : <input type="text" name="companyName" /><br>
		부서 : <input type="text" name="department" /><br>
		직책 : <input type="text" name="position" /><br>
		이메일 : <input type="text" name="email" /><br>
		유선전화 : <input type="text" name="tel" /><br>
		핸드폰 : <input type="text" name="phone" /><br>
		주소 : <input type="text" name="address" /><br>		
		회사 CI : <input type="file" name="companyCI"/><br>
		<input type="submit" value="명함 등록" />
	</form>
</body>
</html>