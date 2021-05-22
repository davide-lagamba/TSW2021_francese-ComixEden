package control.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Carrello;
import model.ProdottoDS;
import model.Utente;

@WebServlet("/admin/view")
public class AdminControl  extends HttpServlet {
	
	public AdminControl() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utente user = (Utente) request.getSession().getAttribute("utente");

		if (user == null || !user.isAdmin()) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/nonAutenticato.jsp");
			dispatcher.forward(request, response);
			return;
		} else {
			String adminPage= request.getParameter("adminPage");
			if(adminPage==null || adminPage.equals("")) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/admin/adminView.jsp");
			dispatcher.forward(request, response);
			return;}
		}
	}
			


}
	

