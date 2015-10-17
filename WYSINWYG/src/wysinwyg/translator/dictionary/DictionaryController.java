/*******************************************************************************
 * Copyright (c) 2015 Balázs Felföldi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Balázs Felföldi - initial API and implementation
 ******************************************************************************/
package wysinwyg.translator.dictionary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import wysinwyg.Controller;
import wysinwyg.translator.Translator;

public class DictionaryController implements Controller, ActionListener {

	private DictionaryModel model;
	private DictionaryView view;
	private DictionaryTableCell dtc;

	public DictionaryController(DictionaryModel model, DictionaryView view) {
		Objects.requireNonNull(model);
		Objects.requireNonNull(view);

		this.model = model;
		this.view = view;

		view.getBtnAdd().addActionListener(this);
		view.getBtnRemove().addActionListener(this);

		dtc = (DictionaryTableCell) view.getTable().getDefaultEditor(DictionaryTableCell.class);
		if (dtc != null) {
			dtc.getBtnUp().addActionListener(this);
			dtc.getBtnDown().addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == dtc.getBtnUp()) {
			int row = view.getTable().getSelectedRow();
			if (row > 0) {
				model.getDictionaryTableModel().swapDictionaries(row, row - 1);
			}
		} else if (source == dtc.getBtnDown()) {
			int row = view.getTable().getSelectedRow();
			if (model.getDictionaryTableModel().getRowCount() - 1 > row) {
				model.getDictionaryTableModel().swapDictionaries(row, row + 1);
			}
		} else if (source == dtc.getBtnAddValue()) {
			// TODO
		} else if (source == dtc.getBtnRemoveValue()) {
			// TODO
		} else if (source == view.getBtnAdd()) {
			JFileChooser chooser = new JFileChooser();
			chooser.addChoosableFileFilter(model.getTranslator().getDictionaryFileFilter());
			chooser.setAcceptAllFileFilterUsed(false);
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				model.getDictionaryTableModel().addDictionary(chooser.getSelectedFile());
			}
		} else if (source == view.getBtnRemove()) {
			int row = view.getTable().getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Select a row");
				return;
			}
			model.getDictionaryTableModel().removeDictionary(row);
		}
	}

	public void setNewTranslator(Translator translator) {
		model.setTranslator(translator);
	}

}
