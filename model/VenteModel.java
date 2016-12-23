package fr.imie.gestionepicerie.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VenteModel {
	
	//************************** Attributs ********************************
	private int id;
	private Date date;
	private double quantite;
	private ArticleModel article;

	//************************** Constructeurs ********************************
	public VenteModel() {}
	
	// ************************** getters ********************************
	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}
	
	public double getQuantite() {
		return quantite;
	}

    public ArticleModel getArticle() {
		return article;
	}
	
	public double getPrixTotal(){
		return quantite * article.getPrixVente();
	}

    public double getBenefice(){
		return quantite * (article.getPrixVente() - article.getPrixAchat());
	}
    
	// ************************* setters ***********************************

    public void setId(int id) {
		this.id = id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}
    
	public void setArticle(ArticleModel article) {
		this.article = article;
	}
	
	
	@Override
	public String toString() {
	return "VenteModel [id = " + id + ", date de vente = " + date +
			", quantité = " + quantite + "]";
	}
	
	
	
}