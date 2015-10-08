package wysinwyg.translator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import wysinwyg.translator.dictionary.Dictionary;
import wysinwyg.utils.FileFilter;

public abstract class AbstractTranslator implements Translator {

	protected List<Dictionary> dictionaries;
	protected String[] supportedExtensions;
	protected FileFilter ff;

	public AbstractTranslator(String[] supportedFileExtensions) {
		ff = new FileFilter(supportedFileExtensions);
		dictionaries = new ArrayList<Dictionary>(5);
	}

	@Override
	public boolean addDictionary(String path) {
		if (ff.isSupported(path)) {
			Dictionary d = initDictionary(path);
			if (d != null) {
				dictionaries.add(d);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean removeDictionary(String path) {
		Dictionary d = null;
		for (Dictionary di : dictionaries) {
			if (di.getPath().equals(path)) {
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

	@Override
	public boolean removeDictionary(int index) {
		if (isValidIndex(index)) {
			dictionaries.remove(index);
			return true;
		}
		return false;
	}

	@Override
	public int getDictionaryCount() {
		return dictionaries.size();
	}

	@Override
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

	@Override
	public Dictionary getDictionary(int index) {
		return dictionaries.get(index);
	}

	@Override
	public FileFilter getDictionaryFileFilter() {
		return ff;
	}

	protected boolean isValidIndex(int i) {
		return i >= 0 && dictionaries.size() > i;
	}

	protected abstract Dictionary initDictionary(String path);

}
