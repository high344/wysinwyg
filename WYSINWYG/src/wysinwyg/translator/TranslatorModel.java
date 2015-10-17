/*******************************************************************************
 * Copyright (c) 2015 Balázs Felföldi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Balázs Felföldi - initial API and implementation
 ******************************************************************************/
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
