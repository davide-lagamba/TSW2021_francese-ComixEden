<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>

<%
Utente user = (Utente) request.getSession().getAttribute("utente");

if (user == null || !user.isAdmin()) {
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/nonAutenticato.jsp");
	dispatcher.forward(request, response);
	return;
}

Carrello cart = (Carrello) request.getAttribute("cart");
%>
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="./images/ComixEden.png">
<link rel="stylesheet" type="text/css"
	href="<%=getServletContext().getContextPath() %>/style/productviewstyle.css">
<title>Ricerca Ordini</title>
</head>

<body>

	<%@ include file="/fragments/header.jsp"%>
	<div class="container">
        <h1>Ricerca ordini</h1>
        <form action="ordersad" method="get">
        
		    <input type="hidden" name="research" value="tutti">
		    
			<h2>Lista completa</h2>
			<br> <br>
			<button type="submit" class="registerbtn">Cerca</button>
            
        </form>
        
        <br> <br>
        
		<form action="ordersad" method="get">
		
		    <input type="hidden" name="research" value="user">
		    
			<h2>Ricerca in base all'utente</h2>
			
			<label for="email">
			<b>Email: </b>
		    </label>
		    <input type="text" placeholder="email" name="email" id="email" required> 
			<br> <br>
			<button type="submit" class="registerbtn">Cerca</button>
            
        </form>
        <br> <br>
        <form action="ordersad" method="get">
        
		    <input type="hidden" name="research" value="data">
		    
			<h2>Ricerca in base al periodo</h2>
			<label for="inizio">Inizio:</label>
            <input type="date" id="inizio" name="inizio">
            <br>
		    <label for="fine">Fine:</label>
            <input type="date" id="fine" name="fine">
			<br> <br>
			<button type="submit" class="registerbtn">Cerca</button>
            
        </form>
        
	</div>
	<%@ include file="/fragments/footer.html"%>
	<%
		request.removeAttribute("registrazione");
		request.removeAttribute("error");
	%>
</body>
</html>

