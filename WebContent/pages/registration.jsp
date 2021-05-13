<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>

<%

    Carrello cart = (Carrello) request.getAttribute("cart"); 
 
    String errore = (String) (session.getAttribute("registrazione"));
    
    String pw = (String) (request.getAttribute("error"));
    
%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="./images/ComixEden.png">
<%
     if((pw!=null)||(errore!=null))
     {
       if((pw.equals("errorP"))||(errore.equals("errore")))
       {%>
         <link rel="stylesheet" type="text/css" href="./WebContent/style/registrationstyle.css">
	     
<%}} else {%>
<link rel="stylesheet" type="text/css" href="../style/registrationstyle.css">
<%} %>
</head>
<body>
<%@ include file="../fragments/header.jsp" %>
 <%
     if((pw!=null)||(errore!=null))
     {
       if((pw.equals("errorP"))||(errore.equals("errore")))
       {
    %>
    <form action="./Reg" method="post"> <% }} else {%>
    
<form action="Reg" method="post">
<% } %>
  <div class="container">
    <h1>Register</h1>
    <p>Please fill in this form to create an account.</p>
    <hr>
  
    <label for="nome"><b>Nome</b></label>
    <input type="text" placeholder="nome" name="nome" id="nome" required>
    
    <label for="congome"><b>Cognome</b></label>
    <input type="text" placeholder="cognome" name="cognome" id="cognome" required>
    
    <label for="email"><b>Email</b></label>
    <input type="text" placeholder="email" name="email" id="email" required>
    
    <label for="password"><b>Password</b></label>
    
    <%
       if((pw!=null)&&(pw.equals("errorP")))
       {
    %>
          le password non corrispondono 
        
    <%  }  %>
    <input type="password" placeholder="password" name="password" id="password" required>
    
    <label for="psw-repeat"><b>Repeat Password</b></label>
    <input type="password" placeholder="Repeat Password" name="psw-repeat" id="psw-repeat" required>
    
    <label for="telefono"><b>Telefono</b></label>
    <input type="text" placeholder="telefono" name="telefono" id="telefono" required>


    <button type="submit" class="registerbtn">Register</button>
  </div>
  
  <div class="container signin">
  <%
       if((errore!=null)&&(errore.equals("errore")))
       {
%>
        email già in uso 
        
      <%}%>
    <p>Already have an account? <a href="#">Sign in</a>.</p>
  </div>
</form>
<%@ include file="../fragments/footer.html" %>
<%request.removeAttribute("registrazione");
   request.removeAttribute("error"); %>
</body>
</html>

