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
package wysinwyg.fw.device;

import java.util.ArrayList;
import java.util.List;

import wysinwyg.fw.evaluator.Evaluator;

public abstract class AbstractDevice implements Device, DeviceListener {

	protected List<DeviceListener> list;
	protected Evaluator eva;

	public AbstractDevice() {
		list = new ArrayList<DeviceListener>(10);
	}

	@Override
	public void setEvaluator(Evaluator eva) {
		this.eva = eva;
	}

	@Override
	public void addDeviceListener(DeviceListener devListener) {
		list.add(devListener);
	}

	@Override
	public void removeDeviceListener(DeviceListener devListener) {
		list.remove(devListener);
	}

	@Override
	public void deviceEventOccurred(DeviceEvent e) {
		for (DeviceListener d : list) {
			d.deviceEventOccurred(e);
		}
	}

}
