package wysinwyg.translator.dictionary;

import wysinwyg.Init;
import wysinwyg.translator.Translator;

public class DictionaryInit implements Init {

	private DictionaryController controller;
	private DictionaryView view;

	public DictionaryInit(Translator translator) {
		DictionaryModel model = new DictionaryModel(translator);
		this.view = new DictionaryView(model);
		controller = new DictionaryController(model, view);
	}

	@Override
	public DictionaryController getController() {
		return controller;
	}

	@Override
	public DictionaryView getView() {
		return view;
	}

}
