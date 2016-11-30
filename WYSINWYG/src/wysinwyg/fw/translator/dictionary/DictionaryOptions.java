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
package wysinwyg.fw.translator.dictionary;

import java.io.File;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;

import wysinwyg.utils.FileFilter;

public interface DictionaryOptions {

	public List<Dictionary> getDictionaries();

	public FileFilter getDictionaryFileFilter();

	public Dictionary initDictironay(File f);

	public List<SimpleEntry<String, String>> loadDictionary(Dictionary dictionary);

	public boolean saveDictionary(Dictionary dictionary);

	public boolean addDictionaryEntry(Dictionary dictionary, Integer row, String key, String value);

	public boolean removeDictionaryEntry(Dictionary dictionary, Integer row, String key);

	public void dictionaryOrderChanged(List<Dictionary> dictionaries);

}
