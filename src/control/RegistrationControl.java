package control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
@WebServlet("/Reg")

public class RegistrationControl extends HttpServlet{
	
		private static final long serialVersionUID = 1L;

		static boolean isDataSource = true;

		static UtenteDS model = new UtenteDS();;

		public RegistrationControl() {
			super();
		}

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			           RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/registration.jsp");
		               dispatcher.forward(request, response);
		           }
			       
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			try {
				   Carrello cart = (Carrello) request.getSession().getAttribute("cart");
				   if (cart == null) {
						cart = new Carrello();
					}
			       String nome = (String) request.getParameter("nome");
			       String cognome = (String) request.getParameter("cognome");
			       String email = (String) request.getParameter("email");
			       String password = (String) request.getParameter("password");
			       String rPassword = (String) request.getParameter("pasw-repeat");
			       if (!password.equals(rPassword))
			       {
			    	   request.setAttribute("error","errorP");
		        	   request.getSession().setAttribute("cart", cart);  
				       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/registration.jsp");
				       dispatcher.forward(request, response);
			       }
			       String telefono = (String) request.getParameter("telefono");
			       
		           Utente user = new Utente(nome,cognome,email,password,telefono);
		           model.doSave(user);

		           if (user.getRegistrazione())
		           {
		        	   Utente userReg=model.doRetrieveByKey(email);
		        	   request.getSession().setAttribute("user", userReg);
		        	   request.getSession().setAttribute("cart", cart);  
				       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/userLogged.jsp");
				       dispatcher.forward(request, response);
		                 		
		           }
		      	        
		           else 
		           {	 
		        	   request.setAttribute("registrazione","errore");
		        	   request.getSession().setAttribute("cart", cart);  
				       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/registration.jsp");
				       dispatcher.forward(request, response);
		           }
			       
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}


			}

	}

