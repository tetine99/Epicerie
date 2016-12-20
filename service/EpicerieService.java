package fr.imie.gestionepicerie.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import fr.imie.gestionepicerie.dao.ArticleDAO;
import fr.imie.gestionepicerie.dao.LotDAO;
import fr.imie.gestionepicerie.dao.PanierDAO;
import fr.imie.gestionepicerie.dao.VenteDAO;
import fr.imie.gestionepicerie.exception.BusinessException;
import fr.imie.gestionepicerie.model.ArticleModel;
import fr.imie.gestionepicerie.model.LotModel;
import fr.imie.gestionepicerie.model.PanierModel;
import fr.imie.gestionepicerie.model.VenteModel;

public class EpicerieService {

	private ArticleDAO articleDAO;
	private LotDAO lotDAO;
	private VenteDAO venteDAO;
	private PanierDAO panierDAO;

	// ****************************** getters and setters **********************************
	public VenteDAO getVenteDAO() {
		return venteDAO;
	}

	public PanierDAO getPanierDAO() {
		return panierDAO;
	}

	public void setPanierDAO(PanierDAO panierDAO) {
		this.panierDAO = panierDAO;
	}

	public LotDAO getLotDAO() {
		return lotDAO;
	}

	public void setLotDAO(LotDAO lotDAO) {
		this.lotDAO = lotDAO;
	}

	public ArticleDAO getArticleDAO() {
		return articleDAO;
	}

	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	public void setVenteDAO(VenteDAO venteDAO) {
		this.venteDAO = venteDAO;
	}

	// ************************ ventes ******************************
	public void addVente(VenteModel vente) {
		venteDAO.addVente(vente);
	}

	public ArrayList<VenteModel> getListVente() {
		return venteDAO.getListVente();
	}

	public double getRecette() {
		return venteDAO.getRecette();
	}

	public double getRecetteByDate(String dateDebut, String dateFin) {
		return venteDAO.getRecetteByDate(dateDebut, dateFin);
	}

	// ************************ Articles ****************************

	public void addArticle(ArticleModel article) throws BusinessException, SQLException {

		if (StringUtils.isBlank(article.getReference())) {
			throw new BusinessException("reference.blank");
		}
		if (StringUtils.isBlank(article.getLibelle())) {
			throw new BusinessException("libelle.blank");
		}
		if (StringUtils.isBlank(String.valueOf(article.getPrixAchat()))) {
			throw new BusinessException("prixAchat.blank");
		}
		if (StringUtils.isBlank(String.valueOf(article.getPrixVente()))) {
			throw new BusinessException("prixVente.blank");
		}
		articleDAO.addArticle(article);
	}

	public ArticleModel getArticleByRef(String reference) {
		return articleDAO.getArticleByRef(reference);
	}

	public ArrayList<ArticleModel> getListArticles() throws BusinessException {
		return articleDAO.getListArticles();
	}

	public void delArticle(String ref) {
		articleDAO.delArticle(ref);
	}

	public void modifArticle(ArticleModel article, String ref) throws BusinessException {
		if (StringUtils.isBlank(article.getReference())) {
			throw new BusinessException("reference.blank");
		}
		if (StringUtils.isBlank(article.getLibelle())) {
			throw new BusinessException("libelle.blank");
		}
		if (StringUtils.isBlank(String.valueOf(article.getPrixAchat()))) {
			throw new BusinessException("prixAchat.blank");
		}
		if (StringUtils.isBlank(String.valueOf(article.getPrixVente()))) {
			throw new BusinessException("prixVente.blank");
		}

		articleDAO.modifArticle(article, ref);
	}

	public ArrayList<ArticleModel> getArticlesByRef(String ref) {
		return articleDAO.getArticlesByRef(ref);
	}

	// *********************** Lots ********************************

	public void addLot(LotModel lot) {
		lotDAO.addLot(lot);
	}

	public ArrayList<LotModel> getListLots() {
		return lotDAO.getListLots();
	}

	public ArrayList<LotModel> getLotsByRef(String ref) {

		return lotDAO.getLotsByRef(ref);
	}

	public void delLot(int id) {
		lotDAO.delLot(id);
	}

	public void modifLot(LotModel lot, int id) {
		lotDAO.modifLot(lot, id);
	}

	public LotModel getLotByRef(String ref) {
		return lotDAO.getLotByRef(ref);
	}

	// ************************* Panier **********************************
	public void addPanier(PanierModel panier) throws SQLException {
		panierDAO.addPanier(panier);
	}

	public void delPanier(int id) {
		panierDAO.delPanier(id);
	}

	public void modifPanier(PanierModel panier, int id) {
		panierDAO.modifPanier(panier, id);
	}

	public PanierModel getPanierById(int id) {
		return panierDAO.getPanierById(id);
	}

	// ************************* Exception *******************************

}
