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
package wysinwyg.fb.device.keyboard.hook;

import wysinwyg.fw.device.DeviceEvent;
import wysinwyg.fw.device.DeviceListener;
import wysinwyg.fw.printer.Printer;

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
		if (printer != null) {
			if (printer.isDeviceEventVirtual(e)) {
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
