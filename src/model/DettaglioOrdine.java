package model;


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

public class DettaglioOrdine {
  private Prodotto item;
  private int numItems;

  public DettaglioOrdine(Prodotto item) {
    setItem(item);
    setNumItems(1);
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
}