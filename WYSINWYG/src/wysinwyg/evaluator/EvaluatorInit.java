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
package wysinwyg.evaluator;

import wysinwyg.Init;

public class EvaluatorInit implements Init {

	private EvaluatorModel model;
	private EvaluatorView view;
	private EvaluatorController controller;

	public EvaluatorInit() {
		model = new EvaluatorModel();
		view = new EvaluatorView(model);
		controller = new EvaluatorController(model, view);
	}

	@Override
	public EvaluatorView getView() {
		return view;
	}

	@Override
	public EvaluatorController getController() {
		return controller;
	}

}
