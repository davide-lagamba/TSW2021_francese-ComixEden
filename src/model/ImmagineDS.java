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

public class ImmagineDS {

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
	
	private static final String TABLE_NAME = "immagine";

	
	public synchronized void doSave(Immagine immagine) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ImmagineDS.TABLE_NAME
				+ " (nome, id_prodotto, copertina) VALUES (?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, immagine.getNome());
			preparedStatement.setInt(2, immagine.getIdProdotto());
			preparedStatement.setBoolean(3, immagine.isCopertina());
		
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

	
	public synchronized Immagine doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Immagine bean = new Immagine();

		String selectSQL = "SELECT * FROM " + ImmagineDS.TABLE_NAME + " WHERE id_prodotto = ? and copertina=true";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setNome(rs.getString("nome"));
				bean.setIdProdotto(rs.getInt("id_prodotto"));
				bean.setCopertina(rs.getBoolean("copertina"));
				
				
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
	

	public synchronized boolean doDelete(String nome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ImmagineDS.TABLE_NAME + " WHERE nome = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, nome);

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
	
	public synchronized Collection<Immagine> doRetrieveAll(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Immagine> immagini = new LinkedList<Immagine>();

		String selectSQL = "SELECT * FROM " + ImmagineDS.TABLE_NAME +" WHERE id_prodotto= ?";

		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Immagine bean = new Immagine();
				bean.setNome(rs.getString("nome"));
				bean.setIdProdotto(rs.getInt("id_prodotto"));
				bean.setCopertina(rs.getBoolean("copertina"));
				immagini.add(bean);
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
		return immagini;
	}
	
	
	public static DataSource getDs() {
		return ds;
	}


	public static void setDs(DataSource ds) {
		ImmagineDS.ds = ds;
	}


	public static String getTableName() {
		return TABLE_NAME;
	}
}

