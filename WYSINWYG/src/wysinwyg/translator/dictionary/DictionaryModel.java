package wysinwyg.translator.dictionary;

import wysinwyg.translator.Translator;

public class DictionaryModel {

	private Translator translator;
	private DictionaryTableModel dtm;
	
	public DictionaryModel(Translator translator) {
		this.translator = translator;
		this.dtm = new DictionaryTableModel(translator);
	}
	
	public Translator getTranslator() {
		return translator;
	}
	public DictionaryTableModel getDictionaryTableModel() {
		return dtm;
	}
	
}
