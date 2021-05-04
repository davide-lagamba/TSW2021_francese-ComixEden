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

@WebServlet("/cartView")
public class CartControl  extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public CartControl() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Carrello cart = (Carrello)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new Carrello();
			request.getSession().setAttribute("cart", cart);
		}
		
		String action = request.getParameter("action");

		
			if (action != null) {
				if (action.equalsIgnoreCase("Modifica")) {
					int id = Integer.parseInt(request.getParameter("id"));
					
					String quan=request.getParameter("quantita");				
					int quantita;
					if(quan.equals("")) {
						quantita=1;
					}else {
						quantita=Integer.parseInt(quan);
					}				
					try {
						cart.setNumOrdered(id, quantita);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			} else if (action.equalsIgnoreCase("Rimuovi")) {
					int id = Integer.parseInt(request.getParameter("id"));
					try {
						cart.removeItem(id);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} else if (action.equalsIgnoreCase("Dettagli")) {
				int id = Integer.parseInt(request.getParameter("id"));
				try {
					request.setAttribute("product", new ProdottoDS().doRetrieveByKey(id));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher dispatcher =   getServletContext().getRequestDispatcher("/pages/details.jsp");
				dispatcher.forward(request, response);
			} else if (action.equalsIgnoreCase("Svuota")) {
				cart = new Carrello();
			}
				
			else if (action.equalsIgnoreCase("Checkout")) {
				cart = new Carrello();
				request.getSession().setAttribute("cart", cart);
				request.setAttribute("cart", cart);
				RequestDispatcher dispatcher =   getServletContext().getRequestDispatcher("/pages/checkout.jsp");
				dispatcher.forward(request, response);
			}
			}

		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/cart.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
