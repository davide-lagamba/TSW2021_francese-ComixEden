package model;

import java.io.Serializable;

public class Indirizzo implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String citta;
	private String stato;
	private String cap;
	private String via;
	
	public Indirizzo() {
		id=-1;
		citta="";
		stato="";
		cap="";
		via="";
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Indirizzo other = (Indirizzo) obj;
		
		if (!(id==other.id))
			return false;
		
		if (!citta.equals(other.citta))
			return false;
		
		if (!stato.equals(other.stato))
			return false;
		
	    if (!cap.equals(other.cap))
			return false;
		
		if (!via.equals(other.via))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "Indirizzo [id=" + id + ", citta=" + citta + ", stato=" + stato + ", cap=" + cap + ", via=" + via + "]";
	}
	
	

}
