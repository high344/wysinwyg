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
package wysinwyg.fb.evaluator;

import java.util.Arrays;
import java.util.List;

import wysinwyg.fb.evaluator.steno.StenoEvaluator;
import wysinwyg.fb.evaluator.steno.StenoReferenceWin32;
import wysinwyg.fw.evaluator.Evaluator;

public class Evaluators {

	private Evaluator[] evas = new Evaluator[1];

	/**
	 * Load devices: {@linkplain StenoEvaluator}.
	 */
	public Evaluators() {
		load();
	}

	private void load() {
		evas[0] = new StenoEvaluator(new StenoReferenceWin32());
	}

	public List<Evaluator> getEvaluators() {
		return Arrays.asList(evas);
	}

}
