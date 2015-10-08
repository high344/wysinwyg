package wysinwyg.printer;

import java.util.concurrent.LinkedBlockingQueue;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.INPUT;
import com.sun.jna.platform.win32.WinUser.KEYBDINPUT;
import com.sun.jna.win32.W32APIOptions;

public class WinPrinter implements Printer {
	
	public static void main(String[] args) {
		WinPrinter win = new WinPrinter();
		
		//win.print("B4¼72ÿ!?=ˇ├ਇh B4¼72ÿ!?𒋲=ˇ├ਇh B4¼7𒋲2ÿ!?=ˇ├ਇh╔𒋲", 0);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<10;i++) {
			//win.print("🙈", 0);
			win.print("🙉", 0);
		}
		/*
		for(int i=0;i<10;i++) {
			win.print("!?=ˇ├ਇasdafs!?=ˇ├ਇggssgdsdg   sagd!?=ˇ├ਇ", 0);
			
		}*/
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*win.print("", 30);*/
		//win.print(" ", 0);
		//win.print("", 1);
	}
	
	public interface User32 extends Library {
		public int SendInput(int nInputs, WinUser.INPUT[] pInputs, int cbsize);
		public int MapVirtualKey(int uCode, int uMapType);
	}

	private class Event {

		private String print;
		private int delete;

		public Event(String print, int delete) {
			this.print = print;
			this.delete = delete;
		}

	}

	private User32 user32 = (User32) Native.loadLibrary("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);
	private int delete = 8;
	private Thread printer; // Deamon?
	private LinkedBlockingQueue<Event> queue = new LinkedBlockingQueue<Event>();

	public WinPrinter() {
		printer = new Thread(createPrinterThread(user32), "WinPrinter");
		printer.start();
	}

	private Runnable createPrinterThread(final User32 user32) {
		return new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Event eve = queue.take();

						for(int i = 0; i < eve.delete; i++) {
							sendCommand(delete);
						}
						
						int l = eve.print.length();
						for (int i = 0, ch = 0; i < l; i += Character.charCount(ch)) {
							ch = eve.print.codePointAt(i);
							sendCharacter(ch);
						}
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
			
			private void sendCharacter(int ch) {
				int chc = Character.charCount(ch);
				
				WinUser.INPUT[] in;
				if (chc == 1) {
					in = (WinUser.INPUT[]) new WinUser.INPUT().toArray(2);
					setINPUT(in[0], 0, ch, WinUser.KEYBDINPUT.KEYEVENTF_UNICODE);
					setINPUT(in[1], 0, ch, WinUser.KEYBDINPUT.KEYEVENTF_UNICODE | WinUser.KEYBDINPUT.KEYEVENTF_KEYUP);
					user32.SendInput(2, in, in[0].size());
				} else if(chc == 2) {
					in = (WinUser.INPUT[]) new WinUser.INPUT().toArray(2);
					setINPUT(in[0], 0, Character.highSurrogate(ch), WinUser.KEYBDINPUT.KEYEVENTF_UNICODE);
					setINPUT(in[1], 0, Character.lowSurrogate(ch), WinUser.KEYBDINPUT.KEYEVENTF_UNICODE);
					//setINPUT(in[2], 0, Character.lowSurrogate(ch), WinUser.KEYBDINPUT.KEYEVENTF_KEYUP);
					user32.SendInput(2, in, in[0].size());
				}
			}
			
			private void sendCommand(int ch) {
				WinUser.INPUT[] in = (WinUser.INPUT[]) new WinUser.INPUT().toArray(2);
				int sc = user32.MapVirtualKey(ch, 0);
				setINPUT(in[0], 0, sc, WinUser.KEYBDINPUT.KEYEVENTF_SCANCODE);
				setINPUT(in[1], 0, sc, WinUser.KEYBDINPUT.KEYEVENTF_SCANCODE | WinUser.KEYBDINPUT.KEYEVENTF_KEYUP);
				user32.SendInput(2, in, in[0].size());
			}

			private void setINPUT(INPUT input, int wVk, int wScan, int dwFlags) {
				input.type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
				input.input = new WinUser.INPUT.INPUT_UNION();
				input.input.setType(KEYBDINPUT.class);
				input.input.ki.wVk = new WinDef.WORD(wVk);
				input.input.ki.wScan = new WinDef.WORD(wScan);
				input.input.ki.dwFlags = new WinDef.DWORD(dwFlags);
			}

		};

	}

	@Override
	public void print(String printedString, int removeCount) {
		if(printedString != null) {
			queue.add(new Event(printedString, removeCount));
		}
	}

	@Override
	public void change(String printedString, String removedString) {
		// TODO Auto-generated method stub
	}

}
