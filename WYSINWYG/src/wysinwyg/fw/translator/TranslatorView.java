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
package wysinwyg.fw.translator;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import wysinwyg.utils.renderer.ComboboxListCellRenderer;

public class TranslatorView extends JPanel {

	private static final long serialVersionUID = 3424951162521741376L;

	private JComboBox<Translator> comboBox;
	private JPanel cardsPanel;

	public TranslatorView() {
		setBorder(new TitledBorder(null, "Translator:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.NORTH);

		comboBox = new JComboBox<Translator>();
		comboBox.setPreferredSize(new Dimension(150, 20));
		comboBox.setRenderer(new ComboboxListCellRenderer());
		panel.add(comboBox);

		cardsPanel = new JPanel();
		add(cardsPanel, BorderLayout.WEST);
		cardsPanel.setLayout(new CardLayout(0, 0));

	}

	public JComboBox<Translator> getComboBox() {
		return comboBox;
	}

	public JPanel getCardsPanel() {
		return cardsPanel;
	}

}
