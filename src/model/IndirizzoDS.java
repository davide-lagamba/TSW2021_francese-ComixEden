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

public class IndirizzoDS {

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

	private static final String TABLE_NAME = "indirizzo";

	public synchronized void doSave(Indirizzo indirizzo) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + IndirizzoDS.TABLE_NAME + " (STATO,CITTA,CAP,VIA) "
				+ "VALUES (?,?,?,?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, indirizzo.getStato());
			preparedStatement.setString(2, indirizzo.getCitta());
			preparedStatement.setString(3, indirizzo.getCap());
			preparedStatement.setString(4, indirizzo.getVia());
			preparedStatement.executeUpdate();

			connection.commit();
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
	public synchronized void doSave(Indirizzo indirizzo,Utente user) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatementU = null;
		PreparedStatement preparedStatementR = null;
		String insertSQL = "INSERT INTO " + IndirizzoDS.TABLE_NAME + " (STATO,CITTA,CAP,VIA) "
				+ "VALUES (?,?,?,?)";
		String selectSQL = "SELECT * FROM locazione WHERE id_utente = ? AND id_indirizzo = ?";
		System.out.println(insertSQL);
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, indirizzo.getStato());
			preparedStatement.setString(2, indirizzo.getCitta());
			preparedStatement.setString(3, indirizzo.getCap());
			preparedStatement.setString(4, indirizzo.getVia());
			preparedStatement.executeUpdate();
	
			Indirizzo saved=doRetrieveByKey(indirizzo.getCitta(),
					indirizzo.getStato(),indirizzo.getCap(),indirizzo.getVia());
			
			preparedStatementR = connection.prepareStatement(selectSQL);
			preparedStatementR.setInt(1, user.getId_utente());
			preparedStatementR.setInt(2, saved.getId());
			
			ResultSet rs = preparedStatementR.executeQuery();
			if(!rs.next())
			{			
				String insertUtilizzoSQL = "INSERT INTO locazione (id_utente,id_indirizzo) "
						+ "VALUES (?,?)";
				preparedStatementU = connection.prepareStatement(insertUtilizzoSQL);
				preparedStatementU.setInt(1, user.getId_utente());
				preparedStatementU.setInt(2, saved.getId());
				preparedStatementU.executeUpdate();
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
	}

	public synchronized Indirizzo doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Indirizzo bean = new Indirizzo();

		String selectSQL = "SELECT * FROM " + IndirizzoDS.TABLE_NAME + " WHERE Id_indirizzo = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("ID_INDIRIZZO"));
				bean.setCitta(rs.getString("CITTA"));
				bean.setStato(rs.getString("STATO"));
				bean.setCap(rs.getString("CAP"));
				bean.setVia(rs.getString("VIA"));
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
	//
	public synchronized Indirizzo doRetrieveByKey(Utente user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Indirizzo bean = new Indirizzo();

		String selectSQL = "SELECT indirizzo.* FROM " + IndirizzoDS.TABLE_NAME + ",locazione WHERE "
				+ "locazione.id_indirizzo = Indirizzo.id_indirizzo AND locazione.id_utente = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, user.getId_utente());

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				bean.setId(rs.getInt("ID_INDIRIZZO"));
				bean.setCitta(rs.getString("CITTA"));
				bean.setStato(rs.getString("STATO"));
				bean.setCap(rs.getString("CAP"));
				bean.setVia(rs.getString("VIA"));
				
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
	//
	public synchronized Indirizzo doRetrieveByKey(String citta,String stato,String cap,String via) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Indirizzo bean = new Indirizzo();

		String selectSQL = "SELECT * FROM " + IndirizzoDS.TABLE_NAME + " WHERE "
				+ "citta = ? AND stato = ? AND cap = ? AND via = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, citta);
			preparedStatement.setString(2, stato);
			preparedStatement.setString(3, cap);
			preparedStatement.setString(4, via);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("ID_INDIRIZZO"));
				bean.setCitta(rs.getString("CITTA"));
				bean.setStato(rs.getString("STATO"));
				bean.setCap(rs.getString("CAP"));
				bean.setVia(rs.getString("VIA"));
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

		String deleteSQL = "DELETE FROM " + IndirizzoDS.TABLE_NAME + " WHERE id_indirizzo = ?";

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

	public synchronized Collection<Indirizzo> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Indirizzo> indirizzi = new LinkedList<Indirizzo>();

		String selectSQL = "SELECT * FROM " + IndirizzoDS.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				Indirizzo bean = new Indirizzo();
				bean.setId(rs.getInt("ID_INDIRIZZO"));
				bean.setCitta(rs.getString("CITTA"));
				bean.setStato(rs.getString("STATO"));
				bean.setCap(rs.getString("CAP"));
				bean.setVia(rs.getString("VIA"));
				
				indirizzi.add(bean);
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
		return indirizzi;
	}

}
