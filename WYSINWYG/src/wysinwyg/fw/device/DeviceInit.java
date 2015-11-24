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

import wysinwyg.fw.Init;

/**
 * Initializing the {@linkplain Device} components for an MVC environment.
 * 
 * @author FelfoldiB.
 *
 */
public class DeviceInit implements Init {

	private DeviceModel model;
	private DeviceView view;
	private DeviceController controller;

	/**
	 * Setting up an MVC with no devices.
	 */
	public DeviceInit() {
		this(null);
	}

	/**
	 * Setting up an MVC with the added {@linkplain Device} array.
	 */
	public DeviceInit(Device[] devices) {
		model = new DeviceModel(devices);
		view = new DeviceView(model);
		controller = new DeviceController(model, view);
	}

	/**
	 * Returns the {@linkplain DeviceModel} of the MVC.
	 */
	@Override
	public DeviceModel getModel() {
		return model;
	}

	/**
	 * Returns the {@linkplain DeviceView} of the MVC.
	 */
	@Override
	public DeviceView getView() {
		return view;
	}

	/**
	 * Returns the {@linkplain DeviceController} of the MVC.
	 */
	@Override
	public DeviceController getController() {
		return controller;
	}

}
