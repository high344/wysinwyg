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
package wysinwyg.device.serial.stentura;

import javax.swing.JComponent;

import wysinwyg.Controller;
import wysinwyg.Init;
import wysinwyg.device.Device;
import wysinwyg.device.DeviceListener;
import wysinwyg.utils.StenturaLayout;

public class StenturaDevice implements Init, Device {

	private StenturaLayout panel = new StenturaLayout();

	@Override
	public JComponent getView() {
		// TODO Auto-generated method stub
		return panel;
	}

	@Override
	public Controller getController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDisplayName() {
		// TODO Auto-generated method stub
		return "Stentura";
	}

	@Override
	public void addDeviceListener(DeviceListener devListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startDevice() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopDevice() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeDeviceListener(DeviceListener devListener) {
		// TODO Auto-generated method stub
		
	}

	
}
