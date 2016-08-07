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

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEvaluator implements Evaluator {

	protected List<EvaluationListener> list;

	public AbstractEvaluator() {
		list = new ArrayList<EvaluationListener>(3);
	}

	/**
	 * A {@code EvaluationListener} wants to register itself to this device.
	 * 
	 * @param devListener
	 *            the aforementioned {@code EvaluationListener}
	 */
	public void addEvaluationListener(EvaluationListener evaListener) {
		list.add(evaListener);
	}

	/**
	 * A {@code EvaluationListener} wants to unregister itself from this device.
	 * 
	 * @param devListener
	 *            the aforementioned {@code EvaluationListener}
	 */
	public void removeEvaluationListener(EvaluationListener evaListener) {
		list.remove(evaListener);
	}

	@Override
	public void evaluationEventOccurred(EvaluationEvent ee) {
		for (EvaluationListener eva : list) {
			eva.evaluationEventOccurred(ee);
		}
	}

}
