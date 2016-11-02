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

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class DictionaryTableCellEditor extends AbstractCellEditor implements TableCellEditor {

	private static final long serialVersionUID = -3048732428259105965L;

	private DictionaryTableRowPanel panel;

	public DictionaryTableCellEditor() {
		panel = new DictionaryTableRowPanel();
		panel.getBtnUp().setEnabled(true);
		panel.getBtnDown().setEnabled(true);
		panel.getBtnOpen().setEnabled(true);
	}

	@Override
	public Object getCellEditorValue() {
		return panel;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		update(table, value, isSelected);
		return panel;
	}

	private void update(JTable table, Object value, boolean isSelected) {
		Dictionary v = (Dictionary) value;
		panel.getLabel().setText(v.getPath() + " editor");
		panel.setBackground(table.getSelectionBackground());
	}

}
