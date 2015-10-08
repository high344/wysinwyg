package wysinwyg.evaluator;

import wysinwyg.Init;
import wysinwyg.evaluator.steno.StenoEvaluator;

public class EvaluatorInit implements Init {

	private EvaluatorView view;
	private EvaluatorController controller;

	public EvaluatorInit() {
		Evaluator eva = load();
		view = new EvaluatorView();
		controller = new EvaluatorController(eva, view);
	}

	private Evaluator load() {
		return new StenoEvaluator();
	}

	@Override
	public EvaluatorView getView() {
		return view;
	}

	@Override
	public EvaluatorController getController() {
		return controller;
	}

}
