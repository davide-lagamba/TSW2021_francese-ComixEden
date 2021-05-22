<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=getServletContext().getContextPath()%>/style/headerstyle.css">

</head>
<div class="containernav">
	<div class="left">
		<a href="<%=getServletContext().getContextPath()%>/pages/ProductView.jsp"> <img
			src="<%=getServletContext().getContextPath()%>/images/ComixEden.png" alt="logo" id="logo"></a>
			<a href="<%=getServletContext().getContextPath()%>/product" class="catalog">Catalogo</a>
			
		
	</div>
	
	<div class="push">
	<%if(request.getSession().getAttribute("utente") == null){ %>
		<a href="login" class="catalog" >LogIn</a> <%
	}else{ %>
		<%if( ((Utente)request.getSession().getAttribute("utente")).isAdmin() ){ %>
			<a href="<%=getServletContext().getContextPath()%>/admin/view" class="catalog">Area Admin</a> <%
		}else{ %>
			<a href="<%=getServletContext().getContextPath()%>/orders" class="catalog">Area Utente <%=((Utente)request.getSession().getAttribute("utente")).getNome() %></a>
		<%} %>
		<a href="<%=getServletContext().getContextPath()%>/login?action=logout" class="catalog">Logout</a>
		<%} %>
		<a href="<%=getServletContext().getContextPath()%>/cartView"  class="catalog">Carrello <%if(request.getSession().getAttribute("cart")!=null){ %>(<%=((Carrello) request.getSession().getAttribute("cart")).getQuantity()%>)<%} %>
		</a>
	</div>
</div>
</html>