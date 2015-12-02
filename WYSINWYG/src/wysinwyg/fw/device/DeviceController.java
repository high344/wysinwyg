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

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.SwingUtilities;

import wysinwyg.fw.Controller;
import wysinwyg.fw.Viewable;

/**
 * 
 * @author FelfoldiB.
 *
 */
public class DeviceController implements Controller, ItemListener, DeviceListener, Viewable {

	private DeviceView view;

	/**
	 * 
	 * @param devices
	 * @param view
	 */
	public DeviceController(List<Device> devices, DeviceView view) {
		this.view = view;

		if (!devices.isEmpty()) {
			if (SwingUtilities.isEventDispatchThread()) {
				addDevices(devices);
			} else {
				try {
					if (view.isVisible()) {
						SwingUtilities.invokeAndWait(createAddDevices(devices));
					} else {
						addDevices(devices);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		view.getComboBox().addItemListener(this);
	}

	private void addDevices(List<Device> devices) {
		for (Device d : devices) {
			if (d != null) {
				addDevice(d);
				d.addDeviceListener(this);
			}
		}
	}

	private Runnable createAddDevices(final List<Device> devices) {
		return new Runnable() {

			@Override
			public void run() {
				addDevices(devices);
			}
		};
	}

	/**
	 * 
	 */
	@Override
	public DeviceView getView() {
		return view;
	}

	/**
	 * 
	 * @param device
	 */
	public void addDevice(Device device) {
		view.getComboBox().addItem(device);
		view.getCardsPanel().add(device.getView(), device.getDisplayName());
	}

	/**
	 * The {@linkplain DeviceView}'s card layout will be changed to the selected
	 * {@linkplain Device}'s view.
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			CardLayout cl = (CardLayout) (view.getCardsPanel().getLayout());
			cl.show(view.getCardsPanel(), ((Device) e.getItem()).getDisplayName());
		}
	}

	/**
	 * 
	 * @return Returns the selected {@linkplain Device} from the combo box.
	 */
	public Device getSelectedDevice() {
		return ((Device) view.getComboBox().getSelectedItem());
	}

	/**
	 * The {@linkplain DeviceView}'s text area will be updated with the
	 * DeviceEvent.
	 */
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
