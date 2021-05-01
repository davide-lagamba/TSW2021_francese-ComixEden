<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ page import="model.*" %>
 <%@ page import="java.util.*" %>
<%
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("../product");	
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
	<link href="../style/productviewstyle.css" rel="stylesheet" type="text/css">
	<title>Catalogo</title>
</head>

<body>
	<h2>Prodotti</h2>
	<a href="product">List</a>
	<table border="1">
		<tr>
			<th>ID <a href="product?sort=code">Sort</a></th>
			<th>Nome <a href="product?sort=name">Sort</a></th>
			<th>Descrizione <a href="product?sort=description">Sort</a></th>
			<th>Autori</th>
			<th>Disponibilità</th>
			<th>Azione</th>
		</tr>
		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					Prodotto bean = (Prodotto) it.next();
		%>
		<tr>
			<td><%=bean.getId()%></td>
			<td><%=bean.getNome()%></td>
			<td><%=bean.getDescrizione()%></td>
			<td><%=bean.getAutori()%></td>
			<td><%=bean.getDisponibilita()%></td>
			<td>
				<a href="product?action=read&id=<%=bean.getId()%>">Dettagli</a><br>
				<a href="product?action=addC&id=<%=bean.getId()%>">Aggiungi al carrello</a>
				</td>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">No products available</td>
		</tr>
		<%
			}
		%>
	</table>
	

</body>
</html>