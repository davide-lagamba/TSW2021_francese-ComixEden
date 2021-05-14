package model;

import java.sql.SQLException;
import java.util.*;

/** A shopping cart data structure used to track orders.
 *  The OrderPage servlet associates one of these carts
 *  with each user session.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */
 
public class Carrello {
  private ArrayList prodotti;

  /** Builds an empty shopping cart. */
  
  public Carrello() {
    prodotti = new ArrayList();
  }

  
  public int getQuantity(){
	  int n=0;
	  DettaglioOrdine order;
	  for(int i=0; i<prodotti.size(); i++) {
		  order = (DettaglioOrdine)prodotti.get(i);
		  n=n+order.getNumItems();
	  }
	  return n;
  }
  
  
  /** Returns List of ItemOrder entries giving
   *  Item and number ordered. Declared as List instead
   *  of ArrayList so that underlying implementation
   *  can be changed later.
   */
 
  public ArrayList getItemsOrdered() {
    return(prodotti);
  }

  /** Looks through cart to see if it already contains
   *  an order entry corresponding to item ID. If it does,
   *  increments the number ordered. If not, looks up
   *  Item in catalog and adds an order entry for it.
 * @throws SQLException 
   */
  
  public synchronized void addItem(int itemID) throws SQLException {
    DettaglioOrdine order;
    for(int i=0; i<prodotti.size(); i++) {
      order = (DettaglioOrdine)prodotti.get(i);
      if (order.getItemId()==(itemID)) {
        order.incrementNumItems();
        return;
      }
    }
    DettaglioOrdine newOrder = new DettaglioOrdine(new ProdottoDS().doRetrieveByKey(itemID));
    if(newOrder.getNumItems()>0) {
    prodotti.add(newOrder);}
  }
  
  public synchronized void removeItem(int itemID) throws SQLException {
	    DettaglioOrdine order;
	    int index=-1;
	    for(int i=0; i<prodotti.size(); i++) {
	      order = (DettaglioOrdine)prodotti.get(i);
	      if (order.getItemId()==(itemID)) {
	       index=i;
	      }
	    }
	   prodotti.remove(index);
	   return;
	  }
  
  public boolean hasItem(int itemID) {
	  DettaglioOrdine order;
	    for(int i=0; i<prodotti.size(); i++) {
	      order = (DettaglioOrdine)prodotti.get(i);
	      if (order.getItemId()==(itemID)) {
	       
	        return true;
	      }
	    }
	    return false;
  }

  /** Looks through cart to find order entry corresponding
   *  to item ID listed. If the designated number
   *  is positive, sets it. If designated number is 0
   *  (or, negative due to a user input error), deletes
   *  item from cart.
 * @throws SQLException 
   */
  
  public synchronized void setNumOrdered(int itemID,
                                         int numOrdered) throws SQLException {
	  DettaglioOrdine order;
    for(int i=0; i<prodotti.size(); i++) {
      order = (DettaglioOrdine)prodotti.get(i);
      if (order.getItemId()==(itemID)) {
        if (numOrdered <= 0) {
        	prodotti.remove(i);
        } else {
          order.setNumItems(numOrdered);
        }
        return;
      }
    }
    DettaglioOrdine newOrder =
      new DettaglioOrdine(new ProdottoDS().doRetrieveByKey(itemID));
    prodotti.add(newOrder);
  }
  
  public synchronized void incrementNumOrdered(int itemID,
		  										int numOrdered) throws SQLException {
	DettaglioOrdine order;
	for(int i=0; i<prodotti.size(); i++) {
		order = (DettaglioOrdine)prodotti.get(i);
		if (order.getItemId()==(itemID)) {
			if (numOrdered <= 0) {
				prodotti.remove(i);
			} else {
				order.setNumItems(numOrdered+order.getNumItems());
			}
			return;
			}
		}
	DettaglioOrdine newOrder =
	new DettaglioOrdine(new ProdottoDS().doRetrieveByKey(itemID));
	prodotti.add(newOrder);
	}
  
  public String toString() {
	 String tot="";
	 DettaglioOrdine order;
	  for(int i=0; i<prodotti.size(); i++) {
	      order = (DettaglioOrdine)prodotti.get(i);
	      tot=tot+"nome: "+order.getNome()+" quantità: "+order.getNumItems()+" prezzo tot: "+order.getTotalCost()+"\n";
	  }
	  return tot;
  }
  
  public String getPrezzoTotString() {
	  double tot=0;
	  DettaglioOrdine order;
	  for(int i=0; i<prodotti.size(); i++) {
		  order = (DettaglioOrdine)prodotti.get(i);
			tot=tot+order.getPrezzoTot()*order.getNumItems();
				}
	  return String.format("%.2f", tot)+"€";
	  }
  
  public Double getPrezzoTot() {
	  double tot=0;
	  DettaglioOrdine order;
	  for(int i=0; i<prodotti.size(); i++) {
		  order = (DettaglioOrdine)prodotti.get(i);
			tot=tot+order.getPrezzoTot()*order.getNumItems();
				}
	  return tot;
	  }
  
}
    