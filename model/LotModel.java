package fr.imie.gestionepicerie.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LotModel {

	// ************************** Attributs ********************************
	private int idLot;
	private Date dateAchat;
	private Date datePeremption;
	private double quantite;
	private ArticleModel article;

	// ************************** Constructeurs ********************************
	public LotModel(int id, String achat, String peremption, double quantite, ArticleModel model) {
		this.idLot = id;

		try {
			this.dateAchat = new SimpleDateFormat("dd/MM/yyyy").parse(achat);
			this.datePeremption = new SimpleDateFormat("dd/MM/yyyy").parse(peremption);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		this.quantite = quantite;
		this.article = model;

	}

	public LotModel(String achat, String peremption, double quantite, ArticleModel model) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		try {
			Date date = formatter.parse(achat);
			Date date2 = formatter.parse(peremption);

			this.dateAchat = date;
			this.datePeremption = date2;

		} catch (ParseException e) {
			e.printStackTrace();
		}

		this.quantite = quantite;
		this.article = model;

	}

	public LotModel() {

	}

	// ************************** getters ********************************

	public int getIdLot() {
		return idLot;
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
	public void setIdLot(int idLot) {
		this.idLot = idLot;
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
		return "LotModel [idLot=" + idLot + ", dateAchat=" + date + ", datePeremption=" + date2 + ", quantite="
				+ quantite + ", modelRef=" + article + "]";
	}

}
