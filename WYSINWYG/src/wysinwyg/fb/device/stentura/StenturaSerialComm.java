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
package wysinwyg.fb.device.stentura;

import gnu.io.CommPortIdentifier;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Properties;

import javax.swing.JOptionPane;

import wysinwyg.fb.WysinwygPath;
import wysinwyg.fw.device.serial.SerialComm;
import wysinwyg.fw.device.serial.SerialController;
import wysinwyg.utils.ErrorMessage;
import wysinwyg.utils.PropertiesUtils;

public class StenturaSerialComm implements SerialComm {

	protected Integer[] baudrate = { 50, 75, 110, 134, 150, 200, 300, 600, 1200, 1800, 2400, 4800, 9600, 19200, 38400,
			57600, 115200 };

	protected Integer[] databits = { 5, 6, 7, 8 };

	protected String[] stopbits = { "1", "1.5", "2" };

	protected String[] parity = { "Non", "Even", "Odd", "Mark", "Space" };

	private SerialController controller;

	protected void setSerialController(SerialController controller) {
		this.controller = controller;
		if (controller != null) {
			this.controller.addBaudrateValues(baudrate);
			this.controller.addDatabitsValues(databits);
			this.controller.addStopBitsValues(stopbits);
			this.controller.addParityValues(parity);
			try {
				btnReloadActionOccurred();
			} catch (Exception e) {
				ErrorMessage.show(e, Boolean.parseBoolean(System.getProperty("wysinwyg.devices")));
			}
		}
	}

	@Override
	public void btnScanActionOccurred() {
		if (controller != null) {
			Enumeration<?> e = CommPortIdentifier.getPortIdentifiers();
			controller.removeAllCommPortName();
			while (e.hasMoreElements()) {
				controller.addCommPortName(((CommPortIdentifier) e.nextElement()).getName());
			}
		}
	}

	@Override
	public void btnSaveActionOccurred() {
		if (controller != null) {
			try {
				File f = new File(WysinwygPath.getHome() + File.separator + "serial.properties");
				Properties prop = new Properties();
				if (controller.getCommPortName() != null) {
					prop.put("commportV", controller.getCommPortName());
				}
				prop.put("baudrateV", controller.getBaudrateValue().toString());
				prop.put("databitsV", controller.getDatabitsValue().toString());
				prop.put("stopbitsV", controller.getStopBitsValue());
				prop.put("parityV", controller.getParityValue());
				prop.put("timeout", String.valueOf(controller.isChckbxTimeoutSelected()));
				if (controller.isChckbxTimeoutSelected()) {
					try {
						prop.put("timeoutV", String.valueOf(Integer.parseInt(controller.getTextFieldTimeout())));
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Add a timeout value!", "WhatYouStrokeIsNotWhatYouGet",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}
				prop.put("RTSCTS", String.valueOf(controller.isChckbxRTSCTSSelected()));
				prop.put("XonXoff", String.valueOf(controller.isCchckbxXonXoffSelected()));

				PropertiesUtils.saveProperties(prop, f);
			} catch (Exception e) {
				ErrorMessage.show(e, Boolean.parseBoolean(System.getProperty("wysinwyg.devices")));
			}
		}
	}

	@Override
	public void btnReloadActionOccurred() {
		if (controller != null) {
			File f = new File(WysinwygPath.getHome() + File.separator + "serial.properties");
			if (f.exists()) {
				try {
					Properties prop = PropertiesUtils.loadProperties(f);

					String s = (String) prop.get("commportV");
					int baud = Integer.parseInt((String) prop.get("baudrateV"));
					int datab = Integer.parseInt((String) prop.get("databitsV"));
					String stop = Objects.requireNonNull((String) prop.get("stopbitsV"));
					String par = Objects.requireNonNull((String) prop.get("parityV"));
					boolean timeb = Boolean.parseBoolean(Objects.requireNonNull((String) prop.get("timeout")));
					int time = 0;
					if (timeb) {
						time = Integer.parseInt((String) prop.get("timeoutV"));
					}
					boolean rt = Boolean.parseBoolean(Objects.requireNonNull((String) prop.get("RTSCTS")));
					boolean xo = Boolean.parseBoolean(Objects.requireNonNull((String) prop.get("XonXoff")));

					if (s != null) {
						controller.setCommPortName(s);
					}
					controller.setBaudrateValue(baud);
					controller.setDatabitsValue(datab);
					controller.setStopBitsValue(stop);
					controller.setStopBitsValue(par);
					controller.setChckbxTimeoutSelected(timeb);
					if (timeb) {
						controller.setTextFieldTimeout(String.valueOf(time));
					} else {
						controller.setTextFieldTimeout("");
					}
					controller.setChckbxRTSCTSSelected(rt);
					controller.setCchckbxXonXoffSelected(xo);
				} catch (IOException e) {
					ErrorMessage.show(e, Boolean.parseBoolean(System.getProperty("wysinwyg.devices")));
				}
			}
		}
	}

}
