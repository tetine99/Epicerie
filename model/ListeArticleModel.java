package fr.imie.gestionepicerie.model;

import java.util.*;

import javax.swing.table.AbstractTableModel;


public class ListeArticleModel extends AbstractTableModel {
	
	ArrayList <ArticleModel> articles;
	String colNames[] = {"Référence","Prix d'achat","Prix de vente","Libellé","Unité de mesure"};
	
    public ListeArticleModel(){
        this.articles = new ArrayList<>();
    }
    
    public ListeArticleModel(ArrayList<ArticleModel> articles){
        this.articles = articles;
    }
	
    public int getColumnCount() { return colNames.length; }
    
    public int getRowCount() { return this.articles.size();}
    
    public Object getValueAt(int row, int col) { 
    	ArticleModel obj = this.articles.get(row);
		switch(col){
			case 0:
				return obj.getReference();
			case 1:
				return obj.getPrixAchat();
			case 2:
				return obj.getPrixVente();
			case 3:
				return obj.getLibelle();
			case 4:
				return obj.getUniteMesure();
		}
		return "";
    }
    
    public ArticleModel getArticle(int row){
		return this.articles.get(row);
	}
    
    public String getColumnName(int col) { 
    	return colNames[col];
    }
    
    public Iterator<ArticleModel> getIterator(){
    	return articles.iterator();
    }

}
