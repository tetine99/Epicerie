package fr.imie.gestionepicerie.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LotModel {

	// ************************** Attributs ********************************
	private int id;
	private Date dateAchat;
	private Date datePeremption;
	private double quantite;
	private ArticleModel article;

	// ************************** Constructeurs ********************************

	public LotModel() {

	}

	// ************************** getters ********************************

	public int getId() {
		return id;
	}

	public Date getDateAchat() {
		return dateAchat;
	}

	public Date getDatePeremption() {
		return datePeremption;
	}

	public double getQuantite() {
		return quantite;
	}

	public ArticleModel getArticle() {
		return article;
	}

	// ************************* setters ***********************************
	public void setId(int id) {
		this.id = id;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}

	public void setDatePeremption(Date datePeremption) {
		this.datePeremption = datePeremption;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}

	public void setArticle(ArticleModel article) {
		this.article = article;
	}

	//****************************** toString **********************************************
	@Override
	public String toString() {
		String date = new SimpleDateFormat("dd/MM/yyyy").format(this.getDateAchat());
		String date2 = new SimpleDateFormat("dd/MM/yyyy").format(this.getDatePeremption());
		return "LotModel [id=" + id + ", dateAchat=" + date + ", datePeremption=" + date2 + ", quantite="
				+ quantite + ", modelRef=" + article + "]";
	}

}
