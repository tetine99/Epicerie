package fr.imie.gestionepicerie.model;

import java.util.*;

import javax.swing.table.AbstractTableModel;


@SuppressWarnings("serial")
public class ListeArticleModel extends AbstractTableModel {
	
	private ArrayList <ArticleModel> articles;
	private String colNames[] = {"Référence","Prix d'achat","Prix de vente","Libellé","Unité de mesure"};
	
    public ListeArticleModel(){
        this.articles = new ArrayList<>();
    }
    
    public ListeArticleModel(ArrayList<ArticleModel> articles){
        this.articles = articles;
    }
	
    public int getColumnCount() { return colNames.length; }
    
    public int getRowCount() { return this.articles.size();}

    public String getColumnName(int col) { 
    	return colNames[col];
    }
    
    public Object getValueAt(int row, int col) { 
    	ArticleModel obj = articles.get(row);
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
		return articles.get(row);
	}
    
    public Iterator<ArticleModel> getIterator(){
    	return articles.iterator();
    }

}
