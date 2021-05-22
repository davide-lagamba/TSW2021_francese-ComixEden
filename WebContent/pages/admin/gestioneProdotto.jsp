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
	

<title>Dettagli prodotto</title>
</head>

<body>

	<%@ include file="/fragments/header.jsp"%>

	<div class="container">
	
		<%if(product.getId()==-1) {%>
		<h2>Errore</h2>
		<p>Il prodotto cercato non è presente in catalogo!</p>
		<p>Potrebbe essere stato cancellato o non essere mai esistito</p><%}else{ %>
		<h2><%=product.getNome()%></h2>

		<table border="1">

				
				<%
					Collection<Immagine> img = (new ImmagineDS()).doRetrieveAll(product.getId());
					if (img != null && img.size() != 0) {
						Iterator<?> it = img.iterator();
						while (it.hasNext()) {
							Immagine bean = (Immagine) it.next();
							if (bean.isCopertina()) {
				%>

				<img src="<%=getServletContext().getContextPath()%>/images/<%=bean.getNome()%>" class="dettagliimg">

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
				<img src="<%=getServletContext().getContextPath()%>/images/<%=bean.getNome()%>" class="dettagliimg">
			</div>
			<%
				}
					}
				}
			%>
			<th>Nome</th>
			<td><%=product.getNome()%></td>
			</tr>
			<tr>
				<th>Descrizione</th>
				<td><%=product.getDescrizione()%></td>
			</tr>
			<tr>
				<th>Score</th>
				<td>
					<%
						String score = "" + product.getScoreMedio();
						if (score.equals("-1.0")) {
							score = "N/A";
						}
					%> <%=score%></td>
			</tr>

			<tr>
				<th>Stato</th>
				<td><%=product.getStato()%></td>
			</tr>
			<tr>
				<th>Disponibilità</th>
				<td><%=product.getDisponibilita()%></td>
			</tr>

			<tr>
				<th>Prezzo</th>
				<%
					String prezzo = product.getPrezzoTotString();
				%>
				<td><%=prezzo%></td>
			</tr>

			<tr>
				<th>Sconto</th>
				<td><%=Double.toString(product.getSconto())%>%</td>
			</tr>



			<%
				int idCategoria = product.getIdCategoria();
				Categoria cd = new CategoriaDS().doRetrieveByKey(idCategoria);
				String categoria = cd.getNome();
			%>

			<tr>
				<th>Categoria</th>
				<td><%=categoria%></td>
			</tr>

			<%
				int idProduttore = product.getIdProduttore();
				Produttore p = new ProduttoreDS().doRetrieveByKey(idProduttore);
				String nomeProduttore = p.getNome();
			%>

			<tr>
				<th>Produttore</th>
				<td><%=nomeProduttore%></td>
			</tr>

			<tr>
				<th>Colore</th>
				<td><%=product.getColoreStampa()%></td>
			</tr>

			<tr>
				<th>Autori</th>
				<td><%=product.getAutori()%></td>
			</tr>

			<tr>
				<th>Data pubblicazione</th>
				<td><%=product.getData().toString()%></td>
			</tr>

			<tr>
				<th>Peso</th>
				<td><%=product.getPeso()%>g</td>
			</tr>
			<tr>
				<th>Pagine</th>
				<td><%=product.getPagine()%></td>
			</tr>
			<tr>
				<th>Lingua</th>
				<td><%=product.getLingua()%></td>
			</tr>

		</table><%} %>
		<br><br>
		<a href="gestioneCatalogo?action=inserisci&id=<%=product.getId()%>">Modifica prodotto</a>
	</div>
	<%@ include file="/fragments/footer.html"%>
</body>
</html>