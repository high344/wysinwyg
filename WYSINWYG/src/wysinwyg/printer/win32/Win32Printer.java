package wysinwyg.printer.win32;

import java.util.concurrent.LinkedBlockingQueue;

import wysinwyg.device.DeviceEvent;
import wysinwyg.printer.Printer;
import wysinwyg.printer.PrinterEvent;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser.INPUT;
import com.sun.jna.platform.win32.WinUser.KEYBDINPUT;
import com.sun.jna.win32.W32APIOptions;

public class Win32Printer implements Printer {

	private User32 user32 = (User32) Native.loadLibrary("user32", User32.class,
			W32APIOptions.DEFAULT_OPTIONS);
	private Thread thread;
	private LinkedBlockingQueue<PrinterEvent> queue = new LinkedBlockingQueue<PrinterEvent>();

	private static Win32Printer printer;

	public static Win32Printer getInstance() {
		if (printer == null) {
			printer = new Win32Printer();
		}
		return printer;
	}

	private Win32Printer() {
		thread = new Thread(createPrinterThread(user32), "Win32Printer");
		thread.start();
	}

	private Runnable createPrinterThread(final User32 user32) {
		return new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						PrinterEvent eve = queue.take();
						if (eve.isCommand()) {
							sendCommand(eve.getCommand());
						} else {
							int l = eve.getPrint().length();
							for (int i = 0, ch = 0; i < l; i += Character.charCount(ch)) {
								ch = eve.getPrint().codePointAt(i);
								sendCharacter(ch);
							}
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

			private void sendCharacter(int ch) {
				int chc = Character.charCount(ch);
				INPUT[] in = (INPUT[]) new INPUT().toArray(2);
				if (chc == 1) {
					setInput(in[0], 0, ch, KEYBDINPUT.KEYEVENTF_UNICODE);
					setInput(in[1], 0, ch, KEYBDINPUT.KEYEVENTF_UNICODE
							| KEYBDINPUT.KEYEVENTF_KEYUP);
				} else if (chc == 2) {
					setInput(in[0], 0, Character.highSurrogate(ch), KEYBDINPUT.KEYEVENTF_UNICODE);
					setInput(in[1], 0, Character.lowSurrogate(ch), KEYBDINPUT.KEYEVENTF_UNICODE);
				} else {

				}
				user32.SendInput(2, in, in[0].size());
			}

			private void sendCommand(int ch) {
				INPUT[] in = (INPUT[]) new INPUT().toArray(2);
				int sc = user32.MapVirtualKey(ch, 0);
				setInput(in[0], 0, sc, KEYBDINPUT.KEYEVENTF_SCANCODE);
				setInput(in[1], 0, sc, KEYBDINPUT.KEYEVENTF_SCANCODE | KEYBDINPUT.KEYEVENTF_KEYUP);
				user32.SendInput(2, in, in[0].size());
			}

			private void setInput(INPUT in, int wVk, int wScan, int dwFlags) {
				in.type = new WinDef.DWORD(INPUT.INPUT_KEYBOARD);
				in.input = new INPUT.INPUT_UNION();
				in.input.setType(KEYBDINPUT.class);
				in.input.ki.wVk = new WinDef.WORD(wVk);
				in.input.ki.wScan = new WinDef.WORD(wScan);
				in.input.ki.dwFlags = new WinDef.DWORD(dwFlags);
			}

		};

	}

	@Override
	public void addPrinterEvent(PrinterEvent e) {
		queue.add(e);
	}

	@Override
	public boolean isDeviceEventVirtual(DeviceEvent e) {
		if(e.getvKeyCode() == 231) {
			return true;
		}
		return false;
	}

}
