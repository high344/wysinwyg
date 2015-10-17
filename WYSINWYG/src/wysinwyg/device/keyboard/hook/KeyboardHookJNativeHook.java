/*******************************************************************************
 * Copyright (c) 2015 Balázs Felföldi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Balázs Felföldi - initial API and implementation
 ******************************************************************************/
package wysinwyg.device.keyboard.hook;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import wysinwyg.device.DeviceEvent;
import wysinwyg.device.DeviceListener;

public class KeyboardHookJNativeHook extends AbstractKeyboardHook implements NativeKeyListener {

	private Field f;

	public KeyboardHookJNativeHook(DeviceListener devListener) {
		super(devListener);
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.WARNING);
		GlobalScreen.setEventDispatcher(new DispatchService());
		GlobalScreen.addNativeKeyListener(this);
		try {
			f = NativeInputEvent.class.getDeclaredField("reserved");
			f.setAccessible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enableHook() {
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void disableHook() {
		try {
			GlobalScreen.unregisterNativeHook();
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent nke) {
		try {
			DeviceEvent event = new DeviceEvent(this, nke.getKeyCode(), nke.getRawCode(),
					DeviceEvent.DEVICE_KEY_PRESSED);
			deviceEventOccurred(event);
			if (event.isConsumeEnabled()) {
				f.setShort(nke, (short) 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent nke) {
		try {
			DeviceEvent event = new DeviceEvent(this, nke.getKeyCode(), nke.getRawCode(),
					DeviceEvent.DEVICE_KEY_RELEASED);
			deviceEventOccurred(event);
			if (event.isConsumeEnabled()) {
				f.setShort(nke, (short) 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent nke) {

	}

}
