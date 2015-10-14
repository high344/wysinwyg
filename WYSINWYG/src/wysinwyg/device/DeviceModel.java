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
