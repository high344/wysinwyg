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

import wysinwyg.fw.Init;
import wysinwyg.fw.Model;
import wysinwyg.fw.translator.Translator;

public class DictionaryInit implements Init {

	private DictionaryModel model;
	private DictionaryController controller;
	private DictionaryView view;

	public DictionaryInit(Translator translator) {
		model = new DictionaryModel(translator);
		view = new DictionaryView(model);
		controller = new DictionaryController(model, view);
	}

	@Override
	public Model getModel() {
		return model;
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
