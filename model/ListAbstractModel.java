package fr.imie.gestionepicerie.model;
import javax.swing.table.AbstractTableModel;
import java.util.*;

@SuppressWarnings("serial")
public abstract class ListAbstractModel extends AbstractTableModel {
    protected ArrayList<Object> elems;
    protected String[] colNames;

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		return elems.size();
	}

	@Override
	public abstract Object getValueAt(int row, int col);

	@Override
	public String getColumnName(int col) {
		return colNames[col];
	}

}