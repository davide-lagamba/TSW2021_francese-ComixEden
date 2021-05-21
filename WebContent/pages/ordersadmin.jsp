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
		response.sendRedirect("../ordersad");
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
		<h2>Ordini</h2>
		
		<table border="1">
			<tr>
				<th><a href="ordersad?sort=id_ordine">ID</a></th>
				<th><a href="ordersad?sort=id_spedizione">Indirizzo spedizione</a></th>
				<th><a href="ordersad?sort=prezzo_totale">Prezzo totale</a></th>
				<th><a href="ordersad?sort=quantita">Quantità</a></th>
				<th><a href="ordersad?sort=data">Data</a></th>
				<th><a href="ordersad?sort=note">Note</a></th>
				<th>Azione</th>
			</tr>
			<%
				if (ordini != null && ordini.size() != 0) {
					Iterator<?> it = ordini.iterator();
					while (it.hasNext()) {
						Ordine bean = (Ordine) it.next();
			%>
			<tr>
				<td><%=bean.getIdOrdine()%></td>
				<td><%=bean.getIdSpedizione()%></td>
				<td><%=bean.getPrezzoTotaleString()%></td>
				<td><%=bean.getQuantita()%></td>
				<td><%=bean.getData()%></td>
				<td><%=bean.getNote()%></td>
				<td><form action="ordersad" method="get">
					<a href="ordersad?id=<%=bean.getIdOrdine()%>">Dettagli</a>			
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