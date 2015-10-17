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
package wysinwyg.device;

import wysinwyg.device.keyboard.KeyboardDevice;
import wysinwyg.device.serial.stentura.StenturaDevice;

public class DeviceModel {

	private Device[] devices = new Device[2];

	public DeviceModel() {
		load();
	}

	private void load() {
		devices[1] = new StenturaDevice();
		devices[0] = new KeyboardDevice();
	}

	public Device[] getDevices() {
		return devices;
	}

}
