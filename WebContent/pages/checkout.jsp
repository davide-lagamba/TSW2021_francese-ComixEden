<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<link rel="icon" href="<%=getServletContext().getContextPath() %>/images/ComixEden.png">
<link rel="stylesheet" type="text/css"
	href="<%=getServletContext().getContextPath() %>/style/productviewstyle.css">
<title>Checkout</title>
</head>
<body>
	<%@ include file="/fragments/header.jsp"%>

	<%
	    MetodoPagamento mp = (MetodoPagamento) request.getAttribute("pagamento");
	    Indirizzo in = (Indirizzo) request.getAttribute("indirizzo");
		Utente user = (Utente) request.getSession().getAttribute("utente");
	    	
		if (user == null) {
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login");
			request.getSession().setAttribute("page", "checkout");
			dispatcher.forward(request, response);
		}
	%>
	<div class="container">
		<form action="checkout" method="post" onSubmit="return formValidation();" name=checkout id=checkout>
			<fieldset>
			    <% if(in.getId()==-1){ %>
				<legend>Informazioni di spedizione</legend>
				Spedizione:<br>
				<br> Stato:<select name="stato_spedizione" required>
					<option value="italia">Italia</option>
					<option value="francia">Francia</option>
					<option value="germania">Germania</option>
				</select> <br>
			
				<br> <label>Città:<input type="text"
					name="citta_spedizione" required placeholder="Napoli"></label>*<br>
				<br> <label>CAP: <input type="number"
					name="cap_spedizione" required placeholder="80100"></label>*<br>
					<span class="cap_spedizioneError"></span>
				<br> <label>Via:<input type="text"
					name="via_spedizione" required placeholder="Via Napoli, 40"></label>*<br>
				<br> Fatturazione:<br>
				<br> Stato:<select name="stato_fatturazione">
					<option value="italia">Italia</option>
					<option value="francia">Francia</option>
					<option value="germania">Germania</option>
				</select> <br>
				<br> <label>Città:<input type="text"
					name="citta_fatturazione" placeholder="Napoli"></label><br>
				<br> <label>CAP:<input type="number"
					name="cap_fatturazione" placeholder="80100"></label><br>
					<span class="cap_fatturazioneError"></span>
				<br> <label>Via:<input type="text"
					name="via_fatturazione" placeholder="Via Napoli, 40"></label><br>
				<br>
			</fieldset>
			 <%} else {%>
			 <legend>Informazioni di spedizione</legend>
				Spedizione:<br>
				<br> Stato:<select name="stato_spedizione" value="<%=in.getStato()%>"required>
					<option value="italia">Italia</option>
					<option value="francia">Francia</option>
					<option value="germania">Germania</option>
				</select> <br>
				<br> <label>Città:<input type="text"
					name="citta_spedizione" required placeholder="Napoli" value="<%=in.getCitta()%>"></label>*<br>
				<br> <label>CAP: <input type="number"
					name="cap_spedizione" required placeholder="80100" value="<%=in.getCap()%>"></label>*<br>
					<span class="cap_spedizioneError"></span>
				<br> <label>Via:<input type="text"
					name="via_spedizione" required placeholder="Via Napoli, 40" value="<%=in.getVia()%>"></label>*<br>
				<br> Fatturazione:<br>
				<br> Stato:<select name="stato_fatturazione" value="<%=in.getStato()%>" required>
					<option value="italia">Italia</option>
					<option value="francia">Francia</option>
					<option value="germania">Germania</option>
				</select> <br>
				<br> <label>Città:<input type="text"
					name="citta_fatturazione" value="<%=in.getCitta()%>"> </label><br>
				<br> <label>CAP:<input type="number"
					name="cap_fatturazione" placeholder="80100" value="<%=in.getCap()%>"></label><br>
					<span class="cap_fatturazioneError"></span>
				<br> <label>Via:<input type="text"
					name="via_fatturazione" placeholder="Via Napoli, 40" value="<%=in.getVia()%>"></label><br>
				<br>
			</fieldset>
			<%}%>	 
			<br>
			<fieldset>
			<% if(mp.getNumero()==null){ %>
				<legend>Informazioni di pagamento</legend>
				Circuito:<select name="circuito" required>
					<option value="visa">Visa</option>
					<option value="mastercard">Mastercard</option>
					<option value="americanexpress">American Express</option>
				</select> <br>
				
				<br> <label>Numero carta:<input type="number" name="numero_carta" placeholder="numero di 16 cifre"
					required></label><br>
					<span class="numero_cartaError"></span>
				<br> <label>Scadenza:<input type="date" name="scadenza"
					required></label><br>
					<span class="scadenzaError"></span>
				<br>
			</fieldset>
			<br> <label>Note: <br>
			<textarea name="note" placeholder="Note aggiuntive..."></textarea></label> <br>
			<%} else {%>
			<legend>Informazioni di pagamento</legend>
				<label>Circuito:<input type="text" name="circuito" placeholder="mastercard/visa" value="<%=mp.getCircuito()%>"
					required></label><br>
				
				<br> <label>Numero carta:<input type="number" name="numero_carta" placeholder="numero di 16 cifre" value="<%=mp.getNumero()%>"
					required></label><br>
					<span class="numero_cartaError"></span>
				<br> <label>Scadenza:<input type="date" name="scadenza"
					required value="<%=mp.getScadenza()%>"></label><br>
					<span class="scadenzaError"></span>
				<br>
			</fieldset>
			<br> <label>Note: <br>
			<textarea name="note" placeholder="Note aggiuntive..."></textarea></label> <br>
			<%}%>	
			<br> <input type="submit" value="Acquista">
		</form>
	</div>
	<%@ include file="/fragments/footer.html"%>
</body>

<script src="<%=getServletContext().getContextPath() %>/script/jquery.js"></script>
<script src="<%=getServletContext().getContextPath() %>/script/checkout-validation.js"></script>
</html>