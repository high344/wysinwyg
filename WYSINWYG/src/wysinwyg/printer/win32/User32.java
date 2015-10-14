package wysinwyg.printer.win32;

import com.sun.jna.Library;
import com.sun.jna.platform.win32.WinUser;

public interface User32 extends Library {

	public int SendInput(int nInputs, WinUser.INPUT[] pInputs, int cbsize);

	public int MapVirtualKey(int uCode, int uMapType);

}