package model;

import java.io.Serializable;

/** Associates a catalog Item with a specific order by
 *  keeping track of the number ordered and the total price.
 *  Also provides some convenience methods to get at the
 *  CatalogItem data without extracting the CatalogItem
 *  separately.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class DettaglioOrdine implements Serializable{

	private static final long serialVersionUID = 1L;
private Prodotto item;
  private int numItems;
  private int idProdotto;
  private int idDettaglio;
  private int idOrdine;
  private double prezzoTot;
  private double prezzoSingolo;
  private double iva;
  private String nome;
  private double sconto;
  private int quantita;
  
  public DettaglioOrdine() {
	  item=null;
	   numItems=-1;
	    idProdotto=-1;
	    idDettaglio=-1;
	    idOrdine=-1;
	    prezzoTot=-1;
	    prezzoSingolo=-1;
	    iva=-1;
	    nome="";
	    sconto=-1;
	    quantita=-1;
	  }

  public DettaglioOrdine(Prodotto item) {
    setItem(item);
    setNumItems(1);
    idProdotto=item.getId();
    idDettaglio=-1;
    idOrdine=-1;
    prezzoTot=item.getPrezzoTot()*numItems;
    prezzoSingolo=item.getPrezzoBase();
    iva=item.getIva();
    nome=item.getNome();
    sconto=item.getSconto();
  }

  public Prodotto getItem() {
    return(item);
  }

  protected void setItem(Prodotto item) {
    this.item = item;
  }

  public int getItemId() {
    return(getItem().getId());
  }

  public String getNome() {
    return(getItem().getNome());
  }

  public String getNomeDettaglio() {
	  return nome;
  }
  public String getDescrizione() {
    return(getItem().getDescrizione());
  }

  public double getPrezzoTot() {
	  Double prezzo=getItem().getPrezzoBase();
	  int sconto=getItem().getSconto();
	  Double iva=getItem().getIva();
	  Double tot= (prezzo*(100+iva))/100;
	  tot=(tot*(100-sconto))/100;
    return(tot);
  }
  
  public double getPrezzoTotDettaglio() {
	
	  Double tot= (prezzoSingolo*(100+iva))/100;
	  tot=(tot*(100-sconto))/100;
    return(tot);
  }
  
  public double getPrezzoTotQuantita() {
	  Double prezzo=getItem().getPrezzoBase();
	  int sconto=getItem().getSconto();
	  Double iva=getItem().getIva();
	  Double tot= (prezzo*(100+iva))/100;
	  tot=(tot*(100-sconto))/100;
    return(tot*numItems);
  }
  
  public String getPrezzoTotString() {
	  Double tot=(getPrezzoTot()*getNumItems());
	  return String.format("%.2f", tot) + "€";
  }
  
  public int getNumItems() {
    return(numItems);
  }

  public void setNumItems(int n) {
	  if(n<=item.getDisponibilita()) {
    this.numItems = n;}
  }

  public void incrementNumItems() {
	  if(numItems+1<=item.getDisponibilita()) {
    setNumItems(getNumItems() + 1);}
  }

  public void cancelOrder() {
    setNumItems(0);
  }

  public double getTotalCost() {
    return(getNumItems() * getPrezzoTot());
  }

public int getIdProdotto() {
	return idProdotto;
}

public void setIdProdotto(int idProdotto) {
	this.idProdotto = idProdotto;
}

public int getIdDettaglio() {
	return idDettaglio;
}

public void setIdDettaglio(int idDettaglio) {
	this.idDettaglio = idDettaglio;
}

public int getIdOrdine() {
	return idOrdine;
}

public void setIdOrdine(int idOrdine) {
	this.idOrdine = idOrdine;
}

public double getPrezzoSingolo() {
	return prezzoSingolo;
}
public String getPrezzoTotaleString() {
	  return String.format("%.2f", getPrezzoTotDettaglio()) + "€";
}

public void setPrezzoSingolo(double prezzoSingolo) {
	this.prezzoSingolo = prezzoSingolo;
}

public double getIva() {
	return iva;
}

public void setIva(double iva) {
	this.iva = iva;
}

public double getSconto() {
	return sconto;
}

public void setSconto(double sconto) {
	this.sconto = sconto;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

public void setPrezzoTot(double prezzoTot) {
	this.prezzoTot = prezzoTot;
}

public void setNome(String nome) {
	this.nome = nome;
}

@Override
public String toString() {
	return "DettaglioOrdine [item=" + item + ", numItems=" + numItems + ", idProdotto=" + idProdotto + ", idDettaglio="
			+ idDettaglio + ", idOrdine=" + idOrdine + ", prezzoTot=" + prezzoTot + ", prezzoSingolo=" + prezzoSingolo
			+ ", iva=" + iva + ", nome=" + nome + ", sconto=" + sconto + "]";
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + idDettaglio;
	result = prime * result + idOrdine;
	result = prime * result + idProdotto;
	result = prime * result + ((item == null) ? 0 : item.hashCode());
	long temp;
	temp = Double.doubleToLongBits(iva);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	result = prime * result + numItems;
	temp = Double.doubleToLongBits(prezzoSingolo);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	temp = Double.doubleToLongBits(prezzoTot);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	temp = Double.doubleToLongBits(sconto);
	result = prime * result + (int) (temp ^ (temp >>> 32));
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
	DettaglioOrdine other = (DettaglioOrdine) obj;
	if (idDettaglio != other.idDettaglio)
		return false;
	if (idOrdine != other.idOrdine)
		return false;
	if (idProdotto != other.idProdotto)
		return false;
	if (item == null) {
		if (other.item != null)
			return false;
	} else if (!item.equals(other.item))
		return false;
	if (Double.doubleToLongBits(iva) != Double.doubleToLongBits(other.iva))
		return false;
	if (nome == null) {
		if (other.nome != null)
			return false;
	} else if (!nome.equals(other.nome))
		return false;
	if (numItems != other.numItems)
		return false;
	if (Double.doubleToLongBits(prezzoSingolo) != Double.doubleToLongBits(other.prezzoSingolo))
		return false;
	if (Double.doubleToLongBits(prezzoTot) != Double.doubleToLongBits(other.prezzoTot))
		return false;
	if (Double.doubleToLongBits(sconto) != Double.doubleToLongBits(other.sconto))
		return false;
	return true;
}

public int getQuantita() {
	return quantita;
}

public void setQuantita(int quantita) {
	this.quantita = quantita;
}
}