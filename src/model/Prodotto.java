package model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;



public class Prodotto implements Serializable{

	private static final long serialVersionUID = 1L;
	int id;
	double prezzoBase;
	String descrizione;
	String stato;
	double peso;
	int pagine;
	String autori;
	Date data;
	String lingua;
	int disponibilita;
	int sconto;
	String coloreStampa;
	double iva;
	double scoreMedio;
	int idProduttore;
	int idCategoria;
	String nome;
	
	public Prodotto() {
		id=-1;
		prezzoBase=0;
		descrizione="";
		stato="";
		peso=0;
		pagine=0;
		autori="";
		lingua="";
		data= null;
		disponibilita=0;
		sconto=0;
		coloreStampa="";
		iva=0;
		scoreMedio=-1;
		idProduttore=-1;
		idCategoria=-1;
		nome="";
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrezzoBase() {
		return prezzoBase;
	}
	public void setPrezzoBase(double prezzoBase) {
		this.prezzoBase = prezzoBase;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public int getPagine() {
		return pagine;
	}
	public void setPagine(int pagine) {
		this.pagine = pagine;
	}
	public String getAutori() {
		return autori;
	}
	public void setAutori(String autori) {
		this.autori = autori;
	}

	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getDisponibilita() {
		return disponibilita;
	}
	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}
	public int getSconto() {
		return sconto;
	}
	public void setSconto(int sconto) {
		this.sconto = sconto;
	}
	public String getColoreStampa() {
		return coloreStampa;
	}
	public void setColoreStampa(String coloreStampa) {
		this.coloreStampa = coloreStampa;
	}
	public String getLingua() {
		return lingua;
	}
	public void setLingua(String lingua) {
		this.lingua = lingua;
	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public double getScoreMedio() {
		return scoreMedio;
	}
	public void setScoreMedio(double scoreMedio) {
		this.scoreMedio = scoreMedio;
	}
	public int getIdProduttore() {
		return idProduttore;
	}
	public void setIdProduttore(int idProduttore) {
		this.idProduttore = idProduttore;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	 public double getPrezzoTot() {
		
		double tot=(((getPrezzoBase()*(100+getIva()))/100)*(100-getSconto()))/100;
	    return (tot);
	  }
	 
	 public String getPrezzoTotString() {
		 
	    return String.format("%.2f", getPrezzoTot());
	  }
	 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autori == null) ? 0 : autori.hashCode());
		result = prime * result + ((coloreStampa == null) ? 0 : coloreStampa.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + disponibilita;
		result = prime * result + id;
		result = prime * result + idCategoria;
		result = prime * result + idProduttore;
		long temp;
		temp = Double.doubleToLongBits(iva);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((lingua == null) ? 0 : lingua.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + pagine;
		temp = Double.doubleToLongBits(peso);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(prezzoBase);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + sconto;
		temp = Double.doubleToLongBits(scoreMedio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((stato == null) ? 0 : stato.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prodotto other = (Prodotto) obj;
		if (autori == null) {
			if (other.autori != null)
				return false;
		} else if (!autori.equals(other.autori))
			return false;
		if (coloreStampa == null) {
			if (other.coloreStampa != null)
				return false;
		} else if (!coloreStampa.equals(other.coloreStampa))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (disponibilita != other.disponibilita)
			return false;
		if (id != other.id)
			return false;
		if (idCategoria != other.idCategoria)
			return false;
		if (idProduttore != other.idProduttore)
			return false;
		
		if (Double.doubleToLongBits(iva) != Double.doubleToLongBits(other.iva))
			return false;
		if (lingua == null) {
			if (other.lingua != null)
				return false;
		} else if (!lingua.equals(other.lingua))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pagine != other.pagine)
			return false;
		if (Double.doubleToLongBits(peso) != Double.doubleToLongBits(other.peso))
			return false;
		if (Double.doubleToLongBits(prezzoBase) != Double.doubleToLongBits(other.prezzoBase))
			return false;
		if (sconto != other.sconto)
			return false;
		if (Double.doubleToLongBits(scoreMedio) != Double.doubleToLongBits(other.scoreMedio))
			return false;
		if (stato == null) {
			if (other.stato != null)
				return false;
		} else if (!stato.equals(other.stato))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Prodotto [id=" + id + ", prezzoBase=" + prezzoBase + ", descrizione=" + descrizione + ", stato=" + stato
				+ ", peso=" + peso + ", pagine=" + pagine + ", autori=" + autori +  ", data=" + data
				+ ", lingua=" + lingua + ", disponibilita=" + disponibilita + ", sconto=" + sconto + ", coloreStampa="
				+ coloreStampa + ", iva=" + iva + ", scoreMedio=" + scoreMedio + ", idProduttore=" + idProduttore
				+ ", idCategoria=" + idCategoria + ", nome=" + nome + ", getId()=" + getId() + ", getPrezzoBase()="
				+ getPrezzoBase() + ", getDescrizione()=" + getDescrizione() + ", getStato()=" + getStato()
				+ ", getPeso()=" + getPeso() + ", getPagine()=" + getPagine() + ", getAutori()=" + getAutori()
				 + ", getData()=" + getData() + ", getDisponibilita()=" + getDisponibilita()
				+ ", getSconto()=" + getSconto() + ", getColoreStampa()=" + getColoreStampa() + ", getLingua()="
				+ getLingua() + ", getIva()=" + getIva() + ", getScoreMedio()=" + getScoreMedio()
				+ ", getIdProduttore()=" + getIdProduttore() + ", getIdCategoria()=" + getIdCategoria() + ", getNome()="
				+ getNome() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
