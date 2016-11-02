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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;

public class DictionaryView extends JPanel {

	private static final long serialVersionUID = 6536417544245864384L;

	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnSave;
	private JButton btnReload;
	private DictionaryTable table;

	public DictionaryView() {
		setBorder(new TitledBorder(null, "Dictionary:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.EAST);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.CENTER, 5, 5);
		panel.setLayout(fl_panel);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 5));

		btnAdd = new JButton("Add");
		panel_1.add(btnAdd);

		panel_1.add(createGridBLayoutSeparator());

		btnRemove = new JButton("Remove");
		panel_1.add(btnRemove);

		panel_1.add(createGridBLayoutSeparator());

		btnSave = new JButton("Save");
		panel_1.add(btnSave);

		panel_1.add(createGridBLayoutSeparator());

		btnReload = new JButton("Reload");
		panel_1.add(btnReload);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(600, 200));
		add(scrollPane);

		table = new DictionaryTable();
		scrollPane.setViewportView(table);
	}

	private JPanel createGridBLayoutSeparator() {
		JPanel panel2 = new JPanel(new GridBagLayout());
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel2.add(separator, gbc);
		return panel2;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnRemove() {
		return btnRemove;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public JButton getBtnReload() {
		return btnReload;
	}

	public DictionaryTable getTable() {
		return table;
	}

}
