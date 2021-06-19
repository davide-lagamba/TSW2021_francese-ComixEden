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

public class MetodoPagamentoDS {

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

	private static final String TABLE_NAME = "metodo_pagamento";

	public synchronized void doSave(MetodoPagamento mp) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + MetodoPagamentoDS.TABLE_NAME + " (NUMERO,SCADENZA,CIRCUITO) "
				+ "VALUES (?,?,?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, mp.getNumero());
			preparedStatement.setDate(2, mp.getScadenza());
			preparedStatement.setString(3, mp.getCircuito());
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
	public synchronized void doSave(MetodoPagamento mp,Utente user) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatementR = null;
		PreparedStatement preparedStatementU = null;
		String insertSQL = "INSERT INTO " + MetodoPagamentoDS.TABLE_NAME + " (NUMERO,SCADENZA,CIRCUITO) "
				+ "VALUES (?,?,?)";
		String selectSQL = "SELECT * FROM utilizzo WHERE id_utente = ? AND numero_pagamento = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatementR = connection.prepareStatement(selectSQL);
			preparedStatementR.setInt(1, user.getId_utente());
			preparedStatementR.setString(2, mp.getNumero());
			ResultSet rs = preparedStatementR.executeQuery();
			preparedStatement.setString(1, mp.getNumero());
			preparedStatement.setDate(2, mp.getScadenza());
			preparedStatement.setString(3, mp.getCircuito());
			preparedStatement.executeUpdate();
			if(!rs.next())
			{			
				String insertUtilizzoSQL = "INSERT INTO utilizzo (id_utente,numero_pagamento) "
						+ "VALUES (?,?)";
				preparedStatementU = connection.prepareStatement(insertUtilizzoSQL);
				preparedStatementU.setInt(1, user.getId_utente());
				preparedStatementU.setString(2, mp.getNumero());
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

	public synchronized MetodoPagamento doRetrieveByKey(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		MetodoPagamento bean = new MetodoPagamento();

		String selectSQL = "SELECT * FROM " + MetodoPagamentoDS.TABLE_NAME + " WHERE numero = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setNumero(rs.getString("NUMERO"));
				bean.setScadenza(rs.getDate("SCADENZA"));
				bean.setCircuito(rs.getString("CIRCUITO"));
			
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
	public synchronized MetodoPagamento doRetrieveByKey(Utente user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		MetodoPagamento bean = new MetodoPagamento();

		String selectSQL = "SELECT metodo_pagamento.* FROM " + MetodoPagamentoDS.TABLE_NAME + ",utilizzo WHERE "
				+ "utilizzo.numero_pagamento = metodo_pagamento.numero AND utilizzo.id_utente = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, user.getId_utente());

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setNumero(rs.getString("NUMERO"));
				bean.setScadenza(rs.getDate("SCADENZA"));
				bean.setCircuito(rs.getString("CIRCUITO"));
			
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

	public synchronized boolean doDelete(String numero) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + MetodoPagamentoDS.TABLE_NAME + " WHERE numero = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, numero);
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

	public synchronized Collection<MetodoPagamento> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<MetodoPagamento> pagamenti = new LinkedList<MetodoPagamento>();

		String selectSQL = "SELECT * FROM " + MetodoPagamentoDS.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				MetodoPagamento bean = new MetodoPagamento();
				bean.setNumero(rs.getString("NUMERO"));
				bean.setCircuito(rs.getString("CIRCUITO"));
				bean.setScadenza(rs.getDate("SCADENZA"));				
				pagamenti.add(bean);
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
		return pagamenti;
	}

}

