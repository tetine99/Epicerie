package fr.imie.gestionepicerie.model;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.table.AbstractTableModel;


@SuppressWarnings("serial")
public class ListeLotModel extends AbstractTableModel {
	
	ArrayList <LotModel> lots;
	String colNames[] = {"Date d'achat","Date de péremption","Quantité","Référence de l'article"};
	
    public ListeLotModel(){
        this.lots = new ArrayList<>();
    }
    
    public ListeLotModel(ArrayList<LotModel> lots){
        this.lots = lots;
    }
	
    public int getColumnCount() { return colNames.length; }
    
    public int getRowCount() { return this.lots.size();}
    
    public Object getValueAt(int row, int col) { 
    	LotModel obj = this.lots.get(row);
		switch(col){
			case 0:
				return new SimpleDateFormat("dd/MM/yyyy").format(obj.getDateAchat());
			case 1:
				return new SimpleDateFormat("dd/MM/yyyy").format(obj.getDatePeremption());
			case 2:
				return obj.getQuantite();
			case 3:
				return obj.getArticle().getReference();
		}
		return "";
    }
    
    public LotModel getLot(int row){
    	return this.lots.get(row);
    }
    
    public String getColumnName(int col) { 
    	return colNames[col];
    }
    
    public Iterator<LotModel> getIterator(){
    	return lots.iterator();
    }

}
