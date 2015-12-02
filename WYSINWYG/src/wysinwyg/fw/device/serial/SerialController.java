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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.text.AbstractDocument;

import wysinwyg.fw.Controller;
import wysinwyg.fw.Viewable;
import wysinwyg.utils.NumberDocumentFilter;

/**
 * 
 * @author FelfoldiB.
 *
 */
public class SerialController implements Controller, ActionListener, Viewable {

	private SerialComm serial;
	private SerialView view;

	public SerialController(SerialComm serial, SerialView view) {
		this.serial = serial;
		this.view = view;

		view.getBtnScan().addActionListener(this);
		view.getBtnSet().addActionListener(this);
		view.getBtnCancel().addActionListener(this);

		((AbstractDocument) view.getTextFieldTimeout().getDocument())
				.setDocumentFilter(new NumberDocumentFilter());

		// TODO checkbox
		// TODO EDT
		if (serial instanceof SerialCommSimulator) {
			SerialCommSimulator s = (SerialCommSimulator) serial;
			for (Integer i : s.baudrate) {
				addBaudrateValue(i);
			}
			for (Integer i : s.databits) {
				addDatabitsValue(i);
			}
			for (String str : s.stopbits) {
				addStopBitsValue(str);
			}
			for (String str : s.parity) {
				addParityValue(str);
			}
		}
	}

	@Override
	public SerialView getView() {
		return view;
	}

	public void addCommPortName(String str) {
		add(view.getComboBoxPortName(), str);
	}

	public void removeCommPortName(String str) {
		remove(view.getComboBoxPortName(), str);
	}

	public void addBaudrateValue(Integer i) {
		add(view.getComboBoxBaud(), i);
	}

	public void removeBaudrateValue(Integer i) {
		remove(view.getComboBoxBaud(), i);
	}

	public void addDatabitsValue(Integer i) {
		add(view.getComboBoxDataB(), i);
	}

	public void removeDatabitsValue(Integer i) {
		remove(view.getComboBoxDataB(), i);
	}

	public void addStopBitsValue(String str) {
		add(view.getComboBoxStopB(), str);
	}

	public void removeStopBitsValue(String str) {
		remove(view.getComboBoxStopB(), str);
	}

	public void addParityValue(String str) {
		add(view.getComboBoxParity(), str);
	}

	public void removeParityValue(String str) {
		remove(view.getComboBoxParity(), str);
	}

	private <T> void add(JComboBox<T> v, T item) {
		v.addItem(item);
	}

	private <T> void remove(JComboBox<T> v, T item) {
		v.removeItem(item);
	}

	public boolean isChckbxTimeoutSelected() {
		return view.getChckbxTimeout().isSelected();
	}

	public String getTextFieldTimeout() {
		return view.getTextFieldTimeout().getText();
	}

	public boolean isChckbxRTSCTSSelected() {
		return view.getChckbxRTSCTS().isSelected();
	}

	public boolean isCchckbxXonXoffSelected() {
		return view.getChckbxXonXoff().isSelected();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == view.getBtnScan()) {
			serial.btnScanActionOccurred();
		} else if (o == view.getBtnSet()) {
			serial.btnSetActionOccurred();
		} else if (o == view.getBtnCancel()) {
			serial.btnCancelOccurred();
		}
	}

}
