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
package wysinwyg.fw.device.serial;

/**
 * This is a simulator class for the serial MVC environment, containing default values for the baudrate, databits,
 * stopbits and parity. See {@linkplain SerialBuilder}, {@linkplain SerialController}, {@linkplain SerialView} for
 * reference.
 * 
 * @author FelfoldiB.
 *
 */
public class SerialCommSimulator implements SerialComm {

	protected Integer[] baudrate = { 50, 75, 110, 134, 150, 200, 300, 600, 1200, 1800, 2400, 4800, 9600, 19200, 38400,
			57600, 115200 };

	protected Integer[] databits = { 5, 6, 7, 8 };

	protected String[] stopbits = { "1", "1.5", "2" };

	protected String[] parity = { "Non", "Even", "Odd", "Mark", "Space" };

	@Override
	public void btnScanActionOccurred() {

	}

	@Override
	public void btnSaveActionOccurred() {

	}

	@Override
	public void btnReloadActionOccurred() {

	}

}
