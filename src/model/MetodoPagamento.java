package model;

import java.io.Serializable;
import java.sql.Date;

public class MetodoPagamento implements Serializable{

	private static final long serialVersionUID = 1L;
	private String numero;
	private Date scadenza;
	private String circuito;

	public MetodoPagamento() {
		numero=null;
	    scadenza=null;
		circuito=null;
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getCircuito() {
		return circuito;
	}

	public void setCircuito(String circuito) {
		this.circuito = circuito;
	}
	
	public Date getScadenza() {
		return scadenza;
	}

	public void setScadenza(Date scadenza) {
		this.scadenza = scadenza;
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
		MetodoPagamento other = (MetodoPagamento) obj;
		
		if (!(numero.equals(other.numero)))
			return false;
		
		if (!circuito.equals(other.circuito))
			return false;
		
		if (!scadenza.equals(other.scadenza))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "MetodoPagamento [numero=" + numero + ", circuito=" + circuito + ", scadenza=" + scadenza + "]";
	}
	
	

}

