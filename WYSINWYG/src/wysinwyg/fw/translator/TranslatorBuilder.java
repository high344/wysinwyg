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

import java.util.ArrayList;
import java.util.List;

import wysinwyg.fw.Builder;

public class TranslatorBuilder implements Builder {

	private TranslatorView view;
	private List<Translator> translators = new ArrayList<Translator>(5);

	public int getTranslatorListSize() {
		return translators.size();
	}

	public TranslatorBuilder addTranslator(Translator translator) {
		if (translator != null) {
			if (!translators.contains(translator)) {
				translators.add(translator);
			}
		}
		return this;
	}

	public TranslatorBuilder removeTranslator(Translator translator) {
		translators.remove(translator);
		return this;
	}

	public TranslatorBuilder addTranslatorList(List<Translator> list) {
		if (list != null) {
			for (Translator t : list) {
				addTranslator(t);
			}
		}
		return this;
	}

	public TranslatorBuilder setTranslatorView(TranslatorView view) {
		this.view = view;
		return this;
	}

	@Override
	public TranslatorController build() {
		if (view == null) {
			view = new TranslatorView();
		}
		return new TranslatorController(translators, view);
	}

}
