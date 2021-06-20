<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%	
	Utente user = (Utente) request.getSession().getAttribute("utente");		
	
	if (user == null || !user.isAdmin()) {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/nonAutenticato.jsp");
		dispatcher.forward(request, response);
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
<link rel="icon" href="<%=getServletContext().getContextPath()%>/images/ComixEden.png">
<link rel="stylesheet" type="text/css"
	href="<%=getServletContext().getContextPath()%>/style/productviewstyle.css">
	<%@ include file="/fragments/header.jsp"%>
	<%if (product==null) {%>
<title>Aggiungi prodotto</title>
</head>

<body>

	
	<div class="container">
	<form action="gestioneCatalogo" name="aggiungi" method="post" enctype="multipart/form-data" onSubmit="return formValidation();">
	<fieldset>
	<label>Informazioni principali</label>
	<br><br><br>
	<label>Nome: <input type="text" name="nome" required></label><br><br>
	<label>Descrizione: <input type="text" name="descrizione" required></label><br><br>
	<label>Autori: <input type="text" name="autori" required></label><br><br>
	<label>Lingua: <input type="text" name="lingua" required></label><br><br>
	<label>Produttore: <input type="text" name="produttore" required></label><br><br>
	<label>Categoria: <input type="text" name="categoria" required></label><br><br>
	</fieldset><br><br>
	<fieldset>
	<label>Informazioni di stato</label><br><br><br>
	<label>Stato: <input type="text" name="stato" required></label><br><br>
	<label>Colore: <input type="text" name="colore_stampa" required></label><br><br>
	<label>Prezzo base: <input type="number" name="prezzo_base" required></label><br>
	<span class="prezzoError"></span><br>
	<label>Peso: <input type="number" name="peso" required></label><br><span class="pesoError"></span><br>
	<label>Disponibilità: <input type="number" name="disponibilita" required></label><br><span class="disponibilitaError"></span><br>
	<label>Pagine: <input type="number" name="pagine" required></label><br><span class="pagineError"></span><br>
	<label>Sconto: <input type="number" name="sconto" required></label><br><span class="scontoError"></span><br>
	<label>IVA: <input type="number" name="iva" required></label><br><span class="ivaError"></span><br>
	</fieldset><br><br>
	Aggiungi immagine
	<input type="file" name="img" size="500" accept=".jpg"/><br><br><br>
	<input type="submit" name="action" value="Aggiungi">
	
	
	</form>

	
	</div>
	

	<%}else{%>
	<title>Modifica prodotto</title>
</head>

<body>

	<div class="container">
	<form action="gestioneCatalogo" method="post" name="aggiungi" enctype="multipart/form-data" onSubmit="return formValidation();">
	<fieldset>
	<label>Informazioni principali</label>
	<br><br><br>
	<label>Nome: <input type="text" name="nome" required value="<%=product.getNome() %>"></label><br><br>
	<label>Descrizione: <input type="text" name="descrizione" required value="<%=product.getDescrizione() %>"></label><br><br>
	<label>Autori: <input type="text" name="autori" required value="<%=product.getAutori() %>"></label><br><br>
	<label>Lingua: <input type="text" name="lingua" required value="<%=product.getLingua() %>"></label><br><br>
	<label>Produttore: <input type="text" name="produttore" required value="<%=new ProduttoreDS().doRetrieveByKey(product.getIdProduttore()).getNome()%>"></label><br><br>
	<label>Categoria: <input type="text" name="categoria" required value="<%=new CategoriaDS().doRetrieveByKey(product.getIdCategoria()).getNome()%>"></label><br><br>
	</fieldset><br><br>
	<fieldset>
	<label>Informazioni di stato</label><br><br><br>
	<label>Stato: <input type="text" name="stato" required value="<%=product.getStato() %>"></label><br><br>
	<label>Colore: <input type="text" name="colore_stampa" required value="<%=product.getColoreStampa() %>"></label><br><br>
	<label>Prezzo base: <input type="number" name="prezzo_base" required value="<%=product.getPrezzoBase() %>"></label><br><span class="prezzoError"></span><br>
	<label>Peso: <input type="number" name="peso" required value="<%=product.getPeso()%>"></label><br><span class="pesoError"></span><br>
	<label>Disponibilità: <input type="number" name="disponibilita" required value="<%=product.getDisponibilita() %>"></label><br><span class="disponibilitaError"></span><br>
	<label>Pagine: <input type="number" name="pagine" required value="<%=product.getPagine() %>"></label><br><span class="pagineError"></span><br>
	<label>Sconto: <input type="number" name="sconto" required value="<%=product.getSconto()%>"></label><br><span class="scontoError"></span><br>
	<label>IVA: <input type="number" name="iva" required value="<%=product.getIva() %>"></label><br><span class="ivaError"></span><br>
	</fieldset><br><br>
	Aggiungi immagine
	<input type="file" name="img" size="500" accept=".jpg"/><br><br><br>
	<input type="hidden" name="id" value=<%=product.getId() %>>
	<input type="submit" name="action" value="Modifica">
	
	
	</form>

	
	</div>
	<%} %>
	<%@ include file="/fragments/footer.html"%>
</body>

<script src="<%=getServletContext().getContextPath() %>/script/jquery.js"></script>
<script src="<%=getServletContext().getContextPath() %>/script/aggiungi-validation.js"></script>
</html>