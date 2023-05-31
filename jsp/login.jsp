<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Insert title here</title>
</head>
<body>

<form action="/tunobue/Login" method="post">
	name:<input type="text" name="UserName">
	pass:<input type="text" name="UserPass">
	<input type="submit" value="ろぐいん">
</form>
<a href="/tunobue/UserResisterServlet">新規登録</a>

</body>
</html>