package wysinwyg.translator;

import java.awt.Component;

import wysinwyg.Controller;
import wysinwyg.Init;

public class TranslatorInit implements Init {

	private TranslatorView view;
	private TranslatorController controller;

	public TranslatorInit() {
		TranslatorModel model = new TranslatorModel();
		view = new TranslatorView(model);
		controller = new TranslatorController(model, view);
	}

	@Override
	public Component getView() {
		return view;
	}

	@Override
	public Controller getController() {
		return controller;
	}

}
