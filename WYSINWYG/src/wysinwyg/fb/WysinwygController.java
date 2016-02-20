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
package wysinwyg.fb;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;

import wysinwyg.fw.Controller;
import wysinwyg.fw.Viewable;
import wysinwyg.fw.device.Device;
import wysinwyg.fw.device.DeviceController;

/**
 * 
 * @author FelfoldiB.
 *
 */
public class WysinwygController implements Controller, ActionListener, Viewable {

	public static boolean debug;
	
	private WysinwygView view;
	private DeviceController deviceController;
	private JToggleButton tglbtnStart;

	private Device runningDevice;

	public WysinwygController(WysinwygView view) {
		this.view = view;

		tglbtnStart = view.getControlPanel().getTglbtnStart();
		// remove the space key button event when focused
		tglbtnStart.getInputMap(JComponent.WHEN_FOCUSED).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "none");

		tglbtnStart.addActionListener(this);
	}

	@Override
	public WysinwygView getView() {
		return view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tglbtnStart) {
			if (tglbtnStart.isSelected()) {
				start();
			} else {
				stop();
			}
		}
	}

	public void setDeviceController(DeviceController deviceController) {
		this.deviceController = deviceController;
	}

	public void start() {
		runningDevice = deviceController.getSelectedDevice();
		runningDevice.startDevice();
	}

	public void stop() {
		runningDevice.stopDevice();
	}

}
