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
package wysinwyg.translator.dictionary;

import wysinwyg.Init;
import wysinwyg.translator.Translator;

public class DictionaryInit implements Init {

	private DictionaryController controller;
	private DictionaryView view;

	public DictionaryInit(Translator translator) {
		DictionaryModel model = new DictionaryModel(translator);
		view = new DictionaryView(model);
		controller = new DictionaryController(model, view);
	}

	@Override
	public DictionaryController getController() {
		return controller;
	}

	@Override
	public DictionaryView getView() {
		return view;
	}

}
