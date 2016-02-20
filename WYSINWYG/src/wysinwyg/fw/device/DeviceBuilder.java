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

import java.util.ArrayList;
import java.util.List;

import wysinwyg.fw.Builder;

/**
 * Building a device MCV environment for the given {@linkplain Device devices}.
 * Calling the {@link #build()} method will create and connect the components.
 * If no {@linkplain DeviceView} is added, the builder will create one.
 * 
 * @author FelfoldiB.
 *
 */
public class DeviceBuilder implements Builder {

	private DeviceView view;
	private List<Device> devices = new ArrayList<Device>(5);

	/**
	 * 
	 * @return Size of the added devices list.
	 */
	public int getDeviceListSize() {
		return devices.size();
	}

	/**
	 * Adding a device to the list. Adding the same device multiple times has no
	 * effect.
	 * 
	 * @param device
	 *            The device to be added.
	 * @return the {@linkplain DeviceBuilder}.
	 */
	public DeviceBuilder addDevice(Device device) {
		if (device != null) {
			if (!devices.contains(device)) {
				devices.add(device);
			}
		}
		return this;
	}

	/**
	 * Remove the device from the list.
	 * 
	 * @param device
	 *            The device to be removed.
	 * @return the {@linkplain DeviceBuilder}.
	 */
	public DeviceBuilder removeDevice(Device device) {
		devices.remove(device);
		return this;
	}

	/**
	 * Adding the devices to the list. Adding the same device multiple times has
	 * no effect.
	 * 
	 * @param list
	 *            The list of devices to be added.
	 * @return the {@linkplain DeviceBuilder}.
	 */
	public DeviceBuilder addDeviceList(List<Device> list) {
		if (list != null) {
			for (Device d : list) {
				addDevice(d);
			}
		}
		return this;
	}

	/**
	 * Set the specified view for the MVC.
	 * 
	 * @param view
	 *            {@linkplain DeviceView}.
	 * @return the {@linkplain DeviceBuilder}.
	 */
	public DeviceBuilder setDeviceView(DeviceView view) {
		this.view = view;
		return this;
	}

	/**
	 * Connects the given devices with the given view.
	 * 
	 * @return a new {@link DeviceController} is created.
	 */
	@Override
	public DeviceController build() {
		if (view == null) {
			view = new DeviceView();
		}
		return new DeviceController(devices, view);
	}

}
