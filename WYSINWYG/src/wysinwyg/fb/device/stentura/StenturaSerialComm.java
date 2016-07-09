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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Properties;

import javax.swing.JOptionPane;

import wysinwyg.fb.WysinwygPath;
import wysinwyg.fw.device.serial.SerialComm;
import wysinwyg.fw.device.serial.SerialController;
import wysinwyg.fw.device.serial.SerialOptions;
import wysinwyg.utils.ErrorMessage;
import wysinwyg.utils.PropertiesUtils;

public class StenturaSerialComm implements SerialComm, SerialOptions {

	public static final File SERIAL_OPTIONS_file;

	static {
		SERIAL_OPTIONS_file = new File(WysinwygPath.getHome() + File.separator + "serial.properties");
		if (!SERIAL_OPTIONS_file.exists()) {

			Properties prop = new Properties();
			prop.put("commportV", "");
			prop.put("baudrateV", "");
			prop.put("databitsV", "");
			prop.put("stopbitsV", "");
			prop.put("parityV", "");
			prop.put("timeout", "");
			prop.put("timeoutV", "");
			prop.put("RTSCTS", "");
			prop.put("XonXoff", "");

			try {
				PropertiesUtils
						.saveProperties(
								prop,
								"Example: \nbaudrateV=9600\n timeoutV=2\n XonXoff=false\n commportV=/dev/ttyS0\n parityV=Non\n timeout=true\n databitsV=8\n RTSCTS=false\n stopbitsV=1",
								SERIAL_OPTIONS_file);
			} catch (IOException e) {
				ErrorMessage.show(e, Boolean.parseBoolean(System.getProperty("wysinwyg.devices")));
			}
		}
	}

	protected Integer[] baudrate = { 50, 75, 110, 134, 150, 200, 300, 600, 1200, 1800, 2400, 4800, 9600, 19200, 38400,
			57600, 115200 };

	protected Integer[] databits = { 5, 6, 7, 8 };

	protected String[] stopbits = { "1", "1.5", "2" };

	protected String[] parity = { "Non", "Even", "Odd", "Mark", "Space" };

	private SerialController controller;

	private Properties serialOptions;

	public StenturaSerialComm() throws IOException {
		serialOptions = PropertiesUtils.loadProperties(SERIAL_OPTIONS_file);
	}

	protected void setSerialController(SerialController controller) {
		this.controller = controller;
		if (controller != null) {
			this.controller.addBaudrateValues(baudrate);
			this.controller.addDatabitsValues(databits);
			this.controller.addStopBitsValues(stopbits);
			this.controller.addParityValues(parity);
			try {
				setSerialOptionPropsforSerialController();
			} catch (NumberFormatException | NullPointerException e) {

			}
		}
	}

	private void setSerialOptionPropsforSerialController() throws NumberFormatException, NullPointerException {
		if (controller != null) {
			String s = getCommPortName();
			int baud = getBaudrateValue();
			int datab = getDatabitsValue();
			String stop = getStopBitsValue();
			String par = getParityValue();
			boolean timeb = isChckbxTimeoutSelected();
			int time = 0;
			if (timeb) {
				time = Integer.parseInt((String) serialOptions.get("timeoutV"));
			}
			boolean rt = isChckbxRTSCTSSelected();
			boolean xo = isCchckbxXonXoffSelected();

			if (s != null) {
				controller.setCommPortName(s);
			}
			controller.setBaudrateValue(baud);
			controller.setDatabitsValue(datab);
			controller.setStopBitsValue(stop);
			controller.setParityValue(par);
			controller.setChckbxTimeoutSelected(timeb);
			if (timeb) {
				controller.setTextFieldTimeout(String.valueOf(time));
			} else {
				controller.setTextFieldTimeout("");
			}
			controller.setChckbxRTSCTSSelected(rt);
			controller.setCchckbxXonXoffSelected(xo);
		}
	}

