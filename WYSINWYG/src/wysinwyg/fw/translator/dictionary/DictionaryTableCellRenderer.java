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

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class DictionaryTableCellRenderer implements TableCellRenderer {

	private DictionaryTableRowPanel panel;

	public DictionaryTableCellRenderer() {
		panel = new DictionaryTableRowPanel();
		panel.getBtnUp().setEnabled(false);
		panel.getBtnDown().setEnabled(false);
		panel.getBtnOpen().setEnabled(false);
	}

	public DictionaryTableRowPanel getCellRendererValue() {
		return panel;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		update(table, value, isSelected);
		return panel;
	}

	private void update(JTable table, Object value, boolean isSelected) {
		Dictionary v = (Dictionary) value;
		panel.getLabel().setText(v.getPath() + " renderer");
		panel.setBackground(table.getBackground());
	}

}
