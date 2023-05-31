<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>ほんとに購入するんか？</div>
<form action="/stunobue/PurchaseServlet" method="post">
	<input type="submit" value="yes">
</form>
<form action="/stunbue/CartServlet">
	<input type="submit" value="no">
</form>


</body>
</html>