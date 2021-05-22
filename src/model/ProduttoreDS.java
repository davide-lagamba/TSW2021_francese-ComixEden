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

public class ProduttoreDS {
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/comixedendb");
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "produttore";

	public synchronized void doSave(Produttore producer) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProduttoreDS.TABLE_NAME
				+ " (id_produttore, partita_iva, nome) VALUES (?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, producer.getId_produttore());
			preparedStatement.setString(2, producer.getPartita_iva());
			preparedStatement.setString(3, producer.getNome());

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

	public synchronized Produttore doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Produttore bean = new Produttore();

		String selectSQL = "SELECT * FROM " + ProduttoreDS.TABLE_NAME + " WHERE id_Produttore = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId_produttore(rs.getInt("id_produttore"));
				bean.setPartita_iva(rs.getString("partita_iva"));
				bean.setNome(rs.getString("nome"));
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

		String deleteSQL = "DELETE FROM " + ProduttoreDS.TABLE_NAME + " WHERE id_produttore = ?";

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

	public synchronized Collection<Produttore> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Produttore> products = new LinkedList<Produttore>();

		String selectSQL = "SELECT * FROM " + ProduttoreDS.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Produttore bean = new Produttore();
				bean.setId_produttore(rs.getInt("id_produttore"));
				bean.setPartita_iva(rs.getString("partita_iva"));
				bean.setNome(rs.getString("nome"));
				products.add(bean);
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
		return products;
	}

	public static DataSource getDs() {
		return ds;
	}

	public static void setDs(DataSource ds) {
		ProduttoreDS.ds = ds;
	}

	public static String getTableName() {
		return TABLE_NAME;
	}
	
	public synchronized int getNewId() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;


		String selectSQL = "select max(id_produttore)  from "+ ProduttoreDS.TABLE_NAME;
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
	
	public synchronized Produttore doRetrieveByName(String nome) throws SQLException {

		PreparedStatement preparedStatement = null;

		Produttore bean = new Produttore();

		String selectSQL = "SELECT * FROM " + ProduttoreDS.TABLE_NAME + " WHERE nome = ?";

		try (Connection connection = ds.getConnection()){
			
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId_produttore(rs.getInt("id_produttore"));
				bean.setPartita_iva(rs.getString("partita_iva"));
				bean.setNome(rs.getString("nome"));
			}

		
		}
		return bean;
	}
	
	public synchronized void doSaveSenzaIva(Produttore producer) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProduttoreDS.TABLE_NAME
				+ " (nome) VALUES (?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, producer.getNome());

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
	
	
}
