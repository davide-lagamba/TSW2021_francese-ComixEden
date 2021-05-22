<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%
	Carrello cart = (Carrello) request.getSession().getAttribute("cart");
%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="./images/ComixEden.png">
<link rel="stylesheet" type="text/css"
	href="./style/productviewstyle.css">
<title>Carrello</title>
</head>

<body>
	<%@ include file="../fragments/header.jsp"%>
	<div class="container">
		<table border="1">
			<caption>Carrello</caption>
			<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Nome</th>
				<th scope="col">Descrizione</th>
				<th scope="col">Prezzo totale</th>
				<th scope="col">Quantità</th>
				<th scope="col">Immagine</th>
				<th scope="col">Azione</th>
			</tr>
			</thead>

			<%
				if (cart != null && cart.getQuantity() != 0) {
					ArrayList<DettaglioOrdine> prodotti = cart.getItemsOrdered();
					DettaglioOrdine det;
					for (int i = 0; i < prodotti.size(); i++) {
						det = (DettaglioOrdine) prodotti.get(i);
			%>

			<tr>
				<td data-label="ID"><%=det.getItemId()%></td>
				<td data-label="Nome"><%=det.getNome()%></td>
				<td data-label="Descrizione"><%=det.getDescrizione()%></td>
				<td data-label="Prezzo totale"><%=det.getPrezzoTotString()%></td>
				<td data-label="Quantità">
					<form action="cartView">
						<input id="quantita" type="number" name="quantita"
							value="<%=det.getNumItems()%>" min="1"
							max="<%=det.getItem().getDisponibilita()%>"> <input
							type="hidden" name="id" value=<%=det.getItemId()%>> <input
							type="submit" name="action" value="Modifica"><br>
				</td>
				<td><div>
						<img
							src="images/<%=(new ImmagineDS()).doRetrieveByKey(det.getItemId()).getNome()%>">
					</div></td>
				<td><input type="submit" name="action" value="Dettagli"><br>
					<input type="submit" name="action" value="Rimuovi">
				</form></td>
			</tr>

			<%
				}
			%>
		</table>
		<%
			int items = cart.getQuantity();
				if (items == 1) {
		%>

		<h3>
			Subtotale carrello (<%=items%>
			articolo):
			<%=cart.getPrezzoTotString()%>
		</h3>
		<%
			} else {
		%>
		<h3>
			Subtotale carrello (<%=items%>
			articoli):
			<%=cart.getPrezzoTotString()%>
		</h3>
		<%
			}
		%>

		<form action="cartView">
			<input type="submit" name="action" value="Checkout">
		</form>
		<form action="cartView">
			<input type="submit" name="action" value="Svuota">
		</form>

		<%
			} else {
		%>
		<table>
			<tr>
				<td colspan="6">Carrello vuoto</td>
			</tr>
			<%
				}
			%>
		</table>


	</div>
</body>
<%@ include file="../fragments/footer.html"%>
</html>