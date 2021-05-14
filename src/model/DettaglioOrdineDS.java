package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DettaglioOrdineDS {
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
	
	private static final String TABLE_NAME = "dettaglio_ordine";

	
	public synchronized void doSave(DettaglioOrdine dettaglio) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + DettaglioOrdineDS.TABLE_NAME
				+ " (id_prodotto, id_ordine, prezzo_tot, prezzo_singolo, quantita, iva, nome, sconto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(true);
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, dettaglio.getIdProdotto());
			preparedStatement.setInt(2, dettaglio.getIdOrdine());
			preparedStatement.setDouble(3, dettaglio.getPrezzoTotQuantita());
			preparedStatement.setDouble(4, dettaglio.getPrezzoSingolo());
			preparedStatement.setInt(5, dettaglio.getNumItems());
			preparedStatement.setDouble(6, dettaglio.getIva());
			preparedStatement.setString(7, dettaglio.getNome());			
			preparedStatement.setDouble(8, dettaglio.getSconto());

			preparedStatement.executeUpdate();

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

	
	public synchronized DettaglioOrdine doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		DettaglioOrdine bean = new DettaglioOrdine();

		String selectSQL = "SELECT * FROM " + DettaglioOrdineDS.TABLE_NAME + " WHERE id_dettaglio = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdDettaglio(rs.getInt("id_dettaglio"));
				bean.setIdProdotto(rs.getInt("id_prodotto"));
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setPrezzoTot(rs.getDouble("prezzo_tot"));
				bean.setPrezzoSingolo(rs.getDouble("prezzo_singolo"));
				bean.setIva(rs.getDouble("iva"));
				bean.setNome(rs.getString("nome"));
				bean.setSconto(rs.getDouble("sconto"));
				bean.setNumItems(rs.getInt("quantita"));
				
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
	

	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + DettaglioOrdineDS.TABLE_NAME + " WHERE id_dettaglio = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

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
	
	public synchronized Collection<DettaglioOrdine> doRetrieveAllByUser(int id_utente, int idOrdine, String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<DettaglioOrdine> dettagli = new LinkedList<DettaglioOrdine>();

		String selectSQL = "select * from "+ DettaglioOrdineDS.TABLE_NAME+" join ordine on "+DettaglioOrdineDS.TABLE_NAME+".id_ordine = ordine.id_ordine where id_utente=? and "+DettaglioOrdineDS.TABLE_NAME+".id_ordine=?";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY "+DettaglioOrdineDS.TABLE_NAME+"." + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id_utente);
			preparedStatement.setInt(2, idOrdine);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				DettaglioOrdine bean = new DettaglioOrdine();
				bean.setIdDettaglio(rs.getInt("id_dettaglio"));
				bean.setIdProdotto(rs.getInt("id_prodotto"));
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setPrezzoTot(rs.getDouble("prezzo_tot"));
				bean.setPrezzoSingolo(rs.getDouble("prezzo_singolo"));
				bean.setIva(rs.getDouble("iva"));
				bean.setNome(rs.getString("nome"));
				bean.setSconto(rs.getDouble("sconto"));
				bean.setQuantita(rs.getInt("quantita"));
				dettagli.add(bean);
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
		return dettagli;
	}
	
	
	public static DataSource getDs() {
		return ds;
	}


	public static void setDs(DataSource ds) {
		DettaglioOrdineDS.ds = ds;
	}


	public static String getTableName() {
		return TABLE_NAME;
	}
}
