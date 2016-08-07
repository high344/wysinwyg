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

import wysinwyg.fw.Init;
import wysinwyg.fw.Model;

/**
 * Initializing the Translator components.
 * 
 * @author FelfoldiB.
 *
 */
public class TranslatorInit implements Init {

	private TranslatorModel model;
	private TranslatorView view;
	private TranslatorController controller;

	/**
	 * Setting up an MVC.
	 */
	public TranslatorInit() {
		model = new TranslatorModel();
		view = new TranslatorView(model);
		controller = new TranslatorController(model, view);
	}

	@Override
	public Model getModel() {
		return model;
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
