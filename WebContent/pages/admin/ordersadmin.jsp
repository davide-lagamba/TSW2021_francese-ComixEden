<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%
	Utente user = (Utente) request.getSession().getAttribute("utente");

if (user == null || !user.isAdmin()) {
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/nonAutenticato.jsp");
	dispatcher.forward(request, response);
	return;
}
	Collection<?> ordini = (Collection<?>) request.getAttribute("orders");
	if (ordini == null) {
		response.sendRedirect("../ordersad");
		return;
	}

	Carrello cart = (Carrello) request.getAttribute("cart");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="<%=getServletContext().getContextPath()%>/images/ComixEden.png">
<link rel="stylesheet" type="text/css"
	href="<%=getServletContext().getContextPath() %>/style/productviewstyle.css">
<title>Lista Ordini</title>
</head>

<body>

	<%@ include file="/fragments/header.jsp"%>
	<div class="container">
		
		<table border="1">
		<caption>Ordini</caption>
		<thead>
			<tr>
				<th scope="col"><a href="ordersad?research=<%=request.getAttribute("research") %>&sort=id_ordine<%if(request.getParameter("email")!=null && !request.getParameter("email").equals("")){%><%="&email="+request.getParameter("email")%><%}if(request.getParameter("inizio")!=null && !request.getParameter("inizio").equals("")){%><%="&inizio="+request.getParameter("inizio")+"&fine="+request.getParameter("fine")%><%}%>">ID</a></th>
				<th scope="col"><a href="ordersad?research=<%=request.getAttribute("research") %>&sort=id_spedizione<%if(request.getParameter("email")!=null && !request.getParameter("email").equals("")){%><%="&email="+request.getParameter("email")%><%}if(request.getParameter("inizio")!=null && !request.getParameter("inizio").equals("")){%><%="&inizio="+request.getParameter("inizio")+"&fine="+request.getParameter("fine")%><%}%>">Indirizzo spedizione</a></th>
				<th scope="col"><a href="ordersad?research=<%=request.getAttribute("research") %>&sort=prezzo_totale<%if(request.getParameter("email")!=null && !request.getParameter("email").equals("")){%><%="&email="+request.getParameter("email")%><%}if(request.getParameter("inizio")!=null && !request.getParameter("inizio").equals("")){%><%="&inizio="+request.getParameter("inizio")+"&fine="+request.getParameter("fine")%><%}%>">Prezzo totale</a></th>
				<th scope="col"><a href="ordersad?research=<%=request.getAttribute("research") %>&sort=quantita<%if(request.getParameter("email")!=null && !request.getParameter("email").equals("")){%><%="&email="+request.getParameter("email")%><%}if(request.getParameter("inizio")!=null && !request.getParameter("inizio").equals("")){%><%="&inizio="+request.getParameter("inizio")+"&fine="+request.getParameter("fine")%><%}%>">Quantità</a></th>
				<th scope="col"><a href="ordersad?research=<%=request.getAttribute("research") %>&sort=data<%if(request.getParameter("email")!=null && !request.getParameter("email").equals("")){%><%="&email="+request.getParameter("email")%><%}if(request.getParameter("inizio")!=null && !request.getParameter("inizio").equals("")){%><%="&inizio="+request.getParameter("inizio")+"&fine="+request.getParameter("fine")%><%}%>">Data</a></th>
				<th scope="col" class="Note"><a href="ordersad?research=<%=request.getAttribute("research") %>&sort=note<%if(request.getParameter("email")!=null && !request.getParameter("email").equals("")){%><%="&email="+request.getParameter("email")%><%}if(request.getParameter("inizio")!=null && !request.getParameter("inizio").equals("")){%><%="&inizio="+request.getParameter("inizio")+"&fine="+request.getParameter("fine")%><%}%>">Note</a></th>
				<th>Azione</th>
			</tr>
		</thead>	
			<%
				if (ordini != null && ordini.size() != 0) {
					Iterator<?> it = ordini.iterator();
					while (it.hasNext()) {
						Ordine bean = (Ordine) it.next();
			%>
			<tr>
				<td data-label="ID"><%=bean.getIdOrdine()%></td>
				<td data-label="Indirizzo spedizione"><%=bean.getIdSpedizione()%></td>
				<td data-label="Prezzo totale"><%=bean.getPrezzoTotaleString()%></td>
				<td data-label="Quantità"><%=bean.getQuantita()%></td>
				<td data-label="Data"><%=bean.getData()%></td>
				<td data-label="Note" class="Note"> <span style="text-alling:right"><%=bean.getNote()%></span></td>
				<td><form action="ordersad" method="get">
					<a href="ordersad?id=<%=bean.getIdOrdine()%>">Dettagli</a>			
				</form></td>
			</tr>
			<%
				}
				} else {
			%>
			<tr>
				<td colspan="7">No orders available</td>
			</tr>
			<%
				}
			%>
		</table>


	</div>


</body>
<%@ include file="/fragments/footer.html"%>
</html>