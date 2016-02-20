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

import wysinwyg.fw.Init;
import wysinwyg.utils.renderer.ComboboxListCellRenderer;

public class TranslatorView extends JPanel {

	private static final long serialVersionUID = 3424951162521741376L;

	private TranslatorModel model;
	private JComboBox<Translator> comboBox;
	private JPanel cardsPanel;

	public TranslatorView(TranslatorModel model) {
		this.model = model;
		buildGUI();
	}

	private void buildGUI() {
		setBorder(new TitledBorder(null, "Translator:", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.NORTH);

		if (model != null && model.getTranslators() != null) {
			comboBox = new JComboBox<Translator>(model.getTranslators());
		} else {
			comboBox = new JComboBox<Translator>();
		}
		panel.add(comboBox);
		comboBox.setPreferredSize(new Dimension(150, 20));
		comboBox.setRenderer(new ComboboxListCellRenderer());

		cardsPanel = new JPanel();
		add(cardsPanel, BorderLayout.WEST);
		cardsPanel.setLayout(new CardLayout(0, 0));

		if (model != null && model.getTranslators() != null) {
			for (Translator d : model.getTranslators()) {
				if (d instanceof Init) {
					Init init = (Init) d;
					if (init.getView() != null) {
						cardsPanel.add(init.getView(), d.getDisplayName());
					} else {
						cardsPanel.add(new JPanel(), d.getDisplayName());
					}
				}
			}
		}

		if (model != null) {
			if (model.getDictionary() != null) {
				add(model.getDictionary().getView(), BorderLayout.CENTER);
			}
		}

	}

	public JComboBox<Translator> getComboBox() {
		return comboBox;
	}

	public JPanel getCardsPanel() {
		return cardsPanel;
	}

}
