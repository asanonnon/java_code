<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>アカウント削除しますか？</p>
<form action="/stunobue/UserRemoveServlet" method="post">
	<input type="submit" value="yes" name="yes">
	<input type="submit" value="no" name="no">
</form>

</body>
</html>