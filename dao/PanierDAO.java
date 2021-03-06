package fr.imie.gestionepicerie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Statement;
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

	
	public PanierModel createPanier() throws SQLException {
		String sql = "insert into panier (date_modification) values (?)";
		logger.debug("ajoute un article dans le panier :" + sql);
		PanierModel panier = new PanierModel();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            generatedKeys.first();
            panier.setId(generatedKeys.getInt(1));
		} catch (SQLException e) {
			throw new TechnicalException(e);
		}
		return panier;
	}
	
	public void delPanier(int id) {
		String sql = "delete from panier where id_panier = ?";
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
		String sql = "update panier set id_panier = ? , date_modification = ? where id_panier = ?";
		logger.debug("modification lot en base  :" + sql);
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, panier.getId());
			ps.setTimestamp(2, new Timestamp(panier.getDateModification().getTime()));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public ArrayList<PanierModel> getListPaniers() throws BusinessException {
		String sql = "select * from panier left join vente_article on vente_article.panier_id = panier.id_panier left join article on article.reference = vente_article.article_reference";
		ArrayList<PanierModel> list = new ArrayList<>();
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			PanierModel panier = new PanierModel();
            VenteModel vente = new VenteModel();
            ArticleModel article;
            int id = -1; 
            int lastId = -1;
			while(rs.next()){
                id = rs.getInt("id_panier");
                if (id!=lastId){
                    lastId = id;
                    panier = new PanierModel();
                    panier.setId(id);
                    panier.setDateModification(rs.getDate("date_modification"));
                    list.add(panier);
                }

                if (rs.getDate("date_vente")!=null){
                    vente = new VenteModel();
                    vente.setId(rs.getInt("id_vente"));
                    vente.setDate(rs.getDate("date_vente"));
                    vente.setQuantite(rs.getDouble("quantite"));
                    panier.addVente(vente);
                }
                
                if (rs.getString("reference")!=null){
                    article = new ArticleModel();
                    article.setReference(rs.getString("reference"));
                    article.setPrixAchat(rs.getDouble("prix_achat"));
                    article.setPrixVente(rs.getDouble("prix_vente"));
                    article.setLibelle(rs.getString("libelle"));
                    article.setUniteMesure(rs.getString("unite_de_mesure"));
                    vente.setArticle(article);
                }
				
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public ArrayList<PanierModel> getPaniersByRef (String ref)throws BusinessException{
		String sql = "select * from article join vente_article on article.reference = vente_article.article_reference join panier on vente_article.panier_id = panier.id_panier where article_reference like ?";
		ArrayList<PanierModel> list = new ArrayList<>();
		
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + ref.toUpperCase() + "%");
			ResultSet rs = ps.executeQuery();
			PanierModel panier = new PanierModel();
            int id,lastId = 1;
			while(rs.next()){
                id = rs.getInt("id_panier");
                if (id!=lastId){
                    list.add(panier);
                    lastId = id;
                    panier = new PanierModel();
                }
				VenteModel vente = new VenteModel();
				ArticleModel article = new ArticleModel();
				
				panier.setId(id);
				panier.setDateModification(rs.getDate("date_modification"));
				
				vente.setId(rs.getInt("id_vente"));
				vente.setDate(rs.getDate("date_vente"));
				vente.setQuantite(rs.getDouble("quantite"));
												
				article.setReference(rs.getString("reference"));
				article.setPrixAchat(rs.getDouble("prix_achat"));
				article.setPrixVente(rs.getDouble("prix_vente"));
				article.setLibelle(rs.getString("libelle"));
				article.setUniteMesure(rs.getString("unite_de_mesure"));
				
				vente.setArticle(article);
				panier.addVente(vente);
				
			}
		} catch (SQLException e){
			throw new TechnicalException (e);	
		}
		return list;
	}
	
	
	
	
	
}
