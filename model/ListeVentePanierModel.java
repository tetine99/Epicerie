package fr.imie.gestionepicerie.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ListeVentePanierModel extends AbstractTableModel {

	ArrayList<VenteModel> ventes;
	String colNames[] = { "Références", "Prix", "Libellé", "Quantité", "Prix total"};

	public ListeVentePanierModel(ArrayList<VenteModel> ventes) {
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
		VenteModel vente = this.ventes.get(row);
		switch (col) {
		case 0:
			return vente.getArticle().getReference();
		case 1:
			return vente.getArticle().getPrixVente();
		case 2:
			return vente.getArticle().getLibelle();
		case 3:
			return vente.getQuantite();
		case 4:
			return vente.getPrixTotal();
		}
		return "";
	}

	public String getColumnName(int col) {
		return colNames[col];
	}

	public Iterator<VenteModel> getIterator() {
		return ventes.iterator();
	}

}
