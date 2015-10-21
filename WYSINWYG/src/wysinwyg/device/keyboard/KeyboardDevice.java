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
package wysinwyg.device.keyboard;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import wysinwyg.Controller;
import wysinwyg.Init;
import wysinwyg.device.Device;
import wysinwyg.device.DeviceEvent;
import wysinwyg.device.DeviceListener;
import wysinwyg.device.keyboard.hook.AbstractKeyboardHook;
import wysinwyg.device.keyboard.hook.KeyboardHookWin32;

public class KeyboardDevice implements Keyboard, Init, Device, DeviceListener {

	private KeyboardView view;
	private AbstractKeyboardHook hook;
	private List<DeviceListener> list;

	public KeyboardDevice() {
		list = new ArrayList<DeviceListener>(10);
		view = new KeyboardView();
		// TODO
		// hook = new KeyboardHookJNativeHook(this);
		hook = new KeyboardHookWin32(this);
	}

	@Override
	public AbstractKeyboardHook getHook() {
		return hook;
	}

	@Override
	public JComponent getView() {
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
		hook.setEchoEnabled(view.getChckbxEcho().isSelected());
		hook.enableHook();
	}

	@Override
	public void stopDevice() {
		hook.disableHook();
	}

	@Override
	public void deviceEventOccurred(DeviceEvent e) {
		for (DeviceListener d : list) {
			d.deviceEventOccurred(e);
		}
	}

}
