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
package wysinwyg.device;

import wysinwyg.utils.ComboboxDisplayName;

/**
 * The interface for defining and interacting with the {@linkplain DeviceInit}
 * and it's components.
 * 
 * @author FelfoldiB.
 *
 */
public interface Device extends ComboboxDisplayName {

	/**
	 * A {@code DeviceListener} wants to register itself to this device.
	 * 
	 * @param devListener
	 *            the aforementioned {@code DeviceListener}
	 */
	public void addDeviceListener(DeviceListener devListener);

	/**
	 * A {@code DeviceListener} wants to unregister itself from this device.
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
