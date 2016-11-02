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

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class DictionaryTable extends JTable {

	private static final long serialVersionUID = 2795692272077065409L;

	private DictionaryTableCellRenderer renderer = new DictionaryTableCellRenderer();
	private DictionaryTableCellEditor editor = new DictionaryTableCellEditor();

	public DictionaryTable() {
		super(new DictionaryTableModel());
		setDefaultRenderer(DictionaryTableCellRenderer.class, renderer);
		setDefaultEditor(DictionaryTableCellRenderer.class, editor);
		setToolTipText(null);
		setRowHeight(50);
		setColumnSelectionAllowed(false);
		setRowSelectionAllowed(false);
		setCellSelectionEnabled(false);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTableHeader().setReorderingAllowed(false);
	}

	@Override
	public boolean editCellAt(int row, int column, EventObject e) {
		if (e == null) {
			return super.editCellAt(row, column, null);
		} else if (e instanceof MouseEvent) {
			MouseEvent me = (MouseEvent) e;
			DictionaryTableRowPanel panel = renderer.getCellRendererValue();
			if (panel != null) {
				int x = panel.getLabel().getLocation().x;
				int wx = panel.getLabel().getSize().width + x;
				if (x <= me.getX() && me.getX() <= wx) {
					return super.editCellAt(row, column, me);
				}
			}
		}
		return false;
	}

	@Override
	public DictionaryTableModel getModel() {
		return (DictionaryTableModel) super.getModel();
	}

	public void addTableRowListener(ActionListener l) {
		renderer.getCellRendererValue().getBtnUp().addActionListener(l);
		renderer.getCellRendererValue().getBtnDown().addActionListener(l);
		renderer.getCellRendererValue().getBtnOpen().addActionListener(l);
		((DictionaryTableRowPanel) editor.getCellEditorValue()).getBtnUp().addActionListener(l);
		((DictionaryTableRowPanel) editor.getCellEditorValue()).getBtnDown().addActionListener(l);
		((DictionaryTableRowPanel) editor.getCellEditorValue()).getBtnOpen().addActionListener(l);
	}

	public boolean isBtnUp(Object o) {
		return o == renderer.getCellRendererValue().getBtnUp()
				|| o == ((DictionaryTableRowPanel) editor.getCellEditorValue()).getBtnUp();
	}

	public boolean isBtnDown(Object o) {
		return o == renderer.getCellRendererValue().getBtnDown()
				|| o == ((DictionaryTableRowPanel) editor.getCellEditorValue()).getBtnDown();
	}

	public boolean isBtnOpen(Object o) {
		return o == renderer.getCellRendererValue().getBtnOpen()
				|| o == ((DictionaryTableRowPanel) editor.getCellEditorValue()).getBtnOpen();
	}

	public boolean moveUpRow() {
		int row = getEditingRow();
		if (row > -1) {
			if (getModel().swapDictionaries(row, row - 1)) {
				editCellAt(row - 1, 0, null);
				Rectangle r = new Rectangle();
				Rectangle.union(getCellRect(row, 0, false), getCellRect(row - 1, 0, false), r);
				repaint(r);
				return true;
			}
		}
		return false;
	}

	public boolean moveDownRow() {
		int row = getEditingRow();
		if (row > -1) {
			if (getModel().swapDictionaries(row, row + 1)) {
				editCellAt(row + 1, 0, null);
				Rectangle r = new Rectangle();
				Rectangle.union(getCellRect(row, 0, false), getCellRect(row + 1, 0, false), r);
				repaint(r);
				return true;
			}
		}
		return false;
	}

	public Dictionary getSelectedDictonary() {
		if (getEditingRow() < 0) {
			return null;
		}
		return (Dictionary) getModel().getValueAt(getEditingRow(), 0);
	}

}
