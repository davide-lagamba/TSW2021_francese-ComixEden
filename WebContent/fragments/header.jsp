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
			<a href="./pages/login.jsp" class="catalog" >LogIn</a> <!-- da togliere se già loggati-->
			<a href="./pages/registration.jsp" class="catalog">Registrati</a> <!-- da togliere se già loggati -->
	</div>
	
	<div class="push">
		<a href="cartView"  class="catalog">Carrello (<%=((Carrello) request.getSession().getAttribute("cart")).getQuantity()%>)
		</a>
	</div>
</div>
</html>