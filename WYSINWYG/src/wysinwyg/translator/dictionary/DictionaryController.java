package wysinwyg.translator.dictionary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import wysinwyg.Controller;

public class DictionaryController implements Controller, ActionListener {

	private DictionaryModel model;
	private DictionaryView view;
	private DictionaryTableCell dtc;

	DictionaryController(DictionaryModel model, DictionaryView view) {
		this.model = model;
		this.view = view;

		view.btnAdd.addActionListener(this);
		view.btnRemove.addActionListener(this);

		dtc = (DictionaryTableCell) view.table
				.getDefaultEditor(DictionaryTableCell.class);
		if (dtc != null) {
			dtc.btnUp.addActionListener(this);
			dtc.btnDown.addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == dtc.btnUp) {
			int row = view.table.getSelectedRow();
			if (row > 0) {
				model.getDictionaryTableModel().swapDictionaries(row, row - 1);
			}
		} else if (source == dtc.btnDown) {
			int row = view.table.getSelectedRow();
			if (model.getDictionaryTableModel().getRowCount() - 1 > row) {
				model.getDictionaryTableModel().swapDictionaries(row, row + 1);
			}
		} else if (source == dtc.btnAddValue) {
			// TODO
		} else if (source == dtc.btnRemoveValue) {
			// TODO
		} else if (source == view.btnAdd) {
			JFileChooser chooser = new JFileChooser();
			chooser.addChoosableFileFilter(model.getTranslator().getDictionaryFileFilter());
			chooser.setAcceptAllFileFilterUsed(false);
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				model.getDictionaryTableModel().addDictionary(chooser.getSelectedFile().getAbsolutePath());
			}
		} else if (source == view.btnRemove) {
			int row = view.table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Select a row");
				return;
			}
			model.getDictionaryTableModel().removeDictionary(row);
		}
	}

}
