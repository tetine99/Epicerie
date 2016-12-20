package fr.imie.gestionepicerie.model;

public class ArticleModel {
	private String reference;
	private String libelle;
	private double prixAchat;
	private double prixVente;
	private String uniteMesure;

	public ArticleModel()
	{}
	
	public ArticleModel(String reference, String libelle, double prixAchat , double prixVente, String uniteMesure){
		this.reference = reference;
		this.libelle = libelle;
		this.prixAchat = prixAchat;
		this.prixVente = prixVente;
		this.uniteMesure = uniteMesure;
		
		
	}
	
	
	public String getUniteMesure() {
		return uniteMesure;
	}

	public void setUniteMesure(String uniteMesure) {
		this.uniteMesure = uniteMesure;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(double prixAchat) {
		this.prixAchat = prixAchat;
	}

	public double getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(double prixVente) {
		this.prixVente = prixVente;
	}

	@Override
	public String toString() {
		return "ArticleModel [reference= " + reference + ", libelle= " + libelle + ", prixAchat= " + prixAchat
				+ ", prixVente= " + prixVente + ", uniteMesure= " + uniteMesure + "]";
	}
	

}
