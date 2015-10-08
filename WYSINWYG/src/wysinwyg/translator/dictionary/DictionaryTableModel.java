package wysinwyg.translator.dictionary;

import javax.swing.table.AbstractTableModel;

import wysinwyg.translator.Translator;

public class DictionaryTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -1389920478065237769L;

	// TODO fireTableStructureChanged is overkill

	private String[] names = { "Dictionary" };
	private Class<?>[] clazz = { DictionaryTableCell.class };

	private Translator translator;
	
	public DictionaryTableModel(Translator translator) {
		this.translator = translator;
	}

	void addDictionary(String path) {
		if(translator.addDictionary(path) == true) {
			fireTableDataChanged();
		}
	}

	void removeDictionary(int row) {
		if (validRow(row)) {
			if(translator.removeDictionary(row) == true) {
				fireTableStructureChanged();
			}
		}
	}

	void swapDictionaries(int rowA, int rowB) {
		if (validRow(rowA) && validRow(rowB)) {
			if (translator.changeDictionaryOrders(rowA, rowB) == true) {
				fireTableStructureChanged();
			}
		}
	}

	private boolean validRow(int row) {
		return row > -1 && translator.getDictionaryCount() > row;
	}

	@Override
	public String getColumnName(int column) {
		return names[column];
	}

	@Override
	public int getRowCount() {
		return translator.getDictionaryCount();
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
			return translator.getDictionary(rowIndex);
		}
		return null;
	}

}
