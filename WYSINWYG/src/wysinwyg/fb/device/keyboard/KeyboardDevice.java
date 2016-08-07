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
package wysinwyg.fb.device.keyboard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import javax.swing.JPanel;

import wysinwyg.fb.WysinwygPath;
import wysinwyg.fb.device.keyboard.hook.AbstractKeyboardHook;
import wysinwyg.fb.device.keyboard.hook.KeyboardHookWin32;
import wysinwyg.fb.device.keyboard.hook.KeyboardHookX11;
import wysinwyg.fw.device.Device;
import wysinwyg.fw.device.DeviceEvent;
import wysinwyg.fw.device.DeviceListener;
import wysinwyg.fw.evaluator.Evaluator;

public class KeyboardDevice implements Device, DeviceListener {

	private KeyboardView view;
	private AbstractKeyboardHook hook;
	private List<DeviceListener> list;
	private int rawPower;
	private Deque<Integer> array = new ArrayDeque<Integer>(100);
	private Evaluator eva;

	public KeyboardDevice() {
		list = new ArrayList<DeviceListener>(10);
		view = new KeyboardView();
		if ("win".equals(WysinwygPath.getOS())) {
			hook = new KeyboardHookWin32(this);
		} else if ("linux".equals(WysinwygPath.getOS())) {
			hook = new KeyboardHookX11(this);
		} else {
			// TODO not supported
		}
	}

	public AbstractKeyboardHook getHook() {
		return hook;
	}

	@Override
	public void setEvaluator(Evaluator eva) {
		this.eva = eva;
	}

	@Override
	public JPanel getView() {
		return view;
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
		KeyboardEvent ke = (KeyboardEvent) e;
		if (ke.getKeyState() == KeyboardKeyState.DEVICE_KEY_PRESSED) {
			rawPower += ke.getvKeyCode();
			array.add(ke.getvKeyCode());
		} else if (ke.getKeyState() == KeyboardKeyState.DEVICE_KEY_RELEASED) {
			rawPower -= ke.getvKeyCode();
			if (rawPower < 0) {
				rawPower = 0;
			}
			if (rawPower == 0 && !view.getChckbxArpeggiate().isSelected()) {
				if (eva != null) {
					eva.evaluateCode(array);
					array.clear();
				}
			} else if (view.getChckbxArpeggiate().isSelected() && ke.getvKeyCode() == 32) {
				if (eva != null) {
					eva.evaluateCode(array);
					array.clear();
				}
			}
		}
	}

}
