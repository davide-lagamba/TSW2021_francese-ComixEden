package model;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrdineDS {

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

	private static final String TABLE_NAME = "ordine";

	public synchronized void doSave(Ordine ordine) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + OrdineDS.TABLE_NAME + " (id_utente,id_spedizione,id_fatturazione,note,id_pagamento,"
				+ "costo_spedizione,prezzo_totale,quantita,data,consegnato) VALUES (?,?,?,?,?,?,?,?,?,?)";

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(true);
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, ordine.getIdUtente());
			preparedStatement.setInt(2, ordine.getIdSpedizione());
			preparedStatement.setInt(3, ordine.getIdFatturazione());
			preparedStatement.setString(4, ordine.getNote());
			preparedStatement.setInt(5, ordine.getIdPagamento());
			preparedStatement.setDouble(6, ordine.getCostoSpedizione());
			preparedStatement.setDouble(7, ordine.getPrezzoTotale());
			preparedStatement.setInt(8, ordine.getQuantita());
			preparedStatement.setDate(9, ordine.getData());
			preparedStatement.setBoolean(10, ordine.getConsegnato());

			preparedStatement.executeUpdate();

		//	connection.commit();
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

	public synchronized Ordine doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Ordine bean = new Ordine();

		String selectSQL = "SELECT * FROM " + OrdineDS.TABLE_NAME + " WHERE Id_ordine = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setConsegnato(rs.getBoolean("consegnato"));
				bean.setCostoSpedizione(rs.getDouble("costo_spedizione"));
				bean.setData(rs.getDate("data"));
				bean.setIdFatturazione(rs.getInt("id_categoria"));
				bean.setIdPagamento(rs.getInt("id_pagamento"));
				bean.setIdSpedizione(rs.getInt("id_spedizione"));
				bean.setIdUtente(rs.getInt("id_utente"));
				bean.setNote(rs.getString("note"));
				bean.setPrezzoTotale(rs.getDouble("prezzo_totale"));
				bean.setQuantita(rs.getInt("quantita"));
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

		String deleteSQL = "DELETE FROM " + OrdineDS.TABLE_NAME + " WHERE Id_categoria = ?";

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

	public synchronized Collection<Ordine> doRetrieveAll(int id, String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Ordine> ordini = new LinkedList<Ordine>();

		String selectSQL = "SELECT * FROM " + OrdineDS.TABLE_NAME +" WHERE id_utente= ?";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Ordine bean = new Ordine();
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setIdUtente(rs.getInt("id_utente"));
				bean.setIdSpedizione(rs.getInt("id_spedizione"));
				bean.setIdFatturazione(rs.getInt("id_fatturazione"));
				bean.setIdPagamento(rs.getInt("id_pagamento"));
				bean.setNote(rs.getString("note"));
				bean.setCostoSpedizione(rs.getDouble("costo_spedizione"));
				bean.setPrezzoTotale(rs.getDouble("prezzo_totale"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setData(rs.getDate("data"));
				bean.setConsegnato(rs.getBoolean("consegnato"));
				ordini.add(bean);
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
		return ordini;
	}
	public synchronized Collection<Ordine> doRetrieveAllByEmail(String email, String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Ordine> ordini = new LinkedList<Ordine>();
        
		String selectSQL = "SELECT * FROM " + OrdineDS.TABLE_NAME +" join utente on utente.id_utente=ordine.id_utente"
				+ " WHERE utente.email=?";
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Ordine bean = new Ordine();
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setIdUtente(rs.getInt("id_utente"));
				bean.setIdSpedizione(rs.getInt("id_spedizione"));
				bean.setIdFatturazione(rs.getInt("id_fatturazione"));
				bean.setIdPagamento(rs.getInt("id_pagamento"));
				bean.setNote(rs.getString("note"));
				bean.setCostoSpedizione(rs.getDouble("costo_spedizione"));
				bean.setPrezzoTotale(rs.getDouble("prezzo_totale"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setData(rs.getDate("data"));
				bean.setConsegnato(rs.getBoolean("consegnato"));
				ordini.add(bean);
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
		return ordini;
	}
	public synchronized Collection<Ordine> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Ordine> ordini = new LinkedList<Ordine>();

		String selectSQL = "SELECT * FROM " + OrdineDS.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Ordine bean = new Ordine();
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setIdUtente(rs.getInt("id_utente"));
				bean.setIdSpedizione(rs.getInt("id_spedizione"));
				bean.setIdFatturazione(rs.getInt("id_fatturazione"));
				bean.setIdPagamento(rs.getInt("id_pagamento"));
				bean.setNote(rs.getString("note"));
				bean.setCostoSpedizione(rs.getDouble("costo_spedizione"));
				bean.setPrezzoTotale(rs.getDouble("prezzo_totale"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setData(rs.getDate("data"));
				bean.setConsegnato(rs.getBoolean("consegnato"));
				ordini.add(bean);
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
		return ordini;
	}
	
	public synchronized Collection<Ordine> doRetrieveAllByDate(String order,String inizio,String fine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Ordine> ordini = new LinkedList<Ordine>();

		String selectSQL = "SELECT * FROM " + OrdineDS.TABLE_NAME + " WHERE data BETWEEN ? AND ?";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, inizio);
			preparedStatement.setString(2, fine);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Ordine bean = new Ordine();
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setIdUtente(rs.getInt("id_utente"));
				bean.setIdSpedizione(rs.getInt("id_spedizione"));
				bean.setIdFatturazione(rs.getInt("id_fatturazione"));
				bean.setIdPagamento(rs.getInt("id_pagamento"));
				bean.setNote(rs.getString("note"));
				bean.setCostoSpedizione(rs.getDouble("costo_spedizione"));
				bean.setPrezzoTotale(rs.getDouble("prezzo_totale"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setData(rs.getDate("data"));
				bean.setConsegnato(rs.getBoolean("consegnato"));
				ordini.add(bean);
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
		return ordini;
	}
	
	public synchronized int getNewId() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Ordine> ordini = new LinkedList<Ordine>();

		String selectSQL = "select max(id_ordine)  from "+ OrdineDS.TABLE_NAME;
		int result=-1;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
			result=rs.getInt(1);
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
		return result;
	}

}
