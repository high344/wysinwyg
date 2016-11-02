/*******************************************************************************
 * Copyright (c) 2015 Balazs Felfoldi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Balazs Felfoldi - initial API and implementation
 ******************************************************************************/
package wysinwyg.fw.translator.dictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class DictionaryTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -1389920478065237769L;

	private String[] names = { "Dictionary" };
	private Class<?>[] clazz = { DictionaryTableCellRenderer.class };
	private List<Dictionary> dictionaries = new ArrayList<Dictionary>();

	public void newDictionary(List<Dictionary> dictionaries) {
		this.dictionaries = dictionaries;
		fireTableDataChanged();
	}

	public void addDictionary(Dictionary d) {
		if (dictionaries.add(d) == true) {
			fireTableDataChanged();
		}
	}

	public boolean removeDictionary(int row) {
		if (validRow(row)) {
			dictionaries.remove(row);
			fireTableRowsDeleted(row, row);
			return true;
		}
		return false;
	}

	public boolean swapDictionaries(int rowA, int rowB) {
		if (validRow(rowA) && validRow(rowB)) {
			Collections.swap(dictionaries, rowA, rowB);
			fireTableCellUpdated(rowA, rowB);
			return true;
		}
		return false;
	}

	private boolean validRow(int row) {
		return row > -1 && dictionaries.size() > row;
	}

	@Override
	public String getColumnName(int column) {
		return names[column];
	}

	@Override
	public int getRowCount() {
		return dictionaries.size();
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
			return dictionaries.get(rowIndex);
		}
		return null;
	}
	
	public List<Dictionary> copyList() {
		return new ArrayList<Dictionary>(dictionaries);
	}
	

}
