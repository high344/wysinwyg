package wysinwyg.evaluator;

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.SwingUtilities;

import wysinwyg.Controller;

public class EvaluatorController implements Controller, ItemListener, EvaluationListener {

	private EvaluatorView view;

	EvaluatorController(Evaluator eva, EvaluatorView view) {
		this.view = view;

		eva.addEvaluationListener(this);
		view.comboBox.addItem(eva);
		view.cardsPanel.add(eva.getView(), eva.getDisplayName());
		view.comboBox.addItemListener(this);

		view.comboBox.setSelectedItem(eva);
	}

	public Evaluator getSelectedEvaluator() {
		return ((Evaluator) view.comboBox.getSelectedItem());
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		CardLayout cl = (CardLayout) (view.cardsPanel.getLayout());
		cl.show(view.cardsPanel, ((Evaluator) view.comboBox.getSelectedItem()).getDisplayName());
	}

	@Override
	public void evaluationEventOccurred(EvaluationEvent e) {
		SwingUtilities.invokeLater(createTextAreaUpdate(e.getResult()));
	}

	private Runnable createTextAreaUpdate(final String str) {
		return new Runnable() {

			@Override
			public void run() {
				view.textFieldLastStroke.setText(str);
			}
		};
	}

}
