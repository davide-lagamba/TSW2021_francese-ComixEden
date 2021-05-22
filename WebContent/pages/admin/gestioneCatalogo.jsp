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
	<div class="container">
		<h2>Prodotti</h2>
		<br><br>
		<p><a href="gestioneCatalogo?action=inserisci">Aggiungi prodotto</a></p><br><br>
		<table border="1">
			<tr>
				<th><a href="gestioneCatalogo?sort=id_prodotto">ID</a></th>
				<th><a href="gestioneCatalogo?sort=nome">Nome</a></th>
				<th><a href="gestioneCatalogo?sort=descrizione">Descrizione</a></th>
				<th><a href="gestioneCatalogo?sort=autori">Autori</a></th>
				<th><a href="gestioneCatalogo?sort=disponibilita">Disponibilità</a></th>
				<th><a href="gestioneCatalogo?sort=PrezzoTot">Prezzo totale</a></th>
				<th>Immagine</th>
				<th>Azione</th>
			</tr>
			<%
				if (products != null && products.size() != 0) {
					Iterator<?> it = products.iterator();
					while (it.hasNext()) {
						Prodotto bean = (Prodotto) it.next();
			%>
			<tr>
				<td><%=bean.getId()%></td>
				<td><%=bean.getNome()%></td>
				<td><%=bean.getDescrizione()%></td>
				<td><%=bean.getAutori()%></td>
				<td><%=bean.getDisponibilita()%></td>
				<td><%=bean.getPrezzoTotString()%></td>				
				<td><div><img src="<%=getServletContext().getContextPath()%>/images/<%=(new ImmagineDS()).doRetrieveByKey(bean.getId()).getNome()%>" alt="<%=bean.getNome()%>"></div>
				</td>
				
				<td>
					<a href="gestioneCatalogo?id=<%=bean.getId()%>">Dettagli</a>
					<form action="gestioneCatalogo" method="post">
					<input type="hidden" name="id" value=<%=bean.getId()%>>
					<input type="submit" name="action" value="Rimuovi">
				</form></td>
			</tr>
			<%
				}
				} else {
			%>
			<tr>
				<td colspan="6">No products available</td>
			</tr>
			<%
				}
			%>
		</table>


	</div>


</body>
<%@ include file="/fragments/footer.html"%>
</html>