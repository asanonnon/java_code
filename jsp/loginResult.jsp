<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page import="model.User" %>
    <%
    	User user = (User)session.getAttribute("loginUser");
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
	<%if(user != null){ %>
	<p><%=user.getUserName() %></p>
	<p><%=user.getUserPass() %></p>
	<p><%=user.getMail() %></p>
	<p><%=user.getAddress() %></p>
	<a href="/tunobue/UserEditServlet">編集画面</a>
	<a href="/tunobue/logoutServlet">ログアウト</a>
	<%}else{ %>
	<p>ログインしてません</p>
	<a href="/tunobue/Login">ログイン</a>
	<%} %>

	<a href="/tunobue/SerchServlet">検索画面</a>





</body>
</html>