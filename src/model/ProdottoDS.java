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




public class ProdottoDS {

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
	
	private static final String TABLE_NAME = "prodotto";

	
	public synchronized void doSave(Prodotto product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProdottoDS.TABLE_NAME
				+ " (prezzo_base, descrizione, stato, peso, pagine, autori, lingua, data, disponibilita, sconto, colore_stampa, iva, score_medio, id_produttore, id_categoria, nome) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setDouble(1, product.getPrezzoBase());
			preparedStatement.setString(2, product.getDescrizione());
			preparedStatement.setString(3, product.getStato());
			preparedStatement.setDouble(4, product.getPeso());
			if(product.getPagine()==0) {
				preparedStatement.setNull(5, Types.INTEGER);
			}else {
			preparedStatement.setInt(5, product.getPagine());}
			if(product.getAutori().equals("")) {
				preparedStatement.setNull(6, Types.VARCHAR);
			}else {
			preparedStatement.setString(6, product.getAutori());}
			preparedStatement.setString(7, product.getLingua());
			
			preparedStatement.setDate(8, product.getData());
			preparedStatement.setInt(9, product.getDisponibilita());
			preparedStatement.setInt(10, product.getSconto());
			preparedStatement.setString(11, product.getColoreStampa());
			preparedStatement.setDouble(12, product.getIva());
			if(product.getScoreMedio()==-1) {
				preparedStatement.setNull(13, Types.DOUBLE);
			}else {
			preparedStatement.setDouble(13, product.getScoreMedio());}
			if(product.getIdProduttore()==-1) {
				preparedStatement.setNull(14, Types.INTEGER);
			}else {
			preparedStatement.setInt(14, product.getIdProduttore());}
			if(product.getIdCategoria()==-1) {
				preparedStatement.setNull(15, Types.INTEGER);
			}
			preparedStatement.setInt(15, product.getIdCategoria());
			preparedStatement.setString(16, product.getNome());

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

	
	public synchronized Prodotto doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Prodotto bean = new Prodotto();

		String selectSQL = "SELECT * FROM " + ProdottoDS.TABLE_NAME + " WHERE id_prodotto = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("id_prodotto"));
				bean.setPrezzoBase(rs.getDouble("prezzo_base"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setStato(rs.getString("stato"));
				bean.setPeso(rs.getDouble("peso"));
				bean.setPagine(rs.getInt("pagine"));
				bean.setAutori(rs.getString("autori"));
				bean.setLingua(rs.getString("lingua"));
				bean.setData(rs.getDate("data"));
				bean.setDisponibilita(rs.getInt("disponibilita"));
				bean.setSconto(rs.getInt("sconto"));
				bean.setColoreStampa(rs.getString("colore_stampa"));
				bean.setIva(rs.getDouble("iva"));
				bean.setIdProduttore(rs.getInt("id_produttore"));
				bean.setIdCategoria(rs.getInt("id_categoria"));
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

		String deleteSQL = "DELETE FROM " + ProdottoDS.TABLE_NAME + " WHERE id_prodotto = ?";

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
	
	public synchronized Collection<Prodotto> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Prodotto> products = new LinkedList<Prodotto>();

		String selectSQL = "SELECT * FROM " + ProdottoDS.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Prodotto bean = new Prodotto();
				bean.setId(rs.getInt("id_prodotto"));
				bean.setPrezzoBase(rs.getDouble("prezzo_base"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setStato(rs.getString("stato"));
				bean.setPeso(rs.getDouble("peso"));
				bean.setPagine(rs.getInt("pagine"));
				bean.setAutori(rs.getString("autori"));
				bean.setLingua(rs.getString("lingua"));
				bean.setData(rs.getDate("data"));
				bean.setDisponibilita(rs.getInt("disponibilita"));
				bean.setSconto(rs.getInt("sconto"));
				bean.setColoreStampa(rs.getString("colore_stampa"));
				bean.setIva(rs.getDouble("iva"));
				bean.setIdProduttore(rs.getInt("id_produttore"));
				bean.setIdCategoria(rs.getInt("id_categoria"));
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
	
	public synchronized void removeDisponibilita(int id, int q) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;


		String selectSQL = "UPDATE " + ProdottoDS.TABLE_NAME + " SET disponibilita=disponibilita-? WHERE id_prodotto = ?" ;
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, q);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
	}
	
	public synchronized Collection<Prodotto> doRetrieveAllPrezzoTot() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Prodotto> products = new LinkedList<Prodotto>();

		String selectSQL = "SELECT * FROM " + ProdottoDS.TABLE_NAME;

	
			selectSQL += " ORDER BY (((prezzo_base*(100+iva))/100)*(100-sconto))/100";
		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Prodotto bean = new Prodotto();
				bean.setId(rs.getInt("id_prodotto"));
				bean.setPrezzoBase(rs.getDouble("prezzo_base"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setStato(rs.getString("stato"));
				bean.setPeso(rs.getDouble("peso"));
				bean.setPagine(rs.getInt("pagine"));
				bean.setAutori(rs.getString("autori"));
				bean.setLingua(rs.getString("lingua"));
				bean.setData(rs.getDate("data"));
				bean.setDisponibilita(rs.getInt("disponibilita"));
				bean.setSconto(rs.getInt("sconto"));
				bean.setColoreStampa(rs.getString("colore_stampa"));
				bean.setIva(rs.getDouble("iva"));
				bean.setIdProduttore(rs.getInt("id_produttore"));
				bean.setIdCategoria(rs.getInt("id_categoria"));
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
		ProdottoDS.ds = ds;
	}


	public static String getTableName() {
		return TABLE_NAME;
	}
}
