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

import wysinwyg.Init;

/**
 * Initializing the Device components.
 * 
 * @author FelfoldiB.
 *
 */
public class DeviceInit implements Init {

	private DeviceModel model;
	private DeviceView view;
	private DeviceController controller;

	/**
	 * Setting up an MVC.
	 */
	public DeviceInit() {
		model = new DeviceModel();
		view = new DeviceView(model);
		controller = new DeviceController(model, view);
	}

	@Override
	public DeviceView getView() {
		return view;
	}

	@Override
	public DeviceController getController() {
		return controller;
	}

}
