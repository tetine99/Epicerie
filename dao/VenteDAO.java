package fr.imie.gestionepicerie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import fr.imie.gestionepicerie.exception.TechnicalException;
import fr.imie.gestionepicerie.model.ArticleModel;
import fr.imie.gestionepicerie.model.VenteModel;

public class VenteDAO {
	private Connection connection = ConnectionDAO.getConnection();
	private static final Logger logger = Logger.getLogger(VenteDAO.class);

	/**
	 * methode pour ajouter une vente en base
	 * 
	 * @param vente
	 */
	public void addVente(VenteModel vente) {
		String sql = "insert into vente_article (date_vente, quantite, article_reference) values (?,?,?)";
		logger.debug("ajout d'une vente en base :" + sql);
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setTimestamp(1, new Timestamp(vente.getDate().getTime()));
			ps.setDouble(2, vente.getQuantite());
			ps.setString(3, vente.getArticle().getReference());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new TechnicalException(e);
		}
	}

	/**
	 * methode pour recuperer la list des ventes
	 * 
	 * @return arrayList
	 */
	public ArrayList<VenteModel> getListVente() {
		String sql = "select * from vente_article inner join article on vente_article.article_reference = article.reference";

		try {

			ArrayList<VenteModel> list = new ArrayList<>();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				ArticleModel article = new ArticleModel();
				VenteModel vente = new VenteModel();

				vente.setId(rs.getInt("id_vente"));
				vente.setDate(rs.getDate("date_vente"));
				vente.setQuantite(rs.getDouble("quantite"));
				article.setReference(rs.getString("reference"));
				article.setUniteMesure(rs.getString("unite_de_mesure"));
				article.setPrixAchat(rs.getDouble("prix_achat"));
				article.setPrixVente(rs.getDouble("prix_vente"));
				article.setLibelle(rs.getString("libelle"));
                vente.setArticle( article );
				list.add(vente);

			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * methode pour recuperer le benefice en fonction des ventes
	 * 
	 * @return
	 */
	public double getRecette() {
		String sql = "select * from article, vente_article where reference = article_reference";

		try {

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			double somme = 0;

			while (rs.next()) {
				ArticleModel article = new ArticleModel();

				if (rs.getDate("date_vente") != null)
					;

				article.setPrixAchat(rs.getDouble("prix_achat"));
				article.setPrixVente(rs.getDouble("prix_vente"));

				double nb = rs.getDouble("quantite");

				double achat = article.getPrixAchat();
				double vente = article.getPrixVente();
				somme += (vente - achat) * nb;

			}
			return arrondi(somme, 2);
		} catch (SQLException e) {
			throw new TechnicalException(e);
		}

	}

	/**
	 * methode pour recuperer le benefice des vente dans un intervalle de date
	 * donnée
	 * 
	 * @param dateDebut
	 * @param dateFin
	 * @return double recette
	 */
	public double getRecetteByDate(String dateDebut, String dateFin) {
		String sql = "select * from article right join vente_article on reference = article_reference where date_vente between ? and ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, dateDebut);
			ps.setString(2, dateFin);

			ResultSet rs = ps.executeQuery();
			double somme = 0;

			while (rs.next()) {
				ArticleModel article = new ArticleModel();

				if (rs.getDate("date_vente") != null)
					;

				article.setPrixAchat(rs.getDouble("prix_achat"));
				article.setPrixVente(rs.getDouble("prix_vente"));

				double nb = rs.getDouble("quantite");

				double achat = article.getPrixAchat();
				double vente = article.getPrixVente();
				somme += (vente - achat) * nb;

			}

			return arrondi(somme, 2);
		} catch (SQLException e) {
			throw new TechnicalException(e);
		}

	}

	public static double arrondi(double a, int b) {
		return (double) ((int) (a * Math.pow(10, b) + .5)) / Math.pow(10, b);
	}

}
