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

import java.util.EventObject;

public class DeviceEvent extends EventObject {

	private static final long serialVersionUID = 3273199165035708065L;

	public DeviceEvent(Object source) {
		super(source);
	}

	@Override
	public String toString() {
		return "DeviceEvent [source=" + source + "]";
	}

}
