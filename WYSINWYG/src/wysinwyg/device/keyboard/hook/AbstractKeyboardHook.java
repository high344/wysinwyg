package wysinwyg.device.keyboard.hook;

import wysinwyg.device.DeviceEvent;
import wysinwyg.device.DeviceListener;
import wysinwyg.printer.Printer;

public abstract class AbstractKeyboardHook implements DeviceListener {

	protected DeviceListener devListener;
	protected boolean echo = false;
	protected Printer printer;
	protected int last = 0;
	
	public AbstractKeyboardHook(DeviceListener devListener) {
		this.devListener = devListener;
	}
	
	@Override
	public void deviceEventOccurred(DeviceEvent e) {
		if(printer != null) {
			if(printer.isDeviceEventVirtual(e)) {
				e.setConsumeEnabled(false);
				return;
			}
		}
		if (e.getKeyState() == DeviceEvent.DEVICE_KEY_PRESSED) {
			if (echo) {
				devListener.deviceEventOccurred(e);
			} else {
				if (last != e.getScanCode()) {
					last = e.getScanCode();
					devListener.deviceEventOccurred(e);
				}
				e.setConsumeEnabled(true);
			}
		} else if (e.getKeyState() == DeviceEvent.DEVICE_KEY_RELEASED) {
			if (last == e.getScanCode()) {
				last = 0;
			}
			devListener.deviceEventOccurred(e);
		}
	}
	
	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
	
	public void setEchoEnabled(boolean b) {
		this.echo = b;
	}

	public abstract void enableHook();

	public abstract void disableHook();

}
