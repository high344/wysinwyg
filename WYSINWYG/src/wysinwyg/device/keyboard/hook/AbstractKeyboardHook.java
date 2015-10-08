package wysinwyg.device.keyboard.hook;

import wysinwyg.device.DeviceEvent;
import wysinwyg.device.DeviceListener;

public abstract class AbstractKeyboardHook implements DeviceListener {

	protected int last = 0;
	protected boolean echo = false;
	protected DeviceListener devListener;

	public AbstractKeyboardHook(DeviceListener devListener, boolean echo) {
		this.devListener = devListener;
		this.echo = echo;
	}

	@Override
	public void deviceEventOccurred(DeviceEvent e) {
		if (e.getKeyState() == DeviceEvent.DEVICE_KEY_PRESSED) {
			if (echo) {
				devListener.deviceEventOccurred(e);
			} else {
				if (last != e.getScanCode()) {
					last = e.getScanCode();
					devListener.deviceEventOccurred(e);
				}
			}
		} else if (e.getKeyState() == DeviceEvent.DEVICE_KEY_RELEASED) {
			if (last == e.getScanCode()) {
				last = 0;
			}
			devListener.deviceEventOccurred(e);
		}
	}

	public abstract void enableHook();

	public abstract void disableHook();

}
