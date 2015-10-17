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

import wysinwyg.device.DeviceListener;
import wysinwyg.utils.ComboboxDisplayName;

public interface Evaluator extends DeviceListener, ComboboxDisplayName {

	public void addEvaluationListener(EvaluationListener evaListener);

	public void removeEvaluationListener(EvaluationListener evaListener);

	public void startEvaluation();

	public void stopEvaluation();

}
