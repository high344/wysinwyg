package wysinwyg.translator.dictionary;

import java.io.File;

import javax.swing.table.AbstractTableModel;

public class DictionaryTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -1389920478065237769L;

	// TODO fireTableStructureChanged is overkill

	private String[] names = { "Dictionary" };
	private Class<?>[] clazz = { DictionaryTableCell.class };

	private DictionaryModel model;

	public DictionaryTableModel(DictionaryModel model) {
		this.model = model;
	}

	public void addDictionary(File file) {
		if (model.getTranslator().addDictionary(file) == true) {
			fireTableDataChanged();
		}
	}

	public void removeDictionary(int row) {
		if (validRow(row)) {
			if (model.getTranslator().removeDictionary(row) == true) {
				fireTableStructureChanged();
			}
		}
	}

	public void swapDictionaries(int rowA, int rowB) {
		if (validRow(rowA) && validRow(rowB)) {
			if (model.getTranslator().changeDictionaryOrders(rowA, rowB) == true) {
				fireTableStructureChanged();
			}
		}
	}

	private boolean validRow(int row) {
		return row > -1 && model.getTranslator().getDictionaryCount() > row;
	}

	@Override
	public String getColumnName(int column) {
		return names[column];
	}

	@Override
	public int getRowCount() {
		return model.getTranslator().getDictionaryCount();
	}

	@Override
	public int getColumnCount() {
		return names.length;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return clazz[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return model.getTranslator().getDictionary(rowIndex);
		}
		return null;
	}

}
