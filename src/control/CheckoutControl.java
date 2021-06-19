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
		}

		Carrello cart = (Carrello) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Carrello();
			request.getSession().setAttribute("cart", cart);
		}

		int id = -1;
		
		MetodoPagamento mp=new MetodoPagamento();
		mp.setNumero(request.getParameter("numero_carta"));
		mp.setCircuito(request.getParameter("circuito"));
		mp.setScadenza(Date.valueOf(request.getParameter("scadenza")));
		
		Indirizzo fa=new Indirizzo();
		fa.setCap(request.getParameter("cap_fatturazione"));
		fa.setCitta(request.getParameter("citta_fatturazione"));
		fa.setStato(request.getParameter("stato_fatturazione"));
		fa.setVia(request.getParameter("via_fatturazione"));
		Indirizzo sp=new Indirizzo();
		sp.setCap(request.getParameter("cap_spedizione"));
		sp.setCitta(request.getParameter("citta_spedizione"));
		sp.setStato(request.getParameter("stato_spedizione"));
		sp.setVia(request.getParameter("via_spedizione"));
		
		try {
			
			MetodoPagamentoDS m=new MetodoPagamentoDS();
			IndirizzoDS i=new IndirizzoDS();
			if(m.doRetrieveByKey(mp.getNumero()).getNumero()==null)
			{
				m.doSave(mp, user);
			}
			if(fa.getId()==-1)
			{
				
				if(i.doRetrieveByKey(fa.getCitta(),fa.getStato(),fa.getCap(),fa.getVia()).getId()==-1)
				{
				    i.doSave(fa, user);
				}
			}
			if(sp.getId()==-1)
			{
				if(i.doRetrieveByKey(sp.getCitta(),sp.getStato(),sp.getCap(),sp.getVia()).getId()==-1)
				{
				    i.doSave(sp, user);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error 1:" + e.getMessage());
		}
		
		Ordine order = new Ordine();
		order.setConsegnato(false);
		order.setIdUtente(user.getId_utente());
		//
		try {
		IndirizzoDS iDS=new IndirizzoDS();
		Indirizzo spReg=iDS.doRetrieveByKey(sp.getCitta(),sp.getStato(),sp.getCap(),sp.getVia());
		Indirizzo faReg=iDS.doRetrieveByKey(fa.getCitta(),fa.getStato(),fa.getCap(),fa.getVia());
		order.setIdSpedizione(spReg.getId());
		order.setIdFatturazione(faReg.getId());
		} catch (SQLException e) {
			System.out.println("Error Adresse:" + e.getMessage());
		}
		
		order.setNote(request.getParameter("note"));
		//
		order.setIdPagamento(mp.getNumero());
		//
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
		request.setAttribute("ordine", order);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/acquistoEffettuato.jsp");
		dispatcher.forward(request, response);

	}

}
