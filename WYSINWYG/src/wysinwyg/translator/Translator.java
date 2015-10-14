package wysinwyg.translator;

import java.io.File;

import wysinwyg.evaluator.EvaluationListener;
import wysinwyg.translator.dictionary.Dictionary;
import wysinwyg.utils.ComboboxDisplayName;
import wysinwyg.utils.FileFilter;

public interface Translator extends ComboboxDisplayName, EvaluationListener {

	public boolean addDictionary(File file);

	public boolean removeDictionary(File file);

	public boolean removeDictionary(int index);

	public boolean changeDictionaryOrders(int a, int b);

	public int getDictionaryCount();

	public Dictionary getDictionary(int index);

	public FileFilter getDictionaryFileFilter();

	public boolean loadUpDictionary(int index);

	public void loadUpAllDictionary();

	public void addTranslationListener(TranslationListener transListener);

	public void removeTranslationListener(TranslationListener transListener);

	public void startTranslation();

	public void stopTranslation();

}
