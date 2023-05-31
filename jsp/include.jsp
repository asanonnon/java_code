<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User,productModel.Product,java.util.ArrayList,productModel.Serch"%>
<%
	User user = (User)session.getAttribute("loginUser");
	ArrayList<Product> cart = (ArrayList<Product>)session.getAttribute("cart");
	ArrayList<Product> prductList = (ArrayList<Product>)request.getAttribute("productList");
	ArrayList<Product> top = (ArrayList<Product>)request.getAttribute("top");
	ArrayList<Product> ranking = (ArrayList<Product>)request.getAttribute("ranking");
	Product	product = (Product)request.getAttribute("product");
	Serch search = (Serch)request.getAttribute("search");
%>