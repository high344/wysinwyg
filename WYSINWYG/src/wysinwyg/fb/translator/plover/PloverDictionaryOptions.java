package wysinwyg.fb.translator.plover;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import wysinwyg.fw.translator.dictionary.Dictionary;
import wysinwyg.fw.translator.dictionary.DictionaryOptions;
import wysinwyg.utils.FileFilter;

public class PloverDictionaryOptions implements DictionaryOptions {

	TreeMap<String, Map<String, String>> entries;
	private List<Dictionary> dictionaries;
	private FileFilter ff;

	public PloverDictionaryOptions(String[] supportedFileExtensions) {
		entries = new TreeMap<String, Map<String, String>>();
		dictionaries = new ArrayList<Dictionary>(5);
		ff = new FileFilter(supportedFileExtensions);
	}

	public boolean addDictionary(File file) {
		if (ff.isSupported(file.getAbsolutePath())) {
			Dictionary d = initDictionary(file);
			if (d != null) {
				dictionaries.add(d);
				return true;
			}
		}
		return false;
	}

	protected Dictionary initDictionary(File file) {
		return new PloverJSonDictionary(file.getPath(), entries);
	}

	public boolean removeDictionary(File file) {
		Dictionary d = null;
		for (Dictionary di : dictionaries) {
			if (di.getPath().equals(file.getPath())) {
				d = di;
				break;
			}
		}
		if (d != null) {
			dictionaries.remove(d);
			return true;
		}
		return false;
	}

	public boolean removeDictionary(int index) {
		if (isValidIndex(index)) {
			dictionaries.remove(index);
			return true;
		}
		return false;
	}

	public int getDictionaryCount() {
		return dictionaries.size();
	}

	public boolean changeDictionaryOrders(int indexA, int indexB) {
		if (!isValidIndex(indexA)) {
			return false;
		}
		if (!isValidIndex(indexB)) {
			return false;
		}
		Collections.swap(dictionaries, indexA, indexB);
		return true;
	}

	public Dictionary getDictionary(int index) {
		return dictionaries.get(index);
	}

	public boolean loadUpDictionary(int index) {
		if (isValidIndex(index)) {
			dictionaries.get(index).loadDictionary();
			return true;
		}
		return false;
	}

	public void loadUpAllDictionary() {
		for (Dictionary di : dictionaries) {
			di.loadDictionary();
		}
	}

	protected boolean isValidIndex(int i) {
		return i >= 0 && dictionaries.size() > i;
	}

	@Override
	public List<Dictionary> getDictionaries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileFilter getDictionaryFileFilter() {
		return ff;
	}

	@Override
	public Dictionary initDictironay(File f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Map<String, String> loadDictionary(Dictionary dictionary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveDictionary(Dictionary dictionary) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addDictionaryEntry(Dictionary dictionary, String key, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeDictionaryEntry(Dictionary dictionary, String key, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dictionaryOrderChanged(List<Dictionary> dictionaries) {
		// TODO Auto-generated method stub

	}

}
