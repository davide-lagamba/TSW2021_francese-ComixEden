package control.admin;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import model.*;

@WebServlet("/admin/gestioneCatalogo")
@MultipartConfig
public class GestioneCatalogoControl extends HttpServlet {
	static String SAVE_DIR = "/images";

	public GestioneCatalogoControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Utente user = (Utente) request.getSession().getAttribute("utente");

		if (user == null || !user.isAdmin()) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/nonAutenticato.jsp");
			dispatcher.forward(request, response);
			return;
		} else {
			Carrello cart = (Carrello) request.getSession().getAttribute("cart");
			if (cart == null) {
				cart = new Carrello();
				request.getSession().setAttribute("cart", cart);
			}

			String action = request.getParameter("action");

			String id_prodotto = (request.getParameter("id"));

			try {
				if (id_prodotto != null && (action == null || action.equals(""))) {
					request.removeAttribute("product");
					request.setAttribute("product", new ProdottoDS().doRetrieveByKey(Integer.parseInt(id_prodotto)));
					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher("/pages/admin/gestioneProdotto.jsp");
					dispatcher.forward(request, response);
					return;
				}
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}
			try {
				if (action != null) {
					
					if(action.equalsIgnoreCase("search")) {
						
						var u = new ProdottoDS().doSearchByName(request.getParameter("val"));
						response.setContentType("application/json; charset=UTF-8");
						response.setStatus(200);
						Gson gson= new Gson();
						response.getWriter().print(gson.toJson(u));
						response.getWriter().flush();
						return;
					}
					if(action.equalsIgnoreCase("cerca")) {
					request.removeAttribute("products");
					request.setAttribute("products", new ProdottoDS().doSearchByName(request.getParameter("searchVal")));
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/admin/gestioneCatalogo.jsp");
					dispatcher.forward(request, response);
					return;
					}
					
					if (action.equalsIgnoreCase("inserisci")) {
						request.removeAttribute("product");
						String id = (request.getParameter("id"));
						if (id != null && !id.equals("")) {
							request.setAttribute("product", new ProdottoDS().doRetrieveByKey(Integer.parseInt(id)));
						}
						RequestDispatcher dispatcher = getServletContext()
								.getRequestDispatcher("/pages/admin/aggiungiProdotto.jsp");
						dispatcher.forward(request, response);
						return;
					} else if (action.equalsIgnoreCase("Dettagli")) {
						int id = Integer.parseInt(request.getParameter("id"));
						request.removeAttribute("product");
						request.setAttribute("product", new ProdottoDS().doRetrieveByKey(id));
						RequestDispatcher dispatcher = getServletContext()
								.getRequestDispatcher("/pages/admin/gestioneCatalogo.jsp");
						dispatcher.forward(request, response);
						return;
					} else if (action.equalsIgnoreCase("delete")) {
						int id = Integer.parseInt(request.getParameter("id"));
						new ProdottoDS().doDelete(id);
					}
				}
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}

			request.getSession().setAttribute("cart", cart);
			request.setAttribute("cart", cart);

			String sort = request.getParameter("sort");
			String page = request.getParameter("page");

			try {
				request.removeAttribute("products");
				if (sort != null && sort.equals("PrezzoTot")) {
					request.setAttribute("products", new ProdottoDS().doRetrieveAllPrezzoTot());
				} else {
					request.setAttribute("products", new ProdottoDS().doRetrieveAll(sort));
				}
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}

			if (page != null && page.equals("dettagli")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.removeAttribute("product");
				try {
					request.setAttribute("product", new ProdottoDS().doRetrieveByKey(id));
				} catch (SQLException e) {
					System.out.println("Error:" + e.getMessage());
				}
				
			}
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/pages/admin/gestioneCatalogo.jsp");
			dispatcher.forward(request, response);

		}

	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utente user = (Utente) request.getSession().getAttribute("utente");

		if (user == null || !user.isAdmin()) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/nonAutenticato.jsp");
			dispatcher.forward(request, response);
			return;
		} else {
			Carrello cart = (Carrello) request.getSession().getAttribute("cart");
			if (cart == null) {
				cart = new Carrello();
				request.getSession().setAttribute("cart", cart);
			}

			String action = request.getParameter("action");
			try {
				if (action != null) {
					if (action.equalsIgnoreCase("Rimuovi")) {
						int id = Integer.parseInt(request.getParameter("id"));

						new ProdottoDS().doDelete(id);

					}

					else if (action.equalsIgnoreCase("Aggiungi")) {
						String nome = request.getParameter("nome");
						String descrizione = request.getParameter("descrizione");
						String autori = request.getParameter("autori");
						String lingua = request.getParameter("lingua");
						String produttore = request.getParameter("produttore");
						ProduttoreDS p = new ProduttoreDS();
						Produttore prod = p.doRetrieveByName(produttore);
						String categoria = request.getParameter("categoria");
						CategoriaDS c = new CategoriaDS();
						Categoria cat = c.doRetrieveByName(categoria);
						String stato = request.getParameter("stato");
						String colore = request.getParameter("colore_stampa");
						Double prezzob = Double.parseDouble(request.getParameter("prezzo_base"));
						Double peso = Double.parseDouble(request.getParameter("peso"));
						int disponibilita = Integer.parseInt(request.getParameter("disponibilita"));
						int pagine = Integer.parseInt(request.getParameter("pagine"));
						int sconto = Integer.parseInt(request.getParameter("sconto"));
						Double iva = Double.parseDouble(request.getParameter("iva"));
						Date now = new Date(new java.util.Date().getTime());
						Prodotto prodotto = new Prodotto();
						prodotto.setPrezzoBase(prezzob);
						prodotto.setDescrizione(descrizione);
						prodotto.setStato(stato);
						prodotto.setPagine(pagine);
						prodotto.setPeso(peso);
						prodotto.setAutori(autori);
						prodotto.setLingua(lingua);
						prodotto.setData(now);
						prodotto.setDisponibilita(disponibilita);
						prodotto.setSconto(sconto);
						prodotto.setColoreStampa(colore);
						prodotto.setIva(iva);
						int id_produttore = -1;
						if (prod.getId_produttore() == -1) {
							Produttore newProd = new Produttore();
							newProd.setNome(produttore);
							p.doSaveSenzaIva(newProd);
							id_produttore = p.getNewId();
						} else {
							id_produttore = prod.getId_produttore();
						}
						int id_categoria = -1;
						if (cat.getId() == -1) {
							Categoria newCat = new Categoria();
							newCat.setNome(categoria);
							c.doSave(newCat);
							id_categoria = c.getNewId();
						} else {
							id_categoria = cat.getId();
						}
						prodotto.setIdProduttore(id_produttore);
						prodotto.setIdCategoria(id_categoria);
						prodotto.setNome(nome);
						new ProdottoDS().doSave(prodotto);
						String savePath = request.getServletContext().getRealPath("") + File.separator + SAVE_DIR;
						File fileSaveDir = new File(savePath);
						if (!fileSaveDir.exists()) {
							fileSaveDir.mkdir();
						}

						for (Part part : request.getParts()) {
							String fileName = extractFileName(part);
							if (fileName != null && !fileName.equals("")) {
								part.write(savePath + File.separator+  fileName);
								Immagine img = new Immagine();
								img.setNome(fileName);
								img.setIdProdotto(new ProdottoDS().getNewId());
								img.setCopertina(true);
								new ImmagineDS().doSave(img);
							}
						}
					
					} else if (action.equalsIgnoreCase("Modifica")) {
						String nome = request.getParameter("nome");
						String descrizione = request.getParameter("descrizione");
						String autori = request.getParameter("autori");
						String lingua = request.getParameter("lingua");
						String produttore = request.getParameter("produttore");
						ProduttoreDS p = new ProduttoreDS();
						Produttore prod = p.doRetrieveByName(produttore);
						String categoria = request.getParameter("categoria");
						CategoriaDS c = new CategoriaDS();
						Categoria cat = c.doRetrieveByName(categoria);
						String stato = request.getParameter("stato");
						String colore = request.getParameter("colore_stampa");
						Double prezzob = Double.parseDouble(request.getParameter("prezzo_base"));
						Double peso = Double.parseDouble(request.getParameter("peso"));
						int disponibilita = Integer.parseInt(request.getParameter("disponibilita"));
						int pagine = Integer.parseInt(request.getParameter("pagine"));
						int sconto = Integer.parseInt(request.getParameter("sconto"));
						Double iva = Double.parseDouble(request.getParameter("iva"));
						Prodotto prodotto = new ProdottoDS().doRetrieveByKey(Integer.parseInt(request.getParameter("id")));
						prodotto.setPrezzoBase(prezzob);
						prodotto.setDescrizione(descrizione);
						prodotto.setStato(stato);
						prodotto.setPeso(peso);
						prodotto.setPagine(pagine);
						prodotto.setAutori(autori);
						prodotto.setLingua(lingua);
						prodotto.setDisponibilita(disponibilita);
						prodotto.setSconto(sconto);
						prodotto.setColoreStampa(colore);
						prodotto.setIva(iva);
						int id_produttore = -1;
						if (prod.getId_produttore() == -1) {
							Produttore newProd = new Produttore();
							newProd.setNome(produttore);
							p.doSaveSenzaIva(newProd);
							id_produttore = p.getNewId();
						} else {
							id_produttore = prod.getId_produttore();
						}
						int id_categoria = -1;
						if (cat.getId() == -1) {
							Categoria newCat = new Categoria();
							newCat.setNome(categoria);
							c.doSave(newCat);
							id_categoria = c.getNewId();
						} else {
							id_categoria = cat.getId();
						}
						prodotto.setIdProduttore(id_produttore);
						prodotto.setIdCategoria(id_categoria);
						prodotto.setNome(nome);
						new ProdottoDS().doUpdate(prodotto);
						String savePath = request.getServletContext().getRealPath("") + File.separator + SAVE_DIR;
						File fileSaveDir = new File(savePath);
						if (!fileSaveDir.exists()) {
							fileSaveDir.mkdir();
						}

						for (Part part : request.getParts()) {
							String fileName = extractFileName(part);
							if (fileName != null && !fileName.equals("")) {
								part.write(savePath + File.separator + fileName);
								Immagine img = new Immagine();
								img.setNome(fileName);
								img.setIdProdotto(prodotto.getId());
								img.setCopertina(true);
								new ImmagineDS().doSave(img);
							}
						}
						request.removeAttribute("product");
						
					} else if (action.equalsIgnoreCase("delete")) {
						int id = Integer.parseInt(request.getParameter("id"));
						new ProdottoDS().doDelete(id);
					}
				}
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}

			request.getSession().setAttribute("cart", cart);
			request.setAttribute("cart", cart);

			String sort = request.getParameter("sort");
			String page = request.getParameter("page");

			try {
				request.removeAttribute("products");
				if (sort != null && sort.equals("PrezzoTot")) {
					request.setAttribute("products", new ProdottoDS().doRetrieveAllPrezzoTot());
				} else {
					request.setAttribute("products", new ProdottoDS().doRetrieveAll(sort));
				}
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}

			if (page != null && page.equals("dettagli")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.removeAttribute("product");
				try {
					request.setAttribute("product", new ProdottoDS().doRetrieveByKey(id));
				} catch (SQLException e) {
					System.out.println("Error:" + e.getMessage());
				}
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/pages/admin/gestioneCatalogo.jsp");
				dispatcher.forward(request, response);
				return;
			}
			response.sendRedirect("gestioneCatalogo");
			return;
		}
	}
}
