package model;

import java.io.Serializable;

public class Immagine implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nome;
	private int idProdotto;
	private boolean copertina;
	
	public Immagine() {
		nome="";
		idProdotto=-1;
		copertina=false;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public boolean isCopertina() {
		return copertina;
	}

	public void setCopertina(boolean copertina) {
		this.copertina = copertina;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (copertina ? 1231 : 1237);
		result = prime * result + idProdotto;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Immagine other = (Immagine) obj;
		if (copertina != other.copertina)
			return false;
		if (idProdotto != other.idProdotto)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Immagine [nome=" + nome + ", idProdotto=" + idProdotto + ", copertina=" + copertina + "]";
	}
	
	

}
