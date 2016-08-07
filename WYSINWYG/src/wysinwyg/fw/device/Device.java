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
package wysinwyg.fw.device;

import wysinwyg.fw.Viewable;
import wysinwyg.fw.evaluator.Evaluator;
import wysinwyg.utils.renderer.ComboboxDisplayName;

/**
 * 
 * @author FelfoldiB.
 *
 */
public interface Device extends Viewable, ComboboxDisplayName {

	public void setEvaluator(Evaluator eva);

	/**
	 * Register a {@code DeviceListener} to the device.
	 * 
	 * @param devListener
	 *            the aforementioned {@code DeviceListener}
	 */
	public void addDeviceListener(DeviceListener devListener);

	/**
	 * Unregister a {@code DeviceListener} from the device.
	 * 
	 * @param devListener
	 *            the aforementioned {@code DeviceListener}
	 */
	public void removeDeviceListener(DeviceListener devListener);

	/**
	 * Starting the device.
	 */
	public void startDevice();

	/**
	 * Stopping the device.
	 */
	public void stopDevice();

}
