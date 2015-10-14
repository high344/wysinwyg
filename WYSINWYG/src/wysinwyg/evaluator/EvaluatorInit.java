package wysinwyg.evaluator;

import wysinwyg.Init;

public class EvaluatorInit implements Init {

	private EvaluatorModel model;
	private EvaluatorView view;
	private EvaluatorController controller;

	public EvaluatorInit() {
		model = new EvaluatorModel();
		view = new EvaluatorView(model);
		controller = new EvaluatorController(model, view);
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
