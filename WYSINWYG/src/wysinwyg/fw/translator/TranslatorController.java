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

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import wysinwyg.fw.Controller;
import wysinwyg.fw.Viewable;

public class TranslatorController implements Controller, ItemListener, Viewable, TranslationListener {

	private TranslatorView view;

	public TranslatorController(List<Translator> translators, TranslatorView view) {
		this.view = view;

		if (!translators.isEmpty()) {
			if (SwingUtilities.isEventDispatchThread()) {
				addTranslators(translators);
			} else {
				try {
					if (view.isVisible()) {
						SwingUtilities.invokeAndWait(createAddTranslators(translators));
					} else {
						addTranslators(translators);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		view.getComboBox().addItemListener(this);
	}

	private void addTranslators(List<Translator> translators) {
		for (Translator t : translators) {
			if (t != null) {
				addTranslator(t);
				t.addTranslationListener(this);
			}
		}
	}

	private Runnable createAddTranslators(final List<Translator> translators) {
		return new Runnable() {

			@Override
			public void run() {
				addTranslators(translators);
			}
		};
	}

	@Override
	public JPanel getView() {
		return view;
	}

	public void addTranslator(Translator translator) {
		view.getComboBox().addItem(translator);
		view.getCardsPanel().add(translator.getView(), translator.getDisplayName());
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		CardLayout cl = (CardLayout) (view.getCardsPanel().getLayout());
		cl.show(view.getCardsPanel(), ((Translator) view.getComboBox().getSelectedItem()).getDisplayName());
	}

	public Translator getSelectedTranslator() {
		return ((Translator) view.getComboBox().getSelectedItem());
	}

	@Override
	public void translationEventOccurred(TranslationEvent e) {
		// TODO Auto-generated method stub
	}

}
