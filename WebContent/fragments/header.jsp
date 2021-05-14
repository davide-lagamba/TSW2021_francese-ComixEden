<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./style/headerstyle.css">

</head>
<div class="containernav">
	<div class="left">
		<a href="./pages/ProductView.jsp"> <img
			src="./images/ComixEden.png" alt="logo" id="logo"></a>
			<a href="product" class="catalog">Catalogo</a>
			
		
	</div>
	
	<div class="push">
	<%if(request.getSession().getAttribute("utente") ==null){ %>
		<a href="login" class="catalog" >LogIn</a> <%
	}else{ %>
		<a href="orders" class="catalog">Area Utente <%=((Utente)request.getSession().getAttribute("utente")).getNome() %></a>
		<a href="login?action=logout" class="catalog">Logout</a>
		<%} %>
		<a href="cartView"  class="catalog">Carrello (<%=((Carrello) request.getSession().getAttribute("cart")).getQuantity()%>)
		</a>
	</div>
</div>
</html>