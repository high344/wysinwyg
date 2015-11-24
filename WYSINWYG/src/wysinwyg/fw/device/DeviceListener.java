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

/**
 * The listener interface for receiving device events. The class that is
 * interested in processing a {@linkplain DeviceEvent} implements this
 * interface.
 * 
 * @author FelfoldiB.
 *
 */
public interface DeviceListener {

	/**
	 * Invoked when a device event occurs.
	 * 
	 * @param e
	 *            the occurred event.
	 */
	public void deviceEventOccurred(DeviceEvent e);

}
