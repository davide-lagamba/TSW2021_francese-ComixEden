package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UtenteDS {

	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/comixedendb");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "utente";

	public synchronized void doSave(Utente utente) throws SQLException {
		
		String email = utente.getEmail();
		
	    String searchQuery =
	            "select * from utente where email=?";
		    
	    
	   Connection con = null;
	   PreparedStatement prep = null;

		try {
		      //connect to DB 
			  con = ds.getConnection();
			  prep= con.prepareStatement(searchQuery);
			  prep.setString(1, email);
			  ResultSet rs=prep.executeQuery();	  
		      boolean more = rs.next();
		  	  Connection connection = null;
			  PreparedStatement preparedStatement = null;     
		      // se l'utente già esiste
		      if (more) 
		      {
		         System.out.println("attenzione, l'email è gia utilizzato da altri utenti");
		         utente.setRegistrazione(false);
		      } 
		      //se non esiste ancora
		      else if (!more) 
		      {
		    	try {
		    	utente.setRegistrazione(true);
				String insertSQL = "INSERT INTO " + UtenteDS.TABLE_NAME + " (nome,cognome,email,password,telefono,data_registrazione,admin) VALUES (?,?,?,?,?,?,?)";
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, utente.getNome());
				preparedStatement.setString(2, utente.getCognome());
				preparedStatement.setString(3, utente.getEmail());
				preparedStatement.setString(4, utente.getPassword());

				preparedStatement.setString(5, utente.getTelefono());
				preparedStatement.setString(6, utente.getData_registrazione().toString());
				preparedStatement.setBoolean(7, utente.isAdmin());
				preparedStatement.executeUpdate();
		      }
		     catch (Exception ex) 
				 {
				      System.out.println("registration failed: An Exception has occurred! " + ex);
				 } 
		    	finally {
					try {
						if (preparedStatement != null)
							preparedStatement.close();
					} finally {
						if (connection != null)
							connection.close();
					}
					    
		    	
		}
		}}
				 catch (Exception ex2) 
				   {
				      System.out.println("id già utilizzato" + ex2);
				   } 
					    
				   //some exception handling
				   finally 
				   {
					  try {
							if (prep != null)
								prep.close();
						} finally {
							if (con != null)
								con.close();
						}
				   }
		}
			

	public synchronized Utente doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Utente bean = new Utente();

		String selectSQL = "SELECT * FROM " + UtenteDS.TABLE_NAME + " WHERE Id_utente = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId_utente(rs.getInt("id_utente"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setData_registrazione(rs.getDate("data_registrazione"));
				bean.setAdmin(rs.getBoolean("admin"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}
	
	public synchronized Utente doRetrieveByKey(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Utente bean = new Utente();

		String selectSQL = "SELECT * FROM " + UtenteDS.TABLE_NAME + " WHERE email = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId_utente(rs.getInt("id_utente"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setData_registrazione(rs.getDate("data_registrazione"));
				bean.setAdmin(rs.getBoolean("admin"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}
	
	public static Utente doRetrieve(String email, String password) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		

		Utente bean = new Utente();

		 String searchQuery = "SELECT * FROM " + UtenteDS.TABLE_NAME + " WHERE email=? AND password=?"; 
	

		try {
			//connect to DB 
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(searchQuery);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			rs = preparedStatement.executeQuery();
			
			boolean more = rs.next();

			// if user does not exist set the isValid variable to false
			if (!more) {
				System.out.println("Utente non registrato");
				bean.setValid(false);
			}

			// if user exists set the isValid variable to true
			else if (more) {
				bean.setId_utente(rs.getInt("id_utente"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setData_registrazione(rs.getDate("data_registrazione"));
				bean.setAdmin(rs.getBoolean("admin"));
				bean.setValid(true);
			}
			

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}
	
	public synchronized boolean doDelete(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + UtenteDS.TABLE_NAME + " WHERE Id_utente = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, id);
			
			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	public synchronized boolean checkEmail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;


		String selectSQL = "SELECT email FROM " + UtenteDS.TABLE_NAME +" WHERE email = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			
			ResultSet result = preparedStatement.executeQuery();
			
			if(result.next()==false) {
				return true;
			}
			else
				return false;
				
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
	}
	
	public synchronized Collection<Utente> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Utente> categorie = new LinkedList<Utente>();

		String selectSQL = "SELECT * FROM " + UtenteDS.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Utente bean = new Utente();

				bean.setId_utente(rs.getInt("id_utente"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setData_registrazione(rs.getDate("data_registrazione"));
				bean.setAdmin(rs.getBoolean("admin"));
				
				categorie.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return categorie;
	}

	public static DataSource getDs() {
		return ds;
	}

	public static void setDs(DataSource ds) {
		UtenteDS.ds = ds;
	}

	public static String getTableName() {
		return TABLE_NAME;
	}
}
