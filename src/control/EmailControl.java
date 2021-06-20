package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UtenteDS;
@WebServlet("/email")
public class EmailControl extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	           throws ServletException, IOException {
		
		String email= request.getParameter("email");
		boolean result=false;
		if(!email.equals("")) {
		try {
			result= new UtenteDS().checkEmail(email);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
}
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(""+result);}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 doGet(request, response);
	}
}
