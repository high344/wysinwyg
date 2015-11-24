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

import wysinwyg.fw.Model;

/**
 * Stores the available devices for the MVC component.
 * 
 * @author FelfoldiB.
 *
 */
public class DeviceModel implements Model {

	protected Device[] devices;
	protected DeviceController controller;

	/**
	 * Create a {@linkplain DeviceModel} with not specified devices.
	 */
	public DeviceModel() {
		this(null);
	}

	/**
	 * Create a {@linkplain DeviceModel} with the added {@linkplain Device}
	 * array.
	 * 
	 * @param devices
	 *            an array of devices.
	 */
	public DeviceModel(Device[] devices) {
		this.devices = devices;
	}

	/**
	 * Set the {@linkplain Device} array for this model. If the controller for
	 * this model is not {@code null} the
	 * {@link DeviceController#modelChanged()} method is called.
	 * 
	 * @param devices
	 *            an array of devices.
	 */
	public void setDevices(Device[] devices) {
		this.devices = devices;
		if (controller != null) {
			controller.modelChanged();
		}
	}

	/**
	 * Get the {@linkplain Device} array of this model.
	 * 
	 * @return an array of devices, can be {@code null}.
	 */
	public Device[] getDevices() {
		return devices;
	}

	/**
	 * Set the {@linkplain DeviceController} for this model.
	 * 
	 * @param controller
	 */
	protected void setController(DeviceController controller) {
		this.controller = controller;
	}

}
