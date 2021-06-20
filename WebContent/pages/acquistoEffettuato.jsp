<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<link rel="icon" href="<%=getServletContext().getContextPath() %>/images/ComixEden.png">
<link rel="stylesheet" type="text/css"
	href="<%=getServletContext().getContextPath() %>/style/productviewstyle.css">
<title>Riepilogo</title>
</head>
<body>
	<%@ include file="/fragments/header.jsp"%>

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
		<h1>Grazie per aver acquistato sul nostro sito! Provvederemo a spedire i suoi articoli al più presto</h1>
		<h2>Riepilogo del tuo ordine:</h2>
		<table border="1">
		<thead>
			<tr>
				<th scope="col">Costo spedizione</th>
				<th scope="col">Prezzo totale</th>
				<th scope="col">Data acquisto</th>
				<th scope="col">Quantità</th>
			</tr>
			</thead>
			<tr>
			
				<td data-label="Costo spedizione"><%=or.getCostoSpedizione()%>&euro;</td>
				<td data-label="Prezzo totale"><%=or.getPrezzoTotaleString()%>&euro;</td>
				<td data-label="Data acquisto"><%=or.getData()%></td>
				<td data-label="Quantità"><%=or.getQuantita()%></td>
			</tr>			
		</table>
		<h2>...per maggiori dettagli, consulta la tua area utente!</h2>
	</div>
	<%@ include file="/fragments/footer.html"%>
</body>
</html>