package wysinwyg.translator;

import wysinwyg.Init;

public class TranslatorInit implements Init {

	private TranslatorModel model;
	private TranslatorView view;
	private TranslatorController controller;

	public TranslatorInit() {
		model = new TranslatorModel();
		view = new TranslatorView(model);
		controller = new TranslatorController(model, view);
	}

	@Override
	public TranslatorView getView() {
		return view;
	}

	@Override
	public TranslatorController getController() {
		return controller;
	}

}
