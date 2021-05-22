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
<title>Admin</title>
</head>

<body>
<%@ include file="/fragments/header.jsp" %>
	<div class="container">
		<h2>Opzioni</h2>
		<p><a href="<%=getServletContext().getContextPath()%>/admin/gestioneCatalogo">Gestione catalogo</a></p>
		<p><a href="<%=getServletContext().getContextPath()%>/admin/ordersad">Visualizza ordini utenti</a></p>
	</div>
<%@ include file="/fragments/footer.html"%>

</html>