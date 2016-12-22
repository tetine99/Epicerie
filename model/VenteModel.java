package fr.imie.gestionepicerie.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VenteModel {
	
	//************************** Attributs ********************************
	private int id;
	private Date date;
	private double quantite;
	private String reference; 
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
	
	
	public void setId(int id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}
	
	public double getPrixTotal(){
		return quantite * article.getPrixVente();
	}

    public double getBenefice(){
		return quantite * (article.getPrixVente() - article.getPrixAchat());
	}
	
	// ************************* setters ***********************************

	public void setDate(Date date) {
		this.date = date;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
    
	public ArticleModel getArticle() {
		return article;
	}
    
	public void setArticle(ArticleModel article) {
		this.article = article;
	}
	
	@Override
	public String toString() {
	return "VenteModel [id = " + id + ", date de vente = " + date +
			", quantité = " + quantite + ", référence = " + reference + "]";
	}
	
	
	
}
