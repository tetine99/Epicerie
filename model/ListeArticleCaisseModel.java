package fr.imie.gestionepicerie.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ListeArticleCaisseModel extends AbstractTableModel {
	ArrayList <ArticleModel> articles;
	String colNames[] = {"Référence","Libellé","Prix de vente"};
	
    public ListeArticleCaisseModel(){
        this.articles = new ArrayList<>();
    }
    
    public ListeArticleCaisseModel(List<ArticleModel> articles){
        this.articles = (ArrayList<ArticleModel>) articles;
    }
	
    public int getColumnCount() { return colNames.length; }
    
    public int getRowCount() { return this.articles.size();}
    
    public Object getValueAt(int row, int col) { 
    	ArticleModel obj = this.articles.get(row);
		switch(col){
			case 0:
				return obj.getReference();
			case 1:
				return obj.getLibelle();
			case 2:
				return obj.getPrixVente();
		}
		return "";
    }
    
//    public boolean isPresentInBase(int row){
//    	ArticleModel obj = this.articles.get(row);
//    	return obj.isPresent();
//    }
    
    public String getColumnName(int col) { 
    	return colNames[col];
    }
    
    public Iterator<ArticleModel> getIterator(){
    	return articles.iterator();
    }

}
