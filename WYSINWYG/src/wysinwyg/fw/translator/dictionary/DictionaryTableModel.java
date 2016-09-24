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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class DictionaryTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -1389920478065237769L;

	private String[] names = { "Dictionary" };
	private Class<?>[] clazz = { DictionaryTableCell.class };
	private List<Dictionary> dictionaries = new ArrayList<Dictionary>();

	public void addDictionary(List<Dictionary> dictionaries) {
		this.dictionaries = dictionaries;
		fireTableDataChanged();
	}
	
	public void addDictionary(File file) {
		/*
		if (model.getTranslator().addDictionary(file) == true) {
			fireTableDataChanged();
		}*/
	}

	public void removeDictionary(int row) {
		if (validRow(row)) {
			/*
			if (model.getTranslator().removeDictionary(row) == true) {
				fireTableStructureChanged();
			}*/
		}
	}

	public void swapDictionaries(int rowA, int rowB) {
		if (validRow(rowA) && validRow(rowB)) {
			/*
			if (model.getTranslator().changeDictionaryOrders(rowA, rowB) == true) {
				fireTableStructureChanged();
			}*/
		}
	}

	private boolean validRow(int row) {
		/*
		return row > -1 && model.getTranslator().getDictionaryCount() > row;*/
		return false;
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

}
