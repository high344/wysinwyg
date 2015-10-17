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

import wysinwyg.Init;

public class DeviceInit implements Init {

	private DeviceModel model;
	private DeviceView view;
	private DeviceController controller;

	public DeviceInit() {
		model = new DeviceModel();
		view = new DeviceView(model);
		controller = new DeviceController(model, view);
	}

	@Override
	public DeviceView getView() {
		return view;
	}

	public DeviceController getController() {
		return controller;
	}

}
