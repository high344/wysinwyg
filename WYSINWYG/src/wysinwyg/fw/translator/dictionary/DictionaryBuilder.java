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

import java.util.Objects;

import wysinwyg.fw.Builder;

public class DictionaryBuilder implements Builder {

	private DictionaryView view;
	private DictionaryOptions options;

	public DictionaryBuilder(DictionaryOptions options) {
		Objects.requireNonNull(options, "");
		this.options = options;
	}

	public DictionaryBuilder setDictionaryView(DictionaryView view) {
		this.view = view;
		return this;
	}

	@Override
	public DictionaryController build() {
		if (view == null) {
			view = new DictionaryView();
		}
		return new DictionaryController(options, view);
	}

	/* @formatter:off
	private List<Dictionary> dictionaries = new ArrayList<Dictionary>(5);

	public DictionaryBuilder addDictionary(Dictionary dictionary) {
		if (dictionary != null) {
			if (!dictionaries.contains(dictionary)) {
				dictionaries.add(dictionary);
			}
		}
		return this;
	}

	public DictionaryBuilder addDictionary(Dictionary... dictionary) {
		if (dictionary != null) {
			for (Dictionary d : dictionary) {
				if (!dictionaries.contains(d)) {
					dictionaries.add(d);
				}
			}
		}
		return this;
	}

	public DictionaryBuilder removeDictionary(Dictionary dictionary) {
		dictionaries.remove(dictionary);
		return this;
	}

	public DictionaryBuilder addDictionaryList(List<Dictionary> list) {
		if (list != null) {
			for (Dictionary d : list) {
				addDictionary(d);
			}
		}
		return this;
	}*/

}
