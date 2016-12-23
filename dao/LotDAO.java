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
import fr.imie.gestionepicerie.model.LotModel;

public class LotDAO {
	private Connection connection = ConnectionDAO.getConnection();
	private static final Logger logger = Logger.getLogger(LotDAO.class);

	/**
	 * methode pour ajouter un lot a la base de donnée
	 * 
	 * @param lot
	 */
	public void addLot(LotModel lot) {
		String sql = "insert into lot_article (date_achat, date_peremption, quantite , article_reference) values (?,?,?,?)";

		logger.debug("ajoute un lot en base :" + sql);
		try {

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setTimestamp(1, new Timestamp(lot.getDateAchat().getTime()));
			ps.setTimestamp(2, new Timestamp(lot.getDatePeremption().getTime()));
			ps.setDouble(3, lot.getQuantite());
			ps.setString(4, lot.getArticle().getReference());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new TechnicalException(e);
		}

	}

	/**
	 * methode pour recuperer tout les lots de la base
	 * 
	 * @return arrayList de tout les lots
	 */
	public ArrayList<LotModel> getListLots() {
		String sql = "select * from lot_article inner join article on lot_article.article_reference = article.reference";
		try {

			ArrayList<LotModel> list = new ArrayList<>();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				LotModel lot = new LotModel();
				ArticleModel article = new ArticleModel();

				lot.setId(rs.getInt("id_lot"));
				lot.setDateAchat(rs.getDate("date_achat"));
				lot.setDatePeremption(rs.getDate("date_peremption"));
				lot.setQuantite(rs.getDouble("quantite"));
				article.setReference(rs.getString("reference"));
				article.setUniteMesure(rs.getString("unite_de_mesure"));
				article.setPrixAchat(rs.getDouble("prix_achat"));
				article.setPrixVente(rs.getDouble("prix_vente"));
				article.setLibelle(rs.getString("libelle"));
				lot.setArticle(article);
				list.add(lot);

			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * methode pour recuperer plusieur articles en fonction de leur reference
	 * 
	 * @param ref
	 * @return arraylist de lots
	 */
	public ArrayList<LotModel> getLotsByRef(String ref) {
		String sql = "select * from lot_article inner join article on lot_article.article_reference = article.reference where article_reference like ?";
		ArrayList<LotModel> list = new ArrayList<>();

		try {

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + ref.toUpperCase() + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				LotModel lot = new LotModel();
				ArticleModel article = new ArticleModel();

				lot.setDateAchat(rs.getDate("date_achat"));
				lot.setDatePeremption(rs.getDate("date_peremption"));
				lot.setQuantite(rs.getDouble("quantite"));
				lot.setId(rs.getInt("id_lot"));
				article.setLibelle(rs.getString("libelle"));
				article.setPrixAchat(rs.getDouble("prix_achat"));
				article.setPrixVente(rs.getDouble("prix_vente"));
				article.setReference(rs.getString("reference"));
				article.setUniteMesure(rs.getString("unite_de_mesure"));
				lot.setArticle(article);

				list.add(lot);

			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new TechnicalException(e);
		}

	}

	/**
	 * methode pour supprimer un lot de la base de donnée
	 * 
	 * @param id
	 */
	public void delLot(int id) {
		String sql = "delete from lot_article where id_lot = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * methode pour modifier un lot deja existant
	 * 
	 * @param model
	 * @param id
	 */
	public void modifLot(LotModel model) {
		String sql = "update lot_article set date_achat = ? , date_peremption = ? , quantite = ? , article_reference = ? where id_lot = ?";
		logger.debug("modification lot en base  :" + sql);
		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setTimestamp(1, new Timestamp(model.getDateAchat().getTime()));
			ps.setTimestamp(2, new Timestamp(model.getDatePeremption().getTime()));

			ps.setDouble(3, model.getQuantite());
			ps.setString(4, model.getArticle().getReference());
			ps.setInt(5, model.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * methode pour recuperer un lot en fonction de sa reference
	 * 
	 * @param ref
	 * @return
	 */
	public LotModel getLotByRef(String ref) {

		String sql = "select * from lot_article inner join article on lot_article.article_reference = article.reference where article_reference = ?";
		PreparedStatement ps;

		LotModel lot = new LotModel();
		try {

			ps = connection.prepareStatement(sql);
			ps.setString(1, ref);
			ResultSet rs = ps.executeQuery();
			ArticleModel article = new ArticleModel();

			lot.setDateAchat(rs.getDate("date_achat"));
			lot.setDatePeremption(rs.getDate("date_peremption"));
			lot.setQuantite(rs.getDouble("quantite"));
			lot.setId(rs.getInt("id_lot"));
			article.setLibelle(rs.getString("libelle"));
			article.setPrixAchat(rs.getDouble("prix_achat"));
			article.setPrixVente(rs.getDouble("prix_vente"));
			article.setReference(rs.getString("reference"));
			article.setUniteMesure(rs.getString("unite_de_mesure"));
			lot.setArticle(article);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lot;

	}
}
