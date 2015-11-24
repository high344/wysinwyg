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
package wysinwyg.fw.evaluator;

import wysinwyg.fb.evaluator.steno.StenoEvaluator;
import wysinwyg.fb.evaluator.steno.StenoReferenceWin32;
import wysinwyg.fw.Model;

/**
 * Loading the available evaluators.
 * 
 * @author FelfoldiB.
 *
 */
public class EvaluatorModel implements Model {

	private Evaluator[] evas = new Evaluator[1];

	/**
	 * Load devices: {@linkplain StenoEvaluator}.
	 */
	public EvaluatorModel() {
		load();
	}

	private void load() {
		evas[0] = new StenoEvaluator(new StenoReferenceWin32());
	}

	public Evaluator[] getEvaluators() {
		return evas;
	}

}
