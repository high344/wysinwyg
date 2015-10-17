/*******************************************************************************
 * Copyright (c) 2015 Balázs Felföldi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Balázs Felföldi - initial API and implementation
 ******************************************************************************/
package wysinwyg.device;

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

import javax.swing.SwingUtilities;

import wysinwyg.Controller;

public class DeviceController implements Controller, ItemListener, DeviceListener {

	private DeviceView view;

	public DeviceController(DeviceModel model, DeviceView view) {
		Objects.requireNonNull(model);
		Objects.requireNonNull(view);

		this.view = view;

		for (Device d : model.getDevices()) {
			d.addDeviceListener(this);
		}

		view.getComboBox().addItemListener(this);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		CardLayout cl = (CardLayout) (view.getCardsPanel().getLayout());
		cl.show(view.getCardsPanel(),
				((Device) view.getComboBox().getSelectedItem()).getDisplayName());
	}

	public Device getSelectedDevice() {
		return ((Device) view.getComboBox().getSelectedItem());
	}

	@Override
	public void deviceEventOccurred(DeviceEvent e) {
		SwingUtilities.invokeLater(createTextAreaUpdate(e.toString()));
	}

	private Runnable createTextAreaUpdate(final String str) {
		return new Runnable() {

			@Override
			public void run() {
				view.getTextArea().append(str + "\n");
			}
		};
	}

}
