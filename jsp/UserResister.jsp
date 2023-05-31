<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="/stunobue/UserResisterServlet" method="post">
	ユーザーネーム：<input type="text" name="userName"><br>
	名前：<input type="text" name="name"><br>
	パスワード：<input type="text" name="pass"><br>
	住所：<input type="text" name="address"><br>
	メールアドレス：<input type="text" name="mail"><br>
	<input type="submit" value="登録">
</form>

</body>
</html>