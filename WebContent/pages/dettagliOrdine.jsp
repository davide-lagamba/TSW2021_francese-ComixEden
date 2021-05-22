<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%
	Utente user = (Utente) request.getSession().getAttribute("utente");
    String research = (String) request.getParameter("research");
if (user == null) {
	response.sendRedirect("../login");
}

	Collection<?> dettagli = (Collection<?>) request.getAttribute("dettagli");
	
	if ((dettagli == null)&&(research!=null)) {
		response.sendRedirect("../ordersad");
		return;
	}
	if (dettagli == null) {
		response.sendRedirect("../orders");
		return;
	}

	int id_ordine= (int) (request.getAttribute("id_ordine"));

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
<title>Dettaglio ordine</title>
</head>

<body>
	<%@ include file="/fragments/header.jsp"%>
	
	<div class="container">
		
		<table border="1">
		<caption>Dettaglio Ordine</caption>
			<tr>
				<th scope="col"><a href="orders?id=<%=id_ordine %>&sort=id_dettaglio">ID</a></th>
				<th scope="col"><a href="orders?id=<%=id_ordine %>&sort=nome">Nome</a></th>
				<th scope="col"><a href="orders?id=<%=id_ordine %>&sort=prezzo_tot">Prezzo totale</a></th>
				<th scope="col"><a href="orders?id=<%=id_ordine %>&sort=prezzo_singolo">Prezzo singolo</a></th>
				<th scope="col"><a href="orders?id=<%=id_ordine %>&sort=iva">Iva</a></th>
				<th scope="col"><a href="orders?id=<%=id_ordine %>&sort=quantita">Quantità</a></th>
				<th>Azione</th>
			</tr>
			<%
				if (dettagli != null && dettagli.size() != 0) {
					Iterator<?> it = dettagli.iterator();
					while (it.hasNext()) {
						DettaglioOrdine bean = (DettaglioOrdine) it.next();
			%>
			<tr>
				<td data-label="ID"><%=bean.getIdDettaglio()%></td>
				<td data-label="Nome"><%=bean.getNomeDettaglio()%></td>
				<td data-label="Prezzo totale"><%=bean.getPrezzoTotaleString()%></td>
				<td data-label="Prezzo singolo"><%=bean.getPrezzoSingolo()%></td>
				<td data-label="Iva"><%=bean.getIva()%></td>
				<td data-label="Quantità"><%=bean.getQuantita()%></td>
				<td><%if(research==null) {%>
				<form action="orders" method="post">
				<%}else{ %>
				<form action="ordersad" method="get">
				<%} %>
					<a href="<%=getServletContext().getContextPath() %>/product?id=<%=bean.getIdProdotto()%>">Dettagli</a>			
				</form></td>
			</tr>
			<%
				}
				} else {
			%>
			<tr>
				<td colspan="6">No details available</td>
			</tr>
			<%
				}
			%>
		</table>


	</div>


</body>
<%@ include file="/fragments/footer.html"%>
</html>