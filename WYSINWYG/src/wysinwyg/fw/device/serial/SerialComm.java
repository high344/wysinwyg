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

import java.awt.event.ActionEvent;

/**
 * Interface for the serial communication in an MVC environment. See {@linkplain SerialBuilder},
 * {@linkplain SerialController}, {@linkplain SerialView} for reference.
 * 
 * @author FelfoldiB.
 *
 */
public interface SerialComm {

	/**
	 * If this interface is connected to a {@linkplain SerialView} by a {@linkplain SerialController} This method will
	 * be called by the Scan button's {@linkplain ActionEvent}.
	 */
	public void btnScanActionOccurred();

	/**
	 * If this interface is connected to a {@linkplain SerialView} by a {@linkplain SerialController} This method will
	 * be called by the Set button's {@linkplain ActionEvent}.
	 */
	public void btnSaveActionOccurred();

	/**
	 * If this interface is connected to a {@linkplain SerialView} by a {@linkplain SerialController} This method will
	 * be called by the Cancel button's {@linkplain ActionEvent}.
	 */
	public void btnReloadActionOccurred();

}
