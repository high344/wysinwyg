package wysinwyg.device;

import wysinwyg.Init;
import wysinwyg.device.keyboard.KeyboardDevice;
import wysinwyg.device.serial.stentura.StenturaDevice;

public class DeviceInit implements Init {

	private DeviceView view;
	private DeviceController controller;

	public DeviceInit() {
		Device[] devices = load();
		view = new DeviceView();
		controller = new DeviceController(devices, view);
	}

	private Device[] load() {
		Device[] devices = new Device[2];
		devices[1] = new StenturaDevice();
		devices[0] = new KeyboardDevice();
		return devices;
	}

	@Override
	public DeviceView getView() {
		return view;
	}

	public DeviceController getController() {
		return controller;
	}

}
