/*******************************************************************************
 * Copyright (c) 2015 Balazs Felfoldi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Balazs Felfoldi - initial API and implementation
 ******************************************************************************/
package wysinwyg.fw.translator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import wysinwyg.fw.translator.dictionary.Dictionary;
import wysinwyg.utils.FileFilter;

public abstract class AbstractTranslator implements Translator, TranslationListener {

	protected List<Dictionary> dictionaries;
	protected String[] supportedExtensions;
	protected FileFilter ff;
	protected List<TranslationListener> list;

	public AbstractTranslator(String[] supportedFileExtensions) {
		dictionaries = new ArrayList<Dictionary>(5);
		ff = new FileFilter(supportedFileExtensions);
		list = new ArrayList<TranslationListener>(10);
	}

	@Override
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

	@Override
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

	@Override
	public boolean loadUpDictionary(int index) {
		if (isValidIndex(index)) {
			dictionaries.get(index).loadDictionary();
			return true;
		}
		return false;
	}

	@Override
	public void loadUpAllDictionary() {
		for (Dictionary di : dictionaries) {
			di.loadDictionary();
		}
	}

	protected boolean isValidIndex(int i) {
		return i >= 0 && dictionaries.size() > i;
	}

	@Override
	public void addTranslationListener(TranslationListener transListener) {
		list.add(transListener);
	}

	@Override
	public void removeTranslationListener(TranslationListener transListener) {
		list.remove(transListener);
	}

	@Override
	public void translationEventOccurred(TranslationEvent e) {
		for (TranslationListener trans : list) {
			trans.translationEventOccurred(e);
		}
	}

	protected abstract Dictionary initDictionary(File file);

}
