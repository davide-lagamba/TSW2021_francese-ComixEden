<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%
Carrello cart = (Carrello) request.getAttribute("cart");
%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="./images/ComixEden.png">
<link rel="stylesheet" type="text/css"
	href="./style/productviewstyle.css">
<title>Login</title>
</head>

<body>
<%@ include file="../fragments/header.jsp" %>
	<div class="container">
		<h2>Login</h2>
		<form action="login" method="post">
			Please enter your email <input type="text" name="email" required><br><br>
			Please enter your password <input type="password" name="pw" required> <br><br>
			<input type="submit" value="login">
			
		<%if(request.getAttribute("error")!=null){
			
			%>
			<p><%=request.getAttribute("error") %>
		<%}%>
		</form>
		Non hai un account? <a href="Reg">Registrati</a>
	</div>
</body>

<%@ include file="../fragments/footer.html"%>

</html>