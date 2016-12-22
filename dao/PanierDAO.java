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
import fr.imie.gestionepicerie.model.PanierModel;
import fr.imie.gestionepicerie.model.VenteModel;

public class PanierDAO {

	private Connection connection = ConnectionDAO.getConnection();
	private static final Logger logger = Logger.getLogger(ArticleDAO.class);

	public void addPanier(PanierModel panier) throws SQLException {
		String sql = "insert into panier (total, benefice) values (?,?)";
		logger.debug("ajoute un article dans le panier :" + sql);
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDouble(1, panier.getTotal());
			ps.setDouble(2, panier.getBenefice());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new TechnicalException(e);
		}
	}

	public void delPanier(int id) {
		String sql = "delete from panier where id_lot = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void modifPanier(PanierModel panier) {
		String sql = "update panier set total = ? , benefice = ? where id_panier = ?";
		logger.debug("modification lot en base  :" + sql);
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDouble(1, panier.getTotal());
			ps.setDouble(2, panier.getBenefice());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<PanierModel> getListPaniers() throws BusinessException {
		String sql = "select * from panier";
        ArrayList<PanierModel> list = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PanierModel panier = new PanierModel();
				panier.setId(rs.getInt("id_panier"));
				panier.setDateModification(rs.getDate("date_modification"));

				list.add(panier);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public PanierModel getPanierById(int id) {
		String sql = "select * from panier inner join vente_article on id_panier = panier_id where id_panier = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			PanierModel panier = new PanierModel();
			while (rs.next()) {

				VenteModel vente = new VenteModel();
				ArticleModel article = new ArticleModel();

				panier.setId(rs.getInt("id_panier"));
				panier.setDateModification(rs.getDate("date_modification"));

				vente.setDate(rs.getDate("date_vente"));
				vente.setQuantite(rs.getDouble("quantite"));

				article.setReference(rs.getString("reference"));
				article.setLibelle(rs.getString("libelle"));
				article.setPrixAchat(rs.getDouble("prix_achat"));
				article.setPrixVente(rs.getDouble("prixe_vente"));
				article.setUniteMesure(rs.getString("unite_de_mesure"));

				vente.setArticle(article);
				panier.addVente(vente);
				return panier;
			}
		} catch (SQLException e) {
			throw new TechnicalException(e);
		}
		return null;
	}

    public ArrayList<PanierModel> getPaniersByRef(String ref) {
        return new ArrayList<PanierModel>();
    }

}
