package fr.imie.gestionepicerie.model;

import java.util.*;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ListePanierModel extends AbstractTableModel {
    
    private ArrayList<PanierModel> paniers;
    private String colNames[] = {"ID","Date de dernière modification","Nombre d'articles"};

	public ListePanierModel(ArrayList<PanierModel> paniers) {
		this.paniers = paniers;
	}
    
    public int getColumnCount() { return colNames.length; }
    
    public int getRowCount() { return paniers.size();}

    public String getColumnName(int col) { 
    	return colNames[col];
    }
    
   	public Iterator<PanierModel> getIterator() {
		return paniers.iterator();
	}
    
	@Override
	public Object getValueAt(int row, int col) {
		PanierModel panier = paniers.get(row);
		switch (col) {
		case 0:
			return panier.getId();
		case 1:
			return panier.getDateModification();
		case 2:
			return panier.getNombreArticles();
		}
		return "";
	}
    
    public PanierModel getPanier(int row) {
		return paniers.get(row);
	}

}
