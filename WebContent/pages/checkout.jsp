<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<link rel="icon" href="./images/ComixEden.png">
<link rel="stylesheet" type="text/css"
	href="./style/productviewstyle.css">
<title>Checkout</title>
</head>
<body>
	<%@ include file="../fragments/header.jsp"%>

	<%
		Utente user = (Utente) request.getSession().getAttribute("utente");
		
		if (user == null) {
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login");
			request.getSession().setAttribute("page", "checkout");
			dispatcher.forward(request, response);
		}
	%>
	<div class="container">
		<form action="checkout" method="post">
			<fieldset>
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
				<br> <label>Via:<input type="text"
					name="via_fatturazione" placeholder="Via Napoli, 40"></label><br>
				<br>
			</fieldset>
			<br>
			<fieldset>
				<legend>Informazioni di pagamento</legend>
				Circuito:<select name="circuito" required>
					<option value="visa">Visa</option>
					<option value="mastercard">Mastercard</option>
					<option value="americanexpress">American Express</option>
				</select> <br>
				<br> <label>Scadenza:<input type="date" name="scadenza"
					required></label>*<br>
				<br>
			</fieldset>
			<br> <label>Note: <br>
			<textarea name="note" placeholder="Note aggiuntive..."></textarea></label> <br>
			<br> <input type="submit" value="Acquista">
		</form>
	</div>
	<%@ include file="../fragments/footer.html"%>
</body>
</html>