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
package wysinwyg.utils.serial;

public class SerialModel {

	protected static Integer[] baudrate = { 50, 75, 110, 134, 150, 200, 300, 600, 1200, 1800, 2400, 4800,
			9600, 19200, 38400, 57600, 115200 };

	protected static Integer[] databits = { 5, 6, 7, 8 };

	protected static Double[] stopbits = { 1d, 1.5d, 2d };

	protected static String[] parity = { "N", "E", "O", "M", "S" };

}
