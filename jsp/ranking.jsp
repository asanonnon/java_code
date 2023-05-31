<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="java.util.ArrayList,model.User,productModel.Product" %>
    <% ArrayList<Product> ranking = (ArrayList<Product>)request.getAttribute("ranking"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	int i = 1;
	for(Product p : ranking){ 
	%>
	 <div class="item">
	        <div class="title">
	        	<a href="/stunobue/ProductDetailServlet?id=<%=p.getProductId() %>">
	            	<h2><!--タイトル--><%=i %>位:<%=p.getProductTitle() %></h2>
	        	</a>
	        </div>   
	 </div>
	<%i++;} %>


<a href="/stunobue/SerchServlet">検索</a>

</body>
</html>