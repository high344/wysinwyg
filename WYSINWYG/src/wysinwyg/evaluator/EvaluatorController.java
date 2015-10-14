package wysinwyg.evaluator;

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

import javax.swing.SwingUtilities;

import wysinwyg.Controller;

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
