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

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;

import wysinwyg.fw.Controller;
import wysinwyg.fw.Viewable;
import wysinwyg.utils.NumberDocumentFilter;

/**
 * 
 * @author FelfoldiB.
 *
 */
public class SerialController implements Controller, ActionListener, Viewable, SerialOptions {

	private SerialComm serial;
	private SerialView view;

	public SerialController(SerialComm serial, SerialView view) {
		// TODO check serial null
		this.serial = serial;
		this.view = view;

		view.getBtnScan().addActionListener(this);
		view.getBtnSave().addActionListener(this);
		view.getBtnReload().addActionListener(this);

		((AbstractDocument) view.getTextFieldTimeout().getDocument()).setDocumentFilter(new NumberDocumentFilter());

		// TODO checkbox
		if (serial instanceof SerialCommSimulator) {
			SerialCommSimulator s = (SerialCommSimulator) serial;
			addBaudrateValues(s.baudrate);
			addDatabitsValues(s.databits);
			addStopBitsValues(s.stopbits);
			addParityValues(s.parity);
		}
	}

	@Override
	public SerialView getView() {
		return view;
	}

	public void addCommPortName(String str) {
		add(view.getComboBoxPortName(), str);
	}

	public void addCommPortNames(String[] str) {
		addAll(view.getComboBoxPortName(), str);
	}

	public void removeCommPortName(String str) {
		remove(view.getComboBoxPortName(), str);
	}

	public void removeAllCommPortName() {
		removeAll(view.getComboBoxPortName());
	}

	@Override
	public String getCommPortName() {
		return (String) view.getComboBoxPortName().getSelectedItem();
	}

	public void setCommPortName(String s) {
		setItem(view.getComboBoxPortName(), s, view.getPortJLabel());
	}

	public void addBaudrateValue(Integer i) {
		add(view.getComboBoxBaud(), i);
	}

	public void addBaudrateValues(Integer[] i) {
		addAll(view.getComboBoxBaud(), i);
	}

	public void removeBaudrateValue(Integer i) {
		remove(view.getComboBoxBaud(), i);
	}

	public void removeAllBaudrateValue() {
		removeAll(view.getComboBoxBaud());
	}

	@Override
	public Integer getBaudrateValue() {
		return (Integer) view.getComboBoxBaud().getSelectedItem();
	}

	public void setBaudrateValue(Integer i) {
		setItem(view.getComboBoxBaud(), i, view.getBaudrateJLabel());
	}

	public void addDatabitsValue(Integer i) {
		add(view.getComboBoxDataB(), i);
	}

	public void addDatabitsValues(Integer[] i) {
		addAll(view.getComboBoxDataB(), i);
	}

	public void removeDatabitsValue(Integer i) {
		remove(view.getComboBoxDataB(), i);
	}

	public void removeAllDatabitsValue() {
		removeAll(view.getComboBoxDataB());
	}

	@Override
	public Integer getDatabitsValue() {
		return (Integer) view.getComboBoxDataB().getSelectedItem();
	}

	public void setDatabitsValue(Integer i) {
		setItem(view.getComboBoxDataB(), i, view.getDataBitsJLabel());
	}

	public void addStopBitsValue(String str) {
		add(view.getComboBoxStopB(), str);
	}

	public void addStopBitsValues(String[] str) {
		addAll(view.getComboBoxStopB(), str);
	}

	public void removeStopBitsValue(String str) {
		remove(view.getComboBoxStopB(), str);
	}

	public void removeAllStopBitsValue() {
		removeAll(view.getComboBoxStopB());
	}

	@Override
	public String getStopBitsValue() {
		return (String) view.getComboBoxStopB().getSelectedItem();
	}

	public void setStopBitsValue(String s) {
		setItem(view.getComboBoxStopB(), s, view.getStopBitsJLabel());
	}

	public void addParityValue(String str) {
		add(view.getComboBoxParity(), str);
	}

	public void addParityValues(String[] str) {
		addAll(view.getComboBoxParity(), str);
	}

	public void removeParityValue(String str) {
		remove(view.getComboBoxParity(), str);
	}

	public void removeAllParityValue() {
		removeAll(view.getComboBoxParity());
	}

	@Override
	public String getParityValue() {
		return (String) view.getComboBoxParity().getSelectedItem();
	}

	public void setParityValue(String s) {
		setItem(view.getComboBoxParity(), s, view.getParityJLabel());
	}

	private <T> void add(final JComboBox<T> v, final T item) {
		if (SwingUtilities.isEventDispatchThread()) {
			v.addItem(item);
		} else {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					v.addItem(item);
				}
			});
		}
	}

	private <T> void addAll(final JComboBox<T> v, final T[] items) {
		if (SwingUtilities.isEventDispatchThread()) {
			for (T i : items) {
				v.addItem(i);
			}
		} else {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					for (T i : items) {
						v.addItem(i);
					}
				}
			});
		}
	}

	private <T> void remove(final JComboBox<T> v, final T item) {
		if (SwingUtilities.isEventDispatchThread()) {
			v.removeItem(item);
		} else {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					v.removeItem(item);
				}
			});
		}
	}

	private <T> void removeAll(final JComboBox<T> v) {
		if (SwingUtilities.isEventDispatchThread()) {
			v.removeAllItems();
		} else {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					v.removeAllItems();
				}
			});
		}
	}

	private <T> void setItem(final JComboBox<T> v, final T item, final JLabel lbl) {
		if (SwingUtilities.isEventDispatchThread()) {
			v.setSelectedItem(item);
			lbl.setText(item.toString());
		} else {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					v.setSelectedItem(item);
					lbl.setText(item.toString());
				}
			});
		}
	}

	public void setChckbxTimeoutSelected(boolean b) {
		setSelected(view.getChckbxTimeout(), b, view.getTimeoutJLabel());
	}

	@Override
	public boolean isChckbxTimeoutSelected() {
		return view.getChckbxTimeout().isSelected();
	}

	public void setTextFieldTimeout(final String s) {
		if (SwingUtilities.isEventDispatchThread()) {
			view.getTextFieldTimeout().setText(s);
			view.getTimeoutSecJLabel().setText(s);
		} else {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					view.getTextFieldTimeout().setText(s);
					view.getTimeoutSecJLabel().setText(s);
				}
			});
		}
	}

	@Override
	public String getTextFieldTimeout() {
		return view.getTextFieldTimeout().getText();
	}

	public void setChckbxRTSCTSSelected(boolean b) {
		setSelected(view.getChckbxRTSCTS(), b, view.getRTSCTSJLabel());
	}

	@Override
	public boolean isChckbxRTSCTSSelected() {
		return view.getChckbxRTSCTS().isSelected();
	}

	public void setCchckbxXonXoffSelected(boolean b) {
		setSelected(view.getChckbxXonXoff(), b, view.getXonXoffJLabel());
	}

	@Override
	public boolean isCchckbxXonXoffSelected() {
		return view.getChckbxXonXoff().isSelected();
	}

	private <T> void setSelected(final JCheckBox v, final boolean b, final JLabel lbl) {
		if (SwingUtilities.isEventDispatchThread()) {
			v.setSelected(b);
			lbl.setText(Boolean.toString(b));
		} else {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					v.setSelected(b);
					lbl.setText(Boolean.toString(b));
				}
			});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == view.getBtnScan()) {
			serial.btnScanActionOccurred();
		} else if (o == view.getBtnSave()) {
			serial.btnSaveActionOccurred();
		} else if (o == view.getBtnReload()) {
			serial.btnReloadActionOccurred();
		}
	}

}
