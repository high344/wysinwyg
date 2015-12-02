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

import wysinwyg.fw.Builder;

/**
 * Building a serial MCV environment for the given {@linkplain SerialComm}.
 * Calling the {@link #build()} method will create and connect the components.
 * If no serialComm is added a {@linkplain SerialCommSimulator} will be created.
 * If no {@linkplain SerialView} is added, the builder will create one.
 * 
 * @author FelfoldiB.
 *
 */
public class SerialBuilder implements Builder {

	private SerialComm comm;
	private SerialView view;

	/**
	 * Set the specified comm for the MVC.
	 * 
	 * @param comm
	 *            {@link SerialComm}.
	 * @return the {@link SerialBuilder}.
	 */
	public SerialBuilder setSerialModel(SerialComm comm) {
		this.comm = comm;
		return this;
	}

	/**
	 * Set the specified view for the MVC.
	 * 
	 * @param view
	 *            {@link SerialView}.
	 * @return the {@link SerialBuilder}.
	 */
	public SerialBuilder setSerialView(SerialView view) {
		this.view = view;
		return this;
	}

	/**
	 * Connects the given comm with the given view.
	 * 
	 * @return a new {@link SerialController} is created.
	 */
	@Override
	public SerialController build() {
		if (comm == null) {
			comm = new SerialCommSimulator();
		}
		if (view == null) {
			view = new SerialView();
		}
		return new SerialController(comm, view);
	}

}
