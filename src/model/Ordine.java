package model;
import java.io.Serializable;
import java.sql.Date;

public class Ordine implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private int idOrdine;
	private int idUtente;
	private int idSpedizione;
	private int idFatturazione;
	private String note;
	private String idPagamento;
	private double costoSpedizione;
	private double prezzoTotale;
	private int quantita;
	private Date data;
	private boolean consegnato;

	public void setIdOrdine(int idOrdine) {
		this.idOrdine=idOrdine;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente=idUtente;
	}
	public void setIdSpedizione(int idSpedizione) {
		this.idSpedizione=idSpedizione;
	}
	public void setIdFatturazione(int idFatturazione) {
		this.idFatturazione=idFatturazione;
	}
	public void setNote(String note) {
		this.note=note;
	}
	public void setIdPagamento(String idPagamento) {
		this.idPagamento=idPagamento;
	}
	public void setCostoSpedizione(double costoSpedizione) {
		this.costoSpedizione=costoSpedizione;
	}
	public void setPrezzoTotale(double prezzoTotale) {
		this.prezzoTotale=prezzoTotale;
	}
	public void setQuantita(int quantita) {
		this.quantita=quantita;
	}
	public void setData(Date data) {
		this.data=data;
	}
	public void setConsegnato(boolean consegnato) {
		this.consegnato=consegnato;
	}
	public int getIdOrdine() {
		return idOrdine;
	}
	public int getIdUtente() {
		return idUtente;
	}
	public int getIdSpedizione() {
		return idSpedizione;
	}
	public int getIdFatturazione() {
		return idFatturazione;
	}
	public String getNote() {
		return note;
	}
	public String getIdPagamento() {
		return idPagamento;
	}
	public double getCostoSpedizione() {
		return costoSpedizione;
	}
	public double getPrezzoTotale() {
		return prezzoTotale;
	}
	
	public String getPrezzoTotaleString() {
		  return String.format("%.2f", getPrezzoTotale()) + "�";
	}
	public int getQuantita() {
		return quantita;
	}
	public Date getData() {
		return data;
	}
	public boolean getConsegnato() {
		return consegnato;
	}

	@Override
	public String toString() {
		return "idOrdine " + idOrdine + " idUtente " + idUtente + " idSpedizione " + idSpedizione + 
				" idFatturazione " + idFatturazione + " note " + note + " idPagamento " + idPagamento + 
				" costoSpedizione " + costoSpedizione + " prezzoTotale " + prezzoTotale + " quanita " + quantita + 
				" data " + data +" consegnato " + consegnato;
	}
}