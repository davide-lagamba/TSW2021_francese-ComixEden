package control;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public LoginServlet() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	           throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/login.jsp");
        dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Carrello cart = (Carrello) request.getSession().getAttribute("cart");
			if (cart == null) {
				cart = new Carrello();
			}
			Utente utente = UtenteDS.doRetrieve(request.getParameter("email"),request.getParameter("pw") );

			if (utente.isValid()) {
				request.getSession().setAttribute("utente", utente);
				request.getSession().setAttribute("cart", cart);  
				request.setAttribute("utente", utente);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/cart.jsp");
				dispatcher.forward(request, response);
			} else {				
				request.getSession().setAttribute("cart", cart);  
				RequestDispatcher dispatcher =   getServletContext().getRequestDispatcher("/pages/login.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Throwable e) {
			System.out.println(e);
		}
	}
	
}
