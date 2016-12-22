package fr.imie.gestionepicerie.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.imie.gestionepicerie.dao.VenteDAO;

@SuppressWarnings("serial")
public class ListeInfoModel extends AbstractTableModel{
	
	ArrayList<ArticleModel> articlesVendu;
	ArrayList<VenteModel> ventes;
	
	String colNames[] = {"date de vente", "Référence", "Libellé", "Prix de vente", "quantité vendu", "marge", "recette"};
	
	public ListeInfoModel(ArrayList<ArticleModel> list){
		this.articlesVendu = new ArrayList<>(list);
	}
	
	public ListeInfoModel(List<ArticleModel> article) {
		this.articlesVendu= (ArrayList<ArticleModel>) article;
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		return this.articlesVendu.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		ArticleModel obj1 = this.articlesVendu.get(row);
		
		switch (col){
		case 0:
			return obj1.getReference();
		case 1:
			return obj1.getLibelle();
		case 2:
			return obj1.getPrixVente();
		case 5:
			return VenteDAO.arrondi(obj1.getPrixVente() - obj1.getPrixAchat(), 2);
		}
		return null;
	}

	public String getColumnName (int col){
		return colNames[col];
	}
	
	public Iterator<ArticleModel> getIterator(){
		return articlesVendu.iterator();
	}
}
