package wysinwyg.translator;

import wysinwyg.evaluator.EvaluationListener;
import wysinwyg.translator.dictionary.Dictionary;
import wysinwyg.utils.ComboboxDisplayName;
import wysinwyg.utils.FileFilter;

public interface Translator extends ComboboxDisplayName, EvaluationListener {

	public boolean addDictionary(String path);

	public boolean removeDictionary(String path);
	
	public boolean removeDictionary(int index);
	
	public boolean changeDictionaryOrders(int a, int b);
	
	public int getDictionaryCount();
	
	public Dictionary getDictionary(int index);
	
	public FileFilter getDictionaryFileFilter();

}
