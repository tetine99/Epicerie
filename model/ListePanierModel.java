package fr.imie.gestionepicerie.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ListePanierModel extends AbstractTableModel{
	ArrayList <PanierModel> paniers;
	ArrayList<VenteModel> ventes;
	String colNames[] = {"ID","Date de dernière modification","Nombre d'articles"};

	public ListePanierModel(){
		this.paniers = new ArrayList<>();
	}
	
	public ListePanierModel(List<PanierModel> panier){
		this.paniers = (ArrayList<PanierModel>) panier;
	}
	
	@Override
	public int getColumnCount() { return colNames.length; }
    
	@Override
    public int getRowCount() { return this.paniers.size();}
    
	@Override
    public Object getValueAt(int row, int col) { 
    	PanierModel obj = this.paniers.get(row);
    	VenteModel obj2 = this.ventes.get(row);
		switch(col){
			case 0:
				return obj.getId_panier();
			case 1:
				return obj2.getDateVente(); // a modifier
			case 2:
				return obj2.getQuantite();
		}
		return "";
    }
    
	@Override
    public String getColumnName(int col) { 
    	return colNames[col];
    }
    
    public Iterator<PanierModel> getIterator(){
    	return paniers.iterator();
    }
    
}
