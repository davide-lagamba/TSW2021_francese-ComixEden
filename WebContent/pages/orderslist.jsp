<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%
Utente user = (Utente) request.getSession().getAttribute("utente");

if (user == null) {
	response.sendRedirect("../login");
}

Collection<?> ordini = (Collection<?>) request.getAttribute("orders");
if (ordini == null) {
	response.sendRedirect("../orders");
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
	href="./style/productviewstyle.css">
<title>Lista Ordini</title>
</head>

<body>

	<%@ include file="../fragments/header.jsp"%>
	<div class="container">

		<table border="1">
			<caption>Ordini</caption>
			<thead>
				<tr>
					<th scope="col"><a href="orders?sort=id_ordine">ID</a></th>
					<th scope="col"><a href="orders?sort=id_spedizione">Indirizzo spedizione</a></th>
					<th scope="col"><a href="orders?sort=prezzo_totale">Prezzo totale</a></th>
					<th scope="col"><a href="orders?sort=quantita">Quantità</a></th>
					<th scope="col"><a href="orders?sort=data">Data</a></th>
					<th scope="col" class="Note"><a href="orders?sort=note">Note</a></th>
					<th>Azione</th>
				</tr>
			</thead>
			<%
			if (ordini != null && ordini.size() != 0) {
				Iterator<?> it = ordini.iterator();
				while (it.hasNext()) {
					Ordine bean = (Ordine) it.next();
			%>
			<tr>
				<td data-label="ID"><%=bean.getIdOrdine()%></td>
				<td data-label="Indirizzo spedizione"><%=(new IndirizzoDS().doRetrieveByKey(bean.getIdSpedizione())).getString()%></td>
				<td data-label="Prezzo totale"><%=bean.getPrezzoTotaleString()%>&euro;</td>
				<td data-label="Quantità"><%=bean.getQuantita()%></td>
				<td data-label="Data"><%=bean.getData()%></td>
				<td data-label="Note" class="Note"><%=bean.getNote()%></td>
				<td><form action="orders" method="post">
						<a href="orders?id=<%=bean.getIdOrdine()%>">Dettagli</a>
					</form></td>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td colspan="6">No orders available</td>
			</tr>
			<%
			}
			%>
		</table>


	</div>


</body>
<%@ include file="../fragments/footer.html"%>
</html>