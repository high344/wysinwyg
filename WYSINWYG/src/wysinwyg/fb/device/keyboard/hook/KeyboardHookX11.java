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

import wysinwyg.fw.device.DeviceListener;

import com.sun.jna.NativeLong;
import com.sun.jna.platform.unix.X11;
import com.sun.jna.platform.unix.X11.Display;
import com.sun.jna.platform.unix.X11.XEvent;
import com.sun.jna.platform.unix.X11.XKeyEvent;

public class KeyboardHookX11 extends AbstractKeyboardHook {

	private X11 lib = X11.INSTANCE;
	private Thread thread;
	private boolean stop;

	public KeyboardHookX11(DeviceListener devListener) {
		super(devListener);
		thread = createThread();
	}

	@Override
	public void enableHook() {
		if (thread == null) {
			thread = createThread();
		}
		thread.start();
	}

	@Override
	public void disableHook() {
		stop = true;
	}

	private Thread createThread() {
		return new Thread(setHook(), "KeyHook");
	}

	private Runnable setHook() {
		return new Runnable() {

			@Override
			public void run() {
				Display display = lib.XOpenDisplay(null);
				lib.XGrabKeyboard(display, lib.XDefaultRootWindow(display), 1, X11.GrabModeAsync,
						X11.GrabModeAsync, new NativeLong(X11.CurrentTime));

				System.out.println("Keyboard hook set...");

				while (!stop) {
					XEvent ev = new XEvent();
					lib.XNextEvent(display, ev);
					XKeyEvent key = (XKeyEvent) ev.readField("xkey");

					if (ev.type == X11.KeyPress) {
						System.out.println("KeyPressed: " + key.keycode);

					} else if (ev.type == X11.KeyRelease) {
						System.out.println("KeyReleased: " + key.keycode);
					}

				}

				lib.XUngrabKeyboard(display, new NativeLong(X11.CurrentTime));
				lib.XCloseDisplay(display);
				thread = null;
				stop = false;
				System.out.println("Keyboard hook stopped...");
			}
		};
	}

}
