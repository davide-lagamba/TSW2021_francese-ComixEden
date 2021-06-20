<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if (products == null) {
		response.sendRedirect("../product");
		return;
	}

	Prodotto product = (Prodotto) request.getAttribute("product");

	Carrello cart = (Carrello) request.getAttribute("cart");
	
	Utente user = (Utente) request.getSession().getAttribute("utente");		
	
	if (user == null || !user.isAdmin()) {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/nonAutenticato.jsp");
		dispatcher.forward(request, response);
		return;
	}
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.2/css/bulma.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" href="<%=getServletContext().getContextPath()%>/images/ComixEden.png">
<link rel="stylesheet" type="text/css"
	href="<%=getServletContext().getContextPath()%>/style/productviewstyle.css">
<title>Gestione Catalogo</title>
</head>

<body>

	<%@ include file="/fragments/header.jsp"%>
	
	<br><br>
			<h2 class="is-size-2">Prodotti</h2>
			
	<h3><a href="gestioneCatalogo?action=inserisci">Aggiungi prodotto</a></h3><br><br>
	<div class="field searchbar p-6">
	  <div class="control has-icons-left has-icons-right">
	  	<div id="dropdownsearch" class="dropdown is-active">
	  	  <div class="dropdown-trigger">
	  	    <input id="search" name="search" class="input" type="text" placeholder="Cerca..." autocomplete="off">
	  	    <span class="icon is-small is-left">
		      <i class="fa fa-search" aria-hidden="true"></i>
		    </span>
	  	  </div>
	  	  <div id="dropdownmenu" class="dropdown-menu">
	  	    
	  	  </div>
	  	</div>
	  </div>
	</div>
	<div class="containerProdotti">
		<%
		if (products != null && products.size() != 0) {
			Iterator<?> it = products.iterator();
			while (it.hasNext()) {
				Prodotto bean = (Prodotto) it.next();
			%>
			<div class="card">  
				<p>ID: <%=bean.getId()%></p>
				<p><%=bean.getNome()%></p>
				<p><%=bean.getDescrizione()%></p>
				<div>
					<a href="gestioneCatalogo?id=<%=bean.getId()%>"><img src="<%=getServletContext().getContextPath()%>/images/<%=(new ImmagineDS()).doRetrieveByKey(bean.getId()).getNome()%>" alt="<%=bean.getNome()%>"></a>
				</div>
				<p>Autore: <%=bean.getAutori()%></p>
				<p><a href="gestioneCatalogo?id=<%=bean.getId()%>">Dettagli</a></p>
				<p>Disponibilità:<%=bean.getDisponibilita()%></p>
				<form action="gestioneCatalogo" method="post">
					<input type="hidden" name="id" value=<%=bean.getId()%>>
					<input type="submit" name="action" value="Rimuovi">
				</form>
				<p class="price"><%=bean.getPrezzoTotString()%></p>
				</div>
			<%}
		} else {%>
			<h2>No products available</h2>
		<%}%>
	</div>

</body>
<script src="<%=getServletContext().getContextPath() %>/script/jquery.js"></script>
<script>
var _changeSearch = null;
$(document).ready(function(e) {
	$("#search").on("keyup", function(){
		clearInterval(_changeSearch);
		
		_changeSearch = setInterval(function() {
			//Funzione richiamata quando si scrive in Search e
			//abbiamo finito di scrivere (viene richiamata dopo x secondi, vedi intervallo setInterval)
		
			var textToSearch = $("#search").val();
			if(textToSearch.length > 0) {
				$.get("gestioneCatalogo", { action: "search", val: textToSearch }, function(data) {
					$("#dropdownmenu").empty();
					if(data.length > 0) {
						data.forEach((e) => {
							var myvar = '<div class="dropdown-content"><a class="dropdown-item" href="gestioneCatalogo?action=cerca&searchVal='+ e.nome + '">' + e.nome + '</a></div>';
							$("#dropdownmenu").append(myvar);
						})
						$("#dropdownsearch").addClass("is-active");
					}
				})
			} else {
				$("#dropdownmenu").empty();
			}
	        clearInterval(_changeSearch)
	    }, 500);
	});
	
	$("#search").on('keypress',function(e) {
	    if(e.which == 13) {
	    	location.href = 'gestioneCatalogo?action=cerca&searchVal=' + $("#search").val()
	    }
	});
})


</script>
<%@ include file="/fragments/footer.html"%>
</html>