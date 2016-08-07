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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class DictionaryTableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

	private static final long serialVersionUID = -3048732428259105965L;

	private JPanel panel;
	private JButton btnUp;
	private JButton btnDown;
	private JTextArea textArea;
	private JButton btnAddValue;
	private JButton btnRemoveValue;

	public DictionaryTableCell() {

		panel = new JPanel();
		panel.setBorder(new EmptyBorder(3, 3, 3, 3));

		btnUp = new JButton("Up");
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(btnUp);

		btnDown = new JButton("Down");
		panel.add(btnDown);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		textArea = new JTextArea();
		textArea.setBorder(new CompoundBorder(new EmptyBorder(0, 3, 0, 3), new LineBorder(new Color(0, 0, 0))));
		panel_1.add(textArea);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBackground(UIManager.getColor("TextField.background"));

		btnAddValue = new JButton("Add Value");
		panel.add(btnAddValue);

		btnRemoveValue = new JButton("Remove Value");
		panel.add(btnRemoveValue);
	}

	@Override
	public Object getCellEditorValue() {
		return panel;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		update(table, value, true);
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
		textArea.setText(v.getPath());
		if (isSelected) {
			panel.setBackground(table.getSelectionBackground());
		} else {
			panel.setBackground(table.getBackground());
		}
	}

	public JButton getBtnUp() {
		return btnUp;
	}

	public JButton getBtnDown() {
		return btnDown;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JButton getBtnAddValue() {
		return btnAddValue;
	}

	public JButton getBtnRemoveValue() {
		return btnRemoveValue;
	}

}
