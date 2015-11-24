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
package wysinwyg.fb.printer.win32;

import com.sun.jna.Library;
import com.sun.jna.platform.win32.WinUser;

public interface User32 extends Library {

	public int SendInput(int nInputs, WinUser.INPUT[] pInputs, int cbsize);

	public int MapVirtualKey(int uCode, int uMapType);

}
