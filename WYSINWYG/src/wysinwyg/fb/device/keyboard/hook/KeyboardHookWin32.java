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

import wysinwyg.fb.device.keyboard.KeyboardEvent;
import wysinwyg.fb.device.keyboard.KeyboardKeyState;
import wysinwyg.fw.device.DeviceListener;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.KBDLLHOOKSTRUCT;
import com.sun.jna.platform.win32.WinUser.LowLevelKeyboardProc;
import com.sun.jna.platform.win32.WinUser.MSG;

public class KeyboardHookWin32 extends AbstractKeyboardHook {

	private HHOOK hhk;
	private User32 lib = User32.INSTANCE;
	private Thread thread;
	private boolean stop;

	public KeyboardHookWin32(DeviceListener devListener) {
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
				HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
				LowLevelKeyboardProc keyboardHook = setLowLevelKeyboardProc();
				hhk = lib.SetWindowsHookEx(WinUser.WH_KEYBOARD_LL, keyboardHook, hMod, 0);
				System.out.println("Keyboard hook set...");
				// boolean result;
				MSG msg = new MSG();
				while (!stop) {
					// TODO getMessage
					lib.PeekMessage(msg, null, 0, 0, 1);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					/* @formatter:off
					if (result == -1) {
						System.err.println("error in get message");
						break;
					} else {
						System.err.println("got message");
						lib.TranslateMessage(msg);
						lib.DispatchMessage(msg);
					}*/

				}
				thread = null;
				stop = false;
				System.out.println("Keyboard hook stopped...");
				lib.UnhookWindowsHookEx(hhk);
			}
		};
	}

	private LowLevelKeyboardProc setLowLevelKeyboardProc() {
		return new LowLevelKeyboardProc() {

			@Override
			public LRESULT callback(int nCode, WPARAM wParam, KBDLLHOOKSTRUCT info) {
				KeyboardKeyState state = null;

				if (nCode >= 0) {
					switch (wParam.intValue()) {
					case WinUser.WM_KEYUP:
					case WinUser.WM_SYSKEYUP:
						state = KeyboardKeyState.DEVICE_KEY_RELEASED;
						break;
					case WinUser.WM_KEYDOWN:
					case WinUser.WM_SYSKEYDOWN:
						state = KeyboardKeyState.DEVICE_KEY_PRESSED;
						break;
					}
				}

				KeyboardEvent event = new KeyboardEvent(this, info.vkCode, info.scanCode, state);
				keyboardEventOccurred(event);

				if (event.isConsumable()) {
					return new LRESULT(1);
				} else {
					Pointer ptr = info.getPointer();
					long peer = Pointer.nativeValue(ptr);
					return lib.CallNextHookEx(hhk, nCode, wParam, new LPARAM(peer));
				}
			}
		};
	}

}
