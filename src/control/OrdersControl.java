package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

@WebServlet("/orders")
public class OrdersControl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Utente user = (Utente) request.getSession().getAttribute("utente");

		if (user == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/login.jsp");
			dispatcher.forward(request, response);
			return;
		} else {

			if (request.getParameter("id") != null) {
				int id = Integer.parseInt(request.getParameter("id"));
				try {
					Collection<DettaglioOrdine> dettagli;
					if (request.getParameter("sort") == null) {
						dettagli = new DettaglioOrdineDS().doRetrieveAllByUser(user.getId_utente(), id, "");
					} else {
						dettagli = new DettaglioOrdineDS().doRetrieveAllByUser(user.getId_utente(), id,
								request.getParameter("sort"));
					}
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

			String sort = request.getParameter("sort");
			request.removeAttribute("orders");
			try {
				if (sort != null) {
					request.setAttribute("orders", new OrdineDS().doRetrieveAll(user.getId_utente(), sort));
				} else {
					request.setAttribute("orders", new OrdineDS().doRetrieveAll(user.getId_utente(), "data"));
				}
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/orderslist.jsp");
			dispatcher.forward(request, response);
			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
