package fr.imie.gestionepicerie.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import fr.imie.gestionepicerie.exception.BusinessException;
import fr.imie.gestionepicerie.model.ArticleModel;
import fr.imie.gestionepicerie.model.LotModel;
import fr.imie.gestionepicerie.model.PanierModel;
import fr.imie.gestionepicerie.model.VenteModel;
import fr.imie.gestionepicerie.service.EpicerieService;

public class EpicerieController {

	private static final EpicerieController instance = new EpicerieController();
	private EpicerieService epicerieService;

	public static EpicerieController getInstance() {
		return instance;
	}

	private EpicerieController() {
	};

	public EpicerieService getEpicerieService() {
		return epicerieService;
	}

	public void setEpicerieService(EpicerieService epicerieService) {
		this.epicerieService = epicerieService;
	}

	// ************************ ventes ******************************
	public void addVente(VenteModel vente) {
		epicerieService.addVente(vente);
	}

	public ArrayList<VenteModel> getListVente() {
		return epicerieService.getListVente();
	}

	public double getRecette() {
		return epicerieService.getRecette();
	}

	public double getRecetteByDate(String dateDebut, String dateFin) {
		return epicerieService.getRecetteByDate(dateDebut, dateFin);
	}
	// *********************** Articles ****************************

	public void addArticle(ArticleModel article) throws BusinessException, SQLException {
		epicerieService.addArticle(article);
	}

	public ArrayList<ArticleModel> getListArticles() throws BusinessException {
		return epicerieService.getListArticles();
	}

	public ArticleModel getArticleByRef(String reference) throws BusinessException {
		return epicerieService.getArticleByRef(reference);
	}

	public void delArticle(String ref) {
		epicerieService.delArticle(ref);
	}

	public void modifArticle(ArticleModel article, String ref) throws BusinessException {
		epicerieService.modifArticle(article, ref);
	}

	public ArrayList<ArticleModel> getArticlesByRef(String ref) {
		return epicerieService.getArticlesByRef(ref);
	}

	// ********************** Lots *************************************
	public void addLot(LotModel lot) {
		epicerieService.addLot(lot);
	}

	public ArrayList<LotModel> getListLots() {
		return epicerieService.getListLots();
	}

	public ArrayList<LotModel> getLotsByRef(String ref) {
		return epicerieService.getLotsByRef(ref);
	}

	public void delLot(int id) {
		epicerieService.delLot(id);
	}

	public void modifLot(LotModel lot, int id) {
		epicerieService.modifLot(lot, id);
	}

	public LotModel getLotByRef(String ref) {
		return epicerieService.getLotByRef(ref);
	}

	// ************************* panier **********************************
	public void addPanier(PanierModel panier) throws SQLException {
		epicerieService.addPanier(panier);
	}

	public void delPanier(int id) {
		epicerieService.delPanier(id);
	}

	public void modifPanier(PanierModel panier, int id) {
		epicerieService.modifPanier(panier, id);
	}

	public PanierModel getPanierById(int id) {
		return epicerieService.getPanierById(id);
	}

}
