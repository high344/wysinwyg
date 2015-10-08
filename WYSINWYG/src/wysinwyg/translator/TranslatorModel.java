package wysinwyg.translator;

import wysinwyg.translator.dictionary.DictionaryInit;
import wysinwyg.translator.plover.PloverTranslator;

public class TranslatorModel {

	private Translator translator;
	private DictionaryInit dictionary;

	public TranslatorModel() {
		translator = load();
		dictionary = new DictionaryInit(translator);
	}

	private Translator load() {
		return new PloverTranslator(new String[] { ".json" });
	}

	public Translator getTranslator() {
		return translator;
	}

	public DictionaryInit getDictionary() {
		return dictionary;
	}

}
