<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>

<%
	Carrello cart = (Carrello) request.getAttribute("cart");

	String errore = (String) (session.getAttribute("registrazione"));

	String pw = (String) (request.getAttribute("error"));
%>
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="./images/ComixEden.png">
<link rel="stylesheet" type="text/css"
	href="<%=getServletContext().getContextPath() %>/style/productviewstyle.css">
<title>Registrazione</title>
</head>

<body>

	<%@ include file="../fragments/header.jsp"%>
	<div class="container">

		<form action="Reg" method="post">


			<h1>Register</h1>
			<p>Please fill in this form to create an account.</p>
			<hr>

			<label for="nome"><b>Nome: </b></label> <input type="text"
				placeholder="nome" name="nome" id="nome" required> <br><br><label
				for="cognome"><b>Cognome: </b></label> <input type="text"
				placeholder="cognome" name="cognome" id="cognome" required>
			<br><br>
			<label for="email">
			<b>Email: </b>
			<%
				if ((pw != null) && (pw.equals("errorEmail"))) {
			%>
			Email già registrata

			<%
				}
			%></label><input type="text"
				placeholder="email" name="email" id="email" required> 
				
				
				<br> <br><label
				for="password"><b>Password: </b></label>

			<%
				if ((pw != null) && (pw.equals("errorP"))) {
			%>
			le password non corrispondono

			<%
				}
			%>
			
			<input type="password" placeholder="password" name="password"
				id="password" required><br><br>
				<label for="psw-repeat"><b>Repeat
					Password: </b></label><input type="password" placeholder="Repeat Password"
				name="psw-repeat" id="psw-repeat" required> <br><br><label
				for="telefono"><b>Telefono: </b></label> <input type="number"
				placeholder="telefono" name="telefono" id="telefono" required>
<br>

			<button type="submit" class="registerbtn">Register</button>


	
		<%
			if ((errore != null) && (errore.equals("errore"))) {
		%>
		email già in uso

		<%
			}
		%>
		<p>
			Already have an account? <a href="login">Sign in</a>.
		</p>

	</form>
	</div>
	<%@ include file="../fragments/footer.html"%>
	<%
		request.removeAttribute("registrazione");
		request.removeAttribute("error");
	%>
</body>
</html>

