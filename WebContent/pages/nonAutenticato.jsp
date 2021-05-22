<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>


<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="<%=getServletContext().getContextPath()%>/images/ComixEden.png">
<link rel="stylesheet" type="text/css"
	href="<%=getServletContext().getContextPath()%>/style/productviewstyle.css">
<title>Errore</title>
</head>

<body>
<%@ include file="../fragments/header.jsp" %>
	<div class="container">
		<h2>Errore</h2>
		<p>Visita il nostro sito tramite i link in alto!</p>
	</div>
</body>

<%@ include file="../fragments/footer.html"%>

</html>