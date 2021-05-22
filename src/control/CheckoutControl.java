package control;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

@WebServlet("/checkout")
public class CheckoutControl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public CheckoutControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Utente user = (Utente) request.getSession().getAttribute("utente");
		if (user == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login");
			dispatcher.forward(request, response);
			return;
		}

		Carrello cart = (Carrello) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Carrello();
			request.getSession().setAttribute("cart", cart);
		}

		int id = -1;
		
		Ordine order = new Ordine();
		order.setConsegnato(false);
		order.setIdUtente(user.getId_utente());
		order.setIdSpedizione(-1);
		order.setIdFatturazione(-1);
		order.setNote(request.getParameter("note"));
		order.setIdPagamento(-1);
		order.setQuantita(cart.getQuantity());
		order.setCostoSpedizione(5);
		order.setPrezzoTotale(cart.getPrezzoTot());

		Date now = new Date(new java.util.Date().getTime());
		order.setData(now);

		try {
			new OrdineDS().doSave(order);
		} catch (SQLException e) {
			System.out.println("Error 1:" + e.getMessage());
		}

		try {
			id = new OrdineDS().getNewId();
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		ArrayList lista = cart.getItemsOrdered();

		DettaglioOrdine dettaglio;
		for (int i = 0; i < lista.size(); i++) {
			dettaglio = (DettaglioOrdine) lista.get(i);
			dettaglio.setIdOrdine(id);
			System.out.println("id: " + id);
			try {
				new DettaglioOrdineDS().doSave(dettaglio);
				new ProdottoDS().removeDisponibilita(dettaglio.getIdProdotto(), dettaglio.getNumItems());
			} catch (SQLException e) {
				System.out.println("Error 2:" + e.getMessage());
			}
		}
		Carrello nuovo= new Carrello();
		request.getSession().setAttribute("cart", nuovo);
		request.setAttribute("cart", nuovo);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product");
		dispatcher.forward(request, response);
		return;
	}

}
