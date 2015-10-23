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
package wysinwyg.evaluator;

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

import javax.swing.SwingUtilities;

import wysinwyg.Controller;

/**
 * Implementing listeners between {@linkplain EvaluatorModel} and
 * {@linkplain EvaluatorView}
 * 
 * @author FelfoldiB.
 *
 */
public class EvaluatorController implements Controller, ItemListener, EvaluationListener {

	private EvaluatorView view;

	public EvaluatorController(EvaluatorModel model, EvaluatorView view) {
		Objects.requireNonNull(model);
		Objects.requireNonNull(view);

		this.view = view;

		for (Evaluator d : model.getEvaluators()) {
			d.addEvaluationListener(this);
		}

		view.getComboBox().addItemListener(this);
	}

	/**
	 * Returns the selected Evaluator from the combo box.
	 * 
	 * @return a class implementing the {@linkplain Evaluator} interface.
	 */
	public Evaluator getSelectedEvaluator() {
		return ((Evaluator) view.getComboBox().getSelectedItem());
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		CardLayout cl = (CardLayout) (view.getCardsPanel().getLayout());
		cl.show(view.getCardsPanel(),
				((Evaluator) view.getComboBox().getSelectedItem()).getDisplayName());
	}

	@Override
	public void evaluationEventOccurred(EvaluationEvent e) {
		SwingUtilities.invokeLater(createTextAreaUpdate(e.getResult()));
	}

	private Runnable createTextAreaUpdate(final String str) {
		return new Runnable() {

			@Override
			public void run() {
				view.getTextFieldLastStroke().setText(str);
			}
		};
	}

}
