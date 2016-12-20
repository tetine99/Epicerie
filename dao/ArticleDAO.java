package fr.imie.gestionepicerie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import fr.imie.gestionepicerie.exception.BusinessException;
import fr.imie.gestionepicerie.exception.TechnicalException;
import fr.imie.gestionepicerie.model.ArticleModel;

public class ArticleDAO {

	private Connection connection = ConnectionDAO.getConnection();
	private static final Logger logger = Logger.getLogger(ArticleDAO.class);

	/**
	 * methode pour ajouter un article dans la base
	 * 
	 * @param article
	 * @throws SQLException
	 */
	public void addArticle(ArticleModel article) throws SQLException {
		String sql = "insert into article (reference, prix_achat, prix_vente, libelle, unite_de_mesure) values (?,?,?,?,?)";
		logger.debug("ajoute un article en base :" + sql);

		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, article.getReference());
		ps.setDouble(2, article.getPrixAchat());
		ps.setDouble(3, article.getPrixVente());
		ps.setString(4, article.getLibelle());
		ps.setString(5, article.getUniteMesure());

		ps.executeUpdate();

	}

	/**
	 * methode pour recuperer un article en fonction de sa reference
	 * 
	 * @param reference
	 * @return
	 */
	public ArticleModel getArticleByRef(String reference) {
		String sql = "select * from article where reference = ?";

		try {

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, reference);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ArticleModel article = new ArticleModel();
				article.setReference(rs.getString("reference"));
				article.setPrixAchat(rs.getDouble("prix_achat"));
				article.setPrixVente(rs.getDouble("prix_vente"));
				article.setLibelle(rs.getString("libelle"));
				article.setUniteMesure(rs.getString("unite_de_mesure"));

				return article;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new TechnicalException(e);
		}

	}

	/**
	 * methodes pour recuperer tout les articles de la table sous forme d'une
	 * arraylist
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public ArrayList<ArticleModel> getListArticles() throws BusinessException {
		String sql = "select * from article";

		try {

			ArrayList<ArticleModel> list = new ArrayList<>();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				ArticleModel article = new ArticleModel();

				article.setReference(rs.getString("reference"));
				article.setPrixAchat(rs.getDouble("prix_achat"));
				article.setPrixVente(rs.getDouble("prix_vente"));
				article.setLibelle(rs.getString("libelle"));
				article.setUniteMesure(rs.getString("unite_de_mesure"));

				list.add(article);

			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * methode pour supprimer un article de la base de donnée
	 * 
	 * @param ref
	 */
	public void delArticle(String ref) {
		String sql = "delete from article where reference = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, ref);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * methode pour modifier les attribut d'un article
	 * 
	 * @param article
	 * @param ref
	 */
	public void modifArticle(ArticleModel article, String ref) {
		String sql = "update article set reference = ? , libelle = ? , prix_achat = ? , prix_vente = ? , unite_de_mesure = ? where reference = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, article.getReference());
			ps.setString(2, article.getLibelle());
			ps.setDouble(3, article.getPrixAchat());
			ps.setDouble(4, article.getPrixVente());
			ps.setString(5, article.getUniteMesure());
			ps.setString(6, ref);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * methodes pour recuperer plusieur articles sous forme d'une arraylist en
	 * fonction de leurs reference
	 * 
	 * @param ref
	 * @return
	 */
	public ArrayList<ArticleModel> getArticlesByRef(String ref) {

		String sql = "select * from article where reference like   ?";
		ArrayList<ArticleModel> list = new ArrayList<>();
		try {

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + ref.toUpperCase() + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				ArticleModel article = new ArticleModel();

				article.setReference(rs.getString("reference"));
				article.setPrixAchat(rs.getDouble("prix_achat"));
				article.setPrixVente(rs.getDouble("prix_vente"));
				article.setLibelle(rs.getString("libelle"));
				article.setUniteMesure(rs.getString("unite_de_mesure"));

				list.add(article);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
