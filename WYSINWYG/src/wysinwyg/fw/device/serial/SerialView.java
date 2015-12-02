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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author FelfoldiB.
 *
 */
public class SerialView extends JPanel {

	private static final long serialVersionUID = -4411609601292811983L;

	private JComboBox<String> comboBoxPortName;
	private JButton btnScan;
	private JComboBox<Integer> comboBoxBaud;
	private JComboBox<Integer> comboBoxDataB;
	private JComboBox<String> comboBoxStopB;
	private JComboBox<String> comboBoxParity;
	private JCheckBox chckbxTimeout;
	private JTextField textFieldTimeout;
	private JCheckBox chckbxRTSCTS;
	private JCheckBox chckbxXonXoff;
	private JButton btnSet;
	private JButton btnCancel;

	/**
	 * Creating the {@linkplain JPanel} specified in the {@linkplain SerialView}
	 * .
	 */
	public SerialView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Connection:", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		add(panel);
		panel.setLayout(new GridLayout(2, 2, 0, 0));

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel.add(panel_4);

		JLabel lblNewLabel = new JLabel("Port:");
		panel_4.add(lblNewLabel);

		comboBoxPortName = new JComboBox<String>();
		comboBoxPortName.setEditable(true);
		comboBoxPortName.setPreferredSize(new Dimension(100, 20));
		comboBoxPortName.setMinimumSize(new Dimension(50, 20));
		panel_4.add(comboBoxPortName);

		btnScan = new JButton("Scan");
		panel_4.add(btnScan);

		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_5);

		JLabel lblNewLabel_1 = new JLabel("Baudrate:");
		panel_5.add(lblNewLabel_1);
		comboBoxBaud = new JComboBox<Integer>();
		panel_5.add(comboBoxBaud);

		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setBorder(new TitledBorder(null, "Data Format:", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_1.setLayout(new GridLayout(3, 6, 5, 5));

		JLabel lblNewLabel_2 = new JLabel("Data Bits:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblNewLabel_2);
		comboBoxDataB = new JComboBox<Integer>();
		panel_1.add(comboBoxDataB);

		JLabel lblNewLabel_3 = new JLabel("Stop Bits:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblNewLabel_3);
		comboBoxStopB = new JComboBox<String>();
		panel_1.add(comboBoxStopB);

		JLabel lblNewLabel_4 = new JLabel("Parity:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblNewLabel_4);
		comboBoxParity = new JComboBox<String>();
		panel_1.add(comboBoxParity);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_2.setBorder(new TitledBorder(null, "Timeout:", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		add(panel_2);

		chckbxTimeout = new JCheckBox("Use Timeout");
		panel_2.add(chckbxTimeout);

		textFieldTimeout = new JTextField();
		panel_2.add(textFieldTimeout);
		textFieldTimeout.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("seconds");
		panel_2.add(lblNewLabel_5);

		JPanel panel_3 = new JPanel();
		add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Flow Control:", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_3.add(panel_7);

		chckbxRTSCTS = new JCheckBox("RTS / CTS");
		panel_7.add(chckbxRTSCTS);

		chckbxXonXoff = new JCheckBox("Xon / Xoff");
		panel_7.add(chckbxXonXoff);

		Box verticalBox = Box.createVerticalBox();
		panel_3.add(verticalBox);

		verticalBox.add(Box.createVerticalGlue());
		JPanel panel_6 = new JPanel();
		verticalBox.add(panel_6);
		verticalBox.add(Box.createVerticalGlue());
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));

		btnSet = new JButton("Set");
		panel_6.add(btnSet);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_6.add(horizontalStrut);

		btnCancel = new JButton("Cancel");
		panel_6.add(btnCancel);
	}

	/**
	 * 
	 * @return a {@linkplain JComboBox} containing the port names.
	 * @see {@linkplain SerialView}.
	 */
	protected JComboBox<String> getComboBoxPortName() {
		return comboBoxPortName;
	}

	/**
	 * 
	 * @return a {@linkplain JButton} for scanning.
	 * @see {@linkplain SerialView}.
	 */
	protected JButton getBtnScan() {
		return btnScan;
	}

	/**
	 * 
	 * @return a {@linkplain JComboBox} containing the baudrates.
	 * @see {@linkplain SerialView}.
	 */
	protected JComboBox<Integer> getComboBoxBaud() {
		return comboBoxBaud;
	}

	/**
	 * 
	 * @return a {@linkplain JComboBox} containing the databits.
	 * @see {@linkplain SerialView}.
	 */
	protected JComboBox<Integer> getComboBoxDataB() {
		return comboBoxDataB;
	}

	/**
	 * 
	 * @return a {@linkplain JComboBox} containing the stopbits.
	 * @see {@linkplain SerialView}.
	 */
	protected JComboBox<String> getComboBoxStopB() {
		return comboBoxStopB;
	}

	/**
	 * 
	 * @return a {@linkplain JComboBox} containing the parity values.
	 * @see {@linkplain SerialView}.
	 */
	protected JComboBox<String> getComboBoxParity() {
		return comboBoxParity;
	}

	/**
	 * 
	 * @return a {@linkplain JCheckBox} for the timeout.
	 * @see {@linkplain SerialView}.
	 */
	protected JCheckBox getChckbxTimeout() {
		return chckbxTimeout;
	}

	/**
	 * 
	 * @return a {@linkplain JTextField} for the timeout.
	 * @see {@linkplain SerialView}.
	 */
	protected JTextField getTextFieldTimeout() {
		return textFieldTimeout;
	}

	/**
	 * 
	 * @return a {@linkplain JCheckBox} for the RTS / CTS.
	 * @see {@linkplain SerialView}.
	 */
	protected JCheckBox getChckbxRTSCTS() {
		return chckbxRTSCTS;
	}

	/**
	 * 
	 * @return a {@linkplain JCheckBox} for the Xon / Xoff.
	 * @see {@linkplain SerialView}.
	 */
	protected JCheckBox getChckbxXonXoff() {
		return chckbxXonXoff;
	}

	/**
	 * 
	 * @return a {@linkplain JButton} for setting the values.
	 * @see {@linkplain SerialView}.
	 */
	protected JButton getBtnSet() {
		return btnSet;
	}

	/**
	 * 
	 * @return a {@linkplain JButton} for canceling the values.
	 * @see {@linkplain SerialView}.
	 */
	protected JButton getBtnCancel() {
		return btnCancel;
	}

}
