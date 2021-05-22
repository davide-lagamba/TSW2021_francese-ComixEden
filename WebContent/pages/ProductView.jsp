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
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="./images/ComixEden.png">
<link rel="stylesheet" type="text/css"
	href="./style/productviewstyle.css">
<title>Catalogo</title>
</head>

<body>

	<%@ include file="../fragments/header.jsp"%>
	<h2>Prodotti</h2>
	<div class="containerProdotti">


		<%
		if (products != null && products.size() != 0) {
			Iterator<?> it = products.iterator();
			while (it.hasNext()) {
				Prodotto bean = (Prodotto) it.next();
		%>
		<div class="card">
			<p><%=bean.getNome()%></p>
			<p><%=bean.getDescrizione()%></p>
			<div>
				<img src="images/<%=(new ImmagineDS()).doRetrieveByKey(bean.getId()).getNome()%>" alt="<%=bean.getNome()%>">
			</div>
			<p>
				Autore:
				<%=bean.getAutori()%></p>
			<p>
			<form action="product" method="post">
				<input type="hidden" name="id" value=<%=bean.getId()%>> <a
					href="product?id=<%=bean.getId()%>">Dettagli</a>
				</p>
				<p>
					Disponibilità:<%=bean.getDisponibilita()%>
				</p>

				<%
				if (bean.getDisponibilita() > 0) {
				%>

				<input type="number" name="quantita" min="1" max="<%=bean.getDisponibilita()%>"> <br> 
				<input type="submit" name="action" value="Aggiungi al carrello"></input>
				<%
				}
				%>

			</form>

			<p class="price">
				<%=bean.getPrezzoTotString()%>
			</p>
		</div>
		<%
		}
		} else {
		%>
		<h2>No products available</h2>
		<%
		}
		%>
	</div>

</body>
<%@ include file="../fragments/footer.html"%>
</html>