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
package wysinwyg.fb.device.stentura;

import wysinwyg.fw.device.serial.SerialComm;
import wysinwyg.fw.device.serial.SerialController;

public class StenturaSerialComm implements SerialComm {

	private SerialController controller;
	
	public StenturaSerialComm() {
		// TODO Auto-generated constructor stub
	}
	
	protected void setSerialController(SerialController controller) {
		this.controller = controller;
	}
	
	@Override
	public void btnScanActionOccurred() {
		// TODO Auto-generated method stub
	}

	@Override
	public void btnSetActionOccurred() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void btnCancelOccurred() {
		// TODO Auto-generated method stub
		
	}

}
