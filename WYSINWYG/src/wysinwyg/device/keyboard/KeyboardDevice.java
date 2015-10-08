package wysinwyg.device.keyboard;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import wysinwyg.Controller;
import wysinwyg.device.Device;
import wysinwyg.device.DeviceEvent;
import wysinwyg.device.DeviceListener;
import wysinwyg.device.keyboard.hook.AbstractKeyboardHook;
import wysinwyg.device.keyboard.hook.KeyboardHookWin32;

public class KeyboardDevice implements Device, DeviceListener {

	private KeyboardView view;
	private AbstractKeyboardHook hook;
	private List<DeviceListener> list;

	public KeyboardDevice() {
		list = new ArrayList<DeviceListener>(10);
		view = new KeyboardView();
	}

	@Override
	public Component getView() {
		return view;
	}

	@Override
	public Controller getController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDisplayName() {
		return "NKRO Keyboard";
	}

	@Override
	public void addDeviceListener(DeviceListener devListener) {
		list.add(devListener);
	}

	@Override
	public void removeDeviceListener(DeviceListener devListener) {
		list.remove(devListener);
	}

	@Override
	public void startDevice() {
		// TODO
		hook = new KeyboardHookWin32(this, view.chckbxEcho.isSelected());
		if (hook != null) {
			hook.enableHook();
		}
	}

	@Override
	public void stopDevice() {
		if (hook != null) {
			hook.disableHook();
		}
	}

	@Override
	public void deviceEventOccurred(DeviceEvent e) {
		for (DeviceListener d : list) {
			d.deviceEventOccurred(e);
		}
	}

}
