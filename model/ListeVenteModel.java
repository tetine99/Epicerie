package fr.imie.gestionepicerie.model;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ListeVenteModel extends AbstractTableModel {

	ArrayList<VenteModel> ventes;
	String colNames[] = { "Date de vente", "Quantité", "Référence de l'article" };

	public ListeVenteModel(ArrayList<VenteModel> ventes) {
		this.ventes = ventes;
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		return this.ventes.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		VenteModel obj = this.ventes.get(row);
		switch (col) {
		case 0:
			return obj.getDate();
		case 1:
			return obj.getQuantite();
		case 2:
			return obj.getArticle().getReference();
		}
		return "";
	}

	// public boolean isPresentInBase(int row) {
	// VenteModel obj = this.articles.get(row);
	// return obj.isPresent();
	// }

	public String getColumnName(int col) {
		return colNames[col];
	}

	public Iterator<VenteModel> getIterator() {
		return ventes.iterator();
	}

}
