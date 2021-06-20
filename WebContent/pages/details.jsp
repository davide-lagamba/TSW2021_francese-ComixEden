<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%
	Prodotto product = (Prodotto) request.getAttribute("product");

	Carrello cart = (Carrello) request.getAttribute("cart");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="<%=getServletContext().getContextPath() %>/images/ComixEden.png">

<link rel="stylesheet" type="text/css"
	href="<%=getServletContext().getContextPath() %>/style/productviewstyle.css">

<title>Dettagli dell'articolo</title>
</head>

<body>

	<%@ include file="/fragments/header.jsp"%>

	<div class="container">

		<%
			if (product.getId() == -1) {
		%>
		<h2>Errore</h2>
		<p>Il prodotto cercato non è presente in catalogo!</p>
		<p>Potrebbe essere stato cancellato o non essere mai esistito</p>
		<%
			} else {
		%>
		<h2><%=product.getNome()%></h2>

		<div class="containerImgDtl">
			<%
				Collection<Immagine> img = (new ImmagineDS()).doRetrieveAll(product.getId());
					if (img != null && img.size() != 0) {
						Iterator<?> it = img.iterator();
						while (it.hasNext()) {
							Immagine bean = (Immagine) it.next();
							if (bean.isCopertina()) {
			%><img class="big-img dettagliimg" src="<%=getServletContext().getContextPath() %>/images/<%=bean.getNome()%>">

			<%
				}
						}
					}
			%>
			<%
				if (img != null && img.size() != 0) {
						Iterator<?> it = img.iterator();
						while (it.hasNext()) {
							Immagine bean = (Immagine) it.next();
							if (!bean.isCopertina()) {
			%>
			<img  class="big-img dettagliimg" src="<%=getServletContext().getContextPath() %>/images/<%=bean.getNome()%>">

			<%
				}
						}
					}
			%>
		</div>

		<table border="1" class="dettagli">
			<tr>
				<th scope="row">Nome</th>
				<td data-label="Nome"><%=product.getNome()%></td>
			</tr>
			<tr>
				<th scope="row">Descrizione</th>
				<td data-label="Descrizione"><%=product.getDescrizione()%></td>
			</tr>
			<tr>
				<th scope="row">Score</th>
				<td data-label="Score">
					<%
						String score = "" + product.getScoreMedio();
							if (score.equals("-1.0")) {
								score = "N/A";
							}
					%> <%=score%></td>
			</tr>

			<tr>
				<th scope="row">Stato</th>
				<td data-label="Stato"><%=product.getStato()%></td>
			</tr>
			<tr>
				<th scope="row">Disponibilità</th>
				<td data-label="Disponibilità"><%=product.getDisponibilita()%></td>
			</tr>

			<tr>
				<th scope="row">Prezzo</th>
				<%
					String prezzo = product.getPrezzoTotString();
				%>
				<td data-label="Prezzo"><%=prezzo%></td>
			</tr>

			<tr>
				<th scope="row">Sconto</th>
				<td data-label="Sconto"><%=Double.toString(product.getSconto())%>%</td>
			</tr>
			<%
				if (product.getDisponibilita() > 0) {
			%>
			<tr>
				<td colspan="2">
					<form action="product" method="post">
						<input id="quantita" type="number" name="quantita" min="1"
							max="<%=product.getDisponibilita()%>">
				</td>
			</tr>

			<tr>
				<td colspan="2" scope="row"><input type="hidden" name="id"
					value=<%=product.getId()%>> <input type="hidden"
					name="page" value="dettagli"> <input type="submit"
					name="action" value="Aggiungi al carrello"></input></a>
					</form></td>
			</tr>

			<%
				}
					int idCategoria = product.getIdCategoria();
					Categoria cd = new CategoriaDS().doRetrieveByKey(idCategoria);
					String categoria = cd.getNome();
			%>

			<tr>
				<th scope="row">Categoria</th>
				<td data-label="Categoria"><%=categoria%></td>
			</tr>

			<%
				int idProduttore = product.getIdProduttore();
					Produttore p = new ProduttoreDS().doRetrieveByKey(idProduttore);
					String nomeProduttore = p.getNome();
			%>

			<tr>
				<th scope="row">Produttore</th>
				<td data-label="Produttore"><%=nomeProduttore%></td>
			</tr>

			<tr>
				<th scope="row">Colore</th>
				<td data-label="Colore"><%=product.getColoreStampa()%></td>
			</tr>

			<tr>
				<th scope="row">Autori</th>
				<td data-label="Autori"><%=product.getAutori()%></td>
			</tr>

			<tr>
				<th scope="row">Data pubblicazione</th>
				<td data-label="Data pubblicazione"><%=product.getData().toString()%></td>
			</tr>

			<tr>
				<th scope="row">Peso</th>
				<td data-label="Peso"><%=product.getPeso()%>g</td>
			</tr>
			<tr>
				<th scope="row">Pagine</th>
				<td data-label="Pagine"><%=product.getPagine()%></td>
			</tr>
			<tr>
				<th scope="row">Lingua</th>
				<td data-label="Lingua"><%=product.getLingua()%></td>
			</tr>

		</table>
		<%
			}
		%>
	</div>
	<%@ include file="/fragments/footer.html"%>
</body>
<script
	src="<%=getServletContext().getContextPath()%>/script/jquery.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/smooth-zoom@latest/dist/zoom.browser.js"></script>

	<script type="text/javascript" src="<%=getServletContext().getContextPath()%>/script/okzoom.js"></script>
		<script>
	$(document).ready(function(){
		$('.big-img').okzoom({
			  width: 250,  
			  height: 250,  
			  round: true,
			  background: "#fff",
			  //backgroundRepeat: "repeat",
			  shadow: "0 0 5px #000",
			  border: "1px solid black"
			});			
	})
	  

	</script>
</html>