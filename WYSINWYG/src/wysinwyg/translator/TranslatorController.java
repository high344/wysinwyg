package wysinwyg.translator;

import wysinwyg.Controller;

public class TranslatorController implements Controller {

	private TranslatorModel model;
	private TranslatorView view;

	TranslatorController(TranslatorModel model, TranslatorView view) {
		this.model = model;
		this.view = view;

		view.comboBox.addItem(model.getTranslator());
	}

}
