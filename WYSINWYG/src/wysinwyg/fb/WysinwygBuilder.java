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
package wysinwyg.fb;

import java.util.Arrays;

import wysinwyg.fb.device.keyboard.KeyboardDevice;
import wysinwyg.fb.device.stentura.StenturaDevice;
import wysinwyg.fw.Builder;
import wysinwyg.fw.device.Device;
import wysinwyg.fw.device.DeviceBuilder;
import wysinwyg.fw.device.DeviceController;

public class WysinwygBuilder implements Builder {

	@Override
	public WysinwygController build() {
		WysinwygView view = new WysinwygView();

		Device[] devices = new Device[2];
		devices[0] = new KeyboardDevice();
		devices[1] = new StenturaDevice();
		DeviceController deviceController = new DeviceBuilder().setDeviceView(view.getDeviceView())
				.addDeviceList(Arrays.asList(devices)).build();

		WysinwygController controller = new WysinwygController(view);
		controller.setDeviceController(deviceController);

		return controller;
	}

}
