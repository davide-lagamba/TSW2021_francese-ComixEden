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
	<div class="container">
		<h2>Prodotti</h2>
		<a href="product">List</a>
		<table border="1">
			<tr>
				<th><a href="product?sort=id_prodotto">ID</a></th>
				<th><a href="product?sort=nome">Nome</a></th>
				<th><a href="product?sort=descrizione">Descrizione</a></th>
				<th><a href="product?sort=autori">Autori</a></th>
				<th><a href="product?sort=disponibilita">Disponibilità</a></th>
				<th><a href="product?sort=PrezzoTot">Prezzo totale</a></th>
				<th>Quantità</th>
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
				<td><form action="product" method="post">
						<input id="quantita" type="number" name="quantita" min="1"
							max="<%=bean.getDisponibilita()%>"></td>
				<td><div><img src="images/<%=(new ImmagineDS()).doRetrieveByKey(bean.getId()).getNome()%>" alt="<%=bean.getNome()%>"></div>
				</td>
				
				<td><input type="hidden" name="id" value=<%=bean.getId()%>>
					<a href="product?id=<%=bean.getId()%>">Dettagli</a> <input
					type="submit" name="action" value="Aggiungi al carrello"></input></a>
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
<%@ include file="../fragments/footer.html"%>
</html>