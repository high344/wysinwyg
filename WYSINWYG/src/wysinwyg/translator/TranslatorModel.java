package wysinwyg.translator;

import wysinwyg.translator.dictionary.DictionaryInit;
import wysinwyg.translator.plover.PloverTranslator;

public class TranslatorModel {

	private Translator[] trans = new Translator[1];
	private DictionaryInit dictionary;

	public TranslatorModel() {
		load();
		dictionary = new DictionaryInit(trans[0]);
	}

	private void load() {
		trans[0] = new PloverTranslator(new String[] { ".json" });
	}

	public Translator[] getTranslators() {
		return trans;
	}

	public DictionaryInit getDictionary() {
		return dictionary;
	}

}
