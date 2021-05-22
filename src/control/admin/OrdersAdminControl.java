package control.admin;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

@WebServlet("/admin/ordersad")
public class OrdersAdminControl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Utente user = (Utente) request.getSession().getAttribute("utente");
		String research = (String) request.getParameter("research");
		if ((user == null)||(!user.isAdmin())) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/nonAutenticato.jsp");
			dispatcher.forward(request, response);
			return;
		}
	    if (research == null) {
	    	
	    	if (request.getParameter("id") != null) {
				int id = Integer.parseInt(request.getParameter("id"));
				try {
					Collection<DettaglioOrdine> dettagli;
					if (request.getParameter("sort") == null) {
						dettagli = new DettaglioOrdineDS().doRetrieveAll(id, "");
					} else {
						dettagli = new DettaglioOrdineDS().doRetrieveAll(id,request.getParameter("sort"));
					}
					request.setAttribute("research", "dettagli");
					request.setAttribute("dettagli", dettagli);
					request.setAttribute("id_ordine", id);
					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher("/pages/dettagliOrdine.jsp");
					dispatcher.forward(request, response);
					return;
				} catch (SQLException e) {
					System.out.println("Error 2:" + e.getMessage());
				}
			}
	    	
			RequestDispatcher dispatcherb = getServletContext().getRequestDispatcher("/pages/admin/ordersearch.jsp");
			dispatcherb.forward(request, response);
			return;
		} 
	    if (research.equals("tutti"))
	    {
			String sort = request.getParameter("sort");
			request.removeAttribute("orders");
			request.setAttribute("research", research);
			try {
					request.setAttribute("orders", new OrdineDS().doRetrieveAll(sort));
				}
			 catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}
			RequestDispatcher dispatcherd = getServletContext().getRequestDispatcher("/pages/admin/ordersadmin.jsp");
			dispatcherd.forward(request, response);
			return;
	    }
	    
	    if (research.equals("data"))
	    {
			String sort = request.getParameter("sort");
			String i = (String) request.getParameter("inizio");
			String f = (String) request.getParameter("fine");
				
				request.removeAttribute("orders");
				request.setAttribute("research", research);
				try {
						request.setAttribute("orders", new OrdineDS().doRetrieveAllByDate(sort,i,f));
						System.out.println(i.toString());						}
				
				 catch (SQLException e) {
					System.out.println("Error:" + e.getMessage());
				}
				
				RequestDispatcher dispatcherd = getServletContext().getRequestDispatcher("/pages/admin/ordersadmin.jsp");
				dispatcherd.forward(request, response);
				return;
	    }
	 
	 if (research.equals("user"))
	    {
			String sort = request.getParameter("sort");
			String email = request.getParameter("email");
				request.removeAttribute("orders");
				request.setAttribute("research", research);
				try {
						request.setAttribute("orders", new OrdineDS().doRetrieveAllByEmail(email,sort));
					}
				
				 catch (SQLException e) {
					System.out.println("Error:" + e.getMessage());
				}
				
				RequestDispatcher dispatcherd = getServletContext().getRequestDispatcher("/pages/admin/ordersadmin.jsp");
				dispatcherd.forward(request, response);
				return;
				
		
	    }
	    
}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
		
	}

}
