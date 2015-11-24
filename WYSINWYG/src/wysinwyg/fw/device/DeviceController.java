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
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import wysinwyg.fw.Controller;
import wysinwyg.fw.Init;

/**
 * Implementing listeners for the {@linkplain DeviceModel} and for the
 * {@linkplain DeviceView}
 * 
 * @author FelfoldiB.
 *
 */
public class DeviceController implements Controller, ItemListener, DeviceListener {

	private DeviceView view;
	private DeviceModel model;

	/**
	 * Creating a controller by implementing listeners for the
	 * {@linkplain DeviceModel} and the {@linkplain DeviceView}.
	 * 
	 * @param model
	 *            the added model, can not be {@code null}.
	 * @param view
	 *            the added view, can not be {@code null}.
	 */
	public DeviceController(DeviceModel model, DeviceView view) {
		Objects.requireNonNull(model);
		Objects.requireNonNull(view);

		this.view = view;
		this.model = model;
		this.model.setController(this);

		if (model.getDevices() != null) {
			for (Device d : model.getDevices()) {
				if (d != null) {
					d.addDeviceListener(this);
				}
			}
		}

		view.getComboBox().addItemListener(this);
	}

	/**
	 * The {@linkplain DeviceView}'s card layout will be changed to the selected
	 * {@linkplain Device}'s view if the device is also {@linkplain Init
	 * implementing an MVC}.
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		CardLayout cl = (CardLayout) (view.getCardsPanel().getLayout());
		cl.show(view.getCardsPanel(),
				((Device) view.getComboBox().getSelectedItem()).getDisplayName());
	}

	/**
	 * Returns the selected Device from the combo box.
	 * 
	 * @return a class implementing the {@linkplain Device} interface.
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

	/**
	 * Called by the {@linkplain DeviceModel#setDevices(Device[])} when the data
	 * is changed. The {@linkplain DeviceView} will be changed according to the
	 * new data.
	 */
	protected void modelChanged() {
		SwingUtilities.invokeLater(createModelChangedUpdate());
	}

	private Runnable createModelChangedUpdate() {
		return new Runnable() {

			@Override
			public void run() {
				view.getCardsPanel().removeAll();
				view.getComboBox().removeAllItems();

				for (Device d : model.getDevices()) {
					if (d != null) {
						d.addDeviceListener(DeviceController.this);
						view.getComboBox().addItem(d);
						if (d instanceof Init) {
							Init init = (Init) d;
							if (init.getView() != null) {
								view.getCardsPanel().add(init.getView(), d.getDisplayName());
							} else {
								view.getCardsPanel().add(new JPanel(), d.getDisplayName());
							}
						}
					}
				}
			}
		};
	}

}
