<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<link rel="icon" href="./images/ComixEden.png">
<link rel="stylesheet" type="text/css"
	href="./style/productviewstyle.css">
<title>Riepilogo</title>
</head>
<body>
	<%@ include file="../fragments/header.jsp"%>

	<%
	    MetodoPagamento mp = (MetodoPagamento) request.getAttribute("pagamento");
	    Indirizzo in = (Indirizzo) request.getAttribute("indirizzo");
	    Ordine or = (Ordine) request.getAttribute("ordine");
		Utente user = (Utente) request.getSession().getAttribute("utente");
	    	
		if (user == null) {
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login");
			request.getSession().setAttribute("page", "checkout");
			dispatcher.forward(request, response);
		}
	%>
	<div class="container">
		<h1>Grazie per aver acquistato sul nostro sito!provvederemo a spedire i suoi articoli al più presto</h1>
		<h2>Riepilogo del tuo ordine:</h2>
		<table border="1">
			<tr>
				<th>costo spedizione</th>
				<th>prezzo totale</th>
				<th>data acquisto</th>
				<th>quantità</th>
			</tr>
			<tr>
				<td><%=or.getCostoSpedizione()%></td>
				<td><%=or.getPrezzoTotale()%></td>
				<td><%=or.getData()%></td>
				<td><%=or.getQuantita()%></td>
			</tr>			
		</table>
		<h2>per maggiori dettagli consulta la tua area utente</h2>
	</div>
	<%@ include file="../fragments/footer.html"%>
</body>
</html>