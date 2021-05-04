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

/**
 * Servlet implementation class ProductControl
 */

@WebServlet("/product")
public class ProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static boolean isDataSource = true;

	static ProdottoDS model = new ProdottoDS();;

	public ProductControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Carrello cart = (Carrello) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Carrello();
			request.getSession().setAttribute("cart", cart);
		}

		String action = request.getParameter("action");

		String id_prodotto = (request.getParameter("id"));

		try {
			if (id_prodotto != null) {
				request.removeAttribute("product");
				request.setAttribute("product", model.doRetrieveByKey(Integer.parseInt(id_prodotto)));
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/details.jsp");
				dispatcher.forward(request, response);

			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		try {
			if (action != null) {
				if (action.equalsIgnoreCase("deleteC")) {
					int id = Integer.parseInt(request.getParameter("id"));
					cart.removeItem(id);
				} else if (action.equalsIgnoreCase("Dettagli")) {
					int id = Integer.parseInt(request.getParameter("id"));
					request.removeAttribute("product");
					request.setAttribute("product", model.doRetrieveByKey(id));
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/details.jsp");
					dispatcher.forward(request, response);
				} else if (action.equalsIgnoreCase("delete")) {
					int id = Integer.parseInt(request.getParameter("id"));
					model.doDelete(id);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);

		String sort = request.getParameter("sort");
		String page = request.getParameter("page");

		try {
			request.removeAttribute("products");
			if (sort != null && sort.equals("PrezzoTot")) {
				request.setAttribute("products", model.doRetrieveAllPrezzoTot());
			} else {
				request.setAttribute("products", model.doRetrieveAll(sort));
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		if (page != null && page.equals("dettagli")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.removeAttribute("product");
			try {
				request.setAttribute("product", model.doRetrieveByKey(id));
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/details.jsp");
			dispatcher.forward(request, response);
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/ProductView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Carrello cart = (Carrello) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Carrello();
			request.getSession().setAttribute("cart", cart);
		}

		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("Aggiungi al carrello")) {
					int id = Integer.parseInt(request.getParameter("id"));

					String quan = request.getParameter("quantita");
					int quantita;
					if (quan.equals("")) {
						quantita = 1;
					} else {
						quantita = Integer.parseInt(quan);
					}
					if (cart.hasItem(id)) {
						cart.incrementNumOrdered(id, quantita);
					} else {
						cart.addItem(id);
						cart.setNumOrdered(id, quantita);
					}
					String page = request.getParameter("page");
					if (page != null && page.equals("dettagli")) {
						response.sendRedirect("product?id=" + Integer.parseInt(request.getParameter("id")));
						return;
					} else {
						response.sendRedirect("product");
						return;
					}
				}

				else if (action.equalsIgnoreCase("deleteC")) {
					int id = Integer.parseInt(request.getParameter("id"));
					cart.removeItem(id);
				} else if (action.equalsIgnoreCase("delete")) {
					int id = Integer.parseInt(request.getParameter("id"));
					model.doDelete(id);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);

		String sort = request.getParameter("sort");
		String page = request.getParameter("page");

		try {
			request.removeAttribute("products");
			if (sort != null && sort.equals("PrezzoTot")) {
				request.setAttribute("products", model.doRetrieveAllPrezzoTot());
			} else {
				request.setAttribute("products", model.doRetrieveAll(sort));
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		if (page != null && page.equals("dettagli")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.removeAttribute("product");
			try {
				request.setAttribute("product", model.doRetrieveByKey(id));
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/details.jsp");
			dispatcher.forward(request, response);
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/ProductView.jsp");
		dispatcher.forward(request, response);
	}

}
