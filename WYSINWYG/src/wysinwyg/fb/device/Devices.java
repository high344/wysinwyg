package wysinwyg.fb.device;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
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
				PropertiesUtils.saveProperties(prop, DEFAULT_DEVICES_file);
			} catch (IOException e) {
				ErrorMessage.show(e, Boolean.parseBoolean(System.getProperty("wysinwyg.devices")));
			}
		}
	}

	private File propertiesFile;

	public Devices() {
		propertiesFile = DEFAULT_DEVICES_file;
	}

	public Devices(File propertiesFile) {
		this.propertiesFile = propertiesFile;
	}

	public List<Device> readUpDevices() {
		Properties prop = null;
		try {
			prop = PropertiesUtils.loadProperties(propertiesFile);
		} catch (IOException e) {
			ErrorMessage.show(e, Boolean.parseBoolean(System.getProperty("wysinwyg.devices")));
			return null;
		}

		List<Device> arr = new ArrayList<Device>(prop.size());
		for (Entry<Object, Object> p : prop.entrySet()) {
			try {
				Class<?> c = Class.forName((String) p.getValue());
				arr.add((Device) c.newInstance());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				ErrorMessage.show(e, Boolean.parseBoolean(System.getProperty("wysinwyg.devices")));
			}
		}
		return arr;
	}
	
}
