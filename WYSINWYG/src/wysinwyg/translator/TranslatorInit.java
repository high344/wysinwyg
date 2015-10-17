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

import wysinwyg.Init;

public class TranslatorInit implements Init {

	private TranslatorModel model;
	private TranslatorView view;
	private TranslatorController controller;

	public TranslatorInit() {
		model = new TranslatorModel();
		view = new TranslatorView(model);
		controller = new TranslatorController(model, view);
	}

	@Override
	public TranslatorView getView() {
		return view;
	}

	@Override
	public TranslatorController getController() {
		return controller;
	}

}
