package fr.imie.gestionepicerie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Statement;
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
	public VenteModel createVente(VenteModel venteModel) {
		String sql = "insert into vente_article (date_vente, quantite, article_reference, panier_id) values (?,?,?,?)";
		logger.debug("ajout d'une vente en base :" + sql);
		try {
			PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setTimestamp(1, new Timestamp(venteModel.getDate().getTime()));
			ps.setDouble(2, venteModel.getQuantite());
			ps.setString(3, venteModel.getArticle().getReference());
			ps.setInt(4, venteModel.getParent().getId());
			ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            generatedKeys.first();
            venteModel.setId(generatedKeys.getInt(1));
		} catch (SQLException e) {
			throw new TechnicalException(e);
		}
        return venteModel;
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
	 * methode pour supprimer une vente de la base de donnée
	 * 
	 * @param VenteModel
	 */
	public void delVente(VenteModel venteModel) {
		String sql = "delete from vente_article where id_vente = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, String.valueOf( venteModel.getId() ) );
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
