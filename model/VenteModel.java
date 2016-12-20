package fr.imie.gestionepicerie.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VenteModel {
	
	//************************** Attributs ********************************
	private int id;
	private Date date;
	private double quantite;
	private String modelRef; 
	private ArticleModel article;
	private double total;
	//************************** Constructeurs ********************************
	public VenteModel() {
			
	}
	
	public VenteModel(String date, double quantite, String modelRef){
		
		try{
			this.date = new SimpleDateFormat ("dd/MM/yyyy").parse(date);
		} catch (ParseException e){
			e.printStackTrace();
		}
		
		this.quantite = quantite;
		this.modelRef = modelRef;
	}

	public VenteModel(int id, String date, double quantite, String modelRef){
		this.id = id;
		
		try{
			this.date = new SimpleDateFormat ("dd/MM/yyyy").parse(date);
		} catch (ParseException e){
			e.printStackTrace();
		}
			
		this.quantite = quantite;
		this.modelRef = modelRef;
	}
	
	
	
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

	public String getModelref() {
		return modelRef;
	}
	
	public double getPrixTotal(){
		return total;
	}
	
	// ************************* setters ***********************************

	public void setDate(Date date) {
		this.date = date;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}

	public void setModelref(String modelref) {
		this.modelRef = modelref;
	}
	public ArticleModel getArticle() {
		return article;
	}
	public void setArticle(ArticleModel article) {
		this.article = article;
	}
	
	public void setPrixTotal(double total){
		this.total = total;
	}


	
	@Override
	public String toString() {
	return "VenteModel [id = " + id + ", date de vente = " + date +
			", quantité = " + quantite + ", model de Référence = " + modelRef + "]";
	}
	
	
	
}
