package wysinwyg.translator.dictionary;

import wysinwyg.translator.Translator;

public class DictionaryModel {

	private Translator translator;
	private DictionaryTableModel dtm;

	public DictionaryModel(Translator translator) {
		this.translator = translator;
		this.dtm = new DictionaryTableModel(this);
	}

	public Translator getTranslator() {
		return translator;
	}

	public void setTranslator(Translator translator) {
		this.translator = translator;
		dtm.fireTableStructureChanged();
	}

	public DictionaryTableModel getDictionaryTableModel() {
		return dtm;
	}

}
