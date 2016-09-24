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
package wysinwyg.fb.device;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import wysinwyg.fb.WysinwygPath;
import wysinwyg.fb.device.keyboard.KeyboardDevice;
import wysinwyg.fb.device.stentura.StenturaDevice;
import wysinwyg.fw.device.Device;
import wysinwyg.utils.ErrorMessage;
import wysinwyg.utils.PropertiesUtils;

public class Devices {

	public static final File DEFAULT_DEVICES_file;

	static {
		DEFAULT_DEVICES_file = new File(WysinwygPath.getHome() + File.separator + "devices.properties");
		if (!DEFAULT_DEVICES_file.exists()) {

			Properties prop = new Properties();
			prop.put("device1", KeyboardDevice.class.getName());
			prop.put("device2", StenturaDevice.class.getName());
			try {
				PropertiesUtils
						.saveProperties(
								prop,
								"This file can be edited freely by adding a unique key (name is not important) and a device implementing the \"wysinwyg.fw.device.Device\" interface.",
								DEFAULT_DEVICES_file);
			} catch (IOException e) {
				ErrorMessage.show(e, Boolean.parseBoolean(System.getProperty("wysinwyg.devices")));
			}
		}
	}

	private File propertiesFile;

	public Devices() {
		this(DEFAULT_DEVICES_file);
	}

	public Devices(File propertiesFile) {
		this.propertiesFile = propertiesFile;
	}

	public List<Device> readUpDevices() {
		return PropertiesUtils.<Device> readUpObjects(propertiesFile,
				Boolean.parseBoolean(System.getProperty("wysinwyg.devices")));
	}

}
