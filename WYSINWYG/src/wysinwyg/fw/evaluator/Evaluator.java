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

import wysinwyg.fw.device.DeviceListener;
import wysinwyg.utils.ComboboxDisplayName;

/**
 * The interface for defining and interacting with the
 * {@linkplain EvaluatorInit} and it's components.
 * 
 * @author FelfoldiB.
 *
 */
public interface Evaluator extends DeviceListener, ComboboxDisplayName {

	/**
	 * A {@code EvaluationListener} wants to register itself to this device.
	 * 
	 * @param devListener
	 *            the aforementioned {@code EvaluationListener}
	 */
	public void addEvaluationListener(EvaluationListener evaListener);

	/**
	 * A {@code EvaluationListener} wants to unregister itself from this device.
	 * 
	 * @param devListener
	 *            the aforementioned {@code EvaluationListener}
	 */
	public void removeEvaluationListener(EvaluationListener evaListener);

	/**
	 * Starting the evaluation.
	 */
	public void startEvaluation();

	/**
	 * Stopping the evaluation.
	 */
	public void stopEvaluation();

}
