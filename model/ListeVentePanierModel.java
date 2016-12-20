package fr.imie.gestionepicerie.model;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.table.AbstractTableModel;


@SuppressWarnings("serial")
public class ListeVentePanierModel extends AbstractTableModel {
	
	ArrayList <VenteModel> ventes;
	String colNames[] = {"Référence","Prix","Libellé","Quantité","Prix total"};
	
    public ListeVentePanierModel(){
        this.ventes = new ArrayList<>();
    }
    
    public ListeVentePanierModel(ArrayList<VenteModel> ventes){
        this.ventes = ventes;
    }
	
    public int getColumnCount() { return colNames.length; }
    
    public int getRowCount() { return this.ventes.size();}
    
    public Object getValueAt(int row, int col) { 
    	VenteModel vente = this.ventes.get(row);
		switch(col){
			case 0:
				return vente.getArticle().getReference();
			case 1:
                return vente.getArticle().getPrixVente();
			case 2:
				return vente.getArticle().getLibelle();
			case 3:
                return vente.getQuantite();
			case 4:
                return vente.getPrixVenteTotal();
				
		}
		return "";
    }
    
    public VenteModel getVente(int row){
    	return this.ventes.get(row);
    }
    
    public String getColumnName(int col) { 
    	return colNames[col];
    }
    
    public Iterator<VenteModel> getIterator(){
    	return ventes.iterator();
    }

}
