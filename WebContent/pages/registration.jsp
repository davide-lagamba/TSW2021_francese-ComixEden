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

<body onload="document.registration.nome.focus()">

	<%@ include file="../fragments/header.jsp"%>
	<div class="container">

		<form id="registration" action="Reg" method="post" name="registration" onSubmit="return formValidation();">


			<h1>Register</h1>
			<p>Please fill in this form to create an account.</p>
			<hr>

			<label for="nome"><b>Nome: </b></label> <input type="text"
				placeholder="Mario" name="nome" id="nome" required> <br><span id="nomeError"></span><br><label
				for="cognome"><b>Cognome: </b></label> <input type="text"
				placeholder="Rossi" name="cognome" id="cognome" required>
			<br><span id="cognomeError"></span><br>
			<label for="email">
			<b>Email: </b>
			<%
				if ((pw != null) && (pw.equals("errorEmail"))) {
			%>
			Email già registrata

			<%
				}
			%></label><input type="text"
				placeholder="mrossi@gmail.com" name="email" id="email" required> 
				
				
				<br> <span id="emailError"></span><br> <label
				for="password"><b>Password: </b></label>

			<%
				if ((pw != null) && (pw.equals("errorP"))) {
			%>
			le password non corrispondono

			<%
				}
			%>
			
			<input type="password" placeholder="4-12 caratteri" name="password"
				id="password" required><br><span id="passError"></span><br>
				<label for="psw-repeat"><b>Repeat
					Password: </b></label><input type="password" placeholder="Ripeti Password"
				name="psw-repeat" id="psw-repeat" required> <br><span id="passrepError"></span><br><label
				for="telefono"><b>Telefono: </b></label> <input type="number"
				placeholder="1234567890" name="telefono" id="telefono" required>
<br><span id="telefError"></span><br>

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
<script src="<%=getServletContext().getContextPath() %>/script/jquery.js"></script>
<script src="<%=getServletContext().getContextPath() %>/script/register-validation.js"></script>
<script src="<%=getServletContext().getContextPath() %>/script/email-checking.js"></script>
</html>

