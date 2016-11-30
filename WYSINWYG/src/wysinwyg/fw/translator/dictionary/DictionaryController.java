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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import wysinwyg.fw.Controller;
import wysinwyg.fw.Viewable;

public class DictionaryController implements Controller, ActionListener, Viewable {

	private DictionaryView view;
	private DictionaryOptions options;

	public DictionaryController(DictionaryOptions options, DictionaryView view) {
		this.options = options;
		this.view = view;

		List<Dictionary> list = loadList();

		if (!list.isEmpty()) {
			if (SwingUtilities.isEventDispatchThread()) {
				view.getTable().getModel().newDictionary(list);
			} else {
				try {
					if (view.isVisible()) {
						SwingUtilities.invokeAndWait(createAddDictionaries(list));
					} else {
						view.getTable().getModel().newDictionary(list);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		view.getTable().addTableRowListener(this);
		view.getBtnAdd().addActionListener(this);
		view.getBtnRemove().addActionListener(this);
		view.getBtnSave().addActionListener(this);
		view.getBtnReload().addActionListener(this);
	}

	private Runnable createAddDictionaries(final List<Dictionary> dictionaries) {
		return new Runnable() {

			@Override
			public void run() {
				view.getTable().getModel().newDictionary(dictionaries);
			}
		};
	}

	private List<Dictionary> loadList() {
		List<Dictionary> list = null;
		Objects.requireNonNull(list = options.getDictionaries());
		return new ArrayList<Dictionary>(list);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (view.getTable().isBtnDown(source)) {
			if (view.getTable().moveDownRow()) {
				view.getBtnSave().setText("Save*");
			}
		} else if (view.getTable().isBtnUp(source)) {
			if (view.getTable().moveUpRow()) {
				view.getBtnSave().setText("Save*");
			}
		} else if (view.getTable().isBtnOpen(source)) {
			Dictionary d = view.getTable().getSelectedDictonary();
			if(d != null) {
				new DListController(options, d);
			}
		} else if (view.getBtnAdd() == source) {
			if (options != null) {
				JFileChooser chooser = new JFileChooser();
				chooser.addChoosableFileFilter(options.getDictionaryFileFilter());
				chooser.setAcceptAllFileFilterUsed(false);
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					Dictionary d = options.initDictironay(chooser.getSelectedFile());
					if (d != null) {
						view.getTable().getModel().addDictionary(d);
					}
				}
			}
		} else if (view.getBtnRemove() == source) {
			int row = view.getTable().getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Select a row");
				return;
			}
			if(view.getTable().getModel().removeDictionary(row)) {
				view.getTable().removeEditor();
				view.getBtnSave().setText("Save*");
			}
		} else if (view.getBtnSave() == source) {
			if (options != null) {
				options.dictionaryOrderChanged(view.getTable().getModel().copyList());
				view.getBtnSave().setText("Save");
			}
		} else if (view.getBtnReload() == source) {
			if (options != null) {
				view.getTable().getModel().newDictionary(loadList());
				view.getTable().removeEditor();
				view.getBtnSave().setText("Save");
			}
		}
	}

	@Override
	public JPanel getView() {
		return view;
	}

}
