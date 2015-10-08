package wysinwyg.device;

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.SwingUtilities;

import wysinwyg.Controller;

public class DeviceController implements Controller, ItemListener, DeviceListener {

	private DeviceView view;

	DeviceController(Device[] devices, DeviceView view) {
		this.view = view;

		for (Device d : devices) {
			d.addDeviceListener(this);
			view.comboBox.addItem(d);
			view.cardsPanel.add(d.getView(), d.getDisplayName());
		}

		view.comboBox.addItemListener(this);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		CardLayout cl = (CardLayout) (view.cardsPanel.getLayout());
		cl.show(view.cardsPanel, ((Device) view.comboBox.getSelectedItem()).getDisplayName());
	}

	public Device getSelectedDevice() {
		return ((Device) view.comboBox.getSelectedItem());
	}

	@Override
	public void deviceEventOccurred(DeviceEvent e) {
		SwingUtilities.invokeLater(createTextAreaUpdate(e.toString()));
	}

	private Runnable createTextAreaUpdate(final String str) {
		return new Runnable() {

			@Override
			public void run() {
				view.textArea.append(str + "\n");
			}
		};
	}

}
