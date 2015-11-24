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

import wysinwyg.fb.translator.plover.PloverTranslator;
import wysinwyg.fw.Model;
import wysinwyg.fw.translator.dictionary.DictionaryInit;

/**
 * Loading the available translators and initializing a
 * {@linkplain DictionaryInit dictionary}.
 * 
 * @author FelfoldiB.
 *
 */
public class TranslatorModel implements Model {

	private Translator[] trans = new Translator[1];
	private DictionaryInit dictionary;

	/**
	 * Load translators: {@linkplain PloverTranslator}. Initialize a
	 * {@linkplain DictionaryInit dictionary}.
	 */
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
