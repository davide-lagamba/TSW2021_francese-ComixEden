<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if (products == null) {
		response.sendRedirect("../product");
		return;
	}

	Prodotto product = (Prodotto) request.getAttribute("product");

	Carrello cart = (Carrello) request.getAttribute("cart");
	
	Utente user = (Utente) request.getSession().getAttribute("utente");		
	
	if (user == null || !user.isAdmin()) {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/nonAutenticato.jsp");
		dispatcher.forward(request, response);
		return;
	}
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="<%=getServletContext().getContextPath()%>/images/ComixEden.png">
<link rel="stylesheet" type="text/css"
	href="<%=getServletContext().getContextPath()%>/style/productviewstyle.css">
<title>Gestione Catalogo</title>
</head>

<body>

	<%@ include file="/fragments/header.jsp"%>
	<h3><a href="gestioneCatalogo?action=inserisci">Aggiungi prodotto</a></h3><br><br>
	<h2>Prodotti</h2>
	<br><br>
		
	<div class="containerProdotti">
		<%
		if (products != null && products.size() != 0) {
			Iterator<?> it = products.iterator();
			while (it.hasNext()) {
				Prodotto bean = (Prodotto) it.next();
			%>
			<div class="card">  
				<p>ID: <%=bean.getId()%></p>
				<p><%=bean.getNome()%></p>
				<p><%=bean.getDescrizione()%></p>
				<div>
					<img src="<%=getServletContext().getContextPath()%>/images/<%=(new ImmagineDS()).doRetrieveByKey(bean.getId()).getNome()%>" alt="<%=bean.getNome()%>">
				</div>
				<p>Autore: <%=bean.getAutori()%></p>
				<p><a href="gestioneCatalogo?id=<%=bean.getId()%>">Dettagli</a></p>
				<p>Disponibilità:<%=bean.getDisponibilita()%></p>
				<form action="gestioneCatalogo" method="post">
					<input type="hidden" name="id" value=<%=bean.getId()%>>
					<input type="submit" name="action" value="Rimuovi">
				</form>
				<p class="price"><%=bean.getPrezzoTotString()%></p>
				</div>
			<%}
		} else {%>
			<h2>No products available</h2>
		<%}%>
	</div>

</body>
<%@ include file="/fragments/footer.html"%>
</html>