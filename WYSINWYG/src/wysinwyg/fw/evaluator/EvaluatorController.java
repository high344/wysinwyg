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
package wysinwyg.fw.evaluator;

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.SwingUtilities;

import wysinwyg.fw.Controller;
import wysinwyg.fw.Viewable;

public class EvaluatorController implements Controller, ItemListener, EvaluationListener, Viewable {

	private EvaluatorView view;

	public EvaluatorController(List<Evaluator> evaluators, EvaluatorView view) {
		this.view = view;

		if (!evaluators.isEmpty()) {
			if (SwingUtilities.isEventDispatchThread()) {
				addEvaluators(evaluators);
			} else {
				try {
					if (view.isVisible()) {
						SwingUtilities.invokeAndWait(createAddEvaluators(evaluators));
					} else {
						addEvaluators(evaluators);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		view.getComboBox().addItemListener(this);
	}

	private void addEvaluators(List<Evaluator> evaluators) {
		for (Evaluator e : evaluators) {
			if (e != null) {
				addEvaluator(e);
				e.addEvaluationListener(this);
			}
		}
	}

	private Runnable createAddEvaluators(final List<Evaluator> evaluators) {
		return new Runnable() {

			@Override
			public void run() {
				addEvaluators(evaluators);
			}
		};
	}

	/**
	 * 
	 */
	@Override
	public EvaluatorView getView() {
		return view;
	}

	/**
	 * 
	 * @param device
	 */
	public void addEvaluator(Evaluator evaluator) {
		view.getComboBox().addItem(evaluator);
		view.getCardsPanel().add(evaluator.getView(), evaluator.getDisplayName());
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
		cl.show(view.getCardsPanel(), ((Evaluator) view.getComboBox().getSelectedItem()).getDisplayName());
	}

	@Override
	public void evaluationEventOccurred(EvaluationEvent e) {
		SwingUtilities.invokeLater(createTextFieldUpdate(e.getResult()));
	}

	private Runnable createTextFieldUpdate(final String str) {
		return new Runnable() {

			@Override
			public void run() {
				view.getTextFieldLastStroke().setText(str);
			}
		};
	}

}