	private boolean setSerialControllerOptionsForSerialOptionProps() {
		if (controller != null) {

			if (controller.isChckbxTimeoutSelected()) {
				try {
					serialOptions.put("timeoutV", String.valueOf(Integer.parseInt(controller.getTextFieldTimeout())));
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Add a timeout value!", "WhatYouStrokeIsNotWhatYouGet",
							JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
			} else {
				serialOptions.put("timeoutV", "");
			}

			serialOptions.put("commportV", getEmptyStringOrValue(controller.getCommPortName()));
			serialOptions.put("baudrateV", getEmptyStringOrValue(controller.getBaudrateValue().toString()));
			serialOptions.put("databitsV", getEmptyStringOrValue(controller.getDatabitsValue().toString()));
			serialOptions.put("stopbitsV", getEmptyStringOrValue(controller.getStopBitsValue()));
			serialOptions.put("parityV", getEmptyStringOrValue(controller.getParityValue()));
			serialOptions.put("timeout", getEmptyStringOrValue(String.valueOf(controller.isChckbxTimeoutSelected())));
			serialOptions.put("RTSCTS", getEmptyStringOrValue(String.valueOf(controller.isChckbxRTSCTSSelected())));
			serialOptions.put("XonXoff", getEmptyStringOrValue(String.valueOf(controller.isCchckbxXonXoffSelected())));
			return true;
		}
		return false;
	}

	private String getEmptyStringOrValue(String s) {
		return (s == null) ? "" : s;
	}

	@Override
	public String getCommPortName() {
		return (String) serialOptions.get("commportV");
	}

	@Override
	public Integer getBaudrateValue() throws NumberFormatException {
		return Integer.parseInt((String) serialOptions.get("baudrateV"));
	}

	@Override
	public Integer getDatabitsValue() throws NumberFormatException {
		return Integer.parseInt((String) serialOptions.get("databitsV"));
	}

	@Override
	public String getStopBitsValue() throws NullPointerException {
		return Objects.requireNonNull((String) serialOptions.get("stopbitsV"));
	}

	@Override
	public String getParityValue() throws NullPointerException {
		return Objects.requireNonNull((String) serialOptions.get("parityV"));
	}

	@Override
	public boolean isChckbxTimeoutSelected() throws NullPointerException {
		return Boolean.parseBoolean(Objects.requireNonNull((String) serialOptions.get("timeout")));
	}

	@Override
	public String getTextFieldTimeout() throws NumberFormatException {
		return String.valueOf(Integer.parseInt((String) serialOptions.get("timeoutV")));
	}

	@Override
	public boolean isChckbxRTSCTSSelected() throws NullPointerException {
		return Boolean.parseBoolean(Objects.requireNonNull((String) serialOptions.get("RTSCTS")));
	}

	@Override
	public boolean isCchckbxXonXoffSelected() throws NullPointerException {
		return Boolean.parseBoolean(Objects.requireNonNull((String) serialOptions.get("XonXoff")));
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
				if (setSerialControllerOptionsForSerialOptionProps()) {
					PropertiesUtils.saveProperties(serialOptions, SERIAL_OPTIONS_file);
					setSerialOptionPropsforSerialController();
				}
			} catch (Exception e) {
				ErrorMessage.show(e, Boolean.parseBoolean(System.getProperty("wysinwyg.devices")));
			}
		}
	}

	@Override
	public void btnReloadActionOccurred() {
		if (SERIAL_OPTIONS_file.exists()) {
			try {
				serialOptions = PropertiesUtils.loadProperties(SERIAL_OPTIONS_file);
				setSerialOptionPropsforSerialController();
			} catch (IOException e) {
				ErrorMessage.show(e, Boolean.parseBoolean(System.getProperty("wysinwyg.devices")));
			}
		} else {
			ErrorMessage.show(new FileNotFoundException(SERIAL_OPTIONS_file.getAbsolutePath() + " Doesn't exists"),
					Boolean.parseBoolean(System.getProperty("wysinwyg.devices")));
		}
	}

}
