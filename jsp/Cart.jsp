<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="productModel.Cart,productModel.Product,model.User,java.util.ArrayList" %>
<%
	Cart cart =(Cart)session.getAttribute("cart");
	User loginUser = (User)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(cart != null){ %>
	<%
	int i = 0;
	for(Product p : cart.getCartList()){

	%>
	<div class="cart-main">
    	<div class="cart-item">
        	<div class="title"><%=p.getProductTitle() %></div>

        	<form action="/tunobue/CartRemoveServlet?listNum=<%= i %>" method="post">
            	数量：<input type="number" name="volume" value="<%=cart.getProductCount().get(i) %>" max="<%=cart.getProductCount().get(i) %>" min="0">
            	<input type="submit" value="変更">
        	</form>
    	</div>
	</div>


	<%i++;}%>
<%} %>
<div class="total">
	合計金額:   <%if(cart == null){%>0 <%}else{ %> <%=cart.getTotalPrice() %><%} %>円
</div>

<form action="/stunobue/PurchaseServlet" method="get">
	<input type="submit" value="購入">
</form>

<style>
    .cart-main{
        width: 1000px;
    }
    .cart-item{
        display: flex;
        width: 100%;
        height: 100px;
        border: 2px solid black;
        border-radius: 5px;
        line-height: 100px;
    }
    .title{
        margin-right: 30px;
    }
    .total{
    	font-size: 36px;
    	width: 100%;
    	background-color: #ccc;
    }
</style>

</body>
</html>